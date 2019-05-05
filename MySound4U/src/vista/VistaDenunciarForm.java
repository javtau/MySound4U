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
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import controlador.ControladorDenunciar;

public class VistaDenunciarForm extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel l1;
	private JButton btn1, btn2;
	private JTextArea textArea;
	private Container contenedor;

	public VistaDenunciarForm() {
		setTitle("Denunciar");
		setResizable(false);
		contenedor = new JPanel();
		contenedor.setLayout(new BoxLayout(contenedor, BoxLayout.Y_AXIS));
		setContentPane(contenedor);

		JPanel pl1 = new JPanel();
		JPanel pl2 = new JPanel();

		pl1.setLayout(new FlowLayout(FlowLayout.LEFT));
		pl2.setLayout(new FlowLayout(FlowLayout.CENTER));

		l1 = new JLabel("Motivo: ");
		textArea = new JTextArea(5, 25);
		JScrollPane scrollPane = new JScrollPane();

		btn1 = new JButton("Denunciar");
		btn2 = new JButton(" Cancelar ");

		scrollPane.setViewportView(textArea);
		textArea.setEditable(true);

		pl1.add(l1);
		pl1.add(Box.createRigidArea(new Dimension(7, 0)));
		pl1.add(textArea);
		pl2.add(btn1);
		pl2.add(btn2);

		contenedor.add(pl1);
		contenedor.add(pl2);

		setSize(310, 180);
	}

	public void setControlador(ControladorDenunciar c) {
		btn1.addActionListener(c);
		btn2.addActionListener(c);
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

	public JTextArea getTextArea() {
		return textArea;
	}

	public void setTextArea(JTextArea textArea) {
		this.textArea = textArea;
	}

	public void setLocation(VistaRegistrado vista) {
		setLocationRelativeTo(vista);
	}
}