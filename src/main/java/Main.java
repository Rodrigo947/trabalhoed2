
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import Livro.Livro;
import java.io.BufferedReader;
import java.io.FileReader;

public class Main {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        
        BufferedReader tamanhos = new BufferedReader(new FileReader("data/tamanhos.txt"));
        GerarArrays gerar = new GerarArrays();
        for (int i = 0; i < 1; i++) {
            int tamanho = Integer.parseInt(tamanhos.readLine());
            String[] titulos = gerar.arrayTitulos(tamanho);
            for (int j = 0; j < titulos.length; j++) {
                System.out.println(titulos[j]);
            }
            Livro[] livros = gerar.arrayLivros(tamanho);
            for (int j = 0; j < livros.length; j++) {
                System.out.println(livros[j].getId());
            }
        }

        /*File datasetOBJ = new File("data/datasetOBJ.txt");
        if(!datasetOBJ.exists()){
            System.out.println("Criando arquivo de Objetos"); //Arquivo de 1,3GB 
            File dataset = new File("data/dataset.csv");
            Leitura.datasetCSVtoOBJ(dataset);
        }
        
        int indiceLivro = 1086954; //0 a 1086954
        BufferedInputStream  fi = new BufferedInputStream (new FileInputStream("data/datasetOBJ.txt"));
        ObjectInputStream oi = new ObjectInputStream(fi);
        Livro livro;
        
        System.out.println("Procurando....");
        for (int i = 0; i < indiceLivro; i++) 
            oi.readObject();
        
        livro = (Livro) oi.readObject();
        System.out.println(livro.getAuthor());*/
        

        //Varios arquivos de objetos
        /*System.out.println("Criando arquivos de Objetos");
        File dataset = new File("data/dataset.csv");
        Leitura.datasetCSVtoOBJ(dataset);*/

        /*int indiceLivro = 1086955; //1 a 1086955
        FileInputStream fi = new FileInputStream( new File("objetos/"+indiceLivro+".txt")) ;
        ObjectInputStream oi = new ObjectInputStream(fi);
        Livro livro = (Livro) oi.readObject();

        System.out.println(livro.getAuthor());*/
    }
}
