package testsJUnit;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

import modelo.Administrador;
import modelo.Aplicacion;
import modelo.Cancion;

public class AdministradorTest {
		Administrador admin;
		
		@Before
		public void setUp() throws Exception {
			admin = new Administrador();
			
		}

		@Test
		public void testiniciarSesion() {
			Aplicacion api = null;
			assertNotNull(admin.iniciarSesion(api));
		}

		@Test
		public void testcanListenSong() {
			Cancion cancion = null;
			assertFalse(admin.canListenSong(cancion));
		}

}