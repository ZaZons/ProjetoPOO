public class CartaoCredito extends CartaoDebito{
    protected final double limite;
    protected double gastos;

    public double getLimite() {
        return limite;
    }

    public CartaoCredito(long id, ContaBancaria conta, Data dataValidade, int pin, double limite) {
        super(id, conta, dataValidade, pin);
        this.limite = limite;
    }

    @Override
    public void efetuarPagamento(double valor, Estabelecimento estabelecimento) {
        if (!verificarLimite(valor)) {
            System.out.println("Transacao rejeitada, limite ultrapassado");
            return;
        }

        if (!this.verificarCodigo()) {
            return;
        }

        continuarPagamento(valor, estabelecimento);
    }

    @Override
    public String toString(String nivel) {
        return "CartaoCredito {" + "\n\t" + nivel +
                "Id = " + id + "\n\t" + nivel +
                "Data de validade = " + dataValidade + "\n\t" + nivel +
                "Limite = " + limite + "\n" + nivel +
                '}';
    }

    public boolean verificarLimite(double valor) {
        return gastos + valor <= limite;
    }

    public void addGastos(double valor) {
        gastos += valor;
    }
}
