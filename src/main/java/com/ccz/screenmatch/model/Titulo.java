package com.ccz.screenmatch.model;

import com.google.gson.annotations.SerializedName;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@MappedSuperclass
public abstract class Titulo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @SerializedName("Title")
    @Column(name = "title")
    private String title;

    @SerializedName("Year")
    @Column(name = "year")
    private String year;

    @SerializedName("Released")
    @Column(name = "released")
    private String released;

    @SerializedName("Runtime")
    @Column(name = "runtime")
    private String runtime;

    @SerializedName("Genre")
    @Column(name = "genre")
    private String genre;

    @SerializedName("Director")
    @Column(name = "director")
    private String director;

    @SerializedName("Writer")
    @Column(name = "writer")
    private String writer;

    @SerializedName("Actors")
    @Column(name = "actors")
    private String actors;

    @SerializedName("Language")
    @Column(name = "language")
    private String language;

    @SerializedName("Country")
    @Column(name = "country")
    private String country;

    @SerializedName("imdbRating")
    @Column(name = "imdb_rating")
    private String imdbRating;

    @SerializedName("Type")
    @Column(name = "type")
    private String type;

    @SerializedName("imdbID")
    @Column(name = "imdb_id")
    private String imdbId;
}

