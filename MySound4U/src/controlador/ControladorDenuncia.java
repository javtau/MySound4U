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
import vista.DenunciaForm;
import vista.LoginForm;
import vista.ValidacionForm;
import vista.VistaAdmin;
import vista.VistaAnonimo;
import vista.VistaRegistrado;

public class ControladorDenuncia implements ActionListener {
	private DenunciaForm formulario;
	private Aplicacion api;
	private Denuncia denuncia;
	private VistaAdmin vista;

	public ControladorDenuncia(DenunciaForm formulario, Aplicacion api, VistaAdmin vista, Denuncia denuncia) {
		super();
		this.formulario = formulario;
		this.api = api;
		this.denuncia = denuncia;
		this.vista = vista;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object component = e.getSource();

		if (component == formulario.getBtn1()) {
			System.out.println("boton aceptar pulsado");
			((SesionAdmin) api.getSesion()).aceptarDenuncia(denuncia);
			System.out.println("denucia aceptada");
			formulario.dispose();
			vista.dispose();
			VistaAdmin vistaA = new VistaAdmin();
			ControladorVistaAdmin controlA = new ControladorVistaAdmin(vistaA, api);
			vistaA.setControlador(controlA);
			controlA.start();

		} else if (component == formulario.getBtn2()) {
			System.out.println("boton rechazar denuncia pulsado");
			((SesionAdmin) api.getSesion()).rechazarDenuncia(denuncia);
			System.out.println("denuncia rechzada");
			formulario.dispose();
			vista.dispose();
			VistaAdmin vistaA = new VistaAdmin();
			ControladorVistaAdmin controlA = new ControladorVistaAdmin(vistaA, api);
			vistaA.setControlador(controlA);
			controlA.start();
		}
	}

	public void start() {
		formulario.setLocation(vista);
		formulario.setVisible(true);
	}

}