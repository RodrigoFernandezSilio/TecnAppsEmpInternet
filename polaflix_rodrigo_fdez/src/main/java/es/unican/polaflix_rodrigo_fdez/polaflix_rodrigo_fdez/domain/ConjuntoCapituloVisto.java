package es.unican.polaflix_rodrigo_fdez.polaflix_rodrigo_fdez.domain;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonView;

import es.unican.polaflix_rodrigo_fdez.polaflix_rodrigo_fdez.service.api.Views;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@RequiredArgsConstructor
@EqualsAndHashCode
@ToString
@Entity
public class ConjuntoCapituloVisto {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NonNull
    @OneToMany(cascade = CascadeType.ALL)
    @JsonView(Views.UsuarioSerieDTO_Vista.class)
    private Set<CapituloVisto> capituloVistos;
}
