package model.productos;

import enums.TipoEnvase;
import exception.IdNoValidoException;
import interfaces.IComestible;
import util.GeneradorId;
import model.Producto;

import java.time.LocalDate;

public class Envasado extends Producto implements IComestible {
    private TipoEnvase tipoEnvase;
    private boolean importado;
    private LocalDate fechaVencimiento;
    private double calorias;

    public Envasado(String descripcion, int cantidadStock, double precioUnidad, double porcentajeGanancia, boolean disponible,
                    double porcentajeDescuento, TipoEnvase tipoEnvase, boolean importado, LocalDate fechaVencimiento, double calorias) {
        super(descripcion, cantidadStock, precioUnidad, porcentajeGanancia, disponible, porcentajeDescuento);
        this.tipoEnvase = tipoEnvase;
        this.importado = importado;
        this.fechaVencimiento = fechaVencimiento;
        this.calorias = calorias;
    }

    @Override
    protected String generarId() {
        String id = GeneradorId.generarIdentificadorEnvasado();

        if (!id.matches("AB\\d{3}")) {
            throw new IdNoValidoException(id, "ABXXX");
        }
        return id;
    }

    public boolean isImportado() {
        return importado;
    }

    @Override
    public void setFechaVencimiento(LocalDate fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    @Override
    public LocalDate getFechaVencimiento() {
        return this.fechaVencimiento;
    }

    @Override
    public void setCalorias(double calorias) {
        this.calorias = calorias;
    }

    @Override
    public double getCalorias() {
        return this.calorias;
    }

    @Override
    public String toString() {
        return super.toString()
                + ", Tipo de envase: " + tipoEnvase
                + ", Es Importado: " + importado
                + ", Fecha de Vencimiento: " + fechaVencimiento
                + ", Calorías: " + calorias + "\n";
    }
}
