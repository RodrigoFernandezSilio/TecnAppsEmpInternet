package es.unican.polaflix_rodrigo_fdez.polaflix_rodrigo_fdez.domain;

import com.fasterxml.jackson.annotation.JsonView;

import es.unican.polaflix_rodrigo_fdez.polaflix_rodrigo_fdez.service.api.Views;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
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
public class Capitulo implements Comparable<Capitulo> {

    @Id
    @JsonView({Views.UsuarioSerieDTO_Vista.class})
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;
    
    @NonNull
    @JsonView({Views.UsuarioSerieDTO_Vista.class})
    private String titulo;

    @NonNull
    @JsonView({Views.UsuarioSerieDTO_Vista.class})
    private String descripcion;

    @NonNull
    @JsonView({Views.UsuarioSerieDTO_Vista.class})
    private Integer numCapitulo;

    @NonNull
    @ManyToOne
    private Temporada temporada;

    @Override
    public int compareTo(Capitulo otroCapitulo) {
        return Integer.compare(this.numCapitulo, otroCapitulo.numCapitulo);
    }
}


