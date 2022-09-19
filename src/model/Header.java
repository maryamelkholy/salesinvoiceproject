    
package model;
import java.util.ArrayList;

public class Header {


    private int number ;
    private String date ;
    private String name ;
    private ArrayList<Line> lines ;
    
    public Header() {
    }

    
    public Header(int number, String date, String name) {
        this.number = number;
        this.date = date;
        this.name = name;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumber() {
        return number;
    }

    public String getDate() {
        return date;
    }

    public String getName() {
        return name;
    }

        public ArrayList<Line> getLines() {
        if (lines == null){
            lines = new ArrayList<>();}      
        return lines;
   }
    
        public double getTotalInvoice(){
        double total = 0.0 ;
            for (Line lines : getLines()) {
            total += lines.getTotalLines();
            }
        return total;
        
        }
    
    @Override
    public String toString() {
        return "InvoiceHeader{" + "number=" + number + ", date=" + date + ", name=" + name + '}';
    }

    
    public String getFileCSV()
    {
    return number + "," + date + "," + name ;
            
    }
    

    
}
