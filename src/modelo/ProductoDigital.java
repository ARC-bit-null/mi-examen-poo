package modelo;

public class ProductoDigital extends Producto {
   private double tamanoMB;
    private double porcentajeLicencia;

   public ProductoDigital(int id, String nombre, double precio, int cant, double mb, double licencia) {
    super(id, nombre, precio, cant);
    this.tamanoMB = mb;
    this.porcentajeLicencia = licencia;
     }

   @Override
     public double calcularPrecioFinal() {
        return precioBase + (precioBase * (porcentajeLicencia / 100));
       }

   @Override
     public void mostrarInformacion() {
       System.out.println("[Digital] ID: " + getId() + " | " + getNombre() + " | Precio Final: $" + calcularPrecioFinal());
                                        }
}
                        
