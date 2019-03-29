package principal;

import java.io.File;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
//import utils.ConsolaAnonimo;

/**
 * Clase principal que creara la aplicacion e ilustrara la funcionalidad
 */
public class Demostrador2 {

	public static void main(String[] args) throws InterruptedException {
		Aplicacion api = Aplicacion.getApi();
		SesionAnonima anonima = null;
		SesionAdmin administrador = null;
		SesionUsuarios usuario = null;

		System.out.println("**************************");
		System.out.println("* BIENVENIDO A MySOUND4U *");
		System.out.println("**************************");
		TimeUnit.SECONDS.sleep(2);

		// USUARIO ANONIMO
		
		anonima = (SesionAnonima) api.getSesion();
		ArrayList<Cancion> canciones = api.getLastSongs();

		System.out.println("");
		System.out.println("*******************");
		System.out.println("* Usuario anonimo *");
		System.out.println("*******************\n");
		System.out.println("Vamos a probar una a una la funcionalidad del usuario anonimo: \n");
		TimeUnit.SECONDS.sleep(2);

		// Reproducir una cancion

		System.out.println("****************");
		System.out.println("* Reproduccion *");
		System.out.println("****************");
		System.out.println("\nSe reproducira la cancion \"Levels\" \n");
		anonima.reproducir(canciones.get(0));
		TimeUnit.SECONDS.sleep(2);
		System.out.println("Se va a parar la cancion en 10 segundos\n");
		TimeUnit.SECONDS.sleep(2);
		anonima.stop();

		// Metodo buscar

		System.out.println("************");
		System.out.println("* Busqueda *");
		System.out.println("************");
		System.out.println("\nVamos a buscar la cancion \"Levels\" aplicando el filtro \"todo\": ");
		TimeUnit.SECONDS.sleep(2);
		api.buscar("levels", TIPO_BUSQUEDA.TODO);
		TimeUnit.SECONDS.sleep(2);
		System.out.println("Vamos a buscar la cancion \"RookiezisPunk\" aplicando el filtro \"todo\": ");
		TimeUnit.SECONDS.sleep(2);
		api.buscar("rook", TIPO_BUSQUEDA.TODO);
		TimeUnit.SECONDS.sleep(2);
		System.out.println("La cancion es explicita y, al ser un usuario anonimo, no esta disponible para el");
		TimeUnit.SECONDS.sleep(2);
		// api.buscar("", TIPO_BUSQUEDA.ALBUM);
		System.out.println("\nVamos a buscar la cancion \"CorePride\" aplicando el filtro \"titulo\": ");
		TimeUnit.SECONDS.sleep(2);
		api.buscar("core", TIPO_BUSQUEDA.TITULO);
		TimeUnit.SECONDS.sleep(2);
		System.out.println("Vamos a buscar las canciones del autor \"System\" aplicando el filtro \"autor\": ");
		TimeUnit.SECONDS.sleep(2);
		api.buscar("system", TIPO_BUSQUEDA.AUTOR);
		TimeUnit.SECONDS.sleep(2);

		// Metodo registrarse

		System.out.println("************");
		System.out.println("* Registro *");
		System.out.println("************");

		System.out.println(
				"\nVamos a registrarnos en la aplicacion con nombre de usuario: \"Fernando\", contrasena: \"1234\". El usuario sera menor de edad\n");
		TimeUnit.SECONDS.sleep(2);
		api.registrarse("Fernando", "1234", "14/10/2018");
		TimeUnit.SECONDS.sleep(2);

		System.out.println("\nVamos a registrarnos en la aplicacion con el mismo nombre de usuario\n");
		TimeUnit.SECONDS.sleep(2);
		api.registrarse("Fernando", "1234", "14/10/2018");
		TimeUnit.SECONDS.sleep(2);

		// Metodo login
		System.out.println("");
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
		System.out.println("\nSe va a reproducir la cancion");
		usuario.reproducir(canciones.get(1));
		TimeUnit.SECONDS.sleep(2);
		System.out.println("\nSe va a parar la cancion en 10 segundos");
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
		System.out.println("La cancion es explicita y el usuario es menor de edad, por tanto, no puede encontrarla.");
		TimeUnit.SECONDS.sleep(2);
		System.out.println("\nVamos a hacer logout de " + usuario.getUsuario().getNombre()
				+ ", registrar otro que sea mayor de edad y comprobar que el si que puede encontrarla y, ademas, reproducirla\n");
		TimeUnit.SECONDS.sleep(3);

		System.out.println("**********");
		System.out.println("* Logout *");
		System.out.println("**********");
		System.out.println("\nEl usuario " + usuario.getUsuario().getNombre() + " ha hecho logout");
		TimeUnit.SECONDS.sleep(2);
		api.desloguearse();
		anonima = (SesionAnonima) api.getSesion();

		System.out.println("");
		System.out.println("************");
		System.out.println("* Registro *");
		System.out.println("************");
		System.out.println(
				"\nVamos a registrarnos en la aplicacion con nombre de usuario: \"Javi\", contrasena: \"1234\". El usuario sera mayor de edad\n");
		TimeUnit.SECONDS.sleep(2);
		api.registrarse("Javi", "1234", "14/10/1994");
		TimeUnit.SECONDS.sleep(2);

		System.out.println("");
		System.out.println("********************");
		System.out.println("* Inicio de sesion *");
		System.out.println("********************");
		api.loguearse("Javi", "1234");
		TimeUnit.SECONDS.sleep(2);
		usuario = (SesionUsuarios) api.getSesion();
		canciones = api.getLastSongs();
		System.out.println("La edad de este usuario es: " + usuario.getUsuarioRegistrado().getEdad());
		TimeUnit.SECONDS.sleep(2);

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
		System.out.println("\nSe va a reproducir la cancion explicita \"RookiezisPunk\"");
		TimeUnit.SECONDS.sleep(2);
		usuario.reproducir(canciones.get(1));
		TimeUnit.SECONDS.sleep(2);
		System.out.println("\nSe va a parar la cancion en 10 segundos\n");
		TimeUnit.SECONDS.sleep(10);
		usuario.stop();

		System.out.println("*********************");
		System.out.println("* Subir una cancion *");
		System.out.println("*********************");
		System.out.println("\nVamos a ver el contenido de la carpeta songs antes de subir una cancion: \n");
		api.printDirectory();
		File file = new File("songstoupload/QueElCieloEspereSentao.mp3");
		System.out.println("\nSe va a subir la cancion \"Que el cielo espere sentao\"");
		usuario.subirCancion("Que el cielo espere sentao", file);
		TimeUnit.SECONDS.sleep(5);
		canciones = api.getLastSongs();
		System.out.println(
				"\nVamos a volver a ver el contenido de la carpeta y comprobar que se ha subido la cancion: \n");
		api.printDirectory();
		TimeUnit.SECONDS.sleep(1);
		System.out.println(
				"\nComo aun no esta validada, solo se puede escuchar desde este usuario. Vamos a reproducirla: ");
		TimeUnit.SECONDS.sleep(2);

		usuario.reproducir(canciones.get(0));
		TimeUnit.SECONDS.sleep(2);
		System.out.println("\nVamos a parar la cancion en 10 segundos");
		TimeUnit.SECONDS.sleep(10);
		usuario.stop();
		System.out.println("\nVamos a validar la cancion para que puedan escucharla otros usuarios");
		
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