package testsJUnit;

import static org.junit.Assert.*;

import java.io.File;
import java.time.LocalDate;

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

public class ValidacionTest {
	Cancion cancion;
	Validacion validacion;
	UsuarioRegistrado usuario;

	@Before
	public void setUp() throws Exception {
		usuario = new UsuarioRegistrado("usuario", "passwd", FechaSimulada.getHoy().minusYears(15));
		cancion = new Cancion("cancion", "prueba", usuario);
		validacion = new Validacion(cancion, LocalDate.MAX);

	}

	@Test
	public void testgetPlazo() {
		assertTrue(validacion.getPlazo()==LocalDate.MAX);
	}

	@Test
	public void testsetPlazo() {
		validacion.setPlazo(FechaSimulada.getHoy());
		assertTrue(validacion.getPlazo()==FechaSimulada.getHoy());
	}


	@Test
	public void testgetCancion() {
		assertTrue(validacion.getCancion()==cancion);
	}
	
	
	
}