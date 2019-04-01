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

	/** Usuario autor del album */
	private UsuarioRegistrado autor;

	/** Lista con las canciones del album */
	private ArrayList<Cancion> canciones;

	/**
	 * Este constructor genera un nuevo album con los datos recibidos como
	 * argumentos
	 * 
	 * @param nombre
	 */
	public Album(String nombre, UsuarioRegistrado autor) {
		super(nombre);
		this.autor = autor;
		canciones = new ArrayList<>();
	}

	/**
	 * Este metodo anade una cancion a un album
	 * 
	 * @param cancion Cancion a anadir
	 */
	public boolean anadirCancion(Cancion cancion) {
		return canciones.add(cancion);
	}
	/**
	 * Este metodo borra una cancion de un album
	 * 
	 * @param cancion Cancion a borrar
	 */
	public void borrarCancion(Cancion cancion) {
		canciones.remove(cancion);
	}

	/**
	 * Este metodo sirve para comprobar si un album esta vacio
	 * 
	 * @return True si esta vacio, false si contiene canciones
	 */
	public Boolean isEmpty() {
		return canciones.isEmpty();
	}

	/**
	 * Este metodo reproduce un album
	 * 
	 * @usuario usuario que solicita la reproduccion
	 */
	@Override
	public Boolean reproducir(Usuario usuario) {
		ArrayList<String> rutas = new ArrayList<>();
		Boolean esAutor = usuario == autor;
		if (autor.estaBloqueado() && !esAutor) {
			System.out.println("No se puede reproducir album");
			return false;
		}
		if ((rutas = getrutas(usuario)).isEmpty()) {
			return false;
		}
		System.out.println("se va a reproducir " + rutas);
		return Aplicacion.reproductor.reproducir(rutas.toArray(new String[0]));
	}

	/**
	 * Este metodo devuelve un array con las rutas de las canciones de album que
	 * puede escuchar el usuario
	 * 
	 * @param usuario usuario que solicita el usuario
	 * @return rutas Array list con las rutas de las canciones
	 */
	public ArrayList<String> getrutas(Usuario usuario) {
		ArrayList<String> rutas = new ArrayList<>();
		for (Cancion cancion : canciones) {
			if (usuario.canListenSong(cancion)) {
				System.out.println("no se puede reproducir" + cancion.getNombre());

			} else {
				rutas.add(cancion.getRuta());
				usuario.aumentarReproducidas();
				if (usuario.getClass() == UsuarioRegistrado.class && !cancion.esAutor((UsuarioRegistrado) usuario)) {
					cancion.aumentarReproducciones();
					cancion.getAutor().aumentarReproducciones();
				}
			}

		}
		return rutas;
	}

	/**
	 * Metodo que devuelve el nombre, el autor, la duracion y el tipo de un
	 * elemento
	 * 
	 * @return String string con la informacion del elemento
	 */
	@Override
	public String dataString() {
		return super.getNombre() + "  " + "              Autor: " + autor.getNombre() + " Album";
	}

	/**
	 * Getter de Autor
	 * 
	 * @return autor Autor
	 */
	public UsuarioRegistrado getAutor() {
		return autor;
	}

	@Override
	public String toString() {
		return "Nombre de album: "+ super.getNombre() + " [autor=" + autor.getNombre() + "\nCanciones=" + canciones + "]";
	}
}