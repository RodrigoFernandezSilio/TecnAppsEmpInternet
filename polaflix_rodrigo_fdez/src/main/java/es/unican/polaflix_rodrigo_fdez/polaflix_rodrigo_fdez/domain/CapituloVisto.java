package es.unican.polaflix_rodrigo_fdez.polaflix_rodrigo_fdez.domain;

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
public class CapituloVisto {

    private Serie serie;

    private int numTemporada;

    private int numCapitulo;
}
