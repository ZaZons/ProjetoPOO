public class Main {
    public static void main(String[] args) {
        Estabelecimento cafeDoJoao = testValues();
        int opcao;

        do {
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
                    Cliente cliente = Cliente.registarCliente(); //feito pleo stor, adicionado var por nao estar a receber valor returned todo
                    break;
                case 4:
                    cafeDoJoao.registarTransacao();
                    break;
                case 5:
                    cafeDoJoao.showEstatisticas();
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
        System.out.println("8. Sair");

        return Leitor.lerInteiro(1, 8);
    }

    public static Estabelecimento testValues() {
        Estabelecimento e = new Estabelecimento("Cafe do Joao", 0);
        Cliente clienteJoao = new Cliente("Joao Miguel", 1094010395);
        Cliente clienteSantiago = new Cliente("Santiago Santos", 1005839065, 50);
        Cliente clienteJoana = new Cliente("Joana Mira", 1027747976, 100);
        Cliente clienteMatilde = new Cliente("Matilde Agostinho", 1094088183, -3);

        e.addCliente(clienteJoao);
        e.addCliente(clienteSantiago);
        e.addCliente(clienteJoana);
        e.addCliente(clienteMatilde);
        e.addCliente(clienteJoao);

        return e;
    }
}