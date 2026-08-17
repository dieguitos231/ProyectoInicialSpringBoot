package co.edu.sena.inventario.controller;

import co.edu.sena.inventario.models.Municipio;
import co.edu.sena.inventario.models.Producto;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController

public class MunicipioController {
    private final List<Municipio> municipios=new ArrayList<>(
            List.of(
                new Municipio("ibague","Capital del departamento del tolima"),
                new Municipio("honda","Ubicado en el norte del Tolima"),
                new Municipio("mariquita","Destacado por su clima calido de 26°")
            )
    );

    @GetMapping("/municipios")
    public List<Municipio> listarMunicipios() {
         return municipios;
    }

    @GetMapping("/municipios/{nombre}")
    public Municipio buscarMunicipio(@PathVariable String nombre) {
        for (Municipio municipio : municipios) {
            if (municipio.getNombre().equals(nombre.toLowerCase())) {
                return municipio;
            }
        }
        return null;
    }

    @PostMapping("/municipios")
    public Municipio newMunicipio(@RequestBody Municipio newMunicipio){
        municipios.add(new Municipio(newMunicipio.getNombre(),newMunicipio.getDescripcion()));
        return newMunicipio;
    }

    @PutMapping("/municipios/{nombre}")
    public Municipio putMunicipio(@PathVariable String nombre, @RequestBody Municipio modMunicipio){
        if(nombre != null && !nombre.isEmpty()){
            for (Municipio municipio: municipios){
                if(municipio.getNombre().equals(nombre.toLowerCase())){
                    municipio.setNombre(modMunicipio.getNombre());
                    municipio.setDescripcion(modMunicipio.getDescripcion());
                    return municipio;
                }
            }
        }
        return null;
    }

    @DeleteMapping("/municipios/{nombre}")
    public Municipio deleteMunicipio(@PathVariable String nombre){
        for (Municipio municipio: municipios){
            if(municipio.getNombre().equals(nombre.toLowerCase())){
                municipios.remove(municipio);
                return municipio;
            }
        }
        return null;
    }
}
