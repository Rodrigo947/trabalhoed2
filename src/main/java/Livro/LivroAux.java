package Livro;

import java.util.List;

public class LivroAux {

    private long id;
    private List<Integer> autores;

    public LivroAux() {
    }

    public LivroAux(long id, List<Integer> autores) {
        this.id = id;
        this.autores = autores;
    }

    public long getId() {
        return id;
    }

    public Integer getAutor(int index) {
        return autores.get(index);
    }
    
    public List<Integer> getAutores() {
        return autores;
    }

    public void setAutores(List<Integer> autores) {
        this.autores = autores;
    }
}
