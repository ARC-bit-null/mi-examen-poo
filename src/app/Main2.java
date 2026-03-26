package app;

import modelo.*;
import inventario.*;

public class Main {
    public static void main(String[] args) {

        Inventario miTienda = new Inventario();

        //Aqui se cren los productos que nos dio el profe
        Producto p1 = new ProductoFisico(1, "Laptop", 10000, 5, 2.5, 300);
        Producto p2 = new ProductoDigital(2, "Videojuego", 500, 10, 15000, 20);

        // Agregamos el inventario 
        miTienda.agregar(p1);
        miTienda.agregar(p2);

        // Mostrar productos o el catalogo lo que prefieran
        System.out.println("=== INVENTARIO ===");
        miTienda.mostrarTodo();

        // Buscar por ID (si esque lo utilizamos)
        System.out.println("\n=== BUSCAR POR ID ===");
        Producto buscado = miTienda.buscarPorId(1);
        if (buscado != null) {
            buscado.mostrarInformacion();
        }

        // Buscar por nombre (si elegimos este)
        System.out.println("\n=== BUSCAR POR NOMBRE ===");
        Producto buscado2 = miTienda.buscarPorNombre("Videojuego");
        if (buscado2 != null) {
            buscado2.mostrarInformacion();
        }

        // Actualizar stock
        System.out.println("\n=== ACTUALIZAR STOCK ===");
        miTienda.actualizarStock(1, 8);
        miTienda.mostrarTodo();

        // Calcular valor total
        System.out.println("\nValor total del inventario: $" + miTienda.calcularValorTotal());
    }
}
