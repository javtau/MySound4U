/**
* Clase Sesion de usuario
* @author Gonzalo Madrigal, Fernando Barroso y Javier Lozano
*
*/

package principal;

/**
 * Esta clase contiene todos los atributos y metodos de la sesion de usuario
 */
public class SesionUsuarios extends Sesion {

	public SesionUsuarios(UsuarioRegistrado usuario) {
		super(usuario);
		// TODO Auto-generated constructor stub
	}

	public void subirCancion(Cancion cancion, Usuario usuario) {
	}

	public void borrarCancion(Cancion cancion) {
	}

	public void denunciar(Cancion cancion, Usuario usuario) {
	}

	public void pasarPremium() {
	}

	/**
	 * Este metodo reproduce una cancion. La cancion no se reproducira si la cancion
	 * es explicita, si la cancion es de otro autor y esta bloqueada o si el usuario
	 * a pasado de su limite de reoroducciones en el periodo vigente.
	 * 
	 * @param cancion cancion que se quiere reproducir
	 * @param usuario usuario que solicita la reproduccion
	 */
	@Override
	public void reproducir(Cancion cancion, Usuario usuario) {
		// TODO Auto-generated method stub

	}
	
	// TODO metodo para editar canciones
	
	/**
	 * Este método anade un nuevo cancion a un album
	 * 
	 * @param cancion cancion que se quiere anadir al album
	 * @return Boolean true si se a anadido la cancion, false en caso contrario
	 */
	public Boolean anadiraAlbum(Album album,Cancion cancion) {

		return true;
	}

	/**
	 * Este método anade un nuevo elemento a una lista, se comprobara el tipo de
	 * elemento y se usara el metodo correspondiente. En caso de que introducca un
	 * elemento incompatible no se hara nada
	 * 
	 * @param elemen objeto que se quiere anadir a la lista
	 * @return Boolean true si se a anadido el elemento, false en caso contrario
	 */
	public Boolean anadirALista(Lista lista,Element elemen) {

		return true;
	}

}