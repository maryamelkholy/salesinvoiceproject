
package model;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

public class LineTable extends AbstractTableModel{

    private ArrayList<Line> lines ;
    String [] columnLines = {"No.","Item Name","Item Price","Count","Item Total"};

    public LineTable(ArrayList<Line> lines) {
        this.lines = lines;
    }

       
    @Override
    public int getRowCount() {
        return lines.size();
         }

    @Override
    public int getColumnCount() {
    return columnLines.length;
    }

    @Override
    public String getColumnName(int column) {
      return columnLines[column];      
    }

    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
    
        Line line = lines.get(rowIndex);
        
            if(columnIndex==0) return line.getInvoice().getNumber();
            if(columnIndex==1) return line.getItemName();
            if(columnIndex==2) return line.getPrice();
            if(columnIndex==3) return line.getCount();
            if(columnIndex==4) return line.getTotalLines();
         return "";   
        }
    public ArrayList<Line> getLines() {
        return lines;
    }

}

   
  
    

