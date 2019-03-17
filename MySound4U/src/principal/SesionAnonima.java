/**
* Clase Sesion anonima
* @author Gonzalo Madrigal, Fernando Barroso y Javier Lozano
*
*/

package principal;

/**
 * Esta clase contiene todos los atributos y metodos de la sesion anonima
 */
public class SesionAnonima extends Sesion {
	
	

	public SesionAnonima(UsuarioAnonimo usuario) {
		super(usuario);
		// TODO Auto-generated constructor stub
	}

	public void registrarse() {
	}

	/**
	 * Este metodo reproduce una cancion. La cancion no se reproducira si la cancion
	 * esta bloqueada o es explicita o si el usuario a pasado de su limite de
	 * reoroducciones. 
	 * 
	 * @param cancion cancion que se quiere reproducir
	 * @param usuario usuario que solicita la reproduccion
	 */
	@Override
	public void reproducir(Cancion cancion, Usuario usuario) {
		// TODO Auto-generated method stub

	}

}