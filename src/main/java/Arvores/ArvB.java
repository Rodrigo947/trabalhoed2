package Arvores;

public class ArvB {
    NoArvB raiz;
    int d; // minimo de chaves

    public int comparacoesBusca = 0;
    public int copiasBusca = 0;
    public int comparacoesInsere = 0;
    public int copiasInsere = 0;

    public ArvB(int d) {
        this.raiz = null;
        this.d = d;
    }

    public void insere(long k) {
        if (raiz == null) {
            raiz = new NoArvB(d, true);
            raiz.keys[0] = k;
            raiz.n = 1;
        } else {
            if (raiz.n == 2*d-1) {
                NoArvB s = new NoArvB(d, false);
                s.filhos[0] = raiz;
                s.divideFilho(0, raiz, this);
                int i = 0;
                if (s.keys[0] < k) {
                    this.comparacoesInsere++;
                    i++;
                }
                s.filhos[i].insereNCheia(k, this);
                raiz = s;
            } else
                raiz.insereNCheia(k, this);
        }
    }

    public NoArvB busca(long chave) {
        return (raiz == null) ? null : raiz.busca(chave, this);
    }

    public void imprimeRaiz() {
        raiz.imprimeChaves();
        System.out.println();
        raiz.filhos[0].imprimeChaves();
        raiz.filhos[1].imprimeChaves();
        raiz.filhos[2].imprimeChaves();
    }

}