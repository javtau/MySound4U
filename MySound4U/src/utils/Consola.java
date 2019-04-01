/**
* Clase Consola
* @author Fernando Barroso, Javier Lozano y Gonzalo Madrigal
*/

package utils;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

import principal.Cancion;
import principal.Element;

/**
 * Clase principal para los mensajes de consola de cada uno de los tipos de
 * usuario
 */
public abstract class Consola implements Serializable {

	/**
	 * Este metodo imprime por consola las diferentes opciones del usuario anonimo
	 * 
	 */
	public abstract void printOptions(ArrayList<Element> canciones);

	/**
	 * Este metodo imprime por consola un mensaje para que el usuario introduzca la
	 * cancion que desee reproducir
	 * 
	 */
	public void printSelectSong() {
		System.out.print("Introduzca el numero de la cancion: ");
	}
}