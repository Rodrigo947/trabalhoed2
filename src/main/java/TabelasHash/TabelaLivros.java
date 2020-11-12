package TabelasHash;

import static TabelasHash.FuncoesHash.Divisao;

import java.util.ArrayList;
import java.util.List;

import Livro.LivroAux;

public class TabelaLivros {
    List<LivroAux> livros;
    List<Integer> next;
    int tamanho, ultimo;

    public TabelaLivros(int tamanho) {
        this.livros = new ArrayList<>(tamanho);
        this.next = new ArrayList<>(tamanho);
        this.tamanho = tamanho;
        this.ultimo = tamanho - 1;
        for (int i = 0; i < tamanho; i++) {
            this.livros.add(new LivroAux());
            this.next.add(-2);
        }
    }

    public void add(LivroAux livro) {

        int posicao = Divisao(livro.getId(), tamanho);
        if (next.get(posicao) == -2) {
            livros.set(posicao, livro);
            next.set(posicao, -1);
        } else {
            while (next.get(posicao) != -1)
                posicao = next.get(posicao);

            for (int i = ultimo; i >= 0; i--) {
                if (next.get(i) == -2) {
                    livros.set(i, livro);
                    next.set(i, -1);
                    next.set(posicao, i);
                    ultimo = i - 1;
                    break;
                }
            }
        }
    }

    public void imprimeTabela() {
        for (int i = 0; i < tamanho; i++) {
            System.out.println(livros.get(i).getId() + " / " + next.get(i));
        }
    }

    public List<LivroAux> getLivros() {
        return this.livros;
    }

}
