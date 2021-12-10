
package tesis;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;


public class render extends DefaultTableCellRenderer{

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, 
    boolean isSelected, boolean hasFocus, int row, int column) {
        
        if (value instanceof JButton){
        JButton btn = (JButton)value;
        return btn;
        }
        if(value instanceof JCheckBox){
        JCheckBox jc= (JCheckBox) value;
        return jc;
        }
        if(value instanceof JComboBox){
            JComboBox combo =(JComboBox) value;
            return combo;
        }
        
        
        
        return super.getTableCellRendererComponent(table, value, isSelected, 
                hasFocus, row, column);
    }
    
    
}
