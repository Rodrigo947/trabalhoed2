
import Livro.Livro;
import java.io.BufferedInputStream;
import java.io.FileNotFoundException;
import Ordenacao.*;

import java.util.Random;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;


public class GerarArrays {

    long tempoInicial;
    long tempoFinal;

    public GerarArrays() {
        this.tempoInicial = System.currentTimeMillis();
        this.tempoFinal = 0;
    }

    /**
     * cria um array de titulos aleatórios
     *
     * @param tam tamanho da lista
     * @param quantDados quantidade de linhas do arquivo
     * @return array de títulos aleatórios
     * @throws java.io.FileNotFoundException
     * @throws java.lang.ClassNotFoundException
     */
    public String[] arrayTitulos(int tam, int quantDados, Integer posicoes[] ) throws FileNotFoundException, IOException, ClassNotFoundException, Exception {

        BufferedInputStream fi = new BufferedInputStream(new FileInputStream("data/datasetOBJ.txt"));
        ObjectInputStream oi = new ObjectInputStream(fi);
        String[] titulos = new String[tam];
        int progresso = 0, progressoAtual = 0, posicaoAtual;

        System.out.println("\nConstruindo array de titulos com " + tam + " posicoes aleatorias...");

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
        this.tempoFinal = System.currentTimeMillis() - this.tempoInicial;
        System.out.println("tempo de execucao arrayTitulos: " + tempoFinal);
        return titulos;
    }
    // END ------------------

    /**
     * cria um array de livros aleatórios
     *
     * @param tam tamanho da lista
     * @param quantDados quantidade de dados do arquivo
     * @return array de livros aleatórios
     * @throws java.io.FileNotFoundException
     * @throws java.lang.ClassNotFoundException
     */
    public Livro[] arrayLivros(int tam, int quantDados, Integer posicoes[] ) throws FileNotFoundException, IOException, ClassNotFoundException, Exception {

        BufferedInputStream fi = new BufferedInputStream(new FileInputStream("data/datasetOBJ.txt"));
        ObjectInputStream oi = new ObjectInputStream(fi);
        Livro[] livros = new Livro[tam];
        int progresso = 0, progressoAtual = 0, posicaoAtual;

        System.out.println("Construindo array de Livro com " + tam + " posicoes aleatorias...");
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
        this.tempoFinal = System.currentTimeMillis() - this.tempoInicial;
        System.out.println("tempo de execucao arrayLivros: " + tempoFinal);

        return livros;
    }
    // END ------------------

    /**
     * gera uma lista de n posições aleatórias entre 0 e a quantidade de dados
     * passada como parametro
     *
     * @param tam        tamanho da lista
     * @param seed       semente para gerar numeros aleatorios
     * @param quantDados quantidade de dados do arquivo
     * @return posições
     * @throws Exception
     */
    public Integer[] escolheNPosicoes(int tam, int seed, int quantDados) throws Exception {
        Random gerador = new Random();
        int n;
        gerador.setSeed(seed);
        Integer posicoes[] = new Integer[tam];
        for (int i = 0; i < tam; i++) {
            n=gerador.nextInt(quantDados);
            while( contains(posicoes,n,i) )
                n=gerador.nextInt(quantDados);
            posicoes[i]=n;
        }
        new TreeSort(posicoes, 0 + "", false, false, null);
        return posicoes;
    }
    // END ------------------

    private static boolean contains(Integer[] array, int v, int tam){
        for (int j = 0; j < tam; j++) {
            if(array[j] == v)
                return true;
        }
            
        return false; 
    }

    
}
