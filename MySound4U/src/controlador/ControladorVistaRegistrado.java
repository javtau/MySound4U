package controlador;

import java.awt.List;
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
import vista.VistaDenunciarForm;
import vista.VistaEditarCancionForm;
import vista.VistaPerfilForm;
import vista.VistaPremiumForm;
import vista.VistaSubirCancionForm;
import vista.VistaAnonimo;
import vista.VistaCrearAlbum;
import vista.VistaCrearLista;
import vista.VistaRegistrado;
import vista.VistaAddToAlbum;
import vista.VistaAddToList;;

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

	// Metodo para cambiar de pestaña
	public void changeTablePane(int index) {
		vista.getTpOptions().setSelectedIndex(index);
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

	// Metodo para rellenar la tabla de listas
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
// Metodo que captura las acciones realizadas por el usuario
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
		} else if (component == vista.getBtnPremium()) {
			VistaPremiumForm premium = new VistaPremiumForm();
			if (sesion.getUsuarioRegistrado().esPremium() == false) {
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
				if (selection > -1) {
					sesion.reproducir(elementos.get(selection));
				} else {
					JOptionPane.showMessageDialog(null, "No tienes seleccionada ninguna cancion", "Atencion",
							JOptionPane.ERROR_MESSAGE);
				}

				break;
			case 1:
				selection = vista.getTableAlbums().getSelectedRow();
				if (selection > -1) {
					sesion.reproducir(albumes.get(selection));
				} else {
					JOptionPane.showMessageDialog(null, "No tienes seleccionado ningun album", "Atencion",
							JOptionPane.ERROR_MESSAGE);
				}
				break;
			case 2:
				selection = vista.getTableList().getSelectedRow();
				if (selection > -1) {
					sesion.reproducir(listas.get(selection));
				} else {
					JOptionPane.showMessageDialog(null, "No tienes seleccionada ninguna lista", "Atencion",
							JOptionPane.ERROR_MESSAGE);
				}
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
			vista.getTpOptions().setSelectedIndex(0);
			rellenarTableSongs(elementos);
		} else if (component == vista.getBtnSubir()) {
			VistaSubirCancionForm subir = new VistaSubirCancionForm();
			ControladorSubirCancion controlS = new ControladorSubirCancion(subir, sesion, vista, api, this);
			subir.setControlador(controlS);
			controlS.start();
		} else if (component == vista.getBtnDenunciar()) {
			int selection = vista.getTableSongs().getSelectedRow();
			if (selection > -1) {
				if (((Cancion) elementos.get(selection)).getAutor() == usuario)
					JOptionPane.showMessageDialog(null, "No se puede denunciar a si mismo", "Denunciar",
							JOptionPane.ERROR_MESSAGE);
				else {
					VistaDenunciarForm denunciaF = new VistaDenunciarForm();
					ControladorDenunciar controlD = new ControladorDenunciar(denunciaF, elementos.get(selection),
							sesion, vista, api);
					denunciaF.setControlador(controlD);
					controlD.start();
				}
			} else {
				JOptionPane.showMessageDialog(null, "No tienes seleccionada ninguna cancion", "Atencion",
						JOptionPane.ERROR_MESSAGE);
			}
		} else if (component == vista.getBtnSeguir()) {
			int selection = vista.getTableUsuarios().getSelectedRow();
			if (selection > -1) {
				sesion.seguir(api.getOtrosUsuarios().get(selection));
				rellenarTableUsuarios(usuarios);
			} else {
				JOptionPane.showMessageDialog(null, "No tienes seleccionado ningun usuario", "Atencion",
						JOptionPane.ERROR_MESSAGE);
			}
		} else if (component == vista.getBtnUnfollow()) {
			int selection = vista.getTableUsuarios().getSelectedRow();
			if (selection > -1) {
				sesion.dejarDeSeguir(api.getOtrosUsuarios().get(selection));
				rellenarTableUsuarios(usuarios);
			} else {
				JOptionPane.showMessageDialog(null, "No tienes seleccionado ningun usuario", "Atencion",
						JOptionPane.ERROR_MESSAGE);
			}
		} else if (component == vista.getBtnEditar()) {
			int selection = vista.getTableSongs().getSelectedRow();
			if (selection > -1) {
				if (((Cancion) elementos.get(selection)).getAutor() != usuario)
					JOptionPane.showMessageDialog(null, "No se puede editar una cancion que no es tuya",
							"Editar cancion", JOptionPane.ERROR_MESSAGE);
				else if (((Cancion) elementos.get(selection)).esValidada() == true)
					JOptionPane.showMessageDialog(null, "No se puede editar una cancion que ya ha sido validada",
							"Editar cancion", JOptionPane.ERROR_MESSAGE);
				else if (((Cancion) elementos.get(selection)).enRevision() == false)
					JOptionPane.showMessageDialog(null, "La cancion esta en revision por el administrador",
							"Editar cancion", JOptionPane.INFORMATION_MESSAGE);
				else {
					VistaEditarCancionForm edit = new VistaEditarCancionForm();
					ControladorEditarCancion controlE = new ControladorEditarCancion(edit, sesion, vista, api,
							((Cancion) elementos.get(selection)));
					edit.setControlador(controlE);
					controlE.start();
					elementos = new ArrayList<>(usuario.getCanciones());
					elementos = api.getLastSongs();
					rellenarTableSongs(elementos);
				}
			} else {
				JOptionPane.showMessageDialog(null, "No tienes seleccionada ninguna cancion", "Atencion",
						JOptionPane.ERROR_MESSAGE);
			}
		} else if (component == vista.getBtnBorrar()) {
			int selection = vista.getTableSongs().getSelectedRow();
			if (selection > -1) {
				if (((Cancion) elementos.get(selection)).getAutor() != usuario) {
					JOptionPane.showMessageDialog(null, "No se puede borrar una cancion que no es tuya",
							"Borrar cancion", JOptionPane.ERROR_MESSAGE);
				} else {
					if (JOptionPane.showConfirmDialog(null, "Esta seguro de que desea borrar la cancion?", "Atencion",
							JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
						sesion.borrarCancion(((Cancion) elementos.get(selection)));
						elementos = new ArrayList<>(usuario.getCanciones());
						elementos = api.getLastSongs();
						rellenarTableSongs(elementos);
					}
				}
			} else {
				JOptionPane.showMessageDialog(null, "No tienes seleccionada ninguna cancion", "Atencion",
						JOptionPane.ERROR_MESSAGE);
			}
		} else if (component == vista.getBtnPerfil()) {
			VistaPerfilForm perfil = new VistaPerfilForm();
			ControladorPerfil controlP = new ControladorPerfil(perfil, vista);
			perfil.setControlador(controlP);
			controlP.start();
		} else if (component == vista.getBtnAddToAlbum()) {

			if (vista.getTpOptions().getSelectedIndex() == 1) {
				int selection = vista.getTableAlbums().getSelectedRow();
				if (selection > -1) {
					VistaAddToAlbum addAlbum = new VistaAddToAlbum();
					ControladorAddToAlbum controlA = new ControladorAddToAlbum(addAlbum, api, albumes.get(selection),
							this);
					addAlbum.setControlador(controlA);
					controlA.start();
				} else {
					JOptionPane.showMessageDialog(null, "No tienes seleccionada ningun Album", "Atencion",
							JOptionPane.ERROR_MESSAGE);
				}
			} else {
				int selection = vista.getTableList().getSelectedRow();
				if (selection > -1) {
					VistaAddToList vl = new VistaAddToList();
					ControladorAddToList cl = new ControladorAddToList(vl, api, new Lista("dd"), this);
					vl.setControlador(cl);
					cl.start();
				} else {
					JOptionPane.showMessageDialog(null, "No tienes seleccionada ninguna lista", "Atencion",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		} else if (component == vista.getBtnCrearAlbum()) {
			if (usuario.getCanciones().size() < 1)
				JOptionPane.showMessageDialog(null, "No tienes subida ninguna cancion para añadir en el album",
						"Crear album", JOptionPane.ERROR_MESSAGE);
			else {
				VistaCrearAlbum crearAlbum = new VistaCrearAlbum();
				ControladorCrearAlbum controlC = new ControladorCrearAlbum(crearAlbum, sesion, vista, api, this);
				crearAlbum.setControlador(controlC);
				controlC.start();
				
			}
		} else if (component == vista.getBtnCrearList()) {
			if (usuario.getCanciones().size() < 1 && usuario.getAlbumes().size() < 1 && usuario.getListas().size() < 1)
				JOptionPane.showMessageDialog(null, "No tienes subido ninguna elemento que se pueda añadir en la lista",
						"Crear lista", JOptionPane.ERROR_MESSAGE);
			else {
				VistaCrearLista vistaCL = new VistaCrearLista();
				ControladorCrearLista controlC = new ControladorCrearLista(vistaCL, api, this);
				vistaCL.setControlador(controlC);
				controlC.start();

			}
		}
	}
//Metodo que se debe ejecutar al abrir la vista
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
// Al cerrar la ventana pregunta que si esta seguro y guarda los cambios
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
// Segun la subtabla en la que nos encontremos, los botones se hacen o dejan de hacerse visibles.
	@Override
	public void stateChanged(ChangeEvent e) {
		Object component = e.getSource();

		if (component == vista.getTpOptions()) {
			switch (vista.getTpOptions().getSelectedIndex()) {
			case 0:
				elementos = new ArrayList<>(usuario.getCanciones());
				elementos = api.getLastSongs();
				rellenarTableSongs(elementos);
				vista.getBtnAddToAlbum().setVisible(false);
				vista.getBtnBorrar().setVisible(true);
				vista.getBtnEditar().setVisible(true);
				vista.getBtnSeguir().setVisible(false);
				vista.getBtnUnfollow().setVisible(false);
				vista.getBtnDenunciar().setVisible(true);
				break;
			case 1:
				// albumes = usuario.getAlbumes();
				// rellenarTableAlbums(albumes);
				vista.getBtnAddToAlbum().setVisible(true);
				vista.getBtnAddToAlbum().setText("add song");
				vista.getBtnBorrar().setVisible(false);
				vista.getBtnEditar().setVisible(false);
				vista.getBtnSeguir().setVisible(false);
				vista.getBtnUnfollow().setVisible(false);
				vista.getBtnDenunciar().setVisible(false);
				break;
			case 2:
				// listas = usuario.getListas();
				// rellenarTableList(listas);
				vista.getBtnAddToAlbum().setVisible(true);
				vista.getBtnAddToAlbum().setText("add list");
				vista.getBtnBorrar().setVisible(false);
				vista.getBtnEditar().setVisible(false);
				vista.getBtnSeguir().setVisible(false);
				vista.getBtnUnfollow().setVisible(false);
				vista.getBtnDenunciar().setVisible(false);
				break;
			case 3:
				vista.getBtnAddToAlbum().setVisible(false);
				vista.getBtnBorrar().setVisible(false);
				vista.getBtnEditar().setVisible(false);
				vista.getBtnSeguir().setVisible(false);
				vista.getBtnUnfollow().setVisible(false);
				vista.getBtnDenunciar().setVisible(false);
				break;
			case 4:
				vista.getBtnAddToAlbum().setVisible(false);
				vista.getBtnBorrar().setVisible(false);
				vista.getBtnEditar().setVisible(false);
				vista.getBtnDenunciar().setVisible(false);
				vista.getBtnSeguir().setVisible(true);
				vista.getBtnUnfollow().setVisible(true);
				break;
			case 5:
				vista.getBtnAddToAlbum().setVisible(false);
				vista.getBtnBorrar().setVisible(false);
				vista.getBtnEditar().setVisible(false);
				vista.getBtnDenunciar().setVisible(false);
				vista.getBtnSeguir().setVisible(false);
				vista.getBtnUnfollow().setVisible(false);
				break;
			default:
				break;
			}
		}
	}
}