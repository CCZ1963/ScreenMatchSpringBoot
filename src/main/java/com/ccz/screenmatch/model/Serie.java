package com.ccz.screenmatch.model;

import com.google.gson.annotations.SerializedName;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "series")
public class Serie extends Titulo {
    @SerializedName("totalSeasons")
    @Column(name = "total_seasons")
    private String totalSeasons;
}

