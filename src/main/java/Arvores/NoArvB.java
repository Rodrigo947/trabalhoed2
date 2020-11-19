package Arvores;

public class NoArvB {
  int n; // numero de chaves atual
  int d; // numero minimo de chaves
  int m; // ordem
  long keys[]; // chaves
  NoArvB filhos[]; // filhos
  boolean leaf = true; // se é uma folha

  // m é a ordem da árvore

  public NoArvB(int d, boolean leaf, int m) {
    this.n = 0;
    this.d = d;
    this.m = m;
    this.keys = new long[m];
    this.filhos = new NoArvB[m];
    this.leaf = leaf;
  }

  // insere um nó caso o vetor não estiver cheio
  // Procura a posição da chave,joga o resto das chaves para frente e insere
  public void insereNCheia(long k, int comparacoesInsere, int copiasInsere) {
    int i = n - 1;
    if (leaf) {
      while (i >= 0 && keys[i] > k) {
        comparacoesInsere++;
        copiasInsere++;
        keys[i + 1] = keys[i];
        i--;
      }
      keys[i + 1] = k;
      n = n + 1;
    } else { // se não for folha
      while (i >= 0 && keys[i] > k) {
        comparacoesInsere++;
        i--;
      }

      if (filhos[i + 1].n == m) {
        divideFilho(i + 1, filhos[i + 1], comparacoesInsere, copiasInsere);
        if (keys[i + 1] < k) {
          comparacoesInsere++;
          i++;
        }
      }
      filhos[i + 1].insereNCheia(k, comparacoesInsere, copiasInsere);
    }
  }

  public void divideFilho(int i, NoArvB y, int comparacoesInsere, int copiasInsere) {
    NoArvB z = new NoArvB(y.d, y.leaf, y.m);
    z.n = d - 1;

    // copia o que esta em y para z
    for (int j = 0; j < d - 1; j++) {
      copiasInsere++;
      z.keys[j] = y.keys[j + d];
    }

    // copia os nós caso não for folha
    if (y.leaf == false) {
      for (int j = 0; j < d; j++) {
        copiasInsere++;
        z.filhos[j] = y.filhos[j + d];
      }
    }
    y.n = d - 1;

    for (int j = n; j >= i + 1; j--) {
      copiasInsere++;
      filhos[j + 1] = filhos[j];
    }

    filhos[i + 1] = z;

    for (int j = n - 1; j >= i; j--) {
      copiasInsere++;
      keys[j + 1] = keys[j];
    }

    keys[i] = y.keys[d - 1];

    n = n + 1;
  }

  public void imprimeChaves() {
    for (int i = 0; i < n; i++) {
      System.out.println(keys[i]);
    }
  }

  public NoArvB busca(long chave, int comparacoesBusca) {
    int i = 0;
    while (i < n && chave > keys[i]) {
      comparacoesBusca++;
      i++;
    }

    if (keys[i] == chave) {
      comparacoesBusca++;
      return this;
    }

    if (leaf == true) {
      return null;
    }
    return filhos[i].busca(chave, comparacoesBusca);
  }
}