package exception;

public class DescuentoExcedidoException extends RuntimeException{
    private final double porcentajeMaximo;
    private final double porcentajeExcedido;
    private final String tipoProducto;

    public DescuentoExcedidoException(double porcentajeMaximo, double porcentajeExcedido, String tipoProducto) {
        this.porcentajeMaximo = porcentajeMaximo;
        this.porcentajeExcedido = porcentajeExcedido;
        this.tipoProducto = tipoProducto;
    }

    @Override
    public String getMessage() {
        return "El porcentaje de descuento para " + this.tipoProducto + " no puede superar el " +
                this.porcentajeMaximo + "%, ingresado: " + porcentajeExcedido + "%.";
    }
}
