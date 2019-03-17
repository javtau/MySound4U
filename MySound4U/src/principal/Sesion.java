/**
* Clase Sesion
* @author Gonzalo Madrigal, Fernando Barroso y Javier Lozano
*
*/

package principal;

import utils.Consola;
import utils.Reproductor;

/**
 * Esta clase contiene todos los atributos y metodos de la sesion
 */
public abstract class Sesion {
	protected Usuario usuario;
	protected Aplicacion api;
	protected static Reproductor reproductor;
	protected Consola consola;

	public Sesion(Usuario usuario, Aplicacion api, Consola consola) {
		super();
		this.usuario = usuario;
		this.api = api;
		reproductor = new Reproductor();
		this.consola = consola;
	}

	/**
	 * Este metodo reproduce una cancion. Este metodo solo es una declaracion, se
	 * implementara de forma mas especialida en las clases que extiendan de esta
	 * 
	 * @param cancion cancion que se quiere reproducir
	 * @param usuario usuario que solicita la reproduccion
	 */
	public abstract void reproducir(Cancion cancion);

	/**
	 * Este metodo devuelve el usuario de esta sesion
	 * 
	 * @return usuario usuario que solicita la reproduccion
	 */
	public Usuario getUsuario() {
		return usuario;
	}

	/**
	 * Este metodo devuelve la aplicacion que controla esta sesion
	 * 
	 * @return api api recivida por argumento
	 */
	public Aplicacion getApi() {
		return api;
	}

	/**
	 * Este metodo muestra las opciones para el tipo de usuario, y espera a que este
	 * introduzca la accion a realizar.
	 * 
	 * @return Boolean true si el usuario desea finalizar el programa
	 */
	public abstract Boolean programControl();

}