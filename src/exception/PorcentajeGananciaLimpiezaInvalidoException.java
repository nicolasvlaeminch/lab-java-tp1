package exception;

public class PorcentajeGananciaLimpiezaInvalidoException extends RuntimeException{
    private final double porcentajeMaximo;
    private final double porcentajeMinimo;
    private final double porcentajeExcedido;

    public PorcentajeGananciaLimpiezaInvalidoException(double porcentajeMaximo, double porcentajeMinimo, double porcentajeExcedido) {
        this.porcentajeMaximo = porcentajeMaximo;
        this.porcentajeMinimo = porcentajeMinimo;
        this.porcentajeExcedido = porcentajeExcedido;
    }

    @Override
    public String getMessage() {
        return "El porcentaje de ganancia para los productos de limpieza que no sean de tipo COCINA o MULTIUSO no puede ser menor al " +
                porcentajeMinimo + "% ni superar el " + porcentajeMaximo + "%. Ingresado: " + porcentajeExcedido + "%.";
    }
}
