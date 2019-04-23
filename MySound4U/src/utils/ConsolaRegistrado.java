/**
* Clase ConsolaRegistrado
* @author Fernando Barroso, Javier Lozano y Gonzalo Madrigal
*/

package utils;

import java.io.Serializable;
import java.util.ArrayList;

import modelo.Element;
import modelo.UsuarioRegistrado;

/**
 * Clase para los mensajes de consola del usuario registrado
 * 
 */
public class ConsolaRegistrado extends Consola implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * Este metodo imprime por consola las diferentes opciones del usuario
	 * registrado
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
		System.out.println("*   - Premium                                                            *");
		System.out.println("*   - Subir cancion                                                      *");
		System.out.println("*   - Mis canciones                                                      *");
		System.out.println("*   - Mis albumes                                                        *");
		System.out.println("*   - Mis listas                                                         *");
		System.out.println("*   - Borrar                                                             *");
		System.out.println("*   - Denunciar                                                          *");
		System.out.println("*   - Seguir                                                             *");
		System.out.println("*   - Pendientes                                                         *");
		System.out.println("*   - Logout                                                             *");
		System.out.println("*   - Salir                                                              *");
		System.out.println("**************************************************************************");
		System.out.print("\nIntroduzca la opcion deseada: ");
	}

	/**
	 * Este metodo imprime los usuarios registrados
	 * 
	 */
	public void printUsers(ArrayList<UsuarioRegistrado> usuarios) {
		int i = 0;
		System.out.println("* Usuarios:                                                             *");
		for (UsuarioRegistrado u : usuarios) {
			System.out.println("*   " + i + ". " + u.getNombre());
			i++;
		}
	}
}