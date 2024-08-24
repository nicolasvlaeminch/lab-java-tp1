package exception;

public class MaxProductosExcedidoException extends RuntimeException{
    private final int maxProductos;
    private final int productosPedidos;

    public MaxProductosExcedidoException(int maxProductos, int productosPedidos) {
        this.maxProductos = maxProductos;
        this.productosPedidos = productosPedidos;
    }

    @Override
    public String getMessage() {
        return "La cantidad de productos solicitados excede el maximo permitido, maxima cantidad: " +
                this.maxProductos + ", cantidad solicitada: " + this.productosPedidos;
    }
}
