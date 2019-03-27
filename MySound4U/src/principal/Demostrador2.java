package principal;

import java.io.File;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
//import utils.ConsolaAnonimo;

import utils.DataManager;
import utils.DataManager.*;

/**
 * Clase principal que creara la aplicacion e ilustrara la funcionalidad
 */
public class Demostrador2 {

	public static void main(String[] args) throws InterruptedException {
		Aplicacion api = new Aplicacion();
		SesionAnonima anonima = null;
		SesionAdmin administrador = null;
		SesionUsuarios usuario = null;

		System.out.println("**************************");
		System.out.println("* BIENVENIDO A MySOUND4U *");
		System.out.println("**************************");
		TimeUnit.SECONDS.sleep(2);

		// ADMINISTRADOR

		// USUARIO ANONIMO

		anonima = (SesionAnonima) api.getSesion();

		System.out.println("");
		System.out.println("*******************");
		System.out.println("* Usuario anonimo *");
		System.out.println("*******************\n");

		System.out.println("Vamos a probar una a una la funcionalidad del usuario anonimo: \n");
		TimeUnit.SECONDS.sleep(2);

		ArrayList<Cancion> canciones = api.getLastSongs();

		// System.out.println(canciones);

		// Reproducir una cancion

		System.out.println("****************");
		System.out.println("* Reproduccion *");
		System.out.println("****************");

		System.out.println("\nSe reproducira la cancion \"Levels\" \n");
		anonima.reproducir(canciones.get(0));
		TimeUnit.SECONDS.sleep(2);

		// Parar una cancion

		System.out.println("Se va a parar la cancion en 10 segundos");
		TimeUnit.SECONDS.sleep(2);
		anonima.stop();

		// Metodo buscar

		System.out.println("");
		System.out.println("************");
		System.out.println("* Busqueda *");
		System.out.println("************");

		System.out.println("\nVamos a buscar la cancion \"Levels\" aplicando el filtro \"todo\": ");
		TimeUnit.SECONDS.sleep(2);
		api.buscar("levels", TIPO_BUSQUEDA.TODO);
		TimeUnit.SECONDS.sleep(2);

		System.out.println("\nVamos a buscar la cancion \"RookiezisPunk\" aplicando el filtro \"todo\": ");
		TimeUnit.SECONDS.sleep(2);
		api.buscar("rook", TIPO_BUSQUEDA.TODO);
		System.out.println("\nLa cancion es explicita y, al ser un usuario anonimo, no esta disponible para el");
		TimeUnit.SECONDS.sleep(2);

		// api.buscar("", TIPO_BUSQUEDA.ALBUM);

		System.out.println("\nVamos a buscar la cancion \"CorePride\" aplicando el filtro \"titulo\": ");
		TimeUnit.SECONDS.sleep(2);
		api.buscar("core", TIPO_BUSQUEDA.TITULO);
		TimeUnit.SECONDS.sleep(2);

		System.out.println("\nVamos a buscar las canciones del autor \"System\" aplicando el filtro \"autor\": ");
		TimeUnit.SECONDS.sleep(2);
		api.buscar("system", TIPO_BUSQUEDA.AUTOR);
		TimeUnit.SECONDS.sleep(2);

		// Metodo registrarse

		System.out.println("");
		System.out.println("************");
		System.out.println("* Registro *");
		System.out.println("************");

		System.out.println(
				"\nVamos a registrarnos en la aplicacion con nombre de usuario: \"Fernando\", contrasena: \"1234\" y menor de edad");
		TimeUnit.SECONDS.sleep(2);
		api.registrarse("Fernando", "1234", "14/10/2018");
		TimeUnit.SECONDS.sleep(2);

		System.out.println("\nVamos a registrarnos en la aplicacion con el mismo nombre de usuario");
		TimeUnit.SECONDS.sleep(2);
		api.registrarse("Fernando", "1234", "14/10/2018");
		TimeUnit.SECONDS.sleep(2);

		// Metodo login

		System.out.println("********************");
		System.out.println("* Inicio de sesion *");
		System.out.println("********************");

		api.loguearse("Fernando", "1234");
		usuario = (SesionUsuarios) api.getSesion();
		TimeUnit.SECONDS.sleep(2);

		// USUARIO REGISTRADO
		
		// Reproducir una cancion
		
		canciones = api.getLastSongs();
		
		System.out.println("****************");
		System.out.println("* Reproduccion *");
		System.out.println("****************");

		usuario.reproducir(canciones.get(1));
		TimeUnit.SECONDS.sleep(2);
		System.out.println("Se va a parar la cancion");
		TimeUnit.SECONDS.sleep(2);
		usuario.stop();
		TimeUnit.SECONDS.sleep(2);
		
		// Metodo buscar

		System.out.println("");
		System.out.println("************");
		System.out.println("* Busqueda *");
		System.out.println("************");

		System.out.println("\nVamos a buscar la cancion \"RookiezisPunk\" aplicando el filtro \"todo\": ");
		TimeUnit.SECONDS.sleep(2);
		api.buscar("rook", TIPO_BUSQUEDA.TODO);
		System.out.println("\nLa cancion es explicita y el usuario es menor de edad, por tanto, no puede encontrarla.");
		
		System.out.println("Vamos a hacer logout de este usuario y registrar otro que sea mayor de edad y comprobar que el si que puede encontrarla y, ademas, reproducirla");
		
		System.out.println("");
		System.out.println("************");
		System.out.println("* Logout *");
		System.out.println("************");
		api.desloguearse();
		anonima = (SesionAnonima) api.getSesion();
		
		System.out.println("************");
		System.out.println("* Registro *");
		System.out.println("************");
		
		api.registrarse("Javi", "1234", "14/10/1994");
		
		System.out.println("********************");
		System.out.println("* Inicio de sesion *");
		System.out.println("********************");
		api.loguearse("Javi", "1234");
		usuario = (SesionUsuarios) api.getSesion();
		canciones = api.getLastSongs();
		
		System.out.println("La edad de este usuario es: " + usuario.getUsuarioRegistrado().getEdad());
		
		System.out.println("");
		System.out.println("************");
		System.out.println("* Busqueda *");
		System.out.println("************");

		System.out.println("\nVamos a buscar la cancion \"RookiezisPunk\" aplicando el filtro \"todo\": ");
		TimeUnit.SECONDS.sleep(2);
		api.buscar("rook", TIPO_BUSQUEDA.TODO);
		
		System.out.println("****************");
		System.out.println("* Reproduccion *");
		System.out.println("****************");

		System.out.println(canciones.get(1));
		TimeUnit.SECONDS.sleep(2);
		usuario.reproducir(canciones.get(1));
		TimeUnit.SECONDS.sleep(10);
		usuario.stop();
		
		

		/*
		 * api.desloguearse(); api.loguearse("admin", "admin"); administrador =
		 * (SesionAdmin) api.getSesion();
		 * 
		 * ArrayList<Denuncia> denuncias = api.getLastDenuncias(); ArrayList<Validacion>
		 * validaciones = api.getLastValidaciones(); canciones = api.getLastSongs();
		 * System.out.println(validaciones + "\n");
		 * System.out.println("********************"); System.out.println("*  *");
		 * System.out.println("********************");
		 * 
		 * System.out.println(denuncias + "\n");
		 * 
		 * System.out.println("********************"); System.out.println("*  *");
		 * System.out.println("********************"); api.buscar("levels",
		 * TIPO_BUSQUEDA.TODO); System.out.println(canciones);
		 * 
		 */
	}
}