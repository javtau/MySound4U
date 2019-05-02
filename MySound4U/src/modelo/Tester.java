/**
registrarse* Clase Demostrador
* @author Fernando Barroso, Javier Lozano y Gonzalo Madrigal
*/
package modelo;

/**
 * Esta clase es la clase principal que creara la aplicacion e ilustrara la
 * funcionalidad de la aplicacion
 */
public class Tester {

	public static void main(String[] args) {
		Aplicacion api = null;
		api = Aplicacion.getApi();
		api.desloguearse();
		api.printUsers();
		/*
		 * api.printUsers(); api.printSongs(); api.printAlbums();
		 * System.out.println("avanza fecha"); api.avanzarSimulada(5); api.revision();
		 * api.printUsers(); api.printSongs(); api.printAlbums();
		 */
		/*
		 * Este bucle cede el control a la aplicion, seguira ejecutandose hasta que el
		 * usuario decida finalizar el programa y el metodo devuelva true
		 */
		while (api.gestionAplicacion()) {
		}
		api.save();
	}
}