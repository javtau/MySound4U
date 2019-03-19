/**
* Clase Aplicacion
* @author Fernando Barroso, Javier Lozano y Gonzalo Madrigal
*/

package principal;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;

/**
 * Esta clase contiene todos los atributos y metodos de la aplicacion
 */
public class Aplicacion {

	private Usuario logueado;
	static java.util.Scanner sc;

	private ArrayList<Cancion> canciones;
	private Sesion sesion;
	private ArrayList<UsuarioRegistrado> usuarios;
	private ArrayList<Album> albumes;

	/** Lista de denuncias pendientes */
	private ArrayList<Denuncia> denuncias;

	/** Lista de validaciones pendientes */
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
		Cancion c1 = new Cancion("CorePride", "UVERworld_CorePride.mp3", yo);
		Cancion c2 = new Cancion("RookiezisPunk", "RookiezisPunk'd_InMyWorld.mp3", yo);
		Cancion c3 = new Cancion("Levels", "avicii-levels.mp3", yo);
		canciones.add(c1);
		canciones.add(c2);
		canciones.add(c3);
	}

	public void loguearse(String usuario, String contrasena) {
		Boolean d = false;
		for (UsuarioRegistrado u : usuarios) {
			if ((u.getNombre().equalsIgnoreCase(usuario)) && (u.getContrasena().equals(contrasena))) {
				System.out.print("\nEl usuario " + u.getNombre() + " se ha logueado correctamente\n\n");
				d = true;
			}
		}
		if (d == false)
			System.out.println("\nUsuario y/o contraseña erroneos\n");

		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		// Pasar a la consola del usuario registrado
	}

	public void desloguearse() {

	}

	public void cierreMes() {
	}

	public ArrayList<Cancion> buscar(String busqueda, TIPO_BUSQUEDA tipo) {
		ArrayList<Cancion> match = new ArrayList<Cancion>();
		for (Cancion c : canciones) {
			switch (tipo) {
			case ALBUM:
				//
				match.add(c);
				break;

			case AUTOR:
				if (c.getAutor().toLowerCase().contains(busqueda.toLowerCase()))
					match.add(c);
				break;

			case TITULO:
				if (c.getNombre().toLowerCase().contains(busqueda.toLowerCase())) {
					match.add(c);
				}
				break;

			case TODO:
				if ((c.getAutor().toLowerCase().contains(busqueda.toLowerCase())) || (c.getNombre().toLowerCase().contains(busqueda.toLowerCase()))) {
					match.add(c);
				}

				break;

			default:
				break;
			}
		}
		if (match.isEmpty())
			System.out.println("\nNo se han encontrado coincidencias\n");
		else {
			Iterator<Cancion> iter = match.iterator();
			while (iter.hasNext()) {
				System.out.print("\n" + iter.next() + "\n");
				System.out.print("\n\n");
			}
			try {
				TimeUnit.SECONDS.sleep(2);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		return match;
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

		for (int c = 6, i = canciones.size() - 1; i >= 0; i--, c--) {
			songs.add(canciones.get(c));
		}

		return songs;
	}

	public void addUsuario(UsuarioRegistrado usuario) {
		if (usuario != null)
			usuarios.add(usuario);
	}

	public void print() {
		for (UsuarioRegistrado u : usuarios) {
			System.out.println(u);
		}
	}
}