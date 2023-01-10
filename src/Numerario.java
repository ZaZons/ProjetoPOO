public class Numerario extends MetodoPagamento {
    private final double valorEntregue;

    public Numerario(double valorEntregue) {
        this.valorEntregue = valorEntregue;
    }

    public double getValorEntregue() {
        return valorEntregue;
    }

    protected void continuarPagamento(double valor, Data data, Estabelecimento estabelecimento) {
        Cliente clienteAnonimo = new Cliente("Consumidor final", 999999990);
        Transacao transacao = new Transacao(data, valor, this, clienteAnonimo, estabelecimento);
        estabelecimento.addTransacao(transacao);
    }
}
