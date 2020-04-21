
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

            int quantLinhas = Integer.parseInt(fileQuantLinhas.readLine()); //quantidade de linhas que possui o dataset
            GerarArrays gerar = new GerarArrays();

            //Exemplo para rodar um algoritmo de ordenação
            String[] titulos = gerar.arrayTitulos(1000, 0, quantLinhas);
            Livro[] livros = gerar.arrayLivros(1000, 0, quantLinhas);

            String[] copiaTitulos = titulos.clone();
            Livro[] copiaLivros = new Livro[1000];

            for (int i = 0; i < livros.length; i++) {
                copiaLivros[i] = (Livro) livros[i].clone();
            }
            System.out.println("array titulos :");
            new TreeSort(copiaTitulos);
            System.out.println("\n\n\n=========================================================================\n\n\n");
            System.out.println("array livros: ");
            new TreeSort(copiaLivros);
            //END------------
        }

    }
}
