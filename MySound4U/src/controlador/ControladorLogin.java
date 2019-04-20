package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import modelo.Album;
import modelo.Aplicacion;
import modelo.Cancion;
import modelo.Denuncia;
import modelo.Element;
import modelo.SesionAdmin;
import modelo.SesionAnonima;
import modelo.SesionUsuarios;
import modelo.TIPO_BUSQUEDA;
import modelo.Validacion;
import vista.LoginForm;
import vista.VistaAdmin;
import vista.VistaAnonimo;
import vista.VistaRegistrado;

public class ControladorLogin implements ActionListener {
	private LoginForm login;
	private Aplicacion api;
	private VistaAnonimo vista;

	public ControladorLogin(LoginForm login, Aplicacion api, VistaAnonimo vista) {
		super();
		this.login = login;
		this.api = api;
		this.vista = vista;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object component = e.getSource();

		String uname = login.getTf1().getText();
		String pass = login.getP1().getText();

		if (component == login.getBtn1()) {
			System.out.println("boton login pulsado");

			if (api.loguearse(uname, pass) == true) {
				login.dispose();
				vista.dispose();
				System.out.println("logueando a " + uname + "con contraseña " + pass);

				if (api.getSesion().getUsuario().isAdmin() == true) {
					System.out.println("admin logueado");

					VistaAdmin vistaA = new VistaAdmin();
					ControladorVistaAdmin controlA = new ControladorVistaAdmin(vistaA, api);
					vistaA.setControlador(controlA);
					controlA.start();
				} else {
					System.out.println("usuario logueado");

					VistaRegistrado vistaR = new VistaRegistrado();
					ControladorVistaRegistrado controlR = new ControladorVistaRegistrado(vistaR, api);
					vistaR.setControlador(controlR);
					controlR.start();
				}
			} else {

				System.out.println("no se pudo loguear");
				JOptionPane.showMessageDialog(login, "Incorrect login or password", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	public void start() {
		login.setVisible(true);
	}

}
