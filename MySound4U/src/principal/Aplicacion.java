/**
* Clase Aplicacion
* @author Gonzalo Madrigal, Fernando Barroso y Javier Lozano
*
*/

package principal;

import java.util.ArrayList;

import pads.musicPlayer.Mp3Player;

/**
 * Esta clase contiene todos los atributos y metodos de la aplicacion
 */
public class Aplicacion {

	private Usuario logueado;

	private ArrayList<Cancion> canciones;
	private Sesion sesion;
	private ArrayList<UsuarioRegistrado> usuarios;
	/*private Mp3Player reproductor = new Mp3Player(mp3s);*/
	
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
	}

	public void loguarse(String usuario, String contraseña) {
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