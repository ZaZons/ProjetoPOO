public class CartaoDebito implements MetodoPagamento {
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

    public String efetuarPagamento(int pin, Data data, int hora, double valor) {
        if (valor <= 0) {
            return "Valor tem de ser maior positivo";
        }

        if (!verificarCodigo(pin)) {
            return "Pagamento rejeitado, PIN errado";
        }

        if (valor > conta.getSaldo()) {
            return "Pagamento rejeitado, saldo insuficiente";
        }

        conta.addTransacao(this, data, hora, valor);
        return "Pagamento confirmado";
    }

    protected boolean verificarCodigo(int pin) {
        return this.pin == pin;
    }
}
