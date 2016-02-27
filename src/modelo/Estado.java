/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;
/*
nombre: hace referencia al nombre de un estado en un automta es de tipo String.
tipo de estado:hace referencia al los tipos de estados que hay --> inicial, aceptador, neutro estos de tipo String.
x y:Son las posiciones del estado en un area especifica para graficar.
*/


/**
 *
 * @author Samael
 */
public class Estado {
    
    String nombre;
    boolean esInicial;
    boolean esAceptador;
    int x;
    int y;

    public Estado() {
    }

    public Estado(String nombre, boolean esInicial, boolean esAceptador, int x, int y) {
        this.nombre = nombre;
        this.esInicial = esInicial;
        this.esAceptador = esAceptador;
        this.x = x;
        this.y = y;
    }

    

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean isEsInicial() {
        return esInicial;
    }

    public void setEsInicial(boolean esInicial) {
        this.esInicial = esInicial;
    }

    public boolean isEsAceptador() {
        return esAceptador;
    }

    public void setEsAceptador(boolean esAceptador) {
        this.esAceptador = esAceptador;
    }


    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
    
    
    
}
