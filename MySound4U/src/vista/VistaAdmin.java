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
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import controlador.ControladorVistaAdmin;

public class VistaAdmin extends JFrame {

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
	private JButton btnGestionar;
	private JButton btnAjustes;

	// Elementos del panel sur
	JPanel psouth;
	private JButton btnPlay;
	private JButton btnStop;

	// Elementos del panel central
	JPanel pCenter;
	private JTabbedPane tpOptions;

	// Tablas de los paneles
	private int selectedTable;
	private JTable tableSongs;
	private JTable tableDenuncias;
	private JTable tableValidaciones;

	public VistaAdmin() {

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
		btnGestionar = new JButton("Gestionar");
		btnAjustes = new JButton("  Ajustes  ");

		pEast.setLayout(new FlowLayout(FlowLayout.CENTER));
		pBox.setLayout(new BoxLayout(pBox, BoxLayout.Y_AXIS));

		pBox.add(Box.createRigidArea(new Dimension(0, 20)));
		pBox.add(btnLogOut);
		pBox.add(Box.createRigidArea(new Dimension(0, 7)));
		pBox.add(btnGestionar);
		pBox.add(Box.createRigidArea(new Dimension(0, 460)));
		pBox.add(btnAjustes);
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
		JTabbedPane tpOptions = new JTabbedPane();

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

		// Panel de Denuncias
		JPanel tpTabDenuncias = new JPanel();
		JScrollPane scrollPaneDen = new JScrollPane();

		tableDenuncias = new JTable();
		tableDenuncias.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "Denunciante", "Denunciado", "Cancion", "Motivo" }) {

			private static final long serialVersionUID = 1L;
			Class[] columnTypes = new Class[] { String.class, String.class, String.class, String.class };

			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		tableDenuncias.getColumnModel().getColumn(0).setCellRenderer(modelocentrar);
		tableDenuncias.getColumnModel().getColumn(1).setCellRenderer(modelocentrar);
		tableDenuncias.getColumnModel().getColumn(2).setCellRenderer(modelocentrar);
		tableDenuncias.getColumnModel().getColumn(3).setCellRenderer(modelocentrar);
		scrollPaneDen.setViewportView(tableDenuncias);

		scrollPaneDen.setPreferredSize(new Dimension(650, 542));
		tpTabDenuncias.add(scrollPaneDen);

		// Panel de validaciones
		JPanel tpTabValidaciones = new JPanel();
		JScrollPane scrollPaneVal = new JScrollPane();

		tableValidaciones = new JTable();
		tableValidaciones
				.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "Cancion", "Autor", "Plazo" }) {

					private static final long serialVersionUID = 1L;
					Class[] columnTypes = new Class[] { String.class, String.class, String.class };

					public Class getColumnClass(int columnIndex) {
						return columnTypes[columnIndex];
					}
				});

		tableValidaciones.getColumnModel().getColumn(0).setCellRenderer(modelocentrar);
		tableValidaciones.getColumnModel().getColumn(1).setCellRenderer(modelocentrar);
		tableValidaciones.getColumnModel().getColumn(2).setCellRenderer(modelocentrar);
		scrollPaneVal.setViewportView(tableValidaciones);

		scrollPaneVal.setPreferredSize(new Dimension(650, 542));
		tpTabValidaciones.add(scrollPaneVal);

		// Anadimos los paneles al contenedor con el metodo addTab(<titulo>,<panel>)
		tpOptions.addTab("Canciones", tpTabSongs);
		tpOptions.addTab("Denuncias", tpTabDenuncias);
		tpOptions.addTab("Validaciones", tpTabValidaciones);
		tpOptions.setPreferredSize(new Dimension(665, 580));
		// Podemos seleccionar una pestana del contendor con setSelectedIndex(<indice>)
		tpOptions.setSelectedIndex(0);
		// Para realizar acciones al cambiar de pestañas definiremos un ChangeListener
		tpOptions.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				selectedTable = tpOptions.getSelectedIndex();
				System.out.println(tpOptions.getSelectedIndex());
			}
		});

		// Crear panel central

		pCenter.add(tpOptions);

		// Anadir componentes al contenedor
		contenedor.add(pCenter, BorderLayout.CENTER);
	}

	public static void main(String[] args) {
		VistaAdmin vp = new VistaAdmin();
		// ControladorPrincipal Controlprincipal = new ControladorPrincipal(vp);
		// vp.setControlador(Controlprincipal);
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

	public JButton getBtnGestionar() {
		return btnGestionar;
	}

	public void setBtnGestionar(JButton btnGestionar) {
		this.btnGestionar = btnGestionar;
	}

	public JTabbedPane getTpOptions() {
		return tpOptions;
	}

	public int getTpOptionsIndex() {
		return selectedTable;
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

	public JTable gettableDenuncias() {
		return tableDenuncias;
	}

	public void settableDenuncias(JTable tableDenuncias) {
		this.tableDenuncias = tableDenuncias;
	}

	public JTable gettableValidaciones() {
		return tableValidaciones;
	}

	public void settableValidaciones(JTable tableValidaciones) {
		this.tableValidaciones = tableValidaciones;
	}

	public JButton getBtnAjustes() {
		return btnAjustes;
	}

	public void setBtnAjustes(JButton btnAjustes) {
		this.btnAjustes = btnAjustes;
	}

	public void setControlador(ControladorVistaAdmin c) {

		btnBusqueda.addActionListener(c);
		btnLogOut.addActionListener(c);
		btnGestionar.addActionListener(c);
		btnPlay.addActionListener(c);
		btnStop.addActionListener(c);

		addWindowListener(c);
	}
}