import java.util.Vector;

public abstract class Sesion {

    public Vector  myCancion;
    public Vector  myUsuario;
    private GestionPagos gestorPagos;
    private Reproductor reproductor;
    private Buscador buscador;
    public Vector  myAplicacion;

  public abstract void reproducir( Cancion,  Usuario);

}