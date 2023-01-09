public class Transacao {
    private Data data;
    private int hora;
    private double valor;
    private MetodoPagamento metodoPagamento;
    private Cliente cliente;
    private Estabelecimento estabelecimento;

    public Transacao(Data data, int hora, double valor, MetodoPagamento metodoPagamento, Cliente cliente, Estabelecimento estabelecimento) {
        this.data = data;
        this.hora = hora;
        this.valor = valor;
        this.metodoPagamento = metodoPagamento;
        this.cliente = cliente;
        this.estabelecimento = estabelecimento;
    }

    public Data getData() {
        return data;
    }

    public int getHora() {
        return hora;
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
}
