/**
* Clase usuario
* @author Gonzalo Madrigal, Fernando Barroso y Javier Lozano
*
*/

package principal;

/**
 * Esta clase contiene todos los atributos y metodos de un usuario
 */
public abstract class Usuario {

	/** nombre del usuario */
	private String nombre;
	
	/** contrasena del usuario */
	private String contrasena;

	/**
	 * Cantidad de reproducciones que ha realizadoun usuario dentro del periodo
	 * vigente
	 */
	private Integer reproducidas;

	/**
	 * Este constructor genera un nuevo usuario con los datos recividos como
	 * argumentos Este contrucctor solo podra ser llamado desde las clases herederas
	 * ya que no se puede instanciar un usuario
	 * 
	 * @param nombre     nombre del usuario
	 * @param contrasena contrasena del usuario
	 */
	public Usuario(String nombre, String contrasena) {
		super();
		this.nombre = nombre;
		this.contrasena = contrasena;
		reproducidas = 0;
	}

	/**
	 * Este método devuelve el nombre del usuario
	 * 
	 * @return nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Este método devuelve la contrasena del usuario
	 * 
	 * @return contrasena
	 */
	public String getContrasena() {
		return contrasena;
	}

	/**
	 * Este método devuelve la cantidad de canciones reproducidas por el usuario
	 * 
	 * @return reproducidas
	 */
	public Integer getReproducidas() {
		return reproducidas;
	}

	/**
	 * Este método recive un nombre y contrasena y devuelve true si coinciden con
	 * los del usuario
	 * 
	 * @param nombre     nombre que se quiere verificar
	 * @param contrasena contrasena que se quiere verificar
	 * @return Boolean true en caso de que ambos valores coincidan con los del
	 *         usuario
	 */
	public Boolean validar(String nombre, String contrasena) {

		return (this.nombre.equalsIgnoreCase(nombre) && this.contrasena.equals(contrasena));
	}
	
	/** Este metodo pone a cero el contador de canciones reproducidas */
	public void resetearreproducidas() {
		reproducidas = 0;
	}
	
	/** Este metodo aumenta en 1 el numero de canciones reproducidas */
	public void aumentarReproducidas() {
		reproducidas++;
	}

	
	
	

}