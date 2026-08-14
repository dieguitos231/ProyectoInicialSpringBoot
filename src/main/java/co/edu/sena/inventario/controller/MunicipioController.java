package co.edu.sena.inventario.controller;

import co.edu.sena.inventario.models.Municipio;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController

public class MunicipioController {
    // Lista de municipios
    private final List<Municipio> municipios=new ArrayList<>(List.of(
            new Municipio("ibague","Capital del departamento del tolima"),
            new Municipio("honda","Ubicado en el norte del Tolima"),
            new Municipio("mariquita","Destacado por su clima calido de 26°")
    ));
    //Metodo para lista todos los municipios existentes
    @GetMapping("/municipios")
    public List<Municipio> listarMunicipios() {
        return municipios;
    }

    //Busqueda por nombre de municipio
    @GetMapping("/municipios/{nombre}")
    //Path Variable  extrae lo escrito en la url y sirve como parametro para buscar el municipio
    public Municipio buscarMunicipio(@PathVariable String nombre){
        // Se recorre la lista de municipios.
        for (Municipio municipio :municipios){
            //Condicion si el parametro coincide con el nombre de algun municipio se retorna
            if(municipio.getNombre().equals(nombre.toLowerCase())){
                return municipio;
            }
        }
        //Si no devolvemos un valor nulo
        return null;
    }
    @PostMapping("/municipios")
    public Municipio newMunicipio(@RequestBody Municipio newMunicipio){
        municipios.add(new Municipio(newMunicipio.getNombre(),newMunicipio.getDescripcion()));
        return newMunicipio;
    }
    @PutMapping("/municipios/{nombre}")
    public Municipio putMunicipio(@PathVariable String nombre, @RequestBody Municipio putMunicipio){
        for (Municipio municipio: municipios){
            if(municipio.getNombre().equals(nombre.toLowerCase())){
                municipio.setNombre(putMunicipio.getNombre().toLowerCase());
                municipio.setDescripcion(putMunicipio.getDescripcion().toLowerCase());
                return municipio;
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
