public class Main {
    public static void main(String[] args) {
        Cliente cliente = new Cliente("a", 1);
        ContaBancaria conta = new ContaBancaria(cliente, 20);
        Estabelecimento estabelecimento = new Estabelecimento("b", 0);
        CartaoDebito debito = new CartaoDebito(1, conta, new Data(1, 1, 1), 1234);
        CartaoCredito credito = new CartaoCredito(1, conta, new Data(1, 1, 1), 1234, 500);

        debito.efetuarPagamento(1, new Data(1, 1, 1), 1, 15, estabelecimento);
        credito.efetuarPagamento(1, new Data(1, 1, 1), 1, 15, estabelecimento);
    }
}