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
}
