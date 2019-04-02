/**
* Clase Validacion
* @author Fernando Barroso, Javier Lozano y Gonzalo Madrigal
*/

package principal;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * Esta clase contiene todos los atributos y metodos de una validacion
 */
public class Validacion implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/** Cancion pendiente de validar */
	private Cancion cancion;

	/** Fecha en la que se marco la cancion como pendiente de revision */
	private LocalDate plazo;

	/**
	 * Este constructor genera una nueva validacion con los datos recibidos como
	 * argumentos
	 * 
	 * @param cancion
	 */
	public Validacion(Cancion cancion, LocalDate plazo) {
		super();
		this.cancion = cancion;
		this.plazo = plazo;
	}

	/**
	 * Este metodo devuelve la fecha en la que se inicio el plazo de revision
	 * 
	 * @return plazo
	 */
	public LocalDate getPlazo() {
		return plazo;
	}

	/**
	 * Este metodo establece la fecha en la que inicio el plazo de revision
	 * 
	 * @param plazo
	 */
	public void setPlazo(LocalDate plazo) {
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

	@Override
	public String toString() {
		return "Validacion [Cancion: " + cancion.getNombre() + ", plazo caduca el: " + plazo + "]";
	}
}