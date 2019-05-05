package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import vista.VistaPerfilForm;
import vista.VistaRegistrado;

public class ControladorPerfil implements ActionListener {
	private VistaPerfilForm perfil;
	private VistaRegistrado vista;

	public ControladorPerfil(VistaPerfilForm perfil, VistaRegistrado vista) {
		super();
		this.perfil = perfil;
		this.vista = vista;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object component = e.getSource();

		if (component == perfil.getBtn1())
			perfil.dispose();
	}

	public void start() {
		perfil.setLocation(vista);
		perfil.setVisible(true);
	}
}