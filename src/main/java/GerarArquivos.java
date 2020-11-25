
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;

import Livro.Livro;

public class GerarArquivos {
    /**
     * A partir do dataset.csv que está dentro da pasta data, gera um arquivo de objetos Livro chamado datasetOBJ.txt
     * e um arquivo chamado quantDados.txt que possui apenas a quantidade de registros encontrados no dataset.csv
     * @throws IOException
     */
    public static void criarArqObj() throws IOException, Exception {
        File datasetOBJ = new File("data/datasetOBJ.txt");
        File arqLinhas = new File("data/quantDados.txt");
        if (!datasetOBJ.exists() || !arqLinhas.exists()) {
            System.out.println("Criando arquivo de Objetos....");
            File dataset = new File("data/dataset.csv");
            Leitura.datasetCSVtoOBJ(dataset);
        }
        else{
            System.out.println("\nArquivo datasetOBJ.txt ou quantDados.txt ja estao na pasta data. Caso queira criar novamente, por favor exclua os arquivos manualmente!\n");
        }
    }
    /**
     * Gera todos os arquivos de arrays necessários para executar qualquer cenário do programa.
     * Os arquivos serão gerados a partir do arquivo entrada.txt e serão guardados no diretório
     * data/arrays/
     * @throws ClassNotFoundException
     * @throws Exception
     */
    public static void criarArqArrays() throws ClassNotFoundException, Exception {

        try (BufferedReader entrada = new BufferedReader(new FileReader("data/entrada.txt"));
             BufferedReader fileQuantDados = new BufferedReader(new FileReader("data/quantDados.txt")) ) {

            int quantDados = Integer.parseInt(fileQuantDados.readLine()); // quantidade de dados que possui o dataset
            int quantTamanhos = Integer.parseInt(entrada.readLine()); // quantos tipos de tamanhos estão no arquivo de entrada.txt
            int tam;
            GerarArrays gerar = new GerarArrays();
            String[] titulos;
            Livro[] livros;
            Long[] idsLivros;
            Integer[] nAleatoriosUnicos;

            for (int i = 0; i < quantTamanhos; i++) {
                tam = Integer.parseInt(entrada.readLine());
                File file = new File("data/arrays/" + tam);
                if (!file.exists()) { // Só cria os arquivos caso não exista a pasta do tamanho
                    file.mkdir();

                    for (int seed = 1; seed < 6; seed++) {
                        System.out.println("\n"+"----SEED "+seed+"----\n");
                        nAleatoriosUnicos = gerar.escolheNPosicoes(tam, seed, quantDados);
                        
                        titulos = gerar.arrayTitulos(tam, quantDados, nAleatoriosUnicos);
                        gerarArquivoArray(titulos, tam, seed);

                        if (tam <= 100000) { // Não gera um arquivo de livros para um vetor maior que 100.000
                            livros = gerar.arrayLivros(tam, quantDados, nAleatoriosUnicos);
                            gerarArquivoArray(livros, tam, seed);
                        }
                        
                        idsLivros = gerar.arrayIds(tam, quantDados, nAleatoriosUnicos);
                        gerarArquivoArray(idsLivros, tam, seed);
                    }
                }
                else{
                    System.out.println("\nJa existe uma pasta com vetores do tamanho "+tam+". Caso queira que esses arquivos sejam gerados novamente, exclua a pasta correspondente.\n");
                }
            }
            entrada.close();
            fileQuantDados.close();
        }

    }

    /**
     * Gera um arquivo que com uma lista de titulos escohidos aleatóriamente
     * @param array array a se gerado. Pode ser do tipo string ou um array de objetos Livro
     * @param tamanho tamanho do array  a se gerado
     * @param seed seed usada para escolher os livros 
     * @throws IOException
     */
    public static <T> void gerarArquivoArray(T[] array, int tamanho, int seed) throws IOException {
        if(array instanceof String[]){
            FileWriter arqSaida = new FileWriter("data/arrays/"+tamanho+"/S_"+tamanho+"_"+seed+".txt");
            PrintWriter gravarString = new PrintWriter(arqSaida);
            for (String string : (String[]) array) 
                gravarString.println(string);
            gravarString.close();
            arqSaida.close();
        }
        else if(array instanceof Long[]){
            FileWriter arqSaida = new FileWriter("data/arrays/"+tamanho+"/I_"+tamanho+"_"+seed+".txt");
            PrintWriter gravarString = new PrintWriter(arqSaida);
            for (Long id : (Long[]) array) 
                gravarString.println(id);
            gravarString.close();
            arqSaida.close();
        }
        else{
            FileOutputStream arqSaida = new FileOutputStream("data/arrays/"+tamanho+"/L_"+tamanho+"_"+seed+".txt");
            ObjectOutputStream gravarObj = new ObjectOutputStream(arqSaida);
            for (Livro livro : (Livro[]) array) 
                gravarObj.writeObject(livro);
            gravarObj.close();
            arqSaida.close();
        }    
        
    }
    // END ------------------

    
}