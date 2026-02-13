package com.ccz.screenmatch.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "busquedas")
public class Busqueda {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "titulo_buscado")
    private String tituloBuscado;

    @Column(name = "tipo_resultado") // "Pelicula" o "Serie"
    private String tipoResultado;

    @Column(name = "fecha_busqueda")
    private LocalDateTime fechaBusqueda = LocalDateTime.now();

    // Relación opcional con Titulo (si quieres guardar más datos)
    @Column(name = "imdb_id")
    private String imdbId;
}

