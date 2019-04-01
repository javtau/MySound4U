package testsJUnit;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import principal.Cancion;
import principal.Lista;
import principal.UsuarioRegistrado;
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
