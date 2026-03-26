package inventario;
import modelo.Producto;
import java.util.ArrayList;

public class Inventario {
  private ArrayList<Producto> productos = new ArrayList<>();

  public void agregar(Producto p) {
  productos.add(p);
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
    total += p.calcularPrecioFinal() * p.getCantidad();
     }
     return total;
     }
}

    
