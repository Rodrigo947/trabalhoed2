
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Scanner;

import Livro.Livro;
import Livro.LivroAux;
import Ordenacao.*;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void cenario1(BufferedReader entrada, int imprimirVetor) throws Exception {
        int quantTamanhos = Integer.parseInt(entrada.readLine()); // quantos tipos de tamanhos estão no arquivo de entrada.txt
        int tam;
        String[] strings;
        Livro[] livros;
        FileInputStream fileLivro, fileString;
        QuickSort quicksort = new QuickSort();

        for (int i = 0; i < quantTamanhos; i++) {
            tam = Integer.parseInt(entrada.readLine());
            if (tam <= 100000) { //No cenario 1 são vetores de até 100.000 posições
                //Arquivos q contem tds os resultados das seeds no tamanho determinado por tam
                FileWriter arqResultadosQuickSortString = new FileWriter("resultados/QuickSort/cenario1/Recursivo/S_" + tam + ".txt");
                FileWriter arqResultadosQuickSortObj = new FileWriter("resultados/QuickSort/cenario1/Recursivo/L_" + tam + ".txt");

                for (int seed = 1; seed < 6; seed++) {
                    fileString = new FileInputStream("data/arrays/" + tam + "/S_" + tam + "_" + seed + ".txt");
                    strings = GerarArrays.arqParaVetorString(fileString, tam); //gerando o array de acordo a partir do arquivo de acordo com a seed
                    fileString.close();

                    fileLivro = new FileInputStream("data/arrays/" + tam + "/L_" + tam + "_" + seed + ".txt");
                    livros = GerarArrays.arqParaVetorLivros(fileLivro, tam);

                    quicksort.R_sort(strings, tam, seed, arqResultadosQuickSortString, imprimirVetor);
                    quicksort.R_sort(livros, tam, seed, arqResultadosQuickSortObj, imprimirVetor);
                }

                arqResultadosQuickSortString.close();
                arqResultadosQuickSortObj.close();

            }
        }
    }

    public static void cenario2(BufferedReader entrada, int imprimirVetor) throws Exception {
        int quantTamanhos = Integer.parseInt(entrada.readLine()); // quantos tipos de tamanhos estão no arquivo de entrada.txt
        int tam;
        String[] strings, stringsCopia;
        FileInputStream fileString;

        QuickSort quicksort = new QuickSort();

        for (int i = 0; i < quantTamanhos; i++) {
            tam = Integer.parseInt(entrada.readLine());
            //Arquivos q contem tds os resultados das seeds no tamanho determinado por tam
            FileWriter arqResultadosQuickSortRecursivo = new FileWriter("resultados/QuickSort/cenario2/Recursivo/S_" + tam + ".txt");
            FileWriter arqResultadosQuickSortMediana = new FileWriter("resultados/QuickSort/cenario2/Mediana/S_" + tam + ".txt");
            FileWriter arqResultadosQuickSortInsercao = new FileWriter("resultados/QuickSort/cenario2/Insercao/S_" + tam + ".txt");

            for (int seed = 1; seed < 6; seed++) {

                fileString = new FileInputStream("data/arrays/" + tam + "/S_" + tam + "_" + seed + ".txt");
                strings = GerarArrays.arqParaVetorString(fileString, tam);
                fileString.close();

                stringsCopia = strings.clone(); //necessidade de copiar a string para que a proxima função não ordene uma string já ordenada
                quicksort.R_sort(stringsCopia, tam, seed, arqResultadosQuickSortRecursivo, imprimirVetor);

                stringsCopia = strings.clone();
                quicksort.M_sort(stringsCopia, tam, seed, arqResultadosQuickSortMediana, imprimirVetor);

                stringsCopia = strings.clone();
                quicksort.I_sort(stringsCopia, tam, seed, arqResultadosQuickSortInsercao, imprimirVetor);

            }

            arqResultadosQuickSortRecursivo.close();
            arqResultadosQuickSortMediana.close();
            arqResultadosQuickSortInsercao.close();

        }
    }

    public static void cenario3(BufferedReader entrada, int imprimirVetor) throws Exception {
        int quantTamanhos = Integer.parseInt(entrada.readLine()); // quantos tipos de tamanhos estão no arquivo de entrada.txt
        int tam;
        String[] strings, stringsCopia;
        FileInputStream fileString;
        TreeSort treeSort = new TreeSort();
        for (int i = 0; i < quantTamanhos; i++) {
            tam = Integer.parseInt(entrada.readLine());
            //Arquivos q contem tds os resultados das seeds no tamanho determinado por tam
            FileWriter arqResultInsertionSort = new FileWriter("resultados/InsertionSort/S_" + tam + ".txt");
            FileWriter arqResultMergeSort = new FileWriter("resultados/MergeSort/S_" + tam + ".txt");
            FileWriter arqResultHeapSort = new FileWriter("resultados/HeapSort/S_" + tam + ".txt");
            FileWriter arqResultTreeSort = new FileWriter("resultados/TreeSort/S_" + tam + ".txt");

            for (int seed = 1; seed < 6; seed++) {

                fileString = new FileInputStream("data/arrays/" + tam + "/S_" + tam + "_" + seed + ".txt");
                strings = GerarArrays.arqParaVetorString(fileString, tam);
                fileString.close();

                stringsCopia = strings.clone(); //necessidade de copiar a string para que a proxima função não ordene uma string já ordenada
                InsertionSort.sort(stringsCopia, tam, seed, arqResultInsertionSort, imprimirVetor);

                stringsCopia = strings.clone();
                MergeSort.sort(stringsCopia, tam, seed, arqResultMergeSort, imprimirVetor);

                stringsCopia = strings.clone();
                HeapSort.sort(stringsCopia, tam, seed, arqResultHeapSort, imprimirVetor);

                stringsCopia = strings.clone();
                treeSort = new TreeSort();
                treeSort.sort(stringsCopia, tam, seed, arqResultTreeSort, imprimirVetor);
            }

            arqResultInsertionSort.close();
            arqResultMergeSort.close();
            arqResultHeapSort.close();
            arqResultTreeSort.close();
        }
    }

    public static void parte2() throws Exception {
        BufferedReader fileQuantDados = new BufferedReader(new FileReader("data/quantDados.txt"));
        List<LivroAux> livrosAuxiliares = GerarArrays.arrayLivrosAuxiliares(Integer.parseInt(fileQuantDados.readLine()));
    }

    public static void main(String[] args) throws ClassNotFoundException, Exception {

        // --------------------MENU---------------------
        BufferedReader entrada;
        Scanner sc = new Scanner(System.in);
        int op = 1, opImprime = 0;
        while (op != 0) {
            System.out.println("TRABALHO DE ESTRUTURA DE DADOS 2");
            System.out.println("1-Cenario 1");
            System.out.println("2-Cenario 2");
            System.out.println("3-Cenario 3");
            System.out.println("4-Parte 2");
            System.out.println("5-Gerar Arquivo de Objetos");
            System.out.println("6-Gerar Arquivos de Arrays");
            System.out.println("0-Sair");
            System.out.print("Opcao: ");
            op = sc.nextInt();

            switch (op) {
                case 1:
                    System.out.println("Deseja imprimir os vetores ordenados no arquivo de resultados? (0-Nao/1-Sim): ");
                    opImprime = sc.nextInt();
                    entrada = new BufferedReader(new FileReader("data/entrada.txt"));
                    cenario1(entrada, opImprime);
                    entrada.close();
                    break;

                case 2:
                    System.out.println("Deseja imprimir os vetores ordenados no arquivo de resultados? (0-Nao/1-Sim): ");
                    opImprime = sc.nextInt();
                    entrada = new BufferedReader(new FileReader("data/entrada.txt"));
                    cenario2(entrada, opImprime);
                    entrada.close();
                    break;

                case 3:
                    System.out.println("Deseja imprimir os vetores ordenados no arquivo de resultados? (0-Nao/1-Sim): ");
                    opImprime = sc.nextInt();
                    entrada = new BufferedReader(new FileReader("data/entrada.txt"));
                    cenario3(entrada, opImprime);
                    entrada.close();
                    break;

                case 4:
                    parte2();
                    break;
                    
                case 5:
                    GerarArquivos.criarArqObj();
                    break;

                case 6:
                    GerarArquivos.criarArqArrays();
                    break;

            }
        }
        sc.close();
    }

}
