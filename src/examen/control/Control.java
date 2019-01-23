/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examen.control;

import examen.entidades.Jugador;
import examen.modelo.Modelo;
import examen.vista.Vista;
import java.awt.event.KeyEvent;
import java.util.Observer;
import javax.swing.table.TableModel;

/**
 *
 * @author Estudiante
 */
public class Control {


    Modelo modelo;

    public Control(Modelo modelo) {
        this.modelo = modelo;
    }
    
    public Control( ) {
        this.modelo = new Modelo();
    }
    
    
    public void addObserver(Observer aThis) {
        modelo.addObserver(aThis);
    }

    public TableModel getTabla() {
        return modelo.getTabla();
    }



    public void guardar(Jugador p) {
        modelo.agregar(p);
    }

    public void mover(KeyEvent e) {
    }

    public void completarJugadores() {
        modelo.enviarJugadore();
    }

    public void moverJugador1(KeyEvent e) {
        
        modelo.moverJugador1(e);
    }
    
      public void moverJugador2(KeyEvent e) {
        modelo.moverJugador2(e);
    }

    public void leerxml() {
        modelo.leerXML();
    }

   public void revisar() {
        modelo.revisar();
    }

    public void moverPieza() {
        modelo.moverPieza();
    }

    public void guardarXml() {
        modelo.guardarXML();
    }
}
