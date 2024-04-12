package es.unican.polaflix_rodrigo_fdez.polaflix_rodrigo_fdez.domain;

import java.util.List;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Entity
public class Temporada implements Comparable<Temporada> {

    private final int numTemporada;

    private Serie serie;

    private List<Capitulo> capitulos;

    @Override
    public int compareTo(Temporada otraTemporada) {
        return Integer.compare(this.numTemporada, otraTemporada.numTemporada);
    }
}
