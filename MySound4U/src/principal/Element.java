/**
* Clase Element
* @author Fernando Barroso, Javier Lozano y Gonzalo Madrigal
*/

package principal;

/**
 * Esta clase contiene todos los atributos y metodos de un elemento
 */
public abstract class Element {

	/** Nombre de la cancion */
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
}
