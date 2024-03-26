package es.unican.polaflix_rodrigo_fdez.polaflix_rodrigo_fdez.domain;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;

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

    private Set<Serie> seriesPendientes;
    private Set<Serie> seriesEmpezadas;
    private Set<Serie> seriesTerminadas;

    private List<CapituloVisto> capitulosVistos;

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
        
        // Crear una nueva entrada de CapituloVisto
        CapituloVisto nuevoCapituloVisto = new CapituloVisto(serie, temporada.getNumTemporada(), capitulo.getNumCapitulo());
        // Agregar la nueva entrada a la lista de capítulos vistos del usuario
        capitulosVistos.add(nuevoCapituloVisto);

        // Verificar si la serie a la que pertenece el capitulo esta en pendientes
        if (seriesPendientes.contains(serie)) {
            // Si la serie esta en pendientes, se pasa a la lista de empezadas
            seriesPendientes.remove(serie);
            seriesEmpezadas.add(serie);
        }

        // Verificar si el capitulo es el ultimo de la serie
        if (serie.getTemporadas().size() - 1 == temporada.getNumTemporada()) {
            if (temporada.getCapitulos().size() - 1 == capitulo.getNumCapitulo()) {
                seriesEmpezadas.remove(serie);
                seriesTerminadas.add(serie);
            }
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
            facturas.add(new Factura(anhoActual, mesActual, new ArrayList<>()));
        }

        // Obtener la ultima factura (puede ser la recien creada)
        ultimaFactura = facturas.get(facturas.size() - 1);
        // Agregar la visualizacion a la ultima factura
        ultimaFactura.getVisualizaciones().add(visualizacion);
    }
}
