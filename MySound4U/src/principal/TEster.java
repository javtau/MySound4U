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
		// TODO Auto-generated method stub
		/*UsuarioRegistrado yo = new UsuarioRegistrado("UVERworld", "1234", new Date());
		Cancion cancion = new Cancion("CorePride", "UVERworld_CorePride.mp3",yo);
		Cancion cancion2 = new Cancion("RookiezisPunk", "RookiezisPunk'd_InMyWorld.mp3",yo);
		Cancion cancion3 = new Cancion("avicii", "avicii-levels.mp3",yo);
		ArrayList<UsuarioRegistrado> usu = new ArrayList<>();
		usu.add(yo);
		
		System.out.println(cancion);
		System.out.println(yo);
		System.out.println(usu.get(0));
		usu.get(0).setPremium(true);
		System.out.println(yo);
		//cancion3.reproducir();
		TimeUnit.SECONDS.sleep(15);
		//cancion2.stop();
		//cancion3.stop();
		//cancion3.reproducir();
		TimeUnit.SECONDS.sleep(15);
		//cancion3.stop();
		*/
		
		DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate d;
		d = LocalDate.parse("01/09/1989", format);
		UsuarioRegistrado yo = new UsuarioRegistrado("UVERworld", "1234", d);
		System.out.println(yo);
		System.out.println("Tiene "+ yo.getEdad() +"años");
		System.out.println(System.getProperty("os.name").toLowerCase());
		FechaSimulada fecha = null;
		fecha.fijarFecha(28,04 , 2019);
		System.out.println(fecha.getHoy());
		
		 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	        ChronoLocalDate from = ChronoLocalDate.from(formatter.parse("01/01/2000"));
	        ChronoLocalDate to = ChronoLocalDate.from(formatter.parse("01/01/2015"));
	        ChronoPeriod period = ChronoPeriod.between(fecha.getHoy(), LocalDate.now());
	        System.out.printf("%d años, %d meses y %d días", period.get(ChronoUnit.YEARS), period.get(ChronoUnit.MONTHS), period.get(ChronoUnit.DAYS));

	}
}
 