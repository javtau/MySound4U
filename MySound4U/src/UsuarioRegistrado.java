import java.util.Vector;

public class UsuarioRegistrado extends Usuario {

  private Date fechanac;

  private Boolean bloqueado;

  private Boolean premium;

  private Integer reproducciones;

    /**
   * 
   * @element-type Cancion
   */
  private Vector  canciones;
    /**
   * 
   * @element-type Album
   */
  private Vector  albumes;
    /**
   * 
   * @element-type Lista
   */
  private Vector  listas;
    /**
   * 
   * @element-type UsuarioRegistrado
   */
  private Vector  seguidos;
  
  public void añadirCancion(Cancion ) {
  }

  public void añadirALista( Object) {
  }

  public void borrarCancion( Cancion) {
  }

  public void resetearContadores() {
  }

  public void aumentarReproducciones() {
  }

  public void aumentarReproducidas() {
  }

  public void seguir( ) {
  }

  public void editarCancion( Cancion) {
  }

  public void crearLista() {
  }

  public void crearAlbum() {
  }

  public void añadiraAlbum( Cancion) {
  }

  public void borrarAlbum( Album) {
  }

}