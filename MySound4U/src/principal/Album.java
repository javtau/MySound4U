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
public class Album {

	/** nombre del album */
	private String nombre;

	/** lista con las canciones del album */
	private ArrayList<Cancion> canciones;

	/**
	 * Este constructor genera un nuevo album con los datos recividos como
	 * argumentos.
	 * 
	 * @param nombre
	 */
	public Album(String nombre) {
		super();
		this.nombre = nombre;
		canciones = new ArrayList<>();
	}
	
	/**
	 * Este método devuelve el nombre del album
	 * 
	 * @return nombre
	 */
	public String getNombre() {
		return nombre;
	}

	public void añadirCancion(Cancion cancion) {
		canciones.add(cancion);
	}
	
	public void borrarCancion(Cancion cancion) {
		canciones.remove(cancion);
	}

}