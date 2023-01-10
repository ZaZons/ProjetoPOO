public class MBWay extends CartaoCredito {
    public MBWay(long id, ContaBancaria conta, Data dataValidade, int pin, double limite) {
        super(id, conta, dataValidade, pin, limite);
    }

    @Override
    public boolean verificarLimite(double valor) {
        return super.verificarLimite(valor) && gastos <= conta.getSaldo();
    }
}
