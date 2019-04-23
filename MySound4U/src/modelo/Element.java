/**
* Clase Element
* @author Fernando Barroso, Javier Lozano y Gonzalo Madrigal
*/

package modelo;

import java.io.Serializable;

/**
 * Esta clase contiene todos los atributos y metodos de un elemento
 */
public abstract class Element implements Serializable {

	private static final long serialVersionUID = 1L;

	/** Nombre del elemento */
	private String nombre;

	/**
	 * Este constructor nos permite poder crear un elemento vacio
	 */
	public Element() {
	}

	/**
	 * Este constructor genera un nuevo elemento con los datos recibidos como
	 * argumentos
	 * 
	 * @param nombre Nombre de la cancion
	 */
	public Element(String nombre) {
		super();
		this.nombre = nombre;
	}

	/**
	 * Este metodo devuelve el nombre
	 * 
	 * @return nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Este metodo cambia el nombre de la cancion
	 * 
	 * @param nombre
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Este metodo reproduce un elemento y realiza las acciones necesarias para
	 * dicho elemento
	 * 
	 * @usuario usuario que solicita la reproduccion
	 */
	public abstract Boolean reproducir(Usuario usuario);

	/**
	 * Metodo que devuelve el nombre, el autor, la duracion y el tipo de un elemento
	 * 
	 * @return String string con la informacion del elemento
	 */
	public abstract String dataString();
}
