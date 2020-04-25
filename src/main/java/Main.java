
import java.io.IOException;
import Livro.Livro;
import Ordenacao.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import jdk.jfr.events.FileWriteEvent;

public class Main {

    public static void main(String[] args) throws IOException, ClassNotFoundException, CloneNotSupportedException, Exception {

        //Criando Arquivo de Objetos
        File datasetOBJ = new File("data/datasetOBJ.txt");
        File arqLinhas = new File("data/quantLinhas.txt");
        FileWriter saida = new FileWriter("resultados/saida.txt");
        if (!datasetOBJ.exists() || !arqLinhas.exists()) {
            System.out.println("Criando arquivo de Objetos....");
            File dataset = new File("data/dataset.csv");
            Leitura.datasetCSVtoOBJ(dataset);
        }

        try ( BufferedReader tamanhos = new BufferedReader(new FileReader("data/entrada.txt"));  BufferedReader fileQuantLinhas = new BufferedReader(new FileReader("data/quantLinhas.txt"))) {
            int quantExecucoes = Integer.parseInt(tamanhos.readLine());
            int quantLinhas = Integer.parseInt(fileQuantLinhas.readLine()); //quantidade de linhas que possui o dataset

            GerarArrays gerar = new GerarArrays();
            
            for (int i = 0, j = 0; i < quantExecucoes; i++) {
                
                int tam = Integer.parseInt(tamanhos.readLine());
                for (j = 0; j < 5; j++) {
                    String[] titulos = gerar.arrayTitulos(tam, j + 1, quantLinhas);
                    Livro[] livros = gerar.arrayLivros(tam, j + 1, quantLinhas);
                    new TreeSort(titulos, tam + " titulos", true, false, saida);
                    new TreeSort(livros, tam + " livros", true, false, saida);
                    System.out.println("i: " + (i+1) + "/" + quantExecucoes +" j: " + (j+1) + "/" + quantExecucoes);
                }
                
            }
            //END------------
        }
        saida.close();
    }

}
