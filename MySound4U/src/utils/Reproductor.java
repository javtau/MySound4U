/**
* Clase Reproductor
* @author Fernando Barroso, Javier Lozano y Gonzalo Madrigal
*/

package utils;

import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.ArrayList;

import pads.musicPlayer.Mp3Player;
import pads.musicPlayer.exceptions.Mp3PlayerException;
import principal.Aplicacion;

/**
 * Esta clase genera un reproductor y gestionara las distintas reproducciones
 * 
 */
public class Reproductor implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	/** Reproductor n */
	private Mp3Player reproductor;

	public Reproductor() {
		try {
			reproductor = new Mp3Player();
			reproductor.add(Aplicacion.getPath() + "iniciom.mp3");
			reproductor.play();
		} catch (FileNotFoundException | Mp3PlayerException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Este metodo reproducira una cancion o una lista de canciones
	 * 
	 * @param canciones Cancion o canciones a reproducir
	 * 
	 * @return Boolean True si la cancion se ha reproducido, false en caso contrario
	 * 
	 */
	public Boolean reproducir(String... canciones) {
		ArrayList<String> cancion = new ArrayList<>();
		for (String c : canciones) {
			cancion.add(Aplicacion.getPath() + c);
		}
		try {
			reproductor.stop();
			reproductor = new Mp3Player();
			reproductor.add(cancion.toArray(new String[0]));
			reproductor.play();
		} catch (Mp3PlayerException | FileNotFoundException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	/**
	 * Este metodo para la reproduccion en curso
	 * 
	 */
	public void stop() {
		reproductor.stop();
	}
}
