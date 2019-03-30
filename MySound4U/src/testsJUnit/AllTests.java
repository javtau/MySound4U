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
	
			})


public class AllTests {

}
