/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package paquetes;

import conexion.conectar;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Usuario
 */
public class nplan extends javax.swing.JInternalFrame {

   ResultSet rs;
    DefaultTableModel modelo;
    
    public nplan() {
        initComponents();
        txtid.setVisible(false);
        mostrarplan();
        btneliminar.setEnabled(false);
        
    }

    void mostrarplan(){
   String [] titulos= {"ID","NUMERO","TITULO"};
   modelo=new  DefaultTableModel(null,titulos)
   
   {
   
       public boolean isCellEditable(int row, int column){
              return false; 
                }
   
   };
   
   String datos []= new String[5];
   String sql="SELECT * FROM planestudio"; 
        try {
            Statement st = cn.createStatement();
             rs = st.executeQuery(sql);
            while(rs.next())
            {
                datos[0]=rs.getString("idplanestudio");
                datos[1]=rs.getString("numero");
                datos[2]=rs.getString("titulo");
                
                modelo.addRow(datos);
            }
            tabla3.setModel(modelo);
            tabla3.getColumnModel().getColumn(0).setMaxWidth(0);
            tabla3.getColumnModel().getColumn(0).setMinWidth(0);
            tabla3.getColumnModel().getColumn(0).setPreferredWidth(0);

        } catch (SQLException ex) {
            Logger.getLogger(nusuario.class.getName()).log(Level.SEVERE, null, ex);
        }
   }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtnp = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txttp = new javax.swing.JTextField();
        txtid = new javax.swing.JTextField();
        ingresar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla3 = new javax.swing.JTable();
        jButton3 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        btneliminar = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Plan de Estudio"));

        jLabel1.setText("Plan de Estudio N° :");

        jLabel2.setText("Titulo: ");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txtnp, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addComponent(txtid, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 236, Short.MAX_VALUE))
                    .addComponent(txttp))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtnp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txttp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        ingresar.setText("Ingresar ");
        ingresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ingresarActionPerformed(evt);
            }
        });

        tabla3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tabla3.getTableHeader().setResizingAllowed(false);
        tabla3.getTableHeader().setReorderingAllowed(false);
        tabla3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabla3MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabla3);

        jButton3.setText("Actualizar Plan");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton1.setText("Limpiar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        btneliminar.setText("Eliminar Plan");
        btneliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btneliminarActionPerformed(evt);
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
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(ingresar)
                        .addGap(37, 37, 37)
                        .addComponent(jButton3)
                        .addGap(48, 48, 48)
                        .addComponent(jButton1)
                        .addGap(34, 34, 34)
                        .addComponent(btneliminar)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ingresar)
                    .addComponent(jButton3)
                    .addComponent(jButton1)
                    .addComponent(btneliminar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(38, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ingresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ingresarActionPerformed
        // ingresar plan
        String ins="INSERT INTO planestudio (numero, titulo) VALUES(?,?)";
        try {
            PreparedStatement pst = cn.prepareStatement(ins);
            pst.setInt(1, Integer.parseInt(txtnp.getText()));
            pst.setString(2, txttp.getText());
             int n= pst.executeUpdate();
            if(n>0)
            {
                JOptionPane.showMessageDialog(this, "Se Ingresor Plan de Estudio Correctamente");
                mostrarplan();
                txtnp.setText(null);
                txttp.setText(null);
            }
            else
            {
                 JOptionPane.showMessageDialog(this, "Error al Ingresar");
            }
            
            
        } catch (Exception e) {
        }
    }//GEN-LAST:event_ingresarActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        try {
            
            PreparedStatement pps =cn.prepareStatement("update planestudio set numero='"+txtnp.getText()+"',titulo='"+txttp.getText()+"' where idplanestudio='"+txtid.getText()+"'");
        pps.executeUpdate();
        JOptionPane.showMessageDialog(null, "Datos Actualizados");
        txtnp.setText("");
        txttp.setText("");
        txtid.setText("");
        mostrarplan();
        ingresar.setEnabled(true);
        } catch (SQLException ex) {
            Logger.getLogger(nplan.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void tabla3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabla3MouseClicked
        // TODO add your handling code here:
        ingresar.setEnabled(false);
        btneliminar.setEnabled(true);
        int eli=tabla3.getSelectedRow();
        txtid.setText(String.valueOf(tabla3.getValueAt(eli, 0)));    
         txtnp.setText(String.valueOf(tabla3.getValueAt(eli, 1)));
         txttp.setText(String.valueOf(tabla3.getValueAt(eli, 2)));
    }//GEN-LAST:event_tabla3MouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
         txtid.setText("");    
         txtnp.setText("");
         txttp.setText("");
         ingresar.setEnabled(true);
         btneliminar.setEnabled(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btneliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btneliminarActionPerformed
        // TODO add your handling code here:
         int eli=tabla3.getSelectedRow();
       String valor =tabla3.getValueAt(eli, 0).toString();
         if(eli>=0){
             try {
               
            com.mysql.jdbc.PreparedStatement pps =(com.mysql.jdbc.PreparedStatement) cn.prepareStatement("delete from planestudio where idplanestudio='"+valor+"'");
            pps.executeUpdate();
            
             JOptionPane.showMessageDialog(null, "Plan de Estudio Eliminado");
             mostrarplan();
             } catch (Exception e) {
             }          
        }
        else{
            JOptionPane.showMessageDialog(null, "no hay mas que eliminar");
        }
         txtid.setText("");    
         txtnp.setText("");
         txttp.setText("");
    }//GEN-LAST:event_btneliminarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btneliminar;
    private javax.swing.JButton ingresar;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabla3;
    private javax.swing.JTextField txtid;
    private javax.swing.JTextField txtnp;
    private javax.swing.JTextField txttp;
    // End of variables declaration//GEN-END:variables
 conectar cc=new conectar();
   Connection cn=cc.conexion();
}
