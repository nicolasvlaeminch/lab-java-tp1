package model.producto;

import enums.TipoEnvase;
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
            throw new IllegalArgumentException("El identificador para productos envasados debe seguir el formato ABXXX, donde XXX son dígitos.");
        }
        return id;
    }

    public TipoEnvase getTipoEnvase() {
        return tipoEnvase;
    }

    public void setImportado(boolean importado) {
        this.importado = importado;
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
        StringBuilder sb = new StringBuilder(super.toString());

        sb.append(", Tipo de envase: ").append(tipoEnvase)
          .append(", Es Importado: ").append(importado)
          .append(", Fecha de Vencimiento: ").append(fechaVencimiento)
          .append(", Calorias: ").append(calorias).append("\n");

        return sb.toString();
    }
}
