
import Livro.Livro;
import java.io.BufferedInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

/**
 *
 *
 */
public class GerarArrays {

    public GerarArrays() {

    }

    /**
     * cria um array de titulos aleatórios
     *
     * @param tam tamanho
     * @return array de títulos aleatórios
     * @throws java.io.FileNotFoundException
     * @throws java.lang.ClassNotFoundException
     */
    public String[] arrayTitulos(int tam) throws FileNotFoundException, IOException, ClassNotFoundException {
        
        BufferedInputStream fi = new BufferedInputStream(new FileInputStream("data/datasetOBJ.txt"));
        ObjectInputStream oi = new ObjectInputStream(fi);
        List posicoes = escolheNPosicoes(tam);
        String[] titulos = new String[tam];
        int progresso = 0, progressoAtual = 0;
        
        System.out.println("Construindo array de titulos com " + tam + " posicoes aleatorias...");
        
        for (int i = 0, j = 0; i < 1086954 && j < tam; i++) {
            
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
        return titulos;
    }
    // END ------------------

    /**
     * cria um array de livros aleatórios
     *
     * @param tam
     * @return array de livros aleatórios
     * @throws java.io.FileNotFoundException
     * @throws java.lang.ClassNotFoundException
     */
    public Livro[] arrayLivros(int tam) throws FileNotFoundException, IOException, ClassNotFoundException {

        BufferedInputStream fi = new BufferedInputStream(new FileInputStream("data/datasetOBJ.txt"));
        ObjectInputStream oi = new ObjectInputStream(fi);
        List posicoes = escolheNPosicoes(tam);//define um vetor com n posicoes
        Livro[] livros = new Livro[tam];
        int progresso = 0, progressoAtual = 0;

        System.out.println("Construindo array de Livro com " + tam + " posicoes aleatorias...");

        for (int i = 0, j = 0; i < 1086954 && j < tam; i++) {

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
        return livros;
    }
    // END ------------------

    /**
     * gera uma lista de n posições aleatórias entre 0 e 1086954
     * @param tam tamanho da lista
     * @return posições
     */
    private List escolheNPosicoes(int tam) {
        Random gerador = new Random();
        List posicoes = new ArrayList();
        for (int i = 0; i < tam; i++) {
            int aux = gerador.nextInt(1086955);
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
