
import Livro.Livro;
import java.io.BufferedInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class GerarArrays {

    public GerarArrays() {

    }

    /**
     * cria um array de titulos aleatórios
     * @param tam tamanho da lista
     * @param seed semente para gerar numeros aleatorios
     * @param quantLinhas quantidade de linhas do arquivo
     * @return array de títulos aleatórios
     * @throws java.io.FileNotFoundException
     * @throws java.lang.ClassNotFoundException
     */
    public String[] arrayTitulos(int tam, int seed, int quantLinhas) throws FileNotFoundException, IOException, ClassNotFoundException {
        
        BufferedInputStream fi = new BufferedInputStream(new FileInputStream("data/datasetOBJ.txt"));
        ObjectInputStream oi = new ObjectInputStream(fi);
        List<Integer> posicoes = escolheNPosicoes(tam, seed, quantLinhas);
        String[] titulos = new String[tam];
        int progresso = 0, progressoAtual = 0;
        
        System.out.println("Construindo array de titulos com " + tam + " posicoes aleatorias...");
        
        for (int i = 0, j = 0; i < quantLinhas && j < tam; i++) {
            
            //verifica se a posição atual foi selecionada
            if (posicoes.contains(i) && j < tam) {

                // imprimir progresso
                progressoAtual = (int) (j * 100) / tam; 
                if (progresso < progressoAtual) {
                    progresso = progressoAtual;
                    System.out.println(progressoAtual + "%");
                }
                
                titulos[j] = ((Livro) oi.readObject()).getTitle();
                j++;
                
            } else {
                oi.readObject();
            }
        }
        oi.close();
        return titulos;
    }
    // END ------------------

    /**
     * cria um array de livros aleatórios
     * @param tam tamanho da lista
     * @param seed semente para gerar numeros aleatorios
     * @param quantLinhas quantidade de linhas do arquivo
     * @return array de livros aleatórios
     * @throws java.io.FileNotFoundException
     * @throws java.lang.ClassNotFoundException
     */
    public Livro[] arrayLivros(int tam, int seed, int quantLinhas) throws FileNotFoundException, IOException, ClassNotFoundException {

        BufferedInputStream fi = new BufferedInputStream(new FileInputStream("data/datasetOBJ.txt"));
        ObjectInputStream oi = new ObjectInputStream(fi);
        List<Integer> posicoes = escolheNPosicoes(tam, seed, quantLinhas);//define um vetor com n posicoes
        Livro[] livros = new Livro[tam];
        int progresso = 0, progressoAtual = 0;

        System.out.println("Construindo array de Livro com " + tam + " posicoes aleatorias...");
        for (int i = 0, j = 0; i < quantLinhas && j < tam; i++) {

            //verifica se a posição atual foi selecionada
            if (posicoes.contains(i)) {
                
                // imprimir progresso
                progressoAtual = (int) (j * 100) / tam;
                if (progresso < progressoAtual) {
                    progresso = progressoAtual;
                    System.out.println(progressoAtual + "%");
                }
                
                livros[j] = (Livro) oi.readObject();
                j++;
                
            } else {
                oi.readObject();
            }
        }
        oi.close();
        return livros;
    }
    // END ------------------

    /**
     * gera uma lista de n posições aleatórias entre 0 e a quantidade de linhas passada como parametro
     * @param tam tamanho da lista
     * @param seed semente para gerar numeros aleatorios
     * @param quantLinhas quantidade de linhas do arquivo
     * @return posições
     */
    private List<Integer> escolheNPosicoes(int tam, int seed, int quantLinhas) {
        Random gerador = new Random();
        gerador.setSeed(seed);
        List<Integer> posicoes = new ArrayList<>();
        for (int i = 0; i < tam; i++) {
            int aux = gerador.nextInt(quantLinhas);
            if (!posicoes.contains(aux)) {
                posicoes.add(aux);
            } else {
                i--;
            }
        }
        return posicoes;
    }
    // END ------------------
}
