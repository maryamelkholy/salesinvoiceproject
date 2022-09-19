
package model;

    public class Line {
        private String itemName;
        private double price ;
        private int count;
        private Header invoice ;

    public Line() {
    }

    public Line(String itemName, double price, int count , Header invoice) {
    
        this.invoice = invoice;
        this.itemName = itemName;
        this.price = price;
        this.count = count;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public void setPrice(double price) {
        this.price = price;
    }

  

   

    public double getPrice() {
        return price;
    }

    public void setInvoice(Header invoice) {
        this.invoice = invoice;
    }
    
     public String getItemName() {
        return itemName;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Header getInvoice() {
        return invoice;
    }

    public int getCount() {
        return count;
    }

    public double getTotalLines(){
    return count * price ;
    }
    
        public String getFileCSV()
    {
    return invoice.getNumber() + "," + itemName + "," + price + "," + count ;
            
    }

    @Override
    public String toString() {
        return "InvoiceLine{" + "itemName=" + itemName + ", price=" + price + ", count=" + count + ", invoice=" + invoice + '}';
    }
 

    
}
