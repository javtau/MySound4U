package vista;

import controlador.ControladorVistaAnonimo;
import modelo.Aplicacion;

public class VistaTest {
	

	public static void main(String[] args) {
		Aplicacion api = Aplicacion.getApi();
		VistaAnonimo vista = new VistaAnonimo();
		ControladorVistaAnonimo controlA = new ControladorVistaAnonimo(vista, api);
		vista.setControlador(controlA);
		controlA.start();
	}
}
