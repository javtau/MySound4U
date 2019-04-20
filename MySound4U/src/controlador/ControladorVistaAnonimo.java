package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import vista.VistaAdmin;
import vista.VistaAnonimo;
import vista.VistaRegistrado;

public class ControladorVistaAnonimo implements ActionListener {
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

		if (component == vista.getBtnLogIn()) {
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

}
