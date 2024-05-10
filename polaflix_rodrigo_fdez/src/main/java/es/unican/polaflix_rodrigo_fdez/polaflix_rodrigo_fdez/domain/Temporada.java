package es.unican.polaflix_rodrigo_fdez.polaflix_rodrigo_fdez.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonView;

import es.unican.polaflix_rodrigo_fdez.polaflix_rodrigo_fdez.service.api.Views;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
public class Temporada implements Comparable<Temporada> {

    @Id
    @JsonView({Views.UsuarioSerieDTO_Vista.class})
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;

    @NonNull
    @JsonView({Views.UsuarioSerieDTO_Vista.class})
    private Integer numTemporada;

    @NonNull
    @ManyToOne
    private Serie serie;

    @NonNull
    @JsonView({Views.UsuarioSerieDTO_Vista.class})
    @OneToMany(mappedBy = "temporada", cascade = CascadeType.ALL)
    private List<Capitulo> capitulos;

    @Override
    public int compareTo(Temporada otraTemporada) {
        return Integer.compare(this.numTemporada, otraTemporada.numTemporada);
    }
}
