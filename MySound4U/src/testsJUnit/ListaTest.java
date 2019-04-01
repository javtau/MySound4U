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

public class ListaTest {
	Lista lista;
	UsuarioRegistrado usuario;
	Cancion cancion;

	@Before
	public void setUp() throws Exception {
		usuario = new UsuarioRegistrado("usuario", "passwd", FechaSimulada.getHoy().minusYears(15));
		cancion = new Cancion("prueba", "prueba", usuario);
		lista = new Lista("lista");

	}

	@Test
	public void testanadirElemento() {
		lista.addElemt(cancion);
		assertFalse(lista.getElementos().isEmpty());
		
	}

	@Test
	public void testdeleteElemento() {
		lista.addElemt(cancion);
		lista.removeElemt(cancion);
		assertTrue(lista.getElementos().isEmpty());
		
	}
}
