package Ordenacao;

import Livro.Livro;
import java.io.File;
import java.io.FileWriter;

public class TreeSort {

    /**
     * Classe contendo os filhos da esquerda e da direita do no atual e a chave
     */
    class No {

        Object chave;
        No esquerda;
        No direita;

        public No(Object chave) {
            this.chave = chave;
            esquerda = direita = null;
        }
    }

    No raiz;
    String tipo;
    int comparacoes;
    int copias;//?
    int posicao;
    double tempoInicial;
    double tempoFinal;
    boolean escreverInformacoes;
    boolean escreverResultado;
    FileWriter resultado;

    public <T> TreeSort(T array[], String nomeArquivo, boolean escreverInformacoes, boolean escreverResultado, FileWriter informacoes) throws Exception {
        this.comparacoes++;
        if (escreverResultado) {
            this.resultado = new FileWriter("resultados/TreeSort - " + nomeArquivo + ".txt");
        }

        this.comparacoes++;
        if (array.length > 0) {
            this.comparacoes = 1;
            this.copias = 0;
            this.tempoInicial = System.currentTimeMillis();
            this.posicao = 0;
            this.escreverInformacoes = escreverInformacoes;
            this.escreverResultado = escreverResultado;

            if (array[0] instanceof String) {
                comparacoes++;
                this.tipo = "String";
            } else if (array[0] instanceof Livro) {
                comparacoes += 2;
                this.tipo = "Livro";
            } else if (array[0] instanceof Integer) {
                this.comparacoes += 3;
                this.tipo = "Integer";
            } else {
                this.comparacoes += 4;
                this.tipo = null;
            }

            for (int i = 0; i < array.length; i++) {
                this.comparacoes++;
                this.copias++;
                insere(array[i]);
            }
            
            ordenadoRec(this.raiz, array);
            this.tempoFinal = (System.currentTimeMillis() - this.tempoInicial) / 1000;
            if (this.escreverInformacoes) {
                informacoes.write("\nTreeSort - " + nomeArquivo);
                informacoes.write("\nTempo de execucao: " + this.tempoFinal + " segundos");
                informacoes.write("\nNumero de comparacoes: " + this.comparacoes + "\n");
                informacoes.write("\nNumero de Copias: " + this.copias + "\n");
            }
        }
        if (this.escreverResultado) {
            this.resultado.close();
        }
    }

    void insere(Object key) throws Exception {
        this.raiz = insereRec(this.raiz, key);
    }

    No insereRec(No raiz, Object chave) throws Exception {

        if (raiz == null) {
            comparacoes++;
            raiz = new No(chave);
            return raiz;
        }

        if (comparadorEspecial(chave, raiz.chave) <= 0) {
            comparacoes++;
            raiz.esquerda = insereRec(raiz.esquerda, chave);
        } else if (comparadorEspecial(chave, raiz.chave) > 0) {
            comparacoes += 2;
            raiz.direita = insereRec(raiz.direita, chave);
        }
        return raiz;
    }

    private <T> void ordenadoRec(No raiz, T array[]) throws Exception {

        if (raiz != null) {
            comparacoes++;
            ordenadoRec(raiz.esquerda, array);
            this.comparacoes++;
            if (this.tipo.equals("String")) {
                array[this.posicao] = (T) raiz.chave;
                this.comparacoes++;
                if (this.escreverResultado) {
                    this.resultado.write(array[this.posicao] + "\n");
                }
                this.posicao++;
            } else if (this.tipo.equals("Livro")) {
                this.comparacoes += 2;
                array[this.posicao] = (T) ((Livro) raiz.chave);
                if (this.escreverResultado) {
                    this.comparacoes++;
                    this.resultado.write(((Livro) array[this.posicao]).getTitle() + "\n");
                }
                this.posicao++;
            } else if (this.tipo.equals("Integer")) {
                this.comparacoes += 3;
                array[this.posicao] = (T) raiz.chave;
                if (this.escreverResultado) {
                    this.comparacoes++;
                    this.resultado.write(array[this.posicao] + "\n");
                }
                this.posicao++;
            } else {
                this.comparacoes += 4;
                throw new Exception("Não é possível imprimir um objeto do tipo " + raiz.chave.getClass());
            }
            ordenadoRec(raiz.direita, array);
        }

    }

    /**
     *
     * @param <T>
     * @param array
     */
    public <T> void sort(T array) {

    }

    /**
     *
     * @param object1
     * @param object2
     * @return maior que object2.
     * @throws Exception
     */
    public <T> int comparadorEspecial(T object1, T object2) throws Exception {
        if (this.tipo.equals("String")) {
            comparacoes++;
            return ((String) object1).compareToIgnoreCase((String) object2);
        } else if (this.tipo.equals("Livro")) {
            comparacoes += 2;
            return ((Livro) object1).getTitle().compareToIgnoreCase(((Livro) object2).getTitle());
        } else if (this.tipo.equals("Integer")) {
            comparacoes += 3;
            return ((Integer) object1).compareTo((Integer) object2);
        } else {
            this.comparacoes += 4;
            throw new Exception("Nao e possivel comparar os tipos " + object1.getClass() + " e " + object2.getClass());
        }
    }
}
