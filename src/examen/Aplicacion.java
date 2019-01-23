/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examen;

import examen.control.Control;
import examen.vista.Vista;

/**
 *
 * @author Estudiante
 */
public class Aplicacion {

    public static int CANT_PIEZAS=5;
    
    public static void main(String[] args) {
        Vista v=new Vista(new Control());
    }
    
    
    
}
