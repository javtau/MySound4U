package testsJUnit;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Before;
import org.junit.Test;

import principal.Album;
import principal.Aplicacion;
import principal.Administrador;
import principal.Cancion;
import principal.Denuncia;
import principal.Lista;
import principal.SesionAdmin;
import principal.SesionAnonima;
import principal.SesionUsuarios;
import principal.Usuario;
import principal.UsuarioAnonimo;
import principal.UsuarioRegistrado;
import principal.Validacion;
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
