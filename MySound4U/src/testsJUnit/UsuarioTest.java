package testsJUnit;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import principal.Administrador;
import principal.Usuario;
import principal.UsuarioAnonimo;
import principal.UsuarioRegistrado;
import utils.FechaSimulada;

public class UsuarioTest {
	Usuario usuario;
	Usuario usuario2;
	Usuario usuario3;

	@Before
	public void setUp() throws Exception {
		usuario = new UsuarioRegistrado("Nombre", "passwd", FechaSimulada.getHoy().minusYears(15));
		usuario2 = new UsuarioAnonimo();
		usuario3 = new Administrador();
	}

	@Test
	public void testgetNombre() {
		assertTrue(usuario.getNombre().equals("Nombre") && usuario2.getNombre().equals("anonimo")&& usuario3.getNombre().equals("admin"));
	}

	@Test
	public void testgetContrasena() {
		assertTrue(usuario.getContrasena().equals("passwd") && usuario2.getContrasena().equals("anonimo")&& usuario3.getContrasena().equals("admin"));
	}

	@Test
	public void testgetReproducidas() {
		int i;
		for (i = 0; i < 30; i++) {
			usuario.aumentarReproducidas();
		}
		assertTrue(usuario.getReproducidas()==30);
	}


	@Test
	public void testresetearreproducidas() {
		int i;
		for (i = 0; i < 30; i++) {
			usuario.aumentarReproducidas();
		}
		usuario.resetearreproducidas();
		assertTrue(usuario.getReproducidas()==0);
	}

	@Test
	public void testValidar() {
		assertTrue(usuario.validar("Nombre", "passwd"));
	}

	@Test
	public void testValidar2() {
		assertTrue(usuario.validar("NOMBRE", "passwd"));
	}

	@Test
	public void testValidar3() {
		assertFalse(usuario.validar("Nombre", "passwdMal"));
	}

	@Test
	public void testValidar4() {
		assertFalse(usuario.validar("Nombre", "PASSWD"));
		}

}