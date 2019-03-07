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
public class Lista {

	/** nombre de la lista */
	private String nombre;

	/** lista con las canciones de la lista */
	private ArrayList<Cancion> canciones;

	/** lista con las listas de la lista */
	private ArrayList<Lista> listas;

	/** lista con los albumes de la lista */
	private ArrayList<Album> albumes;

	/**
	 * Este constructor genera una nueva lista con los datos recividos como
	 * argumentos.
	 * 
	 * @param nombre
	 */
	public Lista(String nombre) {
		super();
		this.nombre = nombre;
		canciones = new ArrayList<>();
		listas = new ArrayList<>();
		albumes = new ArrayList<>();
	}
	
	
	/**
	 * Este método devuelve el nombre de la lista
	 * 
	 * @return nombre
	 */
	public String getNombre() {
		return nombre;
	}



	public void anadirCancion(Cancion cancion) {
		canciones.add(cancion);
	}

	public void añadirAlbum(Album album) {
		albumes.add(album);
	}

	public void añadirLista(Lista lista) {
		listas.add(lista);
	}

}