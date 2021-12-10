
package paquetes;

import conexion.conectar;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


public class distribucioncurricular extends javax.swing.JInternalFrame {
ResultSet rs;
DefaultTableModel modelo;
DefaultTableModel modelo1;

    public distribucioncurricular() {
        initComponents();
        curso();
        cargarplan();
        txtidnivel.setVisible(false);
        txtidcurso.setVisible(false);
        txtida.setVisible(false);
        btnEliminar.setEnabled(false);
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
 void curso(){
  
     try {
                PreparedStatement pps=cn.prepareStatement("SELECT Descripcion FROM curso");
                rs   = pps.executeQuery();
      while (rs.next()) {
      this.combocursoaño.addItem(rs.getString("Descripcion"));
      }
                 } catch (Exception e) {
            } 

}
 
 void asignaturasp1(String valor){
   String [] titulos= {"ID","ASIGNATURA"};
   modelo=new  DefaultTableModel(null,titulos)  
   
   {
   
       public boolean isCellEditable(int row, int column){
              return false; 
                }
   
   };

   String datos []= new String[2];
   String sql="SELECT asignatura.idasignatura, asignatura.nombre FROM asignatura, planestudio where asignatura.idplanestudio=planestudio.idplanestudio and asignatura.idplanestudio='"+valor+"'"; 
        try {
            Statement st = cn.createStatement();
             rs = st.executeQuery(sql);
            while(rs.next())
            {
                datos[0]=rs.getString("idasignatura");
                datos[1]=rs.getString("nombre");
                
                modelo.addRow(datos);
            }
            jTable1.setModel(modelo);
            
            jTable1.getColumnModel().getColumn(0).setMaxWidth(0);
            jTable1.getColumnModel().getColumn(0).setMinWidth(0);
            jTable1.getColumnModel().getColumn(0).setPreferredWidth(0); 
            
        } catch (SQLException ex) {
            Logger.getLogger(distribucioncurricular.class.getName()).log(Level.SEVERE, null, ex);
        }
  
  
   }
   void asigcurso(String valor){
   String [] titulos= {"ID","ASIGNATURA"};
   modelo1=new  DefaultTableModel(null,titulos)
           {
   
       public boolean isCellEditable(int row, int column){
              return false; 
                }
   
   };
   String datos []= new String[2];
   String sql="SELECT asignacion_materias.idasignacion_materias, asignatura.nombre FROM asignacion_materias, curso, asignatura where asignacion_materias.idasignatura=asignatura.idasignatura and asignacion_materias.idcurso=curso.idcurso and asignacion_materias.idcurso='"+valor+"'"; 
        try {
            Statement st = cn.createStatement();
             rs = st.executeQuery(sql);
            while(rs.next())
            {
                datos[0]=rs.getString("idasignacion_materias");
                datos[1]=rs.getString("nombre");
                
                modelo1.addRow(datos);
            }
            jTable2.setModel(modelo1);
            
            jTable2.getColumnModel().getColumn(0).setMaxWidth(0);
            jTable2.getColumnModel().getColumn(0).setMinWidth(0);
            jTable2.getColumnModel().getColumn(0).setPreferredWidth(0); 
        } catch (SQLException ex) {
            Logger.getLogger(distribucioncurricular.class.getName()).log(Level.SEVERE, null, ex);
        }
  
  
   }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        combocursoaño = new javax.swing.JComboBox();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox();
        txtidnivel = new javax.swing.JTextField();
        txtidcurso = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txta = new javax.swing.JTextField();
        txtida = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        lcurso = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setTitle("DISTRIBUCION CURRICULAR");

        jLabel1.setText("Curso/Año:");

        combocursoaño.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccione Curso/Año" }));
        combocursoaño.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                combocursoañoItemStateChanged(evt);
            }
        });
        combocursoaño.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                combocursoañoActionPerformed(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTable1.getTableHeader().setResizingAllowed(false);
        jTable1.getTableHeader().setReorderingAllowed(false);
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTable1);

        jLabel2.setText("Plan de Estudio:");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Sellecione Plan de Estudio" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jLabel3.setText("Asignatura:");

        jButton1.setText("Asignar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTable2.getTableHeader().setResizingAllowed(false);
        jTable2.getTableHeader().setReorderingAllowed(false);
        jTable2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable2MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable2);

        jButton2.setText("Limpiar Campos");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel4.setText("Asignaturas Agregadas a");

        lcurso.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        jButton3.setText("Salir");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton2)
                        .addGap(161, 161, 161)
                        .addComponent(btnEliminar))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 407, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txtidnivel, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel3))
                                .addGap(30, 30, 30)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(combocursoaño, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(txtidcurso, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txtida, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(txta, javax.swing.GroupLayout.PREFERRED_SIZE, 370, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(27, 27, 27)
                                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lcurso, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jButton3))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtidnivel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(combocursoaño, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtidcurso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lcurso, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(btnEliminar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                .addComponent(jButton3))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
         try {
            PreparedStatement pps=cn.prepareStatement ("select idplanestudio from planestudio where titulo=?");
             pps.setString(1, jComboBox1.getSelectedItem().toString());
            // jComboBox1.setEnabled(false);
              
            ResultSet resultSet = pps.executeQuery();
            if (resultSet.next()) {
               txtidnivel.setText(resultSet.getString("idplanestudio"));
               asignaturasp1(txtidnivel.getText());
               
            }
        }catch (Exception e) {
        }
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void combocursoañoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combocursoañoActionPerformed
        // TODO add your handling code here:
         try {
            PreparedStatement pps=cn.prepareStatement ("select idcurso from curso where Descripcion=?");
             pps.setString(1, combocursoaño.getSelectedItem().toString());
            // jComboBox1.setEnabled(false);
              
            ResultSet resultSet = pps.executeQuery();
            if (resultSet.next()) {
               txtidcurso.setText(resultSet.getString("idcurso"));
               asigcurso(txtidcurso.getText());
               
            }
        }catch (Exception e) {
        }
    }//GEN-LAST:event_combocursoañoActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:
         int eli=jTable1.getSelectedRow();
        txtida.setText(String.valueOf(jTable1.getValueAt(eli, 0)));
        txta.setText(String.valueOf(jTable1.getValueAt(eli, 1)));
    }//GEN-LAST:event_jTable1MouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        String ins="INSERT INTO asignacion_materias (idcurso, idasignatura) VALUES(?,?)";
        try {
            PreparedStatement pst = cn.prepareStatement(ins);
            pst.setInt(1, Integer.parseInt(txtidcurso.getText()));
            pst.setString(2, txtida.getText());
             int n= pst.executeUpdate();
            if(n>0)
            {
                JOptionPane.showMessageDialog(this, "Se Ingreso Asignatura Correctamente");
                asigcurso(txtidcurso.getText());
                txta.setText(null);
                
            }
            else
            {
//                 JOptionPane.showMessageDialog(this, "Error al Ingresar");
                
            }
            
            
        } catch (Exception e) {
           JOptionPane.showMessageDialog(this, "Error al Agregar Asignatura");
           txtidnivel.setText("");
            txtida.setText("");
            txtidcurso.setText("");
            txta.setText("");
            modelo.setRowCount(0);
            modelo1.setRowCount(0);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        jComboBox1.setSelectedIndex(0);
        combocursoaño.setSelectedIndex(0);
        txtidnivel.setText("");
        txtida.setText("");
        txtidcurso.setText("");
        txta.setText("");
        modelo.setRowCount(0);
        modelo1.setRowCount(0);
                
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jTable2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MouseClicked
        // TODO add your handling code here:
         btnEliminar.setEnabled(true);
    }//GEN-LAST:event_jTable2MouseClicked

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        // TODO add your handling code here:
        int eli=jTable2.getSelectedRow();
        String valor =jTable2.getValueAt(eli, 0).toString();
         if(eli>=0){int bool = JOptionPane.showConfirmDialog(null,"ASIGNATURA:"+" "+jTable2.getValueAt(eli, 1).toString()+"\n"+"CURSO/AÑO:"+" "+combocursoaño.getSelectedItem(),"Eliminar Asignatura de CURSO/AÑO",JOptionPane.YES_NO_OPTION);
            if(bool == JOptionPane.YES_OPTION){
             try {
               
            com.mysql.jdbc.PreparedStatement pps =(com.mysql.jdbc.PreparedStatement) cn.prepareStatement("delete from asignacion_materias where idasignacion_materias='"+valor+"'");
            pps.executeUpdate();
            
             JOptionPane.showMessageDialog(null, "Asignatura Quitada Correctamente");
             asigcurso(txtidcurso.getText());
             } catch (Exception e) {
             }          
        }
         }
        else{
            JOptionPane.showMessageDialog(null, "No Hay Asignatura a Quitar");
        }
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void combocursoañoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_combocursoañoItemStateChanged
        // TODO add your handling code here:
        lcurso.setText(combocursoaño.getSelectedItem().toString());
    }//GEN-LAST:event_combocursoañoItemStateChanged

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_jButton3ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEliminar;
    private javax.swing.JComboBox combocursoaño;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JLabel lcurso;
    private javax.swing.JTextField txta;
    private javax.swing.JTextField txtida;
    private javax.swing.JTextField txtidcurso;
    private javax.swing.JTextField txtidnivel;
    // End of variables declaration//GEN-END:variables
conectar cc=new conectar();
   Connection cn=cc.conexion();
}
