package vista;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import controlador.ControladorLogin;
import controlador.ControladorVistaAnonimo;

public class LoginForm extends JFrame {
	JLabel l1, l2, l3;
	JTextField tf1;
	JButton btn1;
	JPasswordField p1;

	public LoginForm() {
		JFrame frame = new JFrame("Login");
		l1 = new JLabel("Login");
		l1.setForeground(Color.BLACK);
		l1.setFont(new Font("Serif", Font.BOLD, 20));

		setResizable(false);

		l2 = new JLabel("   Usuario");
		l3 = new JLabel("Contraseña");
		tf1 = new JTextField();
		p1 = new JPasswordField();
		btn1 = new JButton("Login");

		l1.setBounds(130, 30, 400, 30);
		l2.setBounds(80, 70, 200, 30);
		l3.setBounds(80, 110, 200, 30);
		tf1.setBounds(150, 70, 200, 30);
		p1.setBounds(150, 110, 200, 30);
		btn1.setBounds(150, 160, 100, 30);

		frame.add(l1);
		frame.add(l2);
		frame.add(tf1);
		frame.add(l3);
		frame.add(p1);
		frame.add(btn1);

		frame.setSize(400, 250);
		frame.setLayout(null);
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		new LoginForm();
	}

	public void setControlador(ControladorLogin c) {
		tf1.addActionListener(c);
		p1.addActionListener(c);
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

	/**
	 * @return the p1
	 */
	public JPasswordField getP1() {
		return p1;
	}

	/**
	 * @param p1 the p1 to set
	 */
	public void setP1(JPasswordField p1) {
		this.p1 = p1;
	}

}