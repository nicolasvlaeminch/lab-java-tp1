package exception;

public class SaldoInsuficienteException extends RuntimeException{
    private final double saldoCaja;
    private final double saldoCompra;

    public SaldoInsuficienteException(double saldoCaja, double saldoCompra) {
        this.saldoCaja = saldoCaja;
        this.saldoCompra = saldoCompra;
    }

    @Override
    public String getMessage() {
        return "El producto no podrá ser agregado a la tienda por saldo insuficiente en la caja, saldo en caja disponible: " +
                this.saldoCaja + " precio de la compra: " + this.saldoCompra;
    }
}
