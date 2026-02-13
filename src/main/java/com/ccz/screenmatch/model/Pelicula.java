package com.ccz.screenmatch.model;

import com.google.gson.annotations.SerializedName;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "peliculas")
public class Pelicula extends Titulo {
    @SerializedName("BoxOffice")
    @Column(name = "box_office")
    private String boxOffice;
}

