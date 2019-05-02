/**
* Clase Lista
* @author Fernando Barroso, Javier Lozano y Gonzalo Madrigal
*/

package modelo;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Esta clase contiene todos los atributos y metodos de una lista
 */
public class Lista extends Element implements Serializable {

	private static final long serialVersionUID = 1L;

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

	/**
	 * Este metodo anade un elemento a la lista
	 * 
	 * @return boolean True si lo ha anadido, false si no
	 */
	public Boolean addElemt(Element element) {
		return elementos.add(element);
	}

	/**
	 * Este metodo elimina un elemento de la lista
	 * 
	 */
	public void removeElemt(Element element) {
		elementos.remove(element);
	}

	/**
	 * Metodo que devuelve el nombre, el autor, la duracion y el tipo de un elemento
	 * 
	 * @return String string con la informacion del elemento
	 */
	@Override
	public String dataString() {
		return super.getNombre() + "                 " + "Lista";
	}

	/**
	 * Este metodo reproduce una lista
	 * 
	 * @usuario usuario que solicita la reproduccion
	 */
	@Override
	public Boolean reproducir(Usuario usuario) {
		ArrayList<String> rutas = new ArrayList<>();
		rutas = getrutas(usuario);
		if (rutas.isEmpty()) {
			return false;
		}
		return Aplicacion.reproductor.reproducir(rutas.toArray(new String[0]));
	}

	/**
	 * Este metodo devuelve un array con las rutas de las canciones y albumes de la
	 * lista
	 * 
	 * @param usuario usuario que solicita el usuario
	 * @return rutas Array list con las rutas de las canciones
	 */
	public ArrayList<String> getrutas(Usuario usuario) {
		ArrayList<String> rutas = new ArrayList<>();
		for (Element element : elementos) {
			switch (element.getClass().getSimpleName()) {
			case "Cancion":
				Cancion cancion = (Cancion) element;
				if (usuario.canListenSong(cancion)) {
					System.out.println("no se puede reproducir" + cancion.getNombre());

				} else {
					rutas.add(cancion.getRuta());
					usuario.aumentarReproducidas();
					if (usuario.getClass() == UsuarioRegistrado.class
							&& !cancion.esAutor((UsuarioRegistrado) usuario)) {
						cancion.aumentarReproducciones();
						cancion.getAutor().aumentarReproducciones();
					}
				}
				break;
			case "Album":
				rutas.addAll(((Album) element).getrutas(usuario));
				break;
			case "Lista":
				if (!super.getNombre().equals(element.getNombre())) {
					rutas.addAll(((Lista) element).getrutas(usuario));
				}
				break;
			default:
				break;
			}

		}
		return rutas;
	}

	/**
	 * Metodo que devuelve los elementos
	 * 
	 * @return elementos Elementos
	 */
	public ArrayList<Element> getElementos() {
		return elementos;
	}
	
	/**
	 * Metodo que devuelve el numero de canciones del album
	 * 
	 * @return int numero de canciones
	 */
	public int getNumElements() {
		return elementos.size();
	}

	@Override
	public String toString() {
		return "Lista [elementos=" + elementos + "]";
	}
}