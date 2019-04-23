/**
* Clase Sesion
* @author Fernando Barroso, Javier Lozano y Gonzalo Madrigal
*/

package modelo;

import java.io.Serializable;

import utils.Consola;
import utils.Reproductor;

/**
 * Esta clase contiene todos los atributos y metodos de la sesion
 */
public abstract class Sesion implements Serializable {

	private static final long serialVersionUID = 1L;

	protected Aplicacion api;
	protected static Reproductor reproductor;
	protected Consola consola;

	public Sesion(Aplicacion api, Consola consola) {
		super();
		this.api = api;
		reproductor = Aplicacion.reproductor;
		this.consola = consola;
	}

	/**
	 * Este metodo reproduce una cancion. Este metodo solo es una declaracion, se
	 * implementara de forma mas especializada en las clases que extiendan de esta
	 * 
	 * @param cancion Cancion que se quiere reproducir
	 * @param usuario Usuario que solicita la reproduccion
	 */
	public abstract void reproducir(Element cancion);

	/**
	 * Este metodo para una cancion.
	 * 
	 */
	public void stop() {
		reproductor.stop();
	}

	/**
	 * Este metodo devuelve el usuario de esta sesion
	 * 
	 * @return usuario Usuario que solicita la reproduccion
	 */
	public abstract Usuario getUsuario();

	/**
	 * Este metodo devuelve la aplicacion que controla esta sesion
	 * 
	 * @return api Api recibida por argumento
	 */
	public Aplicacion getApi() {
		return api;
	}

	/**
	 * Este metodo muestra las opciones para el tipo de usuario y espera a que este
	 * introduzca la accion a realizar
	 * 
	 * @return Boolean True si el usuario desea finalizar el programa
	 */
	public abstract Boolean programControl();
}