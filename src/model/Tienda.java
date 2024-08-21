package model;

import java.util.ArrayList;
import java.util.List;

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

    public void comprarProducto(Producto producto) {
        double totalProducto = calcularTotalProducto(producto);

        verificarSaldo(totalProducto);
        verificarCapacidadStock(producto);

        productosEnStock.add(producto);
//        saldoCaja -= totalProducto;
        actualizarSaldo(-totalProducto);
        maxProductosEnStock -= producto.getCantidadStock();

        System.out.println("Producto: " + producto.getId() + " - " + producto.getDescripcion() + " agregado con exito a la tienda.");
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

    public void venderProducto(String identificador, int cantidad) {
        Producto producto = buscarProductoPorIdentificador(identificador);

        verificarDisponibilidadParaVenta(producto);

        int cantidadVendida = venderUnidades(producto, cantidad);
        double totalVenta = calcularTotalVenta(producto, cantidadVendida);

        actualizarSaldo(totalVenta);
        generarFactura(producto, cantidadVendida, totalVenta, cantidad > cantidadVendida);
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

    private void generarFactura(Producto producto, int cantidadVendida, double totalVenta, boolean stockInsuficiente) {
        System.out.println(producto.getId() + " " + producto.getDescripcion() + " " + cantidadVendida + " x " + producto.getPrecioUnidad());
        System.out.println("TOTAL VENTA: " + totalVenta);

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

    public List<Producto> getProductosEnStock() {
        return productosEnStock;
    }

    @Override
    public String toString() {
        return "Nombre = '" + nombre + '\'' +
               ", Stock disponible = " + maxProductosEnStock +
               ", Saldo de la caja = " + saldoCaja +
               "\n\nProductos en Stock : \n" + productosEnStock;
    }
}
