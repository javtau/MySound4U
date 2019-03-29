/**
* Clase Album
* @author Fernando Barroso, Javier Lozano y Gonzalo Madrigal
*/

package principal;

import java.io.Serializable;
import java.util.ArrayList;

import utils.Reproductor;

/**
 * Esta clase contiene todos los atributos y metodos de un Album
 */
public class Album extends Element implements Serializable {

	/** Lista con las canciones del album */
	private ArrayList<Cancion> canciones;

	/**
	 * Este constructor genera un nuevo album con los datos recibidos como
	 * argumentos
	 * 
	 * @param nombre
	 */
	public Album(String nombre) {
		super(nombre);
		canciones = new ArrayList<>();
	}

	public boolean anadirCancion(Cancion cancion) {
		return canciones.add(cancion);
	}

	public void borrarCancion(Cancion cancion) {
		canciones.remove(cancion);
	}

	public Boolean isEmpty() {
		return canciones.isEmpty();
	}

	@Override
	public Boolean reproducir() {
		return true;
		
	}
	
	
}