package model.producto;

import enums.TipoAplicacion;
import util.GeneradorId;
import model.Producto;

public class Limpieza extends Producto {
    private TipoAplicacion tipoAplicacion;

    public Limpieza(String descripcion, int cantidadStock, double precioUnidad, double porcentajeGanancia,
                    boolean disponible, TipoAplicacion tipoAplicacion) {
        super(descripcion, cantidadStock, precioUnidad,
                porcentajeGanancia, disponible);
        this.tipoAplicacion = tipoAplicacion;
    }

    @Override
    protected String generarId() {
        if (!GeneradorId.generarIdentificadorLimpieza().matches("AZ\\d{3}")) {
            throw new IllegalArgumentException("El identificador para productos de limpieza debe seguir el formato AZXXX, donde XXX son dígitos.");
        }
        return GeneradorId.generarIdentificadorLimpieza();
    }

    @Override
    public double calcularPrecioFinal() {
        return 2;
    }

    @Override
    protected double obtenerDescuentoMaximo() {
        return 0;
    }

    public TipoAplicacion getTipoAplicacion() {
        return tipoAplicacion;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(super.toString());
        sb.append(", Tipo Aplicacion: ").append(tipoAplicacion).append("\n");
        return sb.toString();
    }
}
