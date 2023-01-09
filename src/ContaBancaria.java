import java.util.LinkedList;

public class ContaBancaria {
    private Cliente cliente;
    private double saldo;
    private LinkedList<Transacao> transacoesList;
    private LinkedList<MetodoPagamento> metodosPagamentoList;

    public ContaBancaria(/*Cliente cliente, */double saldo) {
      //  this.cliente = cliente;
        this.saldo = saldo;
        this.transacoesList = new LinkedList<>();
        this.metodosPagamentoList = new LinkedList<>();
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

    public void addTransacao(MetodoPagamento metodo, Data data, int hora, double valor, Estabelecimento estabelecimento) {
        Transacao transacao = new Transacao(data, hora, valor, metodo, this, estabelecimento); //TODO

        if (transacoesList.contains(transacao)) {
            return;
        }

        addMetodoPagamento(metodo);

        transacoesList.add(transacao);
        saldo -= valor;
    }

    public void addMetodoPagamento(MetodoPagamento metodo) {
        if (!metodosPagamentoList.contains(metodo)) {
            metodosPagamentoList.add(metodo);
        }
    }
}
