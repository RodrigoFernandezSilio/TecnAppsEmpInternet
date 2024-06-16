package es.unican.polaflix_rodrigo_fdez.polaflix_rodrigo_fdez.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import es.unican.polaflix_rodrigo_fdez.polaflix_rodrigo_fdez.domain.Persona;

public interface PersonaRepository extends JpaRepository<Persona, Long> {
    
}
