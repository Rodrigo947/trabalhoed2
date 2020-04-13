import java.io.IOException;
import Livro.Livro;
import Ordenacao.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class Main {

    public static void main(String[] args) throws IOException, ClassNotFoundException, CloneNotSupportedException {
        
        //Criando Arquivo de Objetos
        File datasetOBJ = new File("data/datasetOBJ.txt");
        File arqLinhas = new File("data/quantLinhas.txt");
        if(!datasetOBJ.exists() || !arqLinhas.exists()){
            System.out.println("Criando arquivo de Objetos....");
            File dataset = new File("data/dataset.csv");
            Leitura.datasetCSVtoOBJ(dataset);
        }

        
        
        try (BufferedReader tamanhos = new BufferedReader(new FileReader("data/entrada.txt")); 
            BufferedReader fileQuantLinhas = new BufferedReader(new FileReader("data/quantLinhas.txt"))) {
            
            int quantLinhas = Integer.parseInt(fileQuantLinhas.readLine()); //quantidade de linhas que possui o dataset
            GerarArrays gerar = new GerarArrays();
            
            
            //Gera e imprime os vetores - APENAS PARA TESTE
            for (int i = 0; i < Integer.parseInt(tamanhos.readLine()); i++) {
                
                //define os tamanhos dos arrays a serem criados
                int tamanho = Integer.parseInt(tamanhos.readLine());
                //cria e imprime array de titulos
                String[] titulos = gerar.arrayTitulos(tamanho, i, quantLinhas);
                for (String titulo : titulos) {
                    System.out.println(titulo);
                }
                //cria e imprime array de Livros
                Livro[] livros = gerar.arrayLivros(tamanho, 2 * i, quantLinhas);
                for (Livro livro : livros) {
                    System.out.println(livro.getId());
                }
                
            }
            
            
            //Exemplo para rodar um algoritmo de ordenação
            String[] titulos = gerar.arrayTitulos(1000, 123456, quantLinhas);
            Livro[] livros = gerar.arrayLivros(1000, 654321, quantLinhas);

            String[] copiaTitulos = titulos.clone();
            Livro[] copiaLivros = new Livro[1000];

            for (int i = 0; i < livros.length; i++) 
                copiaLivros[i] = (Livro) livros[i].clone();
            

            QuickSort.QuickSortRecursivo(copiaTitulos,1000);
            QuickSort.QuickSortRecursivo(copiaLivros,1000);
            //END------------
        
        }
         

    }
}
