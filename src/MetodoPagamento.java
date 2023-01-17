import java.io.Serializable;

public interface MetodoPagamento extends Serializable {
    /**
     * Função que permite realizar as transações, efetuando as verificações necessárias,
     * tendo em conta a classe que a implementa.
     */
    void efetuarPagamento(double valor, Estabelecimento estabelecimento);
}
