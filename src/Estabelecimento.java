import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;

public class Estabelecimento extends Identificador{
    private double receitas;
    private final LinkedList<Transacao> transacoesList;
    private final LinkedList<Cliente> clientesList;

    public Estabelecimento(String nome, float receitas) {
        super(nome);
        this.receitas = receitas;
        this.transacoesList = new LinkedList<>();
        this.clientesList = new LinkedList<>();
    }

    public double getReceitas() {
        return receitas;
    }

    public LinkedList<Transacao> getTransacoesList() {
        return transacoesList;
    }

    public LinkedList<Cliente> getClientesList() {
        return clientesList;
    }

    public void addTransacao(Transacao transacao) {
        if(transacoesList.contains(transacao)) {
            return;
        }

        transacoesList.add(transacao);
        receitas += transacao.getValor();
    }

    public void addCliente(Cliente cliente) {
        if(clientesList.contains(cliente)) {
            return;
        }

        clientesList.add(cliente);
    }

    public String toString(String nivel) {
        StringBuilder clientesStr = new StringBuilder();

        for (Cliente c : clientesList) {
            clientesStr.append("\n\t\t");
            clientesStr.append(nivel);
            clientesStr.append("'");
            clientesStr.append(c.getNome());
            clientesStr.append("'");
        }

        return "Estabelecimento {" + "\n\t" + nivel +
                "Nome = '" + nome + "',\n\t" + nivel +
                "Valor das receitas = " + receitas + ",\n\t" + nivel +
                "Transacoes = " + Transacao.listarTransacoes(transacoesList, nivel + "\t") + ",\n\t" + nivel +
                "Clientes [" + clientesStr + "\n\t" + nivel + "]\n" + nivel +
                "}";
    }

    public void estatisticas() {
        System.out.println("Numero de clientes registados: " + clientesList.size());
        System.out.println("Numero de transacoes registadas: " + transacoesList.size());
        System.out.println("\n");
        System.out.println("Cliente com mais transacoes registadas: " + clienteComMaisTransacoes());
        System.out.println("Cliente que despendeu maior valor: " + clienteQueMaisGastou());
        System.out.println("\n");
        System.out.println("Transacao de maior valor: " + transacaoDeMaiorValor());
        System.out.println("Transacao de menor valor: " + transacaoDeMenorValor());
        System.out.println("Valor medio das transacoes: " + valorMedioDasTransacoes());
        System.out.println("\n");
        System.out.println("Total de receitas do estabelecimento: " + receitas);
        System.out.println("\n");
        System.out.println("Percentagem de transacoes por metodo de pagamento {");
        System.out.println("\tCartao de Debito: " + percentagemMetodos()[0] / transacoesList.size() * 100 + "% (" + percentagemMetodos()[0] + ")");
        System.out.println("\tCartao de Credito: " + percentagemMetodos()[1] / transacoesList.size() * 100 + "% (" + percentagemMetodos()[1] + ")");
        System.out.println("\tMBWay: " + percentagemMetodos()[2] / transacoesList.size() * 100 + "% (" + percentagemMetodos()[2] + ")");
        System.out.println("\tNumerario: " + percentagemMetodos()[3] / transacoesList.size() * 100 + "% (" + percentagemMetodos()[3] + ")");
        System.out.println("}");
    }

    private Cliente clienteComMaisTransacoes() {
        Cliente clienteComMaisTransacoes = null;
        HashMap<Cliente, Integer> clientesETransacoes = new HashMap<>();

        for (Transacao t : transacoesList) {
            if (!clientesETransacoes.containsKey(t.getCliente())) {
                clientesETransacoes.put(t.getCliente(), 1);
            } else {
                clientesETransacoes.put(t.getCliente(), clientesETransacoes.get(t.getCliente()) + 1);
            }
        }

        if (!clientesETransacoes.isEmpty()) {
            int maxTransacoes = 0;
            for (Cliente c : clientesList) {
                if (clientesETransacoes.get(c) > maxTransacoes) {
                    clienteComMaisTransacoes = c;
                }
            }
        }

        return clienteComMaisTransacoes;
    }

    private Cliente clienteQueMaisGastou() {
        Cliente clienteComMaisGastos = null;
        HashMap<Cliente, Double> clientesEGastos = new HashMap<>();

        for (Transacao t : transacoesList) {
            if (!clientesEGastos.containsKey(t.getCliente())) {
                clientesEGastos.put(t.getCliente(), t.getValor());
            } else {
                clientesEGastos.put(t.getCliente(), clientesEGastos.get(t.getCliente()) + t.getValor());
            }
        }

        if (!clientesEGastos.isEmpty()) {
            int maxGastos = 0;
            for (Cliente c : clientesList) {
                if (clientesEGastos.get(c) > maxGastos) {
                    clienteComMaisGastos = c;
                }
            }
        }

        return clienteComMaisGastos;
    }

    private Transacao transacaoDeMaiorValor() {
        Transacao transacaoMaisCara = new Transacao(null, 0, null, null, null);
        for (Transacao t : transacoesList) {
            if (t.getValor() > transacaoMaisCara.getValor()) {
                transacaoMaisCara = t;
            }
        }

        return transacaoMaisCara;
    }

    private Transacao transacaoDeMenorValor() {
        Transacao transacaoMaisBarata = new Transacao(null, Double.MAX_VALUE, null, null, null);
        for (Transacao t : transacoesList) {
            if (t.getValor() < transacaoMaisBarata.getValor()) {
                transacaoMaisBarata = t;
            }
        }

        return transacaoMaisBarata;
    }

    private double valorMedioDasTransacoes() {
        double valorTotal = 0;

        for (Transacao t : transacoesList) {
            valorTotal += t.getValor();
        }

        return valorTotal / transacoesList.size();
    }

    private double[] percentagemMetodos() {
        double[] metodos = new double[4];

        // todo implementar isto:
        // https://stackoverflow.com/questions/5579309/is-it-possible-to-use-the-instanceof-operator-in-a-switch-statement
        for (Transacao t : transacoesList) {
            if (t.getMetodoPagamento() instanceof Numerario) {
                metodos[3]++;
            } else if (t.getMetodoPagamento() instanceof MBWay) {
                metodos[2]++;
            } else if (t.getMetodoPagamento() instanceof CartaoCredito) {
                metodos[1]++;
            } else if (t.getMetodoPagamento() instanceof CartaoDebito) {
                metodos[0]++;
            }
        }

        return metodos;
    }
}
