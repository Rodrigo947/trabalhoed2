package Livro;

public class Autor {
    private Integer id; 
    private String nome;
    private int quantLivros;

    public Autor(){}

    public Autor(int id, String nome){
        this.id = id;
        this.nome = nome;
        this.quantLivros = 0;
    }

    public long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public int getQuantLivros() {
        return quantLivros;
    }

    public void incrementQuantLivros() {
        this.quantLivros++;
    }
    

}
