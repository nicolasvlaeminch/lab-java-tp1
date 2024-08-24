package exception;

public class PorcentajeGananciaComestiblesInvalidoException extends RuntimeException{
    private final double porcentajeMaximo;
    private final double porcentajeExcedido;

    public PorcentajeGananciaComestiblesInvalidoException(double porcentajeMaximo, double porcentajeExcedido) {
        this.porcentajeMaximo = porcentajeMaximo;
        this.porcentajeExcedido = porcentajeExcedido;
    }

    @Override
    public String getMessage() {
        return "El porcentaje de ganancia para los productos comestibles no puede superar el "  +
                porcentajeMaximo + "%. ingresado: " + porcentajeExcedido + "%.";
    }
}
