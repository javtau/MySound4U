package testsJUnit;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import modelo.Album;
import modelo.Cancion;
import modelo.UsuarioRegistrado;
import utils.FechaSimulada;

public class AlbumTest {
	Album album;
	UsuarioRegistrado usuario;
	Cancion cancion;

	@Before
	public void setUp() throws Exception {
		usuario = new UsuarioRegistrado("usuario", "passwd", FechaSimulada.getHoy().minusYears(15));
		cancion = new Cancion("prueba", "prueba", usuario);
		album = new Album("album", usuario);

	}

	@Test
	public void testanadirCancion() {
		album.anadirCancion(cancion);
		assertFalse(album.isEmpty());
		
	}

	@Test
	public void testborrarCancion() {
		album.anadirCancion(cancion);
		album.borrarCancion(cancion);
		assertTrue(album.isEmpty());
		
	}

	@Test
	public void testgetAutor() {
		assertTrue(album.getAutor()== usuario);
		
	}
}
