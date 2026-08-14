package co.edu.sena.inventario.models;

public class Municipio {
    private String nombre;
    private String descripcion;
    public  Municipio(String nombre, String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
    }
    public String getNombre() {
        return nombre;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public void setNombre(String nombre) {
        this.nombre=nombre;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion=descripcion;
    }
}
