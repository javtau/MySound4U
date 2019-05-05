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
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import controlador.ControladorAddToAlbum;

public class VistaAddToAlbum extends JFrame {

	private static final long serialVersionUID = 1L;
	// Contenedor Principal
	private Container contenedor;
	private JButton btnaccept;

	private JTable tableSongs;

	public VistaAddToAlbum() {
		setTitle("Añadir  canciones");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		contenedor = new JPanel();
		contenedor.setLayout(new BoxLayout(contenedor, BoxLayout.Y_AXIS));
		setContentPane(contenedor);
		setLocationRelativeTo(null);

		setResizable(false);

		btnaccept = new JButton("Añadir");

		// Panel de canciones
		JPanel pnlSongs = new JPanel();
		JScrollPane scrollPane = new JScrollPane();

		tableSongs = new JTable();
		tableSongs.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "Titulo", "Duracion", "Autor", "Album", "Añadir" }) {

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
		pnlSongs.add(scrollPane);

		JPanel pl1 = new JPanel();

		pl1.setLayout(new FlowLayout(FlowLayout.CENTER));
		pl1.add(btnaccept);
		contenedor.add(Box.createRigidArea(new Dimension(0, 3)));
		contenedor.add(pnlSongs);
		contenedor.add(pl1);

		setSize(800, 360);
	}

	public void setControlador(ControladorAddToAlbum c) {
		btnaccept.addActionListener(c);
	}

	public JButton getBtnaccept() {
		return btnaccept;
	}

	public JTable getTableSongs() {
		return tableSongs;
	}
}