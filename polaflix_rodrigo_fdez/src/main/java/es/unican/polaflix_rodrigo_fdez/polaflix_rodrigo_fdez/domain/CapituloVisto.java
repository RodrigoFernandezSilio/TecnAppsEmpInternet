package es.unican.polaflix_rodrigo_fdez.polaflix_rodrigo_fdez.domain;

public class CapituloVisto {

    private Serie serie;

    private int numTemporada;

    private int numCapitulo;

    public CapituloVisto(Serie serie, int numTemporada, int numCapitulo) {
        this.serie = serie;
        this.numTemporada = numTemporada;
        this.numCapitulo = numCapitulo;
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
