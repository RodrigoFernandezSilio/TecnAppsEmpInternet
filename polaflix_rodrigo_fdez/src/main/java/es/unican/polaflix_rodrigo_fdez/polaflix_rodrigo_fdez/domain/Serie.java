package es.unican.polaflix_rodrigo_fdez.polaflix_rodrigo_fdez.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonView;

import es.unican.polaflix_rodrigo_fdez.polaflix_rodrigo_fdez.service.api.Views;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@EqualsAndHashCode
@ToString
@Entity
public class Serie {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;

    @NonNull
    @JsonView({Views.DescripcionSerie.class})
    private String nombre;

    @NonNull
    @JsonView({Views.DescripcionSerie.class})
    private String sinopsis;

    @NonNull
    @Enumerated(EnumType.ORDINAL)
    @JsonView({Views.DescripcionSerie.class})
    private CategoriaSerie categoriaSerie;

    @NonNull
    @ManyToMany(cascade = CascadeType.PERSIST)
    @JsonView({Views.DescripcionSerie.class})
    private List<Persona> creadores;

    @NonNull
    @ManyToMany(cascade = CascadeType.PERSIST)
    @JsonView({Views.DescripcionSerie.class})
    private List<Persona> actores;

    @NonNull
    @OneToMany(mappedBy = "serie", cascade = CascadeType.ALL)
    @JsonView({Views.DescripcionSerie.class})
    private List<Temporada> temporadas;
}
