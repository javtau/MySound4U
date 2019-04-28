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

import controlador.ControladorPremium;

public class PremiumForm extends JFrame {

	private static final long serialVersionUID = 1L;

	JLabel l1;
	JTextField tf1;
	JButton btn1;
	private Container contenedor;

	public PremiumForm() {
		setTitle("Registro");

		contenedor = new JPanel();
		contenedor.setLayout(new BoxLayout(contenedor, BoxLayout.Y_AXIS));
		setContentPane(contenedor);
		setLocationRelativeTo(null);

		setResizable(false);

		l1 = new JLabel("Numero de tarjeta:");
		tf1 = new JTextField(16);
		btn1 = new JButton("Pagar");

		JPanel pl1 = new JPanel();
		JPanel pl2 = new JPanel();

		pl1.setLayout(new FlowLayout(FlowLayout.CENTER));
		pl2.setLayout(new FlowLayout(FlowLayout.CENTER));

		pl1.add(l1);
		pl1.add(tf1);
		pl2.add(btn1);

		contenedor.add(Box.createRigidArea(new Dimension(0, 10)));

		contenedor.add(pl1);
		contenedor.add(pl2);

		setSize(340, 140);
	}

	public void setControlador(ControladorPremium c) {
		tf1.addActionListener(c);
		btn1.addActionListener(c);
	}

	/**
	 * @return the tf1
	 */
	public JTextField getTf1() {
		return tf1;
	}

	/**
	 * @param tf1 the tf1 to set
	 */
	public void setTf1(JTextField tf1) {
		this.tf1 = tf1;
	}

	/**
	 * @return the btn1
	 */
	public JButton getBtn1() {
		return btn1;
	}

	/**
	 * @param btn1 the btn1 to set
	 */
	public void setBtn1(JButton btn1) {
		this.btn1 = btn1;
	}

	public void setLocation(VistaRegistrado vista) {
		this.setLocationRelativeTo(vista);
	}
}