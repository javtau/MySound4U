/**
* Clase SesionUsuarios
* @author Fernando Barroso, Javier Lozano y Gonzalo Madrigal
*/

package principal;

import java.util.ArrayList;

import utils.ConsolaRegistrado;

/**
 * Esta clase contiene todos los atributos y metodos de la sesion de usuario
 */
public class SesionUsuarios extends Sesion {
	static java.util.Scanner sc;

	/**
	 * Este constructor genera una nueva sesion de administrador
	 * 
	 * @param usuario Usuario asociado a esta sesion
	 * @param api     Propietaria de esta sesion
	 */
	public SesionUsuarios(UsuarioRegistrado usuario, Aplicacion api) {
		super(usuario, api, new ConsolaRegistrado());
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
	 * Este metodo reproduce una cancion. No se reproducira si es explicita, si es
	 * de otro autor y esta bloqueada o si el usuario ha pasado su limite de
	 * reoroducciones en el periodo vigente.
	 * 
	 * @param cancion Cancion que se quiere reproducir
	 * @param usuario Usuario que solicita la reproduccion
	 */
	@Override
	public void reproducir(Cancion cancion) {
		// TODO Auto-generated method stub

	}

	// TODO metodo para editar canciones

	/**
	 * Este metodo anade una nueva cancion a un album
	 * 
	 * @param cancion Cancion que se quiere anadir al album
	 * @param album   Album al que se quiere añadir la cancion
	 * @return Boolean True si se ha anadido la cancion, false en caso contrario
	 */
	public Boolean anadiraAlbum(Album album, Cancion cancion) {
		return false;
	}

	/**
	 * Este metodo anade un nuevo elemento a una lista, se comprobara el tipo de
	 * elemento y se usara el metodo correspondiente. En caso de que introduzca un
	 * elemento incompatible no se hara nada
	 * 
	 * @param elemen Objeto que se quiere anadir a la lista
	 * @param lista Lista en la que el objeto sera añadido
	 * @return Boolean True si se ha anadido el elemento, false en caso contrario
	 */
	public Boolean anadirALista(Lista lista, Element elemen) {
		return false;
	}

	/**
	 * Este metodo muestra las opciones para el usuario registrado y espera a que
	 * este introduzca la accion a realizar.
	 * 
	 * @return Boolean True si el usuario desea finalizar el programa
	 */
	@Override
	public Boolean programControl() {
		return false;
	}
}