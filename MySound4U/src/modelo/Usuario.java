/**
* Clase Usuario
* @author Fernando Barroso, Javier Lozano y Gonzalo Madrigal
*/

package principal;

import java.io.Serializable;

/**
 * Esta clase contiene todos los atributos y metodos de un usuario
 */
/**
 * @author javier
 *
 */
public abstract class Usuario implements Serializable {
	
	private static final long serialVersionUID = 1L;
	

	/** Nombre del usuario */
	private String nombre;

	/** Contrasena del usuario */
	private String contrasena;

	/**
	 * Cantidad de reproducciones que ha realizado un usuario dentro del periodo
	 * vigente
	 */
	private Integer reproducidas;

	/**
	 * Este constructor genera un nuevo usuario con los datos recibidos como
	 * argumentos. Este contructor solo podra ser llamado desde las clases
	 * herederas, ya que no se puede instanciar un usuario
	 * 
	 * @param nombre     Nombre del usuario
	 * @param contrasena Contrasena del usuario
	 */
	public Usuario(String nombre, String contrasena) {
		super();
		this.nombre = nombre;
		this.contrasena = contrasena;
		reproducidas = 0;
	}

	/**
	 * Este metodo devuelve el nombre del usuario
	 * 
	 * @return nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Este metodo devuelve la contrasena del usuario
	 * 
	 * @return contrasena
	 */
	public String getContrasena() {
		return contrasena;
	}

	/**
	 * Este metodo devuelve la cantidad de canciones reproducidas por el usuario
	 * 
	 * @return reproducidas
	 */
	public Integer getReproducidas() {
		return reproducidas;
	}

	/**
	 * Este metodo recibe un nombre y contrasena y devuelve true si coinciden con
	 * los del usuario
	 * 
	 * @param nombre     Nombre que se quiere verificar
	 * @param contrasena Contrasena que se quiere verificar
	 * @return Boolean True en caso de que ambos valores coincidan con los del
	 *         usuario
	 */
	public Boolean validar(String nombre, String contrasena) {
		return (this.nombre.equalsIgnoreCase(nombre) && this.contrasena.equals(contrasena));
	}

	/**
	 * Este metodo inicia una nueva sesion del tipo del usuario que la inicia y pasa
	 * a dicha sesion la aplicacion que la genera
	 * 
	 * @param api Aplicacion que pide el inicio de sesion
	 * @return Sesion Sesion del tipo de usuario que la inicia
	 */
	public abstract Sesion iniciarSesion(Aplicacion api);

	/**
	 * Metodo que recibe una cancion e indica si este usuario puede escucharla
	 * 
	 * @param cancion cancion a comprobar
	 * @return Boolean true si el usuario puede reproducir la cancion, false en caso
	 *         contrario
	 */
	public abstract Boolean canListenSong(Cancion cancion);

	/** Este metodo pone a cero el contador de canciones reproducidas */
	public void resetearreproducidas() {
		reproducidas = 0;
	}

	/** Este metodo aumenta en 1 el numero de canciones reproducidas */
	public void aumentarReproducidas() {
		reproducidas++;
	}
}