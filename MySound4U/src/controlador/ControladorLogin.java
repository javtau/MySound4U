package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import modelo.Aplicacion;
import vista.VistaLoginForm;
import vista.VistaAdmin;
import vista.VistaAnonimo;
import vista.VistaRegistrado;

public class ControladorLogin implements ActionListener {
	private VistaLoginForm login;
	private Aplicacion api;
	private VistaAnonimo vista;

	public ControladorLogin(VistaLoginForm login, Aplicacion api, VistaAnonimo vista) {
		super();
		this.login = login;
		this.api = api;
		this.vista = vista;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object component = e.getSource();

		String uname = login.getTf1().getText();
		String pass = new String(login.getP1().getPassword());

		if (component == login.getBtn1()) {
			if (api.loguearse(uname, pass) == true) {
				login.dispose();
				vista.dispose();

				if (api.getSesion().getUsuario().isAdmin() == true) {
					VistaAdmin vistaA = new VistaAdmin();
					ControladorVistaAdmin controlA = new ControladorVistaAdmin(vistaA, api);
					vistaA.setControlador(controlA);
					controlA.start();
				} else {
					VistaRegistrado vistaR = new VistaRegistrado();
					ControladorVistaRegistrado controlR = new ControladorVistaRegistrado(vistaR, api);
					vistaR.setControlador(controlR);
					controlR.start();
				}

			} else {
				JOptionPane.showMessageDialog(login, "Username o password incorrectos", "Error",
						JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	public void start() {
		login.setLocation(vista);
		login.setVisible(true);
	}
}