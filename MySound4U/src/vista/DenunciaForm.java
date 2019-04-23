package vista;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import controlador.ControladorDenuncia;

public class DenunciaForm extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JLabel l2;
	JTextArea textArea;
	JButton btn1, btn2;
	private Container contenedor;

	public DenunciaForm() {
		setTitle("Gestionar denuncia");
		setResizable(false);
		contenedor = new JPanel();
		contenedor.setLayout(new BoxLayout(contenedor, BoxLayout.Y_AXIS));
		setContentPane(contenedor);
		
		JPanel pl1 = new JPanel();
		JPanel pl2 = new JPanel();
		JPanel pl3 = new JPanel();

		pl1.setLayout(new FlowLayout(FlowLayout.CENTER));
		pl2.setLayout(new FlowLayout(FlowLayout.LEFT));
		pl3.setLayout(new FlowLayout(FlowLayout.CENTER));
		
		l2 = new JLabel("Motivo: ");
		l2.setForeground(Color.BLACK);
		l2.setFont(new Font("Serif", Font.PLAIN, 16));

		textArea = new JTextArea(7, 25);
		JScrollPane scrollPane = new JScrollPane();

		scrollPane.setViewportView(textArea);
		textArea.setEditable(false);
		l2.setForeground(Color.BLACK);
		l2.setFont(new Font("Serif", Font.PLAIN, 16));

		btn1 = new JButton("Aceptar");
		btn2 = new JButton("Desestimar");
		
		pl1.add(btn1);
		pl1.add(btn2);
		pl2.add(Box.createRigidArea(new Dimension(7, 0)));

		pl2.add(l2);
		pl3.add(scrollPane);
		setSize(340, 230);
		
		contenedor.add(Box.createRigidArea(new Dimension(0, 10)));		
		contenedor.add(pl2);
		contenedor.add(pl3);
		contenedor.add(pl1);
	}

	public static void main(String[] args) {
		DenunciaForm den = new DenunciaForm();
		den.setVisible(true);

	}

	public void setControlador(ControladorDenuncia controlD) {
		btn1.addActionListener(controlD);
		btn2.addActionListener(controlD);
	}

	public void setDenunciaText(String str) {
		textArea.setText(str);
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