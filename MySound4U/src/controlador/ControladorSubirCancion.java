package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import modelo.Aplicacion;
import modelo.SesionUsuarios;
import vista.VistaRegistrado;
import vista.VistaSubirCancionForm;

public class ControladorSubirCancion implements ActionListener {
	private VistaSubirCancionForm subir;
	private SesionUsuarios sesion;
	private VistaRegistrado vista;
	private Aplicacion api;
	private JFileChooser fileChooser;
	private File origen;
	private ControladorVistaRegistrado control;

	public ControladorSubirCancion(VistaSubirCancionForm subir, SesionUsuarios sesion, VistaRegistrado vista,
			Aplicacion api, ControladorVistaRegistrado control) {
		super();
		this.subir = subir;
		this.sesion = sesion;
		this.vista = vista;
		this.api = api;
		this.control = control;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object component = e.getSource();
		String nombre = subir.getTf2().getText();

		if ((component == subir.getBtnExplorar())) {
			fileChooser = new JFileChooser();
			FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivos de musica", "mp3");
			fileChooser.setFileFilter(filter);
			fileChooser.setAcceptAllFileFilterUsed(false);
			int returnVal = fileChooser.showOpenDialog(null);

			if (returnVal == JFileChooser.APPROVE_OPTION) {
				origen = fileChooser.getSelectedFile();
				subir.getTf1().setText(origen.getName());
			}
		} else if (component == subir.getBtn1() && !nombre.isEmpty()) {
			sesion.subirCancion(nombre, origen);
			JOptionPane.showMessageDialog(subir, "La cancion ha sido subida con exito", "Subir cancion",
					JOptionPane.INFORMATION_MESSAGE);
			subir.dispose();
			control.rellenarTableSongs(api.getLastSongs());
			control.setElementos(api.getLastSongs());
			control.rellenarTablePendientes(api.getValidacionesByUser(sesion.getUsuarioRegistrado()));
			control.changeTablePane(0);
		} else if (component == subir.getBtn1() && nombre.isEmpty()) {
			JOptionPane.showMessageDialog(subir, "El nombre de la cancion no puede estar vacio", "Subir cancion",
					JOptionPane.ERROR_MESSAGE);
		} else
			subir.dispose();
	}

	public void start() {
		subir.setLocation(vista);
		subir.setVisible(true);
	}
}