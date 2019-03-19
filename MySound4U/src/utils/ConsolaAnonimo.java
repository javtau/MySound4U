/**
* Clase ConsolaAnonima
* @author Fernando Barroso, Javier Lozano y Gonzalo Madrigal
*/

package utils;

import java.util.ArrayList;

import principal.Cancion;
import principal.UsuarioRegistrado;

/**
 * Clase para los mensajes de consola del usuario anonimo
 */
public class ConsolaAnonimo extends Consola {

	/**
	 * Este metodo imprime por consola las diferentes opciones del usuario anonimo
	 */
	@Override
	public void printOptions(ArrayList<Cancion> canciones) {
		int i = 0;
		
		System.out.println("**************************************************************************");
		System.out.println("* Canciones:                                                             *");
		for (Cancion c : canciones) {
			System.out.println("*   " + i + ". " + c.getNombre() + "  " + "Duracion: " + c.getDuracion() + " " + "Autor: " + c.getAutor()
					+ "\t                                    *");
			i++;
		}
		System.out.println("*                                                                        *");
		System.out.println("* Opciones:                                                              *");
		System.out.println("*   - Reproducir                                                         *");
		System.out.println("*   - Buscar                                                             *");
		System.out.println("*   - Loguearse                                                          *");
		System.out.println("*   - Registrarse                                                        *");
		System.out.println("*   - Salir                                                              *");
		System.out.println("**************************************************************************");
		System.out.print("\nIntroduzca la opcion deseada: ");
	}
}
