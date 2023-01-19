import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public abstract class Ficheiros {
    private static final String file = "saveFile.txt";

    /**
     * Grava os dados do estabelecimento num ficheiro de texto
     */
    public static void gravar(Estabelecimento estabelecimento) {
        try {
            FileOutputStream outputStream = new FileOutputStream(file);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
            objectOutputStream.writeObject(estabelecimento);
            objectOutputStream.close();

            System.out.println("Dados gravados");
        } catch (Exception e) {
            System.out.println("Erro no ficheiro: " + e);
        }
    }

    /**
     * Lê os dados guardados no ficheiro
     * Devolve o estabelecimento lido
     */
    public static Estabelecimento ler() {
        Estabelecimento estabelecimento = new Estabelecimento("Novo estabelecimento", 0f);
        try {
            FileInputStream outputStream = new FileInputStream(file);
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
