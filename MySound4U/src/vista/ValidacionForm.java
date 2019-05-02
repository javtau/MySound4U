package vista;

import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controlador.ControladorValidacion;

public class ValidacionForm extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JLabel l2, l3;
	JButton btn1, btn2;
	JCheckBox check1;
	private Container contenedor;

	public ValidacionForm() {
		setTitle("Gestionar validacion");
		setResizable(false);
		contenedor = new JPanel();
		contenedor.setLayout(new BoxLayout(contenedor, BoxLayout.Y_AXIS));
		setContentPane(contenedor);

		JPanel pl1 = new JPanel();
		JPanel pl2 = new JPanel();

		pl1.setLayout(new FlowLayout(FlowLayout.CENTER));
		pl2.setLayout(new FlowLayout(FlowLayout.CENTER));

		btn1 = new JButton("  Validar  ");
		btn2 = new JButton("Invalidar");

		check1 = new JCheckBox();
		l2 = new JLabel("Marcar como explicita: ");
		l2.setForeground(Color.BLACK);
		l2.setFont(new Font("Serif", Font.PLAIN, 16));

		pl1.add(btn1);
		pl1.add(btn2);
		pl2.add(l2);
		pl2.add(check1);

		contenedor.add(pl2);
		contenedor.add(pl1);
		setSize(240, 160);
	}

	public static void main(String[] args) {
		ValidacionForm val = new ValidacionForm();
		val.setVisible(true);
	}

	public void setControlador(ControladorValidacion c) {
		btn1.addActionListener(c);
		btn2.addActionListener(c);
		check1.addActionListener(c);
	}

	public JLabel getL2() {
		return l2;
	}

	public void setL2(JLabel l2) {
		this.l2 = l2;
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

	public boolean getCheck1() {
		return check1.isSelected();
	}

	public void setCheck1(JCheckBox check1) {
		this.check1 = check1;
	}

	public void setLocation(VistaAdmin vista) {
		setLocationRelativeTo(vista);
	}
}