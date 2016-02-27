/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;
/*
estado A: es un estado para una transicion en donde este sera la posicion de arranque.
simbolo: es el simbolo con el cual va del estado A al estado B.
estado B:es un estado para una transicion en donde este sera la posicion final de la transicion.

*/


/**
 *
 * @author Samael
 */
public class Transicion {
    
    Estado estadoA;
    String simbolo;
    Estado estadoB;

    public Transicion() {
    }

    public Transicion(Estado estadoA, String simbolo, Estado estadoB) {
        this.estadoA = estadoA;
        this.simbolo = simbolo;
        this.estadoB = estadoB;
    }

    public Estado getEstadoA() {
        return estadoA;
    }

    public void setEstadoA(Estado estadoA) {
        this.estadoA = estadoA;
    }

    public String getSimbolo() {
        return simbolo;
    }

    public void setSimbolo(String simbolo) {
        this.simbolo = simbolo;
    }

    public Estado getEstadoB() {
        return estadoB;
    }

    public void setEstadoB(Estado estadoB) {
        this.estadoB = estadoB;
    }
    
            
}
