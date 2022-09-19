
package model;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

public class HeaderTable extends AbstractTableModel{
    private ArrayList<Header> invoices ;
    private final String [] columns = {"No." , "Date","Customer" , "Total"};
    
    public HeaderTable(ArrayList<Header> invoices) {
        this.invoices = invoices;
    }
    
    @Override
    public String getColumnName(int column) {
        return columns[column];
    }

    @Override
    public int getColumnCount() {
        return columns.length;
          }
    
    @Override
    public int getRowCount() {
        return invoices.size();
           }

   

    
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Header invoice  = invoices.get(rowIndex);
        
        if(columnIndex==0) return invoice.getNumber();
            if(columnIndex==1) return invoice.getDate();
            if(columnIndex==2) return invoice.getName();
            if(columnIndex==3) return invoice.getTotalInvoice();
            return 0;   
    }
   
}
