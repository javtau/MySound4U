/**
* Clase ConsolaAnonima
* @author Fernando Barroso, Javier Lozano y Gonzalo Madrigal
*/

package utils;

import java.io.Serializable;
import java.util.ArrayList;

import modelo.Element;

/**
 * Clase para los mensajes de consola del usuario anonimo
 * 
 */
public class ConsolaAnonimo extends Consola implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * Este metodo imprime por consola las diferentes opciones del usuario anonimo
	 * 
	 */
	@Override
	public void printOptions(ArrayList<Element> elementos) {
		int i = 0;

		System.out.println("**************************************************************************");
		System.out.println("* Canciones:                                                             *");
		for (Element c : elementos) {
			System.out.println("*   " + i + ". " + c.dataString() + "\t                                    *");
			i++;
		}
		System.out.println("*                                                                        *");
		System.out.println("* Opciones:                                                              *");
		System.out.println("*   - Reproducir                                                         *");
		System.out.println("*   - Parar                                                              *");
		System.out.println("*   - Buscar                                                             *");
		System.out.println("*   - Loguearse                                                          *");
		System.out.println("*   - Registrarse                                                        *");
		System.out.println("*   - Salir                                                              *");
		System.out.println("**************************************************************************");
		System.out.print("\nIntroduzca la opcion deseada: ");
	}
}