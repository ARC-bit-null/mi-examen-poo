package modelo;

public abstract class Producto {
    private int id;
    private String nombre;
    protected double precioBase;
    private int cantidadDisponible;

    public Producto(int id, String nombre, double precioBase, int cantidadDisponible) {
        setId(id);
        this.nombre = nombre;
        setPrecioBase(precioBase);
        setCantidadDisponible(cantidadDisponible);
    }

    public void setId(int id) {
        if (id > 0) this.id = id;
    }

    public void setPrecioBase(double precio) {
        if (precio >= 0) this.precioBase = precio;
    }

    public void setCantidadDisponible(int cantidad) {
        if (cantidad >= 0) this.cantidadDisponible = cantidad;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public int getCantidadDisponible() {
        return cantidadDisponible;
    }

    public abstract double calcularPrecioFinal();
    public abstract void mostrarInformacion();
}

