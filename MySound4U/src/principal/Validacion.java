/**
* Clase Validacion
* @author Fernando Barroso, Javier Lozano y Gonzalo Madrigal
*/

package principal;

import java.util.ArrayList;
import java.util.Date;

/**
 * Esta clase contiene todos los atributos y metodos de una validacion
 */
public class Validacion {

	/** Cancion pendiente de validar */
	private Cancion cancion;

	/** Fecha en la que se marco la cancion como pendiente de revision */
	private Date plazo;

	/**
	 * Este constructor genera una nueva validacion con los datos recibidos como
	 * argumentos
	 * 
	 * @param cancion
	 */
	public Validacion(Cancion cancion) {
		super();
		this.cancion = cancion;
	}

	/**
	 * Este metodo devuelve la fecha en la que se inicio el plazo de revision
	 * 
	 * @return plazo
	 */
	public Date getPlazo() {
		return plazo;
	}

	/**
	 * Este metodo establece la fecha en la que inicio el plazo de revision
	 * 
	 * @param plazo
	 */
	public void setPlazo(Date plazo) {
		this.plazo = plazo;
	}

	/**
	 * Este metodo devuelve la cancion pendiente de revision
	 * 
	 * @return cancion
	 */
	public Cancion getCancion() {
		return cancion;
	}
}