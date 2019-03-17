/**
* Clase Demostrador
* @author Gonzalo Madrigal, Fernando Barroso y Javier Lozano
*
*/
package principal;

/**
 * Esta clase es la clase principal que creara la aplicacion he ilustra
 * la funcionalidad de la aplicacion.
 */
public class Demostrador {

	public static void main(String[] args) {
		
		Aplicacion api = new Aplicacion();
		
		/*
		 * Este bucle cede el control a la aplicion,
		 * seguira ejecutandose hasta que el usuario decida finalizar 
		 * el programa y el metodo devuelva true.
		 */
		while (api.gestionAplicacion()) {
		}

		System.out.println("\nAdios y hasta pronto");
		
	}

}
