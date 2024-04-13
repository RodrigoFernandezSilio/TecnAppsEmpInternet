package es.unican.polaflix_rodrigo_fdez.polaflix_rodrigo_fdez.domain;

import java.util.Date;

import jakarta.persistence.Embeddable;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Embeddable
public class Visualizacion {

    private final Date fecha;

    private final float precio;

    @ManyToOne
    private final Serie serie;

    private final int numTemporada;

    private final int numCapitulo;    
}