
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

import Livro.Livro;


public class Main {

    public static void main(String[] args) throws IOException {

        File dataset = new File("data/dataset.csv"); 
        Leitura.tamString(dataset,"descriptions",4);
        
    }
}
