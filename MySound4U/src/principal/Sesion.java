/**
* Clase Sesion
* @author Gonzalo Madrigal, Fernando Barroso y Javier Lozano
*
*/

package principal;

/**
 * Esta clase contiene todos los atributos y metodos de la sesion
 */
public abstract class Sesion {

	/**
	 * Este metodo reproduce una cancion. Este metodo solo es una declaracion, se
	 * implementara de forma mas especialida en las clases que extiendan de esta
	 * 
	 * @param cancion cancion que se quiere reproducir
	 * @param usuario usuario que solicita la reproduccion
	 */
	public abstract void reproducir(Cancion cancion, Usuario usuario);

}