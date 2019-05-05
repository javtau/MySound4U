package vista;

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
	JLabel l2, l3, lbdenunciante, l5, lbcancion, l7, lbautor;
	JTextArea textArea;
	JButton btn1, btn2;
	private Container contenedor;
	private static final int LEFTSPACE = 5;

	public DenunciaForm() {
		setTitle("Gestionar denuncia");
		setResizable(false);
		setLocationRelativeTo(null);
		contenedor = new JPanel();
		contenedor.setLayout(new BoxLayout(contenedor, BoxLayout.Y_AXIS));
		setContentPane(contenedor);

		JPanel pl1 = new JPanel();
		JPanel pl2 = new JPanel();
		JPanel pl3 = new JPanel();
		JPanel pl4 = new JPanel();
		JPanel pl5 = new JPanel();

		pl1.setLayout(new FlowLayout(FlowLayout.CENTER));
		pl2.setLayout(new FlowLayout(FlowLayout.LEFT));
		pl3.setLayout(new FlowLayout(FlowLayout.CENTER));
		pl4.setLayout(new FlowLayout(FlowLayout.LEFT));
		pl5.setLayout(new FlowLayout(FlowLayout.LEFT));

		l3 = new JLabel("Denunciante: ");

		lbdenunciante = new JLabel("");
		lbdenunciante.setFont(new Font("Serif", Font.BOLD, 15));

		l5 = new JLabel("Cancion: ");
		lbcancion = new JLabel("");

		l7 = new JLabel("             Autor: ");
		lbautor = new JLabel("");

		l2 = new JLabel("Motivo: ");

		textArea = new JTextArea(LEFTSPACE, 25);
		JScrollPane scrollPane = new JScrollPane();

		scrollPane.setViewportView(textArea);
		textArea.setEditable(false);

		btn1 = new JButton("Aceptar");
		btn2 = new JButton("Desestimar");
		pl4.add(Box.createRigidArea(new Dimension(LEFTSPACE, 0)));
		pl4.add(l3);
		pl4.add(lbdenunciante);
		pl5.add(Box.createRigidArea(new Dimension(LEFTSPACE, 0)));
		pl5.add(l5);
		pl5.add(lbcancion);
		pl5.add(l7);
		pl5.add(lbautor);
		pl1.add(btn1);
		pl1.add(btn2);
		pl2.add(Box.createRigidArea(new Dimension(7, 0)));

		pl2.add(l2);
		pl3.add(scrollPane);
		setSize(360, 280);

		contenedor.add(Box.createRigidArea(new Dimension(0, 10)));
		contenedor.add(pl4);
		contenedor.add(pl5);
		contenedor.add(pl2);
		contenedor.add(pl3);
		contenedor.add(pl1);
	}

	public void setControlador(ControladorDenuncia controlD) {
		btn1.addActionListener(controlD);
		btn2.addActionListener(controlD);
	}

	public void setLbdenunciante(JLabel lbdenunciante) {
		this.lbdenunciante = lbdenunciante;
	}

	public void setLbcancion(JLabel lbcancion) {
		this.lbcancion = lbcancion;
	}

	public void setLbautor(JLabel lbautor) {
		this.lbautor = lbautor;
	}

	public void setTextArea(JTextArea textArea) {
		this.textArea = textArea;
	}

	public JLabel getLbdenunciante() {
		return lbdenunciante;
	}

	public JLabel getLbcancion() {
		return lbcancion;
	}

	public JLabel getLbautor() {
		return lbautor;
	}

	public JTextArea getTextArea() {
		return textArea;
	}

	public JButton getBtn1() {
		return btn1;
	}

	public JButton getBtn2() {
		return btn2;
	}
}