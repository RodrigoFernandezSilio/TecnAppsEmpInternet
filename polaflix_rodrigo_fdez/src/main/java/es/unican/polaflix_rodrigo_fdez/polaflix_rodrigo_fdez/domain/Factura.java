package es.unican.polaflix_rodrigo_fdez.polaflix_rodrigo_fdez.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonView;

import es.unican.polaflix_rodrigo_fdez.polaflix_rodrigo_fdez.service.api.Views;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
public class Factura {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;

    @NonNull
    @JsonView(Views.Factura_Vista.class)
    private Integer anho;

    @NonNull
    @JsonView(Views.Factura_Vista.class)
    private Integer mes;

    @NonNull
    @OneToMany(cascade = CascadeType.ALL)
    @JsonView(Views.Factura_Vista.class)
    private List<Visualizacion> visualizaciones;

    @NonNull
    @Enumerated(EnumType.ORDINAL)
    private TipoFactura tipoFactura;

    @JsonView(Views.Factura_Vista.class)
    private Float precioTotal = 0f;
}
