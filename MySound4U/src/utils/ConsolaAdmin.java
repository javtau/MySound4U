/**
* Clase ConsolaAdmin
* @author Fernando Barroso, Javier Lozano y Gonzalo Madrigal
*/

package utils;

import java.io.Serializable;
import java.util.ArrayList;

import modelo.Denuncia;
import modelo.Element;
import modelo.Validacion;

/**
 * Clase para los mensajes de consola del administrador
 */
public class ConsolaAdmin extends Consola implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * Este metodo imprime por consola las diferentes denuncias y validaciones
	 * disponibles para el admin
	 * 
	 */
	public void printOptionsAdmin(ArrayList<Denuncia> denuncias, ArrayList<Validacion> validaciones) {
		int i = 0;
		System.out.println("* Denuncias:                                                             *");
		for (Denuncia d : denuncias) {
			System.out.println("*   " + i + ". " + d.getCancion().getNombre() + "  " + "Denunciante: "
					+ d.getDenunciante().getNombre() + " " + "Comentario: " + d.getComentario()
					+ "\t                                    *");
			i++;
		}
		i = 0;
		System.out.println("* Validaciones:                                                             *");
		for (Validacion v : validaciones) {
			System.out.println("*   " + i + ". " + v.getCancion().getNombre() + "  " + "Plazo expira el: "
					+ v.getPlazo() + "\t                                    *");
			i++;
		}
	}

	/**
	 * Este metodo imprime por consola las diferentes opciones del usuario
	 * administrador
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
		System.out.println("*   - Gestionar                                                          *");
		System.out.println("*   - Marcar explicita                                                   *");
		System.out.println("*   - Cambiar limite gratis                                              *");
		System.out.println("*   - Cambiar umbral premium                                             *");
		System.out.println("*   - Desloguearse                                                       *");
		System.out.println("*   - Salir                                                              *");
		System.out.println("**************************************************************************");
		System.out.print("\nIntroduzca la opcion deseada: ");
	}
}