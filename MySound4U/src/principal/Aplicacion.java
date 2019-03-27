/**
* Clase Aplicacion
* @author Fernando Barroso, Javier Lozano y Gonzalo Madrigal
*/

package principal;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Esta clase contiene todos los atributos y metodos de la aplicacion
 */
public class Aplicacion implements Serializable {

	static final int REPRODUCCIONES_MAX = 200;
	static final int UMBRAL_PREMIUM = 10;

	static final String DIRECTORY = "songs";
	private final String PATH;

	private Usuario logueado;
	static java.util.Scanner sc;

	private int umbralPremium;
	private int limiteReproducciones;

	private ArrayList<Cancion> canciones;
	private Administrador admin;
	private Sesion sesion;
	private ArrayList<UsuarioRegistrado> usuarios;
	private ArrayList<Album> albumes;

	/** Lista de denuncias pendientes */
	private ArrayList<Denuncia> denuncias;

	/*
	 * Mapa de usuarios bloqueados (clave, valor) = (Usuario, Fecha de desbloqueo)
	 */
	private Map<UsuarioRegistrado, LocalDate> bloqueados;

	/** Lista de validaciones pendientes */
	private ArrayList<Validacion> validaciones;

	/**
	 * Este constructor genera una nueva sesion de administrador e inicializa todas
	 * las listas
	 * 
	 */
	public Aplicacion() {
		super();
		logueado = new UsuarioAnonimo();
		umbralPremium = 200;
		limiteReproducciones = 50;
		canciones = new ArrayList<>();
		usuarios = new ArrayList<>();
		validaciones = new ArrayList<>();
		denuncias = new ArrayList<>();
		albumes = new ArrayList<>();
		bloqueados = new HashMap<UsuarioRegistrado, LocalDate>();
		admin = new Administrador();
		sesion = logueado.iniciarSesion(this);
		PATH = getPath();

		/* Valores para probar el demostrador */
		UsuarioRegistrado sistema = new UsuarioRegistrado("SYSTEM", "1234", LocalDate.now());
		usuarios.add(sistema);
		UsuarioRegistrado avicii = new UsuarioRegistrado("AVICII", "1234", LocalDate.now());
		usuarios.add(avicii);
		Cancion c1 = new Cancion("CorePride", "UVERworld_CorePride.mp3", sistema);
		c1.validar();
		Cancion c2 = new Cancion("RookiezisPunk", "RookiezisPunk_d_InMyWorld.mp3", sistema);
		c2.marcarExplicita();
		c2.validar();
		Cancion c3 = new Cancion("Levels", "avicii-levels.mp3", avicii);
		c3.validar();
		canciones.add(c1);
		canciones.add(c2);
		canciones.add(c3);
		Denuncia d1 = new Denuncia(c2, avicii, "system me ha copiado la cancion, es un sinverguenza");
		Validacion v1 = new Validacion(c3, LocalDate.MAX);
		denuncias.add(d1);
		validaciones.add(v1);
	}

	/**
	 * Este metodo chequea las credenciales recibidas y si estas coinciden con las
	 * de algun usuario registrado, cierra la sesion actual e inicia la sesion del
	 * usuario solicitado
	 * 
	 * @param usuario    Nombre del usuario que se quiere comprobar
	 * @param contrasena Contraseña que se quiere comprobar
	 * @return Boolean Devuelve true si las credenciales son correctas, false en
	 *         caso contrario
	 */
	public Boolean loguearse(String usuario, String contrasena) {
		Boolean d = false;
		if (admin.validar(usuario, contrasena)) {
			logueado = admin;
			sesion = logueado.iniciarSesion(this);
			return true;
		}
		for (UsuarioRegistrado u : usuarios) {
			if (u.validar(usuario, contrasena)) {
				if (u.estaBloqueado()) {
					System.out.print("\nEl usuario " + u.getNombre()
							+ " no puede entrar al sistema. Esta bloqueado hasta el: " + bloqueados.get(u) + "\n\n");
					return false;
				}
				System.out.print("\nEl usuario " + u.getNombre() + " se ha logueado correctamente\n\n");
				logueado = u;
				sesion = logueado.iniciarSesion(this);
				return true;
			}
		}
		if (d == false)
			System.out.println("\nUsuario y/o contrasena erroneos\n");

		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		return d;
	}

	/**
	 * Este metodo devuelve la sesion actual
	 * 
	 * @return sesion Sesion actual
	 */
	public Sesion getSesion() {
		return sesion;
	}

	/**
	 * Este metodo solicita al usuario sus datos, los comprueba, genera un nuevo
	 * usuario y lo añade a la lista de usuarios de la aplicacion
	 * 
	 * @param nombre Nombre del usuario
	 * @param pass   Contrasena del usuario
	 * @param fecha  Fecha de nacimiento del usuario
	 */
	public void registrarse(String nombre, String pass, String fecha) {
		boolean encontrado = false;
		LocalDate d;
		DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy");

		for (UsuarioRegistrado u : usuarios) {
			if (nombre.equalsIgnoreCase(u.getNombre())) {
				encontrado = true;
				System.out.println("El nombre de usuario ya esta usado, pruebe otro distinto");
			}
		}

		try {
			d = LocalDate.parse(fecha, format);
			if (encontrado == false) {
				addUsuario(new UsuarioRegistrado(nombre, pass, d));
				printUsers();
			}
		} catch (DateTimeParseException e) {

		}
	}

	/**
	 * Metodo que cierra la sesion actual y abre una sesion anonima
	 * 
	 */
	public void desloguearse() {
		logueado = new UsuarioAnonimo();
		sesion = logueado.iniciarSesion(this);
	}

	/**
	 * Metodo que realiza todas las comprobaciones y cambios a final de mes
	 */
	public void cierreMes() {
	}

	/**
	 * Este metodo busca en la lista de canciones las canciones que cumplan ciertos
	 * requisitos y las añade a un array que devolvera posteriormente
	 * 
	 * @param busqueda String que contiene el texto a buscar
	 * @param tipo     Tipo de busqueda que se quiere realizar
	 * @return match ArrayList con las caciones que cumplan los requisitos
	 */
	public ArrayList<Cancion> buscar(String busqueda, TIPO_BUSQUEDA tipo) {
		ArrayList<Cancion> match = new ArrayList<Cancion>();
		for (Cancion c : canciones) {
			switch (tipo) {
			case ALBUM:
				if (c.getAlbum() != null && c.getAlbum().getNombre().toLowerCase().contains(busqueda.toLowerCase())
						&& !logueado.canListenSong(c))
					match.add(c);
				break;

			case AUTOR:
				if (c.getAutorNombre().toLowerCase().contains(busqueda.toLowerCase()) && !logueado.canListenSong(c))
					match.add(c);
				break;

			case TITULO:
				if (c.getNombre().toLowerCase().contains(busqueda.toLowerCase()) && !logueado.canListenSong(c)) {
					match.add(c);
				}
				break;

			case TODO:
				if (((c.getAutorNombre().toLowerCase().contains(busqueda.toLowerCase()))
						|| (c.getNombre().toLowerCase().contains(busqueda.toLowerCase()))
						|| (c.getAlbum() != null
								&& c.getAlbum().getNombre().toLowerCase().contains(busqueda.toLowerCase())))
						&& !logueado.canListenSong(c)) {
					match.add(c);
				}
				break;

			default:
				break;
			}
		}
		if (match.isEmpty())
			System.out.println("\nNo se han encontrado coincidencias\n");
		else {
			Iterator<Cancion> iter = match.iterator();
			while (iter.hasNext()) {
				System.out.print("\n" + iter.next() + "\n");
				System.out.print("\n\n");
			}
			try {
				TimeUnit.SECONDS.sleep(2);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		return match;
	}

	/**
	 * Metodo que comprueba que canciones pendientes de revision se han pasado de
	 * plazo y las elimina
	 */
	public void comprobarRevisiones() {
	}

	/**
	 * Metodo que llama al control de la sesion
	 * 
	 * @return Boolean False si el usuario decide teminar la ejecucion, false en
	 *         caso contrario
	 */
	public Boolean gestionAplicacion() {
		return sesion.programControl();
	}

	/**
	 * Metodo que devuelve una lista con las ultimas canciones anadidas
	 * 
	 * @return songs Lista con las 6 ultimas canciones anadidas
	 */
	public ArrayList<Cancion> getLastSongs() {
		Cancion cancion;
		ArrayList<Cancion> songs = new ArrayList<>();

		for (int c = 6, i = canciones.size() - 1; i >= 0 && c >= 0; i--, c--) {
			cancion = canciones.get(i);
			if (!logueado.canListenSong(cancion)) {
				songs.add(cancion);
			}
		}

		return songs;
	}

	/**
	 * Metodo que devuelve una lista con las ultimas denuncias realizadas
	 * 
	 * @return songs Lista con las 6 ultimas denuncias
	 */
	public ArrayList<Denuncia> getLastDenuncias() {
		if (denuncias.size() < 7) {
			return new ArrayList<Denuncia>(denuncias);
		}
		ArrayList<Denuncia> denun = new ArrayList<>();

		for (int d = 6, i = denuncias.size() - 1; i >= 0; i--, d--) {
			denun.add(denuncias.get(d));
		}

		return denun;
	}

	/**
	 * Metodo que devuelve una lista con las ultimas validaciones pendientes
	 * 
	 * @return songs Lista con las 6 ultimas validaciones
	 */
	public ArrayList<Validacion> getLastValidaciones() {
		if (validaciones.size() < 7) {
			return new ArrayList<Validacion>(validaciones);
		}
		ArrayList<Validacion> valid = new ArrayList<>();

		for (int v = 6, i = validaciones.size() - 1; i >= 0; i--, v--) {
			valid.add(validaciones.get(v));
		}

		return valid;
	}

	/**
	 * Metodo que devuelve una validacion a partir de su indice
	 * 
	 * @return validacion solicitada
	 * @param val indice de la validacion
	 */
	public Validacion getValidacion(int val) {
		return validaciones.get(val);
	}

	/**
	 * Metodo que devuelve una denuncia a partir de su indice
	 * 
	 * @return denuncia solicitada
	 * @param val indice de la denuncia
	 */
	public Denuncia getDenuncia(int den) {
		return denuncias.get(den);
	}

	/**
	 * Metodo que recibe un usuario y lo añade a la lista de usuarios de la
	 * aplicacion
	 * 
	 * @param usuario
	 */
	public void addUsuario(UsuarioRegistrado usuario) {
		if (usuario != null)
			usuarios.add(usuario);
	}

	/**
	 * Metodo que recibe una cancion y la añade a la lista de canciones
	 * 
	 * @param cancion nueva cancion a anadir en la lista
	 */
	public void addCancion(Cancion cancion) {
		if (cancion != null)
			canciones.add(cancion);
	}

	/**
	 * Este metodo crea un nuevo album y lo anade a lista de albumes
	 * 
	 * @param nombre Nombre del album
	 */
	public void addAlbum(Album album) {
		albumes.add(album);
	}

	public void borrarCancion(Cancion cancion) {
		canciones.remove(cancion);
		Album album = cancion.getAlbum();
		if (album.isEmpty()) {
			albumes.remove(album);
		}
	}

	public void pasarPremium() {
		((UsuarioRegistrado) logueado).setPremium(true);
	}

	/**
	 * Metodo que recibe un usuario y una fecha y los anade a bloqueados
	 * 
	 * @param autor
	 * @param plazo
	 */

	public void addBloqueado(UsuarioRegistrado autor, LocalDate plazo) {
		if (autor != null)
			bloqueados.put(autor, plazo);
	}

	/**
	 * Metodo que recibe una validacion y la anade a la lista de la aplicacion
	 * 
	 * @param validacion
	 */
	public void addValidacion(Validacion validacion) {
		validaciones.add(validacion);
	}

	/**
	 * Metodo que recibe una validacion y la elimina de la lista de la aplicacion
	 * 
	 * @param validacion
	 */
	public void deleteValidacion(Validacion validacion) {
		validaciones.remove(validacion);
	}

	/**
	 * Metodo que recibe una denuncia y la anade a la lista de la aplicacion
	 * 
	 * @param denuncia
	 */
	public void addDenuncia(Denuncia denuncia) {
		denuncias.add(denuncia);
	}

	/**
	 * Metodo que recibe una denuncia y la elimina de la lista de la aplicacion
	 * 
	 * @param denuncia
	 */
	public void deleteDenuncia(Denuncia denuncia) {
		denuncias.remove(denuncia);
	}

	/**
	 * Devuelve el umbral para pasar a premium
	 * 
	 * @return the umbralPremium
	 */
	public int getUmbralPremium() {
		return umbralPremium;
	}

	/**
	 * Establece el umbral para pasar a premium
	 * 
	 * @param umbralPremium the umbralPremium to set
	 */
	public void setUmbralPremium(int umbralPremium) {
		this.umbralPremium = umbralPremium;
	}

	/**
	 * Devuelve el limite de reproducciones para un usuario no premium
	 * 
	 * @return the limiteReproducciones
	 */
	public int getLimiteReproducciones() {
		return limiteReproducciones;
	}

	/**
	 * Establece el limite de reproducciones para un usuario no premium
	 * 
	 * @param limiteReproducciones the limiteReproducciones to set
	 */
	public void setLimiteReproducciones(int limiteReproducciones) {
		this.limiteReproducciones = limiteReproducciones;
	}

	/**
	 * Este metodo devuelve un string con la ruta de la carpeta de canciones
	 * 
	 * @return path Ruta de la carpeta
	 */
	public static String getPath() {
		String path = null;

		String sistema = System.getProperty("os.name").toLowerCase();

		if (sistema.indexOf("win") >= 0) {
			path = DIRECTORY + "\\";
		} else if (sistema.indexOf("mac") >= 0) {
			path = DIRECTORY + "/";
		} else if (sistema.indexOf("nix") >= 0 || sistema.indexOf("nux") >= 0 || sistema.indexOf("aix") > 0) {
			path = DIRECTORY + "/";
		}

		return path;
	}

	/**
	 * Metodo que imprime todos los usuarios registrados
	 */
	public void printUsers() {
		for (UsuarioRegistrado u : usuarios) {
			System.out.println(u);
		}
	}

	/**
	 * Metodo que imprime todos las canciones
	 */
	public void printSongs() {
		for (Cancion c : canciones) {
			System.out.println(c);
		}
	}

	/**
	 * Metodo que imprime todos los albumes
	 */
	public void printAlbums() {
		for (Album a : albumes) {
			System.out.println(a);
		}
	}
}