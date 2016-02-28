/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoautomtas;

import controlador.MetodosDeCreacionLogica;

/**
 *
 * @author Samael
 */
public class ProyectoAutomtas {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        
        MetodosDeCreacionLogica mcl=new MetodosDeCreacionLogica();
        

        mcl.crearAutomaatAFD_O_AFN("AT1","AFD","a,b,c","1,0","a","c");
        mcl.agregarTransicionAFD("AT1", "a", "0", "b");
        mcl.agregarTransicionAFD("AT1", "b", "0", "c");
        mcl.agregarTransicionAFD("AT1", "c", "0", "c");
        mcl.agregarTransicionAFD("AT1", "a", "1", "a");
        mcl.agregarTransicionAFD("AT1", "b", "1", "a");
        mcl.agregarTransicionAFD("AT1", "c", "1", "a");
        
//        System.out.println("primera transicion estadoA"+mcl.getAutomatas().get(0).getTransiciones().get(0).getEstadoA().getNombre());
//        System.out.println("primera transicion simbolo"+mcl.getAutomatas().get(0).getTransiciones().get(0).getSimbolo());
//        System.out.println("primera transicion estadoB"+mcl.getAutomatas().get(0).getTransiciones().get(0).getEstadoB().getNombre());
//        
        
//        mcl.crearAutomaatAFN_E("AT2","AFD","a,b,c","1,0","a","a");
//        
//        for (int i = 0; i < mcl.getAutomatas().get(1).getLenguaje().size(); i++) 
//        {
//           System.out.println("Lenguaje"+mcl.getAutomatas().get(1).getLenguaje().get(i)); 
//        }
        
        System.out.println(""+mcl.lectorDeCadena("AT1", "100000100"));
        
        System.out.println("Hola mundo");
       
////        System.out.println("nombre automata:"+mcl.getAutomatas().get(0).getNombre());
////        System.out.println("nombre automata:"+mcl.getAutomatas().get(0).getTipoAutomata());
////        
        
        
    }
    
}
