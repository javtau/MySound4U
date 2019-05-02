package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import modelo.Aplicacion;
import modelo.SesionUsuarios;
import vista.SubirCancionForm;
import vista.VistaRegistrado;

public class ControladorSubirCancion implements ActionListener {
	private SubirCancionForm subir;
	private SesionUsuarios sesion;
	private VistaRegistrado vista;
	private Aplicacion api;
	private JFileChooser fileChooser;

	public ControladorSubirCancion(SubirCancionForm subir, SesionUsuarios sesion, VistaRegistrado vista,
			Aplicacion api) {
		super();
		this.subir = subir;
		this.sesion = sesion;
		this.vista = vista;
		this.api = api;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object component = e.getSource();

		String nombre = subir.getTf1().getText();

		if (nombre.isEmpty() && component == subir.getBtn1()) {
			JOptionPane.showMessageDialog(subir, "El nombre de la cancion que deseas subir no puede estar vacio",
					"Subir cancion", JOptionPane.ERROR_MESSAGE);
		} else if ((!nombre.isEmpty() && component == subir.getBtn1())) {
			fileChooser = new JFileChooser();
			FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivos de musica", "mp3");
			fileChooser.setFileFilter(filter);
			fileChooser.setAcceptAllFileFilterUsed(false);
			int returnVal = fileChooser.showOpenDialog(null);

			if (returnVal == JFileChooser.APPROVE_OPTION) {
				File origen = fileChooser.getSelectedFile();
				sesion.subirCancion(nombre, origen);
				JOptionPane.showMessageDialog(subir, "La cancion ha sido subida con exito", "Subir cancion",
						JOptionPane.INFORMATION_MESSAGE);
				subir.dispose();
				VistaRegistrado vistaR = new VistaRegistrado();
				ControladorVistaRegistrado controlR = new ControladorVistaRegistrado(vistaR, api);
				vistaR.setControlador(controlR);
				controlR.start();
			}
		} else if (component == subir.getBtn2())
			subir.dispose();
	}

	public void start() {
		subir.setLocation(vista);
		subir.setVisible(true);
	}
}