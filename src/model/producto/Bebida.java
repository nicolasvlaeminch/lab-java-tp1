package model.producto;

import interfaces.IComestible;
import util.GeneradorId;
import model.Producto;

import java.time.LocalDate;

public class Bebida extends Producto implements IComestible {
    private LocalDate fechaVencimiento;
    private double graduacionAlcoholica;
    private double calorias;
    private boolean importado;

    // Constructor para bebidas sin alcohol.
    public Bebida(String descripcion, int cantidadStock, double precioUnidad, double porcentajeGanancia,
                  boolean disponible,double porcentajeDescuento, LocalDate fechaVencimiento, double calorias, boolean importado) {
        super(descripcion, cantidadStock, precioUnidad, porcentajeGanancia, disponible, porcentajeDescuento);
        this.fechaVencimiento = fechaVencimiento;
        this.calorias = calcularCalorias(calorias);
        this.importado = importado;
    }

    // Constructor sobrecargado para bebidas alcoholicas.
    public Bebida(String descripcion, int cantidadStock, double precioUnidad, double porcentajeGanancia,
                  boolean disponible, double porcentajeDescuento, LocalDate fechaVencimiento, double graduacionAlcoholica, double calorias, boolean importado) {
        super(descripcion, cantidadStock, precioUnidad, porcentajeGanancia, disponible, porcentajeDescuento);
        this.fechaVencimiento = fechaVencimiento;
        this.graduacionAlcoholica = graduacionAlcoholica;
        this.calorias = calcularCalorias(calorias);
        this.importado = importado;
    }

    @Override
    protected String generarId() {
        String id = GeneradorId.generarIdentificadorBebida();

        if (!id.matches("AC\\d{3}")) {
            throw new IllegalArgumentException("El identificador para productos de bebidas debe seguir el formato ACXXX, donde XXX son dígitos.");
        }
        return id;
    }

    public boolean isImportado() {
        return importado;
    }

    public void setImportado(boolean importado) {
        this.importado = importado;
    }

    @Override
    public LocalDate getFechaVencimiento() {
        return fechaVencimiento;
    }

    @Override
    public void setFechaVencimiento(LocalDate fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    @Override
    public double getCalorias() {
        return calorias;
    }

    @Override
    public void setCalorias(double calorias) {
        this.calorias = calorias;
    }

    public double getGraduacionAlcoholica() {
        return graduacionAlcoholica;
    }

    public void setGraduacionAlcoholica(double graduacionAlcoholica) {
        this.graduacionAlcoholica = graduacionAlcoholica;
    }

    public double calcularCalorias(double caloriasBase) {
        if (graduacionAlcoholica <= 2) {
            return caloriasBase;
        } else if (graduacionAlcoholica <= 4.5) {
            return caloriasBase * 1.25;
        } else {
            return caloriasBase * 1.5;
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(super.toString());

        sb.append(", Es Importado: ").append(importado)
          .append(", Fecha de Vencimiento: ").append(fechaVencimiento)
          .append(", Calorias: ").append(calorias);

        if (graduacionAlcoholica > 0) {
            sb.append(", Graduacion alcoholica: ").append(graduacionAlcoholica).append("\n");
        }
        else {
            sb.append("\n");
        }

        return sb.toString();
    }
}
