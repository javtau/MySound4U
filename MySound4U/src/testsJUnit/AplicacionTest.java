package testsJUnit;

import static org.junit.Assert.*;

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
import principal.TIPO_BUSQUEDA;
import principal.Usuario;
import principal.UsuarioAnonimo;
import principal.UsuarioRegistrado;
import principal.Validacion;
import utils.FechaSimulada;

public class AplicacionTest {
		SesionAdmin s3;
		UsuarioRegistrado usuario;
		UsuarioRegistrado usuario2;
		UsuarioRegistrado usuario3;
		Denuncia denuncia;
		Administrador admin;
		Cancion cancion;
		Cancion cancion2;
		Aplicacion api = null; 
		Validacion validacion; 
		Validacion validacion2; 
		Validacion validacion3; 
		Album album;
		@Before
		public void setUp() throws Exception {
			api = Aplicacion.getApi();
			admin = new Administrador();
			s3 = new SesionAdmin(admin, api);
			usuario = new UsuarioRegistrado("usuario", "passwd", FechaSimulada.getHoy().minusYears(15));
			usuario2 = new UsuarioRegistrado("usuario2", "passwd", FechaSimulada.getHoy().minusYears(16));
			denuncia = new Denuncia(cancion, usuario, "comentario");
			usuario3 = new UsuarioRegistrado("usuario3", "passwd", FechaSimulada.getHoy().minusYears(15));
			cancion = new Cancion("cancion","prueba", usuario2);
			cancion2 = new Cancion("cancion2","prueba", usuario2);
			validacion = new Validacion(cancion, FechaSimulada.getHoy().minusDays(9));
			validacion2 = new Validacion(cancion2, FechaSimulada.getHoy().minusDays(9));
			album = new Album("album", usuario);
			api.addUsuario(usuario);
			api.loguearse("usuario", "passwd");
			api.addAlbum(album);
			usuario.addCancion(cancion);
			api.addUsuario(usuario3);
			api.addDenuncia(denuncia);
			api.addCancion(cancion);
		}
		


		@Test
		public void testLoguearse() {
			assertTrue(api.loguearse("usuario3", "passwd"));
		}
		@Test
		public void testLoguearse2() {
			assertTrue(api.loguearse("admin", "admin"));
		}
		@Test
		public void testLoguearse3() {
			assertTrue(api.loguearse("USUARIO3", "passwd"));
		}

		@Test
		public void testLoguearse4() {
			assertFalse(api.loguearse("USUARIO", "fallo"));
		}
		@Test
		public void testLoguearse5() {
			assertFalse(api.loguearse("fallo", "passwd"));
		}

		@Test
		public void testgetSesion() {
			api.loguearse("USUARIO", "passwd");
			assertNotNull(api.getSesion());
		}

		@Test
		public void testgetSesion2() {
			api.loguearse("admin", "admin");
			assertNotNull(api.getSesion());
		}
		@Test
		public void testRegistrarse() {
			api.registrarse("usuario2", "passwd", "30/08/1996");
			assertFalse(api.getUsuario(1).getNombre()=="usuario2");
		}
		@Test
		public void testRegistrarse2() {
			api.registrarse("admin", "faas", "30/08/1996");
			assertFalse(api.getUsuario(1).getNombre()=="admin");
		}
		@Test
		public void testRegistrarse3() {
			api.registrarse("usuario", "faas", "30/08/1996");
			assertFalse(api.getUsuario(1).getNombre()=="usuario");
		}
		@Test
		public void testDesloguearse() {
			api.loguearse("usuario", "passwd");
			api.desloguearse();
			assertFalse(api.getSesion().getUsuario()==usuario);
		}

		@Test
		public void testRevisarBloqueados() {
			usuario.setBloqueado(true);
			api.addBloqueado(usuario, FechaSimulada.getHoy().minusDays(40));
			api.revisarBloqueados();
			assertFalse(usuario.estaBloqueado());
		}
		@Test
		public void testRevisarBloqueados2() {
			usuario.setBloqueado(true);
			api.addBloqueado(usuario, FechaSimulada.getHoy().plusDays(2));
			api.revisarBloqueados();
			assertTrue(usuario.estaBloqueado());
		}
		@Test
		public void testRevisarValidaciones() {
			api.addValidacion(validacion2);
			api.revisarValidaciones();
			assertFalse(api.getValidacion(cancion) == validacion2);
		}
		@Test
		public void testBuscar() {
			api.loguearse("admin", "admin");
			assertTrue(api.buscar("album", TIPO_BUSQUEDA.ALBUM).contains(album));
		}
		@Test
		public void testBuscar2() {
			api.loguearse("admin", "admin");
			
			assertFalse(api.buscar("cancion", TIPO_BUSQUEDA.ALBUM).contains(cancion));
		}
		@Test
		public void testBuscar3() {
			
			assertFalse(api.buscar("album", TIPO_BUSQUEDA.TITULO).contains(cancion));
		}
		@Test
		public void testBuscar4() {
			api.loguearse("admin", "admin");
			assertTrue(api.buscar("cancion", TIPO_BUSQUEDA.TITULO).contains(cancion));
		}

		@Test
		public void testgetUsuarios() {
			assertTrue(api.getDenuncias().contains(denuncia));
		}
		@Test
		public void testborrarCancion() {
			api.borrarCancion(cancion);
			assertFalse(api.getCanciones().contains(cancion));
		}

		@Test
		public void testpasarPremium() {
			api.loguearse("usuario", "passwd");
			api.pasarPremium(usuario);
			assertTrue(usuario.esPremium());
		}
		@Test
		public void testaddBloqueado() {
			api.addBloqueado(usuario2, FechaSimulada.getHoy());
			assertTrue(api.getBloqueados().containsKey(usuario2));
		}
		@Test
		public void testdeleteBloqueado() {
			api.addBloqueado(usuario2, FechaSimulada.getHoy());
			api.deleteBloqueado(usuario2);
			assertFalse(api.getBloqueados().containsKey(usuario2));
		}

		@Test
		public void testaddValidacion() {
			api.addValidacion(validacion);
			assertTrue(api.getValidaciones().contains(validacion));
		}

		@Test
		public void testdeleteValidacion() {
			api.addValidacion(validacion);
			api.deleteValidacion(validacion);
			assertFalse(api.getValidaciones().contains(validacion));
		}
		@Test
		public void testaddDenuncia() {
			api.addDenuncia(denuncia);
			assertTrue(api.getDenuncias().contains(denuncia));
		}
		@Test
		public void testdeleteDenuncia() {
			api.deleteDenuncia(denuncia);
			assertFalse(api.getDenuncias().contains(denuncia));
		}
		}		