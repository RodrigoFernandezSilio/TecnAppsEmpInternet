package es.unican.polaflix_rodrigo_fdez.polaflix_rodrigo_fdez.service.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

import es.unican.polaflix_rodrigo_fdez.polaflix_rodrigo_fdez.domain.Serie;
import es.unican.polaflix_rodrigo_fdez.polaflix_rodrigo_fdez.repositories.SerieRepository;

@RestController
@RequestMapping("/series")
public class SerieController {

    @Autowired
    protected SerieRepository sr;

    @GetMapping
    @JsonView(Views.SerieResumen.class)
    public ResponseEntity<List<Serie>> obtenerSeriesPorLetraInicial(
            @RequestParam(required = true) char letraInicial) {

        return ResponseEntity.ok(sr.findByNombreStartingWith(letraInicial));
    }

}
