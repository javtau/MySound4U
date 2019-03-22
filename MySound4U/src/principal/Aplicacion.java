/**
* Clase Aplicacion
* @author Fernando Barroso, Javier Lozano y Gonzalo Madrigal
*/

package principal;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Esta clase contiene todos los atributos y metodos de la aplicacion
 */
public class Aplicacion {

	static final int REPRODUCCIONES_MAX = 200;
	static final int UMBRAL_PREMIUM = 10;

	private Usuario logueado;
	static java.util.Scanner sc;
	
	private int umbralPremium;
	private int limiteReproducciones;

	private ArrayList<Cancion> canciones;
	private Sesion sesion;
	private ArrayList<UsuarioRegistrado> usuarios;
	private ArrayList<Album> albumes;

	/** Lista de denuncias pendientes */
	private ArrayList<Denuncia> denuncias;
	
	/*mapa de usuarios bloqueados (clave, valor) = (Usuario, Fecha de desbloqueo)*/
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
		this.logueado = new UsuarioAnonimo();
		umbralPremium = 200;
		limiteReproducciones = 50;
		canciones = new ArrayList<>();
		usuarios = new ArrayList<>();
		validaciones = new ArrayList<>();
		denuncias = new ArrayList<>();
		bloqueados = new HashMap<UsuarioRegistrado, LocalDate>();
		sesion = logueado.iniciarSesion(this);
		
		/*  valores de prueba*/
		UsuarioRegistrado sistema = new UsuarioRegistrado("SYSTEM", "1234", new Date());
		usuarios.add(sistema);
		Cancion c1 = new Cancion("CorePride", "UVERworld_CorePride.mp3", sistema);
		Cancion c2 = new Cancion("RookiezisPunk", "RookiezisPunk'd_InMyWorld.mp3", sistema);
		Cancion c3 = new Cancion("Levels", "avicii-levels.mp3", sistema);
		canciones.add(c1);
		canciones.add(c2);
		canciones.add(c3);
	}

	/**
	 * Este metodo chequea las credenciales recividas y si estas coinciden con las
	 * de algun usuari registrado, cierra la sesion actual e inicia la sesion del
	 * usuario solicitado
	 * 
	 * @param usuario    Nombre del usuario que se quiere comprobar
	 * @param contrasena contraseña que se quiere comprobar
	 * @return Boolean devuelve true si las credenciales son correctas, false en
	 *         caso contrario
	 */
	public Boolean loguearse(String usuario, String contrasena) {
		Boolean d = false;
		for (UsuarioRegistrado u : usuarios) {
			if (u.validar(usuario, contrasena)) {
				System.out.print("\nEl usuario " + u.getNombre() + " se ha logueado correctamente\n\n");
				logueado = u;
				sesion = logueado.iniciarSesion(this);
				return true;
			}
		}
		if (d == false)
			System.out.println("\nUsuario y/o contrase�a erroneos\n");

		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		return d;
	}

	/**
	 * Metodo que cierra la sesion actual y habre una sesion anonima
	 * 
	 */
	public void desloguearse() {
		logueado = new UsuarioAnonimo();
		 sesion = logueado.iniciarSesion(this);
	}

	/**
	 * Metodo que realiza todaslas comprobaciones ycambios a final de mes
	 */
	public void cierreMes() {
	}

	/**
	 * Este metodo busca en la lista de canciones, las canciones cque cumplan
	 * ciertos requisitos y las añade a un array que devolvera posteriormente
	 * 
	 * @param busqueda string que contiene el texto a buscar
	 * @param tipo     tipo de busqueda que se quiere realizar
	 * @return match arrayList con las caciones que cumplan los requisitos
	 */
	public ArrayList<Cancion> buscar(String busqueda, TIPO_BUSQUEDA tipo) {
		ArrayList<Cancion> match = new ArrayList<Cancion>();
		for (Cancion c : canciones) {
			switch (tipo) {
			case ALBUM:
				if (c.getAlbum() != null && c.getAlbum().getNombre().toLowerCase().contains(busqueda.toLowerCase()))
					match.add(c);
				break;

			case AUTOR:
				if (c.getAutorNombre().toLowerCase().contains(busqueda.toLowerCase()))
					match.add(c);
				break;

			case TITULO:
				if (c.getNombre().toLowerCase().contains(busqueda.toLowerCase())) {
					match.add(c);
				}
				break;

			case TODO:
				if ((c.getAutorNombre().toLowerCase().contains(busqueda.toLowerCase()))
						|| (c.getNombre().toLowerCase().contains(busqueda.toLowerCase())) || (c.getAlbum() != null
								&& c.getAlbum().getNombre().toLowerCase().contains(busqueda.toLowerCase()))) {
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
	 * metodo que llama al control de la sesion
	 * 
	 * @return Boolean false si el usuario decide teminar la ejecucion, false en
	 *         caso contrario
	 */
	public Boolean gestionAplicacion() {
		return sesion.programControl();
	}

	/**
	 * Metodo que devuelve una lista con las ultimas canciones añadidas
	 * 
	 * @return songs Lista con las 6 ultimas canciones añadidas
	 */
	public ArrayList<Cancion> getLastSongs() {
		if (canciones.size() < 7) {
			return new ArrayList<Cancion>(canciones);
		}
		ArrayList<Cancion> songs = new ArrayList<>();

		for (int c = 6, i = canciones.size() - 1; i >= 0; i--, c--) {
			songs.add(canciones.get(c));
		}

		return songs;
	}

	/**
	 * Metodo que recive un usuario y lo añade a la lista de usuarios de la
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
	public void addAlbum(Album album ) {
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
	 * Metodo que recibe un usuario y una fecha y los a�ade a bloqueados
	 * 
	 * @param autor 
	 * @param plazo
	 */

	public void addBloqueado(UsuarioRegistrado autor, LocalDate plazo ) {
		if (autor != null)
			bloqueados.put(autor, plazo);
	}
	/**
	 * Metodo que recibe una validacion y la a�ade a la lista de la aplicacion
	 * 
	 * @param validacion
	 */
	public void addValidacion(Validacion validacion) {
			this.validaciones.add(validacion);
	}

	/**
	 * Metodo que recibe una validacion y la elimina de la lista de la aplicacion
	 * 
	 * @param validacion
	 */
	public void deleteValidacion(Validacion validacion) {
			this.validaciones.remove(validacion);
	}

	/**
	 * Metodo que recibe una denuncia y la a�ade a la lista de la aplicacion
	 * 
	 * @param denuncia
	 */
	public void addDenuncia(Denuncia denuncia) {
			this.denuncias.add(denuncia);
	}

	/**
	 * Metodo que recibe una denuncia y la elimina de la lista de la aplicacion
	 * 
	 * @param denuncia
	 */
	public void deleteDenuncia(Denuncia denuncia) {
			this.denuncias.remove(denuncia);
	}
 

	/**
	 * Devuelve el umbral para pasar a premium
	 * @return the umbralPremium
	 */
	public int getUmbralPremium() {
		return umbralPremium;
	}

	/**
	 * Establece el umbral para pasar a premium
	 * @param umbralPremium the umbralPremium to set
	 */
	public void setUmbralPremium(int umbralPremium) {
		this.umbralPremium = umbralPremium;
	}

	/**
	 * Devuelve el limite de reproducciones para un usuario no premium
	 * @return the limiteReproducciones
	 */
	public int getLimiteReproducciones() {
		return limiteReproducciones;
	}

	/**
	 * Establece el limite de reproducciones para un usuario no premium
	 * @param limiteReproducciones the limiteReproducciones to set
	 */
	public void setLimiteReproducciones(int limiteReproducciones) {
		this.limiteReproducciones = limiteReproducciones;
	}

	/**
	 * Metodo que imprime todos los usuarios registrados
	 */
	public void print() {
		for (UsuarioRegistrado u : usuarios) {
			System.out.println(u);
		}
	}
}