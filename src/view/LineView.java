package view;

import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class LineView extends JDialog{
    private final JTextField NameField;
    private final JTextField CountField;
    private final JTextField PriceField;
    private final JLabel itemName;
    private final JLabel itemCount;
    private final JLabel itemPrice;
    private final JButton createBtn;
    private final JButton cancelBtn;
    
    public LineView(frame frame) {
        NameField = new JTextField(20);
        itemName = new JLabel("Item_Name");
        
        CountField = new JTextField(20);
        itemCount = new JLabel("Item_Count");
        
        PriceField = new JTextField(20);
        itemPrice = new JLabel("Item_Price");
        
        createBtn = new JButton("OK");
        cancelBtn = new JButton("Cancel");
        
        createBtn.setBackground(Color.green);
        cancelBtn.setBackground(Color.red);
        
        
        createBtn.setActionCommand("lineCreatedOk");
        cancelBtn.setActionCommand("lineCanceld");
        
        createBtn.addActionListener(frame.getController());
        cancelBtn.addActionListener(frame.getController());
        setLayout(new GridLayout(4, 2));
        
        add(itemName);
        add(NameField);
        add(itemCount);
        add(CountField);
        add(itemPrice);
        add(PriceField);
        add(createBtn);
        add(cancelBtn);
        
        pack();
    }

    public JTextField getItemNameField() {
        return NameField;
    }
    
    public JTextField getItemPriceField() {
        return PriceField;
    }

    public JTextField getItemCountField() {
        return CountField;
    }

}
