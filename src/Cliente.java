import java.time.LocalDateTime;
import java.util.Scanner;

import java.util.LinkedList;

public class Cliente extends Identificador {
    private final long nif;
    private ContaBancaria conta;

    public Cliente(String nome, long nif) {
        super(nome);
        this.nif = nif;
    }

    public long getNif() {
        return nif;
    }

    public ContaBancaria getConta() {
        return conta;
    }

    public void setConta(ContaBancaria conta) {
        this.conta = conta;
    }

    public String toString(String nivel) {
        return "Cliente = {" + "\n\t" + nivel +
                "Nome = '" + nome + "'\n\t" + nivel +
                "NIF = " + nif + "\n" + nivel +
                "}";
    }

    public static String listarClientes(LinkedList<Cliente> clientesList, String nivel) {
        if (clientesList.isEmpty()) {
            return "Sem clientes";
        }

        StringBuilder res = new StringBuilder();
        res.append("[");

        for (Cliente c : clientesList) {
            res.append("\n\t");
            res.append(nivel);
            res.append("'");
            res.append(c.getNome());
            res.append("'");

            if (!clientesList.getLast().equals(c)) {
                res.append(',');
            }
        }

        res.append("\n");
        res.append(nivel);
        res.append("]");

        return res.toString();
    }


    public static Cliente registarCliente() {
        System.out.println("\nInsira o nome do Cliente: ");
        Scanner scanner = new Scanner(System.in);
        String nome = scanner.nextLine();
        scanner.nextLine();
        System.out.println("\nInsira o NIF: ");
        long nif = scanner.nextLong();
        scanner.nextLine();

        Cliente novoCliente = new Cliente(nome, nif);

        System.out.println("\nSaldo inicial da conta: ");
        long saldo = scanner.nextLong();
        scanner.nextLine();
        ContaBancaria novaConta = new ContaBancaria(novoCliente, saldo);

        System.out.println("\nDeseja adicionar métodos de Pagamento? (S/N)");
        String opcao = scanner.nextLine();
        scanner.nextLine();
        String opcaoRepeticao = "";
        int validacaoPin;
        do {
            if (opcao.equals("S")) {
                System.out.println("\nMétodo pretendido:\n\t1. Debito\n\t2. Crédito\n\t3. MbWay");
                int opcaoMetodo = scanner.nextInt();
                scanner.nextLine();

                long id = novaConta.getMetodosPagamentoList().size() + 1;
                LocalDateTime dataDeHoje = LocalDateTime.now();
                Data dataDeValidade = new Data(dataDeHoje.getDayOfMonth(), dataDeHoje.getMonthValue(), dataDeHoje.getYear() + 4);
                int pin;
                do {
                    System.out.println("\nInsira um Pin para este método: ");
                    pin = scanner.nextInt();
                    scanner.nextLine();
                    validacaoPin = String.valueOf(pin).length();
                    if (validacaoPin != 4) {
                        System.out.println("\nTamanho de pin inválido! 4444 -My audience awaits-");
                    }
                } while (validacaoPin != 4);

                if (opcaoMetodo == 1) {
                    novaConta.addMetodoPagamento(new CartaoDebito(id, novaConta, dataDeValidade, pin));
                } else {
                    System.out.println("\nInsira o valor extra limite da conta: ");

                    long limite = scanner.nextLong();
                    scanner.nextLine();
                    if (opcaoMetodo == 2) {
                        novaConta.addMetodoPagamento(new CartaoCredito(id, novaConta, dataDeValidade, pin, limite));
                    } else {
                        novaConta.addMetodoPagamento(new MBWay(id, novaConta, dataDeValidade, pin, limite));
                    }
                }
                System.out.println("\nDeseja adicinar mais métoodos de pagamentos? (S/N)");
                opcaoRepeticao = scanner.nextLine();
                scanner.nextLine();
            }

        }while (opcaoRepeticao.equals("S"));
        return novoCliente;
    }
}
