package es.unican.polaflix_rodrigo_fdez.polaflix_rodrigo_fdez.domain;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Entity
public class Usuario {

    @Id
    private String nombreUsuario;

    private String contrasenha;

    @Enumerated(EnumType.ORDINAL)
    private TipoUsuario tipoUsuario;

    @ManyToMany
    private Set<Serie> seriesPendientes;

    @ManyToMany
    private Set<Serie> seriesEmpezadas;

    @ManyToMany
    private Set<Serie> seriesTerminadas;

    @OneToMany(cascade = CascadeType.ALL)
    private List<CapituloVisto> capitulosVistos;

    @OneToMany(cascade = CascadeType.ALL)
    private List<CapituloVisto> ultimosCapitulosVistos;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Factura> facturas;


    /**
     * Agrega una serie al espacio personal de un usuario.
     * La serie se anhade a series pendientes si no esta pendiente, empezada o terminada.
     * 
     * @param serie La serie que se va a agregar al espacio personal del usuario.
     */
    public void agregarSerieEspacioPersonal(Serie serie) {
        // Verificar si la serie no está ni en seriesPendientes, ni en seriesEmpezadas, ni en seriesTerminadas
        if (!seriesPendientes.contains(serie) && !seriesEmpezadas.contains(serie) && !seriesTerminadas.contains(serie)) {
            // Si la serie cumple con las condiciones, se agrega pendientes
            seriesPendientes.add(serie);
        } // Si ya esta en el espacio personal del usuario, no se realiza ninguna accion
    }


    public void anotarCapituloComoReproducido(Capitulo capitulo) {
        Temporada temporada = capitulo.getTemporada();
        Serie serie = temporada.getSerie();

        // Verifica si la serie no esta en pedientes, ni en empezadas ni en terminadas
        if (seriesPendientes.contains(serie) && seriesEmpezadas.contains(serie) && seriesTerminadas.contains(serie)) {
            // Si la serie no esta en pedientes, ni en empezadas ni en terminadas se termina el metodo
            return;
        }

        // Verificar si la serie a la que pertenece el capitulo esta en pendientes
        if (seriesPendientes.contains(serie)) {
            // Si la serie esta en pendientes, se pasa a la lista de empezadas
            seriesPendientes.remove(serie);
            seriesEmpezadas.add(serie);
        }

        // Verificar si el capitulo es el ultimo de la serie
        if (serie.getTemporadas().size() == temporada.getNumTemporada()) {
            if (temporada.getCapitulos().size() == capitulo.getNumCapitulo()) {
                seriesEmpezadas.remove(serie);
                seriesTerminadas.add(serie);
            }
        }
        
        // Crear una nueva entrada de CapituloVisto
        CapituloVisto nuevoCapituloVisto = new CapituloVisto(serie, temporada.getNumTemporada(), capitulo.getNumCapitulo());
        // Agregar la nueva entrada a la lista de capitulos vistos del usuario
        capitulosVistos.add(nuevoCapituloVisto);

        boolean existeCapituloVistoMismaSerie = false;
        for (CapituloVisto c : ultimosCapitulosVistos) {
            if (c.getSerie().equals(serie)) {
                existeCapituloVistoMismaSerie = true;
                // Verificar si el capitulo reproducido es posterior al ultimo capitulo visto
                if (temporada.getNumTemporada() > c.getNumTemporada() || 
                (temporada.getNumTemporada() == c.getNumTemporada() && capitulo.getNumCapitulo() > c.getNumCapitulo())) {
                    ultimosCapitulosVistos.remove(c);
                    ultimosCapitulosVistos.add(nuevoCapituloVisto);
                }
                break;
            }
        }
        // Verificar si no se encontro ningun capitulo de la misma serie en la lista
        if (!existeCapituloVistoMismaSerie) {
            // Agregar el nuevo capitulo a la lista de ultimos capitulos vistos
            ultimosCapitulosVistos.add(nuevoCapituloVisto);
        }

        Date fechaActual = Calendar.getInstance().getTime();
        Visualizacion visualizacion = new Visualizacion(
            fechaActual, serie.getCategoriaSerie().getPrecio(), serie, temporada.getNumTemporada(), capitulo.getNumCapitulo());
        
        Factura ultimaFactura = null;
        if (!facturas.isEmpty()) {
            ultimaFactura = facturas.get(facturas.size() - 1);
        }

        Calendar calendarioActual = Calendar.getInstance();
        int anhoActual = calendarioActual.get(Calendar.YEAR);
        int mesActual = calendarioActual.get(Calendar.MONTH);

        // Verificar si no hay una ultima factura o si la ultima factura no es para el mes actual
        if (ultimaFactura == null || ultimaFactura.getAnho() != anhoActual || ultimaFactura.getMes() != mesActual) {
            if (tipoUsuario == TipoUsuario.ESTANDAR) {
                // Si el usuario es estandar la factura es variable
                facturas.add(new Factura(anhoActual, mesActual, new ArrayList<>(), TipoFactura.VARIABLE));                
            } else if (tipoUsuario == TipoUsuario.GRAN_CONSUMIDOR) {
                // Si el usuario es gran consumidor la factura es fija
                facturas.add(new Factura(anhoActual, mesActual, new ArrayList<>(), TipoFactura.FIJA));
            }
        }

        // Obtener la ultima factura (puede ser la recien creada)
        ultimaFactura = facturas.get(facturas.size() - 1);
        // Agregar la visualizacion a la ultima factura
        ultimaFactura.getVisualizaciones().add(visualizacion);
        // Verificar si la factura es fija
        if (ultimaFactura.getTipoFactura() == TipoFactura.FIJA) {
            // Si la factura es fija, se establece un precio total fijo de 20 euros
            ultimaFactura.setPrecioTotal(20f);
        } else {
            // Si la factura es variable, se agrega el precio del ultimo capitulo visto al precio total de la factura
            ultimaFactura.setPrecioTotal(ultimaFactura.getPrecioTotal() + visualizacion.getPrecio());
        }
    }
}
