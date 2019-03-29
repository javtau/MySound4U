/**
* Clase Demostrador
* @author Fernando Barroso, Javier Lozano y Gonzalo Madrigal
*/
package principal;
import java.io.File;

import utils.FechaSimulada;


/**
 * Esta clase es la clase principal que creara la aplicacion e ilustrara la
 * funcionalidad de la aplicacion
 */
public class Demostrador {

	public static void main(String[] args) {
		Aplicacion api = null;
		api = Aplicacion.getApi();
		api.desloguearse();
		
		
		api.printUsers();
		api.printSongs();
		api.printAlbums();
		api.avanzarSimulada(4);
		api.revision();
		api.printSongs();
		/*
		 * Este bucle cede el control a la aplicion, seguira ejecutandose hasta que el
		 * usuario decida finalizar el programa y el metodo devuelva true
		 */
		while (api.gestionAplicacion()) {
		}
		api.save();
	}
}