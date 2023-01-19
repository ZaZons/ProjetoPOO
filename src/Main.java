public class Main {
    public static void main(String[] args) {
        Estabelecimento cafeDoJoao = testValues();
//        Estabelecimento cafeDoJoao = new Estabelecimento("Cafe do Jonas", 0);
        int opcao;

        // TODO: 17/01/2023 grafico no lucidchart
        //todo comentar funcoes
        do {
            System.out.println(cafeDoJoao.getNome());
            opcao = menu();

            System.out.print("\n");
            switch (opcao) {
                case 1:
                    System.out.println(Transacao.listarTransacoes(cafeDoJoao.getTransacoesList(), ""));
                    break;
                case 2:
                    System.out.println(Cliente.listarClientes(cafeDoJoao.getClientesList(), ""));
                    break;
                case 3:
                    cafeDoJoao.registarCliente();
                    break;
                case 4:
                    cafeDoJoao.registarTransacao();
                    break;
                case 5:
                    cafeDoJoao.showEstatisticas();
                    break;
                case 6:
                    Ficheiros.gravar(cafeDoJoao);
                    break;
                case 7:
                    cafeDoJoao = Ficheiros.ler();
                    break;
                case 0:
                    System.out.println("Adeus!!!");
                    break;
            }
            System.out.print("\n");
        } while (opcao != 0);
    }

    /**
     * Cria o menu.
     * Recebe e devolve a opção escolhida.
     */
    public static int menu() {
        System.out.println("1. Listar transacoes");
        System.out.println("2. Listar clientes");
        System.out.println("3. Registar novo cliente");
        System.out.println("4. Registar nova transacao");
        System.out.println("5. Mostrar estatisticas");
        System.out.println("6. Gravar informação");
        System.out.println("7. Carregar informação");
        System.out.println("0. Sair");

        return Leitor.lerInteiro(0, 7);
    }

    /**
     * Valores de teste para a aplicação.
     * Devolve o estabelecimento atualizado.
     */
    public static Estabelecimento testValues() {
        // Estabelecimento e valores
        Estabelecimento e = new Estabelecimento("Cafe do Joao", 0);
        Cliente clienteJoao = new Cliente("Joao Miguel", 423567543);
        Cliente clienteSantiago = new Cliente("Santiago Santos", 100583906, 50);
        Cliente clienteJoana = new Cliente("Joana Mira", 473625364, 100);
        Cliente clienteMatilde = new Cliente("Matilde Agostinho", 948374615, -3);

        e.addCliente(clienteJoao);
        e.addCliente(clienteSantiago);
        e.addCliente(clienteJoana);
        e.addCliente(clienteMatilde);
        e.addCliente(clienteJoao);

        // Metodos e transações
        CartaoDebito cdJoao = new CartaoDebito(1, clienteJoao.getConta(), 1234);
        CartaoCredito ccJoao = new CartaoCredito(2, clienteJoao.getConta(), 1234, 15);
        MBWay mbJoana = new MBWay(1, clienteJoana.getConta(), 1234, 70);
        CartaoCredito ccSantiago = new CartaoCredito(1, clienteSantiago.getConta(), 1234, 7);

        Transacao t1 = new Transacao(15.5, cdJoao, clienteJoao, e);
        Transacao t2 = new Transacao(20, ccJoao, clienteJoao, e);
        Transacao t3 = new Transacao(3, mbJoana, clienteJoana, e);

        return e;
    }
}