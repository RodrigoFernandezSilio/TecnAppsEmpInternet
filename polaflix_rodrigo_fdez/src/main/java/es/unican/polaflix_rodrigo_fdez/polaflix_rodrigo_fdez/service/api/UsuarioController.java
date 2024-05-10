package es.unican.polaflix_rodrigo_fdez.polaflix_rodrigo_fdez.service.api;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
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
import es.unican.polaflix_rodrigo_fdez.polaflix_rodrigo_fdez.dto.TemporadaCapituloDTO;
import es.unican.polaflix_rodrigo_fdez.polaflix_rodrigo_fdez.dto.UsuarioSerieDTO;
import es.unican.polaflix_rodrigo_fdez.polaflix_rodrigo_fdez.repositories.SerieRepository;
import es.unican.polaflix_rodrigo_fdez.polaflix_rodrigo_fdez.repositories.UsuarioRepository;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    UsuarioRepository ur;

    @Autowired
    SerieRepository sr;

    @GetMapping("/{usuarioID}")
    @JsonView(Views.UsuarioInicio.class)
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

    @PatchMapping("/{usuarioID}/series/{serieID}")
    @Transactional
    public ResponseEntity<String> anotarCapituloComoReproducido(
            @PathVariable("usuarioID") String usuarioID,
            @PathVariable("serieID") Long serieID,
            @RequestBody TemporadaCapituloDTO temporadaCapituloDTO) {

        Optional<Usuario> usuarioOptional = ur.findById(usuarioID);
        Optional<Serie> serieOptional = sr.findById(serieID);

        if (usuarioOptional.isPresent()) {
            if (serieOptional.isPresent()) {
                Usuario usuario = usuarioOptional.get();
                Serie serie = serieOptional.get();

                Temporada temporada = serie.getTemporadas().get(temporadaCapituloDTO.getNumTemporada()-1);
                Capitulo capitulo = temporada.getCapitulos().get(temporadaCapituloDTO.getNumCapitulo()-1);


                System.out.println(capitulo.getTitulo() + capitulo.getNumCapitulo());
                usuario.anotarCapituloComoReproducido(capitulo);

                return ResponseEntity.ok("Capítulo anotado como reproducido.");
            } else {
                return ResponseEntity.notFound().build();
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/{usuarioID}/series")
    @Transactional
    public ResponseEntity<String> agregarSerieAEspacioPersonal(
            @PathVariable("usuarioID") String usuarioID,
            @RequestBody Long serieID) {

        Optional<Usuario> usuarioOptional = ur.findById(usuarioID);
        Optional<Serie> serieOptional = sr.findById(serieID);

        if (usuarioOptional.isPresent()) {
            if (serieOptional.isPresent()) {
                Usuario usuario = usuarioOptional.get();
                Serie serie = serieOptional.get();

                usuario.agregarSerieEspacioPersonal(serie); // Llama al metodo agregarSerieEspacioPersonal

                return ResponseEntity.ok("Serie agregada al espacio personal del usuario.");
            } else {
                return ResponseEntity.notFound().build();
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{usuarioID}/facturas")
    @JsonView(Views.Factura_Vista.class)
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
