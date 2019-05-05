package vista;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.time.format.DateTimeFormatter;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controlador.ControladorPerfil;
import modelo.Aplicacion;
import modelo.SesionUsuarios;

public class VistaPerfilForm extends JFrame {

	private static final long serialVersionUID = 1L;

	private JLabel l1, l2, l3, l4, l5;
	private JButton btn1;
	private Container contenedor;
	private DateTimeFormatter formatter;
	private Aplicacion api = Aplicacion.getApi();
	private SesionUsuarios sesion = (SesionUsuarios) api.getSesion();

	public VistaPerfilForm() {
		setTitle("Perfil");

		contenedor = new JPanel();
		contenedor.setLayout(new BoxLayout(contenedor, BoxLayout.Y_AXIS));
		setContentPane(contenedor);
		setLocationRelativeTo(null);

		setResizable(false);

		formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

		l1 = new JLabel("Nombre de usuario: " + sesion.getUsuarioRegistrado().getNombre());

		if (sesion.getUsuarioRegistrado().esPremium() == true)
			l2 = new JLabel("Premium: Si");
		else
			l2 = new JLabel("Premium: No");

		l3 = new JLabel("Fecha de nacimiento: " + sesion.getUsuarioRegistrado().getFechanac().format(formatter));
		l4 = new JLabel("Reproducciones: " + sesion.getUsuarioRegistrado().getReproducciones());
		l5 = new JLabel("Reproducidas: " + sesion.getUsuarioRegistrado().getReproducidas());

		btn1 = new JButton("Cerrar");

		JPanel pl1 = new JPanel();
		JPanel pl2 = new JPanel();
		JPanel pl3 = new JPanel();
		JPanel pl4 = new JPanel();
		JPanel pl5 = new JPanel();

		pl1.setLayout(new FlowLayout(FlowLayout.CENTER));
		pl2.setLayout(new FlowLayout(FlowLayout.CENTER));
		pl3.setLayout(new FlowLayout(FlowLayout.CENTER));
		pl4.setLayout(new FlowLayout(FlowLayout.CENTER));
		pl5.setLayout(new FlowLayout(FlowLayout.CENTER));

		pl1.add(l1);
		pl2.add(l2);
		pl3.add(l3);
		pl4.add(l4);
		pl4.add(Box.createHorizontalStrut(28));
		pl4.add(l5);
		pl5.add(btn1);

		contenedor.add(Box.createRigidArea(new Dimension(0, 15)));

		contenedor.add(pl1);
		contenedor.add(pl2);
		contenedor.add(pl3);
		contenedor.add(pl4);
		contenedor.add(pl5);

		setSize(360, 260);
	}

	public void setControlador(ControladorPerfil c) {
		btn1.addActionListener(c);
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