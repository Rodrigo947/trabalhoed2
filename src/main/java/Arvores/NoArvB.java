package Arvores;

public class NoArvB {
  int n; // numero de chaves atual
  int d; // numero minimo de chaves
  long keys[]; // chaves
  NoArvB filhos[]; // filhos
  boolean leaf = true; // se é uma folha

  // m é a ordem da árvore

  public NoArvB(int d, boolean leaf) {
    this.n = 0;
    this.d = d;
    this.keys = new long[2*d-1];
    this.filhos = new NoArvB[2*d];
    this.leaf = leaf;
  }

  // insere um nó caso o vetor não estiver cheio
  // Procura a posição da chave,joga o resto das chaves para frente e insere
  public void insereNCheia(long k, ArvB arv) {
    int i = n - 1;
    if (leaf) {
      while (i >= 0 && keys[i] > k) {
        arv.comparacoesInsere++;
        arv.copiasInsere++;
        keys[i + 1] = keys[i];
        i--;
      }
      keys[i + 1] = k;
      n = n + 1;
    } else { // se não for folha
      while (i >= 0 && keys[i] > k) {
        arv.comparacoesInsere++;
        i--;
      }

      if (filhos[i + 1].n == 2*d-1) {
        divideFilho(i + 1, filhos[i + 1], arv);
        if (keys[i + 1] < k) {
          arv.comparacoesInsere++;
          i++;
        }
      }
      filhos[i + 1].insereNCheia(k, arv);
    }
  }

  public void divideFilho(int i, NoArvB y, ArvB arv) {
    NoArvB z = new NoArvB(y.d, y.leaf);
    z.n = d - 1;

    // copia o que esta em y para z
    for (int j = 0; j < d - 1; j++) {
      arv.copiasInsere++;
      z.keys[j] = y.keys[j + d];
    }

    // copia os nós caso não for folha
    if (y.leaf == false) {
      for (int j = 0; j < d; j++) {
        arv.copiasInsere++;
        z.filhos[j] = y.filhos[j + d];
      }
    }
    y.n = d - 1;

    for (int j = n; j >= i + 1; j--) {
      arv.copiasInsere++;
      filhos[j + 1] = filhos[j];
    }

    filhos[i + 1] = z;

    for (int j = n - 1; j >= i; j--) {
      arv.copiasInsere++;
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

  public NoArvB busca(long chave, ArvB arv) {
    int i = 0;
    
    while (i < n && chave > keys[i]) {
      arv.comparacoesBusca++;
      i++;
    }
    
    if (i < n && keys[i] == chave) {
      arv.comparacoesBusca++;
      return this;
    }

    if (leaf == true) {
      return null;
    }
    return filhos[i].busca(chave, arv);
  }
}