package Livro;

public class Autores {
    private int id; 
    private String nome;
    private int quantLivros;

    public Autores(int id, String nome, int quantLivros){
        this.id = id;
        this.nome = nome;
        this.quantLivros = 0;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public int getQuantLivros() {
        return quantLivros;
    }

    public void incrementQuantLivros(int quantLivros) {
        this.quantLivros++;
    }
    

}
