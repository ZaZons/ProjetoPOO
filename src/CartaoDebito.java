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
     * Implementa a função da super classe MetodoPagamento.
     * Efetua um pagamento, depois de:
     *      Verificar se a conta tem saldo suficiente;
     *      Fazer as validações comuns.
     * Continua o pagamento.
     */
    @Override
    public void efetuarPagamento(double valor, Estabelecimento estabelecimento, Cliente cliente) {
        if (valor > conta.getSaldo()) {
            System.out.println("Transacao rejeitada, saldo insuficiente");
            return;
        }

        if (!validacoesComuns()) {
            return;
        }

        continuarPagamento(valor, estabelecimento);
    }

    /**
     * Verifica as validações comuns a todos os cartões:
     *      Se o código introduzido está correto;
     *      Se o cartão é válido.
     */
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
     * Cria um registo de transação, recebendo como o valor e o estabelecimento a receber.
     * Adiciona a nova transação à lista de transações do cliente.
     */
    protected void continuarPagamento(double valor, Estabelecimento estabelecimento) {
        Transacao transacao = new Transacao(valor, this, conta.getCliente(), estabelecimento);
        conta.addTransacao(transacao);
    }

    /**
     * Devolve a descrição do objeto.
     * Recebe o nivel em que está, para termos estéticos.
     */
    public String toString(String nivel) {
        return "CartaoDebito {" + "\n\t" + nivel +
                "Id = " + id + "\n\t" + nivel +
                "Data de validade = " + dataValidade + "\n" + nivel +
                '}';
    }

    /**
     * Verifica se o PIN introduzido está correto.
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
