
package examen.entidades;

import static examen.Aplicacion.CANT_PIEZAS;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;



public class Piezas {
    ArrayList<Pieza> piezas;
  
    public Piezas(){
        pila=new HashMap<>();
        
        this.numeros = new int[CANT_PIEZAS];
        piezas = new ArrayList<>(CANT_PIEZAS); 
        agregarPiezas();
    }
    
   private void agregarPiezas() {
       
       for(int i=0; i<CANT_PIEZAS;i++){
           Pieza p=new Pieza();
           p.iniciarPieza(r.nextInt(800));
           piezas.add(p);  
       }
           
    }
   
    
    public Pieza obtenerPieza(int i){
        return piezas.get(i);
    }

    public void moverPiezas(){
        for(int i=0; i<CANT_PIEZAS; i++){
            piezas.get(i).aplicarMovimiento();
        }
    }
  
    public int  size(){
        return piezas.size();      
    }
    
    public boolean posile(){       
    return false;
    }
    
    public void add(Pieza e){
    piezas.add(e);
    }
    
    public void remove(int index){
    piezas.remove(index);
    }

    public void reiniciar() {
        for(int i=0; i<this.size(); i++)
            piezas.get(i).iniciarPieza(r.nextInt(800));
    }

     private Random r = new Random();
     int [] numeros;
     private HashMap<Integer,Boolean> pila;

}
