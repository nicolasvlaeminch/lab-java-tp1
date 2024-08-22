package model;

import model.producto.Bebida;
import model.producto.Envasado;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Tienda {
    private String nombre;
    private int maxProductosEnStock;
    private double saldoCaja;
    private final List<Producto> productosEnStock;

    public Tienda(String nombre, int maxProductosEnStock, double saldoCaja) {
        this.nombre = nombre;
        this.maxProductosEnStock = maxProductosEnStock;
        this.saldoCaja = saldoCaja;
        this.productosEnStock = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getMaxProductosEnStock() {
        return maxProductosEnStock;
    }

    public void setMaxProductosEnStock(int maxProductosEnStock) {
        this.maxProductosEnStock = maxProductosEnStock;
    }

    public double getSaldoCaja() {
        return saldoCaja;
    }

    public void setSaldoCaja(double saldoCaja) {
        this.saldoCaja = saldoCaja;
    }

    public List<Producto> getProductosEnStock() {
        return productosEnStock;
    }

    private double calcularTotalProducto(Producto producto) {
        return producto.getCantidadStock() * producto.getPrecioUnidad();
    }

    private void verificarSaldo(double totalProducto) {
        if (saldoCaja < totalProducto) {
            throw new IllegalStateException("El producto no podrá ser agregado a la tienda por saldo insuficiente en la caja.");
        }
    }

    private void verificarCapacidadStock(Producto producto) {
        if (producto.getCantidadStock() > maxProductosEnStock) {
            throw new IllegalStateException("No se pueden agregar nuevos productos a la tienda ya que se alcanzó el máximo de stock.");
        }
    }

    private void actualizarSaldo(double cantidad) {
        saldoCaja += cantidad;
    }

    public void comprarProducto(Producto producto) {
        double totalProducto = calcularTotalProducto(producto);

        verificarSaldo(totalProducto);
        verificarCapacidadStock(producto);

        productosEnStock.add(producto);
        actualizarSaldo(-totalProducto);
        maxProductosEnStock -= producto.getCantidadStock();

        System.out.println("Producto: " + producto.getId() + " - " + producto.getDescripcion() + " agregado con exito a la tienda.");
    }

    private void verificarDisponibilidadParaVenta(Producto producto) {
        if (!producto.isDisponible()) {
            throw new IllegalStateException("El producto " + producto.getId() + " no se encuentra disponible.");
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
        System.out.println(producto.getId() + " " + producto.getDescripcion() + " " + cantidadVendida + " x " + precioFinal);
//        System.out.println("TOTAL VENTA: " + totalVenta);

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
        if (productosAVender.size() > 3) {
            throw new IllegalArgumentException("No se pueden vender más de 3 productos a la vez.");
        }

        double totalVenta = 0.0;
        boolean stockInsuficiente = false;

        for (Map.Entry<String, Integer> entry : productosAVender.entrySet()) {
            String identificador = entry.getKey();
            int cantidadSolicitada = entry.getValue();

            if (cantidadSolicitada > 12) {
                throw new IllegalArgumentException("No se pueden vender más de 12 unidades de un mismo producto.");
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

            if (cantidadVendida < cantidadSolicitada) {
                stockInsuficiente = true;
            }

            generarFactura(producto, cantidadVendida, cantidadVendida < cantidadSolicitada, totalProducto);
        }

        actualizarSaldo(totalVenta);

        System.out.println("TOTAL DE LA VENTA: " + totalVenta);
        if (stockInsuficiente) {
            System.out.println("Algunos productos tenían stock insuficiente para la cantidad solicitada.");
        }
    }

    @Override
    public String toString() {
        return "Nombre = '" + nombre + '\'' +
               ", Stock disponible = " + maxProductosEnStock +
               ", Saldo de la caja = " + saldoCaja +
               "\n\nProductos en Stock : \n" + productosEnStock;
    }
}
