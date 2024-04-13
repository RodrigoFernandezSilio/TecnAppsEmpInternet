package es.unican.polaflix_rodrigo_fdez.polaflix_rodrigo_fdez.domain;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
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
public class Temporada implements Comparable<Temporada> {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private final long id;

    @NonNull
    private final Integer numTemporada;

    @NonNull
    @ManyToOne
    private Serie serie;

    @NonNull
    @OneToMany(mappedBy = "temporada")
    private List<Capitulo> capitulos;

    @Override
    public int compareTo(Temporada otraTemporada) {
        return Integer.compare(this.numTemporada, otraTemporada.numTemporada);
    }
}
