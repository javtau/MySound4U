/**
* Enumeracion tipo
* @author Fernando Barroso, Javier Lozano y Gonzalo Madrigal
*/

package modelo;

import java.io.Serializable;

/**
 * Esta enumeracion define los tipos de busqueda que se pueden realizar
 */
public enum TIPO_BUSQUEDA implements Serializable {
	TODO, AUTOR, TITULO, ALBUM;
}