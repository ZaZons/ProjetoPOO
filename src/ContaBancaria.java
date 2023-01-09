import java.util.LinkedList;

public class ContaBancaria {
    private Cliente cliente;
    private double saldo;
    private LinkedList<Transacao> transacoesList;
    private LinkedList<MetodoPagamento> metodosPagamentoList;

    public ContaBancaria(Cliente cliente, double saldo) {
        this.cliente = cliente;
        this.saldo = saldo;
        this.transacoesList = new LinkedList<>();
        this.metodosPagamentoList = new LinkedList<>();
        cliente.setConta(this);
    }

    public Cliente getCliente() {
        return cliente;
    }

    public double getSaldo() {
        return saldo;
    }

    public LinkedList<Transacao> getTransacoesList() {
        return transacoesList;
    }

    public LinkedList<MetodoPagamento> getMetodosPagamentoList() {
        return metodosPagamentoList;
    }

    public void addTransacao(Transacao transacao) {
        if (transacoesList.contains(transacao)) {
            return;
        }

        addMetodoPagamento(transacao.getMetodoPagamento());

        transacao.getEstabelecimento().addCliente(cliente);
        transacao.getEstabelecimento().addTransacao(transacao);

        transacoesList.add(transacao);
        saldo -= transacao.getValor();
    }

    public void addMetodoPagamento(MetodoPagamento metodo) {
        if (!metodosPagamentoList.contains(metodo)) {
            metodosPagamentoList.add(metodo);
        }
    }
}
