package interfaces;

import java.time.LocalDate;

public interface Comestible {
    void setFechaVencimiento(LocalDate fechaVencimiento);

    LocalDate getFechaVencimiento();

    void setCalorias(double calorias);

    double getCalorias();
}
