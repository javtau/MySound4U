package principal;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
//import utils.ConsolaAnonimo;

/**
 * Clase principal que creara la aplicacion e ilustrara la funcionalidad 
 */
public class Demostrador2 {

	public static void main(String[] args) throws InterruptedException {		
		UsuarioAnonimo anonimo = new UsuarioAnonimo();
		Aplicacion api = new Aplicacion();
		SesionAnonima anonima = new SesionAnonima(anonimo, api);

		System.out.println("**************************");
		System.out.println("* BIENVENIDO A MySOUND4U *");
		System.out.println("**************************");
		TimeUnit.SECONDS.sleep(2);
		
		// ADMINISTRADOR
		
		
		
		
		// USUARIO ANONIMO

		System.out.println("");
		System.out.println("*******************");
		System.out.println("* Usuario anonimo *");
		System.out.println("*******************\n");

		System.out.println("Vamos a probar una a una la funcionalidad del usuario anonimo: \n");
		TimeUnit.SECONDS.sleep(2);

		ArrayList<Cancion> canciones = api.getLastSongs();

		// Reproducir una cancion
		
		System.out.println("****************");
		System.out.println("* Reproduccion *");
		System.out.println("****************");

		System.out.println("\nSe reproducira la cancion \"Levels\" \n");
		anonima.reproducir(canciones.get(2));
		TimeUnit.SECONDS.sleep(2);

		// Parar una cancion

		System.out.println("Se va a parar la cancion en 10 segundos");
		TimeUnit.SECONDS.sleep(10);
		//anonima.parar();

		// Metodo buscar

		System.out.println("");
		System.out.println("************");
		System.out.println("* Busqueda *");
		System.out.println("************");

		System.out.println("\nVamos a buscar la cancion \"Levels\" aplicando el filtro \"todo\": ");
		TimeUnit.SECONDS.sleep(2);
		api.buscar("levels", TIPO_BUSQUEDA.TODO);
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
		
		System.out.println("\nVamos a registrarnos en la aplicacion con nombre de usuario: \"Fernando\" y contrasena: \"1234\"");
		TimeUnit.SECONDS.sleep(2);
		api.registrarse("Fernando", "1234", "14/10/1994");
		TimeUnit.SECONDS.sleep(2);
		
		System.out.println("\nVamos a registrarnos en la aplicacion con nombre de usuario: \"Fernando\" y contrasena: \"1234\"");
		TimeUnit.SECONDS.sleep(2);
		api.registrarse("Fernando", "1234", "14/10/1994");
		TimeUnit.SECONDS.sleep(2);
		

		// Metodo login

		System.out.println("********************");
		System.out.println("* Inicio de sesion *");
		System.out.println("********************");

		api.loguearse("Fernando", "1234");
		TimeUnit.SECONDS.sleep(2);

		// USUARIO REGISTRADO
		
		
	}
}