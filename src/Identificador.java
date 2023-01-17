import java.io.Serializable;

public class Identificador implements Serializable {
    protected String nome;

    public Identificador() {
        this.nome = Cliente.CLIENTE_GENERICO;
    }

    public Identificador(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }
}
