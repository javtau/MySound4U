package testsJUnit;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import modelo.Aplicacion;
import modelo.SesionAnonima;
import modelo.Usuario;
import modelo.UsuarioAnonimo;

public class SesionAnonimaTest {
		SesionAnonima s;
		Usuario usuario;
		Aplicacion api;
		@Before
		public void setUp() throws Exception {
			s = new SesionAnonima((UsuarioAnonimo) usuario, api);
			
		}
		

		@Test
		public void testgetUsuario() {
			assertTrue(s.getUsuario()==usuario);
		}
}