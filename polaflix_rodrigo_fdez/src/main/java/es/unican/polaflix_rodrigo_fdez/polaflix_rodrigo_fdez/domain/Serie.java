package es.unican.polaflix_rodrigo_fdez.polaflix_rodrigo_fdez.domain;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
@Entity
public class Serie {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private final long id;

    @NonNull
    private String nombre;

    @NonNull
    private String sinopsis;

    @NonNull
    @Enumerated(EnumType.ORDINAL)
    private CategoriaSerie categoriaSerie;

    @NonNull
    private List<String> creadores;

    @NonNull
    private List<String> actores;

    @NonNull
    @OneToMany(mappedBy = "serie")
    private List<Temporada> temporadas;
}
