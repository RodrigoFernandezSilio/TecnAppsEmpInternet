package es.unican.polaflix_rodrigo_fdez.polaflix_rodrigo_fdez.domain;

public class Capitulo {
    
    private String titulo;

    private String descripcion;

    private int numCapitulo;

    private Temporada temporada;

    public Capitulo(String titulo, String descripcion, int numCapitulo, Temporada temporada) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.numCapitulo = numCapitulo;
        this.temporada = temporada;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getNumCapitulo() {
        return numCapitulo;
    }

    public void setNumCapitulo(int numCapitulo) {
        this.numCapitulo = numCapitulo;
    }

    public Temporada getTemporada() {
        return temporada;
    }

    public void setTemporada(Temporada temporada) {
        this.temporada = temporada;
    }
}


