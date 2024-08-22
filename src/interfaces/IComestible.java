package interfaces;

import java.time.LocalDate;

public interface IComestible {
    void setFechaVencimiento(LocalDate fechaVencimiento);

    LocalDate getFechaVencimiento();

    void setCalorias(double calorias);

    double getCalorias();
}
