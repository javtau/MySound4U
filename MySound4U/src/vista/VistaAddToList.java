package vista;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import controlador.ControladorAddToAlbum;
import controlador.ControladorAddToList;

public class VistaAddToList extends JFrame {

	private static final long serialVersionUID = 1L;
	// Contenedor Principal
	private Container contenedor;
	private JButton btnaccept;

	private JTabbedPane tpOptions;
	// Tablas de los paneles
	private JTable tableSongs;
	private JTable tableAlbums;
	private JTable tableList;

	public VistaAddToList() {
		setTitle("Añadir  canciones");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		contenedor = new JPanel();
		contenedor.setLayout(new BoxLayout(contenedor, BoxLayout.Y_AXIS));
		setContentPane(contenedor);
		setLocationRelativeTo(null);

		setResizable(false);

		btnaccept = new JButton("Añadir");

		// Panel de canciones
		

		tpOptions = new JTabbedPane();

		// Panel de canciones
		JPanel tpTabSongs = new JPanel();
		JScrollPane scrollPane = new JScrollPane();

		tableSongs = new JTable();
		tableSongs.setModel(
				new DefaultTableModel(new Object[][] {}, new String[] { "Titulo", "Duracion", "Autor", "Album" , "Añadir" }) {

					private static final long serialVersionUID = 1L;
					Class[] columnTypes = new Class[] { String.class, Double.class, String.class, String.class, Boolean.class };

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

		scrollPane.setPreferredSize(new Dimension(780, 310));
		tpTabSongs.add(scrollPane);

		// Panel de albumes

		JPanel tpTabAlbums = new JPanel();
		JScrollPane scrollPaneAl = new JScrollPane();

		tableAlbums = new JTable();
		tableAlbums.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "Titulo", "Autor", "Canciones" , "Añadir" }) {

			private static final long serialVersionUID = 1L;
			Class[] columnTypes = new Class[] { String.class, String.class, Integer.class, Boolean.class };

			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});

		scrollPaneAl.setViewportView(tableAlbums);
		tableAlbums.getColumnModel().getColumn(0).setCellRenderer(modelocentrar);
		tableAlbums.getColumnModel().getColumn(1).setCellRenderer(modelocentrar);
		tableAlbums.getColumnModel().getColumn(2).setCellRenderer(modelocentrar);
		scrollPaneAl.setPreferredSize(new Dimension(780, 310));
		tpTabAlbums.add(scrollPaneAl);

		// Panel de Listas

		JPanel tpTabList = new JPanel();
		JScrollPane scrollPaneList = new JScrollPane();

		tableList = new JTable();
		tableList.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "Titulo", "Canciones", "Añadir"  }) {

			private static final long serialVersionUID = 1L;
			Class[] columnTypes = new Class[] { String.class, Integer.class, Boolean.class };

			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});

		scrollPaneList.setViewportView(tableList);
		tableList.getColumnModel().getColumn(0).setCellRenderer(modelocentrar);
		tableList.getColumnModel().getColumn(1).setCellRenderer(modelocentrar);
		scrollPaneList.setPreferredSize(new Dimension(780, 310));
		tpTabList.add(scrollPaneList);
		
		// Anadimos los paneles al contenedor con el metodo addTab(<titulo>,<panel>)
				tpOptions.addTab("Canciones", tpTabSongs);
				tpOptions.addTab("Albumes", tpTabAlbums);
				tpOptions.addTab("Listas", tpTabList);
		

		JPanel pl1 = new JPanel();

		pl1.setLayout(new FlowLayout(FlowLayout.CENTER));
		pl1.add(btnaccept);
		contenedor.add(Box.createRigidArea(new Dimension(0, 3)));
		contenedor.add(tpOptions);
		contenedor.add(pl1);

		setSize(800, 360);
	}

	public void setControlador(ControladorAddToList c) {
		btnaccept.addActionListener(c);
	}

	public JButton getBtnaccept() {
		return btnaccept;
	}

	public JTable getTableSongs() {
		return tableSongs;
	}
	
	public static void main(String[] args) {
		VistaAddToList vista = new VistaAddToList();
		vista.setVisible(true);
		
	}

	public Container getContenedor() {
		return contenedor;
	}

	public JTabbedPane getTpOptions() {
		return tpOptions;
	}

	public JTable getTableAlbums() {
		return tableAlbums;
	}

	public JTable getTableList() {
		return tableList;
	}
	
	
}