package com.ccz.screenmatch.model;

import com.google.gson.annotations.SerializedName;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;

@Data
@Entity
// @Table(name = "peliculas")
@DiscriminatorValue("movie") // Esto identifica a las películas en la tabla única
public class Pelicula extends Titulo {
    @SerializedName("BoxOffice")
    @Column(name = "box_office")
    private String boxOffice;
}

