/**
* Clase Album
* @author Gonzalo Madrigal, Fernando Barroso y Javier Lozano
*
*/

package principal;

import java.util.ArrayList;

/**
 * Esta clase contiene todos los atributos y metodos de un Album
 */
public class Album extends Element {

	/** lista con las canciones del album */
	private ArrayList<Cancion> canciones;

	/**
	 * Este constructor genera un nuevo album con los datos recibidos como
	 * argumentos.
	 * 
	 * @param nombre
	 */
	public Album(String nombre) {
		super(nombre);
		canciones = new ArrayList<>();
	}

	public void anadirCancion(Cancion cancion) {
		canciones.add(cancion);
	}

	public void borrarCancion(Cancion cancion) {
		canciones.remove(cancion);
	}
	
	public Boolean isEmpty() {
		return canciones.isEmpty();
	}

}