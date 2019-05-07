package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import modelo.Album;
import modelo.Aplicacion;
import modelo.Lista;
import modelo.SesionUsuarios;
import vista.VistaCrearAlbum;
import vista.VistaCrearLista;
import vista.VistaRegistrado;
import vista.VistaAddToAlbum;
import vista.VistaAddToList;

public class ControladorCrearLista implements ActionListener {
	private VistaCrearLista crear;
	private SesionUsuarios sesion;
	private ControladorVistaRegistrado cvr;
	private Aplicacion api;

	public ControladorCrearLista(VistaCrearLista crear, Aplicacion api, ControladorVistaRegistrado cvr) {
		super();
		this.crear = crear;
		this.api = api;
		sesion = (SesionUsuarios) api.getSesion();
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
			ArrayList<Lista> Listas;
			sesion.crearLista(nombre);
			Listas = sesion.getUsuarioRegistrado().getListas();

			if (JOptionPane.showConfirmDialog(null, "Desea añadir algun elemento a la lista?", "Atencion",
					JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
				VistaAddToList vista2 = new VistaAddToList();
				ControladorAddToList controlA = new ControladorAddToList(vista2, api, Listas.get(Listas.size() - 1),
						cvr);
				vista2.setControlador(controlA);
				controlA.start();

			}

			crear.dispose();
		} else if (component == crear.getBtn2())
			crear.dispose();
	}

	public void start() {
		crear.setLocationRelativeTo(null);
		crear.setVisible(true);
	}
}