package controller;
import model.Header;
import model.Line;
import model.LineTable;
import model.HeaderTable;
import view.InvoiceView;
import view.frame;
import view.LineView;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class ControllingClass implements ActionListener , ListSelectionListener {
    private frame frame ;
    private InvoiceView invoiceDialog;
    private LineView lineDialog;
    
    
    
    public ControllingClass (frame frame)
    {
    this.frame = frame;
    }
        
    // Action mehtod to handle buttons 
    @Override
    public void actionPerformed(ActionEvent e) {
        
            String action = e.getActionCommand();
            ArrayList<Header> invoices = new ArrayList<>();
            
            switch(action)
            {
                case "Load File":
                    loadFile();
                    break;
                case "Save File":
                    saveFile();
                    break;
                case "Create New Invoice":
                    createNewInvoice();
                    break;
                case "Delete Invoice":
                    deleteInvoice();
                    break;
                case "Create New Item":
                    createNewItem();
                    break;
                case "Delete Item":
                    deleteItem();
                    break;
                case "invoiceCreatedOk" :   
                    invoiceCreatedOk();
                    break;
                case "invoiceCanceld":   
                    invoiceCanceld();
                    break;
                case "lineCreatedOk":
                    lineCreating();
                    break;
                case "lineCanceld":  
                    lineCancel();
                    break;

                    
                    
            }
        } 
           
    
    // Method to update data for tables
    @Override
    public void valueChanged(ListSelectionEvent e) {
        int selectedIndex = frame.getInvoiceTable().getSelectedRow();
        if(selectedIndex != -1) {
           
            Header invoiceUse = frame.getInvoices().get(selectedIndex);
            frame.getInvoiceNum().setText(""+invoiceUse.getNumber());
            frame.getInvoiceDate().setText(invoiceUse.getDate());
            frame.getCustomerName().setText(invoiceUse.getName());
            frame.getInvoiceTotalCost().setText(""+invoiceUse.getTotalInvoice());

            LineTable linesTableModel = new LineTable(invoiceUse.getLines());
            frame.getTableLine().setModel(linesTableModel);
            linesTableModel.fireTableDataChanged();
        }
    }
    
    // Method to Load File to update and modify the data 
    private void loadFile() {
        
        JFileChooser FileChooser = new JFileChooser();
        try {
        int result = FileChooser.showOpenDialog(frame);
        if (result == JFileChooser.APPROVE_OPTION)
        {
           File headerFile = FileChooser.getSelectedFile();
           Path headerPath = Paths.get(headerFile.getAbsolutePath());
           List<String> headerLines = Files.readAllLines(headerPath);
           ArrayList<Header> invoicesArray = new ArrayList<>();
           
                for (String lineHeader : headerLines){
                    String [] headerParts = lineHeader.split(",");
                    int invoiceNumber = Integer.parseInt(headerParts[0]);
                    String invoiceDate = headerParts[1];
                    String nameCustomer = headerParts[2];

                    Header invoice = new Header(invoiceNumber, invoiceDate, nameCustomer);
                    invoicesArray.add(invoice);

                }
                
          
                
           result = FileChooser.showOpenDialog(frame);
           if (result == JFileChooser.APPROVE_OPTION){
                File linesInvoice = FileChooser.getSelectedFile();
                Path linesPath = Paths.get(linesInvoice.getAbsolutePath());
                List<String> linesParts = Files.readAllLines(linesPath);
               
                
                for (String linesInvoiceSelected : linesParts)
                {
                    String lines [] = linesInvoiceSelected.split(",");
                    int invoiceNumberOfLines = Integer.parseInt(lines[0]);
                    String nameItem = lines[1];
                    double priceItem  = Double.parseDouble(lines[2]);
                    int counter = Integer.parseInt(lines[3]);
                    Header invo = null;
                    for (Header invoice : invoicesArray){
                     if (invoice.getNumber() == invoiceNumberOfLines){

                         invo = invoice;
                         break;

                     }
                  }        
                  Line lineInvoiceLines = new Line(nameItem, priceItem, counter, invo);
                    boolean add = invo.getLines().add(lineInvoiceLines);      
            }
             
        }     
                frame.setInvoices(invoicesArray);
                HeaderTable invoiceTable = new HeaderTable(invoicesArray);
                frame.setInvoiceTableModel(invoiceTable);
                frame.getInvoiceTable().setModel(invoiceTable);
                frame.getInvoiceTableModel().fireTableDataChanged();
                       
        }
     } catch (IOException e){
        }
   }

    // Method To save the updated file when it modified and edited **
    private void saveFile() {
            
        ArrayList<Header> invoices = frame.getInvoices();
        String invoiceHeader = "";
        String invoiceLines = "";
            for (Header invoice : invoices){
                invoiceHeader += invoice.getFileCSV();
                invoiceHeader += "\n";

                for (Line line : invoice.getLines()){
                     String lineCSV = line.getFileCSV();
                     invoiceLines += lineCSV ;
                     invoiceLines += "\n"; 
              }
          }
     
        try {
            
            JFileChooser fc = new JFileChooser(); 
            int result = fc.showSaveDialog(frame);
                if (result == JFileChooser.APPROVE_OPTION){ 
                    File headerFileInvoice = fc.getSelectedFile(); 
                    FileWriter  writeFile = new FileWriter(headerFileInvoice); 
                    writeFile.write(invoiceHeader); 
                    writeFile.flush(); 
                    writeFile.close();
                    result = fc.showSaveDialog(frame);
                    
                        if(result == JFileChooser.APPROVE_OPTION){
                            File invoicesLine = fc.getSelectedFile(); 
                            FileWriter writeFileLine = new FileWriter(invoicesLine);
                            writeFileLine.write(invoiceLines); 
                            writeFileLine.flush(); 
                            writeFileLine.close(); 

                    }
            }          
        } catch (HeadlessException | IOException e) {
            }
    }
    
    //Method for creating dialog to make user to create new invoice
    private void createNewInvoice() {
                invoiceDialog = new InvoiceView(frame);
                invoiceDialog.setLocation(630,300);
                invoiceDialog.setVisible(true);
                
          }

    //Method for deleting an invoice
    private void deleteInvoice() {
        int selectedRow = frame.getInvoiceTable().getSelectedRow();
            if (selectedRow != -1 ){
                frame.getInvoices().remove(selectedRow);
                frame.getInvoiceTableModel().fireTableDataChanged();
            }
         }

     //Method for show adding an item to an invoice dialog
    private void createNewItem() {
            lineDialog = new LineView(frame);
            lineDialog.setLocation(625,300);
            lineDialog.setVisible(true);

          }

      //Method for deleting an item to an invoice
    private void deleteItem() {
        int selectedInvoice = frame.getInvoiceTable().getSelectedRow();
        int selectedLine = frame.getTableLine().getSelectedRow();
        
            if (selectedInvoice != -1 && selectedLine != -1 ){
                Header invoice = frame.getInvoices().get(selectedInvoice);
                invoice.getLines().remove(selectedLine);
                LineTable invoiceLine = new LineTable(invoice.getLines());
                frame.getTableLine().setModel(invoiceLine);
                invoiceLine.fireTableDataChanged();           
                frame.getInvoiceTableModel().fireTableDataChanged(); 
            }
         }
  //Method for adding process an item to an invoice
    private void invoiceCreatedOk() {
        String date = invoiceDialog.getInvoiceDataField().getText();
        String customerName ;
        int number = frame.getNextInvoiceNumber();
        String firstLetter = invoiceDialog.getCustomerNameField().getText().substring(0, 1);
        String remainingLetters = invoiceDialog.getCustomerNameField().getText().substring(1, invoiceDialog.getCustomerNameField().getText().length());
        customerName = firstLetter.toUpperCase() + remainingLetters;
        String msg = "Invoice Created Successfully";
        String filled = "Invoices Is Empty";
        Header newInvoice  = new Header(number, date, customerName);
        
        frame.getInvoices().add(newInvoice);  
        JOptionPane.showMessageDialog(frame, msg,"Status",JOptionPane.INFORMATION_MESSAGE);   
        frame.getInvoiceTableModel().fireTableDataChanged();
        
        invoiceDialog.setVisible(false);
        invoiceDialog.dispose();
        invoiceDialog = null ;

        
    }
// Method to close the creating invoice dialog
    private void invoiceCanceld() {
            invoiceDialog.setVisible(false);
            invoiceDialog.dispose();
            invoiceDialog = null ;
    }
    
// Method to create new item to the invoice
    private void lineCreating() {
        String itemName = lineDialog.getItemNameField().getText();
        String countItem = lineDialog.getItemCountField().getText();
        String itemPrice = lineDialog.getItemPriceField().getText();
        String firstLetter = lineDialog.getItemNameField().getText().substring(0, 1);
        String remainingLetters = lineDialog.getItemNameField().getText().substring(1, lineDialog.getItemNameField().getText().length());
        itemName = firstLetter.toUpperCase() + remainingLetters ;
        
        
        int count = Integer.parseInt(countItem);
        double price = Double.parseDouble(itemPrice);
        int selectedInvoiceNumber = frame.getInvoiceTable().getSelectedRow();
            if (selectedInvoiceNumber != -1) {
                    String outputMsg = "Added";
                    Header invoice = frame.getInvoices().get(selectedInvoiceNumber);
                    Line line = new Line(itemName, price, count, invoice);

                    JOptionPane.showMessageDialog(frame, outputMsg,"Status",JOptionPane.INFORMATION_MESSAGE);
                    LineTable lineTable = (LineTable) frame.getTableLine().getModel();
                    lineTable.getLines().add(line);
                    lineTable.fireTableDataChanged();
                    frame.getInvoiceTableModel().fireTableDataChanged();
                    
        }
        
        lineDialog.setVisible(false);
        lineDialog.dispose();
        lineDialog = null ;
   
    }

    // Method to close adding item to invoice dialog
    private void lineCancel() {
        lineDialog.setVisible(false);
        lineDialog.dispose();
        lineDialog = null ;
              
    }

}