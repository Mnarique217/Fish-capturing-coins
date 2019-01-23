
package examen.modelo;


import examen.entidades.Jugador;
import examen.entidades.Jugadores;
import javax.swing.table.AbstractTableModel;


public class TablaModel extends AbstractTableModel{

    public TablaModel(Jugadores contenedor) {
        this.contenedor = contenedor;
    }
    
    public TablaModel() {
        this(new Jugadores());
    }

    @Override
    public int getRowCount() {
      return contenedor.cantidad(); //cantidad de filas       
    }

    @Override
    public int getColumnCount() {
        return Jugador.getATRIBUTOS().length; 
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
       return  contenedor.recuperar(rowIndex).fullContenido()[columnIndex];
    }           
    

    public void add(Jugador p){
        contenedor.add(p);   
        this.fireTableDataChanged();
    }
    
    public void delete(int rowIndex){
    contenedor.eliminar(rowIndex);
     this.fireTableDataChanged();
    }
    
    @Override
    public String getColumnName(int col){
        return Jugador.getATRIBUTOS()[col];
    }

    public void setContenedor(Jugadores contenedor) {
        this.contenedor = contenedor;
    }

    public void setChanged() {
        this.fireTableDataChanged();
    }
    
    //atrinutos
    private Jugadores contenedor;

}
