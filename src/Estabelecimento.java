import java.util.LinkedList;

public class Estabelecimento {
    private String nome;
    private float receitas;
    private LinkedList<Transacao> transacoesList;
    private LinkedList<Cliente> clientesList;

    public Estabelecimento(String nome, float receitas, LinkedList<Transacao> transacoesList, LinkedList<Cliente> clientesList) {
        this.nome = nome;
        this.receitas = receitas;
        this.transacoesList = transacoesList;
        this.clientesList = clientesList;
    }

    public String getNome() {
        return nome;
    }

    public float getReceitas() {
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
