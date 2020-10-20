package Ordenacao;

import Livro.Livro;
import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class QuickSort {

    //variaveis para QuickSort Recursivo
    private int R_key = 0; //comparações de chaves recursivo
    private int R_copy = 0;//cópia de registro recursivo
    //variaveis para QuickSort Mediana
    private int M_key = 0; //comparações de chaves mediana
    private int M_copy = 0; //cópia de registro mediana
    //variaveis para QuickSort Insertion
    private int I_key = 0;
    private int I_copy = 0;
    
    //variaveis QuickSort Inserção
    int QuickInsertion_key = 0; //comparações de chaves QuickSort Inserção
    int QuickInsertion_copy = 0; //cópia de registro QuickSort Inserção


    public <T> int comparadorEspecial(T string1, T string2) {
        if (string1 instanceof String) {
            return ((String) string1).compareToIgnoreCase((String) string2);
        } else {
            return ((Livro) string1).getTitle().compareToIgnoreCase(((Livro) string2).getTitle());
        }
    }

    public <T> void InsertionSort(T[] array) {
        for (int i = 1; i < array.length; i++) {
            int n = i;

            while (n > 0 && ((String)array[n - 1]).compareTo((String)array[n]) > 0) {
                QuickInsertion_copy = QuickInsertion_copy + 1;
                QuickInsertion_key = QuickInsertion_key + 1;
                T temp = array[n];
                array[n] = array[n - 1];
                array[n - 1] = temp;
                n--;
            }
        }
    }
    public <T> void swap(int a, int b, T[] array) {
        T x = array[a];
        array[a] = array[b];
        array[b] = x;
    }


    <T> int definePivo(int left, int right,T[] array, int k){
        int indexRandom;
        int posicao = (right + left) / 2;
        int tamVetor = right - left + 1;
        Random r = new Random();
        String[] valEscolhidosOrdenados = new String[k];
        Map <Integer,T> valEscolhidosBackup = new HashMap<Integer,T>();
        
        if(k <= tamVetor){
            
            valEscolhidosBackup.put(left, array[left]); // Primeiro
            valEscolhidosBackup.put(right, array[right]); //Ultimo
            valEscolhidosBackup.put( (tamVetor / 2) + left, array[ (tamVetor / 2) + left ] ); //Meio

            //Escolhe mais k-3 posições randomicas para fazer a mediana
            for (int i = 0; i < k-3; i++) {
                indexRandom = r.nextInt(right - left) + left;
                while(valEscolhidosBackup.containsKey(indexRandom))
                    indexRandom = r.nextInt(right - left) + left;
                valEscolhidosBackup.put(indexRandom, array[indexRandom]);
            }

            //Faz uma copia par um vetor de string a ser ordenado
            int m = 0;
            for (int key : valEscolhidosBackup.keySet()) {
                if(valEscolhidosBackup.get(key) instanceof String)
                    valEscolhidosOrdenados[m] = (String) valEscolhidosBackup.get(key);
                else
                    valEscolhidosOrdenados[m] = ((Livro) valEscolhidosBackup.get(key)).getTitle();
                m++;
            }

            //Ordena o vetor e pega o elemento do meio
            R_Quicksort(0, k - 1, valEscolhidosOrdenados);
            String pivoEscolhido = valEscolhidosOrdenados[k/2];
            
            //Procura qual a posição correspondente do pivo escolhido
            for (int key : valEscolhidosBackup.keySet()) {
                if(valEscolhidosBackup.get(key) instanceof String){
                    if(pivoEscolhido == (String) valEscolhidosBackup.get(key)) {
                        posicao = key;
                        break;
                    }
                }
                else{
                    if( pivoEscolhido == ((Livro) valEscolhidosBackup.get(key)).getTitle() ) {
                        posicao = key;
                        break;
                    }
                }
            }

        }
        return posicao;
    }

    public <T> void M_Quicksort(int left, int right, T[] array, int k) {

        if (left >= right) {
            return;
        }

        T pivo = array[ definePivo(left,right,array,k) ];

        int i = left;
        int j = right;

        while (i <= j) {

            while (comparadorEspecial(array[i], pivo) < 0) {
                M_key = M_key + 1;
                i++;
            }

            while (comparadorEspecial(array[j], pivo) > 0) {
                M_key = M_key + 1;
                j--;
            }

            if (i <= j) {
                swap(i, j, array);
                M_copy = M_copy + 1;
                i++;
                j--;
            }
        }
        M_Quicksort(left, j, array, k);
        M_Quicksort(i, right, array, k);
    }

    public <T> void R_Quicksort(int left, int right, T[] array) {
        if (left >= right) {
            return;
        }

        T pivo = array[left + (right - left) / 2];

        int i = left;
        int j = right;

        while (i <= j) {
            while (comparadorEspecial(array[i], pivo) < 0) {
                R_key = R_key + 1;
                I_key = I_key + 1;
                i++;
            }

            while (comparadorEspecial(array[j], pivo) > 0) {
                R_key = R_key + 1;
                I_key = I_key + 1;
                j--;
            }

            if (i <= j) {
                swap(i, j, array);
                R_copy = R_copy + 1;
                I_copy = I_copy + 1;
                i++;
                j--;
            }
        }
        R_Quicksort(left, j, array);
        R_Quicksort(i, right, array);
    }

    //sort recursivo
    public <T> void R_sort(T[] array, int tam, int seed, FileWriter resultado, int imprimirVetor) throws Exception {
        long startTime = System.currentTimeMillis();
        R_Quicksort(0, tam - 1, array);
        long endTime = System.currentTimeMillis();
        double time = (endTime - startTime) / 1000.0;

        PrintWriter gravarArq = new PrintWriter(resultado);
        System.out.println("Executando QuickSort Recursivo...");
        System.out.println("Array: " + tam + " Seed: " + seed);

        gravarArq.println("-------------QuickSort Recursivo-------------");
        gravarArq.println("Array de tamanho " + tam);
        gravarArq.println("Seed: " + seed);
        gravarArq.println("Comparação de chaves: " + R_key);
        gravarArq.println("Cópias de registro: " + R_copy);
        gravarArq.println("Tempo de execução: " + time);
        if (imprimirVetor == 1) {
            gravarArq.println("\n>>>>>Vetor Ordenado:<<<<<\n");
            for (int i = 0; i < tam; i++) {
                if(array instanceof String[]){
                    gravarArq.println(array[i]);
                }
                else{
                    gravarArq.println( ((Livro) array[i]).getTitle());
                }
            }
            gravarArq.println();
        }
        gravarArq.println("\n");

        System.out.println("Finalizado \n");

        startTime = 0;
        endTime = 0;
        time = 0;
        R_key = 0;
        R_copy = 0;
        I_key = 0;
        I_copy = 0;
    }

    //sort mediana
    public <T> void M_sort(T[] array, int tam, int seed, FileWriter resultado, int imprimirVetor) throws Exception {
        long startTime = System.currentTimeMillis();
        M_Quicksort(0, tam - 1, array,5); // k=3 ou k=5
        long endTime = System.currentTimeMillis();
        double time = (endTime - startTime) / 1000.0;

        PrintWriter gravarArq = new PrintWriter(resultado);
        System.out.println("Executando QuickSort Mediana...");
        System.out.println("Array: " + tam + " Seed: " + seed);

        gravarArq.println("-------------QuickSort Mediana-------------");
        gravarArq.println("Array de tamanho " + tam);
        gravarArq.println("Seed: " + seed);
        gravarArq.println("Comparação de chaves: " + M_key);
        gravarArq.println("Cópias de registro: " + M_copy);
        gravarArq.println("Tempo de execução: " + time);
        if (imprimirVetor == 1) {
            gravarArq.println("\n>>>>>Vetor Ordenado:<<<<<\n");
            for (int i = 0; i < tam; i++) {
                gravarArq.println(array[i]);
            }
            
        }
        gravarArq.println("\n");

        System.out.println("Finalizado \n");

        startTime = 0;
        endTime = 0;
        time = 0;
        M_key = 0;
        M_copy = 0;

    }

    public <T> void QuickSortInsert(T[] array, int left, int right, int tam, int seed, FileWriter resultado, int imprimirVetor) throws Exception {
        if (right - left <= 100) {    //m = 10 , m = 100
            long startTime = System.currentTimeMillis();
            InsertionSort((Comparable[]) array);
            long endTime = System.currentTimeMillis();
            double time = (endTime - startTime) / 1000.0;

            PrintWriter gravarArq = new PrintWriter(resultado);
            System.out.println("Executando QuickSort Inserção...");
            System.out.println("Array: " + tam + " Seed: " + seed);

            gravarArq.println("-------------QuickSort Inserção-------------");
            gravarArq.println("Array de tamanho " + tam);
            gravarArq.println("Seed: " + seed);
            gravarArq.println("Comparação de chaves: " + QuickInsertion_key);
            gravarArq.println("Cópias de registro: " + QuickInsertion_copy);
            gravarArq.println("Tempo de execução: " + time);
            if (imprimirVetor == 1) {
                gravarArq.println("\n>>>>>Vetor Ordenado:<<<<<\n");
                for (int i = 0; i < tam; i++) {
                    gravarArq.println(array[i]);
                }
                gravarArq.println();
            }
            gravarArq.println("\n");

            System.out.println("Finalizado \n");

            startTime = 0;
            endTime = 0;
            time = 0;
            QuickInsertion_key = 0;
            QuickInsertion_copy = 0;
        } else {
            long startTime = System.currentTimeMillis();
            R_Quicksort(0, tam - 1, array);
            long endTime = System.currentTimeMillis();
            double time = (endTime - startTime) / 1000.0;

            PrintWriter gravarArq = new PrintWriter(resultado);
            System.out.println("Executando QuickSort Inserção...");
            System.out.println("Array: " + tam + " Seed: " + seed);

            gravarArq.println("-------------QuickSort Inserção-------------");
            gravarArq.println("Array de tamanho " + tam);
            gravarArq.println("Seed: " + seed);
            gravarArq.println("Comparação de chaves: " + I_key);
            gravarArq.println("Cópias de registro: " + I_copy);
            gravarArq.println("Tempo de execução: " + time);
            if (imprimirVetor == 1) {
                gravarArq.println("\n>>>>>Vetor Ordenado:<<<<<\n");
                for (int i = 0; i < tam; i++) {
                    gravarArq.println(array[i]);
                }
                gravarArq.println();
            }
            gravarArq.println("\n");

            System.out.println("Finalizado \n");

            startTime = 0;
            endTime = 0;
            time = 0;
            I_key = 0;
            I_copy = 0;
            R_key = 0;
            R_copy = 0;
        }
    }

    public <T> void I_sort(T[] array, int tam, int seed, FileWriter resultado, int imprimirVetor) throws Exception {
        QuickSortInsert(array, 0, tam - 1, tam, seed, resultado, imprimirVetor);
    }
}

