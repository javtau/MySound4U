/**
* Clase usuario administrador
* @author Gonzalo Madrigal, Fernando Barroso y Javier Lozano
*
*/

package principal;

/**
 * Esta clase contiene todos los atributos y metodos de un administrador
 */
public class Administrador extends Usuario {
	
	/** Nombre por defecto para el administrador */
	private static final String NOMBRE = "admin";
	
	/** Contraseña por defecto para el administrador */
	private static final String CONTRASEÑA = "admin";

	/**
	 * Este constructor genera un nuevo administrador, asignandole unos valores
	 * establecidos por defecto para el nombre y la contraseña. 
	 */
	public Administrador() {
		super(NOMBRE, CONTRASEÑA);
	}
}