package es.unican.polaflix_rodrigo_fdez.polaflix_rodrigo_fdez.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import es.unican.polaflix_rodrigo_fdez.polaflix_rodrigo_fdez.domain.Capitulo;
import es.unican.polaflix_rodrigo_fdez.polaflix_rodrigo_fdez.domain.CategoriaSerie;
import es.unican.polaflix_rodrigo_fdez.polaflix_rodrigo_fdez.domain.Persona;
import es.unican.polaflix_rodrigo_fdez.polaflix_rodrigo_fdez.domain.Serie;
import es.unican.polaflix_rodrigo_fdez.polaflix_rodrigo_fdez.domain.Temporada;
import es.unican.polaflix_rodrigo_fdez.polaflix_rodrigo_fdez.domain.TipoUsuario;
import es.unican.polaflix_rodrigo_fdez.polaflix_rodrigo_fdez.domain.Usuario;
import es.unican.polaflix_rodrigo_fdez.polaflix_rodrigo_fdez.repositories.SerieRepository;
import es.unican.polaflix_rodrigo_fdez.polaflix_rodrigo_fdez.repositories.UsuarioRepository;

@Component
public class AppFeeder implements CommandLineRunner {

	@Autowired
	protected UsuarioRepository ur;
	@Autowired
	protected SerieRepository sr;

	@Override
	@Transactional
	public void run(String... args) throws Exception {
		feedUsuarios();
		feedSeries();
		addSeriesToPersonalSpace();
		markChapterAsPlayed();

		System.out.println("Application feeded");
	}

	private void feedUsuarios() {
		Usuario u1 = new Usuario("usuario1", "contrasenha1", TipoUsuario.ESTANDAR,
			new HashSet<>(), new HashSet<>(), new HashSet<>(), new HashMap<>(), new HashMap<>(), new ArrayList<>());

		Usuario u2 = new Usuario("usuario2", "contrasenha2", TipoUsuario.ESTANDAR,
			new HashSet<>(), new HashSet<>(), new HashSet<>(), new HashMap<>(), new HashMap<>(), new ArrayList<>());

		Usuario u3 = new Usuario("usuario3", "contrasenha3", TipoUsuario.ESTANDAR,
			new HashSet<>(), new HashSet<>(), new HashSet<>(), new HashMap<>(), new HashMap<>(), new ArrayList<>());


		ur.save(u1);
		ur.save(u2);
		ur.save(u3);
	}

	private void feedSeries() {
		/* Los Serrano */
		Persona creador1 = new Persona("Daniel", "Ecija", "Bernal");
		Persona creador2 = new Persona("Alex", "Pina", "Calafi");
		Persona actor1 = new Persona("Antonio", "Fernández", "Resines");
		Persona actor2 = new Persona("Belen", "Rueda", "García");

		CategoriaSerie estandar = new CategoriaSerie("ESTANDAR", 0.50f);		
		CategoriaSerie silver = new CategoriaSerie("SILVER", 0.75f);
		CategoriaSerie gold = new CategoriaSerie("GOLD", 1.50f);

		Serie s1 = new Serie("Los Serrano", "Comedia familiar sobre una familia peculiar", estandar,
			new HashSet<>(Arrays.asList(creador1, creador2)), new HashSet<>(Arrays.asList(actor1, actor2)), new ArrayList<>());

		Temporada t11 = new Temporada(1, s1, new ArrayList<>());
		Temporada t12 = new Temporada(2, s1, new ArrayList<>());

		Capitulo c111 = new Capitulo("El despertar de los Serrano", "Los Serrano se enfrentan a un nuevo día lleno de sorpresas y desafíos.", 1, t11);
		Capitulo c112 = new Capitulo("El secreto revelado", "Un secreto guardado durante mucho tiempo finalmente sale a la luz.", 2, t11);
		Capitulo c121 = new Capitulo("Amor en tiempos difíciles", "Los lazos familiares se ponen a prueba.", 1, t12);
		Capitulo c122 = new Capitulo("Un nuevo comienzo", "Los Serrano se preparan para dejar atrás el pasado y empezar de nuevo.", 2, t12);

		t11.getCapitulos().add(c111);
		t11.getCapitulos().add(c112);
		t12.getCapitulos().add(c121);
		t12.getCapitulos().add(c122);

		s1.getTemporadas().add(t11);
		s1.getTemporadas().add(t12);

		/* Breaking Bad */
		creador1 = new Persona("Vince", "Gilligan", "");
		actor1 = new Persona("Bryan", "Cranston", "");
		actor2 = new Persona("Aaron", "Paul", "");

		Serie s2 = new Serie("Breaking Bad", "Un profesor de quimica se convierte en fabricante de metanfetamina",
			silver, new HashSet<>(Arrays.asList(creador1)), new HashSet<>(Arrays.asList(actor1, actor2)), new ArrayList<>());

		Temporada t21 = new Temporada(1, s2, new ArrayList<>());
		Temporada t22 = new Temporada(2, s2, new ArrayList<>());
		
		Capitulo c211 = new Capitulo("Piloto", "Un profesor de química con cáncer decide entrar en el mundo de la fabricación de drogas.", 1, t21);
		Capitulo c212 = new Capitulo("Cat's in the Bag...", "Walter y Jesse enfrentan las consecuencias de sus acciones en el primer episodio.", 2, t21);
		Capitulo c221 = new Capitulo("Seven Thirty-Seven", "Walter y Jesse lidian con el impacto emocional y físico de su nueva vida.", 1, t22);
		Capitulo c222 = new Capitulo("Grilled", "Los problemas de Walter y Jesse se vuelven más intensos cuando se enfrentan a un peligroso enemigo.", 2, t22);
		
		t21.getCapitulos().add(c211);
		t21.getCapitulos().add(c212);
		t22.getCapitulos().add(c221);
		t22.getCapitulos().add(c222);

		s2.getTemporadas().add(t21);
		s2.getTemporadas().add(t22);
		

		/* Juego de Tronos */
		creador1 = new Persona("David",  "Benioff", "");
		creador2 = new Persona("Daniel Brett", "Weiss", "");
		actor1 = new Persona("Emilia", "Clarke", "");
		actor2 = new Persona("Kit", "Harington", "");

		Serie s3 = new Serie("Juego de Tronos", "Drama y fantasia medieval", gold,
			new HashSet<>(Arrays.asList(creador1, creador2)), new HashSet<>(Arrays.asList(actor1, actor2)), new ArrayList<>());

		Temporada t31 = new Temporada(1, s3, new ArrayList<>());
		Temporada t32 = new Temporada(2, s3, new ArrayList<>());
		
		Capitulo c311 = new Capitulo("Se acerca el invierno", "Los Stark de Invernalia reciben noticias alarmantes del Sur.", 1, t31);
		Capitulo c312 = new Capitulo("El camino real", "Los diferentes personajes comienzan sus viajes hacia Desembarco del Rey.", 2, t31);
		Capitulo c321 = new Capitulo("Las tierras de la noche", "La Guardia de la Noche se enfrenta a peligros más allá del Muro.", 1, t32);
		Capitulo c322 = new Capitulo("El hombre sin honor", "Las intrigas políticas y las traiciones se intensifican en los Siete Reinos.", 2, t32);
		
		t31.getCapitulos().add(c311);
		t31.getCapitulos().add(c312);
		t32.getCapitulos().add(c321);
		t32.getCapitulos().add(c322);
		
		s3.getTemporadas().add(t31);
		s3.getTemporadas().add(t32);
		

		sr.save(s1);
		sr.save(s2);
		sr.save(s3);
	}

	private void addSeriesToPersonalSpace() {
		Usuario u1 = ur.findById("usuario1").get();
		
		Serie s1 = sr.findByNombre("Los Serrano").get(0);
		Serie s2 = sr.findByNombre("Breaking Bad").get(0);
		Serie s3 = sr.findByNombre("Juego de Tronos").get(0);

		u1.agregarSerieEspacioPersonal(s1);
		u1.agregarSerieEspacioPersonal(s2);
		u1.agregarSerieEspacioPersonal(s3);
	}

	private void markChapterAsPlayed() {
		Usuario u1 = ur.findById("usuario1").get();
		Serie s1 = sr.findByNombre("Los Serrano").get(0);
		Serie s2 = sr.findByNombre("Breaking Bad").get(0);

		Temporada t11 = s1.getTemporadas().get(0); // Primera temporada de Los Serrano
		Capitulo c111 = t11.getCapitulos().get(0); // Primer capitulo de la primera temporada
		Capitulo c112 = t11.getCapitulos().get(1); // Segundo capitulo de la primera temporada


		Temporada t22 = s2.getTemporadas().get(1); // Segunda temorada de Breaking Bad
		Capitulo c222 = t22.getCapitulos().get(1); // Segundo capitulo de la segunda temporada (ultimo capitulo de la serie)

		// Para comprobar que al ver un capitulo de una serie esta pasa a empezadas
		// y para comprobar que en ultimosCapitulosVisto" se guarda el capítulo c112 porque es el último lógicamente, aunque no sea el último visto en orden temporal
		u1.anotarCapituloComoReproducido(c112);
		u1.anotarCapituloComoReproducido(c111);

		// Para comorbar que al ver el ultimo capitulo de una serie esta pasa a terminadas
		u1.anotarCapituloComoReproducido(c222);
	}
}