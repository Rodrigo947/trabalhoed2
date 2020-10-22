package Ordenacao;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class InsertionSort {

    public static void sort(String[] array, int tam, int seed, FileWriter resultado, int imprimirVetor) throws IOException {
        PrintWriter gravarArq = new PrintWriter(resultado);
        System.out.println("Executando InsertionSort...");
        System.out.println("Array: "+tam+" Seed: "+seed);
        
        long key = 0, copys = 0;
        double time = 0;

        long initialTime = System.currentTimeMillis();

        for (int i = 1; i < array.length; i++) {
            int n = i;
            key++;

            while (n > 0 && array[n - 1].compareToIgnoreCase(array[n]) > 0) {
                if (array[n - 1].compareToIgnoreCase(array[n]) > 0) {
                    key++;
                }
                String temp = array[n];
                array[n] = array[n - 1];
                array[n - 1] = temp;
                n--;
                copys++;
            }
        }

        long finalTime = System.currentTimeMillis();

        time = ((finalTime - initialTime) / 1000.0);

        gravarArq.println("------------- InsertionSort -------------");
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
    
}