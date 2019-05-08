package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import modelo.Album;
import modelo.Aplicacion;
import modelo.Cancion;
import modelo.Lista;
import modelo.UsuarioRegistrado;
import vista.VistaAddToList;

public class ControladorAddToList implements ActionListener {
	private VistaAddToList vista;
	private UsuarioRegistrado user;
	private ControladorVistaRegistrado cvr;
	Aplicacion api;
	private Lista list;
	private List<Cancion> canciones;
	private ArrayList<Lista> listas;
	private ArrayList<Album> albumes;

	public ControladorAddToList(VistaAddToList vista, Aplicacion api, Lista list, ControladorVistaRegistrado cvr) {
		super();
		this.vista = vista;
		this.cvr = cvr;
		this.api = api;
		this.list = list;
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

	// Metodo para rellenar la tabla de albumes
	public void rellenarTableAlbums(ArrayList<Album> elements) {
		// TODO Auto-generated method stub
		DefaultTableModel table = (DefaultTableModel) vista.getTableAlbums().getModel();
		table.getDataVector().removeAllElements();
		table.fireTableDataChanged();
		for (Album album : elements) {
			table.addRow(new Object[] { album.getNombre(), album.getAutor().getNombre(), album.getNumSongs(), false });

		}
		vista.getTableAlbums().setRowSorter(new TableRowSorter<TableModel>(table));
	}

	// Metodo para rellenar la tabla de listas
	public void rellenarTableList(ArrayList<Lista> elements) {
		// TODO Auto-generated method stub
		DefaultTableModel table = (DefaultTableModel) vista.getTableList().getModel();
		table.getDataVector().removeAllElements();
		table.fireTableDataChanged();
		for (Lista lista : elements) {
			table.addRow(new Object[] { lista.getNombre(), lista.getNumElements(), false });
		}
		vista.getTableList().setRowSorter(new TableRowSorter<TableModel>(table));
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object component = e.getSource();
		ArrayList<Integer> songs = new ArrayList<>();
		ArrayList<Integer> lists = new ArrayList<>();
		ArrayList<Integer> albums = new ArrayList<>();

		if (component == vista.getBtnaccept()) {
			for (int i = 0; i < vista.getTableSongs().getRowCount(); i++) {
				if ((boolean) vista.getTableSongs().getValueAt(i, 4)) {
					songs.add(i);
				}
			}
			for (int i = 0; i < vista.getTableAlbums().getRowCount(); i++) {
				if ((boolean) vista.getTableAlbums().getValueAt(i, 3)) {
					albums.add(i);
				}
			}
			for (int i = 0; i < vista.getTableList().getRowCount(); i++) {
				if ((boolean) vista.getTableList().getValueAt(i, 2)) {
					lists.add(i);
				}
			}
			if (songs.size() < 1 && lists.size() < 1 && albums.size() < 1) {
				JOptionPane.showMessageDialog(vista, "Debe de seleccionar al menos una cancion", "Error",
						JOptionPane.ERROR_MESSAGE);
			} else {
				for (int i : songs) {
					list.addElemt(canciones.get(i));
				}
				for (int i : lists) {
					list.addElemt(listas.get(i));
				}
				for (int i : albums) {
					list.addElemt(albumes.get(i));
				}
				cvr.rellenarTableList(((UsuarioRegistrado) api.getSesion().getUsuario()).getListas());
				cvr.changeTablePane(2);
				cvr.setListas(((UsuarioRegistrado) api.getSesion().getUsuario()).getListas());
				vista.dispose();
			}
		}
	}

	public void start() {
		canciones = api.getCanciones().stream().filter(c -> c.esValidada()).collect(Collectors.toList());
		albumes = api.getAlbumes();
		listas = user.getListas();
		rellenarTableSongs(new ArrayList<Cancion>(canciones));
		rellenarTableAlbums(albumes);
		rellenarTableList(listas);
		vista.setLocationRelativeTo(null);
		vista.setVisible(true);
	}
}