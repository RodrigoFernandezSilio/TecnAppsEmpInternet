package es.unican.polaflix_rodrigo_fdez.polaflix_rodrigo_fdez.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

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
import es.unican.polaflix_rodrigo_fdez.polaflix_rodrigo_fdez.repositories.PersonaRepository;
import es.unican.polaflix_rodrigo_fdez.polaflix_rodrigo_fdez.repositories.SerieRepository;
import es.unican.polaflix_rodrigo_fdez.polaflix_rodrigo_fdez.repositories.UsuarioRepository;

@Component
public class AppFeeder implements CommandLineRunner {

	@Autowired
	protected UsuarioRepository ur;
	@Autowired
	protected SerieRepository sr;
	@Autowired
	protected PersonaRepository pr;

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
		CategoriaSerie estandar = new CategoriaSerie("ESTANDAR", 0.50f);		
		CategoriaSerie silver = new CategoriaSerie("SILVER", 0.75f);
		CategoriaSerie gold = new CategoriaSerie("GOLD", 1.50f);

		/* Los Serrano */
		Persona creador1 = new Persona("Daniel", "Ecija", "Bernal");
		Persona creador2 = new Persona("Alex", "Pina", "Calafi");
		Persona actor1 = new Persona("Antonio", "Fernández", "Resines");
		Persona actor2 = new Persona("Belen", "Rueda", "García");
		guardarPersona(Arrays.asList(creador1, creador2, actor1, actor2));

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
		guardarPersona(Arrays.asList(creador1, actor1, actor2));
		

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
		guardarPersona(Arrays.asList(creador1, creador2, actor1, actor2));

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


		/* 24 */
		creador1 = new Persona("Joel", "Surnow", "");
		actor1 = new Persona("Kiefer", "Sutherland", "");
		guardarPersona(Arrays.asList(creador1, actor1));

		Serie s4 = new Serie("24", "Thriller de acción y drama político", estandar,
			new HashSet<>(Arrays.asList(creador1)), new HashSet<>(Arrays.asList(actor1)), new ArrayList<>());

		Temporada t41 = new Temporada(1, s4, new ArrayList<>());
		Temporada t42 = new Temporada(2, s4, new ArrayList<>());

		Capitulo c411 = new Capitulo("12:00 a.m. - 1:00 a.m.", "Inicio del día más largo de Jack Bauer", 1, t41);
		Capitulo c412 = new Capitulo("1:00 a.m. - 2:00 a.m.", "Jack intenta evitar un ataque terrorista", 2, t41);
		Capitulo c421 = new Capitulo("8:00 a.m. - 9:00 a.m.", "Bauer debe enfrentarse a una nueva amenaza", 1, t42);
		Capitulo c422 = new Capitulo("9:00 a.m. - 10:00 a.m.", "La situación se complica para Jack y su equipo", 2, t42);

		t41.getCapitulos().add(c411);
		t41.getCapitulos().add(c412);
		t42.getCapitulos().add(c421);
		t42.getCapitulos().add(c422);

		s4.getTemporadas().add(t41);
		s4.getTemporadas().add(t42);


		/* 13 Reasons Why */
		creador1 = new Persona("Brian", "Yorkey", "");
		actor1 = new Persona("Dylan", "Minnette", "");
		actor2 = new Persona("Katherine", "Langford", "");
		guardarPersona(Arrays.asList(creador1, actor1, actor2));

		Serie s5 = new Serie("13 Reasons Why", "Un adolescente recibe una serie de cintas de una compañera de clase que se suicidó.", estandar,
        new HashSet<>(Arrays.asList(creador1)), new HashSet<>(Arrays.asList(actor1, actor2)), new ArrayList<>());

		Temporada t51 = new Temporada(1, s5, new ArrayList<>());
		Temporada t52 = new Temporada(2, s5, new ArrayList<>());

		Capitulo c511 = new Capitulo("Tape 1, Side A", "Clay Jensen escucha la primera cinta de Hannah Baker.", 1, t51);
		Capitulo c512 = new Capitulo("Tape 1, Side B", "La historia de Hannah continúa revelando secretos.", 2, t51);
		Capitulo c521 = new Capitulo("Tape 2, Side A", "Clay descubre más sobre la vida de Hannah.", 1, t52);
		Capitulo c522 = new Capitulo("Tape 2, Side B", "Las cintas empiezan a afectar la vida de Clay.", 2, t52);

		t51.getCapitulos().add(c511);
		t51.getCapitulos().add(c512);
		t52.getCapitulos().add(c521);
		t52.getCapitulos().add(c522);

		s5.getTemporadas().add(t51);
		s5.getTemporadas().add(t52);


		/* Los Simpson */
		creador1 = new Persona("Matt", "Groening", "");
		actor1 = new Persona("Dan", "Castellaneta", "");
		actor2 = new Persona("Nancy", "Cartwright", "");
		guardarPersona(Arrays.asList(creador1, actor1, actor2));
	
		Serie s6 = new Serie("Los Simpson", "Una familia disfuncional vive en una ciudad ficticia llamada Springfield.", 
			silver, new HashSet<>(Arrays.asList(creador1)), new HashSet<>(Arrays.asList(actor1, actor2)), new ArrayList<>());
	
		Temporada t61 = new Temporada(1, s6, new ArrayList<>());
		Temporada t62 = new Temporada(2, s6, new ArrayList<>());
	
		Capitulo c611 = new Capitulo("Simpsons Roasting on an Open Fire", "Homer descubre que no recibirá el bono de Navidad.", 1, t61);
		Capitulo c612 = new Capitulo("Bart the Genius", "Bart hace trampa en una prueba de inteligencia.", 2, t61);
		Capitulo c621 = new Capitulo("Bart Gets an F", "Bart trata de aprobar un examen para evitar repetir el curso.", 1, t62);
		Capitulo c622 = new Capitulo("Simpson and Delilah", "Homer usa un producto milagroso para hacer crecer su cabello.", 2, t62);
	
		t61.getCapitulos().add(c611);
		t61.getCapitulos().add(c612);
		t62.getCapitulos().add(c621);
		t62.getCapitulos().add(c622);
	
		s6.getTemporadas().add(t61);
		s6.getTemporadas().add(t62);


		/* Lost */
		creador1 = new Persona("J.J.", "Abrams", "");
		creador2 = new Persona("Damon", "Lindelof", "");
		actor1 = new Persona("Matthew", "Fox", "");
		actor2 = new Persona("Evangeline", "Lilly", "");
		guardarPersona(Arrays.asList(creador1, creador2, actor1, actor2));
	
		Serie s7 = new Serie("Lost", "Un grupo de supervivientes de un accidente aéreo están varados en una misteriosa isla tropical.", 
			silver, new HashSet<>(Arrays.asList(creador1, creador2)), new HashSet<>(Arrays.asList(actor1, actor2)), new ArrayList<>());
	
		Temporada t71 = new Temporada(1, s7, new ArrayList<>());
		Temporada t72 = new Temporada(2, s7, new ArrayList<>());
	
		Capitulo c711 = new Capitulo("Piloto", "Los supervivientes del vuelo 815 de Oceanic Airlines se estrellan en una isla.", 1, t71);
		Capitulo c712 = new Capitulo("Tabula Rasa", "Los supervivientes intentan adaptarse a la vida en la isla.", 2, t71);
		Capitulo c721 = new Capitulo("Man of Science, Man of Faith", "Jack comienza a explorar la misteriosa escotilla.", 1, t72);
		Capitulo c722 = new Capitulo("Adrift", "Michael y Sawyer enfrentan peligros en el mar.", 2, t72);
	
		t71.getCapitulos().add(c711);
		t71.getCapitulos().add(c712);
		t72.getCapitulos().add(c721);
		t72.getCapitulos().add(c722);
	
		s7.getTemporadas().add(t71);
		s7.getTemporadas().add(t72);


		/* Aquí no hay quien viva */
		creador1 = new Persona("Laura", "Caballero", "");
		creador2 = new Persona("Alberto", "Caballero", "");
		actor1 = new Persona("José", "Luis", "Gil");
		actor2 = new Persona("Fernando", "Tejero", "");
		guardarPersona(Arrays.asList(creador1, creador2, actor1, actor2));
	
		Serie s8 = new Serie("Aquí no hay quien viva", "Comedia sobre la vida en una comunidad de vecinos en un edificio del centro de Madrid.", 
			estandar, new HashSet<>(Arrays.asList(creador1, creador2)), new HashSet<>(Arrays.asList(actor1, actor2)), new ArrayList<>());
	
		Temporada t81 = new Temporada(1, s8, new ArrayList<>());
		Temporada t82 = new Temporada(2, s8, new ArrayList<>());
	
		Capitulo c811 = new Capitulo("Érase una mudanza", "Los nuevos inquilinos llegan al edificio y se enfrentan a sus peculiares vecinos.", 1, t81);
		Capitulo c812 = new Capitulo("Érase una boda", "El edificio entero se prepara para una boda, pero nada sale como estaba planeado.", 2, t81);
		Capitulo c821 = new Capitulo("Érase una reforma", "Las reformas en el edificio causan caos y confusión entre los vecinos.", 1, t82);
		Capitulo c822 = new Capitulo("Érase un reencuentro", "Un antiguo inquilino regresa y desata viejas tensiones y nuevas intrigas.", 2, t82);
	
		t81.getCapitulos().add(c811);
		t81.getCapitulos().add(c812);
		t82.getCapitulos().add(c821);
		t82.getCapitulos().add(c822);
	
		s8.getTemporadas().add(t81);
		s8.getTemporadas().add(t82);


		sr.save(s1); // Guardar la serie "Los Serrano" en el repositorio
		sr.save(s2); // Guardar la serie "Breaking Bad" en el repositorio
		sr.save(s3); // Guardar la serie "Juego de Tronos" en el repositorio
		sr.save(s4); // Guardar la serie "24" en el repositorio
		sr.save(s5); // Guardar la serie "13 Reasons Why" en el repositorio
		sr.save(s6); // Guardar la serie "Los Simpson" en el repositorio
		sr.save(s7); // Guardar la serie "Lost" en el repositorio
		sr.save(s8); // Guardar la serie "Aquí no hay quien viva" en el repositorio
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

	private void guardarPersona(List<Persona> listaPersona) {
		for (Persona persona: listaPersona) {
			pr.save(persona);
		}
	}
}