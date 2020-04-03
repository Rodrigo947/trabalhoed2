import java.io.File;
import java.io.IOException;


public class Main {

    public static void main(String[] args) throws IOException {

        File dataset = new File("data/dataset.csv"); 
      
        Leitura.tamString(dataset,"descriptions",4);
        
    }
}
