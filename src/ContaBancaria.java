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

    public String toString(String nivel) {
        return "Conta Bancaria {" + "\n\t" + nivel +
                "Cliente = '" + cliente.getNome() + "',\n\t" + nivel +
                "Saldo = " + saldo + ",\n\t" + nivel +
                "Transacoes " + Transacao.listarTransacoes(transacoesList, nivel + "\t") + ",\n\t" + nivel +
                "Metodos de pagamento " + listarMetodosPagamento(metodosPagamentoList, nivel + "\t") + "\t" + nivel + "]\n" + nivel +
                "}";
    }

    public static String listarMetodosPagamento(LinkedList<MetodoPagamento> metodosPagamentoList, String nivel) {
        StringBuilder metodosPagamentoStr = new StringBuilder();

        if (metodosPagamentoList.isEmpty()) {
            metodosPagamentoStr.append("Nao existem metodos de pagamento associados a esta conta");
        } else {
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
        }

        return metodosPagamentoStr.toString();
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
