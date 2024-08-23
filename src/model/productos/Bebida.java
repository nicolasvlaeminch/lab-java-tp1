package model.productos;

import exception.IdNoValidoException;
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

    // Constructor para bebidas alcoholicas.
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
            throw new IdNoValidoException(id, "ACXXX");
        }
        return id;
    }

    public boolean isImportado() {
        return importado;
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
        return super.toString()
                + ", Es Importado: " + importado
                + ", Fecha de Vencimiento: " + fechaVencimiento
                + ", Calorías: " + calorias
                + (graduacionAlcoholica > 0
                ? ", Graduación alcohólica: " + graduacionAlcoholica + "\n"
                : "\n");
    }
}
