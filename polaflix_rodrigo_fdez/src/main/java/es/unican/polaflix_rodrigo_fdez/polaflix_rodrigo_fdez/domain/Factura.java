package es.unican.polaflix_rodrigo_fdez.polaflix_rodrigo_fdez.domain;

import java.util.Date;

public class Factura {

    private Date fecha;

    private float precio;

    private Capitulo capitulo;

    public Factura(Date fecha, float precio, Capitulo capitulo) {
        this.fecha = fecha;
        this.precio = precio;
        this.capitulo = capitulo;
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

    public Capitulo getCapitulo() {
        return capitulo;
    }

    public void setCapitulo(Capitulo capitulo) {
        this.capitulo = capitulo;
    }
}
