package inventario;

import modelo.Producto;
import java.util.ArrayList;

public class Inventario {

    private ArrayList<Producto> productos = new ArrayList<>();

    public void agregar(Producto p) {
        productos.add(p);
    }

    // Este busca por ID
    public Producto buscarPorId(int id) {
        for (Producto p : productos) {
            if (p.getId() == id) {
                return p;
            }
        }
        return null;
    }

    // Este busca por nombre
    public Producto buscarPorNombre(String nombre) {
        for (Producto p : productos) {
            if (p.getNombre().equalsIgnoreCase(nombre)) {
                return p;
            }
        }
        return null;
    }

    // Actualiza el stock
    public void actualizarStock(int id, int nuevaCantidad) {
        for (Producto p : productos) {
            if (p.getId() == id) {
                p.setCantidadDisponible(nuevaCantidad);
                break; 
            }
        }
    }

    public void mostrarTodo() {
        if (productos.isEmpty()) {
            System.out.println("Inventario vacío.");
            return;
        }
        for (Producto p : productos) {
            p.mostrarInformacion();
        }
    }

    public double calcularValorTotal() {
        double total = 0;
        for (Producto p : productos) {
            total += p.calcularPrecioFinal() * p.getCantidadDisponible();
        }
        return total;
    }
}
