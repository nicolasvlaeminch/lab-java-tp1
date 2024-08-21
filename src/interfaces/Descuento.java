package interfaces;

import java.math.BigDecimal;

public interface Descuento {
    void setPorcentajeDescuento(double porcentaje);
    double getPorcentajeDescuento();
    double getPrecioFinalConDescuento();
}
