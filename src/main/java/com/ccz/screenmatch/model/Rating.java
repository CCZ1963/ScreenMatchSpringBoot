package com.ccz.screenmatch.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.gson.annotations.SerializedName;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "ratings")
@Data
public class Rating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @SerializedName("Source")
    private String source;

    @SerializedName("Value")
    private String value;

    @ManyToOne
    @JoinColumn(name = "titulo_id")
    @JsonIgnore
    private Titulo titulo;

    // El campo 'titulo' es necesario para la relación bidireccional.
    // Como Titulo es abstracta, JPA usará la FK hacia la tabla específica (pelicula o serie).
}
