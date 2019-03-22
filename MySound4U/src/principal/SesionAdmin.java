/**
* Clase SesionAdmin
* @author Fernando Barroso, Javier Lozano y Gonzalo Madrigal
*/

package principal;

import java.time.LocalDate;
import java.util.ArrayList;

import utils.ConsolaAnonimo;
import utils.FechaSimulada;

/**
 * Esta clase contiene todos los atributos y metodos de la sesion de
 * administrador
 */
public class SesionAdmin extends Sesion {
	private Administrador usuario;

	/**
	 * Este constructor genera una nueva sesion de administrador
	 * 
	 * @param usuario    Usuario asociado a esta sesion
	 * @param Aplicacion Propietaria de esta sesion
	 */
	public SesionAdmin(Administrador usuario, Aplicacion api) {
		super( api, new ConsolaAnonimo());
		this.usuario = usuario;
	}

	/**
	 * Este metodo reproduce una cancion. Se reproducir� independientemente de las restricciones que
	 * pueda tener.
	 * 
	 * @param cancion Cancion que se quiere reproducir
	 */
	// @Override
	public void reproducir(Cancion cancion) {
		reproductor.reproducir(cancion.getRuta());
	}

	/**
	 * Este metodo acepta una denuncia. Bloquea la canci�n y bloquea al autor indefinidamente.
	 * 
	 * @param denuncia a aceptar
	 */
	public void aceptarDenuncia(Denuncia denuncia) {
		denuncia.getCancion().getAutor().setBloqueado(true);
		api.addBloqueado(denuncia.getCancion().getAutor(), LocalDate.MAX);
		denuncia.getCancion().bloquear();
		api.deleteDenuncia(denuncia);
	}

	/**
	 * Este metodo rechaza una denuncia. Desbloquea la canci�n y bloquea al denunciante 30 dias.
	 * 
	 * @param denuncia a rechazar
	 */
	public void rechazarDenuncia(Denuncia denuncia) {
		denuncia.getDenunciante().setBloqueado(true);
		api.addBloqueado(denuncia.getDenunciante(), FechaSimulada.getHoy().plusDays(30));
		denuncia.getCancion().desbloquear();
		api.deleteDenuncia(denuncia);
	}

	/**
	 * Este metodo valida una cancion.
	 * 
	 * @param cancion a validar
	 */
	public void validar(Validacion validacion) {
		validacion.getCancion().validar();
		api.deleteValidacion(validacion);
		
	}
	/**
	 * Este metodo invalida una cancion, es decir, da tres d�as para que se rectifique.
	 * 
	 * @param cancion a invalidar
	 */

	public void invalidar(Validacion validacion) {
		validacion.setPlazo(FechaSimulada.getHoy().plusDays(3));
	}
	
	/**
	 * Este metodo devuelve el usuario de esta sesion
	 * 
	 * @return usuario Usuario que solicita la reproduccion
	 */
	public Usuario getUsuario() {
		return usuario;
	}

	public void setLimiteReproducciones(int lim) {
	}

	public void setUmbralPremium(int lim) {
	}
	/**
	 * Este metodo marca como expl�cita una cancion.
	 * 
	 * @param cancion a marcar como explicita
	 */

	public void marcarExplicita(Cancion cancion) {
		cancion.setExplicita();
	}

	/**
	 * Este metodo muestra las opciones para el administrador y espera a que este
	 * introduzca la accion a realizar
	 * 
	 * @return Boolean True si el usuario desea finalizar el programa
	 */
	@Override
	public Boolean programControl() {
		return false;
	}
}