package Ordenacao;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class HeapSort {

    private static long key;
    private static long copys;
    
    public static void sort(String[] array, int tam, int seed, FileWriter resultado, int imprimirVetor) throws IOException {
        PrintWriter gravarArq = new PrintWriter(resultado);
        System.out.println("Executando o HeapSort...");
        System.out.println("Array: " + tam + " Seed: " + seed);
        
        double time = 0;

        long initialTime = System.currentTimeMillis();

        buildHeap(array);
        int sizeOfHeap = array.length - 1;
        for (int i = sizeOfHeap; i > 0; i--) {
            swap(array, 0, i);
            sizeOfHeap--;
            heapify(array, 0, sizeOfHeap);
        }

        long finalTime = System.currentTimeMillis();

        time = ((finalTime - initialTime) / 1000.0);

        gravarArq.println("------------- Heap Sort -------------");
        gravarArq.println("Array de tamanho " + tam);
        gravarArq.println("Seed: " + seed);
        gravarArq.println("Comparação de chaves: " + key);
        gravarArq.println("Cópias de registro: " + copys);
        gravarArq.println("Tempo de execução: " + time + "s");
        if (imprimirVetor == 1) {
            gravarArq.println("\n>>>>> Vetor Ordenado: <<<<<\n");
            for (int i = 0; i < tam; i++) {
                gravarArq.println(array[i]);
            }
            gravarArq.println();
        }
        gravarArq.println("\n");

        System.out.println("Finalizado \n");
    }

    private static void buildHeap(String[] arr) {
        // Como o último nó não folha estará em (arr.length-1) / 2 então vamos começar a
        // partir deste local para heapificar o elemento
        for (int i = (arr.length - 1) / 2; i >= 0; i--) {
            heapify(arr, i, arr.length - 1);
        }
    }

    private static void heapify(String[] arr, int i, int size) {
        int left = 2 * i + 1;
        int right = 2 * i + 2;
        int max;

        if (left <= size && arr[left].compareTo(arr[i]) > 0) {
            max = left;
            key++;
        } else {
            max = i;
            key++;
        }

        if (right <= size && arr[right].compareTo(arr[max]) > 0) {
            max = right;
            key++;
        }

        // Se max não for o nó atual, troque-o por max do filho esquerdo e direito
        if (max != i) {
            swap(arr, i, max);
            heapify(arr, max, size);
            key++;
        }
    }

    private static void swap(String[] arr, int i, int j) {
        String t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
        copys++;
    }

}