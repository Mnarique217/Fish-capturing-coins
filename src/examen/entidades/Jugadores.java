
package examen.entidades;


import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import juego.xml.UtilidadesXML;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


public class Jugadores {

    public Jugadores() {
         Jugadores = new ArrayList<>();
    }

    public ArrayList<Jugador> getJugadores() {
        return Jugadores;
    }
    
    public void add(Jugador p){
        Jugadores.add(p);
        
    }
    
    public Jugador recuperar(int p){
        return Jugadores.get(p);
    }
    
    public int cantidad(){
        return Jugadores.size();
    }
    
    public void eliminar(int i){
        if(!Jugadores.isEmpty())
            Jugadores.remove(i);
    }
    
    public Jugadores filtrar(String n){
        
        Jugadores aux=new Jugadores();
        for(int i=0; i<Jugadores.size(); i++){
        
            if(Jugadores.get(i).getNick().contains(n))
             aux.add(Jugadores.get(i));
        }
        return aux;
    }
    
    public void guardarXml() {
   
        Document d;
   try {
          d = UtilidadesXML.crearDocumento();
      
          Node r = d.createElement("Informacion_Jugadores");
          for(int i=0; i <this.cantidad(); i++)
             r.appendChild(recuperar(i).toXML(d)); 
               
          
            d.appendChild(r);
            UtilidadesXML.guardarArchivoXML(d, "jugadores.xml"); 
            
            System.out.println("Programa finalizado..");
         } catch (ParserConfigurationException ex) {
        Logger.getLogger(Jugadores.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
     public void leerXML(Node nodo) {
        NodeList arbolEtiquetas = nodo.getChildNodes();
        int numJugadores = arbolEtiquetas.getLength();
        for (int i = 0; i < numJugadores; i++) {
            Node etiquetaJugador = arbolEtiquetas.item(i);
            Jugador p = new Jugador();
            p.leerXML(etiquetaJugador);
            if(!verificar(p.getNombre()))
             Jugadores.add(p);            
        }
    }
     
     
     public boolean verificar(String nombre){
            for(int i=0; i<Jugadores.size(); i++){
            if(Jugadores.get(i).getNombre().contains(nombre))
             return true;
        }
            return false;
     }
    //--------------------------------------------------------------------------
    private ArrayList<Jugador> Jugadores;

    public void setJugador(Jugador j, int pos) {
        Jugadores.set(pos, j);
    }

 
  
}
