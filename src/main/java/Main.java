
import java.io.IOException;
import Livro.Livro;
import Ordenacao.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class Main {

    public static void main(String[] args) throws IOException, ClassNotFoundException, CloneNotSupportedException, Exception {

        //Criando Arquivo de Objetos
        File datasetOBJ = new File("data/datasetOBJ.txt");
        File arqLinhas = new File("data/quantLinhas.txt");
        if (!datasetOBJ.exists() || !arqLinhas.exists()) {
            System.out.println("Criando arquivo de Objetos....");
            File dataset = new File("data/dataset.csv");
            Leitura.datasetCSVtoOBJ(dataset);
        }

        try ( BufferedReader tamanhos = new BufferedReader(new FileReader("data/entrada.txt"));  BufferedReader fileQuantLinhas = new BufferedReader(new FileReader("data/quantLinhas.txt"))) {
            int quantExecucoes = Integer.parseInt(tamanhos.readLine());
            int quantLinhas = Integer.parseInt(fileQuantLinhas.readLine()); //quantidade de linhas que possui o dataset
            
            GerarArrays gerar = new GerarArrays();
            
            for (int i = 0; i < quantExecucoes; i++ ) {
                int tam = Integer.parseInt(tamanhos.readLine());
                String[] titulos = gerar.arrayTitulos(tam, i+1, quantLinhas);
                Livro[] livros = gerar.arrayLivros(tam, i+1, quantLinhas);
                QuickSort quickSort = new QuickSort();
                
                quickSort.R_sort(titulos);
                quickSort.R_sort((Comparable[]) livros);
            }
            //END------------
        }

    }

}
