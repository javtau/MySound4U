package vista;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controlador.ControladorEditarCancion;

public class EditarCancionForm extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel l1;
	private JButton btn1, btn2;
	private JTextField tf1;
	private Container contenedor;

	public EditarCancionForm() {
		setTitle("Editar cancion");
		setResizable(false);
		contenedor = new JPanel();
		contenedor.setLayout(new BoxLayout(contenedor, BoxLayout.Y_AXIS));
		setContentPane(contenedor);

		JPanel pl1 = new JPanel();
		JPanel pl2 = new JPanel();

		pl1.setLayout(new FlowLayout(FlowLayout.CENTER));
		pl2.setLayout(new FlowLayout(FlowLayout.CENTER));

		l1 = new JLabel("Nuevo nombre para la cancion: ");
		tf1 = new JTextField(15);

		btn1 = new JButton("Aceptar");
		btn2 = new JButton(" Cancelar ");

		pl1.add(l1);
		pl1.add(tf1);
		pl2.add(btn1);
		pl2.add(btn2);

		contenedor.add(Box.createRigidArea(new Dimension(0, 25)));
		contenedor.add(pl1);
		contenedor.add(pl2);

		setSize(450, 150);
	}

	public static void main(String[] args) {
		EditarCancionForm edit = new EditarCancionForm();
		edit.setVisible(true);
	}

	public void setControlador(ControladorEditarCancion controlE) {
		btn1.addActionListener(controlE);
		btn2.addActionListener(controlE);
	}

	public JLabel getL1() {
		return l1;
	}

	public void setL1(JLabel l1) {
		this.l1 = l1;
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

	public JTextField getTf1() {
		return tf1;
	}

	public void setTf1(JTextField tf1) {
		this.tf1 = tf1;
	}

	public void setLocation(VistaRegistrado vista) {
		setLocationRelativeTo(vista);
	}
}