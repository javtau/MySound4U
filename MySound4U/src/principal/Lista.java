/**
* Clase Lista
* @author Fernando Barroso, Javier Lozano y Gonzalo Madrigal
*/

package principal;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Esta clase contiene todos los atributos y metodos de una lista
 */
public class Lista extends Element implements Serializable {

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

	public Boolean addElemt(Element element) {
		return elementos.remove(element);
	}

	public void removeElemt(Element element) {
		elementos.add(element);
	}
	

	/**
	 * Metodo que devuelve el el nombre , el autor, la duracion y el tipo de un
	 * elemento
	 * 
	 * @return String string con la informacion del elemento
	 */
	@Override
	public String dataString() {
		return super.getNombre() + "                 " + "Lista";
	}
	
	/**
	 * Este metodo reproduce un elemento y realiza las acciones necesaria para dicho
	 * elelmento
	 * 
	 * @usuario usuario que solicita la reproduccion
	 */
	@Override
	public Boolean reproducir(Usuario usuario) {
		ArrayList<String> rutas;
		/*
		 * if (Aplicacion.reproductor.reproducir(ruta)) {
		 * usuario.aumentarReproducidas(); if (usuario.getClass() ==
		 * UsuarioRegistrado.class && autor != usuario) { aumentarReproducciones();
		 * autor.aumentarReproducciones(); } }
		 */
		return false;
	}

	
	
}