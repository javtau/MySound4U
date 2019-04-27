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
import vista.LoginForm;
import vista.RegistroForm;
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

	// Metodo para rellenar la tabla de proveedores
	public void rellenarTableSongs(ArrayList<Element> elements) {
		// TODO Auto-generated method stub
		DefaultTableModel table = (DefaultTableModel) vista.getTableSongs().getModel();
		table.getDataVector().removeAllElements();
		table.fireTableDataChanged();
		for (Element e : elements) {
			Cancion cancion = (Cancion) e;
			Album album = cancion.getAlbum();
			table.addRow(new Object[] { cancion.getNombre(), cancion.getDuracion(), cancion.getAutorNombre(),
					(album != null) ? album.getNombre() : " " });
		}
		vista.getTableSongs().setRowSorter(new TableRowSorter<TableModel>(table));
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object component = e.getSource();

		if (component == vista.getBtnSingUp()) {
			System.out.println("boton registro pulsado");
			RegistroForm registro = new RegistroForm();
			ControladorRegistro controlR = new ControladorRegistro(registro, api, vista);
			registro.setControlador(controlR);
			registro.setVisible(true);
			controlR.start();

		} else if (component == vista.getBtnLogIn()) {
			System.out.println("boton login pulsado");
			LoginForm login = new LoginForm();
			ControladorLogin controlL = new ControladorLogin(login, api, vista);
			login.setControlador(controlL);
			login.setVisible(true);
			controlL.start();

		} else if (component == vista.getBtnSingUp()) {

		} else if (component == vista.getBtnStop()) {
			sesion.stop();
		} else if (component == vista.getBtnPlay()) {
			int selection = vista.getTableSongs().getSelectedRow();
			elementos.get(selection).reproducir(sesion.getUsuario());
			System.out.println("reproduciendo " + elementos.get(selection).getNombre());

		} else if (component == vista.getBtnBusqueda()) {
			TIPO_BUSQUEDA filtro;
			System.out.println("Buscando");
			filtro = TIPO_BUSQUEDA.valueOf(vista.getComboBusqueda().getSelectedItem().toString().toUpperCase());
			elementos = api.buscar(vista.getTfBusqueda().getText(), filtro);
			rellenarTableSongs(elementos);
		}

	}

	public void start() {
		elementos = api.getLastSongs();
		rellenarTableSongs(elementos);
		vista.setVisible(true);
	}

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
