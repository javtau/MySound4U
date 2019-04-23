/**
* Clase UsuarioAnonimo
* @author Fernando Barroso, Javier Lozano y Gonzalo Madrigal
*/

package modelo;

import java.io.Serializable;

/**
 * Esta clase contiene todos los atributos y metodos de un usuario anonimo
 */
public class UsuarioAnonimo extends Usuario implements Serializable {

	private static final long serialVersionUID = 1L;

	/** Nombre por defecto para el usuario anonimo */
	private static final String NOMBRE = "anonimo";

	/** Contrasena por defecto para el usuario anonimo */
	private static final String CONTRASENA = "anonimo";

	/**
	 * Este constructor genera un nuevo usuario anonimo, asignandole unos valores
	 * establecidos por defecto para el nombre y la contrasena
	 */
	public UsuarioAnonimo() {
		super(NOMBRE, CONTRASENA);
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
		return new SesionAnonima(this, api);
	}

	/**
	 * Metodo que recibe una cancion e indica si este usuario puede escucharla
	 * 
	 * @param cancion cancion a comprobar
	 * @return Boolean true si el usuario puede reproducir la cancion, false en caso
	 *         contrario
	 */
	public Boolean canListenSong(Cancion cancion) {

		return cancion.esBloqueda() || cancion.esExplicita() || !cancion.esValidada();
	}

	/**
	 * Este devuelve un string con la informacion del usuario anonimo
	 * 
	 * @param String string con la info del usuario
	 */
	@Override
	public String toString() {
		return "UsuarioAnonimo" + "canciones reproducidas" + getReproducidas();
	}

	@Override
	public boolean isAdmin() {
		return false;
	}

}