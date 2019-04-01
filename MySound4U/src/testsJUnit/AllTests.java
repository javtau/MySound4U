package testsJUnit;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;


@RunWith(Suite.class)
@SuiteClasses({
	UsuarioRegistradoTest.class,
	UsuarioAnonimoTest.class,
	UsuarioTest.class,
	AdministradorTest.class,
	SesionTest.class,
	SesionAnonimaTest.class,
	SesionAdminTest.class,
	SesionUsuarioTest.class,
	CancionTest.class,
	AlbumTest.class,
	ListaTest.class,
	ElementTest.class,
	ValidacionTest.class,
	DenunciaTest.class,
	AplicacionTest.class
			})


public class AllTests {

}
