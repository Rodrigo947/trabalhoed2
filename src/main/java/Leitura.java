import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;



import Livro.Livro;

public class Leitura {

    /**
     * Converte o objeto para um vetor de bytes.
     * 
     * @param obj objeto a ser convertido
     *
     * 
     * @return vetor de bytes
     */
    public static byte[] toBytes(Livro obj) {

        byte[] bytes = null;

        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(obj);
            bytes = baos.toByteArray();
        }

        catch (IOException e) {
            // Erro na serialização
            e.printStackTrace();
        }
        return bytes;
    }
    // END ------------------

    /**
     * Converte o vetor bytes para um objeto
     *
     * @param bytes vetor de bytes a ser convertido
     * 
     * @return retorna um objeto do tipo livro
     */
    public static Livro toObjeto(byte[] bytes) {
        Livro obj = null;

        try {
            ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
            ObjectInputStream ois = new ObjectInputStream(bais);
            obj = (Livro) ois.readObject();
        } catch (IOException e) {
            System.out.println("Erro na deserialização");
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.out.println("Sequência de bytes não corresponde a classe do objeto");
            e.printStackTrace();
        }
        return obj;
    }
    // END ------------------

    /**
     * A partir do arquivo a função retorna a linha que pertence o byte passado
     *
     * @param file         arquivo que contem os dados em bytes
     * @param randomByte   byte alvo
     * @param sizeObjBytes tamanho da linha que armazena o objeto no arquivo
     * 
     * @return retorna um vetor de bytes
     */
    public static byte[] randomLineFile(File file, long randomByte, int sizeObjBytes) {
        FileInputStream fileInputStream = null;
        byte[] lineBytes = new byte[sizeObjBytes];
        try {
            // Procura em qual faixa de bytes está o randomByte
            boolean flag = false;
            long start = 0;
            long end = sizeObjBytes;
            while (!flag) {
                if (randomByte < start || randomByte > end) {
                    start = end + 1;
                    end += sizeObjBytes;
                } else
                    flag = true;
            }

            fileInputStream = new FileInputStream(file);
            if (start > 1)
                fileInputStream.skip(start - 1); //Pula o cursor de leitura para o começo da linha a qual pertence o randomByte

            fileInputStream.read(lineBytes, 0, sizeObjBytes); //lê a quant de bytes definida no sizeObjBytes e guarda no lineBytes começando pelo index 0 do array
            fileInputStream.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lineBytes;
    }
    // END ------------------
}
