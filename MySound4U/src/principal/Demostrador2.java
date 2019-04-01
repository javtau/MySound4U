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
		System.out.println(
				"\nNada mas iniciar la aplicacion, el usuario conectado es el anonimo, asi que vamos a probar su funcionalidad\n");
		TimeUnit.SECONDS.sleep(2);

		// Reproducir una cancion

		System.out.println("****************");
		System.out.println("* Reproduccion *");
		System.out.println("****************");
		TimeUnit.SECONDS.sleep(2);
		System.out.println("\nSe reproducira la cancion \"Levels\" \n");
		TimeUnit.SECONDS.sleep(2);
		anonima.reproducir(canciones.get(1));
		System.out.println("Se va a parar la cancion en 10 segundos");
		TimeUnit.SECONDS.sleep(2);
		anonima.stop();
		TimeUnit.SECONDS.sleep(2);

		// Metodo buscar

		System.out.println("");
		System.out.println("************");
		System.out.println("* Busqueda *");
		System.out.println("************");
		TimeUnit.SECONDS.sleep(2);
		System.out.println("\nVamos a buscar la cancion \"Levels\" aplicando el filtro \"todo\": \n");
		TimeUnit.SECONDS.sleep(2);
		System.out.println(api.buscar("levels", TIPO_BUSQUEDA.TODO));
		TimeUnit.SECONDS.sleep(2);
		System.out.println("\nVamos a buscar la cancion \"CorePride\" aplicando el filtro \"todo\": \n");
		TimeUnit.SECONDS.sleep(2);
		System.out.println(api.buscar("rook", TIPO_BUSQUEDA.TODO));
		TimeUnit.SECONDS.sleep(2);
		System.out.println("\nLa cancion es explicita y, al ser un usuario anonimo, no esta disponible para el\n");
		TimeUnit.SECONDS.sleep(2);
		System.out.println("Vamos a buscar el album \"Mix\": \n");
		TimeUnit.SECONDS.sleep(2);
		System.out.println(api.buscar("mix", TIPO_BUSQUEDA.ALBUM));
		TimeUnit.SECONDS.sleep(2);
		System.out.println("\nVamos a buscar la cancion \"Levels\" aplicando el filtro \"titulo\": \n");
		TimeUnit.SECONDS.sleep(2);
		System.out.println(api.buscar("lev", TIPO_BUSQUEDA.TITULO));
		TimeUnit.SECONDS.sleep(2);
		System.out.println("\nVamos a buscar las canciones del autor \"Gonzalo\" aplicando el filtro \"autor\": \n");
		TimeUnit.SECONDS.sleep(2);
		System.out.println(api.buscar("gonzalo", TIPO_BUSQUEDA.AUTOR));
		TimeUnit.SECONDS.sleep(2);

		// Metodo registrarse

		System.out.println("");
		System.out.println("************");
		System.out.println("* Registro *");
		System.out.println("************");
		TimeUnit.SECONDS.sleep(2);
		System.out.println(
				"\nVamos a registrar 2 usuarios: Uno menor de edad y otro mayor de edad para comprobar las canciones explicitas");
		TimeUnit.SECONDS.sleep(2);
		System.out.println(
				"\nAdemas, probaremos a registrar otro usuario con el mismo nombre para comprobar que no se puede");
		TimeUnit.SECONDS.sleep(2);
		System.out.println("\nRegistro del usuario menor de edad con nombre \"Fernando\"\n");
		TimeUnit.SECONDS.sleep(2);
		api.registrarse("Fernando", "1234", "14/10/2002");
		TimeUnit.SECONDS.sleep(2);

		System.out.println("\nRegistro del usuario mayor de edad con nombre \"Javier\"\n");
		TimeUnit.SECONDS.sleep(2);
		api.registrarse("Javier", "1234", "14/10/1994");
		TimeUnit.SECONDS.sleep(2);

		System.out.println(
				"\nVamos a intentar registrarnos en la aplicacion con el nombre de usuario \"Fernando\" ya existente: \n");
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
		System.out.println(" - Nombre de la cancion: " + canciones.get(1).getNombre() + "\n - Autor: "
				+ ((Cancion) canciones.get(1)).getAutorNombre() + "\n - Reproducciones = "
				+ ((Cancion) canciones.get(1)).getNumreproducciones());
		TimeUnit.SECONDS.sleep(2);
		System.out
				.println("\nVamos a ver que se estan aumentando las canciones reproducidas del usuario \"Fernando\"\n");
		TimeUnit.SECONDS.sleep(2);
		System.out.println(" - Usuario: " + usuario.getUsuarioRegistrado().getNombre()
				+ "\n - Canciones reproducidas = " + usuario.getUsuarioRegistrado().getReproducidas());
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
		System.out.println(" - Nombre de la cancion: " + canciones.get(0).getNombre() + "\n - Autor: "
				+ ((Cancion) canciones.get(0)).getAutorNombre() + "\n - Bloqueada: "
				+ ((Cancion) canciones.get(0)).esBloqueda());
		TimeUnit.SECONDS.sleep(2);
		System.out.println("\n - Nombre de la cancion: " + canciones.get(1).getNombre() + "\n - Autor: "
				+ ((Cancion) canciones.get(1)).getAutorNombre() + "\n - Bloqueada: "
				+ ((Cancion) canciones.get(1)).esBloqueda());
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
		System.out.println(
				"Vamos a comprobar la lista de usuarios que sigue \"Fernando\" y ver si se ha seguido a \"Javier\"\n");
		TimeUnit.SECONDS.sleep(2);
		System.out.println(" - Usuario: " + usuario.getUsuarioRegistrado().getNombre()
				+ "\n - Lista de usuarios seguidos: \n" + usuario.getUsuarioRegistrado().getSeguidos());
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
		System.out.println(
				"\nLas canciones bloqueadas por las denuncias no estaran disponibles hasta que lo resuelva el administrador\n");
		TimeUnit.SECONDS.sleep(2);
		System.out.println(canciones);
		TimeUnit.SECONDS.sleep(2);

		// Reproduccion

		System.out.println(
				"\nSe va a reproducir la cancion explicita \"CorePride\" para demostrar que el usuario mayor de edad si puede");
		usuario.reproducir(canciones.get(0));
		TimeUnit.SECONDS.sleep(2);
		System.out.println("\nSe va a parar la cancion en 10 segundos");
		TimeUnit.SECONDS.sleep(2);
		usuario.stop();
		System.out.println("\nVamos a ver que se estan aumentando las reproducciones de la cancion\n ");
		TimeUnit.SECONDS.sleep(2);
		System.out.println(" - Nombre de la cancion: " + canciones.get(0).getNombre() + "\n - Autor: "
				+ ((Cancion) canciones.get(0)).getAutorNombre() + "\n - Reproducciones = "
				+ ((Cancion) canciones.get(0)).getNumreproducciones());
		TimeUnit.SECONDS.sleep(2);
		System.out.println("\nVamos a ver que se estan aumentando las canciones reproducidas del usuario \"Javier\"\n");
		TimeUnit.SECONDS.sleep(2);
		System.out.println(" - Usuario: " + usuario.getUsuarioRegistrado().getNombre()
				+ "\n - Canciones reproducidas = " + usuario.getUsuarioRegistrado().getReproducidas());
		TimeUnit.SECONDS.sleep(2);
		System.out.println("\nCerramos la sesión del usuario \"Javier\"");
		TimeUnit.SECONDS.sleep(2);
		api.desloguearse();
		System.out.println("\nEl usuario " + usuario.getUsuario().getNombre() + " ha hecho logout");
		TimeUnit.SECONDS.sleep(2);

		System.out.println("\nVamos a iniciar sesion con el administrador");
		TimeUnit.SECONDS.sleep(2);
		api.loguearse("admin", "admin");
		TimeUnit.SECONDS.sleep(2);
		administrador = (SesionAdmin) api.getSesion();

		ArrayList<Denuncia> denuncias = api.getDenuncias();
		ArrayList<Validacion> validaciones = api.getValidaciones();
		canciones = api.getLastSongs();
		System.out.println(
				"Vamos a ver las canciones disponibles para el admin (son todas tanto explicitas como no y validadas o no validadas)\n");
		TimeUnit.SECONDS.sleep(2);
		api.printSongs();
		TimeUnit.SECONDS.sleep(2);
		System.out.println(
				"\nVamos a reproducir 3 segundos de cada cancion para demostrar que las reproducciones del admin no se tienen en cuenta");
		TimeUnit.SECONDS.sleep(2);

		System.out.println("\n" + canciones.get(0));
		System.out.println("\n - Reproducciones totales antes de reproducir: "
				+ ((Cancion) canciones.get(0)).getNumreproducciones());
		TimeUnit.SECONDS.sleep(2);
		administrador.reproducir(canciones.get(0));
		TimeUnit.SECONDS.sleep(1);
		System.out.println(
				"\n - Reproducciones totales tras reproducir: " + ((Cancion) canciones.get(0)).getNumreproducciones());
		TimeUnit.SECONDS.sleep(3);
		administrador.stop();

		System.out.println("\n" + canciones.get(1));
		System.out.println("\n - Reproducciones totales antes de reproducir: "
				+ ((Cancion) canciones.get(1)).getNumreproducciones());
		TimeUnit.SECONDS.sleep(2);
		administrador.reproducir(canciones.get(1));
		TimeUnit.SECONDS.sleep(1);
		System.out.println(
				"\n - Reproducciones totales tras reproducir: " + ((Cancion) canciones.get(1)).getNumreproducciones());
		TimeUnit.SECONDS.sleep(3);
		administrador.stop();

		System.out.println("\n" + canciones.get(2));
		System.out.println("\n - Reproducciones totales antes de reproducir: "
				+ ((Cancion) canciones.get(2)).getNumreproducciones());
		TimeUnit.SECONDS.sleep(2);
		administrador.reproducir(canciones.get(2));
		TimeUnit.SECONDS.sleep(1);
		System.out.println(
				"\n - Reproducciones totales tras reproducir: " + ((Cancion) canciones.get(2)).getNumreproducciones());
		TimeUnit.SECONDS.sleep(3);
		administrador.stop();

		System.out.println("\n" + canciones.get(3));
		System.out.println("\n - Reproducciones totales antes de reproducir: "
				+ ((Cancion) canciones.get(3)).getNumreproducciones());
		TimeUnit.SECONDS.sleep(2);
		administrador.reproducir(canciones.get(3));
		TimeUnit.SECONDS.sleep(1);
		System.out.println(
				"\n - Reproducciones totales tras reproducir: " + ((Cancion) canciones.get(3)).getNumreproducciones());
		TimeUnit.SECONDS.sleep(3);
		administrador.stop();

		System.out.println("\n" + canciones.get(4));
		System.out.println("\n - Reproducciones totales antes de reproducir: "
				+ ((Cancion) canciones.get(4)).getNumreproducciones());
		TimeUnit.SECONDS.sleep(2);
		administrador.reproducir(canciones.get(4));
		TimeUnit.SECONDS.sleep(1);
		System.out.println(
				"\n - Reproducciones totales tras reproducir: " + ((Cancion) canciones.get(4)).getNumreproducciones());
		TimeUnit.SECONDS.sleep(3);
		administrador.stop();

		// Validacion de una cancion

		System.out.println("\nVamos a ver la lista de validaciones pendientes: \n");
		TimeUnit.SECONDS.sleep(3);
		api.printValidaciones();
		TimeUnit.SECONDS.sleep(2);
		System.out.println("\nRookiezisPunk va a ser validada y Wake me up sera rechazada\n");
		TimeUnit.SECONDS.sleep(3);
		administrador.validar(validaciones.get(0));
		administrador.invalidar(validaciones.get(0)); // Es la 0 tambien porque al validar una de ellas, se elimina de
														// la lista

		System.out.println("Comprobacion:\n");
		TimeUnit.SECONDS.sleep(3);
		System.out.println(" - Cancion: " + canciones.get(3).getNombre());
		System.out.println(" - Validada: " + ((Cancion) canciones.get(3)).esValidada());
		TimeUnit.SECONDS.sleep(3);
		System.out.println("\n - Cancion: " + canciones.get(0).getNombre());
		System.out.println(" - Validada: " + ((Cancion) canciones.get(0)).esValidada());
		TimeUnit.SECONDS.sleep(3);

		// Gestion de las denuncias

		System.out.println(
				"\nVamos a gestionar las denuncias que hemos puesto anteriormente y la inicializada en el constructor: ");
		TimeUnit.SECONDS.sleep(2);
		System.out.println("\nLista de denuncias: \n");
		api.printDenuncias();
		TimeUnit.SECONDS.sleep(2);
		System.out.println("\nVamos a aceptar la primera denuncia de Fernando");
		TimeUnit.SECONDS.sleep(2);
		System.out.println("\nComprobacion: \n");
		TimeUnit.SECONDS.sleep(2);
		System.out.println("Gonzalo y su cancion bloqueados permanentemente: ");
		TimeUnit.SECONDS.sleep(2);
		String denunciante = api.getDenuncia(1).getDenunciante().getNombre();
		boolean bloq = api.getDenuncia(1).getDenunciante().estaBloqueado();
		System.out.println("\n - Cancion: " + api.getDenuncia(1).getCancion().getNombre());
		administrador.aceptarDenuncia(denuncias.get(1));
		System.out.println(" - Bloqueada: " + ((Cancion) canciones.get(1)).esBloqueda());
		TimeUnit.SECONDS.sleep(2);
		System.out.println("\n - Autor: " + ((Cancion) canciones.get(1)).getAutorNombre() + "\n - Bloqueado: "
				+ ((Cancion) canciones.get(1)).getAutor().estaBloqueado());
		TimeUnit.SECONDS.sleep(2);
		System.out.println("\nComprobacion de que al aceptarle la denuncia a Fernando no ha sido bloqueado: ");
		System.out.println("\n - Denunciante: " + denunciante + "\n - Bloqueado: " + bloq);
		TimeUnit.SECONDS.sleep(2);
		System.out.println(
				"\nVamos a rechazar la segunda denuncia de Fernando a Avicii, bloquearle durante 30 dias y desbloquear la cancion de Avicii: ");
		TimeUnit.SECONDS.sleep(2);
		String denunciante2 = api.getDenuncia(1).getDenunciante().getNombre();
		administrador.rechazarDenuncia(denuncias.get(1));
		System.out
				.println("\n - Denunciante: " + denunciante2 + "\n - Bloqueado: " + api.getUsuario(3).estaBloqueado());
		TimeUnit.SECONDS.sleep(2);
		System.out.println("\n - Cancion: " + canciones.get(2).getNombre() + "\n - Bloqueada: "
				+ ((Cancion) canciones.get(2)).getAutor().estaBloqueado());
		TimeUnit.SECONDS.sleep(2);
		System.out.println("\nVamos a reducir el umbral para ser premium y reducir el limite de reproducciones: ");
		TimeUnit.SECONDS.sleep(2);
		System.out.println("\n - Umbral para pasar a premium actual = " + api.getUmbralPremium());
		api.setUmbralPremium(5);
		TimeUnit.SECONDS.sleep(2);
		System.out.println(" - Umbral para pasar a premium modificado a = " + api.getUmbralPremium());
		TimeUnit.SECONDS.sleep(2);
		System.out.println("\n - Limite de reproducciones actual = " + api.getLimiteReproducciones());
		api.setLimiteReproducciones(5);
		TimeUnit.SECONDS.sleep(2);
		System.out.println(" - Limite de reproducciones modificado a = " + api.getUmbralPremium());
		TimeUnit.SECONDS.sleep(2);
		System.out.println(
				"\nVamos a cerrar sesion del admin y loguearnos con un usuario no premium para comprobar estas modificaciones");
		api.desloguearse();
		TimeUnit.SECONDS.sleep(2);
		System.out.println("\nEl " + administrador.getUsuario().getNombre() + " ha hecho logout");
		TimeUnit.SECONDS.sleep(2);

		anonima = (SesionAnonima) api.getSesion();
		System.out.println("\nVamos a iniciar sesion con la cuenta \"Javier\"");
		api.loguearse("Javier", "1234");
		usuario = (SesionUsuarios) api.getSesion();
		TimeUnit.SECONDS.sleep(2);

		canciones = api.getLastSongs();

		System.out.println(
				"Vamos a reproducir 4 veces \"Levels\" para llegar al limite de reproducciones del usuario\ny al umbral de reproducciones en la cancion para ser premium");
		TimeUnit.SECONDS.sleep(2);
		
		// 1
		System.out.println("\n - Primera reproduccion");
		usuario.reproducir(canciones.get(0));
		TimeUnit.SECONDS.sleep(2);
		usuario.stop();

		// 2
		System.out.println("\n - Segunda reproduccion");
		usuario.reproducir(canciones.get(0));
		TimeUnit.SECONDS.sleep(2);
		usuario.stop();

		// 3
		System.out.println("\n - Tercera reproduccion ");
		usuario.reproducir(canciones.get(0));
		TimeUnit.SECONDS.sleep(2);
		usuario.stop();

		// 4
		System.out.println("\n - Cuarta reproduccion ");
		usuario.reproducir(canciones.get(0));
		TimeUnit.SECONDS.sleep(2);
		usuario.stop();
		TimeUnit.SECONDS.sleep(2);

		System.out.println("\nComprobacion: ");
		TimeUnit.SECONDS.sleep(2);
		System.out.println("\n - Usuario: " + usuario.getUsuarioRegistrado().getNombre()
				+ "\n - Canciones reproducidas = " + usuario.getUsuarioRegistrado().getReproducidas());
		TimeUnit.SECONDS.sleep(2);
		System.out.println("\n - Cancion: " + canciones.get(0).getNombre() + "\n - Reproducciones de la cancion: "
				+ ((Cancion) canciones.get(0)).getNumreproducciones() + "\n - Autor de la cancion: "
				+ ((Cancion) canciones.get(0)).getAutor().getNombre()
				+ "\n - Reproducciones totales al autor de la cancion entre todas sus canciones: "
				+ ((Cancion) canciones.get(0)).getAutor().getReproducciones() + "\n");
		TimeUnit.SECONDS.sleep(2);
		System.out.println("Vamos a probar a escuchar la cancion de nuevo\n");
		TimeUnit.SECONDS.sleep(2);
		usuario.reproducir(canciones.get(0));
		TimeUnit.SECONDS.sleep(2);
		System.out.println(
				"\nVamos a avanzar la fecha para llegar al fin de mes y comprobar qque se hagan las modificaciones pertinentes:\n");
		TimeUnit.SECONDS.sleep(2);
		System.out.println(" - Eliminar validaciones rechazadas que no hayan sufrido cambios en 3 dias");
		TimeUnit.SECONDS.sleep(2);
		System.out.println(" - Desbloquear un usuario que haya puesto una denuncia falsa (30 dias despues)");
		TimeUnit.SECONDS.sleep(2);
		System.out.println(" - Pasar a premium a los usuarios que hayan llegado al umbral de reproducciones");
		TimeUnit.SECONDS.sleep(2);
		System.out.println(" - Resetear los contadores a 0 tanto de las reproducciones como de las reproducidas");
		TimeUnit.SECONDS.sleep(2);

		api.avanzarSimulada(32);
		api.revision();

		System.out.println(
				"\nVamos a comprobar que el usuario \"Avicii\" se convierte en usuario premium por numero de reproducciones conseguidas\n");
		TimeUnit.SECONDS.sleep(2);
		System.out.println(
				" - Usuario: " + api.getUsuario(1).getNombre() + "\n - Premium: " + api.getUsuario(1).esPremium());
		TimeUnit.SECONDS.sleep(2);
		System.out.println("\nComprobacion de que \"Fernando\" ha sido desbloqueado: \n");
		TimeUnit.SECONDS.sleep(2);
		System.out.println(" - Usuario: " + api.getUsuario(3).getNombre() + "\n - Bloqueado: "
				+ api.getUsuario(3).estaBloqueado() + "\n");
		TimeUnit.SECONDS.sleep(2);
		System.out.println("Comprobacion de que las reproducciones de \"Javier\" han sido restablecidas: \n");
		TimeUnit.SECONDS.sleep(2);
		System.out.println(" - Usuario: " + api.getUsuario(4).getNombre() + "\n - Numero de reproducidas: "
				+ api.getUsuario(3).getReproducidas() + "\n");
		TimeUnit.SECONDS.sleep(2);
		// VALIDACION

		api.printValidaciones();

		System.out.println("");
		System.out.println("*****************");
		System.out.println("* Subir cancion *");
		System.out.println("*****************");
		TimeUnit.SECONDS.sleep(2);
		System.out.println("\nVamos a ver el contenido de la carpeta songs antes de subir una cancion: \n");
		TimeUnit.SECONDS.sleep(2);
		api.printDirectory();
		TimeUnit.SECONDS.sleep(2);
		File file1 = new File("songstoupload/QueElCieloEspereSentao.mp3");
		System.out.println("\nSe va a subir la cancion \"Que el cielo espere sentao\"");
		TimeUnit.SECONDS.sleep(1);
		usuario.subirCancion("Que el cielo espere sentao", file1);
		TimeUnit.SECONDS.sleep(2);
		canciones = api.getLastSongs();
		System.out.println(
				"\nVamos a volver a ver el contenido de la carpeta y comprobar que se ha subido la cancion: \n");
		api.printDirectory();
		TimeUnit.SECONDS.sleep(2);
		System.out.println(
				"\nComo aun no esta validada, solo se puede escuchar desde este usuario. Vamos a reproducirla: ");
		TimeUnit.SECONDS.sleep(2);
		canciones = api.getLastSongs();
		usuario.reproducir(canciones.get(0));
		System.out.println("\nSe parara en 10 segundos");
		TimeUnit.SECONDS.sleep(2);
		usuario.stop();
		TimeUnit.SECONDS.sleep(2);

		System.out.println("\nVamos a subir mas canciones para crear un album");
		TimeUnit.SECONDS.sleep(2);
		File file2 = new File("songstoupload/BesosALaLona.mp3");
		System.out.println("\nSe va a subir la cancion \"Besos a la lona\"");
		TimeUnit.SECONDS.sleep(2);
		usuario.subirCancion("Besos a la lona", file2);
		TimeUnit.SECONDS.sleep(2);
		File file3 = new File("songstoupload/Mirame.mp3");
		System.out.println("\nSe va a subir la cancion \"Mirame\"");
		TimeUnit.SECONDS.sleep(2);
		usuario.subirCancion("Mirame", file3);
		TimeUnit.SECONDS.sleep(2);
		File file4 = new File("songstoupload/SinNoticiasDeHolanda.mp3");
		System.out.println("\nSe va a subir la cancion \"Sin noticias de Holanda\"");
		TimeUnit.SECONDS.sleep(2);
		usuario.subirCancion("Sin noticias de Holanda", file4);
		TimeUnit.SECONDS.sleep(2);
		File file5 = new File("songstoupload/BohemianRhapsody.mp3");
		System.out.println("\nSe va a subir la cancion \"Bohemian Rhapsody\"");
		TimeUnit.SECONDS.sleep(2);
		usuario.subirCancion("Bohemian Rhapsody", file5);
		TimeUnit.SECONDS.sleep(2);
		File file6 = new File("songstoupload/WeAreTheChampions.mp3");
		System.out.println("\nSe va a subir la cancion \"We are the champions\"");
		TimeUnit.SECONDS.sleep(2);
		usuario.subirCancion("We are the champions", file6);
		TimeUnit.SECONDS.sleep(2);

		System.out.println(
				"\nVamos a loguearnos con el administrador para validar todas las canciones que acabamos de subir");
		api.desloguearse();
		System.out.println("\nEl usuario " + usuario.getUsuario().getNombre() + " ha hecho logout");
		TimeUnit.SECONDS.sleep(2);
		anonima = (SesionAnonima) api.getSesion();
		api.loguearse("admin", "admin");
		TimeUnit.SECONDS.sleep(2);
		administrador = (SesionAdmin) api.getSesion();
		validaciones = api.getValidaciones();
		canciones = api.getLastSongs();
		System.out.println("Validaciones:");
		TimeUnit.SECONDS.sleep(2);
		administrador.validar(validaciones.get(1));
		administrador.validar(validaciones.get(1));
		administrador.validar(validaciones.get(1));
		administrador.validar(validaciones.get(1));
		administrador.validar(validaciones.get(1));
		administrador.validar(validaciones.get(1));
		TimeUnit.SECONDS.sleep(2);
		System.out.println("\n - Cancion: " + canciones.get(0).getNombre() + "\n - Validada: "
				+ ((Cancion) canciones.get(0)).esValidada());
		TimeUnit.SECONDS.sleep(2);
		System.out.println("\n - Cancion: " + canciones.get(1).getNombre() + "\n - Validada: "
				+ ((Cancion) canciones.get(1)).esValidada());
		TimeUnit.SECONDS.sleep(2);
		System.out.println("\n - Cancion: " + canciones.get(2).getNombre() + "\n - Validada: "
				+ ((Cancion) canciones.get(2)).esValidada());
		TimeUnit.SECONDS.sleep(2);
		System.out.println("\n - Cancion: " + canciones.get(3).getNombre() + "\n - Validada: "
				+ ((Cancion) canciones.get(3)).esValidada());
		TimeUnit.SECONDS.sleep(2);
		System.out.println("\n - Cancion: " + canciones.get(4).getNombre() + "\n - Validada: "
				+ ((Cancion) canciones.get(4)).esValidada());
		TimeUnit.SECONDS.sleep(2);
		System.out.println("\n - Cancion: " + canciones.get(5).getNombre() + "\n - Validada: "
				+ ((Cancion) canciones.get(5)).esValidada());
		TimeUnit.SECONDS.sleep(2);
		
		
		System.out.println("\nVamos a cerrar sesion del admin e iniciar con Javier para crear una lista");
		TimeUnit.SECONDS.sleep(2);
		
		api.desloguearse();
		TimeUnit.SECONDS.sleep(2);
		System.out.println("\nEl " + administrador.getUsuario().getNombre() + " ha hecho logout");
		TimeUnit.SECONDS.sleep(2);

		anonima = (SesionAnonima) api.getSesion();
		api.loguearse("Javier", "1234");
		usuario = (SesionUsuarios) api.getSesion();
		TimeUnit.SECONDS.sleep(2);

		canciones = api.getLastSongs();
		
		System.out.println("***************");
		System.out.println("* Crear album *");
		System.out.println("***************");
		TimeUnit.SECONDS.sleep(2);
		
		Album album = new Album ("Melendi", usuario.getUsuarioRegistrado());
		album.anadirCancion((Cancion)canciones.get(2));
		album.anadirCancion((Cancion)canciones.get(3));
		album.anadirCancion((Cancion)canciones.get(4));
		album.anadirCancion((Cancion)canciones.get(5));
		TimeUnit.SECONDS.sleep(2);
		System.out.println("\nSe ha creado el album \"Melendi\"\n");
		api.addAlbum(album);
		TimeUnit.SECONDS.sleep(2);

		System.out.println("***************");
		System.out.println("* Crear lista *");
		System.out.println("***************");
		TimeUnit.SECONDS.sleep(2);
		System.out.println("\nVamos a crear una lista");
		Lista lista = new Lista ("Lista Mix - Queen y Melendi");
		lista.addElemt(canciones.get(0));
		lista.addElemt(canciones.get(1));
		lista.addElemt(album);
		TimeUnit.SECONDS.sleep(2);
		System.out.println("\nSe ha creado la lista \"Lista Mix - Queen y Melendi\"");
		TimeUnit.SECONDS.sleep(2);
		
		System.out.println("\nVamos a cerrar sesion de \"Javier\" y a iniciar \"Fernando\" para buscar el album \ny reproducir album y lista");
		api.desloguearse();
		TimeUnit.SECONDS.sleep(2);
		System.out.println("\nEl usuario " + usuario.getUsuario().getNombre() + " ha hecho logout");
		TimeUnit.SECONDS.sleep(2);
		anonima = (SesionAnonima) api.getSesion();
		api.loguearse("Fernando", "1234");
		usuario = (SesionUsuarios) api.getSesion();
		TimeUnit.SECONDS.sleep(2);
		canciones = api.getLastSongs();
		TimeUnit.SECONDS.sleep(2);
		
		System.out.println("Vamos a pasar a premium con el usuario \"Fernando\" pagando\n");
		usuario.pasarPremium("1111222233334444");
		TimeUnit.SECONDS.sleep(2);
		System.out.println("\nComprobacion: \n");
		TimeUnit.SECONDS.sleep(2);
		System.out.println(" - Usuario: " + usuario.getUsuarioRegistrado().getNombre() + "\n - Premium: " + usuario.getUsuarioRegistrado().esPremium());
		TimeUnit.SECONDS.sleep(2);
		
		System.out.println("\nVamos a reproducir la lista creada por \"Javier\"");
		TimeUnit.SECONDS.sleep(2);
		usuario.reproducir(lista);
		TimeUnit.SECONDS.sleep(62);
		usuario.stop();

		System.out.println("\nVamos a buscar el album \"Melendi\": \n");
		TimeUnit.SECONDS.sleep(2);
		System.out.println(api.buscar("Melendi", TIPO_BUSQUEDA.ALBUM));
		TimeUnit.SECONDS.sleep(2);
		
		System.out.println("\nVamos a reproducir el album \"Melendi\"");
		usuario.reproducir(album);
		TimeUnit.SECONDS.sleep(42);
		usuario.stop();
	}
}