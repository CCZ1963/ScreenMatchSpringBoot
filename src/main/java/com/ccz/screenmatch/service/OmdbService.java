package com.ccz.screenmatch.service;

import com.ccz.screenmatch.dto.ResultadosBusqueda;
import com.ccz.screenmatch.model.Titulo;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
public class OmdbService {

    private static final String API_KEY = "aa2f1ec7";
    private static final String URL_BASE = "http://www.omdbapi.com/?";

    private final Gson gson;

    public OmdbService() {
        this.gson = new GsonBuilder()
                .registerTypeAdapter(Titulo.class, new TituloDeserializador())
                .create();
    }

    public Titulo buscarPorTitulo(String titulo) {
        try {
            String url = URL_BASE + "t=" + titulo.replace(" ", "+") + "&apikey=" + API_KEY;
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.body().contains("\"Response\":\"False\"")) {
                return null;
            }

            // Gson mapeará automáticamente todos los campos, incluyendo ratings (solo para la respuesta JSON)
            return gson.fromJson(response.body(), Titulo.class);

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return null;
        }
    }

    public ResultadosBusqueda buscarPorTermino(String termino) {
        try {
            String url = URL_BASE + "s=" + termino.replace(" ", "+") + "&apikey=" + API_KEY;
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.body().contains("\"Response\":\"False\"")) {
                return null;
            }

            Gson gsonSimple = new Gson();
            return gsonSimple.fromJson(response.body(), ResultadosBusqueda.class);

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return null;
        }
    }
}