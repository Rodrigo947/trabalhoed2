package Ordenacao;

import Livro.Livro;
import java.io.FileWriter;

public final class TreeSort {

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
    long tempoInicial;
    long tempoFinal;
    FileWriter arq = new FileWriter("data/TreeSortOrdenado.txt");

    public <T> TreeSort(T array[]) throws Exception {

        if (array.length > 0) {
            this.comparacoes = 1;
            this.copias = 0;
            this.tempoInicial = System.currentTimeMillis();
            this.posicao = 0;
            
            if (array[0] instanceof String) {
                comparacoes++;
                this.tipo = "String";
            } else if (array[0] instanceof Livro) {
                comparacoes++;
                this.tipo = "Livro";
            } else if (array[0] instanceof Integer) {
                this.tipo = "Integer";
            } else {
                this.tipo = null;
            }

            for (int i = 0; i < array.length; i++) {
                insere(array[i]);
            }
            ordenadoRec(this.raiz, array);
            this.tempoFinal = System.currentTimeMillis() - this.tempoInicial;
            arq.write("Tempo de execucao: " + this.tempoFinal);
            arq.write("\nNumero de comparacoes: " + this.comparacoes);
        }
        arq.close();
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
            comparacoes++;
            raiz.direita = insereRec(raiz.direita, chave);
        }
        return raiz;
    }

    private <T> void ordenadoRec(No raiz, T array[]) throws Exception {

        if (raiz != null) {
            comparacoes++;
            ordenadoRec(raiz.esquerda, array);
            comparacoes += 3;
            if (this.tipo.equals("String")) {
                array[posicao] = (T)raiz.chave;
                arq.write(array[posicao]+"\n");
                posicao++;
            } else if (this.tipo.equals("Livro")) {
                array[posicao] = (T)((Livro) raiz.chave);
                arq.write(((Livro)array[posicao]).getTitle()+"\n");
                posicao++;
            } else if(this.tipo.equals("Integer")){
                array[posicao] = (T)raiz.chave;
                posicao++;
            }else{
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
            comparacoes++;
            return ((Livro) object1).getTitle().compareToIgnoreCase(((Livro) object2).getTitle());
        } else if (this.tipo.equals("Integer")) {
            comparacoes++;
            return ((Integer) object1).compareTo((Integer) object2);
        } else {
            throw new Exception("Nao e possivel comparar os tipos " + object1.getClass() + " e " + object2.getClass());
        }
    }
}
