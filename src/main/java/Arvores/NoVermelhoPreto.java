package Arvores;

public class NoVermelhoPreto{
    public static final int preto = 0;
    public static final int vermelho = 1;
	public long chave;
   
    NoVermelhoPreto pai;

    NoVermelhoPreto esquerda;

    NoVermelhoPreto direita;

    public int cor;

    NoVermelhoPreto(){
        cor = preto;
        pai = null;
        esquerda = null;
        direita = null;
    }

    NoVermelhoPreto(long chave){
      this();
      this.chave = chave;
	}
}