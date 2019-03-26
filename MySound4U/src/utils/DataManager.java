/**
* Clase Usuario
* @author Fernando Barroso, Javier Lozano y Gonzalo Madrigal
*/

package utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import principal.Aplicacion;

/**
 * Esta clase contiene los metedos nece package utils;
 */

public class DataManager {
	public static final String BACK_UP_FILE = "data/MySound4U.data";

	/**
	 * Metodo que guarda el estado de toda la aplicacion
	 */
	public void saveApi(Aplicacion api) {

		ObjectOutputStream file;
		try {
			file = new ObjectOutputStream(new FileOutputStream(BACK_UP_FILE));
			file.writeObject(api);
			file.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * Metodo que recupera el estado anterior de la aplicacion
	 */
	public Aplicacion loadApi() {
		Aplicacion api;
		try {
			ObjectInputStream file = new ObjectInputStream(new FileInputStream(BACK_UP_FILE));
			api = (Aplicacion) file.readObject();
			file.close();

		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}

		return api;

	}

}
