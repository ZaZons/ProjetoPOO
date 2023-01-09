public class MetodoPagamento {
    public void efetuarPagamento(double valor, int pin, Data data, int hora, Estabelecimento estabelecimento) {
        if (valor <= 0) {
            System.out.println("Transacao rejeitada, o valor deve ser positivo.");
            return;
        }

        if (this instanceof CartaoDebito) {
            //todo apenas receber pin se for cartao
            if (!((CartaoDebito) this).verificarCodigo(pin)) {
                System.out.println("Transacao rejeitada, o pin introduzido esta errado");
                return;
            }

            if (this instanceof CartaoCredito) {
                if (((CartaoCredito) this).verificarLimite(valor)) {
                    System.out.println("Transacao rejeitada, limite ultrapassado");
                    return;
                }
            } else {
                if (valor > ((CartaoDebito) this).conta.getSaldo()) {
                    System.out.println("Transacao rejeitada, saldo insuficiente");
                    return;
                }
            }

            ((CartaoDebito) this).continuarPagamento(valor, data, hora, estabelecimento);
        }

        if (this instanceof Numerario) {
            if (((Numerario) this).getValorEntregue() < valor) {
                System.out.println("Transacao rejeitada, valor entregue insuficiente");
                return;
            }

            ((Numerario) this).continuarPagamento(valor, data, hora, estabelecimento);
        }

        System.out.println("Transacao aceite");
    }
}
