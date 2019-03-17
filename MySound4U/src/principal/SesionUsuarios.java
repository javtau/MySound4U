/**
* Clase Sesion de usuario
* @author Gonzalo Madrigal, Fernando Barroso y Javier Lozano
*
*/

package principal;

import utils.ConsolaAnonimo;

/**
 * Esta clase contiene todos los atributos y metodos de la sesion de usuario
 */
public class SesionUsuarios extends Sesion {

	/**
	 * Este constructor genera una nueva sesion de administrador
	 * 
	 * @param usuario    Usuario asociado a esta sesion
	 * @param Aplicacion propietaria de esta sesion
	 */
	public SesionUsuarios(UsuarioRegistrado usuario, Aplicacion api) {
		super(usuario, api, new ConsolaAnonimo());
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
	public void reproducir(Cancion cancion) {
		// TODO Auto-generated method stub

	}

	// TODO metodo para editar canciones

	/**
	 * Este método anade un nuevo cancion a un album
	 * 
	 * @param cancion cancion que se quiere anadir al album
	 * @return Boolean true si se a anadido la cancion, false en caso contrario
	 */
	public Boolean anadiraAlbum(Album album, Cancion cancion) {

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
	public Boolean anadirALista(Lista lista, Element elemen) {

		return true;
	}

	/**
	 * Este metodo muestra las opciones para el usuario registrado, y espera a que
	 * este introduzca la accion a realizar.
	 * 
	 * @return Boolean true si el usuario desea finalizar el programa
	 */
	@Override
	public Boolean programControl() {
		return false;
	}
}