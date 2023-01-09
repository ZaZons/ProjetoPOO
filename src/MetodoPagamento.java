public class MetodoPagamento {
    public String efetuarPagamento(int pin, Data data, int hora, double valor, Estabelecimento estabelecimento) {
        System.out.println(this.getClass());
        if (this instanceof CartaoDebito) {
            System.out.println("a");
            ((CartaoDebito) this).continuarPagamento();
        }
        return "";
//        int verificado = verificacao(pin, valor);
//
//        if (verificado == -1) {
//            return "Pagamento rejeitado, o valor tem de ser positivo";
//        } else if (verificado == -2) {
//            return "Pagamento rejeitado, PIN errado";
//        }
//
//        if (valor > conta.getSaldo()) {
//            return "Pagamento rejeitado, saldo insuficiente";
//        }
//
//        conta.addTransacao(this, data, hora, valor, estabelecimento);
//        return "Pagamento confirmado";
    }
}
