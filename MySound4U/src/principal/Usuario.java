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
	
	/** contraseña del usuario */
	private String contraseña;

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
	 * @param contraseña contraseña del usuario
	 */
	public Usuario(String nombre, String contraseña) {
		super();
		this.nombre = nombre;
		this.contraseña = contraseña;
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
	 * Este método devuelve la contraseña del usuario
	 * 
	 * @return contraseña
	 */
	public String getContraseña() {
		return contraseña;
	}

	/**
	 * Este método devuelve la cantidad de canciones reproducidas por el usuario
	 * 
	 * @return energia
	 */
	public Integer getReproducidas() {
		return reproducidas;
	}

	/**
	 * Este método recive un nombre y contraseña y devuelve true si coinciden con
	 * los del usuario
	 * 
	 * @param nombre     nombre que se quiere verificar
	 * @param contraseña contraseña que se quiere verificar
	 * @return Boolean true en caso de que ambos valores coincidan con los del
	 *         usuario
	 */
	public Boolean validar(String nombre, String contraseña) {

		return (this.nombre.equalsIgnoreCase(nombre) && this.contraseña.equals(contraseña));
	}

}