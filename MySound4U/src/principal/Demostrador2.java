package principal;

import java.io.File;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

/**
 * Clase principal que creara la aplicacion e ilustrara la funcionalidad
 */
public class Demostrador2 {

	public static void main(String[] args) throws InterruptedException {

		File file = new File("data/MySound4U.data");

		if (file.exists())
			file.delete();

		Aplicacion api, api2 = null;
		api = Aplicacion.getApi();
		
		SesionAnonima anonima = null;
		SesionAdmin administrador = null;
		SesionUsuarios usuario = null;

		System.out.println("");
		System.out.println("**************************");
		System.out.println("* BIENVENIDO A MySOUND4U *");
		System.out.println("**************************");
		TimeUnit.SECONDS.sleep(2);

		// USUARIO ANONIMO

		anonima = (SesionAnonima) api.getSesion();
		ArrayList<Element> canciones = api.getLastSongs();
		System.out.println("\nNada mas iniciar la aplicacion, el usuario conectado es el anonimo, asi que vamos a probar su funcionalidad\n");
		TimeUnit.SECONDS.sleep(2);

		// Reproducir una cancion

		System.out.println("****************");
		System.out.println("* Reproduccion *");
		System.out.println("****************");
		TimeUnit.SECONDS.sleep(2);
		System.out.println("\nSe reproducira la cancion \"Levels\" \n");
		anonima.reproducir(canciones.get(1));
		TimeUnit.SECONDS.sleep(2);
		System.out.println("Se va a parar la cancion en 10 segundos\n");
		TimeUnit.SECONDS.sleep(2);
		anonima.stop();
		System.out.println("Vamos a comprobar que se ha aumentado el número de reproducciones de la canción \"Levels\"\n");
		TimeUnit.SECONDS.sleep(2);
		System.out.println("- Nombre de la cancion: " + canciones.get(1).getNombre() + "\n- Autor: " + ((Cancion) canciones.get(1)).getAutorNombre() 
				+ "\n- Reproducciones = " + ((Cancion) canciones.get(1)).getNumreproducciones());
		TimeUnit.SECONDS.sleep(2);

		// Metodo buscar
		System.out.println("");
		System.out.println("************");
		System.out.println("* Busqueda *");
		System.out.println("************");
		TimeUnit.SECONDS.sleep(2);
		System.out.println("\nVamos a buscar la cancion \"Levels\" aplicando el filtro \"todo\": ");
		TimeUnit.SECONDS.sleep(2);
		api.buscar("levels", TIPO_BUSQUEDA.TODO);
		TimeUnit.SECONDS.sleep(2);
		System.out.println("Vamos a buscar la cancion \"CorePride\" aplicando el filtro \"todo\": ");
		TimeUnit.SECONDS.sleep(2);
		api.buscar("rook", TIPO_BUSQUEDA.TODO);
		TimeUnit.SECONDS.sleep(2);
		System.out.println("La cancion es explicita y, al ser un usuario anonimo, no esta disponible para el");
		TimeUnit.SECONDS.sleep(2);
		System.out.println("\nVamos a buscar la cancion \"Levels\" aplicando el filtro \"titulo\": ");
		TimeUnit.SECONDS.sleep(2);
		api.buscar("lev", TIPO_BUSQUEDA.TITULO);
		TimeUnit.SECONDS.sleep(2);
		System.out.println("Vamos a buscar el autor \"System\" aplicando el filtro \"autor\": ");
		TimeUnit.SECONDS.sleep(2);
		api.buscar("system", TIPO_BUSQUEDA.AUTOR);
		TimeUnit.SECONDS.sleep(2);

		// Metodo registrarse

		System.out.println("************");
		System.out.println("* Registro *");
		System.out.println("************");
		TimeUnit.SECONDS.sleep(2);
		System.out.println("\nVamos a registrar 2 usuarios: Uno menor de edad y otro mayor de edad para comprobar las canciones explicitas");
		TimeUnit.SECONDS.sleep(2);
	    System.out.println("\nAdemas, probaremos a registrar otro usuario con el mismo nombre para comprobar que no se puede");
		TimeUnit.SECONDS.sleep(2);
		System.out.println("\nRegistro del usuario menor de edad con nombre \"Fernando\"\n");
		TimeUnit.SECONDS.sleep(2);
		api.registrarse("Fernando", "1234", "14/10/2002");
		TimeUnit.SECONDS.sleep(2);

		System.out.println("\nRegistro del usuario mayor de edad con nombre \"Javier\"\n");
		TimeUnit.SECONDS.sleep(2);
		api.registrarse("Javier", "1234", "14/10/1994");
		TimeUnit.SECONDS.sleep(2);

		System.out.println("\nVamos a registrarnos en la aplicacion con el mismo nombre de usuario \"Fernando\"\n");
		TimeUnit.SECONDS.sleep(2);
		api.registrarse("Fernando", "1234", "14/10/2018");
		TimeUnit.SECONDS.sleep(2);

		// Metodo login
		System.out.println("");
		System.out.println("********************");
		System.out.println("* Inicio de sesion *");
		System.out.println("********************");
		TimeUnit.SECONDS.sleep(2);
		api.loguearse("Fernando", "1234");
		usuario = (SesionUsuarios) api.getSesion();
		TimeUnit.SECONDS.sleep(2);
		System.out.println("La edad de este usuario es: " + usuario.getUsuarioRegistrado().getEdad());
		TimeUnit.SECONDS.sleep(2);

		// USUARIO REGISTRADO

		// Reproducir una cancion

		canciones = api.getLastSongs();

		System.out.println("");
		System.out.println("****************");
		System.out.println("* Reproduccion *");
		System.out.println("****************");
		TimeUnit.SECONDS.sleep(2);
		System.out.println("\nSe va a reproducir la cancion \"Levels\" que esta validada y no es explicita");
		usuario.reproducir(canciones.get(1));
		TimeUnit.SECONDS.sleep(2);
		System.out.println("\nSe va a parar la cancion en 10 segundos");
		TimeUnit.SECONDS.sleep(2);
		usuario.stop();
		TimeUnit.SECONDS.sleep(2);
		System.out.println("\nVamos a ver que se estan aumentando las reproducciones de la cancion\n ");
		TimeUnit.SECONDS.sleep(2);
		System.out.println("- Nombre de la cancion: " + canciones.get(1).getNombre() + "\n- Autor: "
				+ ((Cancion) canciones.get(1)).getAutorNombre() + "\n- Reproducciones = "
				+ ((Cancion) canciones.get(1)).getNumreproducciones());
		TimeUnit.SECONDS.sleep(2);
		System.out
				.println("\nVamos a ver que se estan aumentando las canciones reproducidas del usuario \"Fernando\"\n");
		TimeUnit.SECONDS.sleep(2);
		System.out.println("- Usuario: " + usuario.getUsuarioRegistrado().getNombre() + "\n- Canciones reproducidas = "
				+ usuario.getUsuarioRegistrado().getReproducidas());
		TimeUnit.SECONDS.sleep(2);

		// Denunciar

		System.out.println("");
		System.out.println("*************");
		System.out.println("* Denunciar *");
		System.out.println("*************");
		TimeUnit.SECONDS.sleep(2);
		System.out.println("\nVamos a denunciar a \"Avicii\" y a \"Gonzalo\" por supuesto plagio\n");
		TimeUnit.SECONDS.sleep(2);
		usuario.denunciar((Cancion) canciones.get(0), "Gonzalo me ha plagiado la cancion, merece un castigo!");
		usuario.denunciar((Cancion) canciones.get(1), "Avicii me ha plagiado la cancion, que cara dura!");
		System.out.println(
				"Vamos a comprobar si las canciones de \"Avicii\" y de \"Gonzalo\" han sido bloqueadas momentaneamente hasta que el administrador resuelva las denuncias\n");
		TimeUnit.SECONDS.sleep(3);
		System.out.println("- Nombre de la cancion: " + canciones.get(0).getNombre() + "\n- Autor: "
				+ ((Cancion) canciones.get(0)).getAutorNombre() + "\n- Bloqueada: " + ((Cancion) canciones.get(0)).esBloqueda());
		TimeUnit.SECONDS.sleep(2);
		System.out.println("\n- Nombre de la cancion: " + canciones.get(1).getNombre() + "\n- Autor: "
				+ ((Cancion) canciones.get(1)).getAutorNombre() + "\n- Bloqueada: " + ((Cancion) canciones.get(1)).esBloqueda());
		TimeUnit.SECONDS.sleep(2);

		// Seguir a un usuario

		System.out.println("");
		System.out.println("***********************");
		System.out.println("* Seguir a un usuario *");
		System.out.println("***********************");
		TimeUnit.SECONDS.sleep(2);
		System.out.println("\nLista de usuarios: \n");
		api.printUsers();
		TimeUnit.SECONDS.sleep(2);
		System.out.println("\nVamos a seguir a \"Javier\"\n");
		usuario.seguir(api.getUsuario(4));
		TimeUnit.SECONDS.sleep(2);
		System.out.println("Vamos a comprobar la lista de usuarios que sigue \"Fernando\" y ver si se ha seguido a \"Javier\"\n");
		TimeUnit.SECONDS.sleep(2);
		System.out.println("- Usuario: " + usuario.getUsuarioRegistrado().getNombre()
				+ "\n- Lista de usuarios seguidos: " + usuario.getUsuarioRegistrado().getSeguidos());
		TimeUnit.SECONDS.sleep(2);

		// Cambio de sesión

		System.out.println("\nCerramos la sesión del usuario \"Fernando\"");
		api.desloguearse();
		TimeUnit.SECONDS.sleep(2);
		System.out.println("\nEl usuario " + usuario.getUsuario().getNombre() + " ha hecho logout");
		TimeUnit.SECONDS.sleep(2);
		anonima = (SesionAnonima) api.getSesion();
		System.out.println("\nVamos a iniciar sesion con la cuenta \"Javier\"");
		api.loguearse("Javier", "1234");
		usuario = (SesionUsuarios) api.getSesion();
		System.out.println("La edad de este usuario es: " + usuario.getUsuarioRegistrado().getEdad());
		TimeUnit.SECONDS.sleep(2);

		// Lista de canciones
		
		canciones = api.getLastSongs();
		System.out.println("\nVamos a comprobar que canciones tiene disponibles");
		TimeUnit.SECONDS.sleep(2);
		System.out.println("\nLas canciones bloqueadas por las denuncias no estaran disponibles hasta que lo resuelva el administrador\n");
		TimeUnit.SECONDS.sleep(2);
		System.out.println(canciones);
		TimeUnit.SECONDS.sleep(2);
		
		// Reproduccion
		
		System.out.println("\nSe va a reproducir la cancion explicita \"CorePride\" para demostrar que el usuario mayor de edad si puede");
		usuario.reproducir(canciones.get(0));
		TimeUnit.SECONDS.sleep(2);
		System.out.println("\nSe va a parar la cancion en 10 segundos");
		TimeUnit.SECONDS.sleep(2);
		usuario.stop();
		System.out.println("\nVamos a ver que se estan aumentando las reproducciones de la cancion\n ");
		TimeUnit.SECONDS.sleep(2);
		System.out.println("- Nombre de la cancion: " + canciones.get(0).getNombre() + "\n- Autor: "
				+ ((Cancion) canciones.get(0)).getAutorNombre() + "\n- Reproducciones = "
				+ ((Cancion) canciones.get(0)).getNumreproducciones());
		TimeUnit.SECONDS.sleep(2);
		System.out
				.println("\nVamos a ver que se estan aumentando las canciones reproducidas del usuario \"Javier\"\n");
		TimeUnit.SECONDS.sleep(2);
		System.out.println("- Usuario: " + usuario.getUsuarioRegistrado().getNombre() + "\n- Canciones reproducidas = "
				+ usuario.getUsuarioRegistrado().getReproducidas());
		TimeUnit.SECONDS.sleep(2);
		api.desloguearse();
		
		api.loguearse("admin", "admin")
	/*	// Creacion de nueva api para probar el guardado
		
		System.out.println("\nVamos a crear una nueva aplicacion para ver que el guardado y cargado funciona correctamente");
		TimeUnit.SECONDS.sleep(2);
	
		System.out.println("");
		System.out.println("**************************************");
		System.out.println("* Creacion de nueva aplicacion API 2 *");
		System.out.println("**************************************");
		api2 = Aplicacion.getApi();
		anonima = (SesionAnonima) api2.getSesion();
		
		System.out.println("");
		System.out.println("*********************");
		System.out.println("* Registro en API 2 *");
		System.out.println("*********************");
		TimeUnit.SECONDS.sleep(2);
		System.out.println("\nRegistro de un usuario mayor de edad con nombre \"Alejandro\"\n");
		TimeUnit.SECONDS.sleep(2);
		api2.registrarse("Alejandro", "1234", "14/10/1992");
		TimeUnit.SECONDS.sleep(2);
		
		System.out.println("");
		System.out.println("******************************");
		System.out.println("* Iniciar sesion en API 2 ****");
		System.out.println("******************************");
		api2.loguearse("Alejandro", "1234");
		usuario = (SesionUsuarios) api2.getSesion();
	
		TimeUnit.SECONDS.sleep(2);
		System.out.println("La edad de este usuario es: " + usuario.getUsuarioRegistrado().getEdad());
		TimeUnit.SECONDS.sleep(2);
		api2.printUsers();
		TimeUnit.SECONDS.sleep(2);
		canciones = api.getLastSongs();
		TimeUnit.SECONDS.sleep(2);
		System.out.println(canciones);

		 * // Metodo buscar
		 * 
		 * System.out.println(""); System.out.println("************");
		 * System.out.println("* Busqueda *"); System.out.println("************");
		 * System.out.
		 * println("\nVamos a buscar la cancion \"RookiezisPunk\" aplicando el filtro \"todo\": "
		 * ); TimeUnit.SECONDS.sleep(2); api.buscar("rook", TIPO_BUSQUEDA.TODO);
		 * System.out.
		 * println("La cancion es explicita y el usuario es menor de edad, por tanto, no puede encontrarla."
		 * ); TimeUnit.SECONDS.sleep(2); System.out.println("\nVamos a hacer logout de "
		 * + usuario.getUsuario().getNombre() +
		 * ", registrar otro que sea mayor de edad y comprobar que el si que puede encontrarla y, ademas, reproducirla\n"
		 * ); TimeUnit.SECONDS.sleep(3);
		 * 
		 * System.out.println("**********"); System.out.println("* Logout *");
		 * System.out.println("**********"); System.out.println("\nEl usuario " +
		 * usuario.getUsuario().getNombre() + " ha hecho logout");
		 * TimeUnit.SECONDS.sleep(2); api.desloguearse(); anonima = (SesionAnonima)
		 * api.getSesion();
		 * 
		 * System.out.println(""); System.out.println("************");
		 * System.out.println("* Registro *"); System.out.println("************");
		 * System.out.println(
		 * "\nVamos a registrarnos en la aplicacion con nombre de usuario: \"Javi\", contrasena: \"1234\". El usuario sera mayor de edad\n"
		 * ); TimeUnit.SECONDS.sleep(2); api.registrarse("Javi", "1234", "14/10/1994");
		 * TimeUnit.SECONDS.sleep(2);
		 * 
		 * System.out.println(""); System.out.println("********************");
		 * System.out.println("* Inicio de sesion *");
		 * System.out.println("********************"); api.loguearse("Javi", "1234");
		 * TimeUnit.SECONDS.sleep(2); usuario = (SesionUsuarios) api.getSesion();
		 * canciones = api.getLastSongs();
		 * System.out.println("La edad de este usuario es: " +
		 * usuario.getUsuarioRegistrado().getEdad()); TimeUnit.SECONDS.sleep(2);
		 * 
		 * System.out.println(""); System.out.println("************");
		 * System.out.println("* Busqueda *"); System.out.println("************");
		 * System.out.
		 * println("\nVamos a buscar la cancion \"RookiezisPunk\" aplicando el filtro \"todo\": "
		 * ); TimeUnit.SECONDS.sleep(2); api.buscar("rook", TIPO_BUSQUEDA.TODO);
		 * 
		 * System.out.println("****************");
		 * System.out.println("* Reproduccion *");
		 * System.out.println("****************"); System.out.
		 * println("\nSe va a reproducir la cancion explicita \"RookiezisPunk\"");
		 * TimeUnit.SECONDS.sleep(2); usuario.reproducir(canciones.get(1));
		 * TimeUnit.SECONDS.sleep(2);
		 * System.out.println("\nSe va a parar la cancion en 10 segundos\n");
		 * TimeUnit.SECONDS.sleep(10); usuario.stop();
		 * 
		 * System.out.println("*********************");
		 * System.out.println("* Subir una cancion *");
		 * System.out.println("*********************"); System.out.
		 * println("\nVamos a ver el contenido de la carpeta songs antes de subir una cancion: \n"
		 * ); api.printDirectory(); File file2 = new
		 * File("songstoupload/QueElCieloEspereSentao.mp3"); System.out.
		 * println("\nSe va a subir la cancion \"Que el cielo espere sentao\"");
		 * usuario.subirCancion("Que el cielo espere sentao", file);
		 * TimeUnit.SECONDS.sleep(5); canciones = api.getLastSongs();
		 * System.out.println(
		 * "\nVamos a volver a ver el contenido de la carpeta y comprobar que se ha subido la cancion: \n"
		 * ); api.printDirectory(); TimeUnit.SECONDS.sleep(1); System.out.println(
		 * "\nComo aun no esta validada, solo se puede escuchar desde este usuario. Vamos a reproducirla: "
		 * ); TimeUnit.SECONDS.sleep(2);
		 * 
		 * usuario.reproducir(canciones.get(0)); TimeUnit.SECONDS.sleep(2);
		 * System.out.println("\nVamos a parar la cancion en 10 segundos");
		 * TimeUnit.SECONDS.sleep(10); usuario.stop(); System.out.
		 * println("\nVamos a validar la cancion para que puedan escucharla otros usuarios"
		 * );
		 * 
		 * /* api.desloguearse(); api.loguearse("admin", "admin"); administrador =
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