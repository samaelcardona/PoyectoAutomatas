/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import com.sun.org.apache.bcel.internal.generic.ATHROW;
import java.util.LinkedList;
import modelo.Automata;
import modelo.Estado;
import modelo.Transicion;


/*
Esta clase esta creada para manejar la construccion del automata, con todos sus atributos.

*/
/**
 *
 * @author Samael
 */
public class MetodosDeCreacionLogica {
  
    ////codigo inicio de MetodosDeCreacionLogica
    //lista de automatas esta para guardar los automatas creados, tiene un get en la parte final de la clase
    //para extraer todos los automatas creados.
    
    LinkedList<Automata> automatas=new LinkedList<>();

    ///metodo para separar las cadenas de string y volverlas una lista, este recibe una cadena y el tipo de la cadena 
    //para saber de que tipo devolver la lista.
    
    
    public  LinkedList  separarCadenas(String cadena,String TipoDividir){
        String cadenaSeparada[]=cadena.split(",");
        
        LinkedList<Object> lista=new LinkedList();
        if("estado".equals(TipoDividir)){
            for (int i = 0; i < cadenaSeparada.length; i++) 
            {
             
             lista.add(new Estado(cadenaSeparada[i], false,false, 0, 0));
            }
        }
        if("string".equals(TipoDividir)){
           
           for (int i = 0; i < cadenaSeparada.length; i++) 
            {             
             lista.add(cadenaSeparada[i]);
            }
        }
        
        
        return lista;
    } 
   
    
    
    //verifica que todos los estados aceptadores esten en la lista de estados si no lo estan muestra un mensaje 
    //y no crea el automata
    public boolean verificarexistencia(LinkedList<Estado> listadeestadosaceptadores,LinkedList<Estado> listadeestados, Estado estadoini){
   
        boolean primeraVerificacion=true;
        boolean segundaVerificacion=false;
        LinkedList<Estado> copiaListaEstadosAceptadores=new LinkedList();
        copiaListaEstadosAceptadores=listadeestadosaceptadores;
        
                
        for (int i = 0; i < listadeestados.size(); i++) 
        {
            for (int j = 0; j < copiaListaEstadosAceptadores.size(); j++) 
            {
                if (listadeestados.get(i).getNombre().equals(copiaListaEstadosAceptadores.get(j).getNombre())) 
                {
                    copiaListaEstadosAceptadores.remove(j);
                }

            } 
           
        }
        if(copiaListaEstadosAceptadores.size()>0)
        {
           primeraVerificacion=false; 
            System.out.println("verifique sus estados");
        }
                

        if(primeraVerificacion==true)
        {
            for (int i = 0; i < listadeestados.size(); i++) 
            {
                if(listadeestados.get(i).getNombre().equals(estadoini.getNombre()))
                {       
                   segundaVerificacion=true; 
                }
            }
            
            if (segundaVerificacion==true) 
            {
                return true;
            }
            else{
                System.out.println("Verifique su estado inicial");
            }
        }
        
    return false;
    }
    
    
    ///Este metodo es utilizado para crear un automata finito determinista o automata finito no determinista
    //recibe todos los parametros en una cadena, esta cadena llama al metodo separarCadenas para crear las listas 
    //necesarias para el automata y llama al metodo de verificarExistencia para que este verifique si concuerdan
    //los estados aceptadores y el estado inicial con la lista de estados agregada, por ultimo crea un automata
    //y lo agrega en la lista de automatas.
    public void crearAutomaatAFD_O_AFN(String nombre,String tipoAutomata, String estados,String lenguaje,String estadoinicial,String estadosaceptadores)
    {
      
        boolean variable=false;
        
        if(!automatas.isEmpty())
        {
            for (int i = 0; i <automatas.size(); i++) 
            {
                if(automatas.get(i).getNombre().equals(nombre))
                {
                    variable=true;
                }
            }
        }
        
        if(variable==true)
        {
            System.out.println("El automata ya existe, verifique nombre");
        }
        else
        {         
            
            LinkedList<Estado> listadeestados=separarCadenas(estados, "estado");
            LinkedList<String> listalenguaje=separarCadenas(lenguaje, "string");
            LinkedList<Estado> listadeestadosaceptadores=separarCadenas(estadosaceptadores, "estado");
           
          
            //cambia el tipo del estado a aceptador en la lista de estados y en la lista de aceptadores
            
               for (int i = 0; i < listadeestadosaceptadores.size(); i++) 
               {
                   for (int j = 0; j < listadeestados.size(); j++) 
                   {
                       if(listadeestados.get(j).getNombre().equals(listadeestadosaceptadores.get(i).getNombre()))
                       {
                        listadeestados.get(j).setEsAceptador(true);
                        listadeestadosaceptadores.get(i).setEsInicial(true);
                       }
                   }
            }
               //cambia de tipo en la lista de estados a inicial para el estado que es inicial
               Estado estadoini=new Estado();
               
               for (int i = 0; i < listadeestados.size(); i++)
               {
                if(listadeestados.get(i).getNombre().equals(estadoinicial))
                {
                    
                    listadeestados.get(i).setEsInicial(true);
                    estadoini=listadeestados.get(i);                   
                }
            }
               
               
           if(verificarexistencia(listadeestadosaceptadores, listadeestados, estadoini)==true)
           {
               Automata automataNuevo=new Automata(nombre,tipoAutomata,listadeestados, listalenguaje, estadoini, listadeestadosaceptadores);               
               automatas.add(automataNuevo);
               System.out.println("El automata se creo exitosamente");
           }
          
            
            
        }
        
         
    }
    
    
    
    //metodo para crear automatas finitos no deterministas con transiciones E
    //recibe todos los parametros en una cadena, esta cadena llama al metodo separarCadenas para crear las listas 
    //necesarias para el automata en la lista de lenguaje se le agrega la transicion E para que se identifique 
    //como un AFN_E luego de agregar esto se llama al metodo de verificarExistencia para que este verifique si concuerdan
    //los estados aceptadores y el estado inicial con la lista de estados agregada, por ultimo crea un automata
    //y lo agrega en la lista de automatas.
    
    public void crearAutomaatAFN_E(String nombre,String tipoAutomata, String estados,String lenguaje,String estadoinicial,String estadosaceptadores)
    {
        boolean variable=false;
        
        if(!automatas.isEmpty())
        {
            for (int i = 0; i <automatas.size(); i++) 
            {
                if(automatas.get(i).getNombre().equals(nombre))
                {
                    variable=true;
                }
            }
        }
        
        if(variable==true)
        {
            System.out.println("El automata ya existe, verifique nombre");
        }
        else
        {         
            
            LinkedList<Estado> listadeestados=separarCadenas(estados, "estado");
            LinkedList<String> listalenguaje=separarCadenas(lenguaje, "string");
            listalenguaje.add("E");
            LinkedList<Estado> listadeestadosaceptadores=separarCadenas(estadosaceptadores, "estado");
           
            
            
            //cambia el tipo del estado a aceptador en la lista de estados y en la lista de aceptadores
            
               for (int i = 0; i < listadeestadosaceptadores.size(); i++) 
               {
                   for (int j = 0; j < listadeestados.size(); j++) 
                   {
                       if(listadeestados.get(j).getNombre().equals(listadeestadosaceptadores.get(i).getNombre()))
                       {
                        listadeestados.get(j).setEsAceptador(true);
                        listadeestadosaceptadores.get(i).setEsAceptador(true);
                       }
                   }
            }
               
               //cambia de tipo en la lista de estados a inicial para el estado que es inicial
               Estado estadoini=new Estado();
               
               for (int i = 0; i < listadeestados.size(); i++)
               {
                if(listadeestados.get(i).getNombre().equals(estadoinicial))
                { 
                    listadeestados.get(i).setEsInicial(true);
                    estadoini=listadeestados.get(i);                   
                }
            }
              
           if(verificarexistencia(listadeestadosaceptadores, listadeestados, estadoini)==true)
           {
               Automata automataNuevo=new Automata(nombre,tipoAutomata,listadeestados, listalenguaje, estadoini, listadeestadosaceptadores);               
               automatas.add(automataNuevo);
               System.out.println("El automata se creo exitosamente");
           }
        }   
    }
    
    
    
    ///devuelve el estado de un automata recicibiendo el nombre del estado como una cadena o String y se busca en el automata
    //que tambien se recibe como parametro
    public Estado devolverEstado(Automata automata,String nombreEstado)
    {
        for (int i = 0; i < automata.getEstados().size(); i++) {
            if(automata.getEstados().get(i).getNombre().equals(nombreEstado))
            {
                return automata.getEstados().get(i);
            }
        }
        return null;
    }
    
    
    ///metodo para verificar si un estado existe en una lista de estados.
    //este recie la lista de estados del automata y el estado a verificar 
    //retorna un true si el estado si existe en la lista de estados
    public boolean verificarEstadoTransicion(LinkedList<Estado> listadeEstados,Estado estado)
    {
        boolean primeraVerificacion=false;
        
         for (int i = 0; i < listadeEstados.size(); i++) 
            {
                if(listadeEstados.get(i).getNombre().equals(estado.getNombre()))
                {       
                   primeraVerificacion=true; 
                }
            }
            
            if (primeraVerificacion==true) 
            {
                return true;
            }
            else{
                System.out.println("Verifique su estado inicial");
            }
            return false;
    }      
    
    
    ///verifica si el simbolo si existe en la lista de simbolos de un automata
    //recibe el automata y el simbolo retorna un true para decir que si existe 
    
    public boolean verificarSimbolo(Automata automata,String simbolo)
    {
        for (int i = 0; i < automata.getLenguaje().size(); i++)
        {
             if (automata.getLenguaje().get(i).equals(simbolo))
             {
                   return true;
             }
        }
        
        return false;
    }
   
    
    ///metodo para la verificacion de afd esto nos ayuda a manterner un estado con un simbolo no duplicado, es decir:
    //este vela por que el automata sea finito de determinista.
    //recibe e automata al cual le voy a agregar la transicion el estadoA y el simbolo para hacer la verificacion 
    //si no hay duplicacion de simbolo  retorna un true de lo contrario false
    
    public boolean verificarTransicionAFD(Automata automata,String estadoA,String simbolo)
    {
        
        for (int i = 0; i < automata.getTransiciones().size(); i++)
        {
            if (automata.getTransiciones().get(i).getEstadoA().getNombre().equals(estadoA)) 
            {
                if (automata.getTransiciones().get(i).getSimbolo().equals(simbolo)) 
                {
                    return false;
                } 
            }
        }
        
         return true;
       
    }
    
    
    
    
    ///Este metodo es para agregar una  transicion recibe el nombre del automata al cual se le va a agregar 
    //la transicion el estadoA con el simbolo al cual va al estadoB ya despues este llamo al metodo verificarEstadoTransicion
    //este devuelve un true o false para saber si el estado al cual me refiero existe, luego llamo al metodo verificarSimbolo
    //este verifica que el simbolo que menciona si exista en la lista de simbolos de dicho automata 
    //tambien se utiliza el metodo devolverEstado este para recolectar el estado al cual me estoy refiriendo 
    //para agregar la transicion y por ultimo llama al metodo verificarTransicionAFD este para verificar la transicion
   // ya que este metodo es para automatas finitos deterministas y no se puede agregar los estados con simbolos repetidos
///a otros estados.
    
    public void agregarTransicionAFD(String nombreAutomata,String estadoA,String simbolo,String estadoB)
    {
        //esto es para verificar a que automata le voy a agregar la transicion
        
        int posicionAutomata=0;
        boolean verificacionExistenciaAutomata=false;
        
        for (int i = 0; i < automatas.size(); i++) {
            if(automatas.get(i).getNombre().equals(nombreAutomata))
            {
                posicionAutomata=i;
                verificacionExistenciaAutomata=true;
            }
        }
        
        
       ///aca ya inicio a agregar 
      if(verificacionExistenciaAutomata==true)  
      {
          if(verificarEstadoTransicion(automatas.get(posicionAutomata).getEstados(), devolverEstado(automatas.get(posicionAutomata), estadoA))==true)
          {
              if(verificarEstadoTransicion(automatas.get(posicionAutomata).getEstados(), devolverEstado(automatas.get(posicionAutomata), estadoB))==true)
              {
                  if(verificarSimbolo(automatas.get(posicionAutomata), simbolo)==true)
                  {
                     ///verificar si no hay una transicion con ese simbolo 
                      
                      if(automatas.get(posicionAutomata).getTransiciones().size()==0){
                          Transicion nuevaTransicion=new Transicion(devolverEstado(automatas.get(posicionAutomata), estadoA), simbolo, devolverEstado(automatas.get(posicionAutomata), estadoB));
                          
                          automatas.get(posicionAutomata).getTransiciones().add(nuevaTransicion);
                          
                      }
                      else{
                          
                            if(verificarTransicionAFD(automatas.get(posicionAutomata), estadoA, simbolo)==true)
                            {
                                 Transicion nuevaTransicion=new Transicion(devolverEstado(automatas.get(posicionAutomata), estadoA), simbolo, devolverEstado(automatas.get(posicionAutomata), estadoB));
                          
                                 automatas.get(posicionAutomata).getTransiciones().add(nuevaTransicion); 
                            }
                            else
                            {
                                    System.out.println("verifique transicion Para AFD");
                            }
                            
                              
                          }
                                                    
                      }
                      
                  } 
              }
          }
      
      
      
      }
        
    
    
        
    ///Este metodo es para agregar una  transicion para un AFN y un AFN_E recibe el nombre del automata al cual se le va a agregar 
    //la transicion el estadoA con el simbolo al cual va al estadoB ya despues este llamo al metodo verificarEstadoTransicion
    //este devuelve un true o false para saber si el estado al cual me refiero existe, luego llamo al metodo verificarSimbolo
    //este verifica que el simbolo que menciona si exista en la lista de simbolos de dicho automata 
    //tambien se utiliza el metodo devolverEstado este para recolectar el estado al cual me estoy refiriendo 
    //para agregar la transicion.
    
    public void agregarTransicionAFN_O_AFNE(String nombreAutomata,String estadoA,String simbolo,String estadoB)
    { 
        //esto es para verificar a que automata le voy a agregar la transicion
        
        int posicionAutomata=0;
        boolean verificacionExistenciaAutomata=false;
        
        for (int i = 0; i < automatas.size(); i++) {
            if(automatas.get(i).getNombre().equals(nombreAutomata))
            {
                posicionAutomata=i;
                verificacionExistenciaAutomata=true;
            }
        }
        
        
       ///aca ya inicio a agregar 
        if (verificacionExistenciaAutomata == true) 
        {
            if (verificarEstadoTransicion(automatas.get(posicionAutomata).getEstados(), devolverEstado(automatas.get(posicionAutomata), estadoA)) == true) 
            {
                if (verificarEstadoTransicion(automatas.get(posicionAutomata).getEstados(), devolverEstado(automatas.get(posicionAutomata), estadoB)) == true) 
                {
                    if (verificarSimbolo(automatas.get(posicionAutomata), simbolo) == true) 
                    {
                     ///verificar si no hay una transicion con ese simbolo 

                        if (automatas.get(posicionAutomata).getTransiciones().size() == 0) 
                        {
                            Transicion nuevaTransicion = new Transicion(devolverEstado(automatas.get(posicionAutomata), estadoA), simbolo, devolverEstado(automatas.get(posicionAutomata), estadoB));

                            automatas.get(posicionAutomata).getTransiciones().add(nuevaTransicion);

                        }
                        else 
                        {
                            Transicion nuevaTransicion = new Transicion(devolverEstado(automatas.get(posicionAutomata), estadoA), simbolo, devolverEstado(automatas.get(posicionAutomata), estadoB));

                            automatas.get(posicionAutomata).getTransiciones().add(nuevaTransicion);

                        }

                    }

                }
            }
        }

    }
    
    
    
    
    
    ///metodo para verificar si con una cadena acepta o no 
    //este meotodo recorre estado por estado segun la cadena que nos da.
    public boolean lectorDeCadena(String nombreAutomata,String cadena)
    {
        
        LinkedList<Estado> rutaCadena=new LinkedList<>();
       
        for (int i = 0; i < this.automatas.size(); i++) 
        {
            ///saco el automata que me dieron en el string 
            if (automatas.get(i).getNombre().equals(nombreAutomata)) 
            {
                if (automatas.get(i).getTipoAutomata().equals("AFD")) 
                {
                    if (this.verificarLenguaje(automatas.get(i), cadena)==true) 
                    {

                        //ruta cadena es la ruta que toma con la cadena dada 
                        rutaCadena = recorridoEvaluarCadena(automatas.get(i), rutaCadena, automatas.get(i).getEstadoInicial(), cadena);

                    //aca tomo la ultima posicion en la que quedo por el recorrido de la cadena
                        //y verifico si es aceptador o no 
                        if (rutaCadena.getLast().isEsAceptador() == true) 
                        {
                            return true;
                        }

                        if (rutaCadena.getLast().isEsAceptador() == false) 
                        {
                            return false;
                        }
                    }
                }
            }
        }
        return false;
    }
    
    
    //verificar que la cadena si este en el lenguaje si no esta no sigue el metodo lectorDeCadena este retorna un true o un false 
    //true si la cadena toda es del lenguaje
    
    public boolean verificarLenguaje(Automata automata,String cadena)
    { 
        for (int i = 0; i < cadena.length(); i++) {
            if (!automata.getLenguaje().contains(""+cadena.charAt(i))) 
            {
                return false;
            }
        }
        return true;
    }
    
    ///metodo para complementar lectorDeCadena retorna la lista de estados del recorrido que dejo la cadena
        public LinkedList<Estado> recorridoEvaluarCadena(Automata automata,LinkedList<Estado> rutaCadena,Estado estadoN,String cadena)
        {
            
            if (cadena.length()!=0) 
            {           
                    for (int k = 0; k < automata.getTransiciones().size(); k++) {
                        
                        if(automata.getTransiciones().get(k).getEstadoA().getNombre().equals(estadoN.getNombre())
                                &&automata.getTransiciones().get(k).getSimbolo().equals(cadena.charAt(0)+""))
                        {
                            System.out.println(""+estadoN.getNombre()+"Ace"+estadoN.isEsAceptador());
                            estadoN=automata.getTransiciones().get(k).getEstadoB();
                            rutaCadena.add(estadoN);
                            return recorridoEvaluarCadena(automata,rutaCadena, estadoN,cadena.substring(1));
                        }
                    }
                    
                
            }            
            return rutaCadena;
        }
       
        
        
        
        
        
        
        
        
        
        
    
    
    
    
    
    
    
    public LinkedList<Automata> getAutomatas() {
        return automatas;
    }
    
    
    ///fin de la clase MetodosDeCreacionLogica
}
