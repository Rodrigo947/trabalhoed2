package Ordenacao;

import java.io.FileWriter;
import java.io.PrintWriter;

public class TreeSort {

    public TreeSort() {
    }

    /**
     * Classe contendo os filhos da esquerda e da direita do no atual e a chave
     */
    class No {

        String chave;
        No esquerda;
        No direita;

        public No(String chave) {
            this.chave = chave;
            esquerda = direita = null;
        }
    }

    No raiz;
    int comparacoes;
    int copias;
    int posicao;
    int posição2 = 0;
    double tempoInicial;
    double tempoFinal;

    void insere(String key) throws Exception {
        this.raiz = insereRec(this.raiz, key);
    }

    No insereRec(No raiz, String chave) throws Exception {
        if (raiz == null) {
            comparacoes++;
            raiz = new No(chave);
            return raiz;
        }

        if (chave.compareToIgnoreCase(raiz.chave) < 0) {
            comparacoes++;
            raiz.esquerda = insereRec(raiz.esquerda, chave);
        } else if (chave.compareToIgnoreCase(raiz.chave) > 0){
            raiz.direita = insereRec(raiz.direita, chave);
        }

        return raiz;
    }

    private void ordenadoRec(No atual, String array[], int tam) throws Exception {
        if (atual != null) {
            comparacoes++;
            ordenadoRec(atual.esquerda, array, tam);
            array[this.posicao] = atual.chave;
            if (this.posicao < tam-1) {
                this.posicao++;
            }
            ordenadoRec(atual.direita, array, tam);
        }
    }
    
    
    public void sort(String[] array, int tam, int seed, FileWriter resultado, int imprimirVetor) throws Exception {
        
        PrintWriter gravarArq = new PrintWriter(resultado);
        System.out.println("Executando Treesort...");
        System.out.println("Array: " + tam + " Seed: " + seed);
        this.comparacoes++;
        if (array.length > 0) {
            this.comparacoes = 1;
            this.copias = 0;
            this.tempoInicial = System.currentTimeMillis();
            this.posicao = 0;
            
            for (int i = 0; i < array.length; i++) {
                this.comparacoes++;
                this.copias++;
                insere(array[i]);
            }
            ordenadoRec(this.raiz, array, tam);
            System.out.println("posicao = " + this.posição2);
            this.tempoFinal = (System.currentTimeMillis() - this.tempoInicial) / 1000;
            gravarArq.println("-------------Treesort-------------");
            gravarArq.println("Array de tamanho " + tam);
            gravarArq.println("Seed: " + seed);
            gravarArq.println("Comparação de chaves: " + this.comparacoes);
            gravarArq.println("Cópias de registro: " + this.copias);
            gravarArq.println("Tempo de execução: " + this.tempoFinal + " segundos");
            
            if (imprimirVetor == 1) {
                for (String string : array) {
                    gravarArq.write(string + "\n");
                }
            }
        }
        System.out.println("Finalizado \n");
    }
}
