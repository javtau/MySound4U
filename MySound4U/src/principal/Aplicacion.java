/**
* Clase Aplicacion
* @author Fernando Barroso, Javier Lozano y Gonzalo Madrigal
*/

package principal;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.chrono.ChronoPeriod;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import utils.FechaSimulada;
import utils.Reproductor;

/**
 * Esta clase contiene todos los atributos y metodos de la aplicacion
 */
public class Aplicacion implements Serializable {
	/** Instancia de la aplicacion */
	private static Aplicacion myApi;
	/** Numero de reproducciones de los usuarios no premium */
	static final int REPRODUCCIONES_MAX = 10;
	/**
	 * Numero de reproducciones que tiene que tener un usuario para pasar a premium
	 * gratis
	 */
	static final int UMBRAL_PREMIUM = 5;

	/** Nombre de la carpeta donde se almacenan las canciones */
	static final String DIRECTORY = "songs";

	/**
	 * Ruta de las canciones con el formato del sistema en el que se este ejecutando
	 */
	private final String PATH;

	/** Nombre de la carpeta donde se almacenan los guardados de la aplicacion */
	static final String DATA_DIRECTORY = "data";

	/** Nombre del fichero de guardaddo */
	static final String DATA_FILE = "MySound4U.data";

	/**
	 * Ruta del fichero de guardado con el formato del sistema en el que se este
	 * ejecutando
	 */
	public final String DATA_PATH;

	public static Reproductor reproductor;

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

	private LocalDate lastDate;

	/**
	 * Este constructor genera una nueva sesion de administrador e inicializa todas
	 * las listas si detectase que hay alguna archivos de datos de sesiones
	 * anteriores se recuperaria dicha sesion
	 * 
	 */
	private Aplicacion() {
		PATH = getPath();
		DATA_PATH = getDataPath();
		FechaSimulada.restablecerHoyReal();
		reproductor = new Reproductor();
		logueado = new UsuarioAnonimo();
		if (!(new File(DATA_PATH)).exists()) {
			System.out.println("Cargando datos base");
			umbralPremium = UMBRAL_PREMIUM;
			limiteReproducciones = REPRODUCCIONES_MAX;
			canciones = new ArrayList<>();
			usuarios = new ArrayList<>();
			validaciones = new ArrayList<>();
			denuncias = new ArrayList<>();
			albumes = new ArrayList<>();
			bloqueados = new HashMap<UsuarioRegistrado, LocalDate>();
			admin = new Administrador();
			lastDate = FechaSimulada.getHoy();

			/* VALORES DE PRUEBA PARA EL DEMOSTRADOR */

			UsuarioRegistrado sistema = new UsuarioRegistrado("System", "1234", LocalDate.now());
			usuarios.add(sistema);
			UsuarioRegistrado avicii = new UsuarioRegistrado("Avicii", "1234", LocalDate.now());
			usuarios.add(avicii);
			UsuarioRegistrado gonzalo = new UsuarioRegistrado("Gonzalo", "1234", LocalDate.now());
			usuarios.add(gonzalo);
			Cancion c1 = new Cancion("CorePride", "UVERworld_CorePride.mp3", sistema);
			c1.validar();
			c1.marcarExplicita();
			Cancion c2 = new Cancion("RookiezisPunk", "RookiezisPunk_d_InMyWorld.mp3", sistema);
			Cancion c3 = new Cancion("Levels", "avicii-levels.mp3", avicii);
			c3.validar();
			Cancion c4 = new Cancion("Relax", "Relax.mp3", gonzalo);
			c4.validar();
			canciones.add(c1);
			canciones.add(c2);
			canciones.add(c3);
			canciones.add(c4);
			Cancion wakeMe = new Cancion("Wake me up", "avicii-wake-me-up.mp3", avicii);
			Album album = new Album("Mix", avicii);
			album.anadirCancion(c3);
			album.anadirCancion(wakeMe);
			avicii.addAlbum(album);
			albumes.add(album);
			canciones.add(wakeMe);
			Denuncia d1 = new Denuncia(c2, avicii, "System me ha copiado la cancion, es un sinverguenza");
			Validacion v1 = new Validacion(c2, LocalDate.MAX);
			Validacion v2 = new Validacion(wakeMe, LocalDate.MAX);
			denuncias.add(d1);
			validaciones.add(v1);
			validaciones.add(v2);
			sesion = logueado.iniciarSesion(this);
			revision();

		} else {
			System.out.println("Cargando sesion anterior");
			load();

			sesion = logueado.iniciarSesion(Aplicacion.myApi);
			Aplicacion.myApi.revision();
		}

	}

	/**
	 * Este metodo genera una nueva aplicacion y la devuelve, si ya se hubiera
	 * instaciado anteriormentese devolveria dicha instancia
	 * 
	 * @return myApi
	 */
	public static Aplicacion getApi() {
		if (Aplicacion.myApi == null) {
			if (!(new File(getDataPath())).exists()) {
				Aplicacion.myApi = new Aplicacion();
			} else {
				new Aplicacion();
			}
		}

		return Aplicacion.myApi;
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
			sesion = logueado.iniciarSesion(Aplicacion.myApi);
			System.out.print("\nEl usuario " + admin.getNombre() + " se ha logueado correctamente\n\n");
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
				sesion = logueado.iniciarSesion(Aplicacion.myApi);
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
			UsuarioRegistrado u1;
			d = LocalDate.parse(fecha, format);
			if (encontrado == false) {
				u1 = new UsuarioRegistrado(nombre, pass, d);
				addUsuario(u1);
				System.out.println(u1.toString());
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
		sesion = logueado.iniciarSesion(Aplicacion.myApi);
	}

	/**
	 * Metodo que comprueba si hay que desbloquear a algun usuario con bloqueo
	 * temporal
	 */
	public void revisarBloqueados() {
		for (Map.Entry<UsuarioRegistrado, LocalDate> bloqueado : bloqueados.entrySet()) {
			ChronoPeriod period = ChronoPeriod.between(bloqueado.getValue(), FechaSimulada.getHoy());
			System.out.println(FechaSimulada.getHoy() + ", " + bloqueado.getValue() + ", "
					+ period.get(ChronoUnit.MONTHS) + "-" + period.get(ChronoUnit.DAYS));
			if (period.get(ChronoUnit.YEARS) > 0 || period.get(ChronoUnit.MONTHS) > 0) {
				bloqueado.getKey().setBloqueado(false);
				bloqueados.remove(bloqueado.getKey());
			}
		}
	}

	/**
	 * Metodo que comprueba si hay que eliminar alguna denuncia
	 */
	public void revisarValidaciones() {
		Cancion cancion;
		System.out.println(validaciones);
		for (Validacion v : validaciones) {
			ChronoPeriod period = ChronoPeriod.between(v.getPlazo(), FechaSimulada.getHoy());
			System.out.println(v.getPlazo() + " " + FechaSimulada.getHoy());
			System.out.println(period.get(ChronoUnit.YEARS) + " " + period.get(ChronoUnit.MONTHS) + " "
					+ period.get(ChronoUnit.DAYS));
			if (period.get(ChronoUnit.YEARS) > 1 || period.get(ChronoUnit.MONTHS) > 1
					|| period.get(ChronoUnit.DAYS) > 3) {

				cancion = v.getCancion();
				System.out.println("borrar cancion" + cancion.getNombre() + " borrar validacion");
				validaciones.remove(v);
				cancion.getAutor().borrarCancion(cancion);
				borrarCancion(cancion);

			}
		}
	}

	/**
	 * Metodo que realiza todas las comprobaciones y cambios dependientes del tiempo
	 */
	public void revision() {
		revisarBloqueados();
		revisarValidaciones();

		ChronoPeriod period = ChronoPeriod.between(lastDate, FechaSimulada.getHoy());
		if (period.get(ChronoUnit.YEARS) > 0 || period.get(ChronoUnit.MONTHS) > 0
				|| ChronoPeriod.between(lastDate.minusDays(lastDate.getDayOfMonth() - 1), FechaSimulada.getHoy())
						.get(ChronoUnit.MONTHS) > 0) {

			for (UsuarioRegistrado user : usuarios) {
				user.setPremium(false);
				if (user.getReproducciones() >= umbralPremium) {
					pasarPremium(user);
				}
				user.resetearContadores();
			}

			for (Cancion song : canciones) {
				song.resetearReproducciones();
			}
		}

	}

	/**
	 * Este metodo busca en la lista de canciones las canciones que cumplan ciertos
	 * requisitos y las añade a un array que devolvera posteriormente
	 * 
	 * @param busqueda String que contiene el texto a buscar
	 * @param tipo     Tipo de busqueda que se quiere realizar
	 * @return match ArrayList con las caciones que cumplan los requisitos
	 */
	public ArrayList<Element> buscar(String busqueda, TIPO_BUSQUEDA tipo) {
		ArrayList<Element> match = new ArrayList<Element>();

		switch (tipo) {
		case ALBUM:
			for (Element album : albumes) {
				if (album.getNombre().toLowerCase().contains(busqueda.toLowerCase()))
					match.add(album);
			}
			break;

		case AUTOR:
			for (Cancion cancion : canciones) {
				if (cancion.getAutorNombre().toLowerCase().contains(busqueda.toLowerCase())
						&& !logueado.canListenSong(cancion))
					match.add(cancion);
			}
			break;

		case TITULO:
			for (Cancion cancion : canciones) {
				if (cancion.getNombre().toLowerCase().contains(busqueda.toLowerCase())
						&& !logueado.canListenSong(cancion)) {
					match.add(cancion);
				}
			}
			break;

		case TODO:
			for (Cancion cancion : canciones) {
				if (((cancion.getAutorNombre().toLowerCase().contains(busqueda.toLowerCase()))
						|| (cancion.getNombre().toLowerCase().contains(busqueda.toLowerCase())))
						&& !logueado.canListenSong(cancion)) {
					match.add(cancion);
				}
				for (Element album : albumes) {
					if (album.getNombre().toLowerCase().contains(busqueda.toLowerCase()))
						match.add(album);
				}
			}
			break;

		default:
			break;
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
	public ArrayList<Element> getLastSongs() {
		Cancion cancion;
		ArrayList<Element> songs = new ArrayList<>();

		for (int c = 6, i = canciones.size() - 1; i >= 0 && c >= 0; i--, c--) {
			cancion = canciones.get(i);
			if (!logueado.canListenSong(cancion)) {
				songs.add(cancion);
			}
		}

		return songs;
	}

	/**
	 * Metodo que devuelve una lista con los usuarios registrados
	 * 
	 * @return songs Lista con los usuarios
	 */
	public ArrayList<UsuarioRegistrado> getUsuarios() {
		return usuarios;
	}

	/**
	 * Metodo que devuelve una lista con las denuncias realizadas
	 * 
	 * @return songs Lista con las denuncias
	 */
	public ArrayList<Denuncia> getDenuncias() {
		return denuncias;
	}

	/**
	 * Metodo que devuelve una lista con las validaciones pendientes
	 * 
	 * @return songs Lista con las validaciones
	 */
	public ArrayList<Validacion> getValidaciones() {
		return validaciones;
	}

	/**
	 * Metodo que devuelve un usuario a partir de su indice
	 * 
	 * @return usuario solicitado
	 * @param val indice del usuario
	 */
	public UsuarioRegistrado getUsuario(int user) {
		return usuarios.get(user);
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
		((UsuarioRegistrado) logueado).addAlbum(album);
	}

	/**
	 * Este metodo elimina una cancion
	 * 
	 * @param nombre Nombre de la cancion
	 */
	public void borrarCancion(Cancion cancion) {
		Album album;
		if (canciones.contains(cancion)) {
			canciones.remove(cancion);
			File f = new File(PATH + cancion.getRuta());
			f.delete();
			if ((album = cancion.getAlbum()) != null) {
				album.borrarCancion(cancion);
				if (album.isEmpty()) {
					albumes.remove(album);
					((UsuarioRegistrado) logueado).borrarAlbum(album);
				}
			}
		}
	}

	/**
	 * Este metodo pasa a premium a un usuario
	 * 
	 * @param usuario Usuario a pasar a premium
	 */
	public void pasarPremium(UsuarioRegistrado usuario) {
		usuario.setPremium(true);
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
	 * Metodo que recibe un usuario y lo elimina de bloqueados
	 * 
	 * @param autor
	 */

	public void deleteBloqueado(UsuarioRegistrado autor) {
		if (autor != null)
			bloqueados.remove(autor);
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
	 * Metodo que recibe una cancion y devuelve la validacion que tiene esa cancion
	 * 
	 * @param cancion
	 * @return validacion
	 */
	public Validacion getValidacion(Element cancion) {
		for (Validacion v : validaciones) {
			if (v.getCancion() == cancion) {
				return v;
			}
		}
		return null;
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
	 * Este metodo devuelve un string con la ruta de la carpeta de datos, que es
	 * donde se guardan los datos de la aplicacion
	 * 
	 * @return path Ruta de la carpeta
	 */
	public static String getDataPath() {
		String path = null;

		String sistema = System.getProperty("os.name").toLowerCase();

		if (sistema.indexOf("win") >= 0) {
			path = DATA_DIRECTORY + "\\" + DATA_FILE;
		} else if (sistema.indexOf("mac") >= 0) {
			path = DATA_DIRECTORY + "/" + DATA_FILE;
		} else if (sistema.indexOf("nix") >= 0 || sistema.indexOf("nux") >= 0 || sistema.indexOf("aix") > 0) {
			path = DATA_DIRECTORY + "/" + DATA_FILE;
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

	public void printDenuncias() {
		for (Denuncia d : denuncias) {
			System.out.println(d);
		}
	}

	public void printValidaciones() {
		for (Validacion v : validaciones) {
			System.out.println(v);
		}
	}

	/**
	 * Metodo que imprime todos las canciones
	 */
	public void printSongs() {
		for (Element c : canciones) {
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

	/**
	 * Metodo que guarda el estado de toda la aplicacion
	 */
	public void save() {

		ObjectOutputStream file;
		try {
			file = new ObjectOutputStream(new FileOutputStream(getDataPath()));
			Aplicacion a = Aplicacion.myApi;
			file.writeObject(Aplicacion.myApi);
			file.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * Metodo que recupera el estado anterior de la aplicacion
	 */
	public void load() {
		Aplicacion api;
		try {
			ObjectInputStream file = new ObjectInputStream(new FileInputStream(DATA_PATH));
			api = (Aplicacion) file.readObject();
			file.close();
			Aplicacion.myApi = api;
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
			return;
		}

	}

	/**
	 * Metodo que imprime todo el contenido de un directorio
	 * 
	 * @throws InterruptedException
	 */
	public void printDirectory() throws InterruptedException {

		File directory = new File("songs/");

		String[] filesInDir = directory.list();

		for (int i = 0; i < filesInDir.length; i++) {
			System.out.println("Archivo: " + filesInDir[i]);
			TimeUnit.SECONDS.sleep(1);
		}
	}

	/**
	 * Metodo que aumenta la fecha simulada tantos dias como reciba
	 *
	 * @param dias dias que se quiere avanzar la fecha
	 */
	public void avanzarSimulada(int dias) {
		FechaSimulada.avanzar(dias);
	}

	public void updateLastDate() {
		lastDate = FechaSimulada.getHoy();
	}
}