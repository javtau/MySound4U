package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import modelo.Album;
import modelo.Aplicacion;
import modelo.Cancion;
import modelo.UsuarioRegistrado;
import vista.VistaAddToAlbum;

public class ControladorAddToAlbum implements ActionListener, WindowListener {
	private VistaAddToAlbum vista;
	private UsuarioRegistrado user;
	private ControladorVistaRegistrado cvr;
	Aplicacion api;
	private Album album;
	private ArrayList<Cancion> canciones;

	public ControladorAddToAlbum(VistaAddToAlbum vista, Aplicacion api, Album album, ControladorVistaRegistrado cvr) {
		super();
		this.vista = vista;
		this.cvr = cvr;
		this.api = api;
		this.album = album;
		user = (UsuarioRegistrado) api.getSesion().getUsuario();
	}

	// Metodo para rellenar la tabla de canciones
	public void rellenarTableSongs(ArrayList<Cancion> canciones) {
		// TODO Auto-generated method stub
		DefaultTableModel table = (DefaultTableModel) vista.getTableSongs().getModel();
		table.getDataVector().removeAllElements();
		table.fireTableDataChanged();
		for (Cancion cancion : canciones) {
			Album album = cancion.getAlbum();
			table.addRow(new Object[] { cancion.getNombre(), cancion.getDuracion(), cancion.getAutorNombre(),
					(album != null) ? album.getNombre() : " ", false });

		}
		vista.getTableSongs().setRowSorter(new TableRowSorter<TableModel>(table));
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object component = e.getSource();
		ArrayList<Integer> songs = new ArrayList<>();

		if (component == vista.getBtnaccept()) {
			for (int i = 0; i < vista.getTableSongs().getRowCount(); i++) {
				if ((boolean) vista.getTableSongs().getValueAt(i, 4)) {
					songs.add(i);
				}
			}
			if (songs.size() < 1) {
				JOptionPane.showMessageDialog(vista, "Debe de seleccionar al menos una cancion", "Error",
						JOptionPane.ERROR_MESSAGE);
			} else {
				for (int i = 0; i < songs.size(); i++) {
					album.anadirCancion(canciones.get(i));
				}
				cvr.rellenarTableAlbums(user.getAlbumes());
				cvr.changeTablePane(1);
				vista.dispose();
			}
		}
	}

	public void start() {
		canciones = user.getCanciones();
		rellenarTableSongs(canciones);
		vista.setLocationRelativeTo(null);
		vista.setVisible(true);
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowClosing(WindowEvent e) {
		if (album.getNumSongs() < 1) {
			api.borrarAlbum(album);
		}
	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub

	}
}