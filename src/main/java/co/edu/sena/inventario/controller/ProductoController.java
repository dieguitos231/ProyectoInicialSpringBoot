package co.edu.sena.inventario.controller;

import co.edu.sena.inventario.models.Municipio;
import co.edu.sena.inventario.models.Producto;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
public class ProductoController {
    private final List<Producto> productos = new ArrayList<>(
            List.of(
                    new Producto(1L, "Papa Pastusa", 2500.00, 10),
                    new Producto(2L, "Tomate", 2500.00, 20),
                    new Producto(3L, "Fresa", 2500.00, 50)
            )
    );

    @GetMapping("/productos")
    public List<Producto> listarProductos(
            @RequestParam(required = false) String nombre,
            @RequestParam(required = false) Double precio,
            @RequestParam(required = false) Integer cantidad) {

        if (nombre == null && precio == null && cantidad == null) {
            return productos;
        }

        List<Producto> productosFiltrados = new ArrayList<>();
        for (Producto producto : productos) {
            boolean coincideNombre = (nombre == null || nombre.isEmpty()) ||
                    producto.getNombre().toLowerCase().contains(nombre.toLowerCase());

            boolean coincidePrecio = (precio == null) ||
                    producto.getPrecio() == precio;

            boolean coincideCantidad = (cantidad == null) ||
                    producto.getCantidad().equals(cantidad);

            if (coincideNombre && coincidePrecio && coincideCantidad) {
                productosFiltrados.add(producto);
            }
        }
        return productosFiltrados;
    }

    @GetMapping("/productos/{id}")
    public Producto buscarProducto(@PathVariable Long id) {
        for (Producto producto : productos) {
            if (producto.getId().equals(id)) {
                return producto;
            }
        }
        return null;
    }

    @PostMapping("/productos")
    public Producto guardarProducto(@RequestBody Producto producto) {
        Producto nuevoProducto = new Producto(
                producto.getId(),
                producto.getNombre(),
                producto.getPrecio(),
                producto.getCantidad()
        );
        productos.add(nuevoProducto);
        return producto;
    }

    @PutMapping("/productos/{id}")
    public Producto actualizarProducto(@PathVariable Long id, @RequestBody Producto modProducto) {
        if(id != null) {
            for (Producto actProducto : productos) {
                if(actProducto.getId().equals(id)) {
                    actProducto.setNombre(modProducto.getNombre());
                    actProducto.setPrecio(modProducto.getPrecio());
                    actProducto.setCantidad(modProducto.getCantidad());
                    return actProducto;
                }
            }
        }
        return null;
    }

    @DeleteMapping("/productos/{id}")
    public Producto deleteProducto(@PathVariable Long id) {
        if (id != null) {
            for (Producto producto : productos) {
                if (producto.getId().equals(id)) {
                    productos.remove(producto);
                    return producto;
                }
            }
        }
        return null;
    }
    //Metodo registrar una venta
    @PostMapping("/productos/{id}/vender")
    public String registrarVenta(@PathVariable Long id, @RequestParam Integer cantidad) {
        if (cantidad == null || cantidad <= 0) {
            return "Error: La cantidad a vender debe ser mayor a cero.";
        }

        for (Producto producto : productos) {
            if (producto.getId().equals(id)) {
                if (producto.getCantidad() >= cantidad) {
                    producto.setCantidad(producto.getCantidad() - cantidad);
                    return "Venta registrada con éxito. Nuevo stock de " + producto.getNombre() + ": " + producto.getCantidad();
                } else {
                    return "Error: Stock insuficiente. Solo quedan " + producto.getCantidad() + " unidades de " + producto.getNombre();
                }
            }
        }
        return "Error: Producto no encontrado.";
    }
}
