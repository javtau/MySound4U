package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import modelo.Album;
import modelo.Aplicacion;
import modelo.Cancion;
import modelo.Element;
import modelo.Lista;
import modelo.SesionUsuarios;
import modelo.TIPO_BUSQUEDA;
import modelo.UsuarioRegistrado;
import vista.PremiumForm;
import vista.VistaAnonimo;
import vista.VistaRegistrado;

public class ControladorVistaRegistrado implements ActionListener, WindowListener, ChangeListener {
	private VistaRegistrado vista;
	private Aplicacion api;
	private SesionUsuarios sesion;
	private UsuarioRegistrado usuario;
	private ArrayList<Element> elementos;
	private ArrayList<Album> albumes;
	private ArrayList<Lista> listas;
	private ArrayList<Cancion> pendientes;

	public ControladorVistaRegistrado(VistaRegistrado vista, Aplicacion api) {
		super();
		this.vista = vista;
		this.api = api;
		sesion = (SesionUsuarios) api.getSesion();
		usuario = (UsuarioRegistrado) sesion.getUsuario();
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

	// Metodo para rellenar la tabla de albumes
	public void rellenarTableAlbums(ArrayList<Album> elements) {
		// TODO Auto-generated method stub
		DefaultTableModel table = (DefaultTableModel) vista.getTableAlbums().getModel();
		table.getDataVector().removeAllElements();
		table.fireTableDataChanged();
		for (Album album : elements) {
			table.addRow(new Object[] { album.getNombre(), album.getAutor().getNombre(), album.getNumSongs() });

		}
		vista.getTableAlbums().setRowSorter(new TableRowSorter<TableModel>(table));
	}

	// Metodo para rellenar la tabla de albumes
	public void rellenarTableList(ArrayList<Lista> elements) {
		// TODO Auto-generated method stub
		DefaultTableModel table = (DefaultTableModel) vista.getTableList().getModel();
		table.getDataVector().removeAllElements();
		table.fireTableDataChanged();
		for (Lista lista : elements) {
			table.addRow(new Object[] { lista.getNombre(), lista.getNumElements() });
		}
		vista.getTableList().setRowSorter(new TableRowSorter<TableModel>(table));
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
			int selection = 0;
			switch (vista.getTpOptions().getSelectedIndex()) {
			case 0:
				selection = vista.getTableSongs().getSelectedRow();
				if (selection > -1)
				elementos.get(selection).reproducir(sesion.getUsuario());
				break;
			case 1:
				selection = vista.getTableAlbums().getSelectedRow();
				if (selection > -1)
					albumes.get(selection).reproducir(sesion.getUsuario());
				break;
			case 2:
				selection = vista.getTableList().getSelectedRow();
				if (selection > -1)
					listas.get(selection).reproducir(sesion.getUsuario());
				break;
			case 3:

				break;

			default:
				break;
			}
			
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
			elementos = new ArrayList<>(usuario.getCanciones());
			//elementos = api.getLastSongs();
			rellenarTableSongs(elementos);

		}

	}

	public void start() {
		elementos = api.getLastSongs();
		rellenarTableSongs(elementos);
		vista.pack();
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

	@Override
	public void stateChanged(ChangeEvent e) {
		Object component = e.getSource();
		
		if (component == vista.getTpOptions()) {
			switch (vista.getTpOptions().getSelectedIndex()) {
			case 0:
				//elementos = new ArrayList<>(usuario.getCanciones());
				elementos = api.getLastSongs();
				rellenarTableSongs(elementos);
				vista.getBtnDenunciar().setVisible(true);
				break;
			case 1:
				albumes = usuario.getAlbumes();
				rellenarTableAlbums(albumes);
				vista.getBtnDenunciar().setVisible(false);
				break;
			case 2:
				listas = usuario.getListas();
				rellenarTableList(listas);
				vista.getBtnDenunciar().setVisible(false);
				break;
			case 3:

				vista.getBtnDenunciar().setVisible(false);
				break;

			default:
				break;
			}
		}

	}
}
