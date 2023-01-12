import java.util.LinkedList;
import java.util.Scanner;

public class Transacao {
    private final Data data;
    private final double valor;
    private final MetodoPagamento metodoPagamento;
    private final Cliente cliente;
    private final Estabelecimento estabelecimento;

    public Transacao(Data data, double valor, MetodoPagamento metodoPagamento, Cliente cliente, Estabelecimento estabelecimento) {
        this.data = data;
        this.valor = valor;
        this.metodoPagamento = metodoPagamento;
        this.cliente = cliente;
        this.estabelecimento = estabelecimento;
    }

    public Data getData() {
        return data;
    }

    public double getValor() {
        return valor;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public MetodoPagamento getMetodoPagamento() {
        return metodoPagamento;
    }

    public Estabelecimento getEstabelecimento() {
        return estabelecimento;
    }

    public String toString(String nivel) {
        String metodoPagamentoStr = metodoPagamento.getClass().toString().replace("class ", "");

        return "Transacao " + data + " {\n\t" + nivel +
                "Cliente {" + "\n" + cliente.toString(nivel + "\t") + "\n\t" + nivel + "},\n\t" + nivel +
                "Data = " + data + ",\n\t" + nivel +
                "Valor = " + valor + ",\n\t" + nivel +
                "Metodo de pagamento = " + metodoPagamentoStr + ",\n\t" + nivel +
                "Estabelecimento = '" + estabelecimento.getNome() + "'\n" + nivel +
                "}";
    }

    public static String listarTransacoes(LinkedList<Transacao> transacoesList, String nivel) {
        if (transacoesList.isEmpty()) {
            return "Sem transacoes";
        }

        StringBuilder res = new StringBuilder();
        res.append("[");

        for (Transacao t : transacoesList) {
            res.append("\n\t");
            res.append(nivel);
            res.append(t.toString(nivel + "\t"));

            if (!transacoesList.getLast().equals(t)) {
                res.append(',');
            }
        }

        res.append("\n");
        res.append(nivel);
        res.append("]");

        return res.toString();
    }
    public static Transacao registarTransacao(LinkedList<Cliente> clientesList, Estabelecimento estabelecimento) {
        System.out.println("Clientes [");
        for (Cliente c : clientesList) {
            System.out.println("\t" + c.getNome());
        }
        System.out.println("]");
        Scanner scanner = new Scanner(System.in);

        boolean validacaoCliente = false;
        Cliente clienteTransacao = null;
        do {
            System.out.println("\nInsira o NIF de um cliente da lista apresentada: ");
            long nifCliente = scanner.nextLong();
            scanner.nextLine();

            for (Cliente cliente1 : clientesList) {

                if (cliente1.getNif() == nifCliente) {
                    clienteTransacao = cliente1;
                    validacaoCliente = true;
                }
            }
                if (clienteTransacao == null) {
                    System.out.println("\nCliente inválido!");
                }
        }while(validacaoCliente != true);

        MetodoPagamento metodoSelecionado = null;
        LinkedList<MetodoPagamento> metodosCliente = clienteTransacao.getConta().getMetodosPagamentoList();

        do {
            System.out.println("\n" + clienteTransacao.getConta().listarMetodosPagamento(metodosCliente, ""));
            System.out.println("\nSelecione um dos metodos de pagamento apresentados: ");

            long idSelecionado = scanner.nextLong();
            scanner.nextLine();


            for (MetodoPagamento m : metodosCliente) {
                if (m instanceof CartaoDebito) {
                    if (((CartaoDebito) m).getId() == idSelecionado) {
                        metodoSelecionado = m;
                    }
                }
            }
            if (metodoSelecionado == null) {
                System.out.println("\nMétodo inválido!");
            }
        }while(metodoSelecionado == null);

        double valorTransacao = 0;

        do {
            System.out.println("\nValor da transação: ");
            valorTransacao = scanner.nextDouble();
            scanner.nextLine();

            if(valorTransacao <= 0) {
                System.out.println("Valor inválido [Inferior ou igual a 0]");
            }
        }while(valorTransacao <= 0);

        Transacao novaTransacao = new Transacao(new Data(), valorTransacao, metodoSelecionado, clienteTransacao, estabelecimento);

        System.out.println("\n\n\t\tTransação efetuada!");

        return novaTransacao;
    }

}
