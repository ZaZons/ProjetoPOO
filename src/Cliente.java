import java.util.LinkedList;

public class Cliente extends Identificador {
    private final long nif;
    private ContaBancaria conta;

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

    @Override
    public String toString() {
        return toString("");
    }

    /**
     * Devolve uma String contendo o nome e o NIF do cliente.
     */
    public String toString(String nivel) {
        return "\t" + nivel + "Nome = " + nome + ",\n\t" + nivel +
                "NIF = " + nif;
    }

    /**
     * Regista novos clientes,
     * criando também uma conta bancária com saldo inicial e métodos de pagamento, e respetivos dados.
     * Adiciona-os ao estabelecimento.
     */
    public static void registarCliente(Estabelecimento estabelecimento) {
        System.out.println("Insira o nome do Cliente: ");
        String nome = Leitor.lerString(-1);

        System.out.println("\nInsira o NIF: ");
        long nif = Leitor.lerNif();

        System.out.println("\nSaldo inicial da conta: ");
        double saldo = Leitor.lerDouble(0, 9999999);

        Cliente novoCliente = new Cliente(nome, nif, saldo);
        ContaBancaria novaConta = novoCliente.getConta();

        System.out.println("\nDeseja adicionar métodos de Pagamento? (S/N)");
        String opcao = Leitor.lerString(1);
        do {
            if (opcao.equalsIgnoreCase("S")) {
                System.out.println("\nMétodo pretendido:\n\t1. Debito\n\t2. Crédito\n\t3. MbWay");
                int opcaoMetodo = Leitor.lerInteiro(1,3);

                long id = novaConta.getMetodosPagamentoList().size() + 1;

                System.out.println("\nInsira um Pin para este método: ");
                int pin = Leitor.lerPin();

                if (opcaoMetodo == 1) {
                    novaConta.addMetodoPagamento(new CartaoDebito(id, novaConta, pin));
                } else {
                    System.out.println("\nInsira o valor extra limite da conta: ");

                    long limite = Leitor.lerLong(0, 9999);
                    if (opcaoMetodo == 2) {
                        novaConta.addMetodoPagamento(new CartaoCredito(id, novaConta, pin, limite));
                    } else {
                        novaConta.addMetodoPagamento(new MBWay(id, novaConta, pin, limite));
                    }
                }
                System.out.println("\nDeseja adicinar mais métodos de pagamentos? (S/N)");
                opcao = Leitor.lerString(1);
            } else if (!opcao.equalsIgnoreCase("N")) {
                System.out.println("\nOpcao invalida, introduza uma opcao valida: ");
                opcao = Leitor.lerString(1);
            }
        } while (!opcao.equalsIgnoreCase("N"));

        estabelecimento.addCliente(novoCliente);
    }
}
