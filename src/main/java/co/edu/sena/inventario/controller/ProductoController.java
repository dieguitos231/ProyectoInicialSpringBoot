package co.edu.sena.inventario.controller;

import co.edu.sena.inventario.models.Producto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class ProductoController {
    private final List<Producto> productos = List.of(
            new Producto(1L, "Papa Pastusa", 2500.00, 50),
            new Producto(2L, "Tomate", 2500.00, 50),
            new Producto(3L, "Fresa", 2500.00, 50)
    );

    @GetMapping("/productos")
    public List<Producto> listarProductos() {
        return productos;
    }
    @GetMapping("/productos/{id}")
    public Producto buscarProducto(@PathVariable Long id) {
        for (Producto producto : productos) {
            if(producto.getId().equals(id)) {
                return producto;
            }
        }
        return null;
    }


}
