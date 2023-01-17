import java.util.LinkedList;

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
                "Estabelecimento = " + estabelecimento.getNome() + "\n" + nivel +
                "}";
    }

    public static String listarTransacoes(LinkedList<Transacao> transacoesList, String nivel) {
        if (transacoesList.isEmpty()) {
            return "[\n\t" + nivel + "<Sem transacoes>\n" + nivel + "]";
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
    public static void registarTransacao(Estabelecimento estabelecimento) {
        System.out.println("Clientes [");
        for (Cliente c : estabelecimento.getClientesList()) {
            System.out.println("\t" + c.getNome() + " (" + c.getNif() + ")");
        }
        System.out.println("]");

//        boolean validacaoCliente = false;
        Cliente clienteTransacao = null;
        do {
            System.out.println("\nInsira o NIF de um cliente da lista apresentada: ");
            long nifCliente = Leitor.lerNif();

            for (Cliente cliente1 : estabelecimento.getClientesList()) {
                if (cliente1.getNif() == nifCliente) {
                    clienteTransacao = cliente1;
//                    validacaoCliente = true;
                }
            }

            if (clienteTransacao == null) {
                System.out.println("\nCliente inválido!");
            }
//        }while(!validacaoCliente);
        } while (clienteTransacao == null);

        double valorTransacao;
        do {
            System.out.println("\nValor da transação: ");
            valorTransacao = Leitor.lerDouble(0.01, 9999);

            if(valorTransacao <= 0) {
                System.out.println("Valor inválido [Inferior ou igual a 0]");
            }
        } while (valorTransacao <= 0);

        MetodoPagamento metodoSelecionado = null;
        LinkedList<MetodoPagamento> metodosCliente = clienteTransacao.getConta().getMetodosPagamentoList();
        do {
            System.out.println("\n" + clienteTransacao.getConta().listarMetodosPagamento(""));
            System.out.println("ou \nNumerario (0)");
            System.out.println("\nSelecione um dos metodos de pagamento apresentados: ");

            int tamanho = clienteTransacao.getConta().isTemNumerario() ? metodosCliente.size() - 1 : metodosCliente.size();
            int idSelecionado = Leitor.lerInteiro(0, tamanho);
            if (idSelecionado == 0) {
                Numerario numerario = null;
                for (MetodoPagamento m : metodosCliente) {
                    if (m instanceof Numerario) {
                        numerario = (Numerario) m;
                    }
                }

                if (numerario == null) {
                    System.out.println("\nIntroduza o valor entregue: ");
                    double valorEntregue = Leitor.lerDouble(valorTransacao, 99999);
                    metodoSelecionado = new Numerario(valorEntregue);
                    clienteTransacao.getConta().addMetodoPagamento(metodoSelecionado);
                    clienteTransacao.getConta().setTemNumerario(true);
                } else {
                    metodoSelecionado = numerario;
                    System.out.println("\nTem " + numerario.getValorEntregue() + " restantes.");
                    System.out.println("Introduza o valor que pretende adicionar: ");
                    double valorAAdicionar = Leitor.lerDouble(valorTransacao - numerario.getValorEntregue(), 99999);
                    numerario.addValor(valorAAdicionar);
                }
            } else {
                for (MetodoPagamento m : metodosCliente) {
                    if (m instanceof CartaoDebito) {
                        if (((CartaoDebito) m).getId() == idSelecionado) {
                            metodoSelecionado = m;
                        }
                    }
                }
            }
        } while (metodoSelecionado == null);

        metodoSelecionado.efetuarPagamento(valorTransacao, estabelecimento);
    }

}
