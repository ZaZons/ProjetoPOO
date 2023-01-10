import java.util.LinkedList;

public class Transacao {
    private final Data data;
    private final double valor;
    private final MetodoPagamento metodoPagamento;
    private final Cliente cliente;
    private final Estabelecimento estabelecimento;

    public Transacao(Data data, double valor, MetodoPagamento metodoPagamento, Cliente cliente, Estabelecimento estabelecimento) {
        this.data = data;
        this.valor = valor;
        this.metodoPagamento = metodoPagamento;
        this.cliente = cliente;
        this.estabelecimento = estabelecimento;
    }

    public Data getData() {
        return data;
    }

    public double getValor() {
        return valor;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public MetodoPagamento getMetodoPagamento() {
        return metodoPagamento;
    }

    public Estabelecimento getEstabelecimento() {
        return estabelecimento;
    }

    public String toString(String nivel) {
        String metodoPagamentoStr = metodoPagamento.getClass().toString().replace("class ", "");

        return "Transacao " + data + " {\n\t" + nivel +
                "Cliente {" + "\n" + cliente.toString(nivel + "\t") + "\n\t" + nivel + "},\n\t" + nivel +
                "Data = " + data + ",\n\t" + nivel +
                "Valor = " + valor + ",\n\t" + nivel +
                "Metodo de pagamento = " + metodoPagamentoStr + ",\n\t" + nivel +
                "Estabelecimento = '" + estabelecimento.getNome() + "'\n" + nivel +
                "}";
    }

    public static String listarTransacoes(LinkedList<Transacao> transacoesList, String nivel) {
        if (transacoesList.isEmpty()) {
            return "Sem transacoes";
        }

        StringBuilder res = new StringBuilder();
        res.append("[");

        for (Transacao t : transacoesList) {
            res.append("\n\t");
            res.append(nivel);
            res.append(t.toString(nivel + "\t"));

            if (!transacoesList.getLast().equals(t)) {
                res.append(',');
            }
        }

        res.append("\n");
        res.append(nivel);
        res.append("]");

        return res.toString();
    }
}
