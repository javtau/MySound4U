package modelo;

import java.io.Serializable;
import java.time.LocalDate;

/*clase noticia de notificaciones*/
public class Noticia implements Serializable {

	private static final long serialVersionUID = 1L;

	private String noticia;
	private LocalDate fecha;

	/**
	 * Este constructor genera una nueva noticia con los datos recibidos como
	 * argumentos
	 * 
	 * @param noticia cuerpo de la noticia
	 * @param fecha   de creacion de la noticia
	 */
	public Noticia(String noticia, LocalDate fecha) {
		super();
		this.noticia = noticia;
		this.fecha = fecha;
	}

	public String getNoticia() {
		return noticia;
	}

	public void setNoticia(String noticia) {
		this.noticia = noticia;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

}
