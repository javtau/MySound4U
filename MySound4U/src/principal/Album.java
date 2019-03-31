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

	public boolean anadirCancion(Cancion cancion) {
		return canciones.add(cancion);
	}

	public void borrarCancion(Cancion cancion) {
		canciones.remove(cancion);
	}

	public Boolean isEmpty() {
		return canciones.isEmpty();
	}

	/**
	 * Este metodo reproduce un elemento y realiza las acciones necesaria para dicho
	 * elelmento
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
		if ((rutas = getrutas(usuario)) == null) {
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
	 * Metodo que devuelve el el nombre , el autor, la duracion y el tipo de un
	 * elemento
	 * 
	 * @return String string con la informacion del elemento
	 */
	@Override
	public String dataString() {
		return super.getNombre() + "  " + "              Autor: " + autor.getNombre() + " Album";
	}

	public UsuarioRegistrado getAutor() {
		return autor;
	}

}