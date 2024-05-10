package es.unican.polaflix_rodrigo_fdez.polaflix_rodrigo_fdez.domain;

import java.util.Date;

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
import lombok.ToString;

@Getter
@NoArgsConstructor
@RequiredArgsConstructor
@EqualsAndHashCode
@ToString
@Entity
public class Visualizacion {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;

    @NonNull
        @JsonView(Views.Factura_Vista.class)
    private Date fecha;

    @NonNull
    @JsonView(Views.Factura_Vista.class)
    private Float precio;

    @NonNull
    @ManyToOne
    @JsonView(Views.Factura_Vista.class)
    private Serie serie;

    @NonNull
    @JsonView(Views.Factura_Vista.class)
    private Integer numTemporada;

    @NonNull
    @JsonView(Views.Factura_Vista.class)
    private Integer numCapitulo;    
}