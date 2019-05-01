package vista;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import controlador.ControladorAjustes;
import modelo.Aplicacion;
import utils.FechaSimulada;

public class AjustesForm extends JFrame {

	private static final long serialVersionUID = 1L;

	private JLabel l2, l3, l4, l5;
	private JSpinner sp1, sp2, sp3;
	private JButton btn1, btn2;
	private Container contenedor;
	private LocalDate localDate;
	private DateTimeFormatter formatter;
	private JComponent mySpinnerEditor1, mySpinnerEditor2, mySpinnerEditor3;
	private JFormattedTextField jftf1, jftf2, jftf3;
	private Aplicacion api = Aplicacion.getApi();
	private String fechaHoy;

	public AjustesForm() {
		setTitle("Ajustes");

		contenedor = new JPanel();
		contenedor.setLayout(new BoxLayout(contenedor, BoxLayout.Y_AXIS));
		setContentPane(contenedor);
		setLocationRelativeTo(null);

		setResizable(false);

		l2 = new JLabel("              Umbral premium:");
		sp1 = new JSpinner();
		sp1.setValue(Integer.valueOf(api.getUmbralPremium()));
		mySpinnerEditor1 = sp1.getEditor();
		jftf1 = ((JSpinner.DefaultEditor) mySpinnerEditor1).getTextField();
		jftf1.setColumns(2);

		l3 = new JLabel("Limite de reproducciones:");
		sp2 = new JSpinner();
		sp2.setValue(Integer.valueOf(api.getLimiteReproducciones()));
		mySpinnerEditor2 = sp2.getEditor();
		jftf2 = ((JSpinner.DefaultEditor) mySpinnerEditor2).getTextField();
		jftf2.setColumns(2);

		l4 = new JLabel("                   Pasar tiempo:");
		sp3 = new JSpinner();
		sp3.setValue(Integer.valueOf(0));
		mySpinnerEditor3 = sp3.getEditor();
		jftf3 = ((JSpinner.DefaultEditor) mySpinnerEditor3).getTextField();
		jftf3.setColumns(2);

		btn1 = new JButton("Aceptar");
		btn2 = new JButton("Cancelar");

		localDate = FechaSimulada.getHoy();
		formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		fechaHoy = localDate.format(formatter);
		l5 = new JLabel("Hoy es: " + fechaHoy);
		l5.setForeground(Color.BLUE);
		l5.setFont(new java.awt.Font("Tahoma", 0, 13));

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

		pl1.add(l2);
		pl1.add(sp1);
		pl2.add(l3);
		pl2.add(sp2);
		pl3.add(l5);
		pl3.add(l4);
		pl3.add(sp3);
		pl4.add(btn1);
		pl4.add(btn2);
		pl5.add(l5);

		contenedor.add(Box.createRigidArea(new Dimension(0, 10)));

		contenedor.add(pl1);
		contenedor.add(pl2);
		contenedor.add(pl3);
		contenedor.add(pl4);
		contenedor.add(pl5);

		setSize(360, 260);
	}

	public static void main(String[] args) {
		AjustesForm aj = new AjustesForm();
		aj.setVisible(true);
	}

	public void setControlador(ControladorAjustes c) {
		sp1.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				sp1.setToolTipText(sp1.getValue().toString());
			}
		});
		sp2.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				sp2.setToolTipText(sp2.getValue().toString());
			}
		});

		sp3.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				sp3.setToolTipText(sp3.getValue().toString());
			}
		});

		btn1.addActionListener(c);
		btn2.addActionListener(c);
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

	public JButton getBtn2() {
		return btn2;
	}

	public void setBtn2(JButton btn2) {
		this.btn2 = btn2;
	}

	public void setLocation(VistaAdmin vista) {
		this.setLocationRelativeTo(vista);
	}
}