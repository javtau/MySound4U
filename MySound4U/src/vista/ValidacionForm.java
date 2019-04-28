package vista;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;

import controlador.ControladorValidacion;

public class ValidacionForm extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JLabel l1, l2, l3;
	JButton btn1, btn2;
	JCheckBox check1;

	public ValidacionForm() {
		l1 = new JLabel("Gestionar Validacion");
		l1.setForeground(Color.BLACK);
		l1.setFont(new Font("Serif", Font.BOLD, 20));
		l2 = new JLabel("Marcar como explicita: ");
		l2.setForeground(Color.BLACK);
		l2.setFont(new Font("Serif", Font.PLAIN, 16));

		setResizable(false);

		btn1 = new JButton("Validar");
		btn2 = new JButton("Invalidar");
		check1 = new JCheckBox();

		l1.setBounds(100, 20, 200, 30);
		btn1.setBounds(216, 100, 100, 30);
		btn2.setBounds(66, 100, 100, 30);
		l2.setBounds(66, 160, 160, 30);
		check1.setBounds(240, 162, 160, 30);

		this.add(check1);
		this.add(l1);
		this.add(l2);
		this.add(btn1);
		this.add(btn2);
		this.add(check1);
		this.setSize(400, 250);
		this.setLayout(null);
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

	public JLabel getL1() {
		return l1;
	}

	public void setL1(JLabel l1) {
		this.l1 = l1;
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