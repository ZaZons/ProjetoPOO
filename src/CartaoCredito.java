public class CartaoCredito extends CartaoDebito{
    protected double limite;

    public double getLimite() {
        return limite;
    }

    public CartaoCredito(long id, ContaBancaria conta, Data dataValidade, int pin, double limite) {
        super(id, conta, dataValidade, pin);
        this.limite = limite;
    }

    @Override
    public void pagar(int pin, double novoValor) {
        if (!verificarCodigo(pin)) {
            return;
        }

        if (novoValor > conta.getSaldo()) {
            return;
        }

        this.setValor(novoValor);
        conta.addTransacao(valor);
    }

    public boolean verificarLimite() {
        return limite > valor;
    }
}
