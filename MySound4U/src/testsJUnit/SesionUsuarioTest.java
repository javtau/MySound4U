package testsJUnit;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.Before;
import org.junit.Test;

import modelo.Administrador;
import modelo.Album;
import modelo.Aplicacion;
import modelo.Cancion;
import modelo.Lista;
import modelo.SesionUsuarios;
import modelo.UsuarioRegistrado;
import utils.FechaSimulada;

public class SesionUsuarioTest {
		SesionUsuarios s3;
		UsuarioRegistrado usuario;
		Administrador admin;
		Aplicacion api = null; 
		
		
		@Before
		public void setUp() throws Exception {
			api = Aplicacion.getApi();
			usuario = new UsuarioRegistrado("usuario", "passwd", FechaSimulada.getHoy().minusYears(15));
			s3 = new SesionUsuarios(usuario, api);
			api.addUsuario(usuario);
			api.loguearse("usuario", "passwd");
			
		}

		@Test
		public void testsubirCancion() {
			File file = new File("songstoupload/Mirame.mp3");
			s3.subirCancion("nombre", file);
			System.out.println("canciones" + usuario.getCanciones());
			assertFalse(usuario.getCanciones().isEmpty());
		}
		@Test
		public void testcrearLista() {
			s3.crearLista("nombre");
			assertTrue(!usuario.getListas().isEmpty());
		}
		@Test
		public void testcrearAlbum() {
			s3.crearAlbum("nombre");
			assertTrue(!usuario.getAlbumes().isEmpty());
		}

		@Test
		public void testborrarCancion() {
			Cancion cancion = new Cancion("prueba", "prueba", usuario);
			usuario.addCancion(cancion);
			s3.borrarCancion(cancion);
			assertTrue(usuario.getCanciones().isEmpty());
		}
		@Test
		public void testDenunciar() {
			UsuarioRegistrado usuario2 = new UsuarioRegistrado("usuario2", "passwd", FechaSimulada.getHoy().minusYears(15));
			Cancion cancion = new Cancion("prueba", "prueba", usuario2);
			s3.denunciar(cancion, "prueba");
			assertTrue((!api.getDenuncias().isEmpty()) && (cancion.esBloqueda()== true));
		}

		@Test
		public void testSeguir() {
			UsuarioRegistrado usuario2 = new UsuarioRegistrado("usuario2", "passwd", FechaSimulada.getHoy().minusYears(15));
			s3.seguir(usuario2);
			assertTrue((usuario.getSeguidos().contains(usuario2)));
		}

		@Test
		public void testSeguir2() {
			s3.seguir(usuario);
			assertFalse((usuario.getSeguidos().contains(usuario)));
		}
		@Test
		public void testanadiraAlbum() {
			Album album = new Album("nombre", usuario);
			Cancion cancion = new Cancion("prueba", "prueba", usuario);
			s3.anadiraAlbum(album, cancion);
			assertFalse(album.isEmpty());
		}
		@Test
		public void testanadiraLista() {
			Lista lista = new Lista("nombre");
			Cancion cancion = new Cancion("prueba", "prueba", usuario);
			s3.anadirALista(lista, cancion);
			assertFalse(lista.getElementos().isEmpty());
		}
		
}