
package view;

import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

//

public class InvoiceView extends JDialog {
    private final JTextField NameField;
    private final JTextField DataField;
    private final JLabel NameLable;
    private final JLabel DataLable;
    private final JButton createBtn;
    private final JButton cancelBtn;


    public InvoiceView(frame frame) {
        NameLable = new JLabel("Customer Name:");
        NameField = new JTextField(20);
        DataLable = new JLabel("Invoice Date:");
        DataField = new JTextField(20);
        createBtn = new JButton("OK");
        cancelBtn = new JButton("Cancel");
        
        
        // set acction command text
        createBtn.setActionCommand("invoiceCreatedOk");
        cancelBtn.setActionCommand("invoiceCanceld");
        
        // set color to buttons
        createBtn.setBackground(Color.green);
        cancelBtn.setBackground(Color.red);
        
        // For Know how form of enter date
        DataField.setText("01-01-2022");
        
        
  
        
        
        // but controller as a acction listener 
        createBtn.addActionListener(frame.getController());
        cancelBtn.addActionListener(frame.getController());
        setLayout(new GridLayout(3, 2));
        
        add(DataLable);
        add(DataField);
        add(NameLable);
        add(NameField);
        add(createBtn);
        add(cancelBtn);
        
        pack();
        
    }
    
      public JTextField getInvoiceDataField() {
        return DataField;
    }

    public JTextField getCustomerNameField() {
        return NameField;
    }

  
    
}
