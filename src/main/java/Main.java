import java.io.IOException;
import Livro.Livro;
import java.io.BufferedReader;
import java.io.FileReader;

public class Main {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        try (BufferedReader tamanhos = new BufferedReader(new FileReader("data/tamanhos.txt")); 
            BufferedReader fileQuantLinhas = new BufferedReader(new FileReader("data/quantLinhas.txt"))) {
            int quantLinhas = Integer.parseInt(fileQuantLinhas.readLine());
            GerarArrays gerar = new GerarArrays();
            for (int i = 0; i < 1; i++) {
                
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
        }
    }
}
