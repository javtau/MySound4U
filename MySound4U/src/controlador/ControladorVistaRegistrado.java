package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import modelo.Album;
import modelo.Aplicacion;
import modelo.Cancion;
import modelo.Element;
import modelo.SesionUsuarios;
import modelo.TIPO_BUSQUEDA;
import modelo.Validacion;
import vista.DenunciarForm;
import vista.PremiumForm;
import vista.VistaAnonimo;
import vista.VistaRegistrado;

public class ControladorVistaRegistrado implements ActionListener, WindowListener {
	private VistaRegistrado vista;
	private Aplicacion api;
	private SesionUsuarios sesion;
	private ArrayList<Element> elementos;
	private ArrayList<Validacion> pendientes;

	public ControladorVistaRegistrado(VistaRegistrado vista, Aplicacion api) {
		super();
		this.vista = vista;
		this.api = api;
		sesion = (SesionUsuarios) api.getSesion();
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

	// Metodo para rellenar la tabla de Validaciones
	public void rellenarTablePendientes(ArrayList<Validacion> pendientes) {
		// TODO Auto-generated method stub
		DefaultTableModel table = (DefaultTableModel) vista.getTablePendientes().getModel();
		table.getDataVector().removeAllElements();
		table.fireTableDataChanged();
		for (Validacion v : pendientes) {
			table.addRow(new Object[] { v.getCancion().getNombre(),
					(v.getPlazo().equals(LocalDate.MAX)) ? "No caduca" : v.getPlazo().toString() });
		}
		vista.getTablePendientes().setRowSorter(new TableRowSorter<TableModel>(table));
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object component = e.getSource();

		if (component == vista.getBtnLogOut()) {
			VistaAnonimo vistaR = new VistaAnonimo();
			ControladorVistaAnonimo controlA = new ControladorVistaAnonimo(vistaR, api);
			vistaR.setControlador(controlA);
			controlA.start();
			System.out.println("pasar a anonimo");
			vista.dispose();
		} else if (component == vista.getBtnPremium()) {
			PremiumForm premium = new PremiumForm();
			if (sesion.getUsuarioRegistrado().esPremium() == false) {
				System.out.println("boton premium pulsado");
				ControladorPremium controlP = new ControladorPremium(premium, api);
				premium.setControlador(controlP);
				premium.setVisible(true);
				controlP.start();
			} else {
				JOptionPane.showMessageDialog(premium, "El usuario ya es premium", "Error", JOptionPane.ERROR_MESSAGE);
			}

		} else if (component == vista.getBtnStop()) {
			sesion.stop();
		} else if (component == vista.getBtnPlay()) {
			int selection = vista.getTableSongs().getSelectedRow();
			sesion.reproducir(elementos.get(selection));
			System.out.println("reproduciendo " + elementos.get(selection).getNombre());

		} else if (component == vista.getBtnBusqueda()) {
			TIPO_BUSQUEDA filtro;
			filtro = TIPO_BUSQUEDA.valueOf(vista.getComboBusqueda().getSelectedItem().toString().toUpperCase());
			elementos = api.buscar(vista.getTfBusqueda().getText(), filtro);
			rellenarTableSongs(elementos);

		} else if (component == vista.getBtnSubir()) {
			String nombre = "love";

			System.out.println("a subir");
			JFileChooser fileChooser = new JFileChooser();
			int returnVal = fileChooser.showOpenDialog(null);
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				File origen = fileChooser.getSelectedFile();
				sesion.subirCancion(nombre, origen);
			} else {
				System.out.println("File access cancelled by user");
			}
			elementos = api.getLastSongs();
			rellenarTableSongs(elementos);
		} else if (component == vista.getBtnDenunciar()) {
			DenunciarForm denunciaF = new DenunciarForm();
			int selection = vista.getTableSongs().getSelectedRow();
			ControladorDenunciar controlD = new ControladorDenunciar(denunciaF, elementos.get(selection), sesion, vista,
					api);
			denunciaF.setControlador(controlD);
			controlD.start();
		}
	}

	public void start() {
		elementos = api.getLastSongs();
		pendientes = api.getValidacionesByUser(sesion.getUsuarioRegistrado());
		rellenarTableSongs(elementos);
		rellenarTablePendientes(pendientes);
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
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
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