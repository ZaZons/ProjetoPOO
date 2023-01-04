public class Cliente extends Identificador{
    private long nif;
    private ContaBancaria conta;

    public Cliente(String nome, long nif, ContaBancaria conta) {
        super(nome);
        this.nif = nif;
        this.conta = conta;
    }

    public long getNif() {
        return nif;
    }

    public ContaBancaria getConta() {
        return conta;
    }
}
