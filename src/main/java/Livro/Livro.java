package Livro;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 *
 */
public class Livro implements Serializable,Cloneable {
    private int rank; // ranking na lista de mais vendidos
    private long id; // id único do livro atribuído pelo Bookdepository.com
    private float ratingsAVG; // avaliação média 0-5
    private int ratingsCount; // número de avaliações

    private float dimensionX; // largura cm
    private float dimensionY; // altura cm
    private float dimensionZ; // espessura mm
    private float weight; //  peso

    private String edition; // edição
    private String editionStatment; // informação de edição
    private String description; // descrição do livro
    private String forAges; // classificação indicativa
    private String illustrationsNote; //  nota de ilustrações
    private String imprint; //  selo
    private String isbn10; //  ISBN-10
    private String isbn13; //  ISBN-13
    private String title; //  título
    private String publicationPlace; //  *id do local de publicação do livro(os locas de publicação estão em
                                     // places.csv)
    private String format; //  * id do formato do livro(os formatos possiveis estão em formats.csv)
    private String lang; //  vetor de idiomas do livro
    private String url; //  url relativa (https://bookdepository.com + url)

    private String categories; //  * lista de ids de categorias de livros (os nomes das categorias estão em
                               // categories.csv)
    private String authors; //  *vetor de ids de autores entre colchetes e separados por vírgulas (os nomes
                            // dos autores associados aos ids estão em authors.csv)

    private Date indexDate; //  data de indexação
    private Date publicationDate; //  data de publicação

    public Livro() {
    
    }

    public void preencheLivro(Map<String, String> Atributos){
        this.rank = Integer.parseInt( trataNull( Atributos.get("bestsellers-rank").replaceAll(",", "") ) );
        this.id =  Long.parseLong( Atributos.get("id") );
        this.ratingsAVG =  Float.parseFloat( trataNull(Atributos.get("rating-avg")) );
        this.ratingsCount =  Integer.parseInt( trataNull(Atributos.get("rating-count")) );
        // ========================
        this.dimensionX = Float.parseFloat( trataNull(Atributos.get("dimension-x")) );
        this.dimensionY = Float.parseFloat( trataNull(Atributos.get("dimension-y")) );
        this.dimensionZ = Float.parseFloat( trataNull(Atributos.get("dimension-z")) );
        this.weight = Float.parseFloat( trataNull(Atributos.get("weight")) );
        // ========================
        this.edition = Atributos.get("edition");
        this.editionStatment = Atributos.get("edition-statement");
        this.description = Atributos.get("description");
        this.forAges = Atributos.get("for-ages");
        
        this.illustrationsNote = Atributos.get("illustrations-note");
        this.imprint = Atributos.get("imprint");
        this.isbn10 = Atributos.get("isbn10");
        this.isbn13 = Atributos.get("isbn13");
        
        this.title = Atributos.get("title");
        this.publicationPlace = Atributos.get("publication-place");
        this.format = Atributos.get("format");
        this.lang = Atributos.get("lang");
        
        this.url = "https://bookdepository.com" + Atributos.get("url");
        // ========================
        this.categories = Atributos.get("categories");
        this.authors = Atributos.get("authors");
        // ========================

       DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            this.indexDate = formatter.parse( trataNullDate( Atributos.get("index-date") ));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        formatter = new SimpleDateFormat("yyyy-MM-dd");
        try {
            this.publicationDate = formatter.parse( trataNullDate(Atributos.get("publication-date")) );
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

   
    /**
     * Trata valores nulos ou vazios para um atributo int,float,double, etc.
     * Pois a função Integer.parseInt, por exemplo, não funciona caso
     * o valor for nulo ou vazio 
     * 
     * @param atributo valor em string
     * 
     * @return retorna o proprio atributo caso for um valor diferente de null ou vazio,
     * caso contrário, retorna 0
     *
     */

    public String trataNull( String atributo ){
        if(atributo == null )
            return "0";
        else
            if(atributo.length()==0)
                return "0";
            else
                return atributo;
    }
    // END ------------------

    /**
     * Trata valores nulos ou vazios para um atributo Date
     * Pois não é a função formatter.parse, não funciona caso
     * o valor for nulo ou vazio  
     * 
     * @param atributo
     * @return
     */
    public String trataNullDate( String atributo ){
        if(atributo == null )
            return "0000-00-00 00:00:00";
        else
            if(atributo.length()==0)
                return "0000-00-00 00:00:00";
            else
                return atributo;
    }
    // END ------------------

    public String getLang() {
        return lang;
    }

    public String getFormat() {
        return format;
    }

    public String getAuthor() {
        return authors;
    }

    public String getCategorie() {
        return categories;
    }


    public String getTitle() {
        return title;
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

    public long getId() {
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

    public Date getPublicationDate() {
        return publicationDate;
    }

    public String getPublicationPlace() {
        return publicationPlace;
    }

    public int getRank() {
        return rank;
    }

    public float getRatingsAVG() {
        return ratingsAVG;
    }

    public int getRatingsCount() {
        return ratingsCount;
    }

    public String getUrl() {
        return url;
    }

    public float getWeight() {
        return weight;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Object clone()throws CloneNotSupportedException{  
        return super.clone();  
    }  

}
