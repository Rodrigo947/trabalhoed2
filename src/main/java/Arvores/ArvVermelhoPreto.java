package Arvores;

public class ArvVermelhoPreto {
    private NoVermelhoPreto nil = new NoVermelhoPreto();
    private NoVermelhoPreto raiz = nil;
    int comparacoesBusca = 0;
    int copiasBusca = 0;
    int comparacoesInsere = 0;
    int copiasInsere = 0;

    public ArvVermelhoPreto() {
        raiz.esquerda = nil;
        raiz.direita = nil;
        raiz.pai = nil;
    }

    // Retorna se o No é nulo
    private boolean isNil(NoVermelhoPreto no) {
        return no == nil;
    }

    private void rotacaoEsquerda(NoVermelhoPreto x) {
        NoVermelhoPreto y;
        y = x.direita;
        x.direita = y.esquerda;
        copiasInsere++;

        if (!isNil(y.esquerda))
            y.esquerda.pai = x;
        y.pai = x.pai;

        if (isNil(x.pai))
            raiz = y;

        else if (x.pai.esquerda == x)
            x.pai.esquerda = y;

        else
            x.pai.direita = y;
        y.esquerda = x;
        x.pai = y;
    }

    private void rotacaoDireita(NoVermelhoPreto y) {

        NoVermelhoPreto x = y.esquerda;
        y.esquerda = x.direita;
        copiasInsere++;

        if (!isNil(x.direita))
            x.direita.pai = y;
        x.pai = y.pai;

        if (isNil(y.pai)) // se o pai é nulo então o nó se torna raiz
            raiz = x;
        else if (y.pai.direita == y)
            y.pai.direita = x;
        else
            y.pai.esquerda = x;

        x.direita = y;
        y.pai = x;
    }

    public void insere(long chave) {
        auxinsere(new NoVermelhoPreto(chave));
    }

    private void auxinsere(NoVermelhoPreto z) {

        NoVermelhoPreto y = nil;
        NoVermelhoPreto x = raiz;
        copiasInsere++;

        while (!isNil(x)) {
            y = x;

            if (z.chave < x.chave) {
                comparacoesInsere++;
                x = x.esquerda;
            } else {
                comparacoesInsere++;
                x = x.direita;
            }
        }
        z.pai = y;

        if (isNil(y))
            raiz = z;
        else if (z.chave < y.chave) {
            comparacoesInsere++;
            y.esquerda = z;
        } else {
            comparacoesInsere++;
            y.direita = z;
        }

        z.esquerda = nil;
        z.direita = nil;
        z.cor = NoVermelhoPreto.vermelho;

        correcaoArvore(z);

    }

    // Depois da insercao, verifica se todas as condições da arvore
    // foram atendidas
    private void correcaoArvore(NoVermelhoPreto z) {

        NoVermelhoPreto y = nil;
        while (z.pai.cor == NoVermelhoPreto.vermelho) {

            if (z.pai == z.pai.pai.esquerda) {

                y = z.pai.pai.direita;

                if (y.cor == NoVermelhoPreto.vermelho) {
                    z.pai.cor = NoVermelhoPreto.preto;
                    y.cor = NoVermelhoPreto.preto;
                    z.pai.pai.cor = NoVermelhoPreto.vermelho;
                    z = z.pai.pai;
                } else if (z == z.pai.direita) {
                    z = z.pai;
                    rotacaoEsquerda(z);
                } else {
                    z.pai.cor = NoVermelhoPreto.preto;
                    z.pai.pai.cor = NoVermelhoPreto.vermelho;
                    rotacaoDireita(z.pai.pai);
                }
            } else {

                y = z.pai.pai.esquerda;

                if (y.cor == NoVermelhoPreto.vermelho) {
                    z.pai.cor = NoVermelhoPreto.preto;
                    y.cor = NoVermelhoPreto.preto;
                    z.pai.pai.cor = NoVermelhoPreto.vermelho;
                    z = z.pai.pai;
                } else if (z == z.pai.esquerda) {
                    z = z.pai;
                    rotacaoDireita(z);
                } else {
                    z.pai.cor = NoVermelhoPreto.preto;
                    z.pai.pai.cor = NoVermelhoPreto.vermelho;
                    rotacaoEsquerda(z.pai.pai);
                }
            }
        }
        raiz.cor = NoVermelhoPreto.preto;

    }

    public NoVermelhoPreto busca(long chave) {
        NoVermelhoPreto atual = raiz;
        while (!isNil(atual)) {

            if (atual.chave == chave) {
                comparacoesBusca++;
                return atual;
            }

            else if (atual.chave < chave) {
                comparacoesBusca++;
                atual = atual.direita;
            } else {
                comparacoesBusca++;
                atual = atual.esquerda;
            }
        }
        return null;
    }

    public void imprime() {
        System.out.println("raiz: " + raiz.direita.direita.direita.chave);
        System.out.println("cor raiz: " + raiz.direita.direita.direita.cor);
    }
}