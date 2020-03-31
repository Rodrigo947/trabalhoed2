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

    private List<String> categorie; //* lista de ids de categorias de livros (os nomes das categorias estão em categories.csv)
    private List<String> author; //*vetor de ids de autores entre colchetes e separados por vírgulas (os nomes dos autores associados aos ids estão em authors.csv)
    private List<String> format; //* id do formato do livro(os formatos possiveis estão em formats.csv)
    private List<String> lang; // vetor de idiomas do livro

    private Date indexDate; // data de indexação
    private Date publicationDate; // data de publicação

    private URL url; // url relativa (https://bookdepository.com + url)

    public Livro(int rank, int id, int ratingsAVG, int ratingsCount, float dimensionX, float dimensionY,
            float dimensionZ, float weight, String edition, String editionStatment, String description,
            String forAges, String illustrationNotes, String imprint, String isbn10, String isbn13,
            String publisher, String title, String publicationPlace, List<String> categorie,
            List<String> author, List<String> format, List<String> lang, Date indexDate, Date publicationDate, URL url) {
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
        this.edition = preencheString(edition, 0);
        this.editionStatment = preencheString(editionStatment, 0);
        this.description = preencheString(description, 0);
        this.forAges = preencheString(forAges, 0);
        this.illustrationsNote = preencheString(illustrationNotes, 0);
        this.imprint = preencheString(imprint, 0);
        this.isbn10 = preencheString(isbn10, 0);
        this.isbn13 = preencheString(isbn13, 0);
        this.publisher = preencheString(publisher, 0);
        this.title = preencheString(title, 0);
        this.publicationPlace = preencheString(publicationPlace, 0);
        //========================
        this.categorie = preencheListaString(categorie, 0, 0);
        this.author = preencheListaString(author, 0, 0);
        this.format = preencheListaString(format, 0, 0);
        this.lang = preencheListaString(lang, 0, 0);
        //========================
        this.indexDate = indexDate;
        this.publicationDate = publicationDate;
        this.url = url;
    }

    public Livro() {

    }

    private String preencheString(String str, int tamMax) {
        if (str.length() > tamMax) {
            return str.substring(0, tamMax);
        } else if (str.length() < tamMax) {
            String aux = new String();
            aux = str;
            for (int i = aux.length(); i < tamMax; i++) {
                aux.concat("*");//preenche a string com "*" caso a string passada seja menor que o tamanho maximo
            }
            return aux;
        } else {
            return str;
        }
    }

    private List<String> preencheListaString(List<String> str, int tamMaxStr, int tamMaxList) {
        List<String> aux = null;
        if (str.size() >= tamMaxList) {
            for (int i = 0; i < tamMaxList; i++) {
                aux.add(preencheString(str.get(i), tamMaxStr));//cria uma sublista do tamanho maximo passado
            }
            return aux;
        } else {
            for (int i = 0; i < tamMaxList; i++) {
                String strAux = null;
                if (i < str.size()) {
                    aux.add(preencheString(str.get(i), tamMaxStr));
                } else {
                    aux.add(preencheString(strAux, tamMaxStr));//preenche a lista com strings cheias de "*" caso a 
                }                                                                //lista passada seja menor que o tamanho maximo
            }
            return aux;
        }
    }

    public List<String> getAuthor() {
        return author;
    }

    public List<String> getCategorie() {
        return categorie;
    }

    public List<String> getFormat() {
        return format;
    }

    public List<String> getLang() {
        return lang;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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
