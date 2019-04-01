package testsJUnit;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import principal.Album;
import principal.Aplicacion;
import principal.Cancion;
import principal.Lista;
import principal.UsuarioAnonimo;
import principal.UsuarioRegistrado;
import utils.FechaSimulada;

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