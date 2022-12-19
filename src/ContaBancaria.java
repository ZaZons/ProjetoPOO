import java.util.LinkedList;

public class ContaBancaria {
    //private Cliente cliente;
    private double saldo;
    private LinkedList<Transacao> transacoesList;
    private LinkedList<MetodoPagamento> metodosPagamentoList;

    public ContaBancaria(/*Cliente cliente, */double saldo) {
      //  this.cliente = cliente;
        this.saldo = saldo;
        this.transacoesList = new LinkedList<>();
        this.metodosPagamentoList = new LinkedList<>();
    }

//    public Cliente getCliente() {
//        return cliente;
//    }

    public double getSaldo() {
        return saldo;
    }

    public LinkedList<Transacao> getTransacoesList() {
        return transacoesList;
    }

    public LinkedList<MetodoPagamento> getMetodosPagamentoList() {
        return metodosPagamentoList;
    }

    public void addTransacao(double valor) {
        Data data = new Data(0, 0, 0);
        Transacao transacao = new Transacao(data, 0, valor);

        if (transacoesList.contains(transacao)) {
            return;
        }

        transacoesList.add(transacao);
        saldo -= valor;
    }
}
