package com.ccz.screenmatch.dto;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class ResultadosBusqueda {
    @SerializedName("Search")
    private List<TituloResumen> resultados;

    @SerializedName("totalResults")
    private String totalResultados;

    // getter
    public List<TituloResumen> getResultados() {
        return resultados;
    }
}

