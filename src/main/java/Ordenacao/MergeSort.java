package Ordenacao;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class MergeSort {

    private static long key;
    private static long copys;

    public static void sort(String[] array, int tam, int seed, FileWriter resultado, int imprimirVetor) throws IOException {
        PrintWriter gravarArq = new PrintWriter(resultado);
        System.out.println("Executando o MergeSort...");
        System.out.println("Array: "+tam+" Seed: "+seed);
        
        double time = 0;

        long initialTime = System.currentTimeMillis();

        int from = 0;
        int to = array.length - 1;

        auxSortTitles(array, from, to);

        long finalTime = System.currentTimeMillis();

        time = ((finalTime - initialTime) / 1000.0);

        gravarArq.println("------------- Merge Sort -------------");
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
        
        // // Solução 1
        // // O número de comparações é sempre igual ao tamanho do array
        // // O número de cópias é menor que o da solução 2, porém está negativo

        // // Tamanho dos arrays auxiliares
        // int iniAuxCount = meio - ini + 1;
        // int fimAuxCount = fim - meio;

        // // Criar arrays auxiliares
        // String[] iniAux = new String[iniAuxCount];
        // String[] fimAux = new String[fimAuxCount];

        // // Zerar quantidade de comparações e de cópias
        // key = 0;
        // copys = 0;

        // // Copiar dados para os arrays auxiliares
        // for (int i = 0; i < iniAuxCount; i++) {
        //     iniAux[i] = array[ini + i];
        // }
        // for (int j = 0; j < fimAuxCount; j++) {
        //     fimAux[j] = array[meio + 1 + j];
        // }

        // Valores inicias do arrays auxiliares
        // int i = 0, j = 0;

        // int k = ini;
        // while (i < iniAuxCount && j < fimAuxCount) {
        //     if (iniAux[i].compareTo(fimAux[j]) <= 0) {
        //         array[k] = iniAux[i];
        //         i++;

        //         key++;
        //     } else {
        //         array[k] = fimAux[j];
        //         j++;

        //         key++;
        //         copys += (j - k);
        //     }
        //     k++;
        // }

        // while (i < iniAuxCount) {
        //     array[k] = iniAux[i];
        //     i++;
        //     k++;

        //     key++;
        // }

        // while (j < fimAuxCount) {
        //     array[k] = fimAux[j];
        //     j++;
        //     k++;

        //     key++;
        // }

        

        // Solução 2
        // O número de comparações é sempre igual ao tamanho do array

        int arrayAuxCount = fim - ini + 1;
        String[] arrayAux = new String[arrayAuxCount];

        key = 0;
        copys = 0;

        int i = ini, j = meio + 1, k = 0;
        while (i <= meio && j <= fim) {

            if (array[i].compareTo(array[j]) < 0) {
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