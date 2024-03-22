package es.unican.polaflix_rodrigo_fdez.polaflix_rodrigo_fdez.domain;

import java.util.ArrayList;
import java.util.List;

public class Serie {

    private int id;

    private String nombre;

    private String sinopsis;

    private List<String> creadores;

    private List<String> actores;

    private List<Temporada> temporadas;

    public Serie(int id, String nombre, String sinopsis, List<String> creadores, List<String> actores) {
        this.id = id;
        this.nombre = nombre;
        this.sinopsis = sinopsis;
        this.creadores = creadores;
        this.actores = actores;
        this.temporadas = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getSinopsis() {
        return sinopsis;
    }

    public void setSinopsis(String sinopsis) {
        this.sinopsis = sinopsis;
    }

    public List<String> getCreadores() {
        return creadores;
    }

    public void setCreadores(List<String> creadores) {
        this.creadores = creadores;
    }

    public List<String> getActores() {
        return actores;
    }

    public void setActores(List<String> actores) {
        this.actores = actores;
    }

    public List<Temporada> getTemporadas() {
        return temporadas;
    }

    public void setTemporadas(List<Temporada> temporadas) {
        this.temporadas = temporadas;
    }
}
