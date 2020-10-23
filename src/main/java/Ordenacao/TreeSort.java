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
    int comparacoes = 0;
    int copias = 0;
    int posicao = 0;
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
        } else if (chave.compareToIgnoreCase(raiz.chave) > 0) {
            raiz.direita = insereRec(raiz.direita, chave);
        }

        return raiz;
    }

    private void ordenadoRec(No atual, String array[], int tam) throws Exception {
        if (atual != null) {
            comparacoes++;
            ordenadoRec(atual.esquerda, array, tam);
            if (!array[this.posicao].equals(atual.chave)) {
                copias++;
                array[this.posicao] = atual.chave;
            }
            ordenadoRec(atual.direita, array, tam);
        }
    }

    public void sort(String[] array, int tam, int seed, FileWriter resultado, int imprimirVetor) throws Exception {
//        comparacoes = copias = 0;
        tempoInicial = tempoFinal = 0;
        PrintWriter gravarArq = new PrintWriter(resultado);
        System.out.println("Executando TreeSort...");
        System.out.println("Array: " + tam + " Seed: " + seed);
        this.comparacoes++;
        if (array.length > 0) {
            this.comparacoes = 1;
            this.tempoInicial = System.currentTimeMillis();

            for (int i = 0; i < array.length; i++) {
                this.comparacoes++;
                this.copias++;
                insere(array[i]);
            }
            
            ordenadoRec(this.raiz, array, tam);

            this.tempoFinal = (System.currentTimeMillis() - this.tempoInicial) / 1000;
            gravarArq.println("------------- TreeSort -------------");
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
