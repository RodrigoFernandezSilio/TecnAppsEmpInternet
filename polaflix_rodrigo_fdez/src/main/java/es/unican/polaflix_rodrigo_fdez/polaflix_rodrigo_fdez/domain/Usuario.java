package es.unican.polaflix_rodrigo_fdez.polaflix_rodrigo_fdez.domain;

import java.util.List;

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
public class Usuario {

    private String nombreUsuario;

    private String contrasenha;

    private List<Serie> seriesPendientes;
    private List<Serie> seriesEmpezadas;
    private List<Serie> seriesTerminadas;

    private List<CapituloVisto> capitulosVistos;

    private List<Factura> facturas;
}
