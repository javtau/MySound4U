package principal;

import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;
import java.time.chrono.ChronoPeriod;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import pads.musicPlayer.Mp3Player;
import pads.musicPlayer.exceptions.Mp3PlayerException;
import utils.FechaSimulada;

public class TEster {

	public static void main(String[] args) throws InterruptedException {
//		// TODO Auto-generated method stub
//		/*
//		 * UsuarioRegistrado yo = new UsuarioRegistrado("UVERworld", "1234", new
//		 * Date()); Cancion cancion = new Cancion("CorePride",
//		 * "UVERworld_CorePride.mp3",yo); Cancion cancion2 = new
//		 * Cancion("RookiezisPunk", "RookiezisPunk'd_InMyWorld.mp3",yo); Cancion
//		 * cancion3 = new Cancion("avicii", "avicii-levels.mp3",yo);
//		 * ArrayList<UsuarioRegistrado> usu = new ArrayList<>(); usu.add(yo);
//		 * 
//		 * System.out.println(cancion); System.out.println(yo);
//		 * System.out.println(usu.get(0)); usu.get(0).setPremium(true);
//		 * System.out.println(yo); //cancion3.reproducir(); TimeUnit.SECONDS.sleep(15);
//		 * //cancion2.stop(); //cancion3.stop(); //cancion3.reproducir();
//		 * TimeUnit.SECONDS.sleep(15); //cancion3.stop();
//		 */
//		FechaSimulada.restablecerHoyReal();
//		DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy");
//		LocalDate d;
//		d = LocalDate.parse("01/09/1989", format);
//		Usuario yo = new UsuarioRegistrado("UVERworld", "1234", d);
//		System.out.println(yo);
//		System.out.println("Tiene " + ((UsuarioRegistrado)yo).getEdad() + "años");
//		System.out.println(System.getProperty("os.name").toLowerCase());
//
//		System.out.println(FechaSimulada.getHoy() + " " + d);
//
//		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
//		ChronoPeriod period = ChronoPeriod.between(FechaSimulada.getHoy(), LocalDate.now());
//		System.out.printf("%d años, %d meses y %d días", period.get(ChronoUnit.YEARS), period.get(ChronoUnit.MONTHS),
//				period.get(ChronoUnit.DAYS));
//		yo = new UsuarioAnonimo();
//		System.out.println("\n" +yo.getClass());
		
		/*
		 DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate d= LocalDate.parse("01/02/1989", format);
		LocalDate d1 = LocalDate.parse("01/03/1989", format);
		ChronoPeriod period = ChronoPeriod.between(d, d1);
		System.out.printf("%d años, %d meses y %d días", period.get(ChronoUnit.YEARS), period.get(ChronoUnit.MONTHS),
				period.get(ChronoUnit.DAYS));
		
		period = ChronoPeriod.between(d.minusDays(d.getDayOfMonth()), d1);
		System.out.printf("%d años, %d meses y %d días", period.get(ChronoUnit.YEARS), period.get(ChronoUnit.MONTHS),
				period.get(ChronoUnit.DAYS));
				
		*/
		UsuarioRegistrado avicii = new UsuarioRegistrado("AVICII", "1234", LocalDate.now());
		
		Cancion c3 = new Cancion("Levels", "avicii-levels.mp3", avicii);
		c3.validar();
		Cancion wakeMe = new Cancion("wake me up", "avicii-wake-me-up.mp3", avicii);
		wakeMe.validar();
		Cancion brother = new Cancion("Hey brother", "avicii-hey-brother-lyric.mp3", avicii);
		Album album = new Album("mix", avicii);
		Lista lista = new Lista("miLista");
		lista.addElemt(brother);
		lista.addElemt(wakeMe);
		album.anadirCancion(c3);
		album.anadirCancion(wakeMe);
		album.anadirCancion(brother);
		avicii.addAlbum(album);
		Aplicacion api =Aplicacion.getApi();
		SesionAnonima sa = (SesionAnonima) api.getSesion();
		System.out.println("reproduccion anonimo"+ "\n");
		sa.reproducir(album);
		System.out.println("reproduccion avicii"+ "\n");
		System.out.println(c3+ "\n");
		System.out.println(wakeMe+ "\n");
		System.out.println(brother+ "\n");
		System.out.println(avicii+ "\n");
		api.loguearse("avicii", "1234");
		SesionUsuarios sesion = (SesionUsuarios) api.getSesion();
		sesion.reproducir(album);
		System.out.println(c3+ "\n");
		System.out.println(wakeMe+ "\n");
		System.out.println(brother+ "\n");
		System.out.println(avicii+ "\n");
		
		sesion.reproducir(album);
		System.out.println(c3+ "\n");
		System.out.println(wakeMe+ "\n");
		System.out.println(brother+ "\n");
		System.out.println(avicii+ "\n");
		System.out.println("reproduccion lista"+ "\n");
		System.out.println(lista);
		sesion.reproducir(lista);
		lista.addElemt(album);
		System.out.println(lista);
		sesion.reproducir(lista);
		
		System.out.println("reproduccion lista  con canciones albumes y listas"+ "\n");
		Lista lista2 = new Lista("mmiLista");
		lista2.addElemt(brother);
		lista2.addElemt(c3);
		lista.addElemt(lista);
		System.out.println(lista2);
		sesion.reproducir(lista);
		lista.addElemt(lista2);
		sesion.reproducir(lista);
		
		
		
		
		
	}
}
