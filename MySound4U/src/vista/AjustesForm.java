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
import javax.swing.JSpinner;
import javax.swing.JTextField;

import controlador.ControladorAjustes;
import modelo.Aplicacion;

public class AjustesForm extends JFrame {

	private static final long serialVersionUID = 1L;

	JLabel l2, l3, l4;
	private JSpinner sp1, sp2, sp3;
	JButton btn1;
	private Container contenedor;

	Aplicacion api;
	public AjustesForm() {
		setTitle("Registro");

		contenedor = new JPanel();
		contenedor.setLayout(new BoxLayout(contenedor, BoxLayout.Y_AXIS));
		setContentPane(contenedor);
		setLocationRelativeTo(null);

		setResizable(false);
 
		l2 = new JLabel("              Umbral premium:");
		sp1 = new JSpinner();
		sp1.setValue(api.getUmbralPremium());
		l3 = new JLabel("Limite de reproducciones:");
		sp2 = new JSpinner();
		sp2.setValue(api.getUmbralPremium());
		l4 = new JLabel("                   Pasar tiempo:");
		sp3 = new JSpinner();
		sp3.setValue(0);
		btn1 = new JButton("Aceptar");

		JPanel pl1 = new JPanel();
		JPanel pl2 = new JPanel();
		JPanel pl3 = new JPanel();
		JPanel pl4 = new JPanel();

		pl1.setLayout(new FlowLayout(FlowLayout.CENTER));
		pl2.setLayout(new FlowLayout(FlowLayout.CENTER));
		pl3.setLayout(new FlowLayout(FlowLayout.CENTER));
		pl4.setLayout(new FlowLayout(FlowLayout.CENTER));

		pl1.add(l2);
		pl1.add(sp1);
		pl2.add(l3);
		pl2.add(sp2);
		pl3.add(l4);
		pl3.add(sp3);
		pl4.add(btn1);

		contenedor.add(Box.createRigidArea(new Dimension(0, 10)));

		contenedor.add(pl1);
		contenedor.add(pl2);
		contenedor.add(pl3);
		contenedor.add(pl4);

		setSize(340, 220);
	}

	public void setControlador(ControladorAjustes c) {
		sp1.addChangeListener(c);
		sp2.addActionListener(c);
		sp3.addActionListener(c);
		btn1.addActionListener(c);
	}

	public static void main(String[] args) {
		AjustesForm ajustes = new AjustesForm();
		// ControladorPrincipal Controlprincipal = new ControladorPrincipal(vp);
		// vp.setControlador(Controlprincipal);
		ajustes.setVisible(true);
	}
	
	public JSpinner getSp1() {
		return sp1;
	}

	public void setSp1(JSpinner sp1) {
		this.sp1 = sp1;
	}

	public JSpinner getSp2() {
		return sp2;
	}

	public void setSp2(JSpinner sp2) {
		this.sp2 = sp2;
	}

	public JSpinner getSp3() {
		return sp3;
	}

	public void setSp3(JSpinner sp3) {
		this.sp3 = sp3;
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

	public void setLocation(VistaAnonimo vista) {
		this.setLocationRelativeTo(vista);
	}
}