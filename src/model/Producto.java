package model;

import enums.TipoAplicacion;
import model.productos.Bebida;
import model.productos.Envasado;
import model.productos.Limpieza;

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
        this.porcentajeGanancia = validarPorcentajeGanancia(porcentajeGanancia);
        this.disponible = disponible;
        this.porcentajeDescuento = validarPorcentajeDescuento(porcentajeDescuento);
    }

    protected abstract String generarId();

    private double validarPorcentajeDescuento(double porcentajeDescuento) {
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

    private double validarPorcentajeGanancia(double porcentajeGanancia) {
        if (this instanceof Bebida || this instanceof Envasado) {
            if (porcentajeGanancia > 20) {
                throw new IllegalArgumentException("El porcentaje de ganancia para los productos comestibles no puede superar el 20%.");
            }
        } else if (this instanceof Limpieza limpieza) {
            TipoAplicacion tipoAplicacion = limpieza.getTipoAplicacion();
            if (!(tipoAplicacion == TipoAplicacion.COCINA || tipoAplicacion == TipoAplicacion.MULTIUSO)) {
                if (porcentajeGanancia < 10 || porcentajeGanancia > 25) {
                    throw new IllegalArgumentException("El porcentaje de ganancia para los productos de limpieza que no" +
                            " sean de tipo COCINA o MULTIUSO no puede ser menor al 10% ni superar el 25%.");
                }
            }
        }
        return porcentajeGanancia;
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

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public double getPorcentajeDescuento() {
        return porcentajeDescuento;
    }

    @Override
    public String toString() {
        return "ID: " + id
                + ", Descripción: " + descripcion
                + ", Cantidad en Stock: " + cantidadStock
                + ", Precio por Unidad: " + String.format("%.2f", precioUnidad)
                + ", Porcentaje de Ganancia: " + porcentajeGanancia
                + ", Disponible para la Venta: " + disponible;
    }
}
