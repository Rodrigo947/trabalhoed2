package Livro;

import java.io.Serializable;
import java.net.URL;
import java.util.Date;
import java.util.List;

/**
 *
 */
public class Livro implements Serializable {

    private int rank; // ranking na lista de mais vendidos
    private int id; // id único do livro atribuído pelo Bookdepository.com 
    private int ratingsAVG; // avaliação média 0-5
    private int ratingsCount; // número de avaliações   

    private float dimensionX; // largura cm
    private float dimensionY; // altura cm
    private float dimensionZ; // espessura mm
    private float weight; // peso

    private String edition; // edição
    private String editionStatment; // informação de edição
    private String description; //descrição do livro
    private String forAges; //classificação indicativa
    private String illustrationsNote; // nota de ilustrações    
    private String imprint; // selo
    private String isbn10; // ISBN-10
    private String isbn13; // ISBN-13
    private String publisher; // editora
    private String title; // título
    private String publicationPlace; // *id do local de publicação do livro(os locas de publicação estão em places.csv)
   
    private String[] categorie; //* lista de ids de categorias de livros (os nomes das categorias estão em categories.csv)
    private String[] author; //*vetor de ids de autores entre colchetes e separados por vírgulas (os nomes dos autores associados aos ids estão em authors.csv)
    private String[] format; //* id do formato do livro(os formatos possiveis estão em formats.csv)
    private String[] lang; // vetor de idiomas do livro
    

    private Date indexDate; // data de indexação
    private Date publicationDate; // data de publicação

    private URL url; // url relativa (https://bookdepository.com + url)

    public Livro(int rank, int id, int ratingsAVG, int ratingsCount, float dimensionX, float dimensionY, 
            float dimensionZ, float weight, String edition, String editionStatment, String description, 
            String forAges, String illustrationNotes, String imprint, String isbn10, String isbn13,
            String publisher, String title,String publicationPlace, String[] categorie,
            String[] author, String[]format,String[] lang, Date indexDate, Date publicationDate, URL url)
    {
        this.rank = rank;
        this.id = id;
        this.ratingsAVG = ratingsAVG;
        this.ratingsCount = ratingsCount;
        //========================
        this.dimensionX = dimensionX;
        this.dimensionY = dimensionY;
        this.dimensionZ = dimensionZ;
        this.weight = weight;
        //========================
        this.edition = edition;
        this.editionStatment = editionStatment;
        this.description = description;
        this.forAges = forAges;
        this.illustrationsNote = illustrationNotes;
        this.imprint = imprint;
        this.isbn10 = isbn10;
        this.isbn13 = isbn13;
        this.publisher = publisher;
        this.title = title;
        this.publicationPlace = publicationPlace;
        //========================
        this.categorie = categorie;
        this.author = author;
        this.format = format;
        this.lang = lang;
        //========================
        this.indexDate = indexDate;
        this.publicationDate = publicationDate;
        this.url = url;
    }

    public Livro() {

    }
    
    public String preencheString(String str, int tamMax){
        if(str.length() > tamMax){
            return str.substring(0, tamMax);
        }else if(str.length() < tamMax){
            String aux = new String();
            aux = str;
            for (int i = aux.length(); i < tamMax; i++) {
                aux.concat("*");//preenche a string com "*" caso a string passada seja menor que o tamanho maximo
            }
            return aux;
        }else{
            return str;
        }
    }
    
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String[] getAuthor() {
        return author;
    }

    public String[] getCategorie() {
        return categorie;
    }

    public String getDescription() {
        return description;
    }

    public float getDimensionX() {
        return dimensionX;
    }

    public float getDimensionY() {
        return dimensionY;
    }

    public float getDimensionZ() {
        return dimensionZ;
    }

    public String getEdition() {
        return edition;
    }

    public String getEditionStatment() {
        return editionStatment;
    }

    public String getForAges() {
        return forAges;
    }

    public String[] getFormat() {
        return format;
    }

    public int getId() {
        return id;
    }

    public String getIllustrationsNote() {
        return illustrationsNote;
    }

    public String getImprint() {
        return imprint;
    }

    public Date getIndexDate() {
        return indexDate;
    }

    public String getIsbn10() {
        return isbn10;
    }

    public String getIsbn13() {
        return isbn13;
    }

    public String[] getLang() {
        return lang;
    }

    public Date getPublicationDate() {
        return publicationDate;
    }

    public String getPublicationPlace() {
        return publicationPlace;
    }

    public String getPublisher() {
        return publisher;
    }

    public int getRank() {
        return rank;
    }

    public int getRatingsAVG() {
        return ratingsAVG;
    }

    public int getRatingsCount() {
        return ratingsCount;
    }

    public URL getUrl() {
        return url;
    }

    public float getWeight() {
        return weight;
    }
}
