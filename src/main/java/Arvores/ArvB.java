package Arvores;

public class ArvB {
    NoArvB raiz;
    int d; // minimo de chaves
    int m; // ordem
    int comparacoesBusca = 0;
    int copiasBusca = 0;
    int comparacoesInsere = 0;
    int copiasInsere = 0;

    public ArvB(int d, int m) {
        this.raiz = null;
        this.d = d;
        this.m = m;
    }

    public void insere(long k) {
        if (raiz == null) {
            raiz = new NoArvB(d, true, m);
            raiz.keys[0] = k;
            raiz.n = 1;
        } else {
            if (raiz.n == m) {
                NoArvB s = new NoArvB(d, false, m);
                s.filhos[0] = raiz;
                s.divideFilho(0, raiz, comparacoesInsere, copiasInsere);
                int i = 0;
                if (s.keys[0] < k) {
                    comparacoesInsere++;
                    i++;
                }
                s.filhos[i].insereNCheia(k, comparacoesInsere, copiasInsere);
                raiz = s;
            } else
                raiz.insereNCheia(k, comparacoesInsere, copiasInsere);
        }
    }

    public NoArvB busca(long chave) {
        return (raiz == null) ? null : raiz.busca(chave, comparacoesBusca);
    }

    public void imprimeRaiz() {
        raiz.imprimeChaves();
        System.out.println();
        raiz.filhos[0].imprimeChaves();
        raiz.filhos[1].imprimeChaves();
        raiz.filhos[2].imprimeChaves();
    }

}