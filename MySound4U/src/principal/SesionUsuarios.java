/**
* Clase SesionUsuarios
* @author Fernando Barroso, Javier Lozano y Gonzalo Madrigal
*/

package principal;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import javax.swing.JFileChooser;

import es.uam.eps.padsof.telecard.OrderRejectedException;
import es.uam.eps.padsof.telecard.TeleChargeAndPaySystem;
import utils.ConsolaRegistrado;

/**
 * Esta clase contiene todos los atributos y metodos de la sesion de usuario
 */
public class SesionUsuarios extends Sesion implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	static java.util.Scanner sc;
	private UsuarioRegistrado usuario;

	/**
	 * Este constructor genera una nueva sesion de administrador
	 * 
	 * @param usuario Usuario asociado a esta sesion
	 * @param api     Propietaria de esta sesion
	 */
	public SesionUsuarios(UsuarioRegistrado usuario, Aplicacion api) {
		super(api, new ConsolaRegistrado());
		sc = new java.util.Scanner(System.in);
		this.usuario = usuario;
	}

	/**
	 * Metodo que recibe la ruta de una cancion y su nombre, se copia la cancion en
	 * la carpeta de la aplicacion y se anade la cancion a la lista el sistema
	 * 
	 * @param nombre Nombre de la cancio
	 * @param origen Ruta de la cancion que se quiere subir
	 */
	public void subirCancion(String nombre, File origen) {
		Cancion c;
		if (!origen.exists()) {
			return;
		}

		try {
			// What to do with the file, e.g. display it in a TextArea
			Files.copy(origen.toPath(), new FileOutputStream(Aplicacion.getPath() + origen.getName()));

			c = new Cancion(nombre, origen.getName(), (UsuarioRegistrado) usuario);
			api.addCancion(c);
			usuario.addCancion(c);
			api.addValidacion(new Validacion(c, LocalDate.MAX));

		} catch (IOException ex) {
			System.out.println("problem accessing file" + origen.getAbsolutePath());
		}
	}

	/**
	 * Este metodo crea una nueva lista y la anade a lista de listas
	 * 
	 * @param nombre Nombre de la lista
	 */
	public void crearLista(String nombre) {
		Lista lista = new Lista(nombre);
		usuario.addLista(lista);
	}

	/**
	 * Este metodo crea un nuevo album y lo anade a lista de albumes
	 * 
	 * @param nombre Nombre del album
	 */
	public void crearAlbum(String nombre) {
		Album album = new Album(nombre, usuario);
		api.addAlbum(album);
		usuario.addAlbum(album);
	}

	public void borrarCancion(Cancion cancion) {
		if (cancion.getAutor() != usuario) {
			System.out.println("No puede borrar una cancion de otro usuario.");
			return;
		}
		usuario.borrarCancion(cancion);
		api.deleteValidacion(api.getValidacion(cancion));
		api.borrarCancion(cancion);
	}

	public void denunciar(Cancion cancion, String comentario) {
		if (usuario.equals(cancion.getAutor())) {
			System.out.println("No se puede denunciar a si mismo");
			return;
		}
		Denuncia denuncia = new Denuncia(cancion, usuario, comentario);
		api.addDenuncia(denuncia);
		cancion.bloquear();
	}

	public void seguir(UsuarioRegistrado usuario2) {
		if (usuario.getSeguidos().contains(usuario2)) {
			System.out.println("Ya sigue a este usuario.");
			return;
		} else if (usuario.equals(usuario2)) {
			System.out.println("No se puede seguir a si mismo.");
			return;
		}

		usuario.seguir(usuario2);
	}

	/**
	 * Metodo que recibe un numero de tarjeta, lo comprueba y si se puede hacer el
	 * cargo se pasa al usuario a premium
	 * 
	 * @param numTarjeta
	 */
	public void pasarPremium(String numTarjeta) {
		try {
			TeleChargeAndPaySystem.charge(numTarjeta, "Pago premium", 20, true);
			api.pasarPremium(usuario);
		} catch (OrderRejectedException e) {
			System.out.println("Invalida");
		}
	}

	/**
	 * Metodo que permita cambiar el autor y ruta de una cancion
	 * 
	 * @param cancion
	 */
	public void editarCancion(Cancion cancion, String newName, File file) {
		if (!cancion.esValidada() && cancion.enRevision() && cancion.esAutor(usuario)) {
			try {
				// What to do with the file, e.g. display it in a TextArea
				Files.copy(file.toPath(), new FileOutputStream(Aplicacion.getPath() + file.getName()));
				cancion.setRuta(file.getName());
				cancion.setNombre(newName);
				cancion.setRevision(false);
				Validacion v = api.getValidacionbySong(cancion);
				v.setPlazo(LocalDate.MAX);
			} catch (IOException ex) {
				System.out.println("problem accessing file" + file.getAbsolutePath());
			}
		}

	}

	/**
	 * Este metodo reproduce una cancion. No se reproducira si es explicita, si es
	 * de otro autor y esta bloqueada o si el usuario ha pasado su limite de
	 * reproducciones en el periodo vigente.
	 * 
	 * @param cancion Cancion que se quiere reproducir
	 * @param usuario Usuario que solicita la reproduccion
	 */
	@Override
	public void reproducir(Element elemento) {
		if ((!usuario.esPremium() && usuario.getReproducidas() >= api.getLimiteReproducciones())) {
			System.out.println("Este usuario ha llegado al limite de reproducciones. Pasa a premium");
			return;
		}

		elemento.reproducir(usuario);
	}

	// TODO metodo para editar canciones

	/**
	 * Este metodo anade una nueva cancion a un album
	 * 
	 * @param cancion Cancion que se quiere anadir al album
	 * @param album   Album al que se quiere anadir la cancion
	 * @return Boolean True si se ha anadido la cancion, false en caso contrario
	 */
	public Boolean anadiraAlbum(Album album, Cancion cancion) {
		return album.anadirCancion(cancion);
	}

	/**
	 * Este metodo anade un nuevo elemento a una lista, se comprobara el tipo de
	 * elemento y se usara el metodo correspondiente. En caso de que introduzca un
	 * elemento incompatible no se hara nada
	 * 
	 * @param elemen Objeto que se quiere anadir a la lista
	 * @param lista  Lista en la que el objeto sera anadido
	 * @return Boolean True si se ha anadido el elemento, false en caso contrario
	 */
	public Boolean anadirALista(Lista lista, Element elemen) {
		return lista.addElemt(elemen);
	}

	/**
	 * Este metodo devuelve el usuario de esta sesion
	 * 
	 * @return usuario Usuario que solicita la reproduccion
	 */
	public Usuario getUsuario() {
		return usuario;
	}

	public UsuarioRegistrado getUsuarioRegistrado() {
		return usuario;
	}

	/**
	 * Este metodo muestra las opciones para el usuario registrado y espera a que
	 * este introduzca la accion a realizar.
	 * 
	 * @return Boolean True si el usuario desea finalizar el programa
	 */
	@Override
	public Boolean programControl() {

		String opcion, comentario;
		ArrayList<Element> elementos = api.getLastSongs();
		ArrayList<UsuarioRegistrado> usuarios = api.getUsuarios();
		int index, seguido;
		boolean exit = true;

		consola.printOptions(elementos);
		opcion = sc.nextLine();
		switch (opcion.toLowerCase()) {
		case "reproducir":
			consola.printSelectSong();
			try {
				index = Integer.parseInt(sc.nextLine());
				if (index > elementos.size() - 1 || index > 6 || index < 0) {
					System.out.println("Ha introducido un numero de cancion incorrecto.");
				} else {
					reproducir(elementos.get(index));
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

		case "desloguearse":
			stop();
			api.desloguearse();
			break;

		case "subir":
			String nombre;
			System.out.print("Introduzca nombre: ");
			nombre = sc.nextLine();

			JFileChooser fileChooser = new JFileChooser();
			int returnVal = fileChooser.showOpenDialog(null);
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				File origen = fileChooser.getSelectedFile();
				subirCancion(nombre, origen);
			} else {
				System.out.println("File access cancelled by user");
			}
			elementos = api.getLastSongs();
			break;

		case "seguir":
			((ConsolaRegistrado) consola).printUsers(usuarios);
			System.out.println("Introduzca el numero del usuario al que desea seguir");
			try {
				seguido = Integer.parseInt(sc.nextLine());
				if (seguido > usuarios.size() - 1 || seguido < 0) {
					System.out.println("Ha introducido un numero de usuario incorrecto");
				} else {
					seguir(api.getUsuario(seguido));
					System.out.println("Usuarios seguidos: ");
					((ConsolaRegistrado) consola).printUsers(usuario.getSeguidos());
				}
			} catch (NumberFormatException e) {
				System.out.println("Debe introducir el numero del usuario");
			}
			break;

		case "denunciar":
			consola.printSelectSong();
			try {
				index = Integer.parseInt(sc.nextLine());
				if (index > elementos.size() - 1 || index > 6 || index < 0) {
					System.out.println("Ha introducido un numero de cancion incorrecto");
				} else if (elementos.get(index).getClass() == Cancion.class) {
					System.out.println("No ha seleccionado una cancion");
				} else {
					System.out.println("Por favor, introduzca el motivo de la denuncia: ");
					comentario = sc.nextLine();
					denunciar((Cancion) elementos.get(index), comentario);
				}
			} catch (NumberFormatException e) {
				System.out.println("Debe introducir el numero de la cancion.");
			}
			break;
		case "borrar":
			consola.printSelectSong();
			try {
				index = Integer.parseInt(sc.nextLine());
				if (index > elementos.size() - 1 || index > 6 || index < 0) {
					System.out.println("Ha introducido un numero de cancion incorrecto");
				} else if (elementos.get(index).getClass() == Cancion.class) {
					System.out.println("No ha seleccionado una cancion");
				} else {
					borrarCancion((Cancion) elementos.get(index));
				}
			} catch (NumberFormatException e) {
				System.out.println("Debe introducir el numero de la cancion");
			}
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