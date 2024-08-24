package model;

import enums.TipoAplicacion;
import exception.DescuentoExcedidoException;
import exception.PorcentajeGananciaComestiblesInvalidoException;
import exception.PorcentajeGananciaLimpiezaInvalidoException;
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
                throw new DescuentoExcedidoException(10, porcentajeDescuento, getTipoProducto());
            }
        } else if (this instanceof Envasado) {
            if (porcentajeDescuento > 15) {
                throw new DescuentoExcedidoException(15, porcentajeDescuento, getTipoProducto());
            }
        } else if (this instanceof Limpieza) {
            if (porcentajeDescuento > 20) {
                throw new DescuentoExcedidoException(20, porcentajeDescuento, getTipoProducto());
            }
        }
        return porcentajeDescuento;
    }

    private String getTipoProducto() {
        if (this instanceof Bebida) {
            return "bebidas";
        } else if (this instanceof Envasado) {
            return "envasados";
        } else if (this instanceof Limpieza) {
            return "limpieza";
        } else {
            return "desconocido";
        }
    }

    private double validarPorcentajeGanancia(double porcentajeGanancia) {
        if (this instanceof Bebida || this instanceof Envasado) {
            if (porcentajeGanancia > 20) {
                throw new PorcentajeGananciaComestiblesInvalidoException(20, porcentajeGanancia);
            }
        } else if (this instanceof Limpieza limpieza) {
            TipoAplicacion tipoAplicacion = limpieza.getTipoAplicacion();
            if (!(tipoAplicacion == TipoAplicacion.COCINA || tipoAplicacion == TipoAplicacion.MULTIUSO)) {
                if (porcentajeGanancia < 10 || porcentajeGanancia > 25) {
                    throw new PorcentajeGananciaLimpiezaInvalidoException(25, 10, porcentajeGanancia);
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
