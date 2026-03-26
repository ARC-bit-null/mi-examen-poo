package modelo;

public class ProductoFisico extends Producto {
   private double peso;
   private double costoEnvio;

   public ProductoFisico(int id, String nombre, double precio, int cant, double peso, double envio) {
      super(id, nombre, precio, cant);
           this.peso = peso;
      this.costoEnvio = envio;  
   }

      @Override
   public double calcularPrecioFinal() {
     return precioBase + costoEnvio;
     }
       @Override
      public void mostrarInformacion() {
      System.out.println("[Físico] ID: " + getId() + " | " + getNombre() + " | Precio Final: $" + calcularPrecioFinal());
      }
}
  
