public class Numerario implements MetodoPagamento {
    private double valorEntregue;

    public Numerario(double valorEntregue) {
        this.valorEntregue = valorEntregue;
    }

    public double getValorEntregue() {
        return valorEntregue;
    }

    /**
     * Recebe um valor que o utilizador pretende adicionar ao valor do numerário.
     * Adiciona valor recebido ao valor entregue.
     */
    public void addValor(double valor) {
        valorEntregue += valor;
    }

    /**
     * Implementa a função da super classe MetodoPagamento.
     * Efetua um pagamento, depois de:
     *      Verificar se o valor entregue foi suficiente.
     * Subtrai o valor da transação ao valor entregue em dinheiro.
     * Regista o cliente da transação como "Consumidor final" e com NIF genérico.
     */
    @Override
    public void efetuarPagamento(double valor, Estabelecimento estabelecimento, Cliente cliente) {
        if (valorEntregue < valor) {
            System.out.println("Transacao rejeitada, valor entregue insuficiente");
            return;
        }

        valorEntregue -= valor;
        Transacao transacao = new Transacao(valor, this, cliente, estabelecimento);
        estabelecimento.addTransacao(transacao);
    }
}
