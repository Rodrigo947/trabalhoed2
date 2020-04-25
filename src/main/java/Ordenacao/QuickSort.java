package Ordenacao;

import Livro.Livro;
import java.io.*;

class InsertSort<T extends Comparable<T>> implements Insert<T> {

    //variaveis QuickSort Inserção
    int QuickInsertion_key = 0; //comparações de chaves QuickSort Inserção
    int QuickInsertion_record = 0; //cópia de registro QuickSort Inserção

    @Override
    public void InsertionSort(T[] array) {
        for (int i = 1; i < array.length; i++) {
            int n = i;

            while (n > 0 && array[n - 1].compareTo(array[n]) > 0) {
                QuickInsertion_record = QuickInsertion_record + 1;
                QuickInsertion_key = QuickInsertion_key + 1;
                T temp = array[n];
                array[n] = array[n - 1];
                array[n - 1] = temp;
                n--;
            }
        }
    }
}

public class QuickSort {

    //variaveis para QuickSort Recursivo
    private int R_key = 0; //comparações de chaves recursivo
    private int R_record = 0;//cópia de registro recursivo
    //variaveis para QuickSort Mediana
    private int M_key = 0; //comparações de chaves mediana
    private int M_record = 0; //cópia de registro mediana
    private static int seed = 0;

    public <T> QuickSort() throws Exception {
        this.M_key = M_key;
        this.M_record = M_record;
        this.R_key = R_key;
        this.R_record = R_record;
    }

    public <T> int comparadorEspecial(T string1, T string2) {
        if (string1 instanceof String) {
            return ((String) string1).compareToIgnoreCase((String) string2);
        } else {
            return ((Livro) string1).getTitle().compareToIgnoreCase(((Livro) string2).getTitle());
        }
    }

    public <T> void swap(int a, int b, T[] array) {
        T x = array[a];
        array[a] = array[b];
        array[b] = x;
    }

    public <T> void M_Quicksort(int left, int right, T[] array) {

        if (left >= right) {
            M_key = M_key + 1;
            return;
        }

        T pivo = array[(right + left) / 2];

        int i = left;
        int j = right;

        while (i <= j) {
            M_key = M_key + 1;

            while (comparadorEspecial(array[i], pivo) < 0) {
                M_key = M_key + 1;
                i++;
            }

            while (comparadorEspecial(array[j], pivo) > 0) {
                M_key = M_key + 1;
                j--;
            }

            if (i <= j) {
                M_key = M_key + 1;
                swap(i, j, array);
                M_record = M_record + 1;
                i++;
                j--;
            }
        }
        M_Quicksort(left, j, array);
        M_Quicksort(i, right, array);
    }

    public <T> void R_Quicksort(int left, int right, T[] array) {
        if (left >= right) {
            R_key = R_key + 1;
            return;
        }

        T pivo = array[left + (right - left) / 2];

        int i = left;
        int j = right;

        while (i <= j) {
            R_key = R_key + 1;

            while (comparadorEspecial(array[i], pivo) < 0) {
                R_key = R_key + 1;
                i++;
            }

            while (comparadorEspecial(array[j], pivo) > 0) {
                R_key = R_key + 1;
                j--;
            }

            if (i <= j) {
                R_key = R_key + 1;
                swap(i, j, array);
                R_record = R_record + 1;
                i++;
                j--;
            }
        }
        R_Quicksort(left, j, array);
        R_Quicksort(i, right, array);
    }

    public <T> void WriteFileRecursivo(double time, int sizeArray, T[] array, int s) throws Exception {
        System.out.println("seed: " + s);
        if (s == 1) {
            if (array[0] instanceof String) {
                FileWriter arq = new FileWriter("data/resultados/QuickSortRecursivoString" + sizeArray + ".txt");
                BufferedWriter buffer = new BufferedWriter(arq);
                buffer.write("QuickSort Recursivo String" + "\n");
                buffer.write("Seed: " + s + "\n");
                buffer.write("Array de tamanho " + sizeArray + "\n");
                buffer.write("Tempo de execução QuickSort Recursivo:" + time + "\n");
                buffer.write("Comparação de chaves: " + R_key + "\n");
                buffer.write("Cópias de registro: " + R_record + "\n\n");
                for (int i = 0; i < sizeArray; i++) {
                    buffer.write((String) array[i] + "\n");
                }
                buffer.close();
            }
            if (array[0] instanceof Livro) {
                FileWriter arq = new FileWriter("data/resultados/QuickSortRecursivoObj" + sizeArray + ".txt");
                BufferedWriter buffer = new BufferedWriter(arq);
                buffer.write("QuickSort Recursivo Objeto" + "\n");
                buffer.write("Seed: " + s + "\n");
                buffer.write("Array de tamanho " + sizeArray + "\n");
                buffer.write("Tempo de execução QuickSort Recursivo:" + time + "\n");
                buffer.write("Comparação de chaves: " + R_key + "\n");
                buffer.write("Cópias de registro: " + R_record + "\n\n");
                for (int i = 0; i < sizeArray; i++) {
                    buffer.write(((Livro) array[i]).getTitle() + "\n");
                }
                buffer.close();
            }
        }
        if (s > 1) {
            if (array[0] instanceof String) {
                FileWriter arq = new FileWriter("data/resultados/QuickSortRecursivoString" + sizeArray + ".txt", true);
                BufferedWriter buffer = new BufferedWriter(arq);
                buffer.write("\n\n" + "QuickSort Recursivo String" + "\n");
                buffer.write("Seed: " + s + "\n");
                buffer.write("Array de tamanho " + sizeArray + "\n");
                buffer.write("Tempo de execução QuickSort Recursivo:" + time + "\n");
                buffer.write("Comparação de chaves: " + R_key + "\n");
                buffer.write("Cópias de registro: " + R_record + "\n\n");
                for (int i = 0; i < sizeArray; i++) {
                    buffer.write((String) array[i] + "\n");
                }
                buffer.close();
            }
            if (array[0] instanceof Livro) {
                FileWriter arq = new FileWriter("data/resultados/QuickSortRecursivoObj" + sizeArray + ".txt", true);
                BufferedWriter buffer = new BufferedWriter(arq);
                buffer.write("\n\n" + "QuickSort Recursivo Objeto" + "\n");
                buffer.write("Seed: " + s + "\n");
                buffer.write("Array de tamanho " + sizeArray + "\n");
                buffer.write("Tempo de execução QuickSort Recursivo:" + time + "\n");
                buffer.write("Comparação de chaves: " + R_key + "\n");
                buffer.write("Cópias de registro: " + R_record + "\n\n");
                for (int i = 0; i < sizeArray; i++) {
                    buffer.write(((Livro) array[i]).getTitle() + "\n");
                }
                buffer.close();
            }
        }
        System.out.println("SUCESS QuickSort Recursivo");
    }

    public <T> void WriteFileMediana(double time, int sizeArray, T[] array, int s) throws Exception {
        if (s == 1) {
            if (array[0] instanceof String) {
                FileWriter arq = new FileWriter("data/resultados/QuickSortMedianaString" + sizeArray + ".txt");
                BufferedWriter buffer = new BufferedWriter(arq);
                buffer.write("QuickSort Mediana String" + "\n");
                buffer.write("Seed: " + s + "\n");
                buffer.write("Array de tamanho " + sizeArray + "\n");
                buffer.write("Tempo de execução QuickSort Mediana:" + time + "\n");
                buffer.write("Comparação de chaves: " + R_key + "\n");
                buffer.write("Cópias de registro: " + R_record + "\n\n");

                for (int i = 0; i < sizeArray; i++) {
                    buffer.write((String) array[i] + "\n");
                }
                buffer.close();
            }
            if (array[0] instanceof Livro) {
                FileWriter arq = new FileWriter("data/resultados/QuickSortMedianaObj" + sizeArray + ".txt");
                BufferedWriter buffer = new BufferedWriter(arq);
                buffer.write("QuickSort Mediana Objeto" + "\n");
                buffer.write("Seed: " + s + "\n");
                buffer.write("Array de tamanho " + sizeArray + "\n");
                buffer.write("Tempo de execução QuickSort Mediana:" + time + "\n");
                buffer.write("Comparação de chaves: " + R_key + "\n");
                buffer.write("Cópias de registro: " + R_record + "\n\n");
                for (int i = 0; i < sizeArray; i++) {
                    buffer.write(((Livro) array[i]).getTitle() + "\n");
                }
                buffer.close();
            }
        }
        if (s > 1) {
            if (array[0] instanceof String) {
                FileWriter arq = new FileWriter("data/resultados/QuickSortMedianaString" + sizeArray + ".txt", true);
                BufferedWriter buffer = new BufferedWriter(arq);
                buffer.write("QuickSort Mediana String" + "\n");
                buffer.write("Seed: " + s + "\n");
                buffer.write("Array de tamanho " + sizeArray + "\n");
                buffer.write("Tempo de execução QuickSort Mediana:" + time + "\n");
                buffer.write("Comparação de chaves: " + R_key + "\n");
                buffer.write("Cópias de registro: " + R_record + "\n\n");

                for (int i = 0; i < sizeArray; i++) {
                    buffer.write((String) array[i] + "\n");
                }
                buffer.close();
            }
            if (array[0] instanceof Livro) {
                FileWriter arq = new FileWriter("data/resultados/QuickSortMedianaObj" + sizeArray + ".txt", true);
                BufferedWriter buffer = new BufferedWriter(arq);
                buffer.write("QuickSort Mediana Objeto" + "\n");
                buffer.write("Seed: " + s + "\n");
                buffer.write("Array de tamanho " + sizeArray + "\n");
                buffer.write("Tempo de execução QuickSort Mediana:" + time + "\n");
                buffer.write("Comparação de chaves: " + R_key + "\n");
                buffer.write("Cópias de registro: " + R_record + "\n\n");
                for (int i = 0; i < sizeArray; i++) {
                    buffer.write(((Livro) array[i]).getTitle() + "\n");
                }
                buffer.close();
            }
        }

        System.out.println("SUCESS QuickSort Mediana");
    }

    public <T> void WriteFileQuickSortInsertion(double time, int sizeArray, T[] array, boolean state, int s) throws Exception {
        InsertSort is = new InsertSort();
        if (s == 1) {
            if (array[0] instanceof String) {
                FileWriter arq = new FileWriter("data/resultados/QuickSortInsertionString" + sizeArray + ".txt");
                BufferedWriter buffer = new BufferedWriter(arq);
                if (state) {
                    buffer.write("QuickSort Inserção String");
                    buffer.write("Seed: " + s + "\n");
                    buffer.write("Array de tamanho " + sizeArray + "\n");
                    buffer.write("Tempo de execução QuickSort: Inserção:" + time + "\n");
                    buffer.write("Comparação de chaves: " + is.QuickInsertion_key + "\n");
                    buffer.write("Cópias de registro: " + is.QuickInsertion_record + "\n\n");
                } else {
                    buffer.write("QuickSort Recursivo String");
                    buffer.write("Seed: " + s + "\n");
                    buffer.write("Array de tamanho " + sizeArray + "\n");
                    buffer.write("Tempo de execução QuickSort: Recursivo:" + time + "\n");
                    buffer.write("Comparação de chaves: " + R_key + "\n");
                    buffer.write("Cópias de registro: " + R_record + "\n\n");
                }
                for (int i = 0; i < sizeArray; i++) {
                    buffer.write((String) array[i] + "\n");
                }
                buffer.close();
            }
            if (array[0] instanceof Livro) {
                FileWriter arq = new FileWriter("data/resultados/QuickSortInsertionObj" + sizeArray + ".txt");
                BufferedWriter buffer = new BufferedWriter(arq);
                if (state) {
                    buffer.write("QuickSort Inserção Objeto");
                    buffer.write("Seed: " + s + "\n");
                    buffer.write("Array de tamanho " + sizeArray + "\n");
                    buffer.write("Tempo de execução QuickSort: Inserção:" + time + "\n");
                    buffer.write("Comparação de chaves: " + is.QuickInsertion_key + "\n");
                    buffer.write("Cópias de registro: " + is.QuickInsertion_record + "\n\n");
                } else {
                    buffer.write("QuickSort Recursivo Objeto");
                    buffer.write("Seed: " + s + "\n");
                    buffer.write("Array de tamanho " + sizeArray + "\n");
                    buffer.write("Tempo de execução QuickSort: Recursivo:" + time + "\n");
                    buffer.write("Comparação de chaves: " + R_key + "\n");
                    buffer.write("Cópias de registro: " + R_record + "\n\n");
                }
                for (int i = 0; i < sizeArray; i++) {
                    buffer.write(((Livro) array[i]).getTitle() + "\n");
                }
                buffer.close();
            }
        }
        if (s > 1) {
            if (array[0] instanceof String) {
                FileWriter arq = new FileWriter("data/resultados/QuickSortInsertionString" + sizeArray + ".txt",true);
                BufferedWriter buffer = new BufferedWriter(arq);
                if (state) {
                    buffer.write("QuickSort Inserção String");
                    buffer.write("Seed: " + s + "\n");
                    buffer.write("Array de tamanho " + sizeArray + "\n");
                    buffer.write("Tempo de execução QuickSort: Inserção:" + time + "\n");
                    buffer.write("Comparação de chaves: " + is.QuickInsertion_key + "\n");
                    buffer.write("Cópias de registro: " + is.QuickInsertion_record + "\n\n");
                } else {
                    buffer.write("QuickSort Recursivo String");
                    buffer.write("Seed: " + s + "\n");
                    buffer.write("Array de tamanho " + sizeArray + "\n");
                    buffer.write("Tempo de execução QuickSort: Recursivo:" + time + "\n");
                    buffer.write("Comparação de chaves: " + R_key + "\n");
                    buffer.write("Cópias de registro: " + R_record + "\n\n");
                }
                for (int i = 0; i < sizeArray; i++) {
                    buffer.write((String) array[i] + "\n");
                }
                buffer.close();
            }
            if (array[0] instanceof Livro) {
                FileWriter arq = new FileWriter("data/resultados/QuickSortInsertionObj" + sizeArray + ".txt",true);
                BufferedWriter buffer = new BufferedWriter(arq);
                if (state) {
                    buffer.write("QuickSort Inserção Objeto");
                    buffer.write("Seed: " + s + "\n");
                    buffer.write("Array de tamanho " + sizeArray + "\n");
                    buffer.write("Tempo de execução QuickSort: Inserção:" + time + "\n");
                    buffer.write("Comparação de chaves: " + is.QuickInsertion_key + "\n");
                    buffer.write("Cópias de registro: " + is.QuickInsertion_record + "\n\n");
                } else {
                    buffer.write("QuickSort Recursivo Objeto");
                    buffer.write("Seed: " + s + "\n");
                    buffer.write("Array de tamanho " + sizeArray + "\n");
                    buffer.write("Tempo de execução QuickSort: Recursivo:" + time + "\n");
                    buffer.write("Comparação de chaves: " + R_key + "\n");
                    buffer.write("Cópias de registro: " + R_record + "\n\n");
                }
                for (int i = 0; i < sizeArray; i++) {
                    buffer.write(((Livro) array[i]).getTitle() + "\n");
                }
                buffer.close();
            }
        }
        System.out.println("SUCESS QuickSort Inserção");
    }

    //sort recursivo
    public <T> void R_sort(T[] array, int sizeArray) throws Exception {
        long startTime = System.currentTimeMillis();
        R_Quicksort(0, sizeArray - 1, array);
        long endTime = System.currentTimeMillis();
        double time = (endTime - startTime) / 1000.0;
        seed = seed + 1;
        WriteFileRecursivo(time, array.length, array, seed);
        if (seed == 5) //quando acabar todas as seeds, zera e começa de novo
        {
            seed = 0;
        }
        startTime = 0;
        endTime = 0;
        time = 0;
        R_key = 0;
        R_record = 0;
    }

    //sort mediana
    public <T> void M_sort(T[] array, int sizeArray) throws Exception {
        long startTime = System.currentTimeMillis();
        M_Quicksort(0, sizeArray - 1, array);
        long endTime = System.currentTimeMillis();
        double time = (endTime - startTime) / 1000.0;
        seed = seed + 1;
        WriteFileMediana(time, array.length, array, seed);
        if (seed == 5) //quando acabar todas as seeds, zera e começa de novo
        {
            seed = 0;
        }
        startTime = 0;
        endTime = 0;
        time = 0;
        M_key = 0;
        M_record = 0;

    }

    public <T> void QuickSortInsert(T[] array, int left, int right) throws Exception {
        if (right - left < 10) {    //m = 10 , m = 100
            InsertSort is = new InsertSort();
            long startTime = System.currentTimeMillis();
            is.InsertionSort((Comparable[]) array);
            long endTime = System.currentTimeMillis();
            double time = (endTime - startTime) / 1000.0;
            seed = seed + 1;
            WriteFileQuickSortInsertion(time, array.length, array, true, seed);
            if (seed == 5) {
                seed = 0;
            }
            startTime = 0;
            endTime = 0;
            time = 0;
            is.QuickInsertion_key = 0;
            is.QuickInsertion_record = 0;
        } else {
            long startTime = System.currentTimeMillis();
            R_sort(array, array.length);
            long endTime = System.currentTimeMillis();
            double time = (endTime - startTime) / 1000.0;
            seed = seed + 1;
            WriteFileQuickSortInsertion(time, array.length, array, false, seed);
            if (seed == 5) {
                seed = 0;
            }
            startTime = 0;
            endTime = 0;
            time = 0;
            R_key = 0;
            R_record = 0;
        }
    }

    public <T> void sort_QuickSortInsert(T[] array, int sizeArray) throws Exception {
        QuickSortInsert(array, 0, sizeArray - 1);
    }
}
