package com.ccz.screenmatch.controller;

import com.ccz.screenmatch.dto.ResultadosBusqueda;
import com.ccz.screenmatch.model.Busqueda;
import com.ccz.screenmatch.model.Pelicula;
import com.ccz.screenmatch.model.Serie;
import com.ccz.screenmatch.model.Titulo;
import com.ccz.screenmatch.repository.BusquedaRepository;
import com.ccz.screenmatch.repository.PeliculaRepository;
import com.ccz.screenmatch.repository.SerieRepository;
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

    @Autowired
    private PeliculaRepository peliculaRepository;

    @Autowired
    private SerieRepository serieRepository;

    @GetMapping("/buscar")
    public Titulo buscar(@RequestParam String titulo) {
        Titulo resultado = omdbService.buscarPorTitulo(titulo);

        if (resultado != null) {
            // 1. IMPORTANTE: Vincular cada Rating con su Titulo padre
            // Esto es necesario para que JPA guarde la llave foránea correctamente.
            if (resultado.getRatings() != null) {
                resultado.getRatings().forEach(r -> r.setTitulo(resultado));
            }

            // 2. Guardar el objeto completo en su tabla respectiva
            if (resultado instanceof Pelicula) {
                peliculaRepository.save((Pelicula) resultado);
            } else if (resultado instanceof Serie) {
                serieRepository.save((Serie) resultado);
            }

            // 3. Guardar en el historial de búsquedas (tu lógica original)
            Busqueda busqueda = new Busqueda();
            busqueda.setTituloBuscado(titulo);
            busqueda.setTipoResultado(resultado instanceof Pelicula ? "Pelicula" : "Serie");
            busqueda.setImdbId(resultado.getImdbId());
            busquedaRepository.save(busqueda);

            return resultado;
        }
        return null;
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

