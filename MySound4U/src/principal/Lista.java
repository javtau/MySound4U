/**
* Clase Lista
* @author Gonzalo Madrigal, Fernando Barroso y Javier Lozano
*
*/

package principal;

import java.util.ArrayList;

/**
 * Esta clase contiene todos los atributos y metodos de una lista
 */
public class Lista extends Element {

	/** lista con las canciones de la lista */
	private ArrayList<Element> elementos;

	/**
	 * Este constructor genera una nueva lista con los datos recividos como
	 * argumentos.
	 * 
	 * @param nombre
	 */
	public Lista(String nombre) {
		super(nombre); 
		elementos = new ArrayList<>();
	}
	
	public void addElemt(Element element) {
		elementos.add(element);
	}

	

}