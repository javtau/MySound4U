package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Calendar;

import javax.swing.JOptionPane;

import modelo.Aplicacion;
import modelo.UsuarioRegistrado;
import vista.RegistroForm;
import vista.VistaAnonimo;

/*public class ControladorAjustes implements ActionListener {
	private RegistroForm registro;
	private Aplicacion api;
	private VistaAnonimo vista;
	// private boolean fechaCorrecta;
	private boolean comprobacion;

	public ControladorAjustes(RegistroForm registro, Aplicacion api) {
		super();
		this.registro = registro;
		this.api = api;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object component = e.getSource();
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

		String uname = registro.getTf1().getText();
		String pass = registro.getP1().getText();
		String fecha = format.format(registro.getDcFecha().getDate());

		if (component == registro.getBtn1()) {
			if (uname.contentEquals("") || pass.contentEquals("") || fecha.contentEquals("")) {
				JOptionPane.showMessageDialog(registro, "Por favor, rellene el formulario correctamente", "Error",
						JOptionPane.ERROR_MESSAGE);

			} else {
				try {
					System.out.println("boton registrarse pulsado");
					comprobacion = api.registrarse(uname, pass, fecha);

					if (comprobacion == false) {
						JOptionPane.showMessageDialog(registro, "Usuario registrado correctamente", "Registro",
								JOptionPane.INFORMATION_MESSAGE);
						registro.dispose();
					} else
						JOptionPane.showMessageDialog(registro,
								"El nombre de usuario ya esta usado, pruebe otro distinto", "Registro",
								JOptionPane.INFORMATION_MESSAGE);
				} catch (DateTimeParseException ex) {
					JOptionPane.showMessageDialog(registro, "Fecha incorrecta, introducela de nuevo", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		}
	}

	public void start() {
		registro.setLocation(vista);
		registro.setVisible(true);
	}
	
}*/