
import Livro.Livro;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import Ordenacao.*;
import java.io.File;

import java.util.Random;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;


public class GerarArrays {


    /**
     * Cria um vetor do tipo string de titulos aleatórios
     *
     * @param tam tamanho da lista
     * @param quantDados quantidade de registros do arquivo dataset.csv
     * @param posicoes vetor de posicoes escolhidas aleatoriamente
     * @return array de títulos aleatórios
     * @throws java.io.FileNotFoundException
     * @throws java.lang.ClassNotFoundException
     */
    public String[] arrayTitulos(int tam, int quantDados, Integer posicoes[] ) throws FileNotFoundException, IOException, ClassNotFoundException, Exception {

        BufferedInputStream fi = new BufferedInputStream(new FileInputStream("data/datasetOBJ.txt"));
        ObjectInputStream oi = new ObjectInputStream(fi);
        String[] titulos = new String[tam];
        int progresso = 0, progressoAtual = 0, posicaoAtual;

        System.out.println("\nConstruindo array de STRING titulos com " + tam + " posicoes aleatorias...");

        for (int i = 0, j = 0; i < quantDados && j < tam; i++) {

            //verifica se a posição atual foi selecionada
            if (posicoes[j] == i) {
                titulos[j] = ((Livro) oi.readObject()).getTitle();
                posicaoAtual = j;
                j++;
                progressoAtual = (int) (j * 100) / tam;
                if (progresso < progressoAtual) {
                    progresso = progressoAtual;
                    System.out.println(progressoAtual + "%");
                }
                while (j < tam && posicoes[j] == i) {

                    // imprimir progresso
                    progressoAtual = (int) (j * 100) / tam;
                    if (progresso < progressoAtual) {
                        progresso = progressoAtual;
                        System.out.println(progressoAtual + "%");
                    }

                    titulos[j] = titulos[posicaoAtual];
                    j++;
                }

            } else {
                oi.readObject();
            }
        }
        oi.close();
        fi.close();
        return titulos;
    }
    // END ------------------

    /**
     * Cria um vetor do tipo livro com objetos aleatórios
     *
     * @param tam tamanho da lista
     * @param quantDados quantidade de dados do arquivo
     * @param posicoes vetor de posicoes escolhidas aleatoriamente
     * @throws java.io.FileNotFoundException
     * @throws java.lang.ClassNotFoundException
     */
    public Livro[] arrayLivros(int tam, int quantDados, Integer posicoes[] ) throws FileNotFoundException, IOException, ClassNotFoundException, Exception {

        BufferedInputStream fi = new BufferedInputStream(new FileInputStream("data/datasetOBJ.txt"));
        ObjectInputStream oi = new ObjectInputStream(fi);
        Livro[] livros = new Livro[tam];
        int progresso = 0, progressoAtual = 0, posicaoAtual;

        System.out.println("Construindo array de OBJETOS Livro com " + tam + " posicoes aleatorias...");
        for (int i = 0, j = 0; i < quantDados && j < tam; i++) {

            //verifica se a posição atual foi selecionada
            if (posicoes[j] == i) {
                livros[j] = (Livro) oi.readObject();
                posicaoAtual = j;
                j++;
                progressoAtual = (int) (j * 100) / tam;
                if (progresso < progressoAtual) {
                    progresso = progressoAtual;
                    System.out.println(progressoAtual + "%");
                }
                while (j < tam && posicoes[j] == i) {

                    // imprimir progresso
                    progressoAtual = (int) (j * 100) / tam;
                    if (progresso < progressoAtual) {
                        progresso = progressoAtual;
                        System.out.println(progressoAtual + "%");
                    }

                    livros[j] = (Livro) livros[posicaoAtual].clone();
                    j++;
                }

            } else {
                oi.readObject();
            }
        }
        oi.close();

        return livros;
    }
    // END ------------------

    /**
     * Gera uma lista ordenada de n posições aleatórias unicas entre 0 e a quantidade de dados
     * do dataset passada como parametro 
     *
     * @param tam        tamanho da lista
     * @param seed       semente para gerar numeros aleatorios
     * @param quantDados quantidade de dados do arquivo
     * @return vetor de posições
     * @throws Exception
     */
    public Integer[] escolheNPosicoes(int tam, int seed, int quantDados) throws Exception {
        Random gerador = new Random();
        int n;
        gerador.setSeed(seed);
        Integer posicoes[] = new Integer[tam];
        for (int i = 0; i < tam; i++) {
            n = gerador.nextInt(quantDados);
            while( contains(posicoes,n,i) )
                n = gerador.nextInt(quantDados);
            posicoes[i]=n;
        }
        QuickSort qs = new QuickSort();
        qs.R_QuicksortINT(0, tam-1, posicoes);
        return posicoes;
    }
    // END ------------------

    /**
     * Verifica se o valor v está contido no array
     * @param array array de pesquisa
     * @param v valor a ser procurado
     * @param tam tamnho do array
     * @return
     */
    private static boolean contains(Integer[] array, int v, int tam){
        for (int j = 0; j < tam; j++) {
            if(array[j] == v)
                return true;
        }
            
        return false; 
    }

    /**
     * Gera um vetor de strings a partir de um arquivo do tipo S_tamanho_seed.txt
     * @param file arquivo a ser lido
     * @param tam tamanho do vetor gerado
     * @return vetor de strings
     * @throws IOException
     */
    public static String[] arqParaVetorString(FileInputStream file, int tam) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(file));
        String[] linhas = new String[tam];
        for (int i = 0; i < tam; i++) {
            linhas[i] = br.readLine();
        }
        return linhas;
    }

    /**
     * Gera um vetor de objetos Livro a partir de um arquivo do tipo L_tamanho_seed.txt
     * @param file arquivo a ser lido
     * @param tam tamanho do vetor gerado
     * @return vetor de objetos Livro
     * @throws IOException
     */
    public static Livro[] arqParaVetorLivros(FileInputStream file, int tam)throws ClassNotFoundException, IOException {
        ObjectInputStream oi = new ObjectInputStream(new BufferedInputStream(file));
        Livro[] livros = new Livro[tam];
        for (int i = 0; i < tam; i++) 
            livros[i] = (Livro) oi.readObject();
        
        return livros;
    }

    
}
