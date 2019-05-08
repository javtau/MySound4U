package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import modelo.Aplicacion;
import modelo.Denuncia;
import modelo.SesionAdmin;
import vista.VistaAdmin;
import vista.VistaDenunciaForm;

public class ControladorDenuncia implements ActionListener {
	private VistaDenunciaForm formulario;
	private Aplicacion api;
	private Denuncia denuncia;
	private VistaAdmin vista;

	public ControladorDenuncia(VistaDenunciaForm formulario, Aplicacion api, VistaAdmin vista, Denuncia denuncia) {
		super();
		this.formulario = formulario;
		this.api = api;
		this.denuncia = denuncia;
		this.vista = vista;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object component = e.getSource();

		if (component == formulario.getBtn1()) {
			((SesionAdmin) api.getSesion()).aceptarDenuncia(denuncia);
			formulario.dispose();
			vista.dispose();
			VistaAdmin vistaA = new VistaAdmin();
			ControladorVistaAdmin controlA = new ControladorVistaAdmin(vistaA, api);
			vistaA.setControlador(controlA);
			controlA.start();

		} else if (component == formulario.getBtn2()) {
			((SesionAdmin) api.getSesion()).rechazarDenuncia(denuncia);
			formulario.dispose();
			vista.dispose();
			VistaAdmin vistaA = new VistaAdmin();
			ControladorVistaAdmin controlA = new ControladorVistaAdmin(vistaA, api);
			vistaA.setControlador(controlA);
			controlA.start();
		}
	}

	public void start() {
		formulario.setVisible(true);
	}
}