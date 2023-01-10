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
}
