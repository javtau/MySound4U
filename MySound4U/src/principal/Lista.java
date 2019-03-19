/**
* Clase Lista
* @author Fernando Barroso, Javier Lozano y Gonzalo Madrigal
*/

package principal;

import java.util.ArrayList;

/**
 * Esta clase contiene todos los atributos y metodos de una lista
 */
public class Lista extends Element {

	/** Lista con las canciones de la lista */
	private ArrayList<Element> elementos;

	/**
	 * Este constructor genera una nueva lista con los datos recibidos como
	 * argumentos
	 * 
	 * @param nombre
	 */

	public Lista(String nombre) {
		super(nombre);
		elementos = new ArrayList<>();
	}

	public void addElemt(Element element) {
		elementos.remove(element);
	}

	public void removeElemt(Element element) {
		elementos.add(element);
	}
}