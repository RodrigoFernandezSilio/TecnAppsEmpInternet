package es.unican.polaflix_rodrigo_fdez.polaflix_rodrigo_fdez.dto;

import com.fasterxml.jackson.annotation.JsonView;

import es.unican.polaflix_rodrigo_fdez.polaflix_rodrigo_fdez.domain.CapituloVisto;
import es.unican.polaflix_rodrigo_fdez.polaflix_rodrigo_fdez.domain.ConjuntoCapituloVisto;
import es.unican.polaflix_rodrigo_fdez.polaflix_rodrigo_fdez.domain.Serie;
import es.unican.polaflix_rodrigo_fdez.polaflix_rodrigo_fdez.service.api.Views.UsuarioSerieDTO_Vista;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class UsuarioSerieDTO {

    @JsonView(UsuarioSerieDTO_Vista.class)
    private Serie serie;

    @JsonView(UsuarioSerieDTO_Vista.class)
    private ConjuntoCapituloVisto conjuntoCapituloVisto;

    @JsonView(UsuarioSerieDTO_Vista.class)
    private CapituloVisto ultimoCapituloVisto;
}
