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

public class DenunciaTest {
	Denuncia denuncia;
	UsuarioRegistrado usuario;
	Cancion cancion;

	@Before
	public void setUp() throws Exception {
		usuario = new UsuarioRegistrado("usuario", "passwd", FechaSimulada.getHoy().minusYears(15));
		cancion = new Cancion("prueba", "prueba", usuario);
		denuncia = new Denuncia(cancion, usuario, "comentario");

	}

	@Test
	public void testgetCancion() {
		assertTrue(denuncia.getCancion() == cancion);
		
	}

	@Test
	public void testgetDenunciante() {
		assertTrue(denuncia.getDenunciante() == usuario);
		
	}

	@Test
	public void testgetComentario() {
		assertTrue(denuncia.getComentario() == "comentario");
		
	}
}