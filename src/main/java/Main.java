import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import Livro.Livro;




public class Main {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
 
        File datasetOBJ = new File("data/datasetOBJ.txt");
        if(!datasetOBJ.exists()){
            System.out.println("Criando arquivo de Objetos"); //Arquivo de 1,3GB 
            File dataset = new File("data/dataset.csv");
            Leitura.datasetCSVtoOBJ(dataset);
        }
        
        
        int indiceLivro = 100; //0 a 1086954
        FileInputStream fi = new FileInputStream(datasetOBJ);
		ObjectInputStream oi = new ObjectInputStream(fi);
        Livro livro;
        
        System.out.println("Procurando....");
        for (int i = 0; i < indiceLivro-1; i++) 
            oi.readObject();    
        
        livro = (Livro) oi.readObject();
        
        System.out.println(livro.getAuthor());
        
    }
}
