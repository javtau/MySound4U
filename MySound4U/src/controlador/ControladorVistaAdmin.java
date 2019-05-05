package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
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

public class ControladorVistaAdmin implements ActionListener, WindowListener, ChangeListener {
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
			vista.dispose();
			sesion.stop();
		} else if (component == vista.getBtnStop()) {
			sesion.stop();
		} else if (component == vista.getBtnPlay()) {
			int selection = vista.getTableSongs().getSelectedRow();

			switch (vista.getTpOptions().getSelectedIndex()) {
			case 0:
				selection = vista.getTableSongs().getSelectedRow();
				if (selection > -1)
					sesion.reproducir(elementos.get(selection));
				break;
			case 1:
				selection = vista.gettableDenuncias().getSelectedRow();
				if (selection > -1)
					sesion.reproducir(denuncias.get(selection).getCancion());
				break;
			case 2:
				selection = vista.gettableValidaciones().getSelectedRow();
				if (selection > -1)
					sesion.reproducir(validaciones.get(selection).getCancion());
				break;

			default:
				break;
			}
		} else if (component == vista.getBtnBusqueda()) {
			TIPO_BUSQUEDA filtro;
			filtro = TIPO_BUSQUEDA.valueOf(vista.getComboBusqueda().getSelectedItem().toString().toUpperCase());
			elementos = api.buscar(vista.getTfBusqueda().getText(), filtro);
			vista.getTpOptions().setSelectedIndex(0);
			rellenarTableSongs(elementos);
		} else if (component == vista.getBtnGestionar()) {
			switch (vista.getTpOptions().getSelectedIndex()) {
			case 1:
				DenunciaForm formD = new DenunciaForm();
				int selec = vista.gettableDenuncias().getSelectedRow();
				ControladorDenuncia controlD = new ControladorDenuncia(formD, api, vista, denuncias.get(selec));
				formD.getTextArea().setText(denuncias.get(selec).getComentario());
				formD.getLbdenunciante().setText(denuncias.get(selec).getDenunciante().getNombre());
				formD.getLbcancion().setText(denuncias.get(selec).getCancion().getNombre());
				formD.getLbautor().setText(denuncias.get(selec).getCancion().getAutorNombre());
				formD.setControlador(controlD);
				controlD.start();
				break;
			case 2:
				ValidacionForm formV = new ValidacionForm();
				int selection = vista.gettableValidaciones().getSelectedRow();
				ControladorValidacion controlV = new ControladorValidacion(formV, api, vista,
						validaciones.get(selection));
				formV.setControlador(controlV);
				controlV.start();
				break;
			}
		} else if (component == vista.getBtnAjustes()) {
			AjustesForm ajustes = new AjustesForm();
			ControladorAjustes controlA = new ControladorAjustes(ajustes, api);
			ajustes.setControlador(controlA);
			ajustes.setVisible(true);
			controlA.start();
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

	@Override
	public void stateChanged(ChangeEvent e) {
		Object component = e.getSource();

		if (component == vista.getTpOptions()) {
			switch (vista.getTpOptions().getSelectedIndex()) {
			case 0:
				vista.getBtnGestionar().setVisible(false);
				break;
			case 1:
				vista.getBtnGestionar().setVisible(true);
				break;
			case 2:
				vista.getBtnGestionar().setVisible(true);
				break;

			default:
				break;
			}
		}
	}
}