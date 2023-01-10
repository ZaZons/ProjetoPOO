public class CartaoCredito extends CartaoDebito{
    protected final double limite;
    protected double gastos;

    public double getLimite() {
        return limite;
    }

    public CartaoCredito(long id, ContaBancaria conta, Data dataValidade, int pin, double limite) {
        super(id, conta, dataValidade, pin);
        this.limite = limite;
    }

    public boolean verificarLimite(double valor) {
        return gastos + valor <= limite;
    }

    public void addGastos(double valor) {
        gastos += valor;
    }
}
