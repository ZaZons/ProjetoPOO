import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public abstract class Ficheiros {
    public static void gravar(Estabelecimento estabelecimento) {
        String path = "saveFile.txt";

        try {
            FileOutputStream outputStream = new FileOutputStream(path);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
            objectOutputStream.writeObject(estabelecimento);
            objectOutputStream.close();

            System.out.println("Dados gravados");
        } catch (Exception e) {
            System.out.println("Erro no ficheiro: " + e);
        }
    }

    public static Estabelecimento ler() {
        String path = "saveFile.txt";
        Estabelecimento estabelecimento = null;

        try {
            FileInputStream outputStream = new FileInputStream(path);
            ObjectInputStream objectOutputStream = new ObjectInputStream(outputStream);
            estabelecimento = (Estabelecimento) objectOutputStream.readObject();
            objectOutputStream.close();

            System.out.println("Dados carregados");
        } catch (Exception e) {
            System.out.println("Erro no ficheiro: " + e);
        }

        return estabelecimento;
    }
}
