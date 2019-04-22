package vista;

import java.awt.Checkbox;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import controlador.ControladorDenuncia;
import controlador.ControladorLogin;
import controlador.ControladorValidacion;
import controlador.ControladorVistaAnonimo;
import modelo.Denuncia;
import modelo.Validacion;

public class DenunciaForm extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JLabel l1, l2, l3;
	JButton btn1, btn2;

	public DenunciaForm() {
		l1 = new JLabel("Gestionar Denuncia");
		l1.setForeground(Color.BLACK);
		l1.setFont(new Font("Serif", Font.BOLD, 20));
		l2 = new JLabel("Motivo: ");
		l2.setForeground(Color.BLACK);
		l2.setFont(new Font("Serif", Font.PLAIN, 16));

		//l3 = new JLabel(denuncia.getComentario());

		l3 = new JLabel();
		l2.setForeground(Color.BLACK);
		l2.setFont(new Font("Serif", Font.PLAIN, 16));
		
		setResizable(false);

		btn1 = new JButton("Aceptar");
		btn2 = new JButton("Desestimar");

		l1.setBounds(100, 20, 200, 30);
		btn1.setBounds(216, 80, 100, 30);
		btn2.setBounds(66, 80, 100, 30);
		l2.setBounds(66, 110, 130, 30);
		l3.setBounds(20, 120, 360, 120);
		
		this.add(l1);
		this.add(l2);
		this.add(l3);
		this.add(btn1);
		this.add(btn2);
		this.setSize(400, 250);
		this.setLayout(null);
	}

	public static void main(String[] args) {
		DenunciaForm den = new DenunciaForm();
		den.setVisible(true);
	
	}

	public void setControlador(ControladorDenuncia controlD) {
		btn1.addActionListener(controlD);
		btn2.addActionListener(controlD);
	}

	public JLabel getL1() {
		return l1;
	}

	public void setL1(JLabel l1) {
		this.l1 = l1;
	}

	public JLabel getL3() {
		return l3;
	}

	public void setL3Text(String str) {
		l3.setText(str);
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

	public void setLocation(VistaAdmin vista) {
		setLocationRelativeTo(vista);
		
	}
	
}