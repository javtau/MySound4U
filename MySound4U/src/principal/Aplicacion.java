/**
* Clase Aplicacion
* @author Gonzalo Madrigal, Fernando Barroso y Javier Lozano
*
*/

package principal;

import java.util.ArrayList;
import java.util.Date;

import pads.musicPlayer.Mp3Player;

/**
 * Esta clase contiene todos los atributos y metodos de la aplicacion
 */
public class Aplicacion {

	private Usuario logueado;

	private ArrayList<Cancion> canciones;
	private Sesion sesion;
	private ArrayList<UsuarioRegistrado> usuarios;
	private ArrayList<Album> albumes;

	/** lista de denuncias pendientes */
	private ArrayList<Denuncia> denuncias;

	/** lista de validaciones pendientes */
	private ArrayList<Validacion> validaciones;

	/* private Mp3Player reproductor = new Mp3Player(mp3s); */

	/**
	 * Este constructor genera una nueva sesion de administrador e inicializa todas
	 * las listas.
	 * 
	 */
	public Aplicacion(Usuario logueado) {
		super();
		this.logueado = logueado;
		canciones = new ArrayList<>();
		usuarios = new ArrayList<>();
		validaciones = new ArrayList<>();
		denuncias = new ArrayList<>();
	}

	public void loguarse(String usuario, String contrasena) {
	}

	public void desloguearse() {
	}

	public void cierreMes() {
	}

	public ArrayList buscar(String busqueda, TIPO_BUSQUEDA tipo) {

		return null;
	}

	public void comprobarRevisiones() {
	}

}