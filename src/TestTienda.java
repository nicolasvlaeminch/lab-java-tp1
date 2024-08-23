import enums.TipoAplicacion;
import enums.TipoEnvase;
import model.Producto;
import model.Tienda;
import model.productos.Bebida;
import model.productos.Envasado;
import model.productos.Limpieza;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;

public class TestTienda {
    public static void main(String[] args) {
        Tienda tienda = new Tienda("Neo Store", 150, 20000);
        Scanner scan = new Scanner(System.in);

        Producto productoLimpieza1 = new Limpieza("Detergente", 10, 101.1, 10, true,10, TipoAplicacion.COCINA);
        Producto productoLimpieza2 = new Limpieza("Limpiador de pisos", 8, 80, 10, true,10, TipoAplicacion.PISOS);
        Producto productoLimpieza3 = new Limpieza("Jabon en polvo", 8, 60, 10, true,10, TipoAplicacion.ROPA);
        Producto productoLimpieza4 = new Limpieza("Jabon en barra", 7, 10, 10, true,10, TipoAplicacion.ROPA);
        Producto productoLimpieza5 = new Limpieza("Paño de microfibra", 10, 10, 10, true,10, TipoAplicacion.MULTIUSO);
        Producto productoBebida1 = new Bebida("Gaseosa Manaos", 10, 30, 10, true,10, LocalDate.of(2025, 7, 31), 500, false);
        Producto productoBebida2 = new Bebida("Whiskey Jack Daniels", 12, 500, 10, true,10, LocalDate.of(2025, 2, 20), 40,1000,true);
        Producto productoEnvasado1 = new Envasado("Queso cremoso", 11, 30, 10, true,10, TipoEnvase.PLASTICO, false, LocalDate.of(2025, 2, 20), 800);
        Producto productoEnvasado2 = new Envasado("Lata de atun", 10, 20, 10, true,10, TipoEnvase.LATA, true, LocalDate.of(2026, 3, 20), 1000);
        Producto productoEnvasado3 = new Envasado("Mermelada de frutilla", 15, 10, 10, true,10, TipoEnvase.VIDRIO, false, LocalDate.of(2025, 6, 17), 900);

        String opcion;
        boolean flagCompra = false;
        boolean flagVenta = false;

        System.out.println("******************************************************\n" +
                "(Se recomienda seguir el orden de los test desde el 1 al 10 para testear correctamente.)");

        do {
            System.out.println("******************************************************\n" +
                    "********************* " + tienda.getNombre() + " *********************\n" +
                    "******************************************************\n" +
                    "1 - Comprar Productos (La tienda no cuenta con productos inicialmente)\n" +
                    "2 - Mostrar Tienda (Mostrara informacion de la tienda junto a sus productos previamente comprados.)\n" +
                    "3 - Vender productos (Prueba de venta exitosa.)\n" +
                    "4 - Test de excepcion al comprar productos superando el maximo de stock.\n" +
                    "5 - Test de excepcion al comprar un productos con saldo insuficiente en la caja.\n" +
                    "6 - Test de validacion al vender mas de 3 productos a la vez.\n" +
                    "7 - Test de validacion al vender producto con mas de 12 unidades.\n" +
                    "8 - Test al vender un productos con unidades mayor al stock disponible.\n" +
                    "9 - Test de excepcion al vender un producto no disponible para la venta.\n" +
                    "10 - Mostrar lista de comestibles con menor descuento.\n" +
                    "S - Salir\n" +
                    "******************************************************\n" +
                    "Selecciona una opción: \n");
            opcion = scan.nextLine();

            try {
                switch (opcion) {
                    case "1":
                        tienda.comprarProducto(productoLimpieza1);
                        tienda.comprarProducto(productoLimpieza2);
                        tienda.comprarProducto(productoLimpieza3);
                        tienda.comprarProducto(productoLimpieza4);
                        tienda.comprarProducto(productoLimpieza5);
                        tienda.comprarProducto(productoBebida1);
                        tienda.comprarProducto(productoBebida2);
                        tienda.comprarProducto(productoEnvasado1);
                        tienda.comprarProducto(productoEnvasado2);
                        tienda.comprarProducto(productoEnvasado3);
                        flagCompra = true;
                        break;
                    case "2":
                        System.out.println(tienda);
                        flagVenta = true;
                        break;
                    case "3":
                        if (flagCompra) {
                            Map<String, Integer> productosAVender = new HashMap<>();
                            productosAVender.put("AZ001", 10);
                            productosAVender.put("AZ002", 8);
                            productosAVender.put("AZ003", 8);
                            tienda.venderProductos(productosAVender);
                        }
                        else {
                            System.out.println("Primero debes usar la opcion 1 para acceder a este test.");
                        }
                        break;
                    case "4":
                        Producto productoMaxStockSuperado = new Envasado("Mermelada de naranja", 200, 1, 10, true,10, TipoEnvase.VIDRIO, false, LocalDate.of(2025, 6, 17), 1000);
                        tienda.comprarProducto(productoMaxStockSuperado);
                        break;
                    case "5":
                        Producto productoSaldoCajaSuperada = new Envasado("Mermelada de mandarina", 1, 50000, 10, true,10, TipoEnvase.VIDRIO, false, LocalDate.of(2025, 6, 17), 1000);
                        tienda.comprarProducto(productoSaldoCajaSuperada);
                        break;
                    case "6":
                        Map<String, Integer> productosAVenderErrorMaxProductos = new HashMap<>();
                        productosAVenderErrorMaxProductos.put("AZ001", 2);
                        productosAVenderErrorMaxProductos.put("AZ002", 2);
                        productosAVenderErrorMaxProductos.put("AZ003", 2);
                        productosAVenderErrorMaxProductos.put("AZ004", 2);
                        tienda.venderProductos(productosAVenderErrorMaxProductos);
                        break;
                    case "7":
                        Map<String, Integer> productoAVenderErrorMaxUnidades = new HashMap<>();
                        productoAVenderErrorMaxUnidades.put("AZ004", 13);
                        tienda.venderProductos(productoAVenderErrorMaxUnidades);
                        break;
                    case "8":
                        if (flagCompra) {
                            Map<String, Integer> productosAVenderErrorMaxUnidades = new HashMap<>();
                            productosAVenderErrorMaxUnidades.put("AB002", 12);
                            tienda.venderProductos(productosAVenderErrorMaxUnidades);
                        }
                        else {
                            System.out.println("Primero debes usar la opcion 1 para acceder a este test.");
                        }
                        break;
                    case "9":
                        if (flagCompra && flagVenta) {
                            Map<String, Integer> productosAVenderNoDisponibles = new HashMap<>();
                            productosAVenderNoDisponibles.put("AZ001", 10);
                            tienda.venderProductos(productosAVenderNoDisponibles);
                        }
                        else {
                            System.out.println("Primero debes usar la opcion 1 y 2 para acceder a este test.");
                        }
                        break;
                    case "10":
                        if (flagCompra) {
                            System.out.println(tienda.obtenerComestiblesConMenorDescuento(15));
                        }
                        else {
                            System.out.println("Primero debes usar la opcion 1 para acceder a este test.");
                        }
                        break;
                    case "S":
                        opcion = "S";
                        break;
                    default:
                        System.out.println("Debes seleccionar una opcion valida.");
                        break;
                }
            } catch(RuntimeException e) {
                System.out.println( "ERROR: " + e.getMessage());
            }

        } while(!Objects.equals(opcion, "S"));
    }
}
