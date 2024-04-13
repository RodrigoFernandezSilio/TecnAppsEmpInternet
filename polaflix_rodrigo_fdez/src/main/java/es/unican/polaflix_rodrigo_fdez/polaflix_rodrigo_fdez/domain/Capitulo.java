package es.unican.polaflix_rodrigo_fdez.polaflix_rodrigo_fdez.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
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
public class Capitulo implements Comparable<Capitulo> {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;
    
    @NonNull
    private String titulo;

    @NonNull
    private String descripcion;

    @NonNull
    private final Integer numCapitulo;

    @NonNull
    @ManyToOne
    private Temporada temporada;

    @Override
    public int compareTo(Capitulo otroCapitulo) {
        return Integer.compare(this.numCapitulo, otroCapitulo.numCapitulo);
    }
}


