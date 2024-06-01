package es.unican.polaflix_rodrigo_fdez.polaflix_rodrigo_fdez.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import es.unican.polaflix_rodrigo_fdez.polaflix_rodrigo_fdez.domain.Serie;


public interface SerieRepository extends JpaRepository<Serie, Long>{

    List<Serie> findByNombre(String nombre);

    List<Serie> findByNombreStartingWith(char charInicial);

    @Query(value = "SELECT * FROM Serie WHERE nombre REGEXP '^[0-9]'", nativeQuery = true)
    List<Serie> findByNombreStartingWithNumber();
}
