
package paquetes;
import java.sql.Statement;
import conexion.conectar;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


public class cargaasignaturas extends javax.swing.JInternalFrame {
 ResultSet rs;
 DefaultTableModel modelo;   
    
 public cargaasignaturas() {
        initComponents();
        cargarplan();
        idplan.setVisible(false);
        btneliminar.setEnabled(false);
        btnmodificar.setEnabled(false);
        
    }
void cargarplan (){

     try {
                PreparedStatement pps=cn.prepareStatement("SELECT * FROM planestudio ");
                rs   = pps.executeQuery();
      while (rs.next()) {
      this.jComboBox1.addItem(rs.getString("titulo"));
      }
                 } catch (Exception e) {
            } 

}
 
void asignaturasp1(String valor){
   String [] titulos= {"ID","NOMBRE","PLAN DE ESTUDIO"};
   modelo=new  DefaultTableModel(null,titulos)
           
           {
   
       public boolean isCellEditable(int row, int column){
              return false; 
                }
   
   };
           
   String datos []= new String[3];
   String sql="SELECT asignatura.idasignatura, asignatura.nombre, planestudio.titulo FROM asignatura, planestudio where asignatura.idplanestudio=planestudio.idplanestudio and asignatura.idplanestudio='"+valor+"'"; 
        try {
            Statement st = cn.createStatement();
             rs = st.executeQuery(sql);
            while(rs.next())
            {
                datos[0]=rs.getString("idasignatura");
                datos[1]=rs.getString("nombre");
                datos[2]= rs.getString("titulo");
                modelo.addRow(datos);
            }
            tabla4.setModel(modelo);
            tabla4.getColumnModel().getColumn(0).setMaxWidth(0);
             tabla4.getColumnModel().getColumn(0).setMinWidth(0);
             tabla4.getColumnModel().getColumn(0).setPreferredWidth(0);

        } catch (SQLException ex) {
            Logger.getLogger(cargaasignaturas.class.getName()).log(Level.SEVERE, null, ex);
        }
  
  
   }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox();
        idplan = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtasignatura = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        btnmodificar = new javax.swing.JButton();
        btneliminar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla4 = new javax.swing.JTable();

        setClosable(true);
        setIconifiable(true);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Ingresar Asignaturas"));

        jLabel1.setText("Plan de Estudio:");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccionar Plan de Estudio" }));
        jComboBox1.setToolTipText("");
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jLabel2.setText("Nombre Asignatura:");

        jButton1.setText("Ingresar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Limpiar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        btnmodificar.setText("Modificar");
        btnmodificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnmodificarActionPerformed(evt);
            }
        });

        btneliminar.setText("Eliminar");
        btneliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btneliminarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(idplan, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 358, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(86, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtasignatura)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btnmodificar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btneliminar)
                                .addGap(38, 38, 38)
                                .addComponent(jButton1)
                                .addContainerGap())))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(idplan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtasignatura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(btnmodificar)
                    .addComponent(btneliminar))
                .addContainerGap())
        );

        tabla4.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tabla4.getTableHeader().setResizingAllowed(false);
        tabla4.getTableHeader().setReorderingAllowed(false);
        tabla4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabla4MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabla4);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 251, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // mostrar id plan
         try {
            PreparedStatement pps=cn.prepareStatement ("select idplanestudio from planestudio where titulo=?");
             pps.setString(1, jComboBox1.getSelectedItem().toString());
             jComboBox1.setEnabled(false);
              
            ResultSet resultSet = pps.executeQuery();
            if (resultSet.next()) {
               idplan.setText(resultSet.getString("idplanestudio"));
               asignaturasp1(idplan.getText());
               
            }
        }catch (Exception e) {
        }
       
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        jComboBox1.setSelectedIndex(0);
        jComboBox1.setEnabled(true);
        idplan.setText("");
        txtasignatura.setText("");
        jButton1.setEnabled(true);
        modelo.setRowCount(0);
  
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // ingresar asignaturas
        String ins="INSERT INTO asignatura (nombre, idplanestudio) VALUES(?,?)";
        try {
            PreparedStatement pst = cn.prepareStatement(ins);
            pst.setString(1, txtasignatura.getText());
            pst.setString(2, idplan.getText());
             int n= pst.executeUpdate();
            if(n>0){
            JOptionPane.showMessageDialog(this, "Asignatura Ingresada");
            txtasignatura.setText(null);
            asignaturasp1(idplan.getText());
            }
             else
            {
                 JOptionPane.showMessageDialog(this, "Error");
            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void tabla4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabla4MouseClicked
        // TODO add your handling code here:
        int eli=tabla4.getSelectedRow();
         if(eli>=0){
         txtasignatura.setText(tabla4.getValueAt(eli, 1).toString());    
         }else{
         JOptionPane.showMessageDialog(null, "Fila no Sellecionada");
         }
        jButton1.setEnabled(false);
        btneliminar.setEnabled(true);
        btnmodificar.setEnabled(true);
    }//GEN-LAST:event_tabla4MouseClicked

    private void btnmodificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnmodificarActionPerformed
        // TODO add your handling code here:
        int eli=tabla4.getSelectedRow();
        String valor =tabla4.getValueAt(eli, 0).toString();
         if(eli>=0){
             try {
               
            com.mysql.jdbc.PreparedStatement pps =
            (com.mysql.jdbc.PreparedStatement) cn.prepareStatement("update asignatura set nombre='"+txtasignatura.getText()+"', idplanestudio='"+idplan.getText()+"' where idasignatura='"+valor+"'");
            pps.executeUpdate();
            
             JOptionPane.showMessageDialog(null, "Asignatura Modificada");
             asignaturasp1(idplan.getText());
             } catch (Exception e) {
             }          
        }
        else{
            JOptionPane.showMessageDialog(null, "Asignatura no Modificada");
        }
        
    }//GEN-LAST:event_btnmodificarActionPerformed

    private void btneliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btneliminarActionPerformed
        // TODO add your handling code here:
        int eli=tabla4.getSelectedRow();
        String valor =tabla4.getValueAt(eli, 0).toString();
         if(eli>=0){int bool = JOptionPane.showConfirmDialog(null,"ASIGNATURA:"+" "+txtasignatura.getText()+"\n"+"PLAN DE ESTUDIO:"+" "+jComboBox1.getSelectedItem(),"Eliminar Asignatura",JOptionPane.YES_NO_OPTION);
            if(bool == JOptionPane.YES_OPTION){
             try {
               
            com.mysql.jdbc.PreparedStatement pps =(com.mysql.jdbc.PreparedStatement) cn.prepareStatement("delete from asignatura where idasignatura='"+valor+"'");
            pps.executeUpdate();
            
             JOptionPane.showMessageDialog(null, "Asignatura Eliminada");
             asignaturasp1(idplan.getText());
             } catch (Exception e) {
             }          
        }
         }
        else{
            JOptionPane.showMessageDialog(null, "Sellecionar Asignatura para Eliminar");
        }
             jComboBox1.setSelectedIndex(0);
        jComboBox1.setEnabled(true);
        idplan.setText("");
        txtasignatura.setText("");
        jButton1.setEnabled(true);
        modelo.setRowCount(0);        
    }//GEN-LAST:event_btneliminarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btneliminar;
    private javax.swing.JButton btnmodificar;
    private javax.swing.JTextField idplan;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabla4;
    private javax.swing.JTextField txtasignatura;
    // End of variables declaration//GEN-END:variables
conectar cc=new conectar();
Connection cn=cc.conexion();
}
