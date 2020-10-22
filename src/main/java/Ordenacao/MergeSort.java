package Ordenacao;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class MergeSort {

    private static long key;
    private static long copys;

    public static void sort(String[] array, int tam, int seed, FileWriter resultado, int imprimirVetor) throws IOException {
        PrintWriter gravarArq = new PrintWriter(resultado);
        System.out.println("Executando MergeSort...");
        System.out.println("Array: "+tam+" Seed: "+seed);
        
        double time = 0;
        key = 0;
        copys = 0;

        long initialTime = System.currentTimeMillis();

        int from = 0;
        int to = array.length - 1;

        auxSortTitles(array, from, to);

        long finalTime = System.currentTimeMillis();

        time = ((finalTime - initialTime) / 1000.0);

        gravarArq.println("------------- MergeSort -------------");
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

    private static void auxSortTitles(String[] array, int ini, int fim) {
        if (ini < fim) {
            int meio = (ini + fim) / 2;
            auxSortTitles(array, ini, meio);
            auxSortTitles(array, meio + 1, fim);
            mergeString(array, ini, meio, fim);
        }
    }

    private static void mergeString(String[] array, int ini, int meio, int fim) {
        int arrayAuxCount = fim - ini + 1;
        String[] arrayAux = new String[arrayAuxCount];

        int i = ini, j = meio + 1, k = 0;
        while (i <= meio && j <= fim) {

            if (array[i].compareToIgnoreCase(array[j]) < 0) {
                arrayAux[k] = array[i];
                i++;
            } else {
                arrayAux[k] = array[j];
                j++;

                copys += (j - k);
            }
            k++;
            key++;
        }

        while (i <= meio) {
            arrayAux[k] = array[i];
            i++;
            k++;
        }

        while (j <= fim) {
            arrayAux[k] = array[j];
            j++;
            k++;
        }

        for (k = 0; k < fim - ini + 1; k++) {
            array[ini + k] = arrayAux[k];
        }

    }

}