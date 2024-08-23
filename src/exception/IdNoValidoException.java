package exception;

public class IdNoValidoException extends RuntimeException{
    private final String id;
    private final String formatoValido;

    public IdNoValidoException(String id, String formatoValido) {
        this.id = id;
        this.formatoValido = formatoValido;
    }

    @Override
    public String getMessage() {
        return "El id proporcionado no es valido, debe seguir el formato " + this.formatoValido + " donde XXX son dígitos, id ingreasdo: " + this.id;
    }
}
