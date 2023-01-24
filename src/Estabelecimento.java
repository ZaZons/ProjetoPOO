import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.LinkedList;

public class Estabelecimento extends Identificador {
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

    /**
     * Adiciona a transação à lista.
     * Adiciona o valor da transação às receitas.
     */
    public void addTransacao(Transacao transacao) {
        if(transacoesList.contains(transacao)) {
            return;
        }

        transacoesList.add(transacao);
        receitas += transacao.getValor();
    }

    /**
     * Adiciona o cliente à lista
     */
    public void addCliente(Cliente cliente) {
        if (clientesList.contains(cliente)) {
            return;
        }

        for (Cliente c : clientesList) {
            if (c.getNif() == cliente.getNif()) {
                return;
            }
        }

        clientesList.add(cliente);
    }

    /**
     * Devolve uma descrição do estabelecimento.
     * Devolve também uma lista com os nomes dos clientes.
     */
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

    /**
     * Mostra as estatísticas do estabelecimento.
     */
    public void showEstatisticas() {
        System.out.println("Numero de clientes registados: " + clientesList.size());
        System.out.println("Numero de transacoes registadas: " + transacoesList.size());
        System.out.println("Total de receitas do estabelecimento: " + getReceitas());

        if (!clientesList.isEmpty()) {
            System.out.println("\nCliente com mais transacoes registadas {\n" + clienteComMaisTransacoes().toString());
            System.out.println("}\nCliente que despendeu maior valor {\n" + clienteQueMaisGastou().toString());
        }

        if (!transacoesList.isEmpty()) {
            System.out.println("}\nTransacao de maior valor {\n\t" + transacaoDeMaiorValor().toString("\t"));
            System.out.println("}\nTransacao de menor valor {\n\t" + transacaoDeMenorValor().toString("\t"));
            System.out.println("}\nValor medio das transacoes: " + valorMedioDasTransacoes());
            System.out.println("\nPercentagem de transacoes por metodo de pagamento {");
            System.out.println("\tCartao de Debito: " + percentagemMetodos()[0][1] + "% (" + percentagemMetodos()[0][0] + ")");
            System.out.println("\tCartao de Credito: " + percentagemMetodos()[1][1] + "% (" + percentagemMetodos()[1][0] + ")");
            System.out.println("\tMBWay: " + percentagemMetodos()[2][1] + "% (" + percentagemMetodos()[2][0] + ")");
            System.out.println("\tNumerario: " + percentagemMetodos()[3][1] + "% (" + percentagemMetodos()[3][0] + ")");
            System.out.println("}");
        }
    }

    /**
     * Devolve o cliente que realizou mais transações no estabelecimento.
     * Usa um HashMap que usa os clientes como Key e o número de transações que cada um realizou como Value.
     */
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

        int maxTransacoes = 0;
        if (!clientesETransacoes.isEmpty()) {
            for (HashMap.Entry<Cliente, Integer> entry : clientesETransacoes.entrySet()) {
                if (entry.getValue() > maxTransacoes) {
                    maxTransacoes = entry.getValue();
                    clienteComMaisTransacoes = entry.getKey();
                }
            }
        }

        return clienteComMaisTransacoes;
    }

    /**
     * Devolve o cliente que mais gastou dinheiro no estabelecimento.
     * Usa um HashMap que usa os clientes como Key e o valor total de transações que cada um realizou como Value.
     */
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

        double maxGastos = 0;
        if (!clientesEGastos.isEmpty()) {
            for (HashMap.Entry<Cliente, Double> entry : clientesEGastos.entrySet()) {
                if (entry.getValue() > maxGastos) {
                    maxGastos = entry.getValue();
                    clienteComMaisGastos = entry.getKey();
                }
            }
        }

        return clienteComMaisGastos;
    }

    /**
     * Devolve a transação de maior valor da lista.
     */
    private Transacao transacaoDeMaiorValor() {
        Transacao transacaoMaisCara = new Transacao(0, null, null, null);
        for (Transacao t : transacoesList) {
            if (t.getValor() > transacaoMaisCara.getValor()) {
                transacaoMaisCara = t;
            }
        }

        return transacaoMaisCara;
    }

    /**
     * Devolve a transação de menor valor da lista.
     */
    private Transacao transacaoDeMenorValor() {
        Transacao transacaoMaisBarata = new Transacao(Double.MAX_VALUE, null, null, null);
        for (Transacao t : transacoesList) {
            if (t.getValor() < transacaoMaisBarata.getValor()) {
                transacaoMaisBarata = t;
            }
        }

        return transacaoMaisBarata;
    }

    /**
     * Devolve o valor médio das transações da lista.
     */
    private double valorMedioDasTransacoes() {
        if (transacoesList.isEmpty()) {
            return 0;
        }

        double valorTotal = 0;

        for (Transacao t : transacoesList) {
            valorTotal += t.getValor();
        }

        return valorTotal / transacoesList.size();
    }

    /**
     * Devolve uma matriz que armazena o número de vezes que cada método foi usado na primeira linha e a sua percentagem na segunda.
     * Formata todas as percentagens às centésimas.
     */
    private double[][] percentagemMetodos() {
        double[][] metodos = new double[4][2];

        for (Transacao t : transacoesList) {
            if (t.getMetodoPagamento() instanceof Numerario) {
                metodos[3][0]++;
                metodos[3][1] = metodos[3][0] / transacoesList.size() * 100;
            } else if (t.getMetodoPagamento() instanceof MBWay) {
                metodos[2][0]++;
                metodos[2][1] = metodos[2][0] / transacoesList.size() * 100;
            } else if (t.getMetodoPagamento() instanceof CartaoCredito) {
                metodos[1][0]++;
                metodos[1][1] = metodos[1][0] / transacoesList.size() * 100;
            } else if (t.getMetodoPagamento() instanceof CartaoDebito) {
                metodos[0][0]++;
                metodos[0][1] = metodos[0][0] / transacoesList.size() * 100;
            }
        }


        DecimalFormat df = new DecimalFormat("#.##");
        for (int i = 0; i < metodos.length; i++) {
            for (int j = 0; j < metodos[i].length; j++) {
                metodos[i][j] = Double.parseDouble(df.format(metodos[i][j]).replace(',', '.'));
            }
        }

        return metodos;
    }

    /**
     * Regista um cliente.
     */
    public void registarCliente() {
        Cliente.registarCliente(this);
    }

    /**
     * Regista uma transação
     */
    public void registarTransacao() {
        Transacao.registarTransacao(this);
    }
}
