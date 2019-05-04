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

import modelo.*;
import vista.DenunciarForm;
import vista.PremiumForm;
import vista.SubirCancionForm;
import vista.VistaAnonimo;
import vista.VistaRegistrado;

public class ControladorVistaRegistrado implements ActionListener, WindowListener, ChangeListener {
	private VistaRegistrado vista;
	private Aplicacion api;
	private SesionUsuarios sesion;
	private UsuarioRegistrado usuario;
	private ArrayList<Element> elementos;
	private ArrayList<String> noticias;
	private ArrayList<UsuarioRegistrado> usuarios;
	private ArrayList<Album> albumes;
	private ArrayList<Lista> listas;
	private ArrayList<Validacion> pendientes;

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

	// Metodo para rellenar la tabla de usuarios
	public void rellenarTableUsuarios(ArrayList<UsuarioRegistrado> usuarios) {
		boolean seguido = false;
		// TODO Auto-generated method stub
		DefaultTableModel table = (DefaultTableModel) vista.getTableUsuarios().getModel();
		table.getDataVector().removeAllElements();
		table.fireTableDataChanged();
		for (UsuarioRegistrado u : usuarios) {
			seguido = false;
			if (usuario.getSeguidos().contains(u)) {
				seguido = true;
			}
			table.addRow(new Object[] { u.getNombre(),
					(seguido == true) ? "Ya sigues a este usuario" : "Comienza a seguir a este usuario" });
		}
		vista.getTableUsuarios().setRowSorter(new TableRowSorter<TableModel>(table));
	}

	// Metodo para rellenar la tabla de noticias
	public void rellenarTableNoticias(ArrayList<String> noticias) {
		DefaultTableModel table = (DefaultTableModel) vista.getTableNoticias().getModel();
		table.getDataVector().removeAllElements();
		table.fireTableDataChanged();
		for (String n : noticias) {
			table.addRow(new Object[] { n });
		}
		vista.getTableNoticias().setRowSorter(new TableRowSorter<TableModel>(table));
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
			sesion.stop();
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
					sesion.reproducir(elementos.get(selection));
				break;
			case 1:
				selection = vista.getTableAlbums().getSelectedRow();
				if (selection > -1)
					sesion.reproducir(albumes.get(selection));
				break;
			case 2:
				selection = vista.getTableList().getSelectedRow();
				if (selection > -1)
					sesion.reproducir(listas.get(selection));
				break;
			case 3:
				selection = vista.getTablePendientes().getSelectedRow();
				if (selection > -1)
					sesion.reproducir(pendientes.get(selection).getCancion());
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
			SubirCancionForm subir = new SubirCancionForm();
			ControladorSubirCancion controlS = new ControladorSubirCancion(subir, sesion, vista, api);
			subir.setControlador(controlS);
			controlS.start();

			elementos = new ArrayList<>(usuario.getCanciones());
			elementos = api.getLastSongs();
			rellenarTableSongs(elementos);
		} else if (component == vista.getBtnDenunciar()) {
			int selection = vista.getTableSongs().getSelectedRow();
			if (selection > -1) {
				DenunciarForm denunciaF = new DenunciarForm();
				ControladorDenunciar controlD = new ControladorDenunciar(denunciaF, elementos.get(selection), sesion,
						vista, api);
				denunciaF.setControlador(controlD);
				controlD.start();
			}

		} else if (component == vista.getBtnSeguir()) {
			int selection = vista.getTableUsuarios().getSelectedRow();
			if (selection > -1) {
				sesion.seguir(api.getOtrosUsuarios().get(selection));
				rellenarTableUsuarios(usuarios);
				System.out.println(api.getOtrosUsuarios().get(selection).getNombre() + " Seguido.");
			}

		} else if (component == vista.getBtnUnfollow()) {
			int selection = vista.getTableUsuarios().getSelectedRow();
			if (selection > -1) {
				sesion.dejarDeSeguir(api.getOtrosUsuarios().get(selection));
				rellenarTableUsuarios(usuarios);
				System.out.println(api.getOtrosUsuarios().get(selection).getNombre() + " Dejado se seguir.");
			}
		}
	}

	public void start() {
		vista.getBtnSeguir().setVisible(false);
		vista.getBtnUnfollow().setVisible(false);
		usuarios = api.getOtrosUsuarios();
		elementos = api.getLastSongs();
		noticias = sesion.getMisNoticias();
		pendientes = api.getValidacionesByUser(sesion.getUsuarioRegistrado());
		rellenarTableSongs(elementos);
		vista.pack();
		albumes = usuario.getAlbumes();
		rellenarTableAlbums(albumes);
		listas = usuario.getListas();
		rellenarTableList(listas);
		rellenarTableNoticias(noticias);
		rellenarTablePendientes(pendientes);
		rellenarTableUsuarios(usuarios);
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
				elementos = new ArrayList<>(usuario.getCanciones());
				elementos = api.getLastSongs();
				rellenarTableSongs(elementos);
				vista.getBtnSeguir().setVisible(false);
				vista.getBtnUnfollow().setVisible(false);
				vista.getBtnDenunciar().setVisible(true);
				break;
			case 1:
				// albumes = usuario.getAlbumes();
				// rellenarTableAlbums(albumes);
				vista.getBtnSeguir().setVisible(false);
				vista.getBtnUnfollow().setVisible(false);
				vista.getBtnDenunciar().setVisible(false);
				break;
			case 2:
				// listas = usuario.getListas();
				// rellenarTableList(listas);
				vista.getBtnSeguir().setVisible(false);
				vista.getBtnUnfollow().setVisible(false);
				vista.getBtnDenunciar().setVisible(false);
				break;
			case 3:
				vista.getBtnSeguir().setVisible(false);
				vista.getBtnUnfollow().setVisible(false);
				vista.getBtnDenunciar().setVisible(false);
				break;
			case 4:
				vista.getBtnDenunciar().setVisible(false);
				vista.getBtnSeguir().setVisible(true);
				vista.getBtnUnfollow().setVisible(true);
				break;

			default:
				break;
			}
		}
	}
}