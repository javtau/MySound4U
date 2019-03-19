package principal;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import pads.musicPlayer.Mp3Player;
import pads.musicPlayer.exceptions.Mp3PlayerException;

public class TEster {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		UsuarioRegistrado yo = new UsuarioRegistrado("UVERworld", "1234", new Date());
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
		TimeUnit.SECONDS.sleep(15);
		
	}

}
