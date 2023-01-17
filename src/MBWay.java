public class MBWay extends CartaoCredito {
    public MBWay(long id, ContaBancaria conta, Data dataValidade, int pin, double limite) {
        super(id, conta, dataValidade, pin, limite);
    }

    @Override
    public String toString(String nivel) {
        return "MBWay {" + "\n\t" + nivel +
                "Id = " + id + "\n\t" + nivel +
                "Data de validade = " + dataValidade + "\n\t" + nivel +
                "Limite = " + limite + "\n" + nivel +
                '}';
    }

    @Override
    public boolean verificarLimite(double valor) {
        return super.verificarLimite(valor) && conta.getSaldo() - (gastos + valor) < 0;
    }
}
