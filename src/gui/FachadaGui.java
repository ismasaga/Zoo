package gui;

/**
 * Created by miguel on 31/03/17.
 */
public class FachadaGui {
    aplicacion.FachadaAplicacion fa;
    
    public FachadaGui(aplicacion.FachadaAplicacion fa){
        this.fa=fa;
    }

    public void muestraExcepcion(String txtExcepcion){
        System.out.println(txtExcepcion);
    }
}
