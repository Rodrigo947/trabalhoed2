import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;

import Livro.Livro;
import java.io.BufferedReader;
import java.io.FileReader;

public class Leitura {

    /**
     * Converte o objeto para um vetor de bytes.
     * 
     * @param obj objeto a ser convertido
     *
     * 
     * @return vetor de bytes
     */
    public static byte[] toBytes(Livro obj) {

        byte[] bytes = null;

        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(obj);
            bytes = baos.toByteArray();
        }

        catch (IOException e) {
            // Erro na serialização
            e.printStackTrace();
        }
        return bytes;
    }
    // END ------------------

    /**
     * Converte o vetor bytes para um objeto
     *
     * @param bytes vetor de bytes a ser convertido
     * 
     * @return retorna um objeto do tipo livro
     */
    public static Livro toObjeto(byte[] bytes) {
        Livro obj = null;

        try {
            ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
            ObjectInputStream ois = new ObjectInputStream(bais);
            obj = (Livro) ois.readObject();
        } catch (IOException e) {
            System.out.println("Erro na deserialização");
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.out.println("Sequência de bytes não corresponde a classe do objeto");
            e.printStackTrace();
        }
        return obj;
    }
    // END ------------------

    /**
     * A partir do arquivo a função retorna a linha que pertence o byte passado
     *
     * @param file         arquivo que contem os dados em bytes
     * @param randomByte   byte alvo
     * @param sizeObjBytes tamanho da linha que armazena o objeto no arquivo
     * 
     * @return retorna um vetor de bytes
     */
    public static byte[] randomLineFile(File file, long randomByte, int sizeObjBytes) {
        FileInputStream fileInputStream = null;
        byte[] lineBytes = new byte[sizeObjBytes];
        try {
            // Procura em qual faixa de bytes está o randomByte
            boolean flag = false;
            long start = 0;
            long end = sizeObjBytes;
            while (!flag) {
                if (randomByte < start || randomByte > end) {
                    start = end + 1;
                    end += sizeObjBytes;
                } else
                    flag = true;
            }

            fileInputStream = new FileInputStream(file);
            if (start > 1)
                fileInputStream.skip(start - 1); // Pula o cursor de leitura para o começo da linha a qual pertence o
                                                 // randomByte

            fileInputStream.read(lineBytes, 0, sizeObjBytes); // lê a quant de bytes definida no sizeObjBytes e guarda
                                                              // no lineBytes começando pelo index 0 do array
            fileInputStream.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lineBytes;
    }
    // END ------------------

        /**
     * A partir do arquivo dataset a função retorna um arquivo com o atributo escolhido de cada registro,
     * além de retornar o tamanho da maior string e sua linha correspondente
     *
     * @param file         arquivo dataset
     * @param nomeArqSaida nome do arquivo de saida que conterá todos os registros do atributo escolhido
     * @param atributo     posição correspondente ao atributo requerido, por exemplo, caso o atributo 
     *                     desejado seja authors deve colocar 1, categories = 3 e assim sucessivamente
     *                     seguindo o cabeçalho do dataset
     * 
     * @return escreve no terminal o tamanho da maior string e a linha correspondente
     */
    public static void tamString(File file, String nomeArqSaida, int atributo) throws IOException {
        BufferedReader  sc = new BufferedReader (new FileReader(file));
        FileWriter arq = new FileWriter("data/"+nomeArqSaida+".txt");
        String linha, aux = "", description = "", id="", title="", editionStatement="";

        int quantAtributos = 0;
        long quantlinhas = 0, tamMaiorString = 0, maiorLinha = 0;
        
        int progresso = 0, progressoAtual = 0;
        System.out.println(progressoAtual + "%");

        PrintWriter gravarArq = new PrintWriter(arq);

        sc.readLine(); // pular o cabeçalho

        while ( (linha = sc.readLine())!=null ) {

            quantlinhas++;

            for (int i = 0; i < linha.length(); i++) {
                
                aux += linha.charAt(i);
          
                // Caso o dado não termine na mesma linha
                if (i == linha.length() - 1 && !linha.substring(linha.length()-2,linha.length()).matches("[0-9]\"") && !linha.substring(linha.length()-3,linha.length()).matches(",\"\"")  ) {
                    linha = sc.readLine();
                
                    while (linha.length() == 0 || linha.matches(" ")) //Caso a proxima linha seja apenas um espaço em branco encontrar uma linha que contenha alguma informção
                        linha = sc.readLine();

                    i = -1;
                } else {
                    if (i < linha.length() - 2) //Não se deve fazer a proxima comparação caso o caractere seja o penultimo ou ultimo item da linha
                        if (linha.charAt(i) == '\"' && linha.charAt(i + 1) == ',' && linha.charAt(i + 2) == '\"') { //Verifica é o fim de um atributo 
                            if(aux.matches("\".*\"") || quantAtributos==3 || quantAtributos==22 || quantAtributos==8){ //
                                quantAtributos++;
                                
                                //Existem registros que o atributo description possui "," no meio do dado o que invalida a divisão de atributos
                                //portanto, só armazena description quando o proximo atributo for um número
                                //com um devido padrão 
                                
                                //Tratamento de problemas para Description 
                                if(quantAtributos==4){
                                  
                                    if (aux.matches(",\"[0-9]+.[0-9]+\"") || aux.matches(",\"\"")) {
                                        i -= aux.length();
                                        aux = description;
                                        description = "";
                                    }
                                    else{
                                        description += aux;
                                        quantAtributos--;
                                      
                                    }
                                }
                                //END Tratamento de problemas para Description 

                                //Tratamento de problemas para Title 
                                if(quantAtributos==12)
                                    id=aux.substring(1, aux.length()-1);

                                if(quantAtributos==23){
                                    
                                    if (aux.matches(",\"/.*"+id+"\"") || aux.matches(",\"\"")) {
                                        i -= aux.length();
                                        aux = title;
                                        title = "";
                                    }
                                    else{
                                        title += aux;
                                        quantAtributos--;
                                      
                                    }
                                }
                                //END Tratamento de problemas para Title

                                //Tratamento de problemas para edition-statement 
                                if(quantAtributos==9){
                                    
                                    if (aux.matches(",\"[0-9].*\"") || aux.matches(",\"\"")) {
                                        i -= aux.length();
                                        aux = editionStatement;
                                        editionStatement = "";
                                    }
                                    else{
                                        editionStatement += aux;
                                        quantAtributos--;
                                      
                                    }
                                }
                                //END Tratamento de problemas para edition-statement

                                if(quantAtributos==atributo && description.equals("") && title.equals("") && editionStatement.equals("")){ //Caso o atributo seja o escolhido ao chamar função 
                                    
                                    if (tamMaiorString < aux.length()) {
                                        maiorLinha = quantlinhas;
                                        tamMaiorString = aux.length();
                                    }
                                    
                                    gravarArq.printf("%s%n", quantlinhas + "." + aux.substring(1, aux.length()-1) );
                                }
                                
                                
                                if(description.equals("") && title.equals("") && editionStatement.equals("")){
                                    i += 1; //Pula o caractere ,
                                }

                                aux = "";
                                    
                            }                           
                        }
                }
                
                if (i == linha.length() - 1 && atributo==25){
                    if (tamMaiorString < aux.length()) {
                        maiorLinha = quantlinhas;
                        tamMaiorString = aux.length();
                    }
                    gravarArq.printf("%s%n", quantlinhas + "." + aux.substring(1, aux.length()-1) );
                }
                
            }
            aux = "";
            quantAtributos = 0;

            progressoAtual = (int)(quantlinhas * 100) / 1086955; //imprimir progresso
            if(progresso < progressoAtual){
                progresso = progressoAtual;
                System.out.println(progressoAtual + "%");
            }

        }
        System.out.println("Tamanho da maior String: "+ (tamMaiorString-2)); //-2 para não contar com as aspas no começo e final do atributo
        System.out.println("Linha correspondente a maior string: "+ maiorLinha);
        gravarArq.printf("%s%n","Tamanho da maior String: "+ (tamMaiorString-2));
        gravarArq.printf("%s%n","Linha correspondente a maior string: "+ maiorLinha);
        
        arq.close();
        sc.close();       
    }
    // END ------------------


    public static void tamStringAuthors(File file, int atributo) throws IOException {
        BufferedReader sc = new BufferedReader(new FileReader(file));
        FileWriter arq = new FileWriter("data/authors.txt");
        String linha, aux = "";

        int progresso = 0, progressoAtual = 0;

        long quantLinhas = 0, tamMaiorString = 0, maiorLinha = 0;

        PrintWriter gravarArq = new PrintWriter(arq);
        while ((aux = sc.readLine()) != null) {
            quantLinhas++;
            for (int i = 0; i < aux.length(); i++) {
                if (i == aux.length() - 1) {
                    int j;
                    for (j = 0; j < aux.length() && aux.charAt(j) != ','; j++){
                        if (tamMaiorString < aux.length()) {
                            maiorLinha = quantLinhas;
                            tamMaiorString = aux.length();
                        }
                    }
                    linha = aux.substring(j-1, aux.length() - 1);
                    if(linha.length() > 3)
                        linha = linha.substring(3);
                    gravarArq.println(quantLinhas + "." + linha);
                }
            }
            progressoAtual = (int) (quantLinhas * 100) / 243703; // imprimir progresso
            if (progresso < progressoAtual) {
                progresso = progressoAtual;
                System.out.println(progressoAtual + "%");
            }
        }
        System.out.println("Tamanho da maior String: " + (tamMaiorString - 2)); // -2 para não contar com as aspas no
                                                                                // começo e final do atributo
        System.out.println("Linha correspondente a maior string: " + maiorLinha);
        gravarArq.printf("%s%n", "Tamanho da maior String: " + (tamMaiorString - 2));
        gravarArq.printf("%s%n", "Linha correspondente a maior string: " + maiorLinha);
        sc.close();
        gravarArq.close();
    }
    // END ------------------
    public static void tamStringCategories(File file, int atributo) throws IOException {
        BufferedReader sc = new BufferedReader(new FileReader(file));
        FileWriter arq = new FileWriter("data/categories.txt");
        String linha, aux = "";

        int progresso = 0, progressoAtual = 0;

        long quantLinhas = 0, tamMaiorString = 0, maiorLinha = 0;

        PrintWriter gravarArq = new PrintWriter(arq);
        while ((aux = sc.readLine()) != null) {
            quantLinhas++;
            for (int i = 0; i < aux.length(); i++) {
                if (i == aux.length() - 1) {
                    int j;
                    for (j = 0; j < aux.length() && aux.charAt(j) != ','; j++){
                        if (tamMaiorString < aux.length()) {
                            maiorLinha = quantLinhas;
                            tamMaiorString = aux.length();
                        }
                    }
                    linha = aux.substring(j-1, aux.length() - 1);
                    if(linha.length() > 3)
                        linha = linha.substring(3);
                    gravarArq.println(quantLinhas + "." + linha);
                }
            }
            progressoAtual = (int) (quantLinhas * 100) / 2720; // imprimir progresso
            if (progresso < progressoAtual) {
                progresso = progressoAtual;
                System.out.println(progressoAtual + "%");
            }
        }
        System.out.println("Tamanho da maior String: " + (tamMaiorString - 2)); // -2 para não contar com as aspas no
                                                                                // começo e final do atributo
        System.out.println("Linha correspondente a maior string: " + maiorLinha);
        gravarArq.printf("%s%n", "Tamanho da maior String: " + (tamMaiorString - 2));
        gravarArq.printf("%s%n", "Linha correspondente a maior string: " + maiorLinha);
        sc.close();
        gravarArq.close();
    }
    // END ------------------
}
