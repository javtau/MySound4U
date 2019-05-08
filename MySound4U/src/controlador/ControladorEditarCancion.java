package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import modelo.Aplicacion;
import modelo.SesionUsuarios;
import modelo.Validacion;
import vista.VistaEditarCancionForm;
import vista.VistaRegistrado;

public class ControladorEditarCancion implements ActionListener {
	private VistaEditarCancionForm editar;
	private SesionUsuarios sesion;
	private VistaRegistrado vista;
	private Aplicacion api;
	private JFileChooser fileChooser;
	private File origen;
	private ControladorVistaRegistrado control;
	private Validacion validacion;
	private boolean flag;

	public ControladorEditarCancion(VistaEditarCancionForm editar, SesionUsuarios sesion, VistaRegistrado vista,
			Aplicacion api, ControladorVistaRegistrado control, Validacion validacion) {
		super();
		this.editar = editar;
		this.sesion = sesion;
		this.vista = vista;
		this.api = api;
		this.control = control;
		this.validacion = validacion;
		flag = false;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object component = e.getSource();
		String nombre = editar.getTf2().getText();

		if ((component == editar.getBtnExplorar())) {

			fileChooser = new JFileChooser();
			FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivos de musica", "mp3");
			fileChooser.setFileFilter(filter);
			fileChooser.setAcceptAllFileFilterUsed(false);
			int returnVal = fileChooser.showOpenDialog(null);

			if (returnVal == JFileChooser.APPROVE_OPTION) {
				origen = fileChooser.getSelectedFile();
				editar.getTf1().setText(origen.getName());
				flag = true;
			}
		} else if (component == editar.getBtn1() && !nombre.isEmpty()) {
			if (flag)
				sesion.subirCancion(nombre, origen);
			JOptionPane.showMessageDialog(vista, "La cancion ha sido editada con exito", "Editar cancion",
					JOptionPane.INFORMATION_MESSAGE);
			editar.dispose();
			api.borrarValidacion(validacion);
			control.rellenarTableSongs(api.getLastSongs());
			control.setElementos(api.getLastSongs());
			control.setPendientes(api.getValidacionesByUser(sesion.getUsuarioRegistrado()));
			control.rellenarTablePendientes(api.getValidacionesByUser(sesion.getUsuarioRegistrado()));
			control.changeTablePane(3);
		} else if (component == editar.getBtn1() && nombre.isEmpty()) {
			JOptionPane.showMessageDialog(editar, "El nombre de la cancion no puede estar vacio", "Editar cancion",
					JOptionPane.ERROR_MESSAGE);
		} else
			editar.dispose();
	}

	public void start() {
		editar.setLocation(vista);
		editar.setVisible(true);
	}
}