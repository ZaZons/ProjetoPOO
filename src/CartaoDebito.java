public class CartaoDebito extends MetodoPagamento {
    protected long id;
    protected ContaBancaria conta;
    protected Data dataValidade;
    protected int pin;

    public CartaoDebito(long id, ContaBancaria conta, Data dataValidade, int pin) {
        this.id = id;
        this.conta = conta;
        this.dataValidade = dataValidade;
        this.pin = pin;
    }

    public long getId() {
        return id;
    }

    public String continuarPagamento() {
        System.out.println("abla");
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

    protected int verificacao(int pin, double valor) {
        if (valor <= 0) {
            return -1;
        }

        if (!verificarCodigo(pin)) {
            return -2;
        }

        return 0;
    }

    protected boolean verificarCodigo(int pin) {
        return this.pin == pin;
    }
}
