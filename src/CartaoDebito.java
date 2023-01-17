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

    @Override
    public void efetuarPagamento(double valor, Estabelecimento estabelecimento) {
        if (valor > conta.getSaldo()) {
            System.out.println("Transacao rejeitada, saldo insuficiente");
            return;
        }

        if (!verificarCodigo()) {
            return;
        }

        continuarPagamento(valor, estabelecimento);
    }

    protected void continuarPagamento(double valor, Estabelecimento estabelecimento) {
        Transacao transacao = new Transacao(new Data(), valor, this, conta.getCliente(), estabelecimento);
        conta.addTransacao(transacao);
    }

    public String toString(String nivel) {
        return "CartaoDebito {" + "\n\t" + nivel +
                "Id = " + id + "\n\t" + nivel +
                "Data de validade = " + dataValidade + "\n" + nivel +
                '}';
    }

    protected boolean verificarCodigo() {
        System.out.println("Introduza o pin: ");
        int pin = Leitor.lerPin();
        if (pin != this.pin) {
            System.out.println("Transacao rejeitada, o pin introduzido esta errado");
            return false;
        }

        return true;
    }
}
