package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import modelo.Aplicacion;
import vista.RegistroForm;
import vista.VistaAnonimo;

public class ControladorRegistro implements ActionListener {
	private RegistroForm registro;
	private Aplicacion api;
	private VistaAnonimo vista;

	public ControladorRegistro(RegistroForm registro, Aplicacion api, VistaAnonimo vista) {
		super();
		this.registro = registro;
		this.api = api;
		this.vista = vista;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object component = e.getSource();
		String uname = registro.getTf1().getText();
		String pass = registro.getP1().getText();
		String fecha = registro.getTf2().getText();

		if (component == registro.getBtn1()) {
			System.out.println("boton registrarse pulsado");
			api.registrarse(uname, pass, fecha);
			registro.dispose();
		}
	}

	public void start() {
		registro.setLocation(vista);
		registro.setVisible(true);
	}
}