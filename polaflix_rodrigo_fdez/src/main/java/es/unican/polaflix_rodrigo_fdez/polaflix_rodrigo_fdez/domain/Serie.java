package es.unican.polaflix_rodrigo_fdez.polaflix_rodrigo_fdez.domain;

import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonView;

import es.unican.polaflix_rodrigo_fdez.polaflix_rodrigo_fdez.service.api.Views;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
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
    @JsonView({Views.UsuarioInicio.class, Views.UsuarioSerieDTO_Vista.class, Views.SerieResumen.class})
    private long id;

    @NonNull
    @JsonView({Views.UsuarioInicio.class, Views.UsuarioSerieDTO_Vista.class, Views.SerieResumen.class, Views.Factura_Vista.class})
    private String nombre;

    @NonNull
    @JsonView({Views.SerieResumen.class})
    private String sinopsis;

    @NonNull
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JsonView({Views.UsuarioSerieDTO_Vista.class})
    private CategoriaSerie categoriaSerie;

    @NonNull
    @ManyToMany(cascade = CascadeType.PERSIST)
    @JsonView({Views.SerieResumen.class})
    private Set<Persona> creadores;

    @NonNull
    @ManyToMany(cascade = CascadeType.PERSIST)
    @JsonView({Views.SerieResumen.class})
    private Set<Persona> actores;

    @NonNull
    @OneToMany(mappedBy = "serie", cascade = CascadeType.ALL)
    @JsonView({Views.UsuarioSerieDTO_Vista.class})
    private List<Temporada> temporadas;
}
