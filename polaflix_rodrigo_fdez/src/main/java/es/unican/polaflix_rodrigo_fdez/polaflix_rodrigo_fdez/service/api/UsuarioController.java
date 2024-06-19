package es.unican.polaflix_rodrigo_fdez.polaflix_rodrigo_fdez.service.api;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

import es.unican.polaflix_rodrigo_fdez.polaflix_rodrigo_fdez.domain.Capitulo;
import es.unican.polaflix_rodrigo_fdez.polaflix_rodrigo_fdez.domain.CapituloVisto;
import es.unican.polaflix_rodrigo_fdez.polaflix_rodrigo_fdez.domain.ConjuntoCapituloVisto;
import es.unican.polaflix_rodrigo_fdez.polaflix_rodrigo_fdez.domain.Factura;
import es.unican.polaflix_rodrigo_fdez.polaflix_rodrigo_fdez.domain.Serie;
import es.unican.polaflix_rodrigo_fdez.polaflix_rodrigo_fdez.domain.Temporada;
import es.unican.polaflix_rodrigo_fdez.polaflix_rodrigo_fdez.domain.Usuario;
import es.unican.polaflix_rodrigo_fdez.polaflix_rodrigo_fdez.dto.UsuarioSerieDTO;
import es.unican.polaflix_rodrigo_fdez.polaflix_rodrigo_fdez.repositories.SerieRepository;
import es.unican.polaflix_rodrigo_fdez.polaflix_rodrigo_fdez.repositories.UsuarioRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/usuarios")
@Tag(name = "UsuarioController", description = "Controlador para gestionar operaciones relacionadas con usuarios, como la obtención de detalles de usuario y la gestión de sus datos relacionados con series y visualización de contenido.")
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600) // Permitir solicitudes desde http://localhost:4200
public class UsuarioController {

    @Autowired
    UsuarioRepository ur;

    @Autowired
    SerieRepository sr;

    @GetMapping("/{usuarioID}")
    @JsonView(Views.UsuarioInicio.class)
    @Operation(summary = "Obtener usuario por ID", description = "Devuelve los detalles de un usuario específico por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuario encontrado"),
            @ApiResponse(responseCode = "404", description = "Usuario no encontrado", content = @Content)
    })
    public ResponseEntity<Usuario> obtenerUsuario(@PathVariable String usuarioID) {
        Optional<Usuario> usuario = ur.findById(usuarioID);
        if (usuario.isPresent()) {
            return ResponseEntity.ok(usuario.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{usuarioID}/series/{serieID}")
    @JsonView(Views.UsuarioSerieDTO_Vista.class)
    @Operation(summary = "Obtener información de una serie por usuario", description = "Devuelve la información de una serie específica para un usuario específico")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Información de la serie encontrada"),
            @ApiResponse(responseCode = "404", description = "Usuario o serie no encontrados", content = @Content)
    })
    public ResponseEntity<UsuarioSerieDTO> obtenerInformacionSeriePorUsuario(
            @PathVariable("usuarioID") String usuarioID,
            @PathVariable("serieID") Long serieID) {

        Optional<Usuario> usuarioOptional = ur.findById(usuarioID);
        Optional<Serie> serieOptional = sr.findById(serieID);

        if (usuarioOptional.isPresent()) {
            Usuario usuario = usuarioOptional.get();
            if (serieOptional.isPresent()) {
                Serie serie = serieOptional.get();

                ConjuntoCapituloVisto capitulosVistos = usuario.getCapitulosVistosPorSerie().get(serie);
                CapituloVisto ultimCapituloVisto = usuario.getUltimoCapituloVistoPorSerie().get(serie);

                UsuarioSerieDTO usuarioSerieDTO = new UsuarioSerieDTO(serie, capitulosVistos, ultimCapituloVisto);
                return ResponseEntity.ok(usuarioSerieDTO);
            } else {
                return ResponseEntity.notFound().build();
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{usuarioID}/series/{serieID}/temporadas/{numTemporada}/capitulos/{numCapitulo}")
    @Transactional
    @Operation(summary = "Anotar capítulo como reproducido", description = "Marca un capítulo como reproducido para un usuario específico")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Capítulo anotado como reproducido", content = @Content),
            @ApiResponse(responseCode = "404", description = "Usuario o serie no encontrados", content = @Content)
    })
    public ResponseEntity<String> anotarCapituloComoReproducido(
            @PathVariable("usuarioID") String usuarioID,
            @PathVariable("serieID") Long serieID,
            @PathVariable("numTemporada") int numTemporada,
            @PathVariable("numCapitulo") int numCapitulo) {

        Optional<Usuario> usuarioOptional = ur.findById(usuarioID);
        Optional<Serie> serieOptional = sr.findById(serieID);

        if (usuarioOptional.isPresent()) {
            if (serieOptional.isPresent()) {
                Usuario usuario = usuarioOptional.get();
                Serie serie = serieOptional.get();

                Temporada temporada = serie.getTemporadas().get(numTemporada - 1);
                Capitulo capitulo = temporada.getCapitulos().get(numCapitulo - 1);

                usuario.anotarCapituloComoReproducido(capitulo);

                return ResponseEntity.ok("Capítulo anotado como reproducido");
            } else {
                return ResponseEntity.notFound().build();
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{usuarioID}/series/{serieID}")
    @Transactional
    @Operation(summary = "Agregar serie al espacio personal", description = "Agrega una serie al espacio personal del usuario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Serie agregada al espacio personal del usuario", content = @Content),
            @ApiResponse(responseCode = "404", description = "Usuario o serie no encontrados", content = @Content)
    })
    public ResponseEntity<Void> agregarSerieAEspacioPersonal(
            @PathVariable("usuarioID") String usuarioID,
            @PathVariable("serieID") Long serieID) {

        Optional<Usuario> usuarioOptional = ur.findById(usuarioID);
        Optional<Serie> serieOptional = sr.findById(serieID);

        if (usuarioOptional.isPresent()) {
            if (serieOptional.isPresent()) {
                Usuario usuario = usuarioOptional.get();
                Serie serie = serieOptional.get();

                usuario.agregarSerieEspacioPersonal(serie); // Llama al metodo agregarSerieEspacioPersonal

                return ResponseEntity.noContent().build();
            } else {
                return ResponseEntity.notFound().build();
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{usuarioID}/facturas")
    @JsonView(Views.Factura_Vista.class)
    @Operation(summary = "Obtener factura por año y mes", description = "Devuelve la factura de un usuario específico para un año y mes determinados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Factura encontrada"),
            @ApiResponse(responseCode = "404", description = "Usuario no encontrado", content = @Content)
    })
    public ResponseEntity<Factura> obtenerFacturasPorAnhoYMes(
            @PathVariable("usuarioID") String usuarioID,
            @RequestParam(required = true) int anho,
            @RequestParam(required = true) int mes) {

        Optional<Usuario> usuarioOptional = ur.findById(usuarioID);

        if (usuarioOptional.isPresent()) {
            Usuario usuario = usuarioOptional.get();
            Factura factura = usuario.obteneFacturaPorAnhoYMes(anho, mes);
            return ResponseEntity.ok(factura);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
