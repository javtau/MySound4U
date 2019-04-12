package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import modelo.Album;
import modelo.Aplicacion;
import modelo.Cancion;
import modelo.Element;
import modelo.SesionAnonima;
import modelo.TIPO_BUSQUEDA;
import vista.VistaAnonimo;

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
			for (Element e : elements) {
				Cancion cancion = (Cancion) e;
				Album album = cancion.getAlbum();
				table.addRow(new Object[] { cancion.getNombre(), cancion.getDuracion(),cancion.getAutorNombre(),(album != null)? album.getNombre() : " "  });
			}
			vista.getTableSongs().setRowSorter(new TableRowSorter<TableModel>(table));
		}
	
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object component = e.getSource();

		if (component == vista.getBtnLogIn()) {

		} else if (component == vista.getBtnSingUp()) {

		} else if (component == vista.getBtnStop()) {

			System.out.println("parando");

		} else if (component == vista.getBtnPlay()) {
			int selection = vista.getTableSongs().getSelectedRow();
			elementos.get(selection).reproducir(sesion.getUsuario());
			System.out.println("reproduciendo " + elementos.get(selection).getNombre());

		} else if (component == vista.getBtnBusqueda()) {
			TIPO_BUSQUEDA filtro;

			filtro = TIPO_BUSQUEDA.valueOf(vista.getComboBusqueda().getSelectedItem().toString().toUpperCase());
			ArrayList<Element> ele = api.buscar(vista.getTfBusqueda().getText(), filtro);

		}

	}
	
	public void start() {
		elementos = api.getLastSongs();
		rellenarTableSongs(elementos);
		vista.setVisible(true);
	}

}
