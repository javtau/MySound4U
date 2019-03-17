/**
* Clase Demostrador
* @author Gonzalo Madrigal, Fernando Barroso y Javier Lozano
*
*/

package utils;

import java.io.IOException;
import java.util.ArrayList;

import principal.Cancion;

/**
 * Esta clase es la clase, es la clase principal para los mensajes de consola de
 * cada uno de los tipos de usuario .
 */
public abstract class Consola {

	/**
	 * Este metodo imprime por consola las deferentes opciones del usuario anonimo.
	 */
	public abstract void printOptions(ArrayList<Cancion> canciones);

	/**
	 * Este metodo imprime por consola un mensaje para que el usuario introduzca la
	 * cancion que desee reproducir.
	 */
	public void printSelectSong() {
		System.out.print("Introduzca el numero de la cancion a reproducir");
	}
	
	public void clearConsole () {
		
	}

}
