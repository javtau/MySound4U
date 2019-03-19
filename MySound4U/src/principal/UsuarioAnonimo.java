/**
* Clase UsuarioAnonimo
* @author Fernando Barroso, Javier Lozano y Gonzalo Madrigal
*/

package principal;

/**
 * Esta clase contiene todos los atributos y metodos de un usuario anonimo
 */
public class UsuarioAnonimo extends Usuario {

	/** Nombre por defecto para el usuario anonimo */
	private static final String NOMBRE = "anonimo";

	/** Contrasena por defecto para el usuario anonimo */
	private static final String CONTRASENA = "anonimo";

	/**
	 * Este constructor genera un nuevo usuario anonimo, asignandole unos valores
	 * establecidos por defecto para el nombre y la contrasena.
	 */
	public UsuarioAnonimo() {
		super(NOMBRE, CONTRASENA);
	}

	@Override
	public String toString() {
		return "UsuarioAnonimo" + "canciones reproducidas" + getReproducidas();
	}
}