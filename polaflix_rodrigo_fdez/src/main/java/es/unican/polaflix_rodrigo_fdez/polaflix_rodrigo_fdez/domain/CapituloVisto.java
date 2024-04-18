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
public class CapituloVisto {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;
    
    @NonNull
    @ManyToOne
    private Serie serie;

    @NonNull
    private Integer numTemporada;

    @NonNull
    private Integer numCapitulo;
}
