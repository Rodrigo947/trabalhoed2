package Ordenacao;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class ordenacaoTeste {
    public static void funcaoTeste(String[] array, int tam, int seed, FileWriter resultado, int imprimirVetor) throws IOException {
        PrintWriter gravarArq = new PrintWriter(resultado);
        System.out.println("Executando a funcaoTeste...");
        System.out.println("Array: "+tam+" Seed: "+seed);
        
        long key=0,copys=0,time=0;

        gravarArq.println("-------------FuncaoTeste-------------");
        gravarArq.println("Array de tamanho " + tam);
        gravarArq.println("Seed: " + seed);
        gravarArq.println("Comparação de chaves: " + key);
        gravarArq.println("Cópias de registro: " + copys);
        gravarArq.println("Tempo de execução: " + time);
        if(imprimirVetor==1){
            gravarArq.println("\n>>>>>Vetor Ordenado:<<<<<\n");
            for (int i = 0; i < tam; i++) {
                gravarArq.println(array[i]);
            }
            gravarArq.println();
        }
        gravarArq.println("\n");

        System.out.println("Finalizado \n");
    }
}