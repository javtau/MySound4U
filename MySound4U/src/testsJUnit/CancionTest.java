package testsJUnit;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import principal.Aplicacion;
import principal.Cancion;
import principal.UsuarioRegistrado;
import utils.FechaSimulada;

public class CancionTest {
	Cancion cancion;
	UsuarioRegistrado usuario;

	@Before
	public void setUp() throws Exception {
		usuario = new UsuarioRegistrado("usuario", "passwd", FechaSimulada.getHoy().minusYears(15));
		cancion = new Cancion("prueba", "prueba", usuario);

	}

	@Test
	public void testaumentarReproducciones() {
		cancion.aumentarReproducciones();
		assertTrue(cancion.getNumreproducciones() == 1);
	}

	@Test
	public void testvalidar() {
		cancion.validar();
		assertTrue(cancion.esValidada());
	}

	@Test
	public void testsetBloqueada() {
		cancion.bloquear();
		assertTrue(cancion.esBloqueda());
	}

	@Test
	public void testsetExplicita() {
		cancion.setExplicita();
		assertTrue(cancion.esExplicita());
	}

	@Test
	public void testsetRuta() {
		cancion.setRuta("ruta");
		assertTrue(cancion.getRuta().equals(Aplicacion.getPath() + "ruta"));
	}

	@Test
	public void testgetAutor() {
		assertTrue(cancion.getAutor().equals(usuario));
	}

	@Test
	public void testgetAutorNombre() {
		assertTrue(cancion.getAutorNombre().equals("usuario"));
	}

}
