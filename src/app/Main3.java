package app;

import inventario.Inventario;
import modelo.Producto;

import modelo.ProductoDigital;
import modelo.ProductoFisico;

import java.util.List;
import java.util.Scanner;

public class Main {

    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        Inventario miTienda = new Inventario();

        //Aqui se cren los productos que nos dio el profe
        Producto p1 = new ProductoFisico(0, "Laptop", 10000, 5, 2.5, 300);
        Producto p2 = new ProductoDigital(0, "Videojuego", 500, 10, 15000, 20);

        // Agregamos al inventario
        miTienda.agregar(p1);
        miTienda.agregar(p2);

        // Mostrar productos
        System.out.println("=== INVENTARIO ===");
        miTienda.mostrarTodo();

        // Buscar por ID (ahora se elige tipo)
        System.out.println("\n=== BUSCAR POR ID ===");
        Producto buscadoFisico = miTienda.buscarPorId(true, 1); // true = físico
        if (buscadoFisico != null) {
            buscadoFisico.mostrarInformacion();
        }

        Producto buscadoDigital = miTienda.buscarPorId(false, 1); // false = digital
        if (buscadoDigital != null) {
            buscadoDigital.mostrarInformacion();
        }

        // Buscar por nombre
        System.out.println("\n=== BUSCAR POR NOMBRE ===");
        List<Producto> coincidencias = miTienda.buscarPorNombre("Videojuego");
        for (Producto p : coincidencias) {
            p.mostrarInformacion();
        }

        // Actualizar stock
        System.out.println("\n=== ACTUALIZAR STOCK ===");
        miTienda.actualizarStock(true, 1, 8); // actualizar stock del físico con ID 1
        miTienda.mostrarTodo();

        // Calcular valor total
        System.out.println("\nValor total del inventario: $" + miTienda.calcularValorTotal());

        int nextIdFisico = 1;
        int nextIdDigital = 1;

        int opcion;
        do {
            mostrarMenu();
            opcion = leerEntero("Elige una opción: ");

            switch (opcion) {
                case 1 -> { // Crear producto físico
                    ProductoFisico pf = crearProductoFisico(nextIdFisico);
                    miTienda.agregar(pf);
                    System.out.println("Producto físico agregado con ID: " + pf.getId());
                    nextIdFisico++;
                }
                case 2 -> { // Crear producto digital
                    ProductoDigital pd = crearProductoDigital(nextIdDigital);
                    miTienda.agregar(pd);
                    System.out.println("Producto digital agregado con ID: " + pd.getId());
                    nextIdDigital++;
                }
                case 3 -> { // Mostrar inventario
                    System.out.println("\n=== INVENTARIO ===");
                    miTienda.mostrarTodo();
                }
                case 4 -> { // Buscar por ID (elige tipo)
                    boolean esFisico = elegirTipoProducto();
                    int id = leerEntero("ID a buscar: ");

                    Producto p = miTienda.buscarPorId(esFisico, id);
                    if (p == null) {
                        System.out.println("No se encontró el producto.");
                    } else {
                        System.out.println("\n=== RESULTADO ===");
                        p.mostrarInformacion();
                    }
                }
                case 5 -> { // Buscar por nombre (parcial/exacto)
                    System.out.print("Nombre del producto: ");
                    String q = sc.nextLine();

                    List<Producto> resultados = miTienda.buscarPorNombre(q);
                    if (resultados.isEmpty()) {
                        System.out.println("No se encontraron coincidencias.");
                    } else {
                        System.out.println("\n=== COINCIDENCIAS (" + resultados.size() + ") ===");
                        for (Producto p : resultados) {
                            p.mostrarInformacion();
                        }
                    }
                }
                case 6 -> { // Actualizar stock (tipo + id)
                    boolean esFisico = elegirTipoProducto();
                    int id = leerEntero("ID del producto a actualizar: ");

                    Producto p = miTienda.buscarPorId(esFisico, id);
                    if (p == null) {
                        System.out.println("No se encontró el producto.");
                        break;
                    }

                    System.out.println("Producto encontrado:");
                    p.mostrarInformacion();

                    int nuevaCantidad = leerEntero("Nueva cantidad en stock: ");
                    boolean ok = miTienda.actualizarStock(esFisico, id, nuevaCantidad);

                    if (ok) {
                        System.out.println("Stock actualizado.");
                    } else {
                        System.out.println("No se pudo actualizar (producto no encontrado).");
                    }
                }
                case 7 -> { // Calcular valor total
                    double total = miTienda.calcularValorTotal();
                    System.out.println("Valor total del inventario: $" + total);
                }
                case 0 -> System.out.println("Saliendo...");
                default -> System.out.println("Opción inválida.");
            }

            System.out.println(); // línea en blanco
        } while (opcion != 0);
    }

    private static void mostrarMenu() {
        System.out.println("===== MENU INVENTARIO =====");
        System.out.println("1) Agregar producto físico");
        System.out.println("2) Agregar producto digital");
        System.out.println("3) Mostrar inventario");
        System.out.println("4) Buscar por ID");
        System.out.println("5) Buscar por nombre");
        System.out.println("6) Actualizar stock");
        System.out.println("7) Calcular valor total del inventario");
        System.out.println("0) Salir");
    }

    private static boolean elegirTipoProducto() {
        int t;
        do {
            System.out.println("Tipo de producto:");
            System.out.println("1) Físico");
            System.out.println("2) Digital");
            t = leerEntero("Elige: ");
            if (t != 1 && t != 2) System.out.println("Opción inválida.");
        } while (t != 1 && t != 2);

        return t == 1;
    }

    private static ProductoFisico crearProductoFisico(int id) {
        System.out.print("Nombre: ");
        String nombre = sc.nextLine();
        double precioBase = leerDouble("Precio base: ");
        int cantidad = leerEntero("Cantidad disponible: ");
        double peso = leerDouble("Peso (kg): ");
        double costoEnvio = leerDouble("Costo de envío: ");

        return new ProductoFisico(0, nombre, precioBase, cantidad, peso, costoEnvio);
    }

    private static ProductoDigital crearProductoDigital(int id) {
        System.out.print("Nombre: ");
        String nombre = sc.nextLine();
        double precioBase = leerDouble("Precio base: ");
        int cantidad = leerEntero("Cantidad disponible: ");
        double tamanoMB = leerDouble("Tamaño (MB): ");
        double porcentajeLicencia = leerDouble("Porcentaje licencia (%): ");

        return new ProductoDigital(0, nombre, precioBase, cantidad, tamanoMB, porcentajeLicencia);
    }

    private static int leerEntero(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                String s = sc.nextLine().trim();
                return Integer.parseInt(s);
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Debe ser un número entero.");
            }
        }
    }

    private static double leerDouble(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                String s = sc.nextLine().trim();
                return Double.parseDouble(s);
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Debe ser un número.");
            }
        }
    }
}

