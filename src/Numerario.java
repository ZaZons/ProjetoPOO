public class Numerario implements MetodoPagamento {
    private double valorEntregue;

    public Numerario(double valorEntregue) {
        this.valorEntregue = valorEntregue;
    }

    public double getValorEntregue() {
        return valorEntregue;
    }

    public void addValor(double valor) {
        valorEntregue += valor;
    }

    @Override
    public void efetuarPagamento(double valor, Estabelecimento estabelecimento) {
        if (valorEntregue < valor) {
            System.out.println("Transacao rejeitada, valor entregue insuficiente");
            return;
        }

        valorEntregue -= valor;
        Cliente clienteAnonimo = new Cliente();
        Transacao transacao = new Transacao(new Data(), valor, this, clienteAnonimo, estabelecimento);
        estabelecimento.addTransacao(transacao);
    }
}
