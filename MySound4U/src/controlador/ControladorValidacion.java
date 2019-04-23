package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import modelo.Aplicacion;
import modelo.SesionAdmin;
import modelo.Validacion;
import vista.ValidacionForm;
import vista.VistaAdmin;

public class ControladorValidacion implements ActionListener {
	private ValidacionForm formulario;
	private Aplicacion api;
	private Validacion val;
	private VistaAdmin vista;

	public ControladorValidacion(ValidacionForm formulario, Aplicacion api, VistaAdmin vista, Validacion val) {
		super();
		this.formulario = formulario;
		this.api = api;
		this.val = val;
		this.vista = vista;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object component = e.getSource();

		if (component == formulario.getBtn1()) {
			System.out.println("boton validar pulsado");
			((SesionAdmin) api.getSesion()).validar(val);
			System.out.println("Cancion validada");
			if (formulario.getCheck1()) {
				val.getCancion().setExplicita();
				System.out.println(val.getCancion());
			}
			formulario.dispose();
			vista.dispose();
			VistaAdmin vistaA = new VistaAdmin();
			ControladorVistaAdmin controlA = new ControladorVistaAdmin(vistaA, api);
			vistaA.setControlador(controlA);
			controlA.start();

		} else if (component == formulario.getBtn2()) {
			System.out.println("boton invalidar pulsado");
			((SesionAdmin) api.getSesion()).invalidar(val);
			System.out.println("Cancion invalidada");
			formulario.dispose();
			vista.dispose();
			VistaAdmin vistaA = new VistaAdmin();
			ControladorVistaAdmin controlA = new ControladorVistaAdmin(vistaA, api);
			vistaA.setControlador(controlA);
			controlA.start();
		}
	}

	public void start() {
		formulario.setLocation(vista);
		formulario.setVisible(true);
	}

}