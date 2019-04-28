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
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import controlador.ControladorVistaAnonimo;

public class VistaAnonimo extends JFrame {

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
	private JButton btnLogIn;
	private JButton btnSignUp;

	// Elementos del panel sur
	JPanel psouth;
	private JButton btnPlay;
	private JButton btnStop;

	// Elementos del panel central
	JPanel pCenter;
	// Tablas de los paneles
	private JTable tableSongs;

	public VistaAnonimo() {

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
		btnLogIn = new JButton("  Loguearse  ");
		btnSignUp = new JButton(" Registrarse");

		pEast.setLayout(new FlowLayout(FlowLayout.CENTER));
		pBox.setLayout(new BoxLayout(pBox, BoxLayout.Y_AXIS));

		pBox.add(btnLogIn);
		pBox.add(Box.createRigidArea(new Dimension(0, 7)));
		pBox.add(btnSignUp);
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

		// Panel de canciones
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

		pCenter.add(scrollPane);

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

	public JButton getBtnLogIn() {
		return btnLogIn;
	}

	public void setBtnLogIn(JButton btnLogIn) {
		this.btnLogIn = btnLogIn;
	}

	public JButton getBtnSignUp() {
		return btnSignUp;
	}

	public void setBtnSignUp(JButton btnSignUp) {
		this.btnSignUp = btnSignUp;
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

	public JTable getTableSongs() {
		return tableSongs;
	}

	public void setTableSongs(JTable tableSongs) {
		this.tableSongs = tableSongs;
	}

	public void setControlador(ControladorVistaAnonimo c) {

		btnBusqueda.addActionListener(c);
		btnLogIn.addActionListener(c);
		btnSignUp.addActionListener(c);
		btnPlay.addActionListener(c);
		btnStop.addActionListener(c);

		addWindowListener(c);
	}
}