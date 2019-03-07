/**
* Clase Cancion
* @author Gonzalo Madrigal, Fernando Barroso y Javier Lozano
*
*/

package principal;

/**
 * Esta clase contiene todos los atributos y metodos de una cancion
 */
public class Cancion {

	/** Nombre de la cancion */
	private String nombre;

	/** Duracion de la cancion */
	private Double duracion;

	/** Ruta de la cancion */
	private String ruta;

	/**
	 * Numero de veces que la cancion a sido reproducida dentro del periodo vigente
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

	/**
	 * Este constructor genera una nueva cancion con los datos recividos como
	 * argumentos.
	 * 
	 * @param nombre   nombre de la cancion
	 * @param duracion duracion de la cancion
	 * @param ruta     ruta en la que se encuentra de la cancion
	 * @param autor    nombre de la cancion
	 */
	public Cancion(String nombre, Double duracion, String ruta, UsuarioRegistrado autor) {
		super();
		this.nombre = nombre;
		this.duracion = duracion;
		this.ruta = ruta;
		this.autor = autor;
		numreproducciones = 0;
		explicita = false;
		bloqueada = false;
		validada = false;
		revision = false;
	}
	
	

	public void validar() {
	}

	public Boolean esExplicita() {
		return null;
	}

	public Boolean esBloqueda() {
		return null;
	}

	public Boolean esValidada() {
		return null;
	}

	public void marcarExplicita() {
	}

}