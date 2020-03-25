/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Livro;

import java.net.URL;
import java.util.Date;
import java.util.List;

/**
 *
 */
public class Livro {
    
    private int rank; // ranking na lista de mais vendidos
    private int categorie; // lista de ids de categorias de livros (os nomes das categorias estão em categories.csv)
    private int id; // id único do livro atribuído pelo Bookdepository.com
    private int publicationPlace; // id do local de publicação do livro(os locas de publicação estão em places.csv) 
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
    
    private List<String> lang; // lista de idiomas do livro
    private List<Integer> author; //lista de ids de autores entre colchetes e separados por vírgulas (os nomes dos autores associados aos ids estão em authors.csv)
    private List<Integer> format; // id do formato do livro(os formatos possiveis estão em formats.csv)
    
    private Date indexDate; // data de indexação
    private Date publicationDate; // data de publicação
       
    private URL url; // url relativa (https://bookdepository.com + url)
    
    public Livro(){
        
    }

    /**
     * @return the rank
     */
    public int getRank() {
        return rank;
    }

    /**
     * @param rank the rank to set
     */
    public void setRank(int rank) {
        this.rank = rank;
    }

    /**
     * @return the categorie
     */
    public int getCategorie() {
        return categorie;
    }

    /**
     * @param categorie the categorie to set
     */
    public void setCategorie(int categorie) {
        this.categorie = categorie;
    }


    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the publicationPlace
     */
    public int getPublicationPlace() {
        return publicationPlace;
    }

    /**
     * @param publicationPlace the publicationPlace to set
     */
    public void setPublicationPlace(int publicationPlace) {
        this.publicationPlace = publicationPlace;
    }

    /**
     * @return the ratingsAVG
     */
    public int getRatingsAVG() {
        return ratingsAVG;
    }

    /**
     * @param ratingsAVG the ratingsAVG to set
     */
    public void setRatingsAVG(int ratingsAVG) {
        this.ratingsAVG = ratingsAVG;
    }

    /**
     * @return the ratingsCount
     */
    public int getRatingsCount() {
        return ratingsCount;
    }

    /**
     * @param ratingsCount the ratingsCount to set
     */
    public void setRatingsCount(int ratingsCount) {
        this.ratingsCount = ratingsCount;
    }

    /**
     * @return the dimensionX
     */
    public float getDimensionX() {
        return dimensionX;
    }

    /**
     * @param dimensionX the dimensionX to set
     */
    public void setDimensionX(float dimensionX) {
        this.dimensionX = dimensionX;
    }

    /**
     * @return the dimensionY
     */
    public float getDimensionY() {
        return dimensionY;
    }

    /**
     * @param dimensionY the dimensionY to set
     */
    public void setDimensionY(float dimensionY) {
        this.dimensionY = dimensionY;
    }

    /**
     * @return the dimensionZ
     */
    public float getDimensionZ() {
        return dimensionZ;
    }

    /**
     * @param dimensionZ the dimensionZ to set
     */
    public void setDimensionZ(float dimensionZ) {
        this.dimensionZ = dimensionZ;
    }

    /**
     * @return the weight
     */
    public float getWeight() {
        return weight;
    }

    /**
     * @param weight the weight to set
     */
    public void setWeight(float weight) {
        this.weight = weight;
    }

    /**
     * @return the edition
     */
    public String getEdition() {
        return edition;
    }

    /**
     * @param edition the edition to set
     */
    public void setEdition(String edition) {
        this.edition = edition;
    }

    /**
     * @return the editionStatment
     */
    public String getEditionStatment() {
        return editionStatment;
    }

    /**
     * @param editionStatment the editionStatment to set
     */
    public void setEditionStatment(String editionStatment) {
        this.editionStatment = editionStatment;
    }


    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the forAges
     */
    public String getForAges() {
        return forAges;
    }

    /**
     * @param forAges the forAges to set
     */
    public void setForAges(String forAges) {
        this.forAges = forAges;
    }

    /**
     * @return the illustrationsNote
     */
    public String getIllustrationsNote() {
        return illustrationsNote;
    }

    /**
     * @param illustrationsNote the illustrationsNote to set
     */
    public void setIllustrationsNote(String illustrationsNote) {
        this.illustrationsNote = illustrationsNote;
    }

    /**
     * @return the imprint
     */
    public String getImprint() {
        return imprint;
    }

    /**
     * @param imprint the imprint to set
     */
    public void setImprint(String imprint) {
        this.imprint = imprint;
    }

    /**
     * @return the isbn10
     */
    public String getIsbn10() {
        return isbn10;
    }

    /**
     * @param isbn10 the isbn10 to set
     */
    public void setIsbn10(String isbn10) {
        this.isbn10 = isbn10;
    }

    /**
     * @return the isbn13
     */
    public String getIsbn13() {
        return isbn13;
    }

    /**
     * @param isbn13 the isbn13 to set
     */
    public void setIsbn13(String isbn13) {
        this.isbn13 = isbn13;
    }

    /**
     * @return the publisher
     */
    public String getPublisher() {
        return publisher;
    }

    /**
     * @param publisher the publisher to set
     */
    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the lang
     */
    public List<String> getLang() {
        return lang;
    }

    /**
     * @param lang the lang to set
     */
    public void setLang(List<String> lang) {
        this.lang = lang;
    }

    /**
     * @return the indexDate
     */
    public Date getIndexDate() {
        return indexDate;
    }

    /**
     * @param indexDate the indexDate to set
     */
    public void setIndexDate(Date indexDate) {
        this.indexDate = indexDate;
    }

    /**
     * @return the publicationDate
     */
    public Date getPublicationDate() {
        return publicationDate;
    }

    /**
     * @param publicationDate the publicationDate to set
     */
    public void setPublicationDate(Date publicationDate) {
        this.publicationDate = publicationDate;
    }

    /**
     * @return the url
     */
    public URL getUrl() {
        return url;
    }

    /**
     * @param url the url to set
     */
    public void setUrl(URL url) {
        this.url = url;
    }

    /**
     * @return the author
     */
    public List<Integer> getAuthor() {
        return author;
    }

    /**
     * @param author the author to set
     */
    public void setAuthor(List<Integer> author) {
        this.author = author;
    }

    /**
     * @return the format
     */
    public List<Integer> getFormat() {
        return format;
    }

    /**
     * @param format the format to set
     */
    public void setFormat(List<Integer> format) {
        this.format = format;
    }
}
