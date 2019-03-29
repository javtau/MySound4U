/**
* Clase SesionAnonima
* @author Fernando Barroso, Javier Lozano y Gonzalo Madrigal
*/

package principal;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import utils.ConsolaAnonimo;

/**
 * Esta clase contiene todos los atributos y metodos de la sesion anonima
 */
public class SesionAnonima extends Sesion implements Serializable {
	static java.util.Scanner sc;
	private UsuarioAnonimo usuario;

	/**
	 * Este constructor genera una nueva sesion de administrador
	 * 
	 * @param usuario Usuario asociado a esta sesion
	 * @param api     Propietaria de esta sesion
	 */
	public SesionAnonima(UsuarioAnonimo usuario, Aplicacion api) {
		super(api, new ConsolaAnonimo());
		sc = new java.util.Scanner(System.in);
		this.usuario = usuario;
	}

	/**
	 * Este metodo reproduce una cancion. No se reproducira si esta bloqueada, es
	 * explicita o si el usuario ha pasado de su limite de reproducciones
	 * 
	 * @param cancion Cancion que se quiere reproducir
	 */
	// @Override
	public void reproducir(Cancion cancion) {
		System.out.println(usuario.getReproducidas()+ " limite:"+ api.getLimiteReproducciones());
		if (usuario.canListenSong(cancion) || usuario.getReproducidas() > api.getLimiteReproducciones()) {
			System.out.println("No se puede reproducir");
			return;
		}
		if (cancion.reproducir()) {
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
		String nombre, pass, fecha;
		int cancion;
		boolean exit = true;
		initSc();

		consola.printOptions(canciones);
		opcion = sc.nextLine();
		switch (opcion.toLowerCase()) {
		case "reproducir":
			consola.printSelectSong();
			try {
				cancion = Integer.parseInt(sc.nextLine());
				if (cancion > canciones.size() - 1 || cancion > 6 || cancion < 0) {
					System.out.println("Ha introducido un numero de cancion incorrecto");
				} else {
					System.out.println("reproduccion anonima");
					reproducir(canciones.get(cancion));
				}
			} catch (NumberFormatException e) {
				System.out.println("Debe introducir el numero de la cancion6");
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
			}

			break;

		case "loguearse":
			System.out.print("Introduzca nombre: ");
			nombre = sc.nextLine();
			System.out.print("Introduzca contrasena: ");
			pass = sc.nextLine();
			stop();
			api.loguearse(nombre, pass);
			break;

		case "registrarse":

			System.out.print("Nombre de usuario: ");
			nombre = sc.nextLine();
			System.out.print("Contrasena: ");
			pass = sc.nextLine();
			System.out.print("Introduzca su fecha de nacimiento(dd/MM/yyyy): ");
			fecha = sc.nextLine();
			api.registrarse(nombre, pass, fecha);
			break;

		case "parar":
			stop();
			break;
		case "salir":
			stop();
			exit = false;
			break;

		default:
			break;
		}
		return exit;
	}

	public void initSc() {
		sc = new java.util.Scanner(System.in);
	}
}