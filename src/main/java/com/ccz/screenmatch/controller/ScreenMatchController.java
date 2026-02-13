package com.ccz.screenmatch.controller;

import com.ccz.screenmatch.dto.ResultadosBusqueda;
import com.ccz.screenmatch.model.Busqueda;
import com.ccz.screenmatch.model.Pelicula;
import com.ccz.screenmatch.model.Titulo;
import com.ccz.screenmatch.repository.BusquedaRepository;
import com.ccz.screenmatch.service.OmdbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ScreenMatchController {

    @Autowired
    private OmdbService omdbService;

    @Autowired
    private BusquedaRepository busquedaRepository;

    @GetMapping("/buscar")
    public Titulo buscar(@RequestParam String titulo) {
        Titulo resultado = omdbService.buscarPorTitulo(titulo);
        if (resultado != null) {
            // Guardar en historial
            Busqueda busqueda = new Busqueda();
            busqueda.setTituloBuscado(titulo);
            busqueda.setTipoResultado(resultado instanceof Pelicula ? "Pelicula" : "Serie");
            busqueda.setImdbId(resultado.getImdbId());

            // Guardar y obtener el ID
            Busqueda guardada = busquedaRepository.save(busqueda);
            System.out.println("ID de búsqueda: " + guardada.getId()); // opcional

            return resultado; // aún no incluye id, pero podrías crear un DTO si quieres
        }
        return resultado;
    }

    @GetMapping("/historial")
    public List<Busqueda> obtenerHistorial() {
        return busquedaRepository.findAll();
    }

    @GetMapping("/buscar-multiple")
    public ResultadosBusqueda buscarMultiple(@RequestParam String termino) {
        return omdbService.buscarPorTermino(termino);
    }
}
