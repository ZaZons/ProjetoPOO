public class Identificador {
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
