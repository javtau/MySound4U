package testsJUnit;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import modelo.Album;
import modelo.Aplicacion;
import modelo.Cancion;
import modelo.Lista;
import modelo.UsuarioRegistrado;
import utils.FechaSimulada;

public class UsuarioRegistradoTest {
	UsuarioRegistrado usuario;

	@Before
	public void setUp() throws Exception {
		usuario = new UsuarioRegistrado("nombre", "passwd", FechaSimulada.getHoy().minusYears(15));

	}

	@Test
	public void testgetEdad() {
		assertTrue(usuario.getEdad() == 15);
	}

	@Test
	public void testestaBloqeuado() {
		assertTrue(!usuario.estaBloqueado());
	}

	@Test
	public void testsetBloqeuado() {
		usuario.setBloqueado(true);
		assertTrue(usuario.estaBloqueado());
	}

	@Test
	public void testgetCanciones() {
		Cancion cancion = new Cancion("prueba", "prueba", usuario);
		usuario.addCancion(cancion);
		assertTrue(usuario.getCanciones().contains(cancion));
	}

	@Test
	public void testsetPremium() {
		usuario.setPremium(true);
		assertTrue(usuario.esPremium());
	}

	@Test
	public void testgetFechaNac() {
		assertTrue(FechaSimulada.getHoy().minusYears(15).equals(usuario.getFechanac()));
	}

	@Test
	public void testborrarCancion() {
		Cancion cancion = new Cancion("prueba", "prueba", usuario);
		usuario.addCancion(cancion);
		usuario.borrarCancion(cancion);
		assertTrue(!usuario.getCanciones().contains(cancion));
	}

	@Test
	public void testaddAlbum() {
		Album album = new Album("Prueba", usuario);
		usuario.addAlbum(album);
		assertTrue(usuario.getAlbumes().contains(album));
	}

	@Test
	public void testaddLista() {
		Lista lista = new Lista("Prueba");
		usuario.addLista(lista);
		assertTrue(usuario.getListas().contains(lista));
	}

	@Test
	public void testdeleteAlbum() {
		Album album = new Album("Prueba", usuario);
		usuario.addAlbum(album);
		usuario.borrarAlbum(album);
		assertFalse(usuario.getAlbumes().contains(album));
	}

	@Test
	public void testdeleteLista() {
		Lista lista = new Lista("Prueba");
		/* usuario.borrarLista(lista); */
		assertFalse(usuario.getListas().contains(lista));
	}

	@Test
	public void testgetReproducidas() {
		usuario.aumentarReproducidas();
		assertTrue(usuario.getReproducidas() == 1);
	}

	@Test
	public void testSeguir() {
		UsuarioRegistrado usuario2 = new UsuarioRegistrado("nombre", "passwd", FechaSimulada.getHoy().minusYears(25));
		usuario.seguir(usuario2);
		assertTrue(usuario.getSeguidos().contains(usuario2));
	}

	@Test
	public void testiniciarSesion() {
		Aplicacion api = null;
		assertNotNull(usuario.iniciarSesion(api));
	}

	@Test
	public void testcanListenSong() {
		Cancion cancion = new Cancion("prueba", "prueba", usuario);
		cancion.bloquear();
		assertTrue(usuario.canListenSong(cancion));
	}

	@Test
	public void testcanListenSong2() {
		Cancion cancion = new Cancion("prueba", "prueba", usuario);
		cancion.setExplicita();
		assertTrue(usuario.canListenSong(cancion));
	}

	@Test
	public void testcanListenSong3() {
		UsuarioRegistrado usuario2 = new UsuarioRegistrado("nombre", "passwd", FechaSimulada.getHoy().minusYears(15));
		Cancion cancion = new Cancion("prueba", "prueba", usuario2);
		assertFalse(usuario.canListenSong(cancion));
	}

	@Test
	public void testcanListenSong4() {
		Cancion cancion = new Cancion("prueba", "prueba", usuario);
		assertFalse(usuario.canListenSong(cancion));
	}

}
