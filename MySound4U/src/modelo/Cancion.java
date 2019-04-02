/**
* Clase Cancion
* @author Fernando Barroso, Javier Lozano y Gonzalo Madrigal
*/

package modelo;

import java.io.FileNotFoundException;
import java.io.Serializable;

import pads.musicPlayer.Mp3Player;

/**
 * Esta clase contiene todos los atributos y metodos de una cancion
 */
public class Cancion extends Element implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	static final String PATH = Aplicacion.getPath();

	/** Duracion de la cancion */
	private Double duracion;

	/** Ruta de la cancion */
	private String ruta;

	/**
	 * Numero de veces que la cancion ha sido reproducida dentro del periodo vigente
	 */
	private Integer numreproducciones;

	/** Variable que indica si una cancion es explicita (True = explicita) */
	private Boolean explicita;

	/** Variable que indica si una cancion esta bloqueada (True = bloqueada) */
	private Boolean bloqueada;

	/** Variable que indica si una cancion esta validada (True = validada) */
	private Boolean validada;

	/**
	 * Variable que indica si una cancion esta pendiente de revision (True =
	 * pendiente)
	 */
	private Boolean revision;

	/** Usuario autor de la cancion */
	private UsuarioRegistrado autor;

	/** Album al que pertenece la cancion */
	private Album album;


	/**
	 * Este constructor genera una nueva cancion con los datos recibidos como
	 * argumentos
	 * 
	 * @param nombre Nombre de la cancion
	 * @param ruta   Ruta en la que se encuentra de la cancion
	 * @param autor  Nombre de la cancion
	 */
	public Cancion(String nombre, String ruta, UsuarioRegistrado autor) {
		super(nombre);
		this.ruta = ruta;
		this.autor = autor;

		numreproducciones = 0;
		explicita = false;
		bloqueada = false;
		validada = false;
		revision = false;

		if (ruta == "prueba") {
			duracion = (double) 20;
		} else {
			try {
				System.out.println(PATH + ruta);
				duracion = (double) ((int) Mp3Player.getDuration(PATH + ruta) / 60);
				duracion += (double) ((int) Mp3Player.getDuration(PATH + ruta)) % 60 / 100;
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * Metodo que aumenta las reproducciones de una cancion
	 * 
	 */
	public void aumentarReproducciones() {
		numreproducciones++;
	}

	/**
	 * Metodo que resetea las reproducciones de una cancion
	 * 
	 */
	public void resetearReproducciones() {
		numreproducciones = 0;
	}

	/**
	 * Metodo que valida una cancion
	 * 
	 */
	public void validar() {
		validada = true;
	}

	/**
	 * Metodo que bloquea una cancion
	 * 
	 */
	public void bloquear() {
		bloqueada = true;
	}

	/**
	 * Metodo que marca como explicita una cancion
	 * 
	 */
	public void setExplicita() {
		this.explicita = true;
	}

	/**
	 * Metodo que desbloquea una cancion
	 * 
	 */
	public void desbloquear() {
		bloqueada = false;
	}

	/**
	 * Metodo que dice si una cancion es explicita o no
	 * 
	 * @return boolean True si es explicita, false si no lo es
	 */
	public Boolean esExplicita() {
		return explicita;
	}

	/**
	 * Metodo que dice si una cancion esta bloqueada o no
	 * 
	 * @return boolean True si esta bloqueada, false si no lo esta
	 */
	public Boolean esBloqueda() {
		return bloqueada;
	}

	/**
	 * Metodo que dice si una cancion esta validada o no
	 * 
	 * @return boolean True si esta validada, false si no lo esta
	 */
	public Boolean esValidada() {
		return validada;
	}

	/**
	 * Metodo que sirve para marcar una cancion como explicita
	 * 
	 */
	public void marcarExplicita() {
		explicita = true;
	}

	/**
	 * Getter de la duracion de una cancion
	 * 
	 * @return duracion Devuelve la duracion de la cancion
	 */
	public Double getDuracion() {
		return duracion;
	}

	/**
	 * Getter de la ruta de una cancion
	 * 
	 * @return Devuelve la ruta de la cancion
	 */
	public String getRuta() {
		return ruta;
	}

	/**
	 * Setter de la ruta de una cancion
	 * 
	 */
	public void setRuta(String ruta) {
		this.ruta = PATH + ruta;
	}

	/**
	 * Getter de un album
	 * 
	 * @return album Devuelve un album
	 */
	public Album getAlbum() {
		return album;
	}

	/**
	 * Getter del autor
	 * 
	 * @return autor Devuelve el autor
	 */
	public UsuarioRegistrado getAutor() {
		return this.autor;
	}

	/**
	 * Getter del nombre del autor
	 * 
	 * @return nombre del autor Devuelve el nombre del autor
	 */
	public String getAutorNombre() {
		return this.autor.getNombre();
	}

	/**
	 * Metodo que dice si una cancion esta en revision o no
	 * 
	 * @return boolean True si lo esta, false si no
	 */
	public Boolean enRevision() {
		return revision;
	}
	
	

	/**
	 * Metodo que modifica el estado de la revision
	 * @param revision nuevo estado
	 */
	public void setRevision(Boolean revision) {
		this.revision = revision;
	}

	/**
	 * Getter del numero de reproducciones de una cancion
	 * 
	 * @return numreproducciones Devuelve el numero de reproducciones de una cancion
	 */
	public Integer getNumreproducciones() {
		return numreproducciones;
	}

	@Override
	public String toString() {
		return "Cancion [Nombre: " + super.getNombre() + ", duracion = " + duracion + ", numero de reproducciones = "
				+ numreproducciones + ((explicita) ? ", es " : ", no es ") + "explicita,\n\t"
				+ ((bloqueada) ? "esta " : "no esta ") + "bloqueada" + ((validada) ? ", esta " : ", no esta ")
				+ "validada" + ((revision) ? ", pendiente de revision" : "") + ", autor: " + autor.getNombre()
				+ ((album == null) ? "" : ", album : " + album.getNombre()) + "]";
	}

	/**
	 * Este metodo reproduce una cacion
	 * 
	 * @param usuario que quiere reproducir la cancion
	 */
	@Override
	public Boolean reproducir(Usuario usuario) {
		if (usuario.canListenSong(this)) {
			return false;
		}
		if (Aplicacion.reproductor.reproducir(ruta)) {
			usuario.aumentarReproducidas();
			if ((usuario.getClass() == UsuarioRegistrado.class) && !esAutor((UsuarioRegistrado) usuario)) {
				aumentarReproducciones();
				autor.aumentarReproducciones();
			}
			return true;
		}
		return false;
	}

	/**
	 * Metodo que comprueba si un usuario es el autor de la cancion
	 * 
	 * @param usuario
	 * @return Boolean
	 */
	public Boolean esAutor(UsuarioRegistrado usuario) {
		return autor.getNombre().equalsIgnoreCase(usuario.getNombre());
	}

	/**
	 * Metodo que devuelve el nombre, el autor, la duracion y el tipo de un elemento
	 * 
	 * @return String string con la informacion del elemento
	 */
	@Override
	public String dataString() {
		return super.getNombre() + "  " + "Duracion: " + duracion + " " + "Autor: " + autor.getNombre() + " Cancion";
	}
}