import java.util.LinkedList;

public class ContaBancaria {
    private final Cliente cliente;
    private double saldo;
    private final LinkedList<Transacao> transacoesList;
    private final LinkedList<MetodoPagamento> metodosPagamentoList;
    private boolean temNumerario;

    public ContaBancaria(Cliente cliente, double saldo) {
        this.cliente = cliente;

        if (saldo < 0) {
            saldo = 0;
        }

        this.saldo = Math.abs(saldo);
        this.transacoesList = new LinkedList<>();
        this.metodosPagamentoList = new LinkedList<>();
        this.temNumerario = false;
        cliente.setConta(this);
    }

    public Cliente getCliente() {
        return cliente;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setTemNumerario(boolean temNumerario) {
        this.temNumerario = temNumerario;
    }

    public boolean isTemNumerario() {
        return temNumerario;
    }

    public LinkedList<Transacao> getTransacoesList() {
        return transacoesList;
    }

    public LinkedList<MetodoPagamento> getMetodosPagamentoList() {
        return metodosPagamentoList;
    }

    public String toString(String nivel) {
        return "Conta Bancaria {" + "\n\t" + nivel +
                "Cliente = '" + cliente.getNome() + "' (" + cliente.getNif() + "),\n\t" + nivel +
                "Saldo = " + saldo + ",\n\t" + nivel +
                "Transacoes " + Transacao.listarTransacoes(transacoesList, nivel + "\t") + ",\n\t" + nivel +
                "Metodos de pagamento " + listarMetodosPagamento(nivel + "\t") + "\n" + nivel +
                "}";
    }

    public String listarMetodosPagamento(String nivel) {
        if (metodosPagamentoList.isEmpty() || (metodosPagamentoList.size() == 1 && temNumerario)) {
            return "[\n\t" + nivel + "<Sem metodos de pagamento>\n" + nivel + "]";
        }

        StringBuilder metodosPagamentoStr = new StringBuilder();
        metodosPagamentoStr.append("[");
        for (MetodoPagamento metodo : metodosPagamentoList) {
            if (metodo instanceof CartaoDebito) {
                metodosPagamentoStr.append("\n\t");
                metodosPagamentoStr.append(nivel);
                metodosPagamentoStr.append(((CartaoDebito) metodo).toString(nivel + "\t"));
            }

            if (!metodosPagamentoList.getLast().equals(metodo)) {
                metodosPagamentoStr.append(",");
            }
        }

        metodosPagamentoStr.append("\n");
        metodosPagamentoStr.append(nivel);
        metodosPagamentoStr.append("]");

        return metodosPagamentoStr.toString();
    }

    public void addTransacao(Transacao transacao) {
        if (transacoesList.contains(transacao)) {
            return;
        }

        addMetodoPagamento(transacao.getMetodoPagamento());

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
