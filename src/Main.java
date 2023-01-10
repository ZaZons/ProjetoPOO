public class Main {
    public static void main(String[] args) {
        Estabelecimento cafeDoJoao = new Estabelecimento("Cafe do joao", 0);
        Cliente toze = new Cliente("To Ze", 123);
        ContaBancaria contaDoToze = new ContaBancaria(toze, 8);
        CartaoDebito debitoDoToze = new CartaoDebito(1, contaDoToze, new Data(1, 2, 3), 1234);
        CartaoCredito creditoDoToze = new CartaoCredito(2, contaDoToze, new Data(1, 1,1), 12345, 2);

        // O Toze foi ao cafe do joao e decidiu pagar uma rodada ao pessoal em dinheiro, entregou 15 paus para pagar uma rodada de 10
        Numerario rodadaAoPessoal = new Numerario(15);
        rodadaAoPessoal.efetuarPagamento(10, 0, new Data(1, 1, 2), 13, cafeDoJoao);
        System.out.println(cafeDoJoao.getReceitas());

        // Mais tarde o toze tentou fazer o mm, mas como o valor era superior ao dinheiro q tinha em maos, o toze usou o seu cartao de credito
        rodadaAoPessoal.efetuarPagamento(20, 0, new Data(1, 1, 2), 13, cafeDoJoao);

        debitoDoToze.efetuarPagamento(20, 123, new Data(1, 1, 1), 14, cafeDoJoao);
        debitoDoToze.efetuarPagamento(20, 1234, new Data(1, 1, 1), 14, cafeDoJoao);
        // O toze n tinha guito suficiente na conta ent teve de usar o de credito, q errou a primeira
        creditoDoToze.efetuarPagamento(20, 12345, new Data(1, 1, 1), 14, cafeDoJoao);
        // ent o joao fez-lhe o jeitinho e dividiu a conta para metade, que o toze conseguiu pagar com o de credito
        creditoDoToze.efetuarPagamento(10, 12345, new Data(1, 1, 1), 14, cafeDoJoao);
        creditoDoToze.efetuarPagamento(10, 12345, new Data(1, 1, 1), 14, cafeDoJoao);

        // resultado final, o joao ficou com 20 de receitas e o toze com -2 de guito no banco
        System.out.println(cafeDoJoao.toString(""));
        System.out.println(contaDoToze.getSaldo());
    }


}