/**
* Clase Sesion de usuario
* @author Gonzalo Madrigal, Fernando Barroso y Javier Lozano
*
*/

package principal;

/**
 * Esta clase contiene todos los atributos y metodos de la sesion de usuario
 */
public class SesionUsuarios extends Sesion {

	public SesionUsuarios(UsuarioRegistrado usuario) {
		super(usuario);
		// TODO Auto-generated constructor stub
	}

	public void subirCancion(Cancion cancion, Usuario usuario) {
	}

	public void borrarCancion(Cancion cancion) {
	}

	public void denunciar(Cancion cancion, Usuario usuario) {
	}

	public void pasarPremium() {
	}

	/**
	 * Este metodo reproduce una cancion. La cancion no se reproducira si la cancion
	 * es explicita, si la cancion es de otro autor y esta bloqueada o si el usuario
	 * a pasado de su limite de reoroducciones en el periodo vigente.
	 * 
	 * @param cancion cancion que se quiere reproducir
	 * @param usuario usuario que solicita la reproduccion
	 */
	@Override
	public void reproducir(Cancion cancion, Usuario usuario) {
		// TODO Auto-generated method stub

	}

}