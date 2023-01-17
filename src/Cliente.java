import java.time.LocalDateTime;
import java.util.Scanner;

import java.util.LinkedList;

public class Cliente extends Identificador {
    private final long nif;
    private ContaBancaria conta;

    public Cliente(String nome, long nif) {
        super(nome);
        this.nif = nif;
        this.conta = new ContaBancaria(this, 0);
    }

    public Cliente(String nome, long nif, double saldoInicial) {
        super(nome);
        this.nif = nif;
        this.conta = new ContaBancaria(this, saldoInicial);
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
            return "[\n\t" + nivel + "<Sem clientes>\n" + nivel + "]";
        }

        System.out.println("Clientes [");
        StringBuilder res = new StringBuilder();
        for (Cliente c : clientesList) {
            res.append("\t");
            res.append(nivel);
            res.append("Cliente ");
            res.append(clientesList.indexOf(c) + 1);
            res.append(" {");

            res.append("\n");
            res.append(nivel);
            res.append(c.toString(nivel + "\t"));

            res.append(",\n\t\t");
            res.append(nivel);
            res.append(c.getConta().toString(nivel + "\t\t"));

            res.append("\n\t");
            res.append(nivel);
            res.append("}");

            if (!clientesList.getLast().equals(c)) {
                res.append(",");
            }
            res.append('\n');
        }

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
        long nif = Leitor.lerNif(); //todo
        scanner.nextLine();

        System.out.println("\nSaldo inicial da conta: ");
        long saldo = Leitor.lerLong(0, 9999999);


        Cliente novoCliente = new Cliente(nome, nif, saldo);

        ContaBancaria novaConta = novoCliente.getConta();

        System.out.println("\nDeseja adicionar métodos de Pagamento? (S/N)");
        String opcao = Leitor.lerString(1);
        String opcaoRepeticao = "";
        do {
            if (opcao.equals("S")) {
                System.out.println("\nMétodo pretendido:\n\t1. Debito\n\t2. Crédito\n\t3. MbWay");
                int opcaoMetodo = Leitor.lerInteiro(1,3);

                long id = novaConta.getMetodosPagamentoList().size() + 1;
                LocalDateTime dataDeHoje = LocalDateTime.now();
                Data dataDeValidade = new Data(dataDeHoje.getDayOfMonth(), dataDeHoje.getMonthValue(), dataDeHoje.getYear() + 4);

                System.out.println("\nInsira um Pin para este método: ");
                int pin = Leitor.lerPin();

                if (opcaoMetodo == 1) {
                    novaConta.addMetodoPagamento(new CartaoDebito(id, novaConta, dataDeValidade, pin));
                } else {
                    System.out.println("\nInsira o valor extra limite da conta: ");

                    long limite = Leitor.lerLong(0, 9999);
                    scanner.nextLine();
                    if (opcaoMetodo == 2) {
                        novaConta.addMetodoPagamento(new CartaoCredito(id, novaConta, dataDeValidade, pin, limite));
                    } else {
                        novaConta.addMetodoPagamento(new MBWay(id, novaConta, dataDeValidade, pin, limite));
                    }
                }
                System.out.println("\nDeseja adicinar mais métoodos de pagamentos? (S/N)");
                opcaoRepeticao = Leitor.lerString(1);
            }

        }while (opcaoRepeticao.equals("S"));

        return novoCliente;
    }
}
