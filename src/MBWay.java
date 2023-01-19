public class MBWay extends CartaoCredito {
    public MBWay(long id, ContaBancaria conta, int pin, double limite) {
        super(id, conta, pin, limite);
    }

    @Override
    public String toString(String nivel) {
        return "MBWay {" + "\n\t" + nivel +
                "Id = " + id + "\n\t" + nivel +
                "Data de validade = " + dataValidade + "\n\t" + nivel +
                "Limite = " + limite + "\n" + nivel +
                '}';
    }

    /**
     * Verifica o limite do MBWay e se a conta tem saldo suficiente para a transação.
     */
    @Override
    public boolean verificarLimite(double valor) {
        return super.verificarLimite(valor) && conta.getSaldo() - (gastos + valor) < 0;
    }
}
