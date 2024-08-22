package model;

import model.producto.Bebida;
import model.producto.Envasado;
import model.producto.Limpieza;

public abstract class Producto {
    private final String id;
    private String descripcion;
    private int cantidadStock;
    private double precioUnidad;
    private double porcentajeGanancia;
    private boolean disponible;
    private double porcentajeDescuento;

    public Producto(String descripcion, int cantidadStock, double precioUnidad,
                    double porcentajeGanancia, boolean disponible, double porcentajeDescuento) {
        this.id = generarId();
        this.descripcion = descripcion;
        this.cantidadStock = cantidadStock;
        this.precioUnidad = precioUnidad;
        this.porcentajeGanancia = porcentajeGanancia;
        this.disponible = disponible;
        this.porcentajeDescuento = validarPorcentajeDescuento(porcentajeDescuento);
    }

    protected abstract String generarId();

    protected double validarPorcentajeDescuento(double porcentajeDescuento) {
        if (this instanceof Bebida) {
            if (porcentajeDescuento > 10) {
                throw new IllegalArgumentException("El porcentaje de descuento para bebidas no puede superar el 10%.");
            }
        } else if (this instanceof Envasado) {
            if (porcentajeDescuento > 15) {
                throw new IllegalArgumentException("El porcentaje de descuento para productos envasados no puede superar el 15%.");
            }
        } else if (this instanceof Limpieza) {
            if (porcentajeDescuento > 20) {
                throw new IllegalArgumentException("El porcentaje de descuento para productos de limpieza no puede superar el 20%.");
            }
        }
        return porcentajeDescuento;
    }

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
