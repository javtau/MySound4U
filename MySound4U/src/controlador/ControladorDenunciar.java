package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import modelo.Aplicacion;
import modelo.Cancion;
import modelo.Element;
import modelo.SesionUsuarios;
import vista.DenunciarForm;
import vista.VistaRegistrado;

public class ControladorDenunciar implements ActionListener {
	private DenunciarForm denunciaF;
	private SesionUsuarios sesion;
	private Cancion cancion;
	private VistaRegistrado vista;
	private Aplicacion api;

	public ControladorDenunciar(DenunciarForm denunciaF, Element cancion, SesionUsuarios sesion, VistaRegistrado vista,
			Aplicacion api) {
		super();
		this.denunciaF = denunciaF;
		this.cancion = (Cancion) cancion;
		this.sesion = sesion;
		this.vista = vista;
		this.api = api;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object component = e.getSource();

		String motivo = denunciaF.getTextArea().getText();
		if (component == denunciaF.getBtn1()) {
			sesion.denunciar(cancion, motivo);
			JOptionPane.showMessageDialog(denunciaF, "El usuario ha sido denunciado correctamente", "Denuncia",
					JOptionPane.INFORMATION_MESSAGE);
			denunciaF.dispose();
			vista.dispose();
			VistaRegistrado vistaR = new VistaRegistrado();
			ControladorVistaRegistrado controlR = new ControladorVistaRegistrado(vistaR, api);
			vistaR.setControlador(controlR);
			controlR.start();

		} else
			denunciaF.dispose();
	}

	public void start() {
		denunciaF.setLocation(vista);
		denunciaF.setVisible(true);
	}
}