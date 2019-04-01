package testsJUnit;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import principal.Administrador;
import principal.Aplicacion;
import principal.SesionAdmin;
import principal.SesionAnonima;
import principal.SesionUsuarios;
import principal.Usuario;
import principal.UsuarioAnonimo;
import principal.UsuarioRegistrado;

public class SesionTest {
		SesionAnonima s;
		SesionUsuarios s2;
		SesionAdmin s3;
		Usuario usuario;
		Aplicacion api;
		@Before
		public void setUp() throws Exception {
			s = new SesionAnonima((UsuarioAnonimo) usuario, api);
			s2 = new SesionUsuarios((UsuarioRegistrado) usuario, api);
			s3 = new SesionAdmin((Administrador) usuario, api);
			
		}
		

		@Test
		public void testgetUsuario() {
			assertTrue((s2.getUsuario() == usuario) &&  (s3.getUsuario() == usuario) && (s.getUsuario()==usuario));
		}

		@Test
		public void testgetApi() {
			assertTrue((s2.getApi() == api) &&  (s3.getApi() == api) && (s.getApi()==api));
		}
}