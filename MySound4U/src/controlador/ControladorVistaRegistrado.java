package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import modelo.Album;
import modelo.Aplicacion;
import modelo.Cancion;
import modelo.Element;
import modelo.SesionAnonima;
import modelo.SesionUsuarios;
import modelo.TIPO_BUSQUEDA;
import vista.VistaAnonimo;
import vista.VistaRegistrado;

public class ControladorVistaRegistrado implements ActionListener {
	private VistaRegistrado vista;
	private Aplicacion api;
	private SesionUsuarios sesion;
	private ArrayList<Element> elementos;

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

		} else if (component == vista.getBtnStop()) {
			sesion.stop();
		} else if (component == vista.getBtnPlay()) {
			int selection = vista.getTableSongs().getSelectedRow();
			elementos.get(selection).reproducir(sesion.getUsuario());
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

		}

	}

	public void start() {
		elementos = api.getLastSongs();
		rellenarTableSongs(elementos);
		vista.setVisible(true);
	}

}
