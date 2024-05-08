package es.unican.polaflix_rodrigo_fdez.polaflix_rodrigo_fdez.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import es.unican.polaflix_rodrigo_fdez.polaflix_rodrigo_fdez.domain.Serie;


public interface SerieRepository extends JpaRepository<Serie, Long>{

    List<Serie> findByNombre(String nombre);

    List<Serie> findByNombreStartingWith(char letraInicial);
}
