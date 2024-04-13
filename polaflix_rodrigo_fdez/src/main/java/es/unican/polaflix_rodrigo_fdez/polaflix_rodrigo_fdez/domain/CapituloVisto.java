package es.unican.polaflix_rodrigo_fdez.polaflix_rodrigo_fdez.domain;

import jakarta.persistence.Embeddable;
import jakarta.persistence.ManyToOne;
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
@Embeddable
public class CapituloVisto {

    @ManyToOne
    private Serie serie;

    private int numTemporada;

    private int numCapitulo;
}
