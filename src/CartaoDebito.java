public class CartaoDebito extends MetodoPagamento{
    protected long id;
    protected ContaBancaria conta;
    protected Data dataValidade;
    protected int pin;

    public CartaoDebito(long id, ContaBancaria conta, Data dataValidade, int pin) {
        this.id = id;
        this.conta = conta;
        this.dataValidade = dataValidade;
        this.pin = pin;
    }

    public long getId() {
        return id;
    }

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

    protected boolean verificarCodigo(int pin) {
        return this.pin == pin;
    }
}
