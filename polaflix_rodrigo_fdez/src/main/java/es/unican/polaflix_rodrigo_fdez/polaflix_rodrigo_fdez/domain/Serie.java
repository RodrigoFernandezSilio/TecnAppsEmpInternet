package es.unican.polaflix_rodrigo_fdez.polaflix_rodrigo_fdez.domain;

import java.util.List;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@RequiredArgsConstructor
@EqualsAndHashCode
@ToString
public class Serie {

    private final int id;

    @NonNull
    private String nombre;

    @NonNull
    private String sinopsis;

    @NonNull
    private CategoriaSerie categoriaSerie;

    @NonNull
    private List<String> creadores;

    @NonNull
    private List<String> actores;

    @NonNull
    private List<Temporada> temporadas;
}
