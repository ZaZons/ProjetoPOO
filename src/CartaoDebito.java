public class CartaoDebito extends MetodoPagamento {
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

    protected void continuarPagamento(double valor, Data data, Estabelecimento estabelecimento) {
        Transacao transacao = new Transacao(data, valor, this, conta.getCliente(), estabelecimento);
        conta.addTransacao(transacao);
    }

//    protected int verificacao(int pin, double valor) {
//        if (valor <= 0) {
//            return -1;
//        }
//
//        if (!verificarCodigo(pin)) {
//            return -2;
//        }
//
//        return 0;
//    }

    protected boolean verificarCodigo(int pin) {
        return this.pin == pin;
    }
}
