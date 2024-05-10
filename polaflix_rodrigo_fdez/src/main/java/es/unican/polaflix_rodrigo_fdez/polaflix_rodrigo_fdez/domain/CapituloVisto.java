package es.unican.polaflix_rodrigo_fdez.polaflix_rodrigo_fdez.domain;

import com.fasterxml.jackson.annotation.JsonView;

import es.unican.polaflix_rodrigo_fdez.polaflix_rodrigo_fdez.service.api.Views;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@RequiredArgsConstructor
@EqualsAndHashCode
@ToString
@Entity
public class CapituloVisto {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NonNull
    @JsonView(Views.UsuarioSerieDTO_Vista.class)
    private Integer numTemporada;

    @NonNull
    @JsonView(Views.UsuarioSerieDTO_Vista.class)
    private Integer numCapitulo;
}
