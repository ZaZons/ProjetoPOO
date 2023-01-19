public class CartaoCredito extends CartaoDebito{
    protected final double limite;
    protected double gastos;

    public CartaoCredito(long id, ContaBancaria conta, int pin, double limite) {
        super(id, conta, pin);
        this.limite = limite;
    }

    public double getLimite() {
        return limite;
    }

    /**
     * Implementa a função da super classe MetodoPagamento.
     * Efetua um pagamento, depois de:
     *      Verificar se o limite foi ultrapassado;
     *      Fazer as validações comuns.
     * Adiciona o valor da compra aos gastos do cartão para verificações de limites futuras.
     * Continua o pagamento.
     */
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

    /**
     * Verifica se o limite do cartão não será ultrapassado com a próxima compra.
     */
    public boolean verificarLimite(double valor) {
        return gastos + valor <= limite;
    }

    /**
     * Adiciona o valor da última transação ao valor total gasto por este método de pagamento.
     */
    public void addGastos(double valor) {
        gastos += valor;
    }
}
