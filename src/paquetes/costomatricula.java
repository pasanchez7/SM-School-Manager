
package paquetes;

import com.mysql.jdbc.PreparedStatement;
import conexion.conectar;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


public class costomatricula extends javax.swing.JInternalFrame {

    ResultSet rs;
    DefaultTableModel modelo;
    
    public costomatricula() {
        initComponents();
        txtid.setVisible(false);
        mostrarmontomatricula();
    }

   void mostrarmontomatricula(){
   String [] titulos= {"ID","DESCRIPCION","MONTO MATRICULA"};
   modelo=new  DefaultTableModel(null,titulos)
   {
   
       public boolean isCellEditable(int row, int column){
              return false; 
                }
   
   };  
   
   
   
   String datos []= new String[5];
   String sql="SELECT * FROM montomatricula"; 
        try {
            Statement st = cn.createStatement();
             rs = st.executeQuery(sql);
            while(rs.next())
            {
                datos[0]=rs.getString("idmontomatricula");
                datos[1]=rs.getString("descripcion");
                datos[2]=rs.getString("monto");
                
                modelo.addRow(datos);

            }
            tabla2.setModel(modelo);
            tabla2.getColumnModel().getColumn(0).setMaxWidth(0);
             tabla2.getColumnModel().getColumn(0).setMinWidth(0);
             tabla2.getColumnModel().getColumn(0).setPreferredWidth(0);
             
        } catch (SQLException ex) {
            Logger.getLogger(nusuario.class.getName()).log(Level.SEVERE, null, ex);
        }
   }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtdm = new javax.swing.JTextField();
        txtm = new javax.swing.JTextField();
        txtid = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla2 = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        btnlimpiar = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Costo de Matricula"));

        jLabel1.setText("Descripción:");

        jLabel2.setText("Monto:");

        txtm.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtmKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtdm)
                    .addComponent(txtm)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txtid, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 222, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtdm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jButton1.setText("Ingresar Monto");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        tabla2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tabla2.getTableHeader().setResizingAllowed(false);
        tabla2.getTableHeader().setReorderingAllowed(false);
        tabla2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabla2MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabla2);

        jButton2.setText("Eliminar Monto");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton4.setText("Actualizar Monto");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        btnlimpiar.setText("Limpiar");
        btnlimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnlimpiarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnlimpiar)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton4)
                        .addGap(56, 56, 56)
                        .addComponent(jButton2))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(14, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 42, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(jButton4)
                    .addComponent(btnlimpiar))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
//         String ins="INSERT INTO montomatricula (descripcion, monto) VALUES(?,?)";
//         try {
//            java.sql.PreparedStatement pst = cn.prepareStatement(ins);
//            pst.setString(1, txtdm.getText());
//            pst.setString(2, txtm.getText()); 
//            int n= pst.executeUpdate();
//            if(n>0)
//            {
//                JOptionPane.showMessageDialog(this, "Monto ingresado Correctamente");
//                mostrarmontomatricula();
//                txtdm.setText(null);
//                txtm.setText(null);
//                
//            }
//            else
//            {
//                 JOptionPane.showMessageDialog(this, "Error");
//            }
//            
//            
//        } catch (SQLException ex) {
//            Logger.getLogger(costomatricula.class.getName()).log(Level.SEVERE, null, ex);
//        }
String descripcion = txtdm.getText();
String monto=txtm.getText();
    
    if (descripcion.trim().equals("")) {
        JOptionPane.showMessageDialog(
         null, "No se puede dejar espacio en blanco", "Error al cargar Descripción",JOptionPane.ERROR_MESSAGE);
        return;
    }
     if (monto.trim().equals("")) {
        JOptionPane.showMessageDialog(
         null, "No se puede dejar espacio en blanco", "Error al cargar Monto",JOptionPane.ERROR_MESSAGE);
        return;
    }
    String ins="INSERT INTO montomatricula (descripcion, monto) VALUES(?,?)";;

    try {
        java.sql.PreparedStatement pst = cn.prepareStatement(ins);
//        pst.setString(1, tipoDeUsuario);
        pst.setString(1, descripcion);
        pst.setString(2, monto);
        int n= pst.executeUpdate();
        if (n>0) {
            JOptionPane.showMessageDialog(this, "Monto Ingresado Correctamente");
           mostrarmontomatricula();
                txtdm.setText(null);
                txtm.setText(null);
        } else {
            JOptionPane.showMessageDialog(this, "Error");
        }      
    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(this, ex);
    }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // eliminar monto
        int eli=tabla2.getSelectedRow();
        String valor =tabla2.getValueAt(eli, 0).toString();
         if(eli>=0){
             try {
               
            PreparedStatement pps =(PreparedStatement) cn.prepareStatement("delete from montomatricula where idmontomatricula='"+valor+"'");
            pps.executeUpdate();
            
             JOptionPane.showMessageDialog(null, "Dato Eliminado");
             mostrarmontomatricula();
             } catch (Exception e) {
             }          
        }
        else{
            JOptionPane.showMessageDialog(null, "no hay mas que eliminar");
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        try {
            // actualizar monto
        PreparedStatement pps =(PreparedStatement) cn.prepareStatement("update montomatricula set descripcion='"+txtdm.getText()+"',monto='"+txtm.getText()+"' where idmontomatricula='"+txtid.getText()+"'");
        pps.executeUpdate();
        JOptionPane.showMessageDialog(null, "Datos Actualizados");
        txtdm.setText("");
        txtm.setText("");
        txtid.setText("");
        mostrarmontomatricula();
        jButton1.setEnabled(true);
        } catch (SQLException ex) {
            Logger.getLogger(costomatricula.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }//GEN-LAST:event_jButton4ActionPerformed

    private void txtmKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtmKeyTyped
        // TODO add your handling code here:
        char validar=evt.getKeyChar();
        
        if(Character.isLetter(validar)){
        getToolkit().beep();
        evt.consume();
        JOptionPane.showMessageDialog(rootPane, "Ingresar solo Numeros");
        }
    }//GEN-LAST:event_txtmKeyTyped

    private void tabla2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabla2MouseClicked
        // TODO add your handling code here:
        int eli=tabla2.getSelectedRow();
         if(eli>=0){
         txtid.setText(tabla2.getValueAt(eli, 0).toString());    
         txtdm.setText(tabla2.getValueAt(eli, 1).toString());
         txtm.setText(tabla2.getValueAt(eli, 2).toString());
         
         jButton1.setEnabled(false);
         }else{
         JOptionPane.showMessageDialog(null, "Fila no Sellecionada");
         }
    }//GEN-LAST:event_tabla2MouseClicked

    private void btnlimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnlimpiarActionPerformed
        // TODO add your handling code here:
        txtdm.setText("");
        txtid.setText("");
        txtm.setText("");
        jButton1.setEnabled(true);
    }//GEN-LAST:event_btnlimpiarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnlimpiar;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabla2;
    private javax.swing.JTextField txtdm;
    private javax.swing.JTextField txtid;
    private javax.swing.JTextField txtm;
    // End of variables declaration//GEN-END:variables
conectar cc=new conectar();
   Connection cn=cc.conexion();
}
