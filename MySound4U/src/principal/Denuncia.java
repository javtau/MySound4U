/**
* Clase Denuncia
* @author Fernando Barroso, Javier Lozano y Gonzalo Madrigal
*/

package principal;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Esta clase contiene todos los atributos y metodos de una denuncia
 */
public class Denuncia implements Serializable {

	/** Cancion que se quiere denunciar */
	private Cancion cancion;

	/** Usuario que realiza la denuncia */
	private UsuarioRegistrado denunciante;

	/** Comentario de la denuncia */
	private String comentario;

	/**
	 * Este constructor genera una nueva denuncia con los datos recibidos como
	 * argumentos
	 * 
	 * @param cancion
	 * @param denunciante
	 * @param comentario
	 */
	public Denuncia(Cancion cancion, UsuarioRegistrado denunciante, String comentario) {
		this.cancion = cancion;
		this.denunciante = denunciante;
		this.comentario = comentario;
	}

	/**
	 * Este metodo devuelve la cancion que se quiere denunciar
	 * 
	 * @return cancion
	 */
	public Cancion getCancion() {
		return cancion;
	}

	/**
	 * Este metodo devuelve el usuario que realizo la denuncia
	 * 
	 * @return denunciante
	 */
	public UsuarioRegistrado getDenunciante() {
		return denunciante;
	}

	/**
	 * Este metodo devuelve el comentario de la denuncia
	 * 
	 * @return comentario
	 */
	public String getComentario() {
		return comentario;
	}
}