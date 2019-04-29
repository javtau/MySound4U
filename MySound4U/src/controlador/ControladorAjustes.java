package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import modelo.Aplicacion;
import vista.AjustesForm;
import vista.VistaAdmin;

public class ControladorAjustes implements ActionListener {
	private AjustesForm ajustes;
	private Aplicacion api;
	private VistaAdmin vista;

	public ControladorAjustes(AjustesForm ajustes, Aplicacion api) {
		super();
		this.ajustes = ajustes;
		this.api = api;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object component = e.getSource();

		int umbral = (Integer) ajustes.getSp1().getValue();
		int limite = (Integer) ajustes.getSp2().getValue();
		int tiempo = (Integer) ajustes.getSp3().getValue();

		if (component == ajustes.getBtn1()) {
			api.setUmbralPremium(umbral);
			api.setLimiteReproducciones(limite);
			api.avanzarSimulada(tiempo);
			JOptionPane.showMessageDialog(ajustes, "Los valores han sido guardados correctamente", "Ajustes",
					JOptionPane.INFORMATION_MESSAGE);
			ajustes.dispose();
		}
	}

	public void start() {
		ajustes.setLocation(vista);
		ajustes.setVisible(true);
	}
}