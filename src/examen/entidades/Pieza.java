
package examen.entidades;

import java.awt.Image;
import java.util.Random;
import javax.swing.ImageIcon;

public final class Pieza {
    
    int tamano;

    public int getTamano() {
        return tamano;
    }
    
    
    public Pieza(){
        r= new Random();
        this.urlImagenes = new String[]{"a", "b", "c", "d", "e", "f", "g"};//a y b son malass y 
    }

    public void iniciarPieza(int pos){
      
       tamano=r.nextInt(4);
       if(tamano==0)
           tamano=1;
       
       velocidad=2;
       rutaPieza = "../icons/bb.png"; 
       img = new ImageIcon(getClass().getResource(rutaPieza));
       imagen = img.getImage();
       
       tamaH=(int)(Math.random()*(70-20+1)+20);
       tamaV=(int)(Math.random()*(70-20+1)+20);
       posH=pos;
       posV=0;    
    }

    
    public int getPosH() {
        return posH;
    }

    public void setPosH(int h) {
        this.posH = h;
    }

    public int getPosV() {
        return posV;
    }

    public void setPosV(int posV) {
        this.posV = posV;
    }

    public Image getImagen(){
        return imagen;
    }
    
    public void aplicarMovimiento(){
        posV+=r.nextInt(50);
     } 

    public int getTamaH() {
        return tamaH;
    }

    public int getTamaV() {
        return tamaV;
    }

    public String getNombre() {
        return nombre;
    }
    //--------------------------------------------------------------------------ATRIBUTOS
    Random r;
    int posH;
    int posV;
    int tamaH;
    int tamaV;
    int velocidad;
    private Image imagen;
    private ImageIcon img;
    String rutaPieza;
    private final String []urlImagenes;
    String nombre;
   
}
