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
    public String efetuarPagamento(int pin, Data data, int hora, double valor) {
        if (valor <= 0) {
            return "Valor tem de ser maior positivo";
        }

        if (!verificarCodigo(pin)) {
            return "Pagamento rejeitado, PIN errado";
        }

        if (valor > limite) {
            return "Pagamento rejeitado, limite excedido";
        }

        conta.addTransacao(this, data, hora, valor);
        return "Pagamento confirmado";
    }
}
