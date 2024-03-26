package es.unican.polaflix_rodrigo_fdez.polaflix_rodrigo_fdez.domain;

public enum CategoriaSerie {
    ESTANDAR(0.5f),
    SILVER(0.75f),
    GOLD(1.5f);

    private final float precio;

    CategoriaSerie(float precio) {
        this.precio = precio;
    }

    public float getPrecio() {
        return precio;
    }
}