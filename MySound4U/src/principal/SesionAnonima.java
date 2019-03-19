/**
* Clase SesionAnonima
* @author Fernando Barroso, Javier Lozano y Gonzalo Madrigal
*
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
	String nombre, pass, fecha;
	Date d;

	/**
	 * Este constructor genera una nueva sesion de administrador
	 * 
	 * @param usuario    Usuario asociado a esta sesion
	 * @param Aplicacion Propietaria de esta sesion
	 */
	public SesionAnonima(UsuarioAnonimo usuario, Aplicacion api) {
		super(usuario, api, new ConsolaAnonimo());
		sc = new java.util.Scanner(System.in);
	}

	public void registrarse() {
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		System.out.print("Introduzca nombre: ");
		nombre = sc.nextLine();
		System.out.print("Introduzca contraseña: ");
		pass = sc.nextLine();
		System.out.print("Introduzca fecha de nacimiento(dd/MM/yyyy): ");
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
	 * @param usuario Usuario que solicita la reproduccion
	 */
	// @Override
	public void reproducir(Cancion cancion) {
		if (cancion.esBloqueda() || cancion.esExplicita() || cancion.esValidada()) {
			return;
		}
		if (reproductor.reproducir(cancion.getRuta())) {
			usuario.aumentarReproducidas();
		}

	}

	/**
	 * Este metodo muestra las opciones para el usuario anonimo y espera a que este
	 * introduzca la accion a realizar
	 * 
	 * @return Boolean True si el usuario desea finalizar el programa
	 */
	@Override
	public Boolean programControl() {
		String opcion;
		ArrayList<Cancion> canciones = api.getLastSongs();
		int cancion;
		boolean exit = true;

		while (exit) {
			consola.clearConsole();
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
						reproductor.reproducir(canciones.get(cancion).getRuta());
					}
				} catch (NumberFormatException e) {
					System.out.println("Debe introducir el numero de la cancion.");
				}
				break;
			case "buscar":
				break;

			case "loguearse":
				System.out.print("Introduzca nombre: ");
				nombre = sc.nextLine();
				System.out.print("Introduzca contraseña: ");
				pass = sc.nextLine();
				api.loguearse(nombre, pass);
				break;

			case "registrarse":
				registrarse();
				break;

			case "salir":
				reproductor.stop();
				exit = false;
				break;

			default:
				break;
			}
		}
		return exit;
	}
}