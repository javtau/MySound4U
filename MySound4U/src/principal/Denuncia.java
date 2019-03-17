/**
* Clase Denuncia
* @author Gonzalo Madrigal, Fernando Barroso y Javier Lozano
*
*/

package principal;

import java.util.ArrayList;

/**
 * Esta clase contiene todos los atributos y metodos de uana denuncia
 */
public class Denuncia {

	/** Cancion que se quiere denunciar */
	private Cancion cancion;

	/** Usuario que realiza la denuncia */
	private UsuarioRegistrado denunciante;

	/** Comentario de la denuncia */
	private String comentario;

	/**
	 * Este constructor genera una nueva denuncia con los datos recibidos como
	 * argumentos.
	 * 
	 * @param cancion
	 * @param denunciante
	 * @param comentario
	 */
	public Denuncia(Cancion cancion, UsuarioRegistrado denunciante, String comentario) {
		super();
		this.cancion = cancion;
		this.denunciante = denunciante;
		this.comentario = comentario;
	}

	/**
	 * Este método devuelve la cancio que se quiere denunciar
	 * 
	 * @return cancion
	 */
	public Cancion getCancion() {
		return cancion;
	}

	/**
	 * Este método devuelve el usuario que realizo la dennuncia
	 * 
	 * @return denunciante
	 */
	public UsuarioRegistrado getDenunciante() {
		return denunciante;
	}

	/**
	 * Este método devuelve el comentario de la denuncia
	 * 
	 * @return comentario
	 */
	public String getComentario() {
		return comentario;
	}
	
	

}