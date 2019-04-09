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

public class VistaPrincipal {

	public static void main(String[] args) {

		String[] searchTipes = { "Todo", "Cancion", "Autor", "album" };
		JFrame ventana = new JFrame("principal.prueba1");
		// obtnener contenedor, asignar layout
		Container contenedor = ventana.getContentPane();
		contenedor.setLayout(new BorderLayout());

		// elementos del panel norte

		JTextField tfBusqueda = new RoundJTextField(40);
		JComboBox<String> comboBusqueda = new JComboBox<>(searchTipes);
		JButton btnBusqueda = new JButton("Buscar");

		// elementos del panel este
		JButton btnLogIn = new JButton("  Loguearse ");
		JButton btnSingUp = new JButton(" Registrarse");
		
		JButton btnSubir = new JButton    ("    Subir     ");
		JButton btnDenunciar = new JButton("Denunciar");

		// elementos del panel sur

		JButton btnPlay = new JButton("Play");
		JButton btnStop = new JButton("Stop");

		// elementos del panel central
		// Creamos un panel por cada pestaña
		final JPanel tpTab1 = new JPanel();
		tpTab1.setLayout(new FlowLayout());
		tpTab1.add(new JLabel("Nombre"));
		tpTab1.add(new JTextField(10));
		tpTab1.add(new JButton("Aceptar"));
		// Otros dos paneles
		JPanel tpTabSongs = new JPanel();
		JPanel tpTabAlbums = new JPanel();
		JTable tableSongs;

		// panel productos///

		

		JScrollPane scrollPane = new JScrollPane();
		tpTabSongs.add(scrollPane);

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
		tableSongs.getColumnModel().getColumn(0).setCellRenderer(modelocentrar);
		tableSongs.getColumnModel().getColumn(3).setCellRenderer(modelocentrar);
		scrollPane.setViewportView(tableSongs);

		scrollPane.setPreferredSize(new Dimension(650, 580));
		tpTabSongs.add(scrollPane);
			
		
		JTabbedPane tpOptions = new JTabbedPane();
		// Añadimos los paneles al contenedor con el método addTab(<título>,<panel>)
		tpOptions.addTab("Canciones", tpTabSongs);
		tpOptions.addTab("Albumes", tpTabAlbums);
		tpOptions.addTab("Listas", tpTab1);
		tpOptions.setPreferredSize(new Dimension(665, 617));
		// Podemos seleccionar una pestaña del contendor con setSelectedIndex(<indice>)
		tpOptions.setSelectedIndex(0);
		// Para realizar acciones al cambiar de pestañas definiremos un ChangeListener
		tpOptions.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				// TODO Auto-generated method stub
				System.out.println(tpOptions.getSize());
			}
		});

		// crear panel norte
		final JPanel pNorth = new JPanel();
		pNorth.setLayout(new FlowLayout(FlowLayout.LEFT));
		pNorth.add(tfBusqueda);
		pNorth.add(comboBusqueda);
		pNorth.add(btnBusqueda);

		pNorth.setVisible(true);

		// crear panel central
		final JPanel pCenter = new JPanel();
		pCenter.add(tpOptions);

		// crear panel este
		final JPanel pEast = new JPanel();
		final JPanel pBox = new JPanel();
		pEast.setLayout(new FlowLayout(FlowLayout.CENTER));

		pBox.setLayout(new BoxLayout(pBox, BoxLayout.Y_AXIS));
		pBox.add(Box.createRigidArea(new Dimension(0, 20)));
		pBox.add(btnLogIn);
		pBox.add(Box.createRigidArea(new Dimension(0, 7)));
		pBox.add(btnSingUp);
		pBox.add(Box.createRigidArea(new Dimension(0, 480)));
		pBox.add(btnSubir);
		pBox.add(Box.createRigidArea(new Dimension(0, 7)));
		pBox.add(btnDenunciar);
		pEast.add(pBox);

		// crear panel sur
		final JPanel psouth = new JPanel();
		final JPanel psouthL = new JPanel();
		final JPanel psouthR = new JPanel();
		psouthL.setLayout(new FlowLayout());
		psouthL.add(btnPlay);
		psouthL.add(btnStop);
		psouthR.add(Box.createRigidArea(new Dimension(110, 0)));
		psouth.add(psouthL);
		psouth.add(psouthR);

		// a�adir componentes al contenedor
		contenedor.add(pNorth, BorderLayout.NORTH);
		contenedor.add(pCenter, BorderLayout.CENTER);
		contenedor.add(pEast, BorderLayout.EAST);
		contenedor.add(psouth, BorderLayout.SOUTH);

		// mostrar ventana
		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ventana.setSize(810, 700);
		ventana.setResizable(false);
		ventana.setVisible(true);

	}

}