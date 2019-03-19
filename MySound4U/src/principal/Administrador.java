/**
* Clase Administrador
* @author Fernando Barroso, Javier Lozano y Gonzalo Madrigal
*/

package principal;

/**
 * Esta clase contiene todos los atributos y metodos de un administrador
 */
public class Administrador extends Usuario {

	/** Nombre por defecto para el administrador */
	private static final String NOMBRE = "admin";

	/** Contrasena por defecto para el administrador */
	private static final String CONTRASENA = "admin";

	/**
	 * Este constructor genera un nuevo administrador, asignandole unos valores
	 * establecidos por defecto para el nombre y la contrasena.
	 */
	public Administrador() {
		super(NOMBRE, CONTRASENA);
	}

	@Override
	public String toString() {
		return "Administrador ";
	}
}