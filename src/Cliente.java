public class Cliente extends Identificador{
    private long nif;
    private ContaBancaria conta;

    public Cliente(String nome, long nif) {
        super(nome);
        this.nif = nif;
    }

    public long getNif() {
        return nif;
    }

    public ContaBancaria getConta() {
        return conta;
    }

    public void setConta(ContaBancaria conta) {
        this.conta = conta;
    }
}
