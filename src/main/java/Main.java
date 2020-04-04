import java.io.File;
import java.io.IOException;


public class Main {

    public static void main(String[] args) throws IOException {

        File dataset = new File("data/dataset.csv"); 
        Leitura.tamString(dataset,"isbn10",16);
        System.out.println("====isbn10===="); 
        Leitura.tamString(dataset,"isbn13",17);
        System.out.println("====isbn13====");
        Leitura.tamString(dataset,"lang",18);
        System.out.println("====lang====");
        Leitura.tamString(dataset,"publication-date",19);
        System.out.println("====publication-date====");
        Leitura.tamString(dataset,"publication-place",20);
        System.out.println("====publcation-place====");
        Leitura.tamString(dataset,"rating-avg",21);
        System.out.println("====ratings-avg====");
        Leitura.tamString(dataset,"rating-count",22);
        System.out.println("====ratings-count====");
        Leitura.tamString(dataset,"title",23);
        System.out.println("====title====");
        Leitura.tamString(dataset,"url",24);
        System.out.println("====url====");
        Leitura.tamString(dataset,"weight",25);
        System.out.println("====weight====");

        
    }
}
