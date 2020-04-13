
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;

import Livro.Livro;

public class Leitura {

    /**
     * A partir do arquivo dataset a função retorna um arquivo com os objetos para
     * cada dado encontrado
     *
     * @param file arquivo dataset
     *
     * @return escreve no terminal o tamanho da maior string e a linha
     *         correspondente
     * @throws java.io.IOException
     */
    public static void datasetCSVtoOBJ(File file) throws IOException {
        try (BufferedReader sc = new BufferedReader(new FileReader(file)); FileOutputStream arqSaida = new FileOutputStream("data/datasetOBJ.txt")) {
            ObjectOutputStream gravarObj = new ObjectOutputStream(arqSaida);
            Livro livro = new Livro();
            Map<String, String> arrayHashMap = new HashMap<String, String>();
            String linha, aux = "", description = "", id = "", title = "", editionStatement = "";
            String AtributosFinais[] = new String[25];

            long quantlinhas = 0;

            int progresso = 0, progressoAtual = 0, quantAtributos = 0;

            System.out.println(progressoAtual + "%");

            // ------Guardar cabeçalho
            linha = sc.readLine();
            String[] cabecalho = linha.split("\",\"");
            cabecalho[0] = cabecalho[0].substring(1); // Retirar a primeira aspas duplas da linha
            cabecalho[24] = cabecalho[24].substring(0, cabecalho[24].length() - 2); // Retirar a ultima aspas duplas da linha
            // ------END

            while ((linha = sc.readLine()) != null) {

                quantlinhas++;

                for (int i = 0; i < linha.length(); i++) {

                    aux += linha.charAt(i);

                    // Caso o dado não termine na mesma linha
                    if (i == linha.length() - 1 && !linha.substring(linha.length() - 2, linha.length()).matches("[0-9]\"") && !linha.substring(linha.length() - 3, linha.length()).matches(",\"\"")) {
                        linha = sc.readLine();

                        while (linha.length() == 0 || linha.matches(" ")) // Caso a proxima linha seja apenas um espaço em branco encontrar uma linha que contenha alguma informção
                            linha = sc.readLine();

                        i = -1;
                    } else {
                        if (i < linha.length() - 2) // Não se deve fazer a proxima comparação caso o caractere seja o
                        // penultimo ou ultimo item da linha
                        {
                            if (linha.charAt(i) == '\"' && linha.charAt(i + 1) == ',' && linha.charAt(i + 2) == '\"') { // Fim do atributo 
                                if (aux.matches("\".*\"") || quantAtributos == 3 || quantAtributos == 22 || quantAtributos == 8) { 
                                    quantAtributos++;

                                    // Existem registros que o atributo possui "," no meio do dado o que
                                    // invalida a divisão de atributos
                                    // portanto, só armazena os determinados atributos quando o proximo atributo
                                    // tiver um devido padrão

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
                                    if (quantAtributos == 12) {
                                        id = aux.substring(1, aux.length() - 1);
                                    }

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

                                    if (description.equals("") && title.equals("") && editionStatement.equals("")) 
                                        AtributosFinais[quantAtributos - 1] = aux.substring(1, aux.length() - 1);
                                    
                                    if (description.equals("") && title.equals("") && editionStatement.equals("")) {
                                        i += 1; // Pula o caractere ,
                                    }
                                    aux = "";

                                }
                            }
                        }
                    }

                    if (i == linha.length() - 1) {
                        AtributosFinais[24] = aux.substring(1, aux.length() - 1);
                    }

                }
                aux = "";
                quantAtributos = 0;

                for (int j = 0; j < cabecalho.length; j++) {
                    arrayHashMap.put(cabecalho[j], AtributosFinais[j]);
                }

                livro.preencheLivro(arrayHashMap);
                gravarObj.writeObject(livro);
                gravarObj.flush();
                gravarObj.reset();

                progressoAtual = (int) (quantlinhas * 100) / 1086955; // imprimir progresso
                if (progresso < progressoAtual) {
                    progresso = progressoAtual;
                    System.out.println(progressoAtual + "%");
                }

            }
            
            FileWriter  arqTamanhoLinhas = new FileWriter ("data/quantLinhas.txt");
            arqTamanhoLinhas.write(Long.toString(quantlinhas));
            arqTamanhoLinhas.close();
        }



        
    }
    // END ------------------

}
