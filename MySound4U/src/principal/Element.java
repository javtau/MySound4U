/**
* Clase Elemto
* @author Gonzalo Madrigal, Fernando Barroso y Javier Lozano
*
*/

package principal;

/**
 * Esta clase contiene todos los atributos y metodos de un elemento
 */
public abstract class Element {
	
	/** Nombre de la cancion */
	private String nombre;

	/**
	 * Este constructor genera un nuevo elemento con los datos recividos como
	 * argumentos.
	 * 
	 * @param nombre   nombre de la cancion
	 */
	public Element(String nombre) {
		super();
		this.nombre = nombre;
	}

	/**
	 * Este método devuelve el nombre 
	 * 
	 * @return nombre
	 */
	public String getNombre() {
		return nombre;
	}
	
	
	
	
	

}
