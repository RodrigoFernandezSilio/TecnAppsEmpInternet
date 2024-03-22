package es.unican.polaflix_rodrigo_fdez.polaflix_rodrigo_fdez.domain;

import java.util.Date;

public class Factura {

    private Date fecha;

    private float precio;

    private Serie serie;

    private int numTemporada;

    private int numCapitulo;

    public Factura(Date fecha, float precio, Serie serie, int numTemporada, int numCapitulo) {
        this.fecha = fecha;
        this.precio = precio;
        this.serie = serie;
        this.numTemporada = numTemporada;
        this.numCapitulo = numCapitulo;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public Serie getSerie() {
        return serie;
    }

    public void setSerie(Serie serie) {
        this.serie = serie;
    }

    public int getNumTemporada() {
        return numTemporada;
    }

    public void setNumTemporada(int numTemporada) {
        this.numTemporada = numTemporada;
    }

    public int getNumCapitulo() {
        return numCapitulo;
    }

    public void setNumCapitulo(int numCapitulo) {
        this.numCapitulo = numCapitulo;
    }

    
}