 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examen.modelo;

import static examen.Aplicacion.CANT_PIEZAS;
import examen.entidades.Jugador;
import examen.entidades.Jugadores;
import examen.entidades.Piezas;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;
import javax.swing.table.TableModel;
import juego.xml.UtilidadesXML;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

/**
 *
 * @author Estudiante
 */
public class Modelo extends Observable{
    boolean temp;
    int cantidadPiezas;
     private   Piezas piezas;
     int numeroJugador;
    Jugadores contenedor;
    TablaModel tabla;
    Random r;
    Jugador jugador1;
    Jugador jugador2;
    private String[] imagens1 = {"ai", "ad", ""};
    private String[] imagens2 = {"ri", "rd", ""};
    boolean aumentar;
  
    public Modelo() {
        temp=false;
        aumentar=false;
        r=new Random();
        cantidadPiezas=1;
        piezas=new Piezas();
        jugador1=new Jugador("default", "j2",0);
        jugador2=new Jugador("default", "j1",0);
        numeroJugador=0;
        
        contenedor=new Jugadores();
        contenedor.add(jugador1);
        contenedor.add(jugador2);
        jugador1.setIMAGENES(imagens1);
        jugador2.setIMAGENES(imagens2);
        tabla=new TablaModel(contenedor);
            }

    public Piezas getPiezas() {
        return piezas;
    }

    
    public void moverPieza() {
         
        for(int i=0; i<cantidadPiezas; i++)
        piezas.obtenerPieza(i).aplicarMovimiento();
    }
    
    public void revisar(){
           int n;
 
            temp=false;
           golpeado();
            for(int i=0; i<cantidadPiezas && temp==false; i++){
                n=piezas.obtenerPieza(i).getPosV();
                if(n>550){
                    temp=true;piezas.obtenerPieza(i).iniciarPieza(r.nextInt(800));}
            }
       
            if(temp && cantidadPiezas <CANT_PIEZAS){
                cantidadPiezas++; aumentar=true; actualizar(" aumenta numero de piezas");aumentar=false;}
         }
    
       private void golpeado() {
        int x;
        int limite;
        int limite2;
        int limitev;
        
      
        
         for(int i=0; i<cantidadPiezas; i++){// y no ha finalizad agregar
            limitev= piezas.obtenerPieza(i).getPosV();
            x= piezas.obtenerPieza(i).getPosH();
            limite=jugador1.getPosH();
            limite2=jugador2.getPosH();
            
             if(limite-50<=x && x<= limite+100  && limitev>450 ){
                 temp=true;
                 jugador1.setPuntaje(piezas.obtenerPieza(i).getTamano());
                 piezas.obtenerPieza(i).iniciarPieza(r.nextInt(800));
                 tabla.setChanged();
                // System.out.println("jugaro rojo suma puntos ");
             }
             else
                 if(limite2-50<=x && x<= limite2+100  && limitev>450 ){
                 temp=true;
                 jugador2.setPuntaje(piezas.obtenerPieza(i).getTamano());
                 tabla.setChanged();
                 piezas.obtenerPieza(i).iniciarPieza(r.nextInt(800));
                 //System.out.println("jugaro azul suma puntos ");
             }   
         }
    }
   
       
    public Jugador getJugador1() {
        return jugador1;
    }

    public Jugador getJugador2() {
        return jugador2;
    }
    
    @Override
    public void addObserver(Observer o){
        super.addObserver(o);
    }

    public TableModel getTabla() {
        return tabla;
    }
    
    public void agregar(Jugador j){
        if(numeroJugador<1)
            j.setIMAGENES(imagens1);
        else
             j.setIMAGENES(imagens2);
        
        if(!contenedor.verificar(j.getNombre()) && numeroJugador<1){
          jugador1=j;contenedor.setJugador(j,numeroJugador);tabla.setChanged();}
        else
          if(!contenedor.verificar(j.getNombre()) && numeroJugador>=1){
          jugador2=j;contenedor.setJugador(j,numeroJugador);tabla.setChanged();}

        contenedor.guardarXml();
        tabla.setChanged();
        numeroJugador++;
        actualizar("jugador agregado");
    }

    
   public void moverJugador1(KeyEvent e) {
       jugador1.mover(e);
    }

   public void moverJugador2(KeyEvent e) {
       jugador2.mover(e);
    }
    private void actualizar(Object o) {
        this.setChanged();
        this.notifyObservers(o);
    }

    public int getNumeroJugador() {
        return numeroJugador;
    }

   public void enviarJugadore() {
      
       actualizar("jugadores enviados");
    }
    
   
   public void leerXML(){
        File file = new File("jugadores.xml");
        Document documentoXML = UtilidadesXML.crearXMLDocumento(file);
        Node raiz = documentoXML.getDocumentElement();
        contenedor.leerXML(raiz);
        tabla.setChanged();
        actualizar("leidos");
     }

    public int getCantidadPiezas() {
        return cantidadPiezas;
    }

    public void guardarXML() {
        contenedor.guardarXml();
    }
  


 
}
