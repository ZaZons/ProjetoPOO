public class Cliente {

    private String nome;
    private long nif;
    private ContaBancaria conta;

    public Cliente(String nome, long nif, ContaBancaria conta) {
        this.nome = nome;
        this.nif = nif;
        this.conta = conta;
    }

    public String getNome() {
        return nome;
    }

    public long getNif() {
        return nif;
    }

    public ContaBancaria getConta() {
        return conta;
    }
}
