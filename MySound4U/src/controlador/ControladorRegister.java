package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import modelo.Aplicacion;
import vista.RegistroForm;
import vista.VistaAnonimo;

public class ControladorRegister implements ActionListener {
	private RegistroForm registro;
	private Aplicacion api;
	private VistaAnonimo vista;
	private boolean fechaCorrecta;

	public ControladorRegister(RegistroForm registro, Aplicacion api, VistaAnonimo vista) {
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
		String fechaPattern = "^(3[01]|[12][0-9]|0[1-9])/(1[0-2]|0[1-9])/[0-9]{4}$";
		fechaCorrecta = fecha.matches(fechaPattern);

		if (component == registro.getBtn1()) {
			if (uname.contentEquals("") || pass.contentEquals("") || fecha.contentEquals("")
					|| fechaCorrecta == false) {
				JOptionPane.showMessageDialog(registro, "Por favor, rellene el formulario correctamente", "Error",
						JOptionPane.ERROR_MESSAGE);
			} else {
				System.out.println("boton registrarse pulsado");
				api.registrarse(uname, pass, fecha);
				JOptionPane.showMessageDialog(registro, "Usuario registrado correctamente", "Registro",
						JOptionPane.INFORMATION_MESSAGE);
				registro.dispose();
			}
		}
	}

	public void start() {
		registro.setLocation(vista);
		registro.setVisible(true);
	}
}