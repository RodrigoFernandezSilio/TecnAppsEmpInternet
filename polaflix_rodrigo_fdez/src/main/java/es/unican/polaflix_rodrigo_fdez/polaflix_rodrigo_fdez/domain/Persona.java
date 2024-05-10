package es.unican.polaflix_rodrigo_fdez.polaflix_rodrigo_fdez.domain;

import com.fasterxml.jackson.annotation.JsonView;

import es.unican.polaflix_rodrigo_fdez.polaflix_rodrigo_fdez.service.api.Views;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
public class Persona {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @NonNull
    @JsonView(Views.SerieResumen.class)
    private String nombre;

    @NonNull
    @JsonView(Views.SerieResumen.class)
    private String apellido1;
    
    @NonNull
    @JsonView(Views.SerieResumen.class)
    private String apellido2;
}
