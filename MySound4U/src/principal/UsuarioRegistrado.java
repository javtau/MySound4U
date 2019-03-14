/**
* Clase usuario registrado
* @author Gonzalo Madrigal, Fernando Barroso y Javier Lozano
*
*/

package principal;

import java.util.ArrayList;
import java.util.Date;

/**
 * Esta clase contiene todos los atributos y metodos de un usuario registrado
 */
public class UsuarioRegistrado extends Usuario {

	/** Fecha de nacimiento del usuario */
	private Date fechanac;

	/** Variable que indica si un usuario esta bloqueado ( True = bloqueado) */
	private Boolean bloqueado;

	/** Variable que indica si un usuario es premium ( True = usuario premium) */
	private Boolean premium;

	/**
	 * Cantidad de reproducciones que ha acumulado un usuario entre todas sus
	 * canciones dentro del periodo vigente
	 */
	private Integer reproducciones;

	/** Lista con las canciones de un usuario */
	private ArrayList<Cancion> canciones;

	/** lista con los albummes de un usuario */
	private ArrayList<Album> albumes;

	/** lista con las listas creadas por el usuario */
	private ArrayList<Lista> listas;

	/** Lista de los usuarios a los que sigue este usuario */
	private ArrayList<UsuarioRegistrado> seguidos;

	/**
	 * Este constructor genera un nuevo usuario registrado con los datos recividos
	 * como argumentos. se hara uso del constructor del padre
	 * 
	 * @param nombre     nombre del usuario
	 * @param contrasena contrasena del usuario
	 * @param fechanac   fecha de nacimiento del usuario
	 */
	public UsuarioRegistrado(String nombre, String contrasena, Date fechanac) {
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
	 * Este método devuelve si el usuario esta bloqueado
	 * 
	 * @return Boolean true si el usuario esta bloqueado, false en caso contrario
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
	 * Este método devuelve si el usuario es premium
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
	 * @return fechanac fecha de nacimiento del usuario
	 */
	public Date getFechanac() {
		return fechanac;
	}

	/**
	 * Este método anade una nueva cancion a la lista de canciones del usuario
	 * 
	 * @param cancion cancion a anadir
	 */
	public void anadirCancion(Cancion cancion) {
		canciones.add(cancion);
	}

	/**
	 * Este método borra una cancion de la lista de canciones. tambien se borrara de
	 * las listas y albumes que la contengan. si al borrar una cancion de un album
	 * este quedase vacio tambien se procedera al borrado de dicho album
	 * 
	 * @param cancion Cancion que se desea borrar
	 * @return Boolean true si se a borrado la cancion, false en caso contrario
	 */
	public Boolean borrarCancion(Cancion cancion) {

		return true;
	}

	/** Este metodo pone a cero los contadores de reproducciones y reproducidas */
	public void resetearContadores() {
	}

	/** Este metodo aumenta en 1 el numero de reproducciones totales */
	public void aumentarReproducciones() {
	}

	/**
	 * Este metodo aumenta en 1 el numero de canciones reproducidas por el susuario
	 */
	public void aumentarReproducidas() {
	}

	/**
	 * Este método anade un usuario registrado a la lista de usuarios seguidos
	 * 
	 * @param usuario usuario que se quiere anadir
	 * @return Boolean true si se a anadido la cancion, false en caso contrario
	 */
	public Boolean seguir(UsuarioRegistrado usuario) {

		return true;
	}

	public void editarCancion(Cancion cancion) {
	}

	/** Este metodo crea una nueva lista y la anade a lista de listas */
	public void crearLista() {
	}

	/** Este metodo crea un nuevo album y lo anade a lista de albummes */
	public void crearAlbum() {
	}

	/**
	 * Este método anade un nuevo cancion a un album
	 * 
	 * @param cancion cancion que se quiere anadir al album
	 * @return Boolean true si se a anadido la cancion, false en caso contrario
	 */
	public Boolean anadiraAlbum(Cancion cancion) {

		return true;
	}

	/**
	 * Este método anade un nuevo elemento a una lista, se comprobara el tipo de
	 * elemento y se usara el metodo correspondiente. En caso de que introducca un
	 * elemento incompatible no se hara nada
	 * 
	 * @param object objeto que se quiere anadir a la lista
	 * @return Boolean true si se a anadido el elemento, false en caso contrario
	 */
	public Boolean anadirALista(Element elemen) {

		return true;
	}

	/**
	 * Este método Borra una cancion de un album
	 * 
	 * @param object objeto que se quiere anadir a la lista
	 * @return Boolean true si se a anadido el elemento, false en caso contrario
	 */
	public Boolean borrarAlbum(Album album) {

		return true;
	}

}