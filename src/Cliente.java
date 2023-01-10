import java.sql.SQLOutput;
import java.time.LocalDateTime;
import java.util.Scanner;

import java.util.LinkedList;

public class Cliente extends Identificador{
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
        return "\t" + nivel + "Nome = '" + nome + "',\n\t" + nivel +
                "NIF = " + nif;
    }

    public static String listarClientes(LinkedList<Cliente> clientesList, String nivel) {
        if (clientesList.isEmpty()) {
            return "Sem clientes";
        }

        System.out.println("Clientes [");
        StringBuilder res = new StringBuilder();
        for (Cliente c : clientesList) {
            res.append(c.toString(nivel));

            res.append(",\n\t");
            res.append(nivel);
            res.append(c.getConta().toString(nivel + "\t"));

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

        System.out.println("Saldo inicial da conta: ");
        Long saldo = scanner.nextLong();
        scanner.nextLine();
        ContaBancaria novaConta = new ContaBancaria(novoCliente, saldo);

        System.out.println("\nDeseja adicionar métodos de Pagamento? (S/N)");
        String opcao = scanner.nextLine();
        scanner.nextLine();
        if (opcao == "S") {
            System.out.println("Método pretendido:\n\t1. Debito\n\t2. Crédito\n\t3. MbWay");
            int opcaoMetodo = scanner.nextInt();
            scanner.nextLine();

            long id = novaConta.getMetodosPagamentoList().size() + 1;
            LocalDateTime dataDeHoje = LocalDateTime.now();
            Data dataDeValidade = new Data(dataDeHoje.getDayOfMonth(), dataDeHoje.getMonthValue(), dataDeHoje.getYear() + 4);

            System.out.println("Insira um Pin para este método: ");
            int pin = scanner.nextInt();
            scanner.nextLine();

            if (opcaoMetodo == 1) {
                novaConta.addMetodoPagamento(new CartaoDebito(id, novaConta, dataDeValidade, pin));
            } else {
                System.out.println("Insira o valor extra limite da conta: ");

                long limite = scanner.nextLong();
                scanner.nextLine();
                if (opcaoMetodo == 2) {
                    novaConta.addMetodoPagamento(new CartaoCredito(id, novaConta, dataDeValidade, pin, limite));
                } else {
                    novaConta.addMetodoPagamento(new MBWay(id, novaConta, dataDeValidade, pin, limite));
                }
            }

            return novoCliente;
        }
        return null;
    }
}

