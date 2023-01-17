import java.io.Serializable;

public interface MetodoPagamento extends Serializable {
    void efetuarPagamento(double valor, Estabelecimento estabelecimento);
//    public void efetuarPagamento(double valor, Estabelecimento estabelecimento) {
////        if (valor <= 0) {
////            System.out.println("Transacao rejeitada, o valor deve ser positivo.");
////            return;
////        }
//        //todo interface
//        if (this instanceof CartaoDebito) {
//            System.out.println("Introduza o pin: ");
//            int pin = Leitor.lerPin();
//            if (!((CartaoDebito) this).verificarCodigo(pin)) {
//                System.out.println("Transacao rejeitada, o pin introduzido esta errado");
//                return;
//            }
//
//            if (this instanceof CartaoCredito) {
//                if (((CartaoCredito) this).verificarLimite(valor)) {
//                    System.out.println("Transacao rejeitada, limite ultrapassado");
//                    return;
//                }
//            } else {
//                if (valor > ((CartaoDebito) this).conta.getSaldo()) {
//                    System.out.println("Transacao rejeitada, saldo insuficiente");
//                    return;
//                }
//            }
//
//            ((CartaoDebito) this).continuarPagamento(valor, new Data(), estabelecimento);
//        }
//
//        if (this instanceof Numerario) {
//            if (((Numerario) this).getValorEntregue() < valor) {
//                System.out.println("Transacao rejeitada, valor entregue insuficiente");
//                return;
//            }
//
//            ((Numerario) this).continuarPagamento(valor, new Data(), estabelecimento);
//        }
//
//        System.out.println("Transacao aceite");
//    }
}
