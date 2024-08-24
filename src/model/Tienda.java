package model;

import exception.*;
import model.productos.Bebida;
import model.productos.Envasado;

import java.util.*;
import java.util.stream.Collectors;

public class Tienda {
    private final String nombre;
    private int maxProductosEnStock;
    private double saldoCaja;
    private List<Producto> productosEnStock;

    public Tienda(String nombre, int maxProductosEnStock, double saldoCaja) {
        this.nombre = nombre;
        this.maxProductosEnStock = maxProductosEnStock;
        this.saldoCaja = saldoCaja;
        this.productosEnStock = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    private double calcularTotalProducto(Producto producto) {
        return producto.getCantidadStock() * producto.getPrecioUnidad();
    }

    private void verificarSaldo(double totalProducto) {
        if (saldoCaja < totalProducto) {
            throw new SaldoInsuficienteException(saldoCaja, totalProducto);
        }
    }

    private void verificarCapacidadStock(Producto producto) {
        if (producto.getCantidadStock() > maxProductosEnStock) {
            throw new MaxStockSuperadoException(maxProductosEnStock, producto.getCantidadStock());
        }
    }

    private void actualizarSaldo(double aniadirSaldo) {
        saldoCaja += aniadirSaldo;
    }

    private void actualizarMaxStock(int aniadirMaxStock) {
        maxProductosEnStock += aniadirMaxStock;
    }

    private void agregarPorcentajeGanancia(Producto producto) {
        double precioConGanancia = (producto.getPrecioUnidad() * producto.getPorcentajeGanancia()) / 100;

        producto.setPrecioUnidad(precioConGanancia + producto.getPrecioUnidad());
    }

    public void comprarProducto(Producto producto) {
        double totalProducto = calcularTotalProducto(producto);

        verificarSaldo(totalProducto);
        verificarCapacidadStock(producto);

        actualizarSaldo(-totalProducto);
        agregarPorcentajeGanancia(producto);

        boolean productoExiste = false;

        for (Producto p : productosEnStock) {
            if (p.getId().equals(producto.getId())) {
                p.setDisponible(true);
                this.maxProductosEnStock -= producto.getCantidadStock();
                p.setCantidadStock(p.getCantidadStock() + producto.getCantidadStock());
                productoExiste = true;
                break;
            }
        }

        if (!productoExiste) {
            productosEnStock.add(producto);
            maxProductosEnStock -= producto.getCantidadStock();
        }

        System.out.println("Producto: " + producto.getId() + " - " + producto.getDescripcion() + " agregado con exito a la tienda.");
    }

    private void verificarDisponibilidadParaVenta(Producto producto) {
        if (!producto.isDisponible()) {
            throw new ProductoNoDisponibleException(producto.getId());
        }
    }

    private int venderUnidades(Producto producto, int cantidadSolicitada) {
        int cantidadVendida = Math.min(cantidadSolicitada, producto.getCantidadStock());
        producto.setCantidadStock(producto.getCantidadStock() - cantidadVendida);

        if (producto.getCantidadStock() == 0) {
            producto.setDisponible(false);
        }

        return cantidadVendida;
    }

    private double calcularTotalVenta(Producto producto, int cantidadVendida) {
        return producto.getPrecioUnidad() * cantidadVendida;
    }

    private void generarFactura(Producto producto, int cantidadVendida, boolean stockInsuficiente, double precioFinal) {
        System.out.println(producto.getId() + " " + producto.getDescripcion() + " " + cantidadVendida + " x " + String.format("%.2f", precioFinal));

        if (stockInsuficiente) {
            System.out.println("Hay productos con stock disponible menor al solicitado.");
        }
    }

    private Producto buscarProductoPorIdentificador(String identificador) {
        return productosEnStock.stream()
                .filter(producto -> producto.getId().equals(identificador))
                .findFirst()
                .orElse(null);
    }

    public void venderProductos(Map<String, Integer> productosAVender) {
        if (productosAVender.isEmpty()) {
            throw new IllegalArgumentException("No hay ningun producto para vender.");
        } else if (productosAVender.size() > 3) {
            throw new MaxProductosExcedidoException(3, productosAVender.size());
        }
        else {
            double totalVenta = 0.0;
            int cantidadProductos = 0;

            for (Map.Entry<String, Integer> entry : productosAVender.entrySet()) {
                String identificador = entry.getKey();
                int cantidadSolicitada = entry.getValue();

                if (cantidadSolicitada > 12) {
                    throw new MaxUnidadesProductoException(12, cantidadSolicitada);
                }

                Producto producto = buscarProductoPorIdentificador(identificador);

                verificarDisponibilidadParaVenta(producto);

                int cantidadVendida = venderUnidades(producto, cantidadSolicitada);
                double totalProducto = calcularTotalVenta(producto, cantidadVendida);

                // Verificar si el producto es importado y aplicar el impuesto
                if ((producto instanceof Bebida && ((Bebida) producto).isImportado()) ||
                        (producto instanceof Envasado && ((Envasado) producto).isImportado())) {
                    totalProducto *= 1.12; // Aplicar un 12% de impuesto
                }

                totalVenta += totalProducto;

                cantidadProductos += cantidadSolicitada;

                generarFactura(producto, cantidadVendida, cantidadVendida < cantidadSolicitada, totalProducto);
            }

            actualizarMaxStock(cantidadProductos);
            actualizarSaldo(totalVenta);

            System.out.println("TOTAL DE LA VENTA: " + String.format("%.2f", totalVenta));
        }
    }

    public List<String> obtenerComestiblesConMenorDescuento(double porcentajeDescuento) {
        // Crear una copia de la lista original
        List<Producto> copiaProductosEnStock = new ArrayList<>(productosEnStock);

        // Filtrar, ordenar y mapear los productos en la copia
        return copiaProductosEnStock.stream()
                .filter(producto -> esComestibleNoImportado(producto) && producto.getPorcentajeDescuento() < porcentajeDescuento)
                .sorted(Comparator.comparing(producto -> producto.getDescripcion().toUpperCase(Locale.ROOT)))
                .map(producto -> producto.getDescripcion().toUpperCase(Locale.ROOT))
                .collect(Collectors.toList());
    }

    // Método auxiliar para verificar si el producto es comestible y no importado
    private boolean esComestibleNoImportado(Producto producto) {
        if (producto instanceof Bebida) {
            return !((Bebida) producto).isImportado();
        } else if (producto instanceof Envasado) {
            return !((Envasado) producto).isImportado();
        }
        return false;
    }

    @Override
    public String toString() {
        return "Nombre = '" + nombre + '\'' +
               ", Stock disponible = " + maxProductosEnStock +
               ", Saldo de la caja = " + String.format("%.2f", saldoCaja) +
               "\n\nProductos en Stock : \n" + productosEnStock;
    }
}
