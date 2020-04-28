package Ordenacao;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class HeapSort {

    private FileWriter arq;
    private PrintWriter writer;

    private long qtdComparacoes = 0;
    private long qtdCopias = 0;

    public HeapSort(int tam) throws IOException {
        arq = new FileWriter("resultados/Heap Sort/S_" + tam + ".txt");
        writer = new PrintWriter(arq);

        writer.println("Heap Sort:");
        writer.println("Array de tamanho: " + tam + "\n");
    }

    private void buildHeap(String[] arr) {
        // Como o último nó não folha estará em (arr.length-1) / 2 então vamos começar a
        // partir deste local para heapificar o elemento
        for (int i = (arr.length - 1) / 2; i >= 0; i--) {
            heapify(arr, i, arr.length - 1);
        }
    }

    private void heapify(String[] arr, int i, int size) {
        int left = 2 * i + 1;
        int right = 2 * i + 2;
        int max;

        if (left <= size && arr[left].compareTo(arr[i]) > 0) {
            max = left;
            qtdComparacoes++;
        } else {
            max = i;
            qtdComparacoes++;
        }

        if (right <= size && arr[right].compareTo(arr[max]) > 0) {
            max = right;
            qtdComparacoes++;
        }

        // Se max não for o nó atual, troque-o por max do filho esquerdo e direito
        if (max != i) {
            swap(arr, i, max);
            heapify(arr, max, size);
            qtdComparacoes++;
        }
    }

    private void swap(String[] arr, int i, int j) {
        String t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
        qtdCopias++;
    }

    public void sortTitles(String arr[], int seed) throws IOException {
        writer.println("Seed: " + seed);

        qtdComparacoes = 0;
        qtdCopias = 0;

        long initialTime = System.currentTimeMillis();

        buildHeap(arr);
        int sizeOfHeap = arr.length - 1;
        for (int i = sizeOfHeap; i > 0; i--) {
            swap(arr, 0, i);
            sizeOfHeap--;
            heapify(arr, 0, sizeOfHeap);
        }

        long finalTime = System.currentTimeMillis();

        // Escrever os resultados
        writer.println("Quantidade de Comparações: " + qtdComparacoes);
        writer.println("Quantidade de Cópias: " + qtdCopias);

        double resultTime = (finalTime - initialTime) / 1000.0;
        writer.println("Tempo de execução: " + resultTime + "s\n");
    }

    public void closeFile() throws IOException {
        arq.close();
    }

}