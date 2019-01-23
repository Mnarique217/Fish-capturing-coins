package examen.vista;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import examen.control.Control;
import examen.entidades.Jugador;
import examen.modelo.Modelo;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JButton;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.Timer;

/**
 * @author xxxx
 */
public class Vista extends JFrame implements Observer,Runnable,ActionListener {

    int velocidad;
    Thread hilo;
    JPanel pnlPrincipal;
    JPanel pnlIzquierdo;
    JPanel pnlDerecho;
    JScrollPane scroll;
    JTable tabla;
    JButton btnIniciar;
    JButton btnAgregar;
    JButton btnBorrar;
    
    JTextField txtNombre;
    JTextField txtNick;
    PanelImagen fondo;
    Control control;
    Timer t;
    
    public Vista(Control control) {
        
        t=new Timer(2000, this);
        velocidad=800;
        this.control=control;
        this.control.addObserver(this);
        configuracion();
        agregarComponentes(); 
        setFocus();
        istenerVentana();
        this. addKeyListener(new Teclado());
        mostrar(true);
    }

    
    @Override
    public void actionPerformed(ActionEvent e) {
       if(velocidad-50>0)
           velocidad-=50;
       
    }

    
    private void configuracion (){
        this.setSize(1200,800);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    
     private void istenerVentana(){
        this.addWindowListener(new WindowAdapter() {

             @Override
            public void windowClosing(WindowEvent e) {
                if(JOptionPane.showConfirmDialog(null,JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION){
                    control.guardarXml() ;
                    System.exit(0);
                }
                   
            }
        });
    }
    
  private void mostrar(boolean b){
        this.setVisible(b);
    }
    

    private void agregarComponentes() {
      iniciarPanelPrincipal();
      iniciarPanelInferior();
      this.getContentPane().add(pnlPrincipal);
      setFondo();
      control.completarJugadores();
    }
    
    public void iniciarPanelPrincipal(){
      pnlPrincipal=new JPanel(new BorderLayout()); 
    }
    

      
    public void iniciarPanelInferior(){
    
        btnAgregar=new JButton("agregar");
        btnIniciar=new JButton("comenzar");
        btnBorrar=new JButton("leerXml");
        btnAgregar.setActionCommand("agregar");
        btnBorrar.setActionCommand("leer");
        btnIniciar.setActionCommand("comenzar");
        addListener(btnBorrar);
        addListener(btnAgregar);
        addListener(btnIniciar);
        JLabel nom=new JLabel("nombre");
        JLabel desc=new JLabel("nick");
        
        
        txtNombre=new JTextField(5);
        txtNick=new JTextField(5);
        
        tabla=new JTable();
        tabla.setModel(control.getTabla());
        scroll =new JScrollPane(tabla);
        scroll.setPreferredSize(new Dimension(25,25));
        pnlPrincipal.add(scroll);
        
        JPanel aux1 =new JPanel(new BorderLayout());
        
        
        JPanel aux =new JPanel(new FlowLayout(FlowLayout.LEFT));
        aux.add(btnAgregar);
        aux.add(btnIniciar);
        aux.add(btnBorrar);
       
        aux.add(nom);
        aux.add(txtNombre);
        aux.add(desc);
        aux.add(txtNick);
        aux1.add(scroll);
        aux1.add(aux,BorderLayout.PAGE_END);
        pnlPrincipal.add(aux1,BorderLayout.LINE_END);
    }
    
        private void addListener(JButton btn ){
    
        btn.addActionListener((ActionEvent e) -> {
            filtro(e);
        });
    }

    private void filtro(ActionEvent e) {
        switch(e.getActionCommand()){
            case"agregar":agregar();break;
            case"leer": leer(); break;
            case"comenzar":iniciar(); break;
            
        }
    }

        
    public void setFondo(){

        fondo=new PanelImagen();
        pnlPrincipal.add(fondo);
    }

    private void agregar() {

       if(txtNick.getText().isEmpty() || txtNombre.getText().isEmpty()){
           JOptionPane.showMessageDialog(this, "completar datos","completar datos",0);return;}
     
            String c=this.txtNombre.getText();
            String d=this.txtNick.getText();
           
           Jugador  p= new Jugador(c,d,0);
           control.guardar(p);
           txtNombre.setText("");
           txtNick.setText("");
           this.setFocus();
    }
    
    private void leer() {
        btnBorrar.setEnabled(false);
        control.leerxml();
    }

    @Override
    public void run() {
               
        while(hilo==Thread.currentThread() ){
              control.moverPieza();
              control.revisar();
              fondo.repaint();
              setFocus();
            try {
                
                hilo.sleep(velocidad);
            } catch (InterruptedException ex) {
            }

        }
    }

    private void iniciar() {
        t.start();
         hilo=new Thread(this);
        if(hilo!=null)
            hilo.start();
    }


  

     private class Teclado extends KeyAdapter{
        @Override
        public void keyPressed(KeyEvent e){
            
            switch (e.getKeyCode()) {
                case KeyEvent.VK_LEFT:
                case KeyEvent.VK_RIGHT:
                case KeyEvent.VK_DOWN:control.moverJugador1(e);break;
                case KeyEvent.VK_A:
                case KeyEvent.VK_D:control.moverJugador2(e);fondo.repaint(); break;
                default:
                    JOptionPane.showMessageDialog(null, "? ->"+e.getKeyChar());
                    break;
            }
            
             setFocus();
             fondo.repaint();      
        }
    }

     
    @Override
    public boolean isFocusable() {
        return true;
    }
    
     private void setFocus(){
     this.requestFocus();
    }
    
    
    @Override
    public void update(Observable o, Object arg) {
        Modelo m=(Modelo)o;
        if(m.getNumeroJugador()>=2){
            btnAgregar.setEnabled(false);
        }  
        tabla.repaint();
        
        fondo.setJ1(m.getJugador1());
        fondo.setJ2(m.getJugador2());
        
        fondo.setCantidadPiezas(m.getCantidadPiezas());
        fondo.repaint();
        fondo.setPiezas(m.getPiezas());
        
        this.setFocus();
    }
    

    
}
