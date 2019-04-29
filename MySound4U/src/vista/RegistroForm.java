package vista;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.Calendar;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.toedter.calendar.JDateChooser;

import controlador.ControladorRegister;

public class RegistroForm extends JFrame {

	private static final long serialVersionUID = 1L;

	JLabel l2, l3, l4;
	JTextField tf1;
	JButton btn1;
	JPasswordField p1;
	private Container contenedor;
	JDateChooser dcFecha;

	public RegistroForm() {
		setTitle("Registro");

		contenedor = new JPanel();
		contenedor.setLayout(new BoxLayout(contenedor, BoxLayout.Y_AXIS));
		setContentPane(contenedor);
		setLocationRelativeTo(null);

		setResizable(false);

		l2 = new JLabel("                  Username:");
		tf1 = new JTextField(9);
		l3 = new JLabel("                  Password:");
		p1 = new JPasswordField(9);
		l4 = new JLabel("Fecha de nacimiento:");
		dcFecha = new JDateChooser();
		dcFecha.setCalendar(Calendar.getInstance());
		dcFecha.setDateFormatString("dd/MM/yyyy");

		btn1 = new JButton("Registrarse");

		JPanel pl1 = new JPanel();
		JPanel pl2 = new JPanel();
		JPanel pl3 = new JPanel();
		JPanel pl4 = new JPanel();

		pl1.setLayout(new FlowLayout(FlowLayout.CENTER));
		pl2.setLayout(new FlowLayout(FlowLayout.CENTER));
		pl3.setLayout(new FlowLayout(FlowLayout.CENTER));
		pl4.setLayout(new FlowLayout(FlowLayout.CENTER));

		pl1.add(l2);
		pl1.add(tf1);
		pl2.add(l3);
		pl2.add(p1);
		pl3.add(l4);
		pl3.add(dcFecha);
		pl4.add(btn1);

		contenedor.add(Box.createRigidArea(new Dimension(0, 10)));

		contenedor.add(pl1);
		contenedor.add(pl2);
		contenedor.add(pl3);
		contenedor.add(pl4);

		setSize(340, 220);
	}

	public void setControlador(ControladorRegister c) {
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

	public void setLocation(VistaAnonimo vista) {
		this.setLocationRelativeTo(vista);
	}

	public JDateChooser getDcFecha() {
		return dcFecha;
	}

	public void setDcFecha(JDateChooser dcFecha) {
		this.dcFecha = dcFecha;
	}
}