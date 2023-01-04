import java.util.LinkedList;

public class Estabelecimento extends Identificador{
    private double receitas;
    private LinkedList<Transacao> transacoesList;
    private LinkedList<Cliente> clientesList;

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
    }

    public void addCliente(Cliente cliente) {
        if(clientesList.contains(cliente)) {
            return;
        }

        clientesList.add(cliente);
    }


}
