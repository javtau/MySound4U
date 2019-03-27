/**
* Clase SesionAdmin
* @author Fernando Barroso, Javier Lozano y Gonzalo Madrigal
*/

package principal;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import utils.ConsolaAdmin;
import utils.FechaSimulada;

/**
 * Esta clase contiene todos los atributos y metodos de la sesion de
 * administrador
 */
public class SesionAdmin extends Sesion implements Serializable {
	private Administrador usuario;
	static java.util.Scanner sc;

	/**
	 * Este constructor genera una nueva sesion de administrador
	 * 
	 * @param usuario    Usuario asociado a esta sesion
	 * @param Aplicacion Propietaria de esta sesion
	 */
	public SesionAdmin(Administrador usuario, Aplicacion api) {
		super(api, new ConsolaAdmin());
		sc = new java.util.Scanner(System.in);
		this.usuario = usuario;
	}

	/**
	 * Este metodo reproduce una cancion. Se reproducira independientemente de las
	 * restricciones que pueda tener
	 * 
	 * @param cancion Cancion que se quiere reproducir
	 */
	// @Override
	public void reproducir(Cancion cancion) {
		reproductor.reproducir(cancion.getRuta());
	}

	/**
	 * Este metodo acepta una denuncia. Bloquea la cancion y bloquea al autor
	 * indefinidamente
	 * 
	 * @param denuncia a aceptar
	 */
	public void aceptarDenuncia(Denuncia denuncia) {
		denuncia.getCancion().getAutor().setBloqueado(true);
		api.addBloqueado(denuncia.getCancion().getAutor(), LocalDate.MAX);
		denuncia.getCancion().bloquear();
		api.deleteDenuncia(denuncia);
	}

	/**
	 * Este metodo rechaza una denuncia. Desbloquea la cancion y bloquea al
	 * denunciante 30 dias
	 * 
	 * @param denuncia a rechazar
	 */
	public void rechazarDenuncia(Denuncia denuncia) {
		denuncia.getDenunciante().setBloqueado(true);
		api.addBloqueado(denuncia.getDenunciante(), FechaSimulada.getHoy().plusDays(30));
		denuncia.getCancion().desbloquear();
		api.deleteDenuncia(denuncia);
	}

	/**
	 * Este metodo valida una cancion
	 * 
	 * @param cancion a validar
	 */
	public void validar(Validacion validacion) {
		validacion.getCancion().validar();
		api.deleteValidacion(validacion);

	}

	/**
	 * Este metodo invalida una cancion, es decir, da tres dias para que se
	 * rectifique.
	 * 
	 * @param cancion a invalidar
	 */

	public void invalidar(Validacion validacion) {
		validacion.setPlazo(FechaSimulada.getHoy().plusDays(3));
	}

	/**
	 * Este metodo devuelve el usuario de esta sesion
	 * 
	 * @return usuario Usuario que solicita la reproduccion
	 */
	public Usuario getUsuario() {
		return usuario;
	}

	public void setLimiteReproducciones(int lim) {
	}

	public void setUmbralPremium(int lim) {
	}

	/**
	 * Este metodo marca como explicita una cancion
	 * 
	 * @param cancion a marcar como explicita
	 */

	public void marcarExplicita(Cancion cancion) {
		cancion.setExplicita();
	}

	@Override
	/**
	 * Este metodo muestra las opciones para el administrador y espera a que este
	 * introduzca la accion a realizar
	 * 
	 * @return Boolean True si el usuario desea finalizar el programa
	 */
	public Boolean programControl() {

		String opcion, gestion;
		ArrayList<Cancion> canciones = api.getLastSongs();
		ArrayList<Denuncia> denuncias = api.getLastDenuncias();
		ArrayList<Validacion> validaciones = api.getLastValidaciones();
		int cancion, elec = 0;
		boolean exit = true;

		consola.printOptions(canciones);
		opcion = sc.nextLine();
		switch (opcion.toLowerCase()) {
		case "reproducir":
			consola.printSelectSong();
			try {
				cancion = Integer.parseInt(sc.nextLine());
				if (cancion > canciones.size() || cancion > 6 || cancion < 0) {
					System.out.println("Ha introducido un numero de cancion incorrecto");
				} else {
					reproductor.reproducir(canciones.get(cancion).getRuta());
				}
			} catch (NumberFormatException e) {
				System.out.println("Debe introducir el numero de la cancion");
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

		case "desloguearse":
			stop();
			api.desloguearse();
			break;

		case "gestionar":
			((ConsolaAdmin) consola).printOptionsAdmin(denuncias, validaciones);
			System.out.print("Indique que desea gestionar, una  validacion o una denuncia: ");
			gestion = sc.nextLine();
			switch (gestion.toLowerCase()) {
			case "validacion":
				System.out.print("Introduzca el numero de la validacion: ");
				elec = Integer.parseInt(sc.nextLine());
				if (elec > validaciones.size() || elec > 6 || elec < 0) {
					System.out.println("Ha introducido un numero de validacion incorrecto");
				} else {

					System.out.print("¿Desea validar o invalidar la cancion? ");
					gestion = sc.nextLine();
					if (gestion.equalsIgnoreCase("validar")) {
						validar(api.getValidacion(elec));
						System.out.print("Cancion validada\n");
					} else if (gestion.equalsIgnoreCase("invalidar")) {
						invalidar(validaciones.get(elec));
						System.out.print(validaciones.get(elec).getCancion().getNombre() + "Cancion invalidada\n");
					}
				}
				break;

			case "denuncia":
				System.out.print("Introduzca el numero de la denuncia: ");
				elec = Integer.parseInt(sc.nextLine());
				if (elec > denuncias.size() || elec > 6 || elec < 0) {
					System.out.println("Ha introducido un numero de validacion incorrecto.");
				} else {
					System.out.print("¿Desea aceptar o rechazar la denuncia? ");
					gestion = sc.nextLine();
					if (gestion.equalsIgnoreCase("aceptar")) {
						aceptarDenuncia(denuncias.get(elec));
						System.out.print("Denuncia aceptada\n");
					} else if (gestion.equalsIgnoreCase("rechazar")) {
						rechazarDenuncia(denuncias.get(elec));
						System.out.print("Denuncia aceptada\n");
					}
				}
				break;

			default:
				break;
			}

			break;

		case "cambiar limite gratis":
			System.out.print("Introduzca el numero de reproducciones limite para usuarios no premium: ");
			elec = Integer.parseInt(sc.nextLine());
			setLimiteReproducciones(elec);
			System.out.print("Nuevos parametros:\n\tUmbral Premium:" + api.getUmbralPremium()
					+ "\n\tLimite reproducciones gratis:" + api.getLimiteReproducciones() + "\n");
			break;

		case "cambiar umbral premium":
			System.out.print("Introduzca el numero de reproducciones minimas para pasar a premium: ");
			elec = Integer.parseInt(sc.nextLine());
			setUmbralPremium(elec);
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
}