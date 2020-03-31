
import Livro.Livro;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author icout
 */
public class Leitura {
    public void leitura() throws FileNotFoundException, IOException{
        Scanner arquivo = new Scanner(new FileReader("data/dataset.csv"));
        arquivo.close();
    }
    public void converteBinario(ArrayList<Livro> livros) throws IOException {
        FileWriter binario = new FileWriter("data/dataset.bin");
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(livros);
        oos.flush();
        byte[] data = bos.toByteArray();
        for (int i = 0; i < data.length; i++) {
            binario.write(data[i]); 
        }
        binario.close();
        /*Scanner lerBin = new Scanner("data/dataset.bin");
        while(lerBin.hasNextLine()){
            System.out.println(lerBin.nextLine());
        }
        lerBin.close();*/
    }
}
