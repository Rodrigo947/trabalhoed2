package quicksort;

class InsertSort<T extends Comparable<T>> implements Insert<T> {

    //variaveis QuickSort Inserção
    int QuickInsertion_key = 0; //comparações de chaves QuickSort Inserção
    int QuickInsertion_record = 0; //cópia de registro QuickSort Inserção

    @Override
    public void InsertionSort(T[] vet) {
        for (int i = 1; i < vet.length; i++) {
            int n = i;

            while (n > 0 && vet[n - 1].compareTo(vet[n]) > 0) {
                QuickInsertion_record = QuickInsertion_record + 1;
                QuickInsertion_key = QuickInsertion_key + 1;
                T temp = vet[n];
                vet[n] = vet[n - 1];
                vet[n - 1] = temp;
                n--;
            }
        }
    }
}

class QuickSort<T extends Comparable<T>> implements Sort<T> {

    private T[] vet;
    //variaveis para QuickSort Recursivo
    private int R_key = 0; //comparações de chaves recursivo
    private int R_record = 0;//cópia de registro recursivo
    //variaveis para QuickSort Mediana
    private int M_key = 0; //comparações de chaves mediana
    private int M_record = 0; //cópia de registro mediana

    private void swap(int a, int b) {
        T x = vet[a];
        vet[a] = vet[b];
        vet[b] = x;
    }

    private void M_Quicksort(int left, int right) {

        if (left >= right) {
            M_key = M_key + 1;
            return;
        }

        T pivot = vet[(right + left) / 2];

        int i = left;
        int j = right;

        while (i <= j) {
            M_key = M_key + 1;

            while (vet[i].compareTo(pivot) < 0) {
                M_key = M_key + 1;
                i++;
            }

            while (vet[j].compareTo(pivot) > 0) {
                M_key = M_key + 1;
                j--;
            }

            if (i <= j) {
                M_key = M_key + 1;
                swap(i, j);
                M_record = M_record + 1;
                i++;
                j--;
            }
        }
        M_Quicksort(left, j);
        M_Quicksort(i, right);
    }

    public boolean isTypeLivro(T[] t) {
        return t instanceof Livro[]; // verifica se o vetor passado é do tipo Livro
    }

    public boolean isTypeString(T[] t) {
        return t instanceof String[]; // verifica se o vetor passado é do tipo String
    }

    private void R_Quicksort(int left, int right) {

        if (left >= right) {
            R_key = R_key + 1;
            return;
        }
        T pivo = vet[left + (right - left) / 2];

        int i = left;
        int j = right;

        while (i <= j) {
            R_key = R_key + 1;

            while (vet[i].compareTo(pivo) < 0) {
                R_key = R_key + 1;
                i++;
            }

            while (vet[j].compareTo(pivo) > 0) {
                R_key = R_key + 1;
                j--;
            }

            if (i <= j) {
                R_key = R_key + 1;
                swap(i, j);
                R_record = R_record + 1;
                i++;
                j--;
            }
        }
        R_Quicksort(left, j);
        R_Quicksort(i, right);
    }

    //sort recursivo
    @Override
    public void R_sort(T[] vet) {
        this.vet = vet;
        long startTime = System.currentTimeMillis();
        R_Quicksort(0, vet.length - 1);
        long endTime = System.currentTimeMillis();
        double time = (endTime - startTime) / 1000.0;
        System.out.println("Tempo de execução QuickSort Recursivo:" + time);
        System.out.println("Comparação de chaves: " + R_key);
        System.out.println("Cópias de registro: " + R_record + "\n");
        startTime = 0;
        endTime = 0;
        time = 0;
        R_key = 0;
        R_record = 0;

    }

    //sort mediana
    @Override
    public void M_sort(T[] vet) {
        this.vet = vet;

        long startTime = System.currentTimeMillis();
        M_Quicksort(0, vet.length - 1);
        long endTime = System.currentTimeMillis();
        double time = (endTime - startTime) / 1000.0;
        System.out.println("Tempo de execução QuickSort Mediana:" + time);
        System.out.println("Comparação de chaves: " + M_key);
        System.out.println("Cópias de registro: " + M_record + "\n");
        startTime = 0;
        endTime = 0;
        time = 0;
        M_key = 0;
        M_record = 0;

    }

    public void QuickSortInsert(T[] vet, int left, int right) {
        if (right - left < 10) {    //m = 10 , m = 100
            InsertSort is = new InsertSort();
            long startTime = System.currentTimeMillis();
            is.InsertionSort(vet);
            long endTime = System.currentTimeMillis();
            double time = (endTime - startTime) / 1000.0;
            System.out.println("Tempo de execução QuickSort Inserção:" + time);
            System.out.println("Comparação de chaves: " + is.QuickInsertion_key);
            System.out.println("Cópias de registro: " + is.QuickInsertion_record + "\n");
            startTime = 0;
            endTime = 0;
            time = 0;
            is.QuickInsertion_key = 0;
            is.QuickInsertion_record = 0;
        } else {
            long startTime = System.currentTimeMillis();
            R_sort(vet);
            long endTime = System.currentTimeMillis();
            double time = (endTime - startTime) / 1000.0;
            System.out.println("Tempo de execução QuickSort Inserção:" + time);
            System.out.println("Comparação de chaves: " + R_key); //mesmas variaveis do QuickSort recursivo
            System.out.println("Cópias de registro: " + R_record + "\n");
            startTime = 0;
            endTime = 0;
            time = 0;
            R_key = 0;
            R_record = 0;
        }
    }

    public void sort_QuickSortInsert(T[] vet) {
        this.vet = vet;
        QuickSortInsert(vet, 0, vet.length - 1);
    }

    public void printArrayObj(Livro[] vet, int tam) {
        for (int i = 0; i < tam; i++) {
            System.out.println(vet[i].getTitle());
        }
    }

    public void printArrayString(String[] vet, int tam) {
        for (int i = 0; i < tam; i++) {
            System.out.println(vet[i]);
        }
    }
}
