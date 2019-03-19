/**
* Clase Aplicacion
* @author Gonzalo Madrigal, Fernando Barroso y Javier Lozano
*
*/

package principal;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Date;

import pads.musicPlayer.Mp3Player;
import pads.musicPlayer.exceptions.Mp3PlayerException;
import sun.awt.RepaintArea;

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

	/**
	 * Este constructor genera una nueva sesion de administrador e inicializa todas
	 * las listas
	 * 
	 */
	public Aplicacion() {
		super();
		this.logueado = logueado;
		canciones = new ArrayList<>();
		usuarios = new ArrayList<>();
		validaciones = new ArrayList<>();
		denuncias = new ArrayList<>();
		sesion = new SesionAnonima(new UsuarioAnonimo(), this);
		UsuarioRegistrado yo = new UsuarioRegistrado("SYSTEM", "1234", new Date());
		Cancion c1 = new Cancion("CorePride", "UVERworld_CorePride.mp3",yo);
		Cancion c2 = new Cancion("RookiezisPunk", "RookiezisPunk'd_InMyWorld.mp3",yo);
		Cancion c3 = new Cancion("avicii", "avicii-levels.mp3",yo);
		canciones.add(c1);
		canciones.add(c2);
		canciones.add(c3);
		
	}

	public void loguearse(String usuario, String contrasena) {
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

	public Boolean gestionAplicacion() {
		return sesion.programControl();
	}

	public ArrayList<Cancion> getLastSongs() {
		if (canciones.size() < 7) {
			return new ArrayList<Cancion>(canciones);
		}
		ArrayList<Cancion> songs = new ArrayList<>();
		
		for (int c = 6, i = canciones.size() -1; i >=0; i--, c-- ) {
			songs.add(canciones.get(c));
		}
			
		return songs;
	}
	
	/**
	 * Metodo que añade un nuevo usuario registrado a la lista de usuarios
	 * @param usuario
	 */
	public void addUsuario(UsuarioRegistrado usuario) {
		if (usuario != null) usuarios.add(usuario);
	}
	
	
	
	/**
	 * Metodo que imprime por consola todos los usuarios del sistema
	 */
	public void print() {
		for (UsuarioRegistrado u : usuarios) {
			System.out.println(u);
		}
	}
	
	public Boolean checkUser(String name, String pass) {
		for (UsuarioRegistrado u : usuarios) {
			if (u.validar(name,  pass)) return true;
		}
		
		return false;
	}

}