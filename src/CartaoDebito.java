public class CartaoDebito implements MetodoPagamento {
    protected long id;
    protected ContaBancaria conta;
    protected Data dataValidade;
    protected int pin;

    public CartaoDebito(long id, ContaBancaria conta, int pin) {
        this.id = id;
        this.conta = conta;
        this.dataValidade = new Data();
        this.pin = pin;
        conta.addMetodoPagamento(this);
    }

    public long getId() {
        return id;
    }

    /**
     * Efetua uma transação, recebendo como parâmetros o valor e o estabelecimento a receber.
     * Não permite a sua execução caso o cliente não tenha saldo sufuciente ou erre o PIN.
     * Caso efetuada com sucesso, procede para a funcao continuarPagamento().
     */
    @Override
    public void efetuarPagamento(double valor, Estabelecimento estabelecimento) {
        if (valor > conta.getSaldo()) {
            System.out.println("Transacao rejeitada, saldo insuficiente");
            return;
        }

        if (!validacoesComuns()) {
            return;
        }

        continuarPagamento(valor, estabelecimento);
    }

    protected boolean validacoesComuns() {
        if (!verificarCodigo()) {
            System.out.println("Codigo incorreto!");
            return false;
        }

        String metodoExpirado = "Metodo de pagamento expirado!";
        if (this.dataValidade.getAno() > new Data().getAno()) {
            System.out.println(metodoExpirado);
            return false;
        } else if (this.dataValidade.getAno() == new Data().getAno()) {
            if (this.dataValidade.getMes() > new Data().getMes()) {
                System.out.println(metodoExpirado);
                return false;
            } else if (this.dataValidade.getMes() == new Data().getMes()) {
                if (this.dataValidade.getDia() > new Data().getDia()) {
                    System.out.println(metodoExpirado);
                    return false;
                }
            }
        }

        return true;
    }

    /**
     *Cria um registo de transação, recebendo como parâmentros o valor e o estabelecimento a receber.
     * Adiciona a nova transação à lista de transações do cliente.
     */
    protected void continuarPagamento(double valor, Estabelecimento estabelecimento) {
        Transacao transacao = new Transacao(valor, this, conta.getCliente(), estabelecimento);
        conta.addTransacao(transacao);
    }

    /**
     * todo
     */
    public String toString(String nivel) {
        return "CartaoDebito {" + "\n\t" + nivel +
                "Id = " + id + "\n\t" + nivel +
                "Data de validade = " + dataValidade + "\n" + nivel +
                '}';
    }

    /**
     * Verifica se o Pin do método de pagamento é verdadeiro.
     * Devolve boleano conforme o resultado da operação.
     */
    protected boolean verificarCodigo() {
        System.out.println("Introduza o pin: ");
        int pin = Leitor.lerPin();
        if (pin != this.pin) {
            System.out.println("Transacao rejeitada, o pin introduzido está errado");
            return false;
        }

        return true;
    }
}
