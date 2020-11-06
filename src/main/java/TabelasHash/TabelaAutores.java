package TabelasHash;

import java.util.ArrayList;
import java.util.List;

import Livro.Autor;


public class TabelaAutores {
    List<Autor> autores;
    List<Integer> next;
    int tamanho;
    public TabelaAutores(int tamanho){
        this.autores = new ArrayList<>(tamanho);
        this.next = new ArrayList<>(tamanho);
        this.tamanho = tamanho;
        for(int i = 0; i < tamanho; i++){
            
            this.autores.add(new Autor());
            this.next.add(-1);
        }
        //Autor autor = new Autor()
        //this.autores.add(200, autor);
    }
}
