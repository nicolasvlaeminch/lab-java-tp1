package model.producto;

import enums.TipoEnvase;
import interfaces.Comestible;
import util.GeneradorId;
import model.Producto;

import java.time.LocalDate;

public class Envasado extends Producto implements Comestible {
    private TipoEnvase tipoEnvase;
    private boolean importado;
    private LocalDate fechaVencimiento;
    private double calorias;

    public Envasado(String descripcion, int cantidadStock, double precioUnidad, double porcentajeGanancia, boolean disponible,
                    TipoEnvase tipoEnvase, boolean importado, LocalDate fechaVencimiento, double calorias) {
        super(descripcion, cantidadStock, precioUnidad, porcentajeGanancia, disponible);
        this.tipoEnvase = tipoEnvase;
        this.importado = importado;
        this.fechaVencimiento = fechaVencimiento;
        this.calorias = calorias;
    }

    @Override
    protected String generarId() {
        if (!GeneradorId.generarIdentificadorEnvasado().matches("AB\\d{3}")) {
            throw new IllegalArgumentException("El identificador para productos envasados debe seguir el formato ABXXX, donde XXX son dígitos.");
        }
        return GeneradorId.generarIdentificadorEnvasado();
    }

    @Override
    protected double obtenerDescuentoMaximo() {
        return 0;
    }

    @Override
    public double calcularPrecioFinal() {
        double precioFinal = getPrecioUnidad();
        if (importado) {
            precioFinal *= 1.12; // Aplicar impuesto del 12%
        }
        return precioFinal;
    }

    public TipoEnvase getTipoEnvase() {
        return tipoEnvase;
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
