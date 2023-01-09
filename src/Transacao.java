public class Transacao {
    private Data data;
    private int hora;
    private double valor;
    private MetodoPagamento metodoPagamento;
    private ContaBancaria contaBancaria;
    private Estabelecimento estabelecimento;

    public Transacao(Data data, int hora, double valor, MetodoPagamento metodoPagamento, ContaBancaria contaBancaria, Estabelecimento estabelecimento) {
        this.data = data;
        this.hora = hora;
        this.valor = valor;
        this.metodoPagamento = metodoPagamento;
        this.contaBancaria = contaBancaria;
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

    /**public MetodoPagamento getMetodoPagamento() {
        return metodoPagamento;
    }**/

    public ContaBancaria getContaBancaria() {
        return contaBancaria;
    }

    public Estabelecimento getEstabelecimento() {
        return estabelecimento;
    }
}
