package es.unican.polaflix_rodrigo_fdez.polaflix_rodrigo_fdez.domain;

import java.util.Calendar;
import java.util.Date;
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

    /**
     * Anhade una visualización a la factura y actualiza el precio total.
     *
     * @param capitulo  El capitulo que esta siendo visualizado.
     */
    public void anhadirVisualizacion(Capitulo capitulo) {
        Temporada temporada = capitulo.getTemporada();
        Serie serie = temporada.getSerie();

        Date fechaActual = Calendar.getInstance().getTime();

        Visualizacion visualizacion = new Visualizacion(
            fechaActual, serie.getCategoriaSerie().getPrecio(), serie, temporada.getNumTemporada(), capitulo.getNumCapitulo());

        // Agregar la visualizacion a la factura
        visualizaciones.add(visualizacion);

        // Verificar si la factura es fija
        if (tipoFactura == TipoFactura.FIJA) {
            // Si la factura es fija, se establece un precio total fijo de 20 euros
            precioTotal = 20f;
        } else {
            // Si la factura es variable, se agrega el precio del ultimo capitulo visto al precio total de la factura
            precioTotal += visualizacion.getPrecio();
        }
    }
}
