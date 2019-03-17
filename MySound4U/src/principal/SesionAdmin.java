/**
* Clase Sesion de administrador
* @author Gonzalo Madrigal, Fernando Barroso y Javier Lozano
*
*/

package principal;

import java.util.ArrayList;

import utils.ConsolaAnonimo;

/**
 * Esta clase contiene todos los atributos y metodos de la sesion de
 * administrador
 */
public class SesionAdmin extends Sesion {

	/**
	 * Este constructor genera una nueva sesion de administrador
	 * 
	 * @param usuario    Usuario asociado a esta sesion
	 * @param Aplicacion propietaria de esta sesion
	 */
	public SesionAdmin(Administrador usuario, Aplicacion api) {
		super(usuario, api, new ConsolaAnonimo());
	}

	/**
	 * Este metodo reproduce una cancion.
	 * 
	 * @param cancion cancion que se quiere reproducir
	 * @param usuario usuario que solicita la reproduccion
	 */
	@Override
	public void reproducir(Cancion cancion) {
		// TODO Auto-generated method stub

	}

	public void aceptarDenuncia(Denuncia denuncia) {
	}

	public void rechazarDenuncia(Denuncia denuncia) {
	}

	public void validar(Cancion cancion) {
	}

	public void invalidar(Cancion cancion) {
	}

	public void setLimiteReproducciones(int lim) {
	}

	public void setUmbralPremium(int lim) {
	}

	public void marcarExplicita(Cancion cancion) {
	}

	/**
	 * Este metodo muestra las opciones para el administrador, y espera a que este
	 * introduzca la accion a realizar.
	 * 
	 * @return Boolean true si el usuario desea finalizar el programa
	 */
	@Override
	public Boolean programControl() {
		return false;
	}

}