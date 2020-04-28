package Ordenacao;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class MergeSort {

    private FileWriter arq;
    private PrintWriter writer;

    private long qtdComparacoes = 0;
    private long qtdCopias = 0;

    public MergeSort(int tam) throws IOException {
        arq = new FileWriter("resultados/Merge Sort/S_" + tam + ".txt");
        writer = new PrintWriter(arq);

        writer.println("Merge Sort:");
        writer.println("Array de tamanho: " + tam + "\n");
    }

    public void sortTitles(String[] array, int seed) {
        writer.println("Seed: " + seed);

        qtdComparacoes = 0;
        qtdCopias = 0;

        long initialTime = System.currentTimeMillis();

        int from = 0;
        int to = array.length - 1;

        auxSortTitles(array, from, to);

        long finalTime = System.currentTimeMillis();

        // Escrever os resultados
        writer.println("Quantidade de Comparações: " + qtdComparacoes);
        writer.println("Quantidade de Cópias: " + qtdCopias);

        double resultTime = (finalTime - initialTime) / 1000.0;
        writer.println("Tempo de execução: " + resultTime + "s\n");
    }

    private void auxSortTitles(String[] array, int ini, int fim) {
        if (ini < fim) {
            int meio = (ini + fim) / 2;
            auxSortTitles(array, ini, meio);
            auxSortTitles(array, meio + 1, fim);
            mergeString(array, ini, meio, fim);
        }
    }

    private void mergeString(String[] array, int ini, int meio, int fim) {
        int arrayAuxCount = fim - ini + 1;
        String[] arrayAux = new String[arrayAuxCount];

        int i = ini, j = meio + 1, k = 0;
        while (i <= meio && j <= fim) {

            if (array[i].compareTo(array[j]) < 0) {
                arrayAux[k] = array[i];
                i++;
                qtdComparacoes++;
            } else {
                arrayAux[k] = array[j];
                j++;
                qtdComparacoes++;
                qtdCopias += (j - k);
            }

            k++;
        }

        while (i <= meio) {
            arrayAux[k] = array[i];
            i++;
            k++;
            qtdComparacoes++;
        }

        while (j <= fim) {
            arrayAux[k] = array[j];
            j++;
            k++;
            qtdComparacoes++;
        }

        for (k = 0; k < fim - ini + 1; k++) {
            array[ini + k] = arrayAux[k];
        }

    }

    public void closeFile() throws IOException {
        arq.close();
    }

}