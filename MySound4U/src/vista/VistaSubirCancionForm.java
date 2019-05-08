package vista;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controlador.ControladorSubirCancion;

public class VistaSubirCancionForm extends JFrame {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private JLabel l1, l2;
	private JButton btn1, btn2, btnExplorar;
	private JTextField tf1, tf2;
	private Container contenedor;
	private ImageIcon imagen;
	private Icon icono;

	public VistaSubirCancionForm() {
		setTitle("Subir cancion");
		setResizable(false);
		contenedor = new JPanel();
		contenedor.setLayout(new BoxLayout(contenedor, BoxLayout.Y_AXIS));
		setContentPane(contenedor);

		JPanel pl1 = new JPanel();
		JPanel pl2 = new JPanel();
		JPanel pl3 = new JPanel();

		pl1.setLayout(new FlowLayout(FlowLayout.CENTER));
		pl2.setLayout(new FlowLayout(FlowLayout.CENTER));

		l1 = new JLabel("                               Archivo: ");
		tf1 = new JTextField(8);
		tf1.setEditable(false);
		l2 = new JLabel("Nombre de la cancion: ");
		tf2 = new JTextField(8);

		btnExplorar = new JButton();
		btnExplorar.setBounds(0, 0, 15, 15);
		imagen = new ImageIcon("explorador.jpg");
		icono = new ImageIcon(imagen.getImage().getScaledInstance(btnExplorar.getWidth(), btnExplorar.getHeight(),
				Image.SCALE_DEFAULT));
		btnExplorar.setIcon(icono);

		btn1 = new JButton("Subir");
		btn2 = new JButton(" Cancelar ");

		pl1.add(l1);
		pl1.add(tf1);
		pl1.add(btnExplorar);
		pl2.add(l2);
		pl2.add(tf2);
		pl3.add(btn1);
		pl3.add(btn2);

		contenedor.add(Box.createRigidArea(new Dimension(0, 25)));
		contenedor.add(pl1);
		contenedor.add(pl2);
		contenedor.add(Box.createRigidArea(new Dimension(0, 10)));
		contenedor.add(pl3);

		setSize(350, 190);
	}

	public void setControlador(ControladorSubirCancion controlS) {
		btn1.addActionListener(controlS);
		btn2.addActionListener(controlS);
		btnExplorar.addActionListener(controlS);
	}

	public JButton getBtn1() {
		return btn1;
	}

	public void setBtn1(JButton btn1) {
		this.btn1 = btn1;
	}

	public JButton getBtn2() {
		return btn2;
	}

	public void setBtn2(JButton btn2) {
		this.btn2 = btn2;
	}

	public JButton getBtnExplorar() {
		return btnExplorar;
	}

	public void setBtnExplorar(JButton btnExplorar) {
		this.btnExplorar = btnExplorar;
	}

	public JTextField getTf1() {
		return tf1;
	}

	public void setTf1(JTextField tf1) {
		this.tf1 = tf1;
	}

	public JTextField getTf2() {
		return tf2;
	}

	public void setTf2(JTextField tf2) {
		this.tf2 = tf2;
	}

	public void setLocation(VistaRegistrado vista) {
		setLocationRelativeTo(vista);
	}
}
