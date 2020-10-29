package Livro;

import java.util.ArrayList;

public class LivroAux {
    private int id; 
    private ArrayList<Integer> autores;

    public LivroAux(int id, ArrayList<Integer> autores){
        this.id = id;
        this.autores = autores;
    }

    public int getId() {
        return id;
    }

    public Integer getAutores(int index) {
        return autores.get(index);
    }


}

