package model;

import interfaces.Descuento;

public abstract class Producto implements Descuento {
    private final String id;
    private String descripcion;
    private int cantidadStock;
    private double precioUnidad;
    private double porcentajeGanancia;
    private boolean disponible;
    private double porcentajeDescuento;

    public Producto(String descripcion, int cantidadStock, double precioUnidad,
                    double porcentajeGanancia, boolean disponible) {
        this.id = generarId();
        this.descripcion = descripcion;
        this.cantidadStock = cantidadStock;
        this.precioUnidad = precioUnidad;
        this.porcentajeGanancia = porcentajeGanancia;
        this.disponible = disponible;
    }

    protected abstract String generarId();

    public abstract double calcularPrecioFinal();

    protected abstract double obtenerDescuentoMaximo();

    public String getId() {
        return id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getCantidadStock() {
        return cantidadStock;
    }

    public void setCantidadStock(int cantidadStock) {
        this.cantidadStock = cantidadStock;
    }

    public double getPrecioUnidad() {
        return precioUnidad;
    }

    public void setPrecioUnidad(double precioUnidad) {
        this.precioUnidad = precioUnidad;
    }

    public double getPorcentajeGanancia() {
        return porcentajeGanancia;
    }

    public void setPorcentajeGanancia(double porcentajeGanancia) {
        this.porcentajeGanancia = porcentajeGanancia;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    @Override
    public void setPorcentajeDescuento(double porcentaje) {
        this.porcentajeDescuento = porcentaje;
    }

    @Override
    public double getPorcentajeDescuento() {
        return this.porcentajeDescuento;
    }

    @Override
    public double getPrecioFinalConDescuento() {
        double precioVentaOriginal = this.getPrecioUnidad();

        // Calcula el factor de descuento (por ejemplo, 0.8 para un 20% de descuento)
        double factorDescuento = 1 - this.porcentajeDescuento;

        // Multiplica el precio original por el factor de descuento

        return precioVentaOriginal * factorDescuento;
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ID: ").append(id)
                .append(", Descripción: ").append(descripcion)
                .append(", Cantidad en Stock: ").append(cantidadStock)
                .append(", Precio por Unidad: ").append(precioUnidad)
                .append(", Porcentaje de Ganancia: ").append(porcentajeGanancia)
                .append(", Disponible para la Venta: ").append(disponible);

        return sb.toString();
    }
}
