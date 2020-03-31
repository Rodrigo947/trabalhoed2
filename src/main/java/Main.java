
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import Livro.Livro;

public class Main {

    public static void main(String[] args) throws IOException {

        //TESTE DO TAMNHO DO OBJETO COM TITULOS DIFERENTES
        OutputStream arq = new FileOutputStream("data/dataBytes.bin"); 
        
        Livro l1 = new Livro();
        byte[] bytes1 = Leitura.toBytes(l1); 
        arq.write(bytes1);
        arq.close();

        File file = new File("data/dataBytes.bin");
        System.out.println("Tamanho de um objeto Livro sem nenhum dado: "+file.length());

        //-----------------------------------------------------------------
        OutputStream arq2 = new FileOutputStream("data/dataBytes.bin"); 

        Livro l2 = new Livro();
        l2.setTitle("title");
        byte[] bytes2 = Leitura.toBytes(l2);
        arq2.write(bytes2);
        arq2.close();

        File file2 = new File("data/dataBytes.bin");
        System.out.println("Tamanho de um objeto Livro com atibuicao do titulo: "+file2.length());

        //-----------------------------------------------------------------
        OutputStream arq3 = new FileOutputStream("data/dataBytes.bin"); 

        Livro l3 = new Livro();
        l3.setTitle("titletitletitletitletitletitletitletitletitletitletitletitle");
        byte[] bytes3 = Leitura.toBytes(l3);
        arq3.write(bytes3);
        arq3.close();

        File file3 = new File("data/dataBytes.bin");
        System.out.println("Tamanho de um objeto Livro com atibuicao do titulo com mais caracteres: "+file3.length());
        //END TESTE

        //TESTE DAS FUNÇÕES DE PROCURA DE LINHA NO ARQUIVO E CONVERSÃO PARA OBJETO
        OutputStream a = new FileOutputStream("data/dataBytes.bin"); 
        
        Livro livro1 = new Livro();
        livro1.setTitle("Livro 1");
        byte[] b1 = Leitura.toBytes(livro1); 
        a.write(b1);

        Livro livro2 = new Livro();
        livro2.setTitle("Livro 2");
        byte[] b2 = Leitura.toBytes(livro2); 
        a.write(b2);

        a.close();

        File f = new File("data/dataBytes.bin");

        Random r = new Random();
        r.setSeed(123456);
        int randomByte = r.nextInt((int)f.length());
        System.out.println("Numero Sorteado: "+randomByte);
        
        byte[] linha = Leitura.randomLineFile(f, randomByte, 533);
        Livro l = Leitura.toObjeto(linha);
        System.out.println(l.getTitle());
        //END TESTE
        
    }
}
