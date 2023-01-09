public class CartaoCredito extends CartaoDebito{
    protected double limite;

    public double getLimite() {
        return limite;
    }

    public CartaoCredito(long id, ContaBancaria conta, Data dataValidade, int pin, double limite) {
        super(id, conta, dataValidade, pin);
        this.limite = limite;
    }
    @Override
    public String continuarPagamento() {
        System.out.println("shablau");
        return "";
    }
}
