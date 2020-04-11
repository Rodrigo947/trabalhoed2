import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;

import Livro.Livro;

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
     * A partir do arquivo dataset a função retorna um arquivo com os objetos
     * para cada dado encontrado
     * 
     * @param file arquivo dataset
     * 
     * @return escreve no terminal o tamanho da maior string e a linha
     *         correspondente
     */
    public static void datasetCSVtoOBJ(File file) throws IOException {
        BufferedReader sc = new BufferedReader(new FileReader(file));
        //FileOutputStream  arqSaida = new FileOutputStream ("data/datasetOBJ.txt");
        //ObjectOutputStream gravarObj = new ObjectOutputStream(arqSaida);
        Livro livro = new Livro();
        Map<String,String> arrayHashMap = new HashMap<String,String>();
        String linha, aux = "", description = "", id = "", title = "", editionStatement = "";
        String AtributosFinais[] = new String[25];

        long quantlinhas = 0;

        int progresso = 0, progressoAtual = 0, quantAtributos = 0;
        System.out.println(progressoAtual + "%");

        //------Guardar cabeçalho
        linha = sc.readLine();
        String[] cabecalho = linha.split("\",\"");
        cabecalho[0] = cabecalho[0].substring(1); //Retirar a primeira aspas duplas da linha
        cabecalho[24] = cabecalho[24].substring(0, cabecalho[24].length()-2); //Retirar a ultima aspas duplas da linha
        //------END

        /* while ((linha = sc.readLine()) != null) {*/
           for (int r = 0; r < 50; r++) {
                linha = sc.readLine();
            
            quantlinhas++;

            for (int i = 0; i < linha.length(); i++) {

                aux += linha.charAt(i);

                // Caso o dado não termine na mesma linha
                if (i == linha.length() - 1 && !linha.substring(linha.length() - 2, linha.length()).matches("[0-9]\"")
                        && !linha.substring(linha.length() - 3, linha.length()).matches(",\"\"")) {
                    linha = sc.readLine();

                    while (linha.length() == 0 || linha.matches(" ")) // Caso a proxima linha seja apenas um espaço em
                                                                      // branco encontrar uma linha que contenha alguma
                                                                      // informção
                        linha = sc.readLine();

                    i = -1;
                } else {
                    if (i < linha.length() - 2) // Não se deve fazer a proxima comparação caso o caractere seja o
                                                // penultimo ou ultimo item da linha
                        if (linha.charAt(i) == '\"' && linha.charAt(i + 1) == ',' && linha.charAt(i + 2) == '\"') { // Verifica
                                                                                                                    // é
                                                                                                                    // o
                                                                                                                    // fim
                                                                                                                    // de
                                                                                                                    // um
                                                                                                                    // atributo
                            if (aux.matches("\".*\"") || quantAtributos == 3 || quantAtributos == 22
                                    || quantAtributos == 8) { //
                                quantAtributos++;

                                // Existem registros que o atributo description possui "," no meio do dado o que
                                // invalida a divisão de atributos
                                // portanto, só armazena description quando o proximo atributo for um número
                                // com um devido padrão

                                // Tratamento de problemas para Description
                                if (quantAtributos == 4) {

                                    if (aux.matches(",\"[0-9]+.[0-9]+\"") || aux.matches(",\"\"")) {
                                        i -= aux.length();
                                        aux = description;
                                        description = "";
                                    } else {
                                        description += aux;
                                        quantAtributos--;

                                    }
                                }
                                // END Tratamento de problemas para Description

                                // Tratamento de problemas para Title
                                if (quantAtributos == 12)
                                    id = aux.substring(1, aux.length() - 1);

                                if (quantAtributos == 23) {

                                    if (aux.matches(",\"/.*" + id + "\"") || aux.matches(",\"\"")) {
                                        i -= aux.length();
                                        aux = title;
                                        title = "";
                                    } else {
                                        title += aux;
                                        quantAtributos--;

                                    }
                                }
                                // END Tratamento de problemas para Title

                                // Tratamento de problemas para edition-statement
                                if (quantAtributos == 9) {

                                    if (aux.matches(",\"[0-9].*\"") || aux.matches(",\"\"")) {
                                        i -= aux.length();
                                        aux = editionStatement;
                                        editionStatement = "";
                                    } else {
                                        editionStatement += aux;
                                        quantAtributos--;

                                    }
                                }
                                // END Tratamento de problemas para edition-statement

                                if (description.equals("") && title.equals("") && editionStatement.equals("")){

                                    AtributosFinais[quantAtributos-1] = aux.substring(1, aux.length() - 1);
                                    //gravarArq.printf("%s%n", quantlinhas + "." + aux.substring(1, aux.length() - 1));
                                } 
                                    
                                

                                if (description.equals("") && title.equals("") && editionStatement.equals(""))
                                    i += 1; // Pula o caractere ,
                                
                                aux = "";

                            }
                        }
                }

                if (i == linha.length() - 1) 
                    AtributosFinais[24]=aux.substring(1, aux.length() - 1);
                   
                

            }
            aux = "";
            quantAtributos = 0;

            

            for (int j = 0; j < cabecalho.length; j++) 
                arrayHashMap.put(cabecalho[j], AtributosFinais[j]);
            
            
            livro.preencheLivro(arrayHashMap);
            FileOutputStream  arqSaida = new FileOutputStream ("objetos/"+quantlinhas+".txt");
            ObjectOutputStream gravarObj = new ObjectOutputStream(arqSaida);
            gravarObj.writeObject(livro);
            gravarObj.flush();
            gravarObj.reset();
            arqSaida.close();
            
            progressoAtual = (int) (quantlinhas * 100) / 1086955; // imprimir progresso
            if (progresso < progressoAtual) {
                progresso = progressoAtual;
                System.out.println(progressoAtual + "%");
            }

        }

        
        //arqSaida.close();
        sc.close();
    }
    // END ------------------
    
  
}
