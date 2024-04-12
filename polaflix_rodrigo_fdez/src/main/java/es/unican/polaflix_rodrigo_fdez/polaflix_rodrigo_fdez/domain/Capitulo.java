package es.unican.polaflix_rodrigo_fdez.polaflix_rodrigo_fdez.domain;

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
public class Capitulo implements Comparable<Capitulo> {
    
    private String titulo;

    private String descripcion;

    private final int numCapitulo;

    private Temporada temporada;

    @Override
    public int compareTo(Capitulo otroCapitulo) {
        return Integer.compare(this.numCapitulo, otroCapitulo.numCapitulo);
    }
}


