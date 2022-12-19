public class Transacao {
    private Data data;
    private int hora;
    private double valor;
    // todo private MetodoPagamento metodoPagamento;

    public Transacao(Data data, int hora, double valor) {
        this.data = data;
        this.hora = hora;
        this.valor = valor;
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
}
