
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
    public static void criarArqObj() throws IOException {
        File datasetOBJ = new File("data/datasetOBJ.txt");
        File arqLinhas = new File("data/quantDados.txt");
        if (!datasetOBJ.exists() || !arqLinhas.exists()) {
            System.out.println("Criando arquivo de Objetos....");
            File dataset = new File("data/dataset.csv");
            Leitura.datasetCSVtoOBJ(dataset);
        }
    }

    public static void criarArqArrays() throws ClassNotFoundException, Exception {

        try (BufferedReader entrada = new BufferedReader(new FileReader("data/entrada.txt"));
             BufferedReader fileQuantDados = new BufferedReader(new FileReader("data/quantDados.txt")) ) {

            int quantDados = Integer.parseInt(fileQuantDados.readLine()); // quantidade de dados que possui o dataset
            int quantTamanhos = Integer.parseInt(entrada.readLine()); // quantos tipos de tamanhos estão no arquivo de entrada.txt
            int tam;
            GerarArrays gerar = new GerarArrays();
            String[] titulos;
            Livro[] livros;
            Integer[] nAleatoriosUnicos;

            for (int i = 0; i < quantTamanhos; i++) {
                tam = Integer.parseInt(entrada.readLine());
                File file = new File("data/arrays/" + tam);
                if (!file.exists()) { // Só cria os arrays caso não exista a pasta do tamanho
                    file.mkdir();

                    for (int seed = 1; seed < 6; seed++) {

                        nAleatoriosUnicos = gerar.escolheNPosicoes(tam, seed, quantDados);

                        titulos = gerar.arrayTitulos(tam, quantDados, nAleatoriosUnicos);
                        gerarArquivoArray(titulos, tam, seed);

                        if (tam <= 100000) { // Não gera um arquivo de livros para um vetor maior que 100.000
                            livros = gerar.arrayLivros(tam, quantDados, nAleatoriosUnicos);
                            gerarArquivoArray(livros, tam, seed);
                        }

                    }
                }

            }
        }

    }

    /**
     * 
     * @param <T>
     * @param array
     * @param tamanho
     * @param seed
     * @throws IOException
     */
    public static <T> void gerarArquivoArray(T[] array, int tamanho, int seed) throws IOException {
        String tipo;
        if(array instanceof String[]){
            FileWriter arqSaida = new FileWriter("data/arrays/"+tamanho+"/S_"+tamanho+"_"+seed+".txt");
            PrintWriter gravarString = new PrintWriter(arqSaida);
            for (String string : (String[]) array) 
                gravarString.println(string);
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