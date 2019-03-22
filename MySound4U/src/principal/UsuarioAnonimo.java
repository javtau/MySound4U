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

	/** Este metodo inicia una nueva sesion del tipo del usuario que la inicia, y pasa a dicha sesion
	 * la aplicacion que la genera
	 * 
	 *  @param api Aplicacion que pide el inicio de sesion
	 *  @return Sesion Sesion del tipo de usuario que la inicia
	 *  */
	@Override
	public Sesion iniciarSesion(Aplicacion api) {
		
		return new SesionAnonima(this, api);
	}
	
	/** Este devuelve un string con la informacion del usuario anonimo
	 * 
	 *  @param String string con la info del usuario
	 *  */
	@Override
	public String toString() {
		return "UsuarioAnonimo" + "canciones reproducidas" + getReproducidas();
	}
	
	
}