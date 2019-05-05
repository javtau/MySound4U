package vista;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import controlador.ControladorVistaRegistrado;

public class VistaRegistrado extends JFrame {

	private static final long serialVersionUID = 1L;

	String[] searchTipes = { "Todo", "Titulo", "Autor", "Album" };

	// Contenedor Principal,
	private Container contenedor;

	// Elementos del panel norte
	JPanel pNorth;
	private JTextField tfBusqueda;
	private JComboBox<String> comboBusqueda;
	private JButton btnBusqueda;

	// Elementos del panel este
	JPanel pEast;
	private JButton btnLogOut;
	private JButton btnPremium;
	private JButton btnSeguir;
	private JButton btnUnfollow;
	private JButton btnSubir;
	private JButton btnDenunciar;
	private JButton btnEditar;
	private JButton btnBorrar;
	private JButton btnPerfil;

	// Elementos del panel sur
	JPanel psouth;
	private JButton btnPlay;
	private JButton btnStop;

	// Elementos del panel central
	JPanel pCenter;
	private JTabbedPane tpOptions;

	// Tablas de los paneles
	private JTable tableSongs;
	private JTable tableAlbums;
	private JTable tableList;
	private JTable tablePendientes;
	private JTable tableUsuarios;
	private JTable tableNoticias;

	public VistaRegistrado() {

		// Configuracion ventana
		setTitle("MySound4U");
		setSize(810, 700);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setLocationRelativeTo(null);
		contenedor = new JPanel();
		contenedor.setLayout(new BorderLayout());
		setContentPane(contenedor);

		// Creamos el panel norte y sus elementos

		pNorth = new JPanel();
		tfBusqueda = new RoundJTextField(40);
		comboBusqueda = new JComboBox<>(searchTipes);
		btnBusqueda = new JButton("Buscar");
		btnPerfil = new JButton("Perfil");

		pNorth.setLayout(new FlowLayout(FlowLayout.LEFT));

		pNorth.add(tfBusqueda);
		pNorth.add(comboBusqueda);
		pNorth.add(btnBusqueda);
		// pNorth.setVisible(true);
		contenedor.add(pNorth, BorderLayout.NORTH);

		// Creamos el panel este y sus elementos

		pEast = new JPanel();
		JPanel pBox = new JPanel();
		btnLogOut = new JButton("  Logout  ");
		btnPremium = new JButton(" Premium");
		btnSubir = new JButton("    Subir    ");
		btnSeguir = new JButton("   Seguir   ");
		btnUnfollow = new JButton(" Unfollow ");
		btnDenunciar = new JButton("Denunciar");
		btnEditar = new JButton("   Editar   ");
		btnBorrar = new JButton("   Borrar   ");

		pEast.setLayout(new FlowLayout(FlowLayout.CENTER));
		pBox.setLayout(new BoxLayout(pBox, BoxLayout.Y_AXIS));

		pBox.add(Box.createRigidArea(new Dimension(0, 20)));
		pBox.add(btnLogOut);
		pBox.add(Box.createRigidArea(new Dimension(0, 7)));
		pBox.add(btnPremium);
		pBox.add(Box.createRigidArea(new Dimension(0, 7)));
		pBox.add(btnEditar);
		pBox.add(Box.createRigidArea(new Dimension(0, 7)));
		pBox.add(btnBorrar);
		pBox.add(Box.createRigidArea(new Dimension(0, 300)));
		pBox.add(btnSeguir);
		pBox.add(Box.createRigidArea(new Dimension(0, 7)));
		pBox.add(btnUnfollow);
		pBox.add(Box.createRigidArea(new Dimension(0, 7)));
		pBox.add(btnSubir);
		pBox.add(Box.createRigidArea(new Dimension(0, 7)));
		pBox.add(btnDenunciar);
		pEast.add(pBox);

		contenedor.add(pEast, BorderLayout.EAST);

		// Creamos el panel sur y sus elementos

		psouth = new JPanel();
		JPanel psouthL = new JPanel();
		JPanel psouthR = new JPanel();
		btnPlay = new JButton("Play");
		btnStop = new JButton("Stop");

		psouthL.setLayout(new FlowLayout());

		psouthL.add(btnPlay);
		psouthL.add(btnStop);
		psouthR.add(Box.createRigidArea(new Dimension(110, 0)));

		psouth.add(psouthL);
		psouth.add(psouthR);

		contenedor.add(psouth, BorderLayout.SOUTH);

		// Creamos el panel central y sus elementos
		pCenter = new JPanel();
		tpOptions = new JTabbedPane();

		// Panel de canciones
		JPanel tpTabSongs = new JPanel();
		JScrollPane scrollPane = new JScrollPane();

		tableSongs = new JTable();
		tableSongs.setModel(
				new DefaultTableModel(new Object[][] {}, new String[] { "Titulo", "Duracion", "Autor", "Album" }) {

					private static final long serialVersionUID = 1L;
					Class[] columnTypes = new Class[] { String.class, Double.class, String.class, String.class };

					public Class getColumnClass(int columnIndex) {
						return columnTypes[columnIndex];
					}
				});
		DefaultTableCellRenderer modelocentrar = new DefaultTableCellRenderer();
		modelocentrar.setHorizontalAlignment(SwingConstants.CENTER);
		tableSongs.getColumnModel().getColumn(0).setCellRenderer(modelocentrar);
		tableSongs.getColumnModel().getColumn(1).setCellRenderer(modelocentrar);
		tableSongs.getColumnModel().getColumn(2).setCellRenderer(modelocentrar);
		tableSongs.getColumnModel().getColumn(3).setCellRenderer(modelocentrar);
		scrollPane.setViewportView(tableSongs);

		scrollPane.setPreferredSize(new Dimension(650, 542));
		tpTabSongs.add(scrollPane);

		// Panel de albumes

		JPanel tpTabAlbums = new JPanel();
		JScrollPane scrollPaneAl = new JScrollPane();

		tableAlbums = new JTable();
		tableAlbums.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "Titulo", "Autor", "Canciones" }) {

			private static final long serialVersionUID = 1L;
			Class[] columnTypes = new Class[] { String.class, String.class, Integer.class };

			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});

		scrollPaneAl.setViewportView(tableAlbums);
		tableAlbums.getColumnModel().getColumn(0).setCellRenderer(modelocentrar);
		tableAlbums.getColumnModel().getColumn(1).setCellRenderer(modelocentrar);
		tableAlbums.getColumnModel().getColumn(2).setCellRenderer(modelocentrar);
		scrollPaneAl.setPreferredSize(new Dimension(650, 542));
		tpTabAlbums.add(scrollPaneAl);

		// Panel de Listas

		JPanel tpTabList = new JPanel();
		JScrollPane scrollPaneList = new JScrollPane();

		tableList = new JTable();
		tableList.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "Titulo", "Canciones" }) {

			private static final long serialVersionUID = 1L;
			Class[] columnTypes = new Class[] { String.class, Integer.class };

			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});

		scrollPaneList.setViewportView(tableList);
		tableList.getColumnModel().getColumn(0).setCellRenderer(modelocentrar);
		tableList.getColumnModel().getColumn(1).setCellRenderer(modelocentrar);
		scrollPaneList.setPreferredSize(new Dimension(650, 542));
		tpTabList.add(scrollPaneList);

		// Panel de pendientes

		JPanel tpTabPendientes = new JPanel();
		JScrollPane scrollPanePend = new JScrollPane();

		tablePendientes = new JTable();
		tablePendientes.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "Cancion", "Plazo" }) {

			private static final long serialVersionUID = 1L;
			Class[] columnTypes = new Class[] { String.class, Integer.class };

			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});

		scrollPanePend.setViewportView(tablePendientes);
		tablePendientes.getColumnModel().getColumn(0).setCellRenderer(modelocentrar);
		tablePendientes.getColumnModel().getColumn(1).setCellRenderer(modelocentrar);
		scrollPanePend.setPreferredSize(new Dimension(650, 542));
		tpTabPendientes.add(scrollPanePend);

		// Panel de usuarios

		JPanel tpTabUsuarios = new JPanel();
		JScrollPane scrollPaneUs = new JScrollPane();

		tableUsuarios = new JTable();
		tableUsuarios.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "Nombre", "Estado" }) {

			private static final long serialVersionUID = 1L;
			Class[] columnTypes = new Class[] { String.class, String.class };

			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});

		scrollPaneUs.setViewportView(tableUsuarios);
		tableUsuarios.getColumnModel().getColumn(0).setCellRenderer(modelocentrar);
		tableUsuarios.getColumnModel().getColumn(1).setCellRenderer(modelocentrar);
		scrollPaneUs.setPreferredSize(new Dimension(650, 542));
		tpTabUsuarios.add(scrollPaneUs);

		// Panel de noticias

		JPanel tpTabNoticias = new JPanel();
		JScrollPane scrollPaneNot = new JScrollPane();

		tableNoticias = new JTable();
		tableNoticias.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "Novedades" }) {

			private static final long serialVersionUID = 1L;
			Class[] columnTypes = new Class[] { String.class };

			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});

		scrollPaneNot.setViewportView(tableNoticias);
		tableNoticias.getColumnModel().getColumn(0).setCellRenderer(modelocentrar);
		scrollPaneNot.setPreferredSize(new Dimension(650, 542));
		tpTabNoticias.add(scrollPaneNot);

		// Anadimos los paneles al contenedor con el metodo addTab(<titulo>,<panel>)
		tpOptions.addTab("Canciones", tpTabSongs);
		tpOptions.addTab("Albumes", tpTabAlbums);
		tpOptions.addTab("Listas", tpTabList);
		tpOptions.addTab("Pendientes", tpTabPendientes);
		tpOptions.addTab("Usuarios", tpTabUsuarios);
		tpOptions.addTab("Noticias", tpTabNoticias);
		tpOptions.setPreferredSize(new Dimension(665, 580));
		// Podemos seleccionar una pestana del contendor con setSelectedIndex(<indice>)
		tpOptions.setSelectedIndex(0);

		// Crear panel central

		pCenter.add(tpOptions);

		// Anadir componentes al contenedor
		contenedor.add(pCenter, BorderLayout.CENTER);
	}

	public JTextField getTfBusqueda() {
		return tfBusqueda;
	}

	public void setTfBusqueda(JTextField tfBusqueda) {
		this.tfBusqueda = tfBusqueda;
	}

	public JComboBox<String> getComboBusqueda() {
		return comboBusqueda;
	}

	public void setComboBusqueda(JComboBox<String> comboBusqueda) {
		this.comboBusqueda = comboBusqueda;
	}

	public JButton getBtnBusqueda() {
		return btnBusqueda;
	}

	public void setBtnBusqueda(JButton btnBusqueda) {
		this.btnBusqueda = btnBusqueda;
	}

	public JButton getBtnLogOut() {
		return btnLogOut;
	}

	public void setBtnLogOut(JButton btnLogOut) {
		this.btnLogOut = btnLogOut;
	}

	public JButton getBtnPremium() {
		return btnPremium;
	}

	public void setBtnPremium(JButton btnPremium) {
		this.btnPremium = btnPremium;
	}

	public JButton getBtnSeguir() {
		return btnSeguir;
	}

	public void setBtnSeguir(JButton btnSeguir) {
		this.btnSeguir = btnSeguir;
	}

	public JButton getBtnUnfollow() {
		return btnUnfollow;
	}

	public void setBtnUnfollow(JButton btnUnfollow) {
		this.btnUnfollow = btnUnfollow;
	}

	public JButton getBtnSubir() {
		return btnSubir;
	}

	public void setBtnSubir(JButton btnSubir) {
		this.btnSubir = btnSubir;
	}

	public JButton getBtnDenunciar() {
		return btnDenunciar;
	}

	public void setBtnDenunciar(JButton btnDenunciar) {
		this.btnDenunciar = btnDenunciar;
	}

	public JButton getBtnPlay() {
		return btnPlay;
	}

	public void setBtnPlay(JButton btnPlay) {
		this.btnPlay = btnPlay;
	}

	public JButton getBtnStop() {
		return btnStop;
	}

	public void setBtnStop(JButton btnStop) {
		this.btnStop = btnStop;
	}

	public JTabbedPane getTpOptions() {
		return tpOptions;
	}

	public JButton getBtnEditar() {
		return btnEditar;
	}

	public void setBtnEditar(JButton btnEditar) {
		this.btnEditar = btnEditar;
	}

	public JButton getBtnBorrar() {
		return btnBorrar;
	}

	public void setBtnBorrar(JButton btnBorrar) {
		this.btnBorrar = btnBorrar;
	}

	public void setTpOptions(JTabbedPane tpOptions) {
		this.tpOptions = tpOptions;
	}

	public JTable getTableSongs() {
		return tableSongs;
	}

	public JTable getTableUsuarios() {
		return tableUsuarios;
	}

	public void setTableUsuarios(JTable tableUsuarios) {
		this.tableUsuarios = tableUsuarios;
	}

	public JTable getTableNoticias() {
		return tableNoticias;
	}

	public void setTableNoticias(JTable tableNoticias) {
		this.tableNoticias = tableNoticias;
	}

	public void setTableSongs(JTable tableSongs) {
		this.tableSongs = tableSongs;
	}

	public JTable getTableAlbums() {
		return tableAlbums;
	}

	public void setTableAlbums(JTable tableAlbums) {
		this.tableAlbums = tableAlbums;
	}

	public JTable getTableList() {
		return tableList;
	}

	public void setTableList(JTable tableList) {
		this.tableList = tableList;
	}

	public JTable getTablePendientes() {
		return tablePendientes;
	}

	public void setTablePendientes(JTable tablePendientes) {
		this.tablePendientes = tablePendientes;
	}

	public void setControlador(ControladorVistaRegistrado c) {
		btnBusqueda.addActionListener(c);
		btnLogOut.addActionListener(c);
		btnPremium.addActionListener(c);
		btnPlay.addActionListener(c);
		btnStop.addActionListener(c);
		btnSubir.addActionListener(c);
		btnSeguir.addActionListener(c);
		btnUnfollow.addActionListener(c);
		tpOptions.addChangeListener(c);
		btnDenunciar.addActionListener(c);
		btnEditar.addActionListener(c);
		btnBorrar.addActionListener(c);

		addWindowListener(c);
	}
}