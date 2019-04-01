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

public class ElementTest {
	UsuarioRegistrado usuario;
	Lista lista;
	Cancion cancion;
	Album album;

	@Before
	public void setUp() throws Exception {
		album = new Album("album", usuario);
		usuario = new UsuarioRegistrado("usuario", "passwd", FechaSimulada.getHoy().minusYears(15));
		cancion = new Cancion("cancion", "prueba", usuario);
		lista = new Lista("lista");

	}

	@Test
	public void testgetNombre() {
		assertTrue(cancion.getNombre()=="cancion");
	}

	@Test
	public void testgetNombre2() {
		assertTrue(album.getNombre()=="album");
	}

	@Test
	public void testgetNombre3() {
		assertTrue(lista.getNombre()=="lista");
	}
}
