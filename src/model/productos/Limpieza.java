package model.productos;

import enums.TipoAplicacion;
import exception.IdNoValidoException;
import util.GeneradorId;
import model.Producto;

public class Limpieza extends Producto {
    private TipoAplicacion tipoAplicacion;

    public Limpieza(String descripcion, int cantidadStock, double precioUnidad, double porcentajeGanancia, boolean disponible,
                    double porcentajeDescuento, TipoAplicacion tipoAplicacion) {
        super(descripcion, cantidadStock, precioUnidad, porcentajeGanancia, disponible, porcentajeDescuento);
        this.tipoAplicacion = tipoAplicacion;
    }

    @Override
    protected String generarId() {
        String id = GeneradorId.generarIdentificadorLimpieza();

        if (!id.matches("AZ\\d{3}")) {
            throw new IdNoValidoException(id, "AZXXX");
        }
        return id;
    }

    public TipoAplicacion getTipoAplicacion() {
        return tipoAplicacion;
    }

    @Override
    public String toString() {
        return super.toString()
                + ", Tipo Aplicación: " + tipoAplicacion + "\n";
    }
}
