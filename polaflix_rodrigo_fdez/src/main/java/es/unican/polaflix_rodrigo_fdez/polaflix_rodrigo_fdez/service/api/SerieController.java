package es.unican.polaflix_rodrigo_fdez.polaflix_rodrigo_fdez.service.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

import es.unican.polaflix_rodrigo_fdez.polaflix_rodrigo_fdez.domain.Serie;
import es.unican.polaflix_rodrigo_fdez.polaflix_rodrigo_fdez.repositories.SerieRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/series")
@Tag(name = "SerieController", description = "Controlador para manejar operaciones relacionadas con series, como la búsqueda y recuperación de información sobre series.")
@CrossOrigin(origins = "http://localhost:4200", maxAge=3600) // Permitir solicitudes desde http://localhost:4200
public class SerieController {

    @Autowired
    protected SerieRepository sr;

    @GetMapping
    @JsonView(Views.SerieResumen.class)
    @Operation(summary = "Buscar series por letra inicial o nombre", description = "Si se proporciona un nombre, se realiza una búsqueda exacta y, si tiene éxito, la página mostrará el listado de la inicial que corresponda con la serie encontrada. Si la búsqueda por nombre no encuentra resultados, se devuelve un error. Si no se proporciona un nombre, se buscan las series cuyos nombres comienzan con la letra inicial especificada.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Series encontradas"),
            @ApiResponse(responseCode = "404", description = "Serie no encontrada", content = @Content)
    })
    public ResponseEntity<List<Serie>> obtenerSeries(
            @RequestParam(required = true) char letraInicial, // Parametro para buscar por letra inicial
            @RequestParam(required = false) String nombre) { // Parametro para buscar por nombre completo
        if (nombre != null) { // Si se proporciono un nombre para buscar
            if (sr.findByNombre(nombre).isEmpty()) { // Si la busqueda por nombre no devuelve resultados
                return ResponseEntity.notFound().build(); // Retorna una respuesta 404 (Not Found)
            } else { // Si la busqueda por nombre devuelve resultados
                return ResponseEntity.ok(sr.findByNombreStartingWith(letraInicial)); // Retorna las series por letra
                                                                                     // inicial
            }
        } else { // Si no se proporciono un nombre para buscar
            return ResponseEntity.ok(sr.findByNombreStartingWith(letraInicial)); // Retorna las series por letra inicial
        }
    }

    @GetMapping("/numero")
    @JsonView(Views.SerieResumen.class)
    @Operation(summary = "Buscar series cuyo nombre comienza con un número", description = "Buscar las series cuyos nombres comienzan con un número.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Series encontradas")
    })
    public ResponseEntity<List<Serie>> obtenerSeriesQueEmpiezanPorNumero() {
        return ResponseEntity.ok(sr.findByNombreStartingWithNumber());
    }

}
