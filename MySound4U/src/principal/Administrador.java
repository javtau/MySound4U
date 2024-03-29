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

	/**
	 * Este metodo inicia una nueva sesion del tipo del usuario que la inicia, y
	 * pasa a dicha sesion la aplicacion que la genera
	 * 
	 * @param api Aplicacion que pide el inicio de sesion
	 * @return Sesion Sesion del tipo de usuario que la inicia
	 */
	@Override
	public Sesion iniciarSesion(Aplicacion api) {
		 return new SesionAdmin(this, api);
	}

	/**
	 * Este devuelve un string con la informacion del usuario anonimo
	 * 
	 * @param String string con la info del usuario
	 */
	@Override
	public String toString() {
		return "Administrador ";
	}
}