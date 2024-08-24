package exception;

public class MaxUnidadesProductoException extends RuntimeException{
    private final int maxUnidadesProducto;
    private final int unidadesPedidas;

    public MaxUnidadesProductoException(int maxUnidadesProducto, int unidadesPedidas) {
        this.maxUnidadesProducto = maxUnidadesProducto;
        this.unidadesPedidas = unidadesPedidas;
    }

    @Override
    public String getMessage() {
        return "La cantidad de unidades solicitadas por producto excede el maximo permitido, maximo permitido: " +
                this.maxUnidadesProducto + ", cantidad solicitada: " + this.unidadesPedidas;
    }
}
