/**
* Clase ConsolaRegistrado
* @author Fernando Barroso, Javier Lozano y Gonzalo Madrigal
*/

package utils;

import java.util.ArrayList;

import principal.Cancion;

/**
 * Clase para los mensajes de consola del usuario registrado
 */
public class ConsolaRegistrado extends Consola {

	/**
	 * Este metodo imprime por consola las diferentes opciones del usuario
	 * registrado
	 */
	@Override
	public void printOptions(ArrayList<Cancion> canciones) {
		int i = 0;
		System.out.println("**************************************************************************");
		System.out.println("* Canciones:                                                             *");
		for (Cancion c : canciones) {
			System.out.println("*   " + i + ". " + c.getNombre() + "  " + "Duracion: " + c.getDuracion() + " "
					+ "Autor: " + c.getAutorNombre() + "\t                                    *");
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
		System.out.println("*   - Denunciar                                                          *");
		System.out.println("*   - Pendientes                                                         *");
		System.out.println("*   - Logout                                                             *");
		System.out.println("*   - Salir                                                              *");
		System.out.println("**************************************************************************");
		System.out.print("\nIntroduzca la opcion deseada: ");
	}
}