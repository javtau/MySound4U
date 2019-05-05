package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JOptionPane;

import modelo.Aplicacion;
import modelo.Cancion;
import modelo.SesionUsuarios;
import vista.VistaEditarCancionForm;
import vista.VistaRegistrado;

public class ControladorEditarCancion implements ActionListener {
	private VistaEditarCancionForm editar;
	private SesionUsuarios sesion;
	private VistaRegistrado vista;
	private Aplicacion api;
	private Cancion cancion;

	public ControladorEditarCancion(VistaEditarCancionForm editar, SesionUsuarios sesion, VistaRegistrado vista,
			Aplicacion api, Cancion cancion) {
		super();
		this.editar = editar;
		this.sesion = sesion;
		this.vista = vista;
		this.api = api;
		this.cancion = cancion;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object component = e.getSource();

		String nombre = editar.getTf1().getText();
		String ruta = cancion.getRuta();
		File fileEditar = new File("songs/" + ruta);

		if (nombre.isEmpty() && component == editar.getBtn1()) {
			JOptionPane.showMessageDialog(editar, "El nombre de la cancion que deseas editar no puede estar vacio",
					"Editar cancion", JOptionPane.ERROR_MESSAGE);
		} else if ((!nombre.isEmpty() && component == editar.getBtn1())) {
			sesion.editarCancion(cancion, nombre, fileEditar);
			JOptionPane.showMessageDialog(editar, "La cancion ha sido editada con exito", "Editar cancion",
					JOptionPane.INFORMATION_MESSAGE);
			editar.dispose();
			VistaRegistrado vistaR = new VistaRegistrado();
			ControladorVistaRegistrado controlR = new ControladorVistaRegistrado(vistaR, api);
			vistaR.setControlador(controlR);
			controlR.start();
		} else if (component == editar.getBtn2())
			editar.dispose();
	}

	public void start() {
		editar.setLocation(vista);
		editar.setVisible(true);
	}
}