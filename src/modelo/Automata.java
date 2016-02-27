/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.LinkedList;
/*Clase del automata donde estan todos los atributos 
nombre: se refiere al nombre del automata
estados: se refiere a los estados de un automata esta es una lista de la clase  u objeto construida en modelos/Estados
lenguaje: se refiere a la lista de  simbolos de un automata esta en tipo String.
transicion: se refiere a las transiciones de un automata es una lista de la clase u  objeto construida en modelos/Transicion
estado inicial:se refiere al estado inicial del automata esta de la clase  u objeto construida en modelos/Estados
estado aceptador: se refiere una lista de estados que son aceptadores en el  automata esta de la clase  u objeto construida en modelos/Estado.
*/

/**
 *
 * @author Samael
 */
public class Automata {
 
    String nombre;
    String tipoAutomata;
    LinkedList<Estado> estados;
    LinkedList<String> lenguaje;
    LinkedList<Transicion> transiciones;
    Estado estadoInicial;
    LinkedList<Estado> estadoAceptador;

    public Automata() 
    {
    }

    public Automata(String nombre, String tipoAutomata, LinkedList<Estado> estados, LinkedList<String> lenguaje, Estado estadoInicial, LinkedList<Estado> estadoAceptador) 
    {
        this.nombre = nombre;
        this.tipoAutomata = tipoAutomata;
        this.estados = estados;
        this.lenguaje = lenguaje;
        this.transiciones = new LinkedList<>();
        this.estadoInicial = estadoInicial;
        this.estadoAceptador = estadoAceptador;
    }

    public String getTipoAutomata() {
        return tipoAutomata;
    }

    public void setTipoAutomata(String tipoAutomata) {
        this.tipoAutomata = tipoAutomata;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public LinkedList<Estado> getEstados() {
        return estados;
    }

    public void setEstados(LinkedList<Estado> estados) {
        this.estados = estados;
    }

    public LinkedList<String> getLenguaje() {
        return lenguaje;
    }

    public void setLenguaje(LinkedList<String> lenguaje) {
        this.lenguaje = lenguaje;
    }

    public LinkedList<Transicion> getTransiciones() {
        return transiciones;
    }

    public void setTransiciones(LinkedList<Transicion> transiciones) {
        this.transiciones = transiciones;
    }

    public Estado getEstadoInicial() {
        return estadoInicial;
    }

    public void setEstadoInicial(Estado estadoInicial) {
        this.estadoInicial = estadoInicial;
    }

    public LinkedList<Estado> getEstadoAceptador() {
        return estadoAceptador;
    }

    public void setEstadoAceptador(LinkedList<Estado> estadoAceptador) {
        this.estadoAceptador = estadoAceptador;
    }
    
    
    
    
    
}
