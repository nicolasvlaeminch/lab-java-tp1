import enums.TipoEnvase;
import model.Producto;
import model.Tienda;
import enums.TipoAplicacion;
import model.producto.Bebida;
import model.producto.Envasado;
import model.producto.Limpieza;

import java.time.LocalDate;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Tienda tienda = new Tienda("Neo Store", 12, 2000);
        Scanner scan = new Scanner(System.in);
        String opcion;

        do {
            System.out.println("******************************************************\n" +
                               "********************* " + tienda.getNombre() + " *********************\n" +
                               "******************************************************\n" +
                               "1 - Comprar Productos (La tienda no cuenta con productos inicialmente)\n" +
                               "2 - Mostrar Tienda (Testear resultado de operaciones)\n" +
                               "3 - Vender productos (Prueba de venta exitosa.)\n" +
                               "S - Salir\n" +
                               "******************************************************\n" +
                               "Selecciona una opción: \n");
            opcion = scan.nextLine();

            try {
                switch (opcion) {
                    case "1":
                        Producto productoLimpieza1 = new Limpieza("Detergente", 3, 100, 50, true,10, TipoAplicacion.COCINA);
                        Producto productoLimpieza2 = new Limpieza("Limpiador de pisos", 1, 80, 20, true,10, TipoAplicacion.PISOS);
                        Producto productoLimpieza3 = new Limpieza("Jabon en polvo", 1, 60, 30, true,10, TipoAplicacion.ROPA);
                        Producto productoLimpieza4 = new Limpieza("Jabon en barra", 1, 10, 40, true,10, TipoAplicacion.ROPA);
                        Producto productoLimpieza5 = new Limpieza("Paño de microfibra", 1, 10, 40, true,10, TipoAplicacion.MULTIUSO);
                        Producto productoBebida1 = new Bebida("Gaseosa Manaos", 1, 30, 40, true,10, LocalDate.of(2025, 7, 31), 500, false);
                        Producto productoBebida2 = new Bebida("Whiskey Jack Daniels", 1, 500, 35, true,10, LocalDate.of(2025, 2, 20), 4,1000,true);
                        Producto productoEnvasado1 = new Envasado("Queso cremoso", 1, 30, 40, true,10, TipoEnvase.PLASTICO, false, LocalDate.of(2025, 2, 20), 1000);
                        Producto productoEnvasado2 = new Envasado("Lata de frijoles magicos", 1, 20, 30, true,10, TipoEnvase.LATA, true, LocalDate.of(2026, 3, 20), 1000);
                        Producto productoEnvasado3 = new Envasado("Mermelada de frutilla", 1, 10, 50, true,10, TipoEnvase.VIDRIO, false, LocalDate.of(2025, 6, 17), 1000);
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
                        break;
                    case "2":
                        System.out.println(tienda);
                        break;
                    case "3":
                        Map<String, Integer> productosAVender = new HashMap<>();
                        productosAVender.put("AZ001", 3); // Vender 3 unidades de productoLimpieza1
                        productosAVender.put("AC002", 1);  // Vender 1 unidades de productoBebida2
                        productosAVender.put("AB003", 1); // Vender 1 unidades de productoEnvasado3
                        tienda.venderProductos(productosAVender);
                        break;
                    case "4":
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
