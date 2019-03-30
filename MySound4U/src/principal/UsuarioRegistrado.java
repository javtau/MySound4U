/**
* Clase UsuarioRegistrado
* @author Fernando Barroso, Javier Lozano y Gonzalo Madrigal
*/

package principal;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.chrono.ChronoPeriod;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;

import utils.FechaSimulada;

/**
 * Esta clase contiene todos los atributos y metodos de un usuario registrado
 */
public class UsuarioRegistrado extends Usuario implements Serializable {

	/** Fecha de nacimiento del usuario */
	private LocalDate fechanac;

	/** Variable que indica si un usuario esta bloqueado (True = bloqueado) */
	private Boolean bloqueado;

	/** Variable que indica si un usuario es premium (True = usuario premium) */
	private Boolean premium;

	/**
	 * Cantidad de reproducciones que ha acumulado un usuario entre todas sus
	 * canciones dentro del periodo vigente
	 */
	private Integer reproducciones;

	/** Lista con las canciones de un usuario */
	private ArrayList<Cancion> canciones;

	/** Lista con los albummes de un usuario */
	private ArrayList<Album> albumes;

	/** Lista con las listas creadas por el usuario */
	private ArrayList<Lista> listas;

	/** Lista de los usuarios a los que sigue este usuario */
	private ArrayList<UsuarioRegistrado> seguidos;

	/**
	 * Este constructor genera un nuevo usuario registrado con los datos recibidos
	 * como argumentos. Se hara uso del constructor del padre
	 * 
	 * @param nombre     Nombre del usuario
	 * @param contrasena Contrasena del usuario
	 * @param fechanac   Fecha de nacimiento del usuario
	 */
	public UsuarioRegistrado(String nombre, String contrasena, LocalDate fechanac) {
		super(nombre, contrasena);
		this.fechanac = fechanac;

		bloqueado = false;
		premium = false;
		reproducciones = 0;
		canciones = new ArrayList<>();
		albumes = new ArrayList<>();
		listas = new ArrayList<>();
		seguidos = new ArrayList<>();
	}

	/**
	 * Este metodo devuelve si el usuario esta bloqueado
	 * 
	 * @return Boolean True si el usuario esta bloqueado, false en caso contrario
	 */

	public int getEdad() {
		LocalDate today = FechaSimulada.getHoy();
		ChronoPeriod period = ChronoPeriod.between(fechanac, FechaSimulada.getHoy());

		return (int) period.get(ChronoUnit.YEARS);
	}

	/**
	 * Este metodo devuelve si el usuario esta bloqueado
	 * 
	 * @return Boolean True si el usuario esta bloqueado, false en caso contrario
	 */

	public Boolean estaBloqueado() {
		return bloqueado;
	}

	/**
	 * Este metodo actualiza el estado de bloqueado
	 * 
	 * @param bloqueado Nuevo estado al que se quiere pasar
	 */
	public void setBloqueado(Boolean bloqueado) {
		this.bloqueado = bloqueado;
	}

	/**
	 * Metodo que devuelve una lista con las canciones del usuario
	 * 
	 * @return songs Lista con las canciones
	 */
	public ArrayList<Cancion> getCanciones() {
		return canciones;
	}

	/**
	 * Este metodo devuelve si el usuario es premium
	 * 
	 * @return Boolean true si el usuario es premium, false en caso contrario
	 */

	public Boolean esPremium() {
		return premium;
	}

	/**
	 * Este metodo modifica si el usuario es premium
	 * 
	 * @param premium Nuevo estado al que se quiere pasar
	 */
	public void setPremium(Boolean premium) {
		this.premium = premium;
	}

	/**
	 * Este metodo devuelve la fecha de nacimiento del usuario
	 * 
	 * @return fechanac Fecha de nacimiento del usuario
	 */
	public LocalDate getFechanac() {
		return fechanac;
	}

	/**
	 * Este metodo anade una nueva cancion a la lista de canciones del usuario
	 * 
	 * @param cancion Cancion a anadir
	 */
	public void addCancion(Cancion cancion) {
		if (cancion != null)
			canciones.add(cancion);
	}

	/**
	 * Este metodo borra una cancion de la lista de canciones. Tambien se borrara de
	 * las listas y albumes que la contengan. Si al borrar una cancion de un album,
	 * este quedase vacio, tambien se procedera al borrado de dicho album
	 * 
	 * @param cancion Cancion que se desea borrar
	 * @return Boolean True si se a borrado la cancion, false en caso contrario
	 */
	public void borrarCancion(Cancion cancion) {
		if (canciones.contains(cancion)) {
			canciones.remove(cancion);
		}
	}

	/**
	 * Este metodo borra un album de la lista
	 * 
	 * @param album Nombre del album
	 */
	public void borrarAlbum(Album album) {
		if (albumes.contains(album)) {
			albumes.remove(album);
		}
	}

	/** Este metodo pone a cero los contadores de reproducciones y reproducidas */
	public void resetearContadores() {
		reproducciones = 0;
		super.resetearreproducidas();
	}

	/** Este metodo aumenta en 1 el numero de reproducciones totales */
	public void aumentarReproducciones() {
		reproducciones++;
	}

	/**
	 * Este metodo aumenta en 1 el numero de canciones reproducidas por el susuario
	 */
	public void aumentarReproducidas() {
		super.aumentarReproducidas();

	}

	/**
	 * Este metodo anade un usuario registrado a la lista de usuarios seguidos
	 * 
	 * @param usuario Usuario que se quiere anadir
	 * @return Boolean True si se ha anadido la cancion, false en caso contrario
	 */
	public Boolean seguir(UsuarioRegistrado usuario) {
		if (usuario != null) {
			seguidos.add(usuario);
			return true;
		}
		return false;
	}

	/**
	 * Metodo que devuelve una lista con los usuarios a los que sigue
	 * 
	 * @return songs Lista con los usuarios
	 */
	public ArrayList<UsuarioRegistrado> getSeguidos() {
		return seguidos;
	}

	/**
	 * Este metodo crea una nueva lista y la anade a lista de listas
	 * 
	 * @param nombre Nombre de la lista
	 */
	public void addLista(Lista lista) {
		listas.add(lista);
	}

	/**
	 * Este metodo crea un nuevo album y lo anade a lista de albumes
	 * 
	 * @param nombre Nombre del album
	 */
	public void addAlbum(Album album) {
		albumes.add(album);
	}

	/**
	 * Este metodo inicia una nueva sesion del tipo del usuario que la inicia y pasa
	 * a dicha sesion la aplicacion que la genera
	 * 
	 * @param api Aplicacion que pide el inicio de sesion
	 * @return Sesion Sesion del tipo de usuario que la inicia
	 */
	@Override
	public Sesion iniciarSesion(Aplicacion api) {
		return new SesionUsuarios(this, api);
	}

	/**
	 * Este devuelve un string con la informacion del usuario registrado
	 * 
	 * @param String string con la info del usuario
	 */
	@Override
	public String toString() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		return "Usuario registrado " + ((premium) ? "premium " : "") + " [Nombre: " + super.getNombre()
				+ ", fecha de nacimiento: " + fechanac.format(formatter) + ", reproducidas = " + super.getReproducidas()
				+ ", reproducciones = " + reproducciones + ", " + ((bloqueado) ? "esta " : "no esta ") + "bloqueado"
				+ "]";
	}

	/**
	 * Metodo que imprime todos las canciones del usuario
	 */
	public void printSongs() {
		for (Cancion c : canciones) {
			System.out.println(c);
		}
	}

	/**
	 * Metodo que recibe una cancion e indica si este usuario puede escucharla
	 * 
	 * @param cancion Cancion a comprobar
	 * @return Boolean False si el usuario puede reproducir la cancion, true en caso
	 *         contrario
	 */
	public Boolean canListenSong(Cancion cancion) {
		return cancion.esBloqueda()
				|| (!cancion.esValidada() && !cancion.esAutor(this))
				|| (cancion.esExplicita() && getEdad() < 18);
	}

	/**
	 * Metodo que imprime todos los albumes del usuario
	 */
	public void printAlbums() {
		for (Album a : albumes) {
			System.out.println(a);
		}
	}
	
	
}