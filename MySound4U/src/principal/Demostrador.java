/**
* Clase Demostrador
* @author Fernando Barroso, Javier Lozano y Gonzalo Madrigal
*/
package principal;

/**
 * Esta clase es la clase principal que creara la aplicacion e ilustrara la
 * funcionalidad de la aplicacion
 */
public class Demostrador {

	public static void main(String[] args) {

		Aplicacion api = new Aplicacion();

		/*
		 * Este bucle cede el control a la aplicion, seguira ejecutandose hasta que el
		 * usuario decida finalizar el programa y el metodo devuelva true
		 */
		while (api.gestionAplicacion()) {
		}
	}
}