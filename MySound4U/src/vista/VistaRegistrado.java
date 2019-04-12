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
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import controlador.ControladorVistaRegistrado;

public class VistaRegistrado extends JFrame {

	private static long serialVersionUID = 1L;

	String[] searchTipes = { "Todo", "titulo", "Autor", "album" };

	// contenedor Principal,
	private Container contenedor;

	// elementos del panel norte
	JPanel pNorth;
	private JTextField tfBusqueda;
	private JComboBox<String> comboBusqueda;
	private JButton btnBusqueda;

	// elementos del panel este
	JPanel pEast;
	private JButton btnLogOut;
	private JButton btnPremium;
	private JButton btnSubir;
	private JButton btnDenunciar;

	// elementos del panel sur
	JPanel psouth;
	private JButton btnPlay;
	private JButton btnStop;

	// elementos del panel central
	JPanel pCenter;
	private JTabbedPane tpOptions;

	// tablas de los paneles
	private JTable tableSongs;
	private JTable tableAlbums;
	private JTable tableList;
	private JTable tablePendientes;

	public VistaRegistrado() {

		// Configuracion ventana
		setTitle("MySound4U");
		setSize(810, 700);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		contenedor = new JPanel();
		contenedor.setLayout(new BorderLayout());
		setContentPane(contenedor);

		// creamos panel norte y sus elementos

		pNorth = new JPanel();
		tfBusqueda = new RoundJTextField(40);
		comboBusqueda = new JComboBox<>(searchTipes);
		btnBusqueda = new JButton("Buscar");

		pNorth.setLayout(new FlowLayout(FlowLayout.LEFT));

		pNorth.add(tfBusqueda);
		pNorth.add(comboBusqueda);
		pNorth.add(btnBusqueda);
		// pNorth.setVisible(true);
		contenedor.add(pNorth, BorderLayout.NORTH);

		// creamos panel este y sus elementos

		pEast = new JPanel();
		JPanel pBox = new JPanel();
		btnLogOut = new JButton (" Logout  ");
		btnPremium = new JButton(" Premium");
		btnSubir = new JButton("    Subir     ");
		btnDenunciar = new JButton("Denunciar");

		pEast.setLayout(new FlowLayout(FlowLayout.CENTER));
		pBox.setLayout(new BoxLayout(pBox, BoxLayout.Y_AXIS));

		pBox.add(Box.createRigidArea(new Dimension(0, 20)));
		pBox.add(btnLogOut);
		pBox.add(Box.createRigidArea(new Dimension(0, 7)));
		pBox.add(btnPremium);
		pBox.add(Box.createRigidArea(new Dimension(0, 440)));
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

		// Creamos panel central y sus elementos
		pCenter = new JPanel();
		JTabbedPane tpOptions = new JTabbedPane();

		// panel de canciones
		JPanel tpTabSongs = new JPanel();
		JScrollPane scrollPane = new JScrollPane();

		tableSongs = new JTable();
		tableSongs.setModel(
				new DefaultTableModel(new Object[][] {}, new String[] { "Titulo", "Duracion", "autor", "album" }) {

					private static final long serialVersionUID = 1L;
					Class[] columnTypes = new Class[] { String.class, Double.class, String.class, String.class };

					public Class getColumnClass(int columnIndex) {
						return columnTypes[columnIndex];
					}
				});
		DefaultTableCellRenderer modelocentrar = new DefaultTableCellRenderer();
		modelocentrar.setHorizontalAlignment(SwingConstants.CENTER);
		// tableSongs.getColumnModel().getColumn(0).setCellRenderer(modelocentrar);
		// tableSongs.getColumnModel().getColumn(3).setCellRenderer(modelocentrar);
		scrollPane.setViewportView(tableSongs);

		scrollPane.setPreferredSize(new Dimension(650, 542));
		tpTabSongs.add(scrollPane);

		// panel de albumes

		JPanel tpTabAlbums = new JPanel();
		JScrollPane scrollPaneAl = new JScrollPane();

		tableAlbums = new JTable();
		tableAlbums.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "Titulo", "Canciones" }) {

			private static final long serialVersionUID = 1L;
			Class[] columnTypes = new Class[] { String.class, Double.class, String.class, String.class };

			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});

		tableAlbums.getColumnModel().getColumn(1).setCellRenderer(modelocentrar);
		scrollPaneAl.setViewportView(tableAlbums);

		scrollPaneAl.setPreferredSize(new Dimension(650, 542));
		tpTabAlbums.add(scrollPaneAl);

		// panel de Listas

		JPanel tpTabList = new JPanel();
		JScrollPane scrollPaneList = new JScrollPane();

		tableList = new JTable();
		tableList.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "Titulo", "Canciones" }) {

			private static final long serialVersionUID = 1L;
			Class[] columnTypes = new Class[] { String.class, Double.class, String.class, String.class };

			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});

		tableList.getColumnModel().getColumn(1).setCellRenderer(modelocentrar);
		scrollPaneList.setViewportView(tableAlbums);

		scrollPaneList.setPreferredSize(new Dimension(650, 542));
		tpTabList.add(scrollPaneList);

		// panel de pendientes

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

		tablePendientes.getColumnModel().getColumn(1).setCellRenderer(modelocentrar);
		scrollPanePend.setViewportView(tablePendientes);

		scrollPanePend.setPreferredSize(new Dimension(650, 542));
		tpTabPendientes.add(scrollPanePend);

		// Añadimos los paneles al contenedor con el método addTab(<título>,<panel>)
		tpOptions.addTab("Canciones", tpTabSongs);
		tpOptions.addTab("Albumes", tpTabAlbums);
		tpOptions.addTab("Listas", tpTabList);
		tpOptions.addTab("Pendientes", tpTabPendientes);
		tpOptions.setPreferredSize(new Dimension(665, 580));
		// Podemos seleccionar una pestaña del contendor con setSelectedIndex(<indice>)
		tpOptions.setSelectedIndex(0);
		// Para realizar acciones al cambiar de pestañas definiremos un ChangeListener
		tpOptions.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				// TODO Auto-generated method stub
				System.out.println(tpOptions.getSelectedIndex());
			}
		});

		// crear panel central

		pCenter.add(tpOptions);

		// a�adir componentes al contenedor
		contenedor.add(pCenter, BorderLayout.CENTER);

	}

	public static void main(String[] args) {
		VistaRegistrado vp = new VistaRegistrado();
		//ControladorPrincipal Controlprincipal = new ControladorPrincipal(vp);
		//vp.setControlador(Controlprincipal);
		vp.setVisible(true);
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

	public void setTpOptions(JTabbedPane tpOptions) {
		this.tpOptions = tpOptions;
	}

	public JTable getTableSongs() {
		return tableSongs;
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
		
	}

}
