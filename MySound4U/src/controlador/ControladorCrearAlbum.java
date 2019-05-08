package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import modelo.Album;
import modelo.Aplicacion;
import modelo.SesionUsuarios;
import vista.VistaAddToAlbum;
import vista.VistaCrearAlbum;
import vista.VistaRegistrado;

public class ControladorCrearAlbum implements ActionListener {
	private VistaCrearAlbum crear;
	private SesionUsuarios sesion;
	private VistaRegistrado vista;
	private ControladorVistaRegistrado cvr;
	private Aplicacion api;

	public ControladorCrearAlbum(VistaCrearAlbum crear, SesionUsuarios sesion, VistaRegistrado vista, Aplicacion api, ControladorVistaRegistrado cvr) {
		super();
		this.crear = crear;
		this.sesion = sesion;
		this.vista = vista;
		this.api = api;
		this.cvr = cvr;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object component = e.getSource();

		String nombre = crear.getTf1().getText();

		if (nombre.isEmpty() && component == crear.getBtn1())
			JOptionPane.showMessageDialog(crear, "El nombre del album que deseas crear no puede estar vacio",
					"Crear album", JOptionPane.ERROR_MESSAGE);
		else if ((!nombre.isEmpty() && component == crear.getBtn1())) {
			ArrayList<Album> albumes;
			sesion.crearAlbum(nombre);
			albumes = sesion.getUsuarioRegistrado().getAlbumes();
			VistaAddToAlbum vista2 = new VistaAddToAlbum();
			ControladorAddToAlbum controlA = new ControladorAddToAlbum(vista2, api, albumes.get(albumes.size() - 1), cvr);
			vista2.setControlador(controlA);
			controlA.start();
			crear.dispose();
		} else if (component == crear.getBtn2())
			crear.dispose();
	}

	public void start() {
		crear.setLocation(vista);
		crear.setVisible(true);
	}
}