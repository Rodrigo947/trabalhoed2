package Ordenacao;

import Livro.Livro;

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
    long tempoInicial;
    long tempoFinal;

    public <T> TreeSort(T array[]) throws Exception {
        if (array.length > 0) {
            this.comparacoes = 1;
            this.copias = 0;
            this.tempoInicial = System.currentTimeMillis();

            if (array[0] instanceof String) {
                comparacoes++;
                this.tipo = "String";
            } else if (array[0] instanceof Livro) {
                comparacoes++;
                this.tipo = "Livro";
            } else {
                this.tipo = null;
            }

            for (int i = 0; i < array.length; i++) {
                insere(array[i]);
            }
            ordenadoRec(this.raiz);
            this.tempoFinal = System.currentTimeMillis() - this.tempoInicial;
            System.out.println("Tempo de execucao: " + this.tempoFinal);
            System.out.println("Numero de comparacoes: " + this.comparacoes);
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
            comparacoes++;
            raiz.direita = insereRec(raiz.direita, chave);
        }
        return raiz;
    }

    private void ordenadoRec(No raiz) throws Exception {

        if (raiz != null) {
            comparacoes++;
            ordenadoRec(raiz.esquerda);
            if (this.tipo.equals("String")) {
                comparacoes++;
                System.out.println(raiz.chave);
            } else if (this.tipo.equals("Livro")) {
                comparacoes++;
                System.out.println(((Livro)raiz.chave).getTitle());
            } else {
                throw new Exception("Não é possível imprimir um objeto do tipo " + raiz.chave.getClass());
            }
            ordenadoRec(raiz.direita);
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
     * @return -1 se object1 menor que object2, 0 se são iguais e 1 se object1 é
     * maior que object2.
     * @throws Exception
     */
    public <T> int comparadorEspecial(T object1, T object2) throws Exception {
        if (tipo.equals("String")) {
            comparacoes++;
            return ((String) object1).compareToIgnoreCase((String) object2);
        } else if (tipo.equals("Livro")) {
            comparacoes++;
            return ((Livro) object1).getTitle().compareToIgnoreCase(((Livro) object2).getTitle());
        } else {
            throw new Exception("Nao e possivel comparar os tipos " + object1.getClass() + " e " + object2.getClass());
        }
    }
}
