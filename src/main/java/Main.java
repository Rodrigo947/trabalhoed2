import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Scanner;


import Livro.Livro;
import Ordenacao.*;

public class Main {

    public static void cenario1(BufferedReader entrada, int imprimirVetor) throws Exception {
        int quantTamanhos = Integer.parseInt(entrada.readLine()); // quantos tipos de tamanhos estão no arquivo de entrada.txt
        int tam;
        String[] strings;
        Livro[] livros;
        FileInputStream fileLivro,fileString;
        QuickSort quicksort = new QuickSort();
        
        for (int i = 0; i < quantTamanhos; i++) { 
            tam = Integer.parseInt(entrada.readLine());
            if(tam<=100000){ //No cenario 1 são vetores de até 100.000 posições
                //Arquivos q contem tds os resultados das seeds no tamanho determinado por tam
                ////FileWriter arqResultadosString = new FileWriter("resultados/QuickSort/Recursivo/S_" + tam + ".txt");
                ////FileWriter arqResultadosLivros = new FileWriter("resultados/QuickSort/Recursivo/L_" + tam + ".txt");

                //FileWriter arqResultadosFuncaoTeste = new FileWriter("resultados/funcaoTeste/S_" + tam + ".txt");
                FileWriter arqResultadosQuickSortString = new FileWriter("resultados/QuickSort/cenario1/Recursivo/S_" + tam + ".txt");
                FileWriter arqResultadosQuickSortObj = new FileWriter("resultados/QuickSort/cenario1/Recursivo/L_" + tam + ".txt");
                
                for (int seed = 1; seed < 6; seed++) {
                    fileString = new FileInputStream("data/arrays/"+tam+"/S_"+tam+"_"+seed+".txt");
                    strings = GerarArrays.arqParaVetorString(fileString, tam); //gerando o array de acordo a partir do arquivo de acordo com a seed

                    fileLivro = new FileInputStream("data/arrays/"+tam+"/L_"+tam+"_"+seed+".txt");
                    livros = GerarArrays.arqParaVetorLivros(fileLivro, tam); 

                    //Exemplo de chamada de função de ordenação
                    //Essa função está no final da classe "ordenacaoTeste"
                  //  ordenacaoTeste.funcaoTeste(strings, tam, seed, arqResultadosFuncaoTeste, imprimirVetor);
                    quicksort.R_sort(strings, tam, seed, arqResultadosQuickSortString, imprimirVetor);
                    quicksort.R_sort(livros, tam, seed, arqResultadosQuickSortObj, imprimirVetor);
                }

                //arqResultadosFuncaoTeste.close();
                arqResultadosQuickSortString.close();
                arqResultadosQuickSortObj.close();
                
            }
        }
    }

    public static void cenario2(BufferedReader entrada, int imprimirVetor) throws Exception {
        int quantTamanhos = Integer.parseInt(entrada.readLine()); // quantos tipos de tamanhos estão no arquivo de entrada.txt
        int tam;
        String[] strings,stringsCopia;
        FileInputStream fileString;
        
        QuickSort quicksort = new QuickSort();

        for (int i = 0; i < quantTamanhos; i++) { 
            tam = Integer.parseInt(entrada.readLine());
            //Arquivos q contem tds os resultados das seeds no tamanho determinado por tam
            ////FileWriter arqResultadosRecursivo = new FileWriter("resultados/QuickSort/Recursivo/S_" + tam + ".txt"); 
            ////FileWriter arqResultadosMediana = new FileWriter("resultados/QuickSort/Mediana/S_" + tam + ".txt");
            ////FileWriter arqResultadosInsercao = new FileWriter("resultados/QuickSort/Insercao/S_" + tam + ".txt");

           // FileWriter arqResultadosFuncaoTeste = new FileWriter("resultados/funcaoTeste/S_" + tam + ".txt");
            FileWriter arqResultadosQuickSortRecursivo = new FileWriter("resultados/QuickSort/cenario2/Recursivo/S_" + tam + ".txt");
            FileWriter arqResultadosQuickSortMediana = new FileWriter("resultados/QuickSort/cenario2/Mediana/S_" + tam + ".txt");
            FileWriter arqResultadosQuickSortInsercao = new FileWriter("resultados/QuickSort/cenario2/Insercao/S_" + tam + ".txt");
            
            for (int seed = 1; seed < 6; seed++) {

                fileString = new FileInputStream("data/arrays/"+tam+"/S_"+tam+"_"+seed+".txt");
                strings = GerarArrays.arqParaVetorString(fileString, tam);
                
                stringsCopia = strings.clone(); //necessidade de copiar a string para que a proxima função não ordene uma string já ordenada
                quicksort.R_sort(stringsCopia, tam, seed, arqResultadosQuickSortRecursivo, imprimirVetor);

                stringsCopia = strings.clone();
                quicksort.M_sort(stringsCopia, tam, seed, arqResultadosQuickSortMediana, imprimirVetor);

                stringsCopia = strings.clone();
                quicksort.I_sort(stringsCopia, tam, seed, arqResultadosQuickSortInsercao, imprimirVetor);

                //Exemplo de chamada de função de ordenação
                //Essa função está no final da classe "ordenacaoTeste"
                //ordenacaoTeste.funcaoTeste(strings, tam, seed, arqResultadosFuncaoTeste, imprimirVetor);   
            }

           // arqResultadosFuncaoTeste.close();
            arqResultadosQuickSortRecursivo.close();
            arqResultadosQuickSortMediana.close();
            arqResultadosQuickSortInsercao.close();

            ////arqResultadosRecursivo.close();
            ////arqResultadosMediana.close();
            ////arqResultadosInsercao.close();
            
        }
    }

    public static void cenario3(BufferedReader entrada, int imprimirVetor) throws Exception {
        int quantTamanhos = Integer.parseInt(entrada.readLine()); // quantos tipos de tamanhos estão no arquivo de entrada.txt
        int tam;
        String[] strings,stringsCopia;
        FileInputStream fileString;

        ////InsertionSort iSort = new InsertionSort();
        ////MergeSort mSort = new MergeSort();
        ////HeapSort hSort = new HeapSort();
        ////TreeSort tSort = new TreeSort();

        for (int i = 0; i < quantTamanhos; i++) { 
            tam = Integer.parseInt(entrada.readLine());
            //Arquivos q contem tds os resultados das seeds no tamanho determinado por tam
            ////FileWriter arqResultInsertionSort = new FileWriter("resultados/InsertionSort/S_" + tam + ".txt"); 
            ////ileWriter arqResultMergeSort = new FileWriter("resultados/MergeSort/S_" + tam + ".txt");
            ////FileWriter arqResultHeapSort = new FileWriter("resultados/HeapSort/S_" + tam + ".txt");
            ////FileWriter arqResultTreeSort = new FileWriter("resultados/TreeSort/S_" + tam + ".txt");

            FileWriter arqResultadosFuncaoTeste = new FileWriter("resultados/funcaoTeste/S_" + tam + ".txt");
            
            for (int seed = 1; seed < 6; seed++) {

                fileString = new FileInputStream("data/arrays/"+tam+"/S_"+tam+"_"+seed+".txt");
                strings = GerarArrays.arqParaVetorString(fileString, tam);
                
                stringsCopia = strings.clone(); //necessidade de copiar a string para que a proxima função não ordene uma string já ordenada
                InsertionSort insertionSort = new InsertionSort(stringsCopia.length);
                insertionSort.sortTitles(stringsCopia, seed);
                ////iSort.funcao(stringsCopia, tam, seed, arqResultInsertionSort, imprimirVetor);

                stringsCopia = strings.clone();
                MergeSort mergeSort = new MergeSort(stringsCopia.length);
                mergeSort.sortTitles(stringsCopia, seed);
                ////mSort.funcao(stringsCopia, tam, seed, arqResultMergeSort, imprimirVetor);

                stringsCopia = strings.clone();
                HeapSort heapSort = new HeapSort(stringsCopia.length);
                heapSort.sortTitles(stringsCopia, seed);
                ////hSort.funcao(stringsCopia, tam, seed, arqResultHeapSort, imprimirVetor);

                stringsCopia = strings.clone();
                ////tSort.funcao(stringsCopia, tam, seed, arqResultTreeSort, imprimirVetor);

                //Exemplo de chamada de função de ordenação
                //Essa função está no final da classe "ordenacaoTeste"
                ordenacaoTeste.funcaoTeste(strings, tam, seed, arqResultadosFuncaoTeste, imprimirVetor);
               
            }

            arqResultadosFuncaoTeste.close();

            ////arqResultInsertionSort.close();
            ////arqResultMergeSort.close();
            ////arqResultHeapSort.close();
            ////arqResultTreeSort.close();
            
        }
    }
    

    public static void main(String[] args) throws ClassNotFoundException, Exception {

        // --------------------MENU---------------------
        System.out.println("TRABALHO DE ESTRUTURA DE DADOS 2");
        BufferedReader entrada = new BufferedReader(new FileReader("data/entrada.txt"));
        Scanner sc = new Scanner(System.in);
        int op;

        
        System.out.println("1-Cenario 1");
        System.out.println("2-Cenario 2");
        System.out.println("3-Cenario 3");
        System.out.println("4-Gerar Arquivo de Objetos");
        System.out.println("5-Gerar Arquivos de Arrays");
        System.out.print("Opção: ");
        op = sc.nextInt();

        switch (op) {
            case 1:
                 System.out.println("Deseja imprimir os vetores ordenados no arquivo de resultados? (0-Nao/1-Sim): ");
                 op = sc.nextInt();
                 cenario1(entrada,op);
            break;

            case 2:
                System.out.println("Deseja imprimir os vetores ordenados no arquivo de resultados? (0-Nao/1-Sim): ");
                op = sc.nextInt();
                cenario2(entrada,op);
            break;

            case 3:
                System.out.println("Deseja imprimir os vetores ordenados no arquivo de resultados? (0-Nao/1-Sim): ");
                op = sc.nextInt();
                cenario3(entrada,op);
            break;

            case 4:
                GerarArquivos.criarArqObj();
            break;

            case 5:
                GerarArquivos.criarArqArrays();
            break;

        }

        sc.close();
    }

}
