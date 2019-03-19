/**
* Clase Demostrador
* @author Gonzalo Madrigal, Fernando Barroso y Javier Lozano
*
*/

package utils;

import java.util.ArrayList;

import principal.Cancion;

/**
 * Esta clase es la clase, es la clase para los mensajes de consola de del
 * usuario anonimo .
 */
public class ConsolaAnonimo extends Consola {

	/**
	 * Este metodo imprime por consola las deferentes opciones del usuario anonimo.
	 */
	@Override
	public void printOptions(ArrayList<Cancion> canciones) {
		int i =0;
		System.out.println("**************************************************************************");
		System.out.println("*Canciones:                                                              *");
		for (Cancion c : canciones) {
			System.out.println("*  "+i+"."+c.getNombre()+"  "+c.getDuracion()+"\t\t\t*");
			i++;
		}		
		System.out.println("*                                                                        *");
		System.out.println("* Opciones:                                                              *");
		System.out.println("*  - Reproducir                                                          *");
		System.out.println("*  - Stop                                                                *");
		System.out.println("*  - Buscar                                                              *");
		System.out.println("*  - Loguearse                                                           *");
		System.out.println("*  - Registrarse                                                         *");
		System.out.println("*  - Salir                                                               *");
		System.out.println("**************************************************************************");
		System.out.print("Introduzca la opcion deseada: ");

	}

}
