package inventario;

import modelo.Producto;
import modelo.ProductoDigital;
import modelo.ProductoFisico;
import java.util.ArrayList;
import java.util.List;

public class Inventario {

    private ArrayList<ProductoFisico> productosFisicos = new ArrayList<>();
    private ArrayList<ProductoDigital> productosDigitales = new ArrayList<>();

    // Contadores por tipo
    private int nextIdFisico = 1;
    private int nextIdDigital = 1;

    public void agregar(Producto p) {
        if (p instanceof ProductoFisico) {
            p.setId(nextIdFisico++);
            productosFisicos.add((ProductoFisico) p);
        } else if (p instanceof ProductoDigital) {
            p.setId(nextIdDigital++);
            productosDigitales.add((ProductoDigital) p);
        } else {
            throw new IllegalArgumentException("Tipo de producto no soportado: " + p.getClass().getName());
        }
    }

    // Este busca por ID
    public Producto buscarPorId(boolean esFisico, int id) {
        if (esFisico) {
            for (ProductoFisico p : productosFisicos) {
                if (p.getId() == id) return p;
            }
        } else {
            for (ProductoDigital p : productosDigitales) {
                if (p.getId() == id) return p;
            }
        }
        return null;
    }

    // Este busca por nombre
    // Devuelve TODOS los productos (físicos y digitales) que coincidan
    public List<Producto> buscarPorNombre(String texto) {
        String q = (texto == null) ? "" : texto.trim().toLowerCase();

        ArrayList<Producto> resultados = new ArrayList<>();
        if (q.isEmpty()) return resultados;

        for (ProductoFisico p : productosFisicos) {
            if (p.getNombre().toLowerCase().contains(q)) {
                resultados.add(p);
            }
        }
        for (ProductoDigital p : productosDigitales) {
            if (p.getNombre().toLowerCase().contains(q)) {
                resultados.add(p);
            }
        }
        return resultados;
    }

    // Actualiza el stock
    public boolean actualizarStock(boolean esFisico, int id, int nuevaCantidad) {
        Producto p = buscarPorId(esFisico, id);
        if (p == null) return false;

        p.setCantidadDisponible(nuevaCantidad); // tu setter ya valida no-negativo
        return true;
    }

    public void mostrarTodo() {
        if (productosFisicos.isEmpty() && productosDigitales.isEmpty()) {
            System.out.println("Inventario vacío.");
            return;
        }

        System.out.println("---- Productos Físicos ----");
        if (productosFisicos.isEmpty()) {
            System.out.println("(sin productos físicos)");
        } else {
            for (ProductoFisico p : productosFisicos) {
                p.mostrarInformacion();
            }
        }

        System.out.println("---- Productos Digitales ----");
        if (productosDigitales.isEmpty()) {
            System.out.println("(sin productos digitales)");
        } else {
            for (ProductoDigital p : productosDigitales) {
                p.mostrarInformacion();
            }
        }
    }

    public double calcularValorTotal() {
        double total = 0;

        for (ProductoFisico p : productosFisicos) {
            total += p.calcularPrecioFinal() * p.getCantidadDisponible();
        }
        for (ProductoDigital p : productosDigitales) {
            total += p.calcularPrecioFinal() * p.getCantidadDisponible();
        }

        return total;
    }
}
