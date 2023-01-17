public class CartaoCredito extends CartaoDebito{
    protected final double limite;
    protected double gastos;

    public double getLimite() {
        return limite;
    }

    public CartaoCredito(long id, ContaBancaria conta, int pin, double limite) {
        super(id, conta, pin);
        this.limite = limite;
    }

    @Override
    public void efetuarPagamento(double valor, Estabelecimento estabelecimento) {
        if (!this.verificarLimite(valor)) {
            System.out.println("Transacao rejeitada, limite ultrapassado");
            return;
        }

        if (!validacoesComuns()) {
            return;
        }

        this.addGastos(valor);
        continuarPagamento(valor, estabelecimento);
    }

    @Override
    public String toString(String nivel) {
        return "CartaoCredito {" + "\n\t" + nivel +
                "Id = " + id + "\n\t" + nivel +
                "Data de validade = " + dataValidade + "\n\t" + nivel +
                "Limite = " + getLimite() + "\n" + nivel +
                '}';
    }

    public boolean verificarLimite(double valor) {
        return gastos + valor <= limite;
    }

    public void addGastos(double valor) {
        gastos += valor;
    }
}
