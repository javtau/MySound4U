/**
* Clase Cancion
* @author Gonzalo Madrigal, Fernando Barroso y Javier Lozano
*
*/

package principal;

import java.io.FileNotFoundException;

import pads.musicPlayer.Mp3Player;
import pads.musicPlayer.exceptions.Mp3PlayerException;

/**
 * Esta clase contiene todos los atributos y metodos de una cancion
 */
public class Cancion extends Element {
	private static final String PATH = "songs/";

	/** Duracion de la cancion */
	private Double duracion;

	/** Ruta de la cancion */
	private String ruta;

	/**
	 * Numero de veces que la cancion ha sido reproducida dentro del periodo vigente
	 */
	private Integer numreproducciones;

	/** Variable que indica si una cancion es explicita ( True = explicita) */
	private Boolean explicita;

	/** Variable que indica si una cancion esta bloqueada ( True = bloqueada) */
	private Boolean bloqueada;

	/** Variable que indica si una cancion esta validada ( True = validada) */
	private Boolean validada;

	/**
	 * Variable que indica si una cancion esta pendiente de revision ( True =
	 * pendiente)
	 */
	private Boolean revision;

	/** Usuario autor de la cancion */
	private UsuarioRegistrado autor;

	/** Album al que pertenece la cancion */
	private Album album;

	/** Reproductor de la cancion */
	private Mp3Player reproductor;

	/**
	 * Este constructor genera una nueva cancion con los datos recibidos como
	 * argumentos.
	 * 
	 * @param nombre nombre de la cancion
	 * @param ruta   ruta en la que se encuentra de la cancion
	 * @param autor  nombre de la cancion
	 */
	public Cancion(String nombre, String ruta, UsuarioRegistrado autor) {
		super(nombre);
		System.out.println(PATH + ruta);
		this.ruta = PATH + ruta;
		this.autor = autor;

		numreproducciones = 0;
		explicita = false;
		bloqueada = false;
		validada = false;
		revision = false;

		try {
			reproductor = new Mp3Player(this.ruta);
			duracion = (double) ((int) Mp3Player.getDuration(this.ruta) / 60);
			duracion += (double) ((int) Mp3Player.getDuration(this.ruta)) % 60 / 100;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Mp3PlayerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void validar() {
		validada = true;
	}

	public Boolean esExplicita() {
		return explicita;
	}

	public Boolean esBloqueda() {
		return bloqueada;
	}

	public Boolean esValidada() {
		return validada;
	}

	public void marcarExplicita() {
		explicita = true;
	}

	public void reproducir() {
		try {
			reproductor.stop();
			reproductor.play();
		} catch (Mp3PlayerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	

	public Album getAlbum() {
		return album;
	}

	public void stop() {
		reproductor.stop();

	}

	@Override
	public String toString() {
		return "Cancion [Nombre: " + super.getNombre() + ", duracion= " + duracion + ", numero de reproducciones= " + numreproducciones
				+ ((explicita) ? ", es " : ", no es ") + "explicita,\n\t"
				+ ((bloqueada) ? "esta " : "no esta ") + "bloqueada" 
				+ ((validada) ? ", esta " : ", no esta ") + "validada"
				+ ((revision) ? ", pendiente de revision" : "") + ", autor" 
				+ autor.getNombre() + ((album == null) ? "" : ", album=" + album.getNombre() ) + "]";
	}

}