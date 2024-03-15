package es.unican.polaflix_rodrigo_fdez.polaflix_rodrigo_fdez.domain;

import java.util.ArrayList;
import java.util.List;

public class Serie {

    private String nombre;

    private String sinopsis;

    private List<String> creadores;

    private List<String> actores;

    private List<Temporada> temporadas;

    public Serie(String nombre, String sinopsis) {
        this.nombre = nombre;
        this.sinopsis = sinopsis;
        this.creadores = new ArrayList<>();
        this.actores = new ArrayList<>();
        this.temporadas = new ArrayList<>();
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
