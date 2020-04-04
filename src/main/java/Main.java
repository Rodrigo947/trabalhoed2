import java.io.File;
import java.io.IOException;


public class Main {

    public static void main(String[] args) throws IOException {
     
        File dataset = new File("data/dataset.csv"); 
        String[] cabecalho = {"authors","bestsellers-rank","categories","description","dimension-x","dimension-y","dimension-z","edition","edition-statement","for-ages","format","id","illustrations-note","imprint","index-date","isbn10","isbn13","lang","publication-date","publication-place","rating-avg","rating-count","title","url","weight"};
        for (int i = 1; i <= cabecalho.length; i++) {
            Leitura.tamString(dataset,cabecalho[i-1],i);    
        }
        
       
        
    }
}
