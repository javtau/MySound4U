/**
* Clase Sesion de administrador
* @author Gonzalo Madrigal, Fernando Barroso y Javier Lozano
*
*/

package principal;

import java.util.ArrayList;

/**
 * Esta clase contiene todos los atributos y metodos de la sesion de
 * administrador
 */
public class SesionAdmin extends Sesion {

	/** lista de denuncias pendientes */
	private ArrayList<Denuncia> denuncias;

	/** lista de validaciones pendientes */
	private ArrayList<Validacion> validaciones;

	/**
	 * Este constructor genera una nueva sesion de administrador e inicializa todas
	 * las listas.
	 * 
	 */
	public SesionAdmin() {
		super();

		validaciones = new ArrayList<>();
		denuncias = new ArrayList<>();
	}

	/**
	 * Este metodo reproduce una cancion.
	 * 
	 * @param cancion cancion que se quiere reproducir
	 * @param usuario usuario que solicita la reproduccion
	 */
	@Override
	public void reproducir(Cancion cancion, Usuario usuario) {
		// TODO Auto-generated method stub

	}

	public void aceptarDenuncia( Denuncia denuncia) {
  }

	public void rechazarDenuncia( Denuncia denuncia) {
  }

	public void validar( Cancion cancion) {
  }

	public void invalidar( Cancion cancion) {
  }

	public void setLimiteReproducciones(int lim) {
	}

	public void setUmbralPremium(int lim) {
  }

	public void marcarExplicita( Cancion cancion) {
  }

}