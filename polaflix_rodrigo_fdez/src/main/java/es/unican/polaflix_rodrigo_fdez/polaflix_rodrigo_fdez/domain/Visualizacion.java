package es.unican.polaflix_rodrigo_fdez.polaflix_rodrigo_fdez.domain;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@RequiredArgsConstructor
@EqualsAndHashCode
@ToString
@Entity
public class Visualizacion {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;

    @NonNull
    private final Date fecha;

    @NonNull
    private final Float precio;

    @NonNull
    @ManyToOne
    private final Serie serie;

    @NonNull
    private final Integer numTemporada;

    @NonNull
    private final Integer numCapitulo;    
}