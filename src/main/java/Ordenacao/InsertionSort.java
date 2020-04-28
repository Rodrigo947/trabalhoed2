package Ordenacao;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class InsertionSort {

    private long qtdComparacoes = 0;
    private long qtdCopias = 0;

    private FileWriter arq;
    private PrintWriter writer;

    public InsertionSort(int tam) throws IOException {
        arq = new FileWriter("resultados/Insertion Sort/S_" + tam + ".txt");
        writer = new PrintWriter(arq);

        writer.println("Insertion Sort:");
        writer.println("Array de tamanho: " + tam + "\n");
    }

    public void sortTitles(String[] array, int seed) {
        writer.println("Seed: " + seed);

        qtdComparacoes = 0;
        qtdCopias = 0;

        long initialTime = System.currentTimeMillis();

        for (int i = 1; i < array.length; i++) {
            int n = i;
            qtdComparacoes++;

            while (n > 0 && array[n - 1].compareTo(array[n]) > 0) {
                if (array[n - 1].compareTo(array[n]) > 0) {
                    qtdComparacoes++;
                }
                String temp = array[n];
                array[n] = array[n - 1];
                array[n - 1] = temp;
                n--;
                qtdCopias++;
            }
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