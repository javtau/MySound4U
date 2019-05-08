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
import modelo.Element;
import modelo.SesionAnonima;
import modelo.TIPO_BUSQUEDA;
import vista.VistaLoginForm;
import vista.VistaRegistroForm;
import vista.VistaAnonimo;

public class ControladorVistaAnonimo implements ActionListener, WindowListener {
	private VistaAnonimo vista;
	private Aplicacion api;
	private SesionAnonima sesion;
	private ArrayList<Element> elementos;

	public ControladorVistaAnonimo(VistaAnonimo vista, Aplicacion api) {
		super();
		this.vista = vista;
		this.api = api;
		api.desloguearse();
		sesion = (SesionAnonima) api.getSesion();
	}

	// Metodo para rellenar la tabla de canciones
	public void rellenarTableSongs(ArrayList<Element> elements) {
		// TODO Auto-generated method stub
		DefaultTableModel table = (DefaultTableModel) vista.getTableSongs().getModel();
		table.getDataVector().removeAllElements();
		table.fireTableDataChanged();
		for (Element e : elements) {
			if (e.getClass().getSimpleName().equals("Album")) {
				Album album = (Album) e;
				table.addRow(new Object[] { album.getNombre(), " ", album.getAutor().getNombre(), " " });
			} else {
				Cancion cancion = (Cancion) e;
				Album album = cancion.getAlbum();
				table.addRow(new Object[] { cancion.getNombre(), cancion.getDuracion(), cancion.getAutorNombre(),
						(album != null) ? album.getNombre() : " " });
			}

		}
		vista.getTableSongs().setRowSorter(new TableRowSorter<TableModel>(table));
	}
// Metodo que captura la accion que ha realizado el usuario
	@Override
	public void actionPerformed(ActionEvent e) {
		Object component = e.getSource();

		if (component == vista.getBtnSignUp()) {
			VistaRegistroForm registro = new VistaRegistroForm();
			ControladorRegister controlR = new ControladorRegister(registro, api);
			registro.setControlador(controlR);
			registro.setVisible(true);
			controlR.start();

		} else if (component == vista.getBtnLogIn()) {
			VistaLoginForm login = new VistaLoginForm();
			ControladorLogin controlL = new ControladorLogin(login, api, vista);
			login.setControlador(controlL);
			login.setVisible(true);
			controlL.start();

		} else if (component == vista.getBtnStop()) {
			sesion.stop();
		} else if (component == vista.getBtnPlay()) {
			int selection = vista.getTableSongs().getSelectedRow();
			if (selection > -1)
				sesion.reproducir(elementos.get(selection));

		} else if (component == vista.getBtnBusqueda()) {
			TIPO_BUSQUEDA filtro;
			filtro = TIPO_BUSQUEDA.valueOf(vista.getComboBusqueda().getSelectedItem().toString().toUpperCase());
			elementos = api.buscar(vista.getTfBusqueda().getText(), filtro);
			rellenarTableSongs(elementos);
		}
	}
// Metodo a ejecutar cuando abrimos la vista de anonimo
	public void start() {
		elementos = api.getLastSongs();
		rellenarTableSongs(elementos);
		vista.setVisible(true);
	}
// Cuando se cierra la vista, pregunta si esta seguro y guarda los cambios realizados 
	@Override
	public void windowClosing(WindowEvent e) {

		if (JOptionPane.showConfirmDialog(null, "Esta seguro de que desea salir?", "Atencion",
				JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
			api.desloguearse();
			api.save();
			System.exit(0);
		}
	}

	@Override
	public void windowOpened(java.awt.event.WindowEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void windowClosed(java.awt.event.WindowEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void windowIconified(java.awt.event.WindowEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void windowDeiconified(java.awt.event.WindowEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void windowActivated(java.awt.event.WindowEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void windowDeactivated(java.awt.event.WindowEvent e) {
		// TODO Auto-generated method stub
	}
}