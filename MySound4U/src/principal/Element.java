/**
* Clase Element
* @author Fernando Barroso, Javier Lozano y Gonzalo Madrigal
*/

package principal;

import java.io.Serializable;

import utils.Reproductor;

/**
 * Esta clase contiene todos los atributos y metodos de un elemento
 */
public abstract class Element implements Serializable {
	public Element() {

	}

	/** Nombre del elemento */
	private String nombre;

	/**
	 * Este constructor genera un nuevo elemento con los datos recibidos como
	 * argumentos
	 * 
	 * @param nombre Nombre de la cancion
	 */
	public Element(String nombre) {
		super();
		this.nombre = nombre;
	}

	/**
	 * Este metodo devuelve el nombre
	 * 
	 * @return nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Este metodo reproduce un elemento y realiza las acciones necesaria para dicho
	 * elelmento
	 * 
	 * @usuario usuario que solicita la reproduccion
	 */
	public abstract Boolean reproducir(Usuario usuario);

	/**
	 * Metodo que devuelve el el nombre, el autor, la duracion y el tipo de un
	 * elemento
	 * 
	 * @return String string con la informacion del elemento
	 */
	public abstract String dataString();
}
