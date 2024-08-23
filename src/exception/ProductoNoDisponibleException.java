package exception;

public class ProductoNoDisponibleException extends RuntimeException{
    private final String id;

    public ProductoNoDisponibleException(String id) {
        this.id = id;
    }

    @Override
    public String getMessage() {
        return "El producto " + this.id + " no se encuentra disponible.";
    }
}
