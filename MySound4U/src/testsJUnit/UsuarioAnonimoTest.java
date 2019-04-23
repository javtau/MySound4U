package testsJUnit;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import modelo.Aplicacion;
import modelo.Cancion;
import modelo.UsuarioAnonimo;

public class UsuarioAnonimoTest {
	UsuarioAnonimo usuario;

	@Before
	public void setUp() throws Exception {
		usuario = new UsuarioAnonimo();

	}

	@Test
	public void testiniciarSesion() {
		Aplicacion api = null;
		assertNotNull(usuario.iniciarSesion(api));
	}

	@Test
	public void testcanListenSong() {
		Cancion cancion = new Cancion("prueba", "prueba", null);
		cancion.bloquear();
		assertTrue(usuario.canListenSong(cancion));
	}

	@Test
	public void testcanListenSong2() {
		Cancion cancion = new Cancion("prueba", "prueba", null);
		cancion.setExplicita();
		assertTrue(usuario.canListenSong(cancion));
	}

	@Test
	public void testcanListenSong3() {
		Cancion cancion = new Cancion("prueba", "prueba", null);
		assertTrue(usuario.canListenSong(cancion));
	}

	@Test
	public void testcanListenSong4() {
		Cancion cancion = new Cancion("prueba", "prueba", null);
		cancion.validar();
		assertFalse(usuario.canListenSong(cancion));
	}

}