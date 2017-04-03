/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package baseDatos;

public abstract class DAOAbstracto {

    private aplicacion.FachadaAplicacion fa;
    private java.sql.Connection conexion;

    protected java.sql.Connection getConexion() {
        return this.conexion;
    }

    protected void setConexion(java.sql.Connection conexion) {
        this.conexion = conexion;
    }

    protected aplicacion.FachadaAplicacion getFachadaAplicacion() {
        return fa;
    }

    protected void setFachadaAplicacion(aplicacion.FachadaAplicacion fa) {
        this.fa = fa;
    }

}
