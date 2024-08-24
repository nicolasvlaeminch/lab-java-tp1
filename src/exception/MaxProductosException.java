package exception;

public class MaxProductosException extends RuntimeException{
    private final int maxProductos;
    private final int productosPedidos;

    public MaxProductosException(int maxProductos, int productosPedidos) {
        this.maxProductos = maxProductos;
        this.productosPedidos = productosPedidos;
    }

    @Override
    public String getMessage() {
        return "La cantidad de productos solicitados excede el maximo permitido, maxima cantidad: " +
                this.maxProductos + ", cantidad solicitada: " + this.productosPedidos;
    }
}
