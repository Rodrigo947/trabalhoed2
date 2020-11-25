
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;

import Arvores.ArvB;
import Arvores.ArvVermelhoPreto;
import Livro.Autor;
import Livro.Livro;
import Livro.LivroAux;
import Ordenacao.*;
import TabelasHash.TabelaAutores;
import TabelasHash.TabelaLivros;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void cenario1(BufferedReader entrada, int imprimirVetor) throws Exception {
        int quantTamanhos = Integer.parseInt(entrada.readLine()); // quantos tipos de tamanhos estão no arquivo de
                                                                  // entrada.txt
        int tam;
        String[] strings;
        Livro[] livros;
        FileInputStream fileLivro, fileString;
        QuickSort quicksort = new QuickSort();

        for (int i = 0; i < quantTamanhos; i++) {
            tam = Integer.parseInt(entrada.readLine());
            if (tam <= 100000) { // No cenario 1 são vetores de até 100.000 posições
                // Arquivos q contem tds os resultados das seeds no tamanho determinado por tam
                FileWriter arqResultadosQuickSortString = new FileWriter(
                        "resultados/QuickSort/cenario1/Recursivo/S_" + tam + ".txt");
                FileWriter arqResultadosQuickSortObj = new FileWriter(
                        "resultados/QuickSort/cenario1/Recursivo/L_" + tam + ".txt");

                for (int seed = 1; seed < 6; seed++) {
                    fileString = new FileInputStream("data/arrays/" + tam + "/S_" + tam + "_" + seed + ".txt");
                    strings = GerarArrays.arqParaVetorString(fileString, tam); // gerando o array de acordo a partir do
                                                                               // arquivo de acordo com a seed
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
        int quantTamanhos = Integer.parseInt(entrada.readLine()); // quantos tipos de tamanhos estão no arquivo de
                                                                  // entrada.txt
        int tam;
        String[] strings, stringsCopia;
        FileInputStream fileString;

        QuickSort quicksort = new QuickSort();

        for (int i = 0; i < quantTamanhos; i++) {
            tam = Integer.parseInt(entrada.readLine());
            // Arquivos q contem tds os resultados das seeds no tamanho determinado por tam
            FileWriter arqResultadosQuickSortRecursivo = new FileWriter(
                    "resultados/QuickSort/cenario2/Recursivo/S_" + tam + ".txt");
            FileWriter arqResultadosQuickSortMediana = new FileWriter(
                    "resultados/QuickSort/cenario2/Mediana/S_" + tam + ".txt");
            FileWriter arqResultadosQuickSortInsercao = new FileWriter(
                    "resultados/QuickSort/cenario2/Insercao/S_" + tam + ".txt");

            for (int seed = 1; seed < 6; seed++) {

                fileString = new FileInputStream("data/arrays/" + tam + "/S_" + tam + "_" + seed + ".txt");
                strings = GerarArrays.arqParaVetorString(fileString, tam);
                fileString.close();

                stringsCopia = strings.clone(); // necessidade de copiar a string para que a proxima função não ordene
                                                // uma string já ordenada
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
        int quantTamanhos = Integer.parseInt(entrada.readLine()); // quantos tipos de tamanhos estão no arquivo de
                                                                  // entrada.txt
        int tam;
        String[] strings, stringsCopia;
        FileInputStream fileString;
        TreeSort treeSort = new TreeSort();
        for (int i = 0; i < quantTamanhos; i++) {
            tam = Integer.parseInt(entrada.readLine());
            // Arquivos q contem tds os resultados das seeds no tamanho determinado por tam
            FileWriter arqResultInsertionSort = new FileWriter("resultados/InsertionSort/S_" + tam + ".txt");
            FileWriter arqResultMergeSort = new FileWriter("resultados/MergeSort/S_" + tam + ".txt");
            FileWriter arqResultHeapSort = new FileWriter("resultados/HeapSort/S_" + tam + ".txt");
            FileWriter arqResultTreeSort = new FileWriter("resultados/TreeSort/S_" + tam + ".txt");

            for (int seed = 1; seed < 6; seed++) {

                fileString = new FileInputStream("data/arrays/" + tam + "/S_" + tam + "_" + seed + ".txt");
                strings = GerarArrays.arqParaVetorString(fileString, tam);
                fileString.close();

                stringsCopia = strings.clone(); // necessidade de copiar a string para que a proxima função não ordene
                                                // uma string já ordenada
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

    public static void parte2(int topAutores) throws Exception {
        BufferedReader fileQuantDados = new BufferedReader(new FileReader("data/quantDados.txt"));
        List<LivroAux> livrosAuxiliares = GerarArrays.arrayLivrosAuxiliares(Integer.parseInt(fileQuantDados.readLine()));
        List<Autor> autores = GerarArrays.arrayAutores();
        fileQuantDados.close();
        
        //Criando Tabelas Hash
        TabelaLivros tabelaLivros = new TabelaLivros(livrosAuxiliares.size());
        for (LivroAux livroAux : livrosAuxiliares) 
            tabelaLivros.add(livroAux);
        
        TabelaAutores tabelaAutores= new TabelaAutores(autores.size());
        for (Autor autor : autores) 
            tabelaAutores.add(autor);

        //Pecorrendo a tabela de livros e incrementando o contador em cada autor
        for (LivroAux livro : tabelaLivros.getLivros()) {
            for (long autor : livro.getAutores()) {
                if (tabelaAutores.find(autor) != null) {
                    tabelaAutores.find(autor).incrementQuantLivros();
                }
            }
        }

        //Ordenando os autores em ordem decrescente em relação a quantidade de livros
        QuickSort quicksort = new QuickSort();
        Object[] autoresOrdenado = tabelaAutores.getAutores().toArray();
        quicksort.R_Quicksort(0, autoresOrdenado.length - 1, autoresOrdenado);

        //Imprimindo ranking de todos os autores em um arquivo
        FileWriter arqResultado = new FileWriter("resultados/Parte2/ranking_de_autores.txt");
        PrintWriter gravarArq = new PrintWriter(arqResultado);
        gravarArq.println("------------- Ranking de Autores -------------\n");
        System.out.println("------------- Ranking de Autores -------------\n");
        int cont = 1;
        for (Object autor : autoresOrdenado) {
            Autor aux = (Autor) autor;
            gravarArq.println(cont+"."+aux.getNome()+" - " + aux.getQuantLivros());
            if(cont <= topAutores)
                System.out.println(cont+"."+aux.getNome()+" - " + aux.getQuantLivros());
            cont++;
        }

        gravarArq.close();
    }

    public static void parte3() throws Exception{
        BufferedReader entrada = new BufferedReader(new FileReader("data/entrada.txt"));
        int quantTamanhos = Integer.parseInt(entrada.readLine()); // quantos tipos de tamanhos estão no arquivo de entrada.txt
        FileInputStream arqIds;
        Long ids[];
        long startTime,endTime;
        double timeBusca,timeInsercao;
        int tam;
        
        for (int i = 0; i < quantTamanhos; i++) {
            tam = Integer.parseInt(entrada.readLine());
            
            //Criando pastas de resultados
            File pasta = new File("resultados/Parte3/" + tam);
            pasta.mkdir();
            
            //Arquivos q contem tds os resultados das seeds no tamanho determinado por tam
            FileWriter arqResultadosInsercao = new FileWriter("resultados/Parte3/"+tam+"/saidaInsercao.txt");
            FileWriter arqResultadosBusca = new FileWriter("resultados/Parte3/"+tam+"/saidaBusca.txt");
            PrintWriter gravarArqInsercao = new PrintWriter(arqResultadosInsercao);
            PrintWriter gravarArqBusca = new PrintWriter(arqResultadosBusca);

            for (int seed = 1; seed < 6; seed++) {

                gravarArqInsercao.println("------SEED: "+seed+"------");
                gravarArqBusca.println("------SEED: "+seed+"------");

                arqIds = new FileInputStream("data/arrays/" + tam + "/I_" + tam + "_" + seed + ".txt");
                ids = GerarArrays.arqParaVetorInteger(arqIds, tam); // gerando o array a partir do arquivo de acordo com o tamanho e seed
                
                ArvB arvB2 = new ArvB(2);
                startTime = System.currentTimeMillis();
                
                for (Long id : ids){ 
                    arvB2.insere(id);
                }
                endTime = System.currentTimeMillis();
                timeInsercao = (endTime - startTime) / 1000.0;
                
                startTime = System.currentTimeMillis();
                for (Long id : ids) 
                    arvB2.busca(id);
                endTime = System.currentTimeMillis();
                timeBusca = (endTime - startTime) / 1000.0;
                
                gravarArqInsercao.println("Arvore B 2");
                gravarArqInsercao.println("Copias: "+arvB2.copiasInsere);
                gravarArqInsercao.println("Comparacoes: "+arvB2.comparacoesInsere);
                gravarArqInsercao.println("Tempo: "+timeInsercao+"\n");

                gravarArqBusca.println("Arvore B 2");
                gravarArqBusca.println("Copias: "+arvB2.copiasBusca);
                gravarArqBusca.println("Comparacoes: "+arvB2.comparacoesBusca);
                gravarArqBusca.println("Tempo: "+timeBusca+"\n");

                ArvB arvB20 = new ArvB(20);
                startTime = System.currentTimeMillis();
                
                for (Long id : ids){ 
                    arvB20.insere(id);
                }
                endTime = System.currentTimeMillis();
                timeInsercao = (endTime - startTime) / 1000.0;
                
                startTime = System.currentTimeMillis();
                for (Long id : ids) 
                    arvB20.busca(id);
                endTime = System.currentTimeMillis();
                timeBusca = (endTime - startTime) / 1000.0;
                
                gravarArqInsercao.println("Arvore B 20");
                gravarArqInsercao.println("Copias: "+arvB20.copiasInsere);
                gravarArqInsercao.println("Comparacoes: "+arvB20.comparacoesInsere);
                gravarArqInsercao.println("Tempo: "+timeInsercao+"\n");

                gravarArqBusca.println("Arvore B 20");
                gravarArqBusca.println("Copias: "+arvB20.copiasBusca);
                gravarArqBusca.println("Comparacoes: "+arvB20.comparacoesBusca);
                gravarArqBusca.println("Tempo: "+timeBusca+"\n");

                ArvVermelhoPreto arvVP = new ArvVermelhoPreto();
                startTime = System.currentTimeMillis();
                
                for (Long id : ids){ 
                    arvVP.insere(id);
                }
                endTime = System.currentTimeMillis();
                timeInsercao = (endTime - startTime) / 1000.0;
                
                startTime = System.currentTimeMillis();
                for (Long id : ids) 
                    arvVP.busca(id);
                endTime = System.currentTimeMillis();
                timeBusca = (endTime - startTime) / 1000.0;
                
                gravarArqInsercao.println("Arvore Vermelho Preto");
                gravarArqInsercao.println("Copias: "+arvVP.copiasInsere);
                gravarArqInsercao.println("Comparacoes: "+arvVP.comparacoesInsere);
                gravarArqInsercao.println("Tempo: "+timeInsercao+"\n");

                gravarArqBusca.println("Arvore Vermelho Preto");
                gravarArqBusca.println("Copias: "+arvVP.copiasBusca);
                gravarArqBusca.println("Comparacoes: "+arvVP.comparacoesBusca);
                gravarArqBusca.println("Tempo: "+timeBusca+"\n");
                
                arqIds.close();
            }

            arqResultadosInsercao.close();
            arqResultadosBusca.close();
        }
        entrada.close();
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
            System.out.println("5-Parte 3");
            System.out.println("6-Gerar Arquivo de Objetos");
            System.out.println("7-Gerar Arquivos de Arrays");
            System.out.println("0-Sair");
            System.out.print("Opcao: ");
            op = sc.nextInt();

            switch (op) {
                case 1:
                    System.out
                            .print("Deseja imprimir os vetores ordenados no arquivo de resultados? (0-Nao/1-Sim): ");
                    opImprime = sc.nextInt();
                    entrada = new BufferedReader(new FileReader("data/entrada.txt"));
                    cenario1(entrada, opImprime);
                    entrada.close();
                    break;

                case 2:
                    System.out
                            .print("Deseja imprimir os vetores ordenados no arquivo de resultados? (0-Nao/1-Sim): ");
                    opImprime = sc.nextInt();
                    entrada = new BufferedReader(new FileReader("data/entrada.txt"));
                    cenario2(entrada, opImprime);
                    entrada.close();
                    break;

                case 3:
                    System.out
                            .print("Deseja imprimir os vetores ordenados no arquivo de resultados? (0-Nao/1-Sim): ");
                    opImprime = sc.nextInt();
                    entrada = new BufferedReader(new FileReader("data/entrada.txt"));
                    cenario3(entrada, opImprime);
                    entrada.close();
                    break;

                case 4:
                    System.out.print("Quantidade de autores exibidos no ranking: ");
                    opImprime = sc.nextInt();
                    parte2(opImprime);
                    break;

                case 5:
                    parte3();
                    break;

                case 6:
                    GerarArquivos.criarArqObj();
                    break;

                case 7:
                    GerarArquivos.criarArqArrays();
                    break;

            }
        }
        sc.close();
    }

}
