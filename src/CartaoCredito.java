public class CartaoCredito extends CartaoDebito{
    protected double limite;

    public double getLimite() {
        return limite;
    }

    public CartaoCredito(long id, ContaBancaria conta, Data dataValidade, int pin, double limite) {
        super(id, conta, dataValidade, pin);
        this.limite = limite;
    }

    public boolean verificarLimite(double valor) {
        return conta.getSaldo() - valor < -limite;
    }
}
