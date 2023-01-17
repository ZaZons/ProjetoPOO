public class Main {
    public static void main(String[] args) {
        Estabelecimento cafeDoJoao = testValues();
//        Estabelecimento cafeDoJoao = new Estabelecimento("Cafe do Jonas", 0);
        int opcao;

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

    public static Estabelecimento testValues() {
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

        return e;
    }
}