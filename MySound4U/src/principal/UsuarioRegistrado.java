/**
* Clase UsuarioRegistrado
* @author Fernando Barroso, Javier Lozano y Gonzalo Madrigal
*/

package principal;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Esta clase contiene todos los atributos y metodos de un usuario registrado
 */
public class UsuarioRegistrado extends Usuario {

	/** Fecha de nacimiento del usuario */
	private Date fechanac;

	/** Variable que indica si un usuario esta bloqueado (True = bloqueado) */
	private Boolean bloqueado;

	/** Variable que indica si un usuario es premium (True = usuario premium) */
	private Boolean premium;

	/**
	 * Cantidad de reproducciones que ha acumulado un usuario entre todas sus
	 * canciones dentro del periodo vigente
	 */
	private Integer reproducciones;

	/** Lista con las canciones de un usuario */
	private ArrayList<Cancion> canciones;

	/** Lista con los albummes de un usuario */
	private ArrayList<Album> albumes;

	/** Lista con las listas creadas por el usuario */
	private ArrayList<Lista> listas;

	/** Lista de los usuarios a los que sigue este usuario */
	private ArrayList<UsuarioRegistrado> seguidos;

	/**
	 * Este constructor genera un nuevo usuario registrado con los datos recibidos
	 * como argumentos. Se hara uso del constructor del padre
	 * 
	 * @param nombre     Nombre del usuario
	 * @param contrasena Contrasena del usuario
	 * @param fechanac   Fecha de nacimiento del usuario
	 */
	public UsuarioRegistrado(String nombre, String contrasena, Date fechanac) {
		super(nombre, contrasena);
		this.fechanac = fechanac;

		bloqueado = false;
		premium = false;
		reproducciones = 0;
		canciones = new ArrayList<>();
		albumes = new ArrayList<>();
		listas = new ArrayList<>();
		seguidos = new ArrayList<>();
	}

	/**
	 * Este metodo devuelve si el usuario esta bloqueado
	 * 
	 * @return Boolean True si el usuario esta bloqueado, false en caso contrario
	 */
	public Boolean estaBloqueado() {
		return bloqueado;
	}

	/**
	 * Este metodo actualiza el estado de bloqueado
	 * 
	 * @param bloqueado Nuevo estado al que se quiere pasar
	 */
	public void setBloqueado(Boolean bloqueado) {
		this.bloqueado = bloqueado;
	}

	/**
	 * Este metodo devuelve si el usuario es premium
	 * 
	 * @return Boolean true si el usuario es premium, false en caso contrario
	 */
	public Boolean esPremium() {
		return premium;
	}

	/**
	 * Este metodo modifica si el usuario es premium
	 * 
	 * @param premium Nuevo estado al que se quiere pasar
	 */
	public void setPremium(Boolean premium) {
		this.premium = premium;
	}

	/**
	 * Este metodo devuelve la fecha de nacimiento del usuario
	 * 
	 * @return fechanac Fecha de nacimiento del usuario
	 */
	public Date getFechanac() {
		return fechanac;
	}

	/**
	 * Este metodo anade una nueva cancion a la lista de canciones del usuario
	 * 
	 * @param cancion Cancion a anadir
	 */
	public void anadirCancion(Cancion cancion) {
		if (cancion != null)
			canciones.add(cancion);
	}

	/**
	 * Este metodo borra una cancion de la lista de canciones. Tambien se borrara de
	 * las listas y albumes que la contengan. Si al borrar una cancion de un album,
	 * este quedase vacio, tambien se procedera al borrado de dicho album
	 * 
	 * @param cancion Cancion que se desea borrar
	 * @return Boolean True si se a borrado la cancion, false en caso contrario
	 */
	public void borrarCancion(Cancion cancion) {
		canciones.remove(cancion);
		Album album = cancion.getAlbum();
		if (album.isEmpty()) {
			albumes.remove(album);
		}
	}

	/** Este metodo pone a cero los contadores de reproducciones y reproducidas */
	public void resetearContadores() {
		reproducciones = 0;
		super.resetearreproducidas();
	}

	/** Este metodo aumenta en 1 el numero de reproducciones totales */
	public void aumentarReproducciones() {
		reproducciones++;
	}

	/**
	 * Este metodo aumenta en 1 el numero de canciones reproducidas por el susuario
	 */
	public void aumentarReproducidas() {
		super.aumentarReproducidas();

	}

	/**
	 * Este metodo anade un usuario registrado a la lista de usuarios seguidos
	 * 
	 * @param usuario Usuario que se quiere anadir
	 * @return Boolean True si se ha anadido la cancion, false en caso contrario
	 */
	public Boolean seguir(UsuarioRegistrado usuario) {
		if (usuario != null) {
			seguidos.add(usuario);
			return true;
		}
		return false;
	}

	/**
	 * Este metodo crea una nueva lista y la anade a lista de listas
	 * 
	 * @param nombre Nombre de la lista
	 */
	public void crearLista(String nombre) {
		listas.add(new Lista(nombre));
	}

	/**
	 * Este metodo crea un nuevo album y lo anade a lista de albumes
	 * 
	 * @param nombre Nombre del album
	 */
	public void crearAlbum(String nombre) {
		albumes.add(new Album(nombre));
	}

	@Override
	public String toString() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		return "Usuario registrado " + ((premium) ? "premium " : "") + " [Nombre: " + super.getNombre()
				+ ", fecha de nacimiento: " + dateFormat.format(fechanac) + ", reproducidas = " + super.getReproducidas()
				+ ", reproducciones = " + reproducciones + "]";
	}
}