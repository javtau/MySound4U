package testsJUnit;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import principal.Administrador;
import principal.Aplicacion;
import principal.Cancion;
import principal.Denuncia;
import principal.SesionAdmin;
import principal.UsuarioRegistrado;
import principal.Validacion;
import utils.FechaSimulada;

public class SesionAdminTest {
		SesionAdmin s3;
		UsuarioRegistrado usuario;
		Administrador admin;
		Aplicacion api = null; 
		@Before
		public void setUp() throws Exception {

			api = Aplicacion.getApi();
			admin = new Administrador();
			s3 = new SesionAdmin(admin, api);
			usuario = new UsuarioRegistrado("usuario", "passwd", FechaSimulada.getHoy().minusYears(15));
			
		}
		


		@Test
		public void testaceptarDenuncia() {
			UsuarioRegistrado usuario2 = new UsuarioRegistrado("usuario2", "passwd", FechaSimulada.getHoy().minusYears(15));
			Cancion cancion = new Cancion("prueba", "prueba", usuario2);
			Denuncia denuncia = new Denuncia(cancion, usuario, "prueba");
			s3.aceptarDenuncia(denuncia);
			assertTrue((usuario2.estaBloqueado()== true) && (cancion.esBloqueda()== true));
		}

		@Test
		public void testrechazarDenuncia() {
			UsuarioRegistrado usuario2 = new UsuarioRegistrado("usuario2", "passwd", FechaSimulada.getHoy().minusYears(15));
			Cancion cancion = new Cancion("prueba", "prueba", usuario2);
			Denuncia denuncia = new Denuncia(cancion, usuario, "prueba");
			s3.rechazarDenuncia(denuncia);
			assertTrue((usuario.estaBloqueado()== true) && (cancion.esBloqueda()== false));
		}

		@Test
		public void testvalidar() {
			UsuarioRegistrado usuario2 = new UsuarioRegistrado("usuario2", "passwd", FechaSimulada.getHoy().minusYears(15));
			Cancion cancion = new Cancion("prueba", "prueba", usuario2);
			Validacion validacion = new Validacion(cancion, FechaSimulada.getHoy().plusDays(3));
			api.addValidacion(validacion);
			s3.validar(validacion);
			assertTrue((cancion.esValidada()==true) && (!api.getValidaciones().contains(validacion)));
		}

		@Test
		public void testinvalidar() {
			UsuarioRegistrado usuario2 = new UsuarioRegistrado("usuario2", "passwd", FechaSimulada.getHoy().minusYears(15));
			Cancion cancion = new Cancion("prueba", "prueba", usuario2);
			Validacion validacion = new Validacion(cancion, FechaSimulada.getHoy().plusDays(3));
			api.addValidacion(validacion);
			s3.invalidar(validacion);
			assertTrue((cancion.esValidada()==false) && (api.getValidaciones().contains(validacion)));
		}
		
		@Test
		public void testmarcarExplicita() {
			Cancion cancion = new Cancion("prueba", "prueba", usuario);
			s3.marcarExplicita(cancion);
			assertTrue(cancion.esExplicita());
		}

		@Test
		public void testsetLimiteReproducciones() {
			s3.setLimiteReproducciones(5);
			assertTrue(api.getLimiteReproducciones()==5);
		}

		@Test
		public void testsetUmbralPremium() {
			s3.setUmbralPremium(10);
			assertTrue(api.getUmbralPremium()==10);
		}
		
		
		
		
		
		
		
}