package gui;

public class FachadaGui {
    private aplicacion.FachadaAplicacion fa;

    public FachadaGui(aplicacion.FachadaAplicacion fa) {
        this.fa = fa;
    }

    public void muestraExcepcion(String txtExcepcion) {
        System.out.println(txtExcepcion);
    }
}
