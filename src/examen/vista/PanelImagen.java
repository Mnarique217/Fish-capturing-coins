

package examen.vista;

import examen.entidades.Jugador;
import examen.entidades.Piezas;
import java.awt.Graphics;
import java.awt.Image;


import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class PanelImagen extends JPanel   {

    PanelImagen(){
        piezas=null;
        reiniciar();
    }
    
    public void reiniciar(){
        cantPiezas=1;
    }
   
   @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        dibujarImagenes(g);
    }

    public void dibujarImagenes(Graphics g) {
        imagen = new ImageIcon(new ImageIcon(getClass().getResource("../icons/f.jpg")).getImage());
        g.drawImage(imagen.getImage(), 0, 0, getWidth(), getHeight(), null);
        
         g.drawImage(j2.getImagen(), j2.getPosH(),j2.getPosV(), 60, 60, this);
         g.drawImage(j1.getImagen(), j1.getPosH(),j1.getPosV(), 60, 60, this);
       
         if(piezas!=null){
             for(int i=0; i< this.cantPiezas; i++){
             Image img=piezas.obtenerPieza(i).getImagen();
            int posHor  =piezas.obtenerPieza(i).getPosH();
            int posVert =piezas.obtenerPieza(i).getPosV();
            int tamaH=piezas.obtenerPieza(i).getTamaH();
            int tamaV=piezas.obtenerPieza(i).getTamaV();
            g.drawImage(img, posHor,posVert, tamaH, tamaV, this);
         }
         }
             
    }

    public void setJ1(Jugador j1) {
        this.j1 = j1;
    }

    public void setJ2(Jugador j2) {
        this.j2 = j2;
    }
    
    ///atributos
     private  ImageIcon imagen;
     
  
     
    void setPiezas(Piezas piezas) {
        this.piezas=piezas;
    }

    void setCantidadPiezas(int cantidadPiezas) {
        this.cantPiezas=cantidadPiezas;
    }
    
     Jugador j1;
     Jugador j2;
     Piezas piezas;
     int cantPiezas;
   
 }
