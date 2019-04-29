package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import modelo.Album;
import modelo.Aplicacion;
import modelo.Cancion;
import modelo.Denuncia;
import modelo.Element;
import modelo.SesionAdmin;
import modelo.TIPO_BUSQUEDA;
import modelo.Validacion;
import vista.AjustesForm;
import vista.DenunciaForm;
import vista.ValidacionForm;
import vista.VistaAdmin;
import vista.VistaAnonimo;

public class ControladorVistaAdmin implements ActionListener, WindowListener {
	private VistaAdmin vista;
	private Aplicacion api;
	private SesionAdmin sesion;
	private ArrayList<Element> elementos;
	private ArrayList<Validacion> validaciones;
	private ArrayList<Denuncia> denuncias;

	public ControladorVistaAdmin(VistaAdmin vista, Aplicacion api) {
		super();
		this.vista = vista;
		this.api = api;
		sesion = (SesionAdmin) api.getSesion();
	}

	// Metodo para rellenar la tabla de canciones
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
	public void rellenarTablevalidaciones(ArrayList<Validacion> validaciones) {
		// TODO Auto-generated method stub
		DefaultTableModel table = (DefaultTableModel) vista.gettableValidaciones().getModel();
		table.getDataVector().removeAllElements();
		table.fireTableDataChanged();
		for (Validacion v : validaciones) {
			table.addRow(new Object[] { v.getCancion().getNombre(), v.getCancion().getAutorNombre(),
					(v.getPlazo().equals(LocalDate.MAX)) ? "No caduca" : v.getPlazo().toString() });
		}
		vista.gettableValidaciones().setRowSorter(new TableRowSorter<TableModel>(table));
	}

	// Metodo para rellenar la tabla de Validaciones
	public void rellenarTabledenuncias(ArrayList<Denuncia> denuncias) {
		// TODO Auto-generated method stub
		DefaultTableModel table = (DefaultTableModel) vista.gettableDenuncias().getModel();
		table.getDataVector().removeAllElements();
		table.fireTableDataChanged();
		for (Denuncia d : denuncias) {
			table.addRow(new Object[] { d.getDenunciante().getNombre(), d.getCancion().getAutorNombre(),
					d.getCancion().getNombre(), d.getComentario() });

		}
		vista.gettableDenuncias().setRowSorter(new TableRowSorter<TableModel>(table));
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

		} else if (component == vista.getBtnGestionar()) {
			if (vista.getTpOptionsIndex() == 2) {
				ValidacionForm formV = new ValidacionForm();
				int selection = vista.gettableValidaciones().getSelectedRow();
				ControladorValidacion controlV = new ControladorValidacion(formV, api, vista,
						validaciones.get(selection));
				formV.setControlador(controlV);
				controlV.start();
			}

		} else if (component == vista.getBtnAjustes()) {
			AjustesForm ajustes = new AjustesForm();
			ControladorAjustes controlA = new ControladorAjustes(ajustes, api);
			ajustes.setControlador(controlA);
			ajustes.setVisible(true);
			controlA.start();

		} else if (vista.getTpOptionsIndex() == 1) {
			DenunciaForm formD = new DenunciaForm();
			int selection = vista.gettableDenuncias().getSelectedRow();
			ControladorDenuncia controlD = new ControladorDenuncia(formD, api, vista, denuncias.get(selection));
			formD.getTextArea().setText(denuncias.get(selection).getComentario());
			formD.getLbdenunciante().setText(denuncias.get(selection).getDenunciante().getNombre());
			formD.getLbcancion().setText(denuncias.get(selection).getCancion().getNombre());
			formD.getLbautor().setText(denuncias.get(selection).getCancion().getAutorNombre());
			formD.setControlador(controlD);
			controlD.start();
		}
	}

	public void start() {
		loadTable();
		vista.setVisible(true);
	}

	public void loadTable() {
		validaciones = api.getValidaciones();
		denuncias = api.getDenuncias();
		elementos = api.getLastSongs();
		rellenarTableSongs(elementos);
		rellenarTablevalidaciones(validaciones);
		rellenarTabledenuncias(denuncias);
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