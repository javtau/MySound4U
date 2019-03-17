/**
* Clase reproductor
* @author Gonzalo Madrigal, Fernando Barroso y Javier Lozano
*
*/

package utils;

import java.io.FileNotFoundException;

import pads.musicPlayer.Mp3Player;
import pads.musicPlayer.exceptions.Mp3PlayerException;

/**
 * Esta clase genera un reproductor y gestionara las distintas reproduciones
 */
public class Reproductor {
	/** Reproductor n */
	private Mp3Player reproductor;
	
		
	public Reproductor() {
		try {
			reproductor = new Mp3Player("songs/iniciom.mp3");
			reproductor.play();
		} catch (FileNotFoundException | Mp3PlayerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Este metodo reproducira una cancion o una lista de canciones
	 * @param canciones cancion o canciones a reproducir
	 * @return Boolean true si la cancion se ha reproducido, false en caso contrario
	 */
	public Boolean reproducir(String...canciones) {
		try {
			reproductor.stop();
			reproductor = new Mp3Player(canciones);
			reproductor.play();
		} catch (Mp3PlayerException | FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public void stop() {
		reproductor.stop();
	}

}
