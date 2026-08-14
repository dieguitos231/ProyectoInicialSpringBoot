package co.edu.sena.inventario.models;

public class Producto {
    private final Long id;
    private final String nombre;
    private final double precio;
    private final Integer cantidad;
    public Producto(Long id, String nombre, double precio, int cantidad) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.cantidad = cantidad;
    }
    public Long getId() {return id;}
    public String getNombre() {return nombre;}
    public double getPrecio() {return precio;}
    public Integer getCantidad() {return cantidad;}
}

