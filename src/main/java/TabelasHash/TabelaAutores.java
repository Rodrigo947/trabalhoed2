package TabelasHash;

import static TabelasHash.FuncoesHash.Divisao;
import java.util.ArrayList;
import java.util.List;

import Livro.Autor;

public class TabelaAutores {
    List<Autor> autores;
    List<Integer> next;
    int tamanho, ultimo;

    public TabelaAutores(int tamanho) {
        this.autores = new ArrayList<>(tamanho);
        this.next = new ArrayList<>(tamanho);
        this.tamanho = tamanho;
        this.ultimo = tamanho - 1;
        for (int i = 0; i < tamanho; i++) {

            this.autores.add(new Autor());
            this.next.add(-2);

        }
    }

    public void add(Autor autor) {

        int posicao = Divisao(autor.getId(), this.tamanho);
        if (this.next.get(posicao) == -2) {
            this.autores.set(posicao, autor);
            this.next.set(posicao, -1);
        } else {
            while (this.next.get(posicao) != -1)
                posicao = this.next.get(posicao);
            for (int i = ultimo; i >= 0; i--) {
                if (this.next.get(i) == -2) {
                    this.autores.set(i, autor);
                    this.next.set(i, -1);
                    this.next.set(posicao, i);
                    this.ultimo = i - 1;
                    break;
                }
            }
        }
    }

    public void imprimeTabela() {
        for (int i = 0; i < this.tamanho; i++) {
            System.out.println(this.autores.get(i).getId() + " / " + this.next.get(i));
        }
    }
}
