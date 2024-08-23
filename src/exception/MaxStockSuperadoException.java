package exception;

public class MaxStockSuperadoException extends RuntimeException{
    private final int maxStock;
    private final int stockProducto;

    public MaxStockSuperadoException(int maxStock, int stockProducto) {
        this.maxStock = maxStock;
        this.stockProducto = stockProducto;
    }

    @Override
    public String getMessage() {
        return "No se pueden agregar nuevos productos a la tienda ya que se alcanzó el máximo de stock," +
                " el cual es: " + this.maxStock + " y este producto posee: " + stockProducto + " unidades.";
    }
}
