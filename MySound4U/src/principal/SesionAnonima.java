/**
* Clase SesionAnonima
* @author Fernando Barroso, Javier Lozano y Gonzalo Madrigal
*/

package principal;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import utils.ConsolaAnonimo;

/**
 * Esta clase contiene todos los atributos y metodos de la sesion anonima
 */
public class SesionAnonima extends Sesion {
	static java.util.Scanner sc;
	private UsuarioAnonimo usuario;
	
	/**
	 * Este constructor genera una nueva sesion de administrador
	 * 
	 * @param usuario Usuario asociado a esta sesion
	 * @param api     Propietaria de esta sesion
	 */
	public SesionAnonima(UsuarioAnonimo usuario, Aplicacion api) {
		super( api, new ConsolaAnonimo());
		sc = new java.util.Scanner(System.in);
		this.usuario = usuario;
	}

	/**
	 * Este metodo solicita al usuario sus datos, los comprueba, genera un nuevo
	 * usuario y lo añade a la lista de usuarios de la aplicacion
	 */
	public void registrarse() {
		Date d;
		String nombre, pass, fecha;
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

		System.out.print("Nombre de usuario: ");
		nombre = sc.nextLine();
		System.out.print("Contrase�a: ");
		pass = sc.nextLine();
		System.out.print("Introduzca su fecha de nacimiento(dd/MM/yyyy): ");
		// TODO: Comparar si es mayor o menor de edad para poder escuchar canciones
		// explicitas
		fecha = sc.nextLine();

		try {
			d = format.parse(fecha);
			api.addUsuario(new UsuarioRegistrado(nombre, pass, d));
			System.out.println("");
			api.print();
			System.out.println("");
			try {
				TimeUnit.SECONDS.sleep(3);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		} catch (java.text.ParseException e) {
			System.out.println("Formato de fecha incorrecto");
		}
	}

	/**
	 * Este metodo reproduce una cancion. No se reproducira si esta bloqueada, es
	 * explicita o si el usuario ha pasado de su limite de reproducciones
	 * 
	 * @param cancion Cancion que se quiere reproducir
	 */
	// @Override
	public void reproducir(Cancion cancion) {
		if (cancion.esBloqueda() || cancion.esExplicita() || !cancion.esValidada()
				|| usuario.getReproducidas() > Aplicacion.REPRODUCCIONES_MAX) {
			return;
		}
		if (reproductor.reproducir(cancion.getRuta())) {
			usuario.aumentarReproducidas();
		}

	}
	
	/**
	 * Este metodo devuelve el usuario de esta sesion
	 * 
	 * @return usuario Usuario que solicita la reproduccion
	 */
	public Usuario getUsuario() {
		return usuario;
	}

	/**
	 * Este metodo muestra las opciones para el usuario anonimo, espera a que este
	 * introduzca la accion a realizar y realiza las accionez oportunas
	 * 
	 * @return Boolean false si el usuario desea finalizar el programa
	 */
	@Override
	public Boolean programControl() {
		String opcion;
		ArrayList<Cancion> canciones = api.getLastSongs();
		int cancion;
		boolean exit = true;

		consola.printOptions(canciones);
		opcion = sc.nextLine();
		switch (opcion.toLowerCase()) {
		case "reproducir":
			consola.printSelectSong();
			try {
				cancion = Integer.parseInt(sc.nextLine());
				if (cancion > canciones.size() || cancion > 6 || cancion < 0) {
					System.out.println("Ha introducido un numero de cancion incorrecto.");
				} else {
					reproducir(canciones.get(cancion));
				}
			} catch (NumberFormatException e) {
				System.out.println("Debe introducir el numero de la cancion.");
			}
			break;
		case "buscar":
			TIPO_BUSQUEDA filtro;
			String busqueda;

			System.out.print("Introduzca busqueda: ");
			busqueda = sc.nextLine();
			System.out.print("Introduzca filtro de busqueda: (todo, autor, album o titulo): ");

			try {
				filtro = TIPO_BUSQUEDA.valueOf(sc.nextLine().toUpperCase());
				api.buscar(busqueda, filtro);
			} catch (IllegalArgumentException e) {
				System.out.println("\nEl filtro introducido no coincide con ninguno de los filtros disponibles\n");
				try {
					TimeUnit.SECONDS.sleep(2);
				} catch (InterruptedException ex) {
					e.printStackTrace();
				}
			}

			break;

		case "loguearse":
			String nombre, pass;
			System.out.print("Introduzca nombre: ");
			nombre = sc.nextLine();
			System.out.print("Introduzca contrasena: ");
			pass = sc.nextLine();
			reproductor.stop();
			api.loguearse(nombre, pass);
			break;

		case "registrarse":
			registrarse();
			break;

		case "parar":
			reproductor.stop();
			break;
		case "salir":
			reproductor.stop();
			exit = false;
			break;

		default:
			break;
		}
		return exit;
	}
}