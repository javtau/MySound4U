package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import modelo.Aplicacion;
import modelo.SesionUsuarios;
import vista.PremiumForm;
import vista.VistaRegistrado;

public class ControladorPremium implements ActionListener {
	private PremiumForm premium;
	private VistaRegistrado vista;
	private SesionUsuarios sesion;

	public ControladorPremium(PremiumForm premium, Aplicacion api) {
		super();
		this.premium = premium;
		sesion = (SesionUsuarios) api.getSesion();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object component = e.getSource();

		String tarjeta = premium.getTf1().getText();

		if (component == premium.getBtn1() && tarjeta.length() == 16) {
			System.out.println("boton premium pulsado");
			sesion.pasarPremium(tarjeta);
			JOptionPane.showMessageDialog(premium, "Pago realizado con exito, ahora eres un usuario premium", "Premium",
					JOptionPane.INFORMATION_MESSAGE);
			premium.dispose();
			System.out.println(sesion.getUsuarioRegistrado());
		} else {
			JOptionPane.showMessageDialog(premium, "Introduce un numero de tarjeta valido", "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	public void start() {
		premium.setLocation(vista);
		premium.setVisible(true);
	}
}