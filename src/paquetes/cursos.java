
package paquetes;

import conexion.conectar;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


public class cursos extends javax.swing.JInternalFrame {
ResultSet rs;
DefaultTableModel modelo;   
    public cursos() {
        initComponents();
        nivel();
        txtidn.setVisible(false);
        cursodivision();
        txtidcurso.setVisible(false);
        jButton4.setEnabled(false);
        turno();
        txtidturno.setVisible(false);
         btneditar.setVisible(false);
         txtidcd.setVisible(false);
    }

 void turno (){
         try {
                PreparedStatement pps=cn.prepareStatement("SELECT turno FROM turno");
                rs   = pps.executeQuery();
      while (rs.next()) {
      this.cboturno.addItem(rs.getString("turno"));
      }
                 } catch (Exception e) {
            } 
    
    }
    
 void nivel(){

     try {
                PreparedStatement pps=cn.prepareStatement("SELECT Descripcion FROM nivel");
                rs   = pps.executeQuery();
      while (rs.next()) {
      this.combonivel.addItem(rs.getString("Descripcion"));
      }
                 } catch (Exception e) {
            } 

}
 
 void vercursos(String valor){
   String [] titulos= {"ID","CURSO/AÑO","NIVEL ACADEMICO"};
   modelo=new  DefaultTableModel(null,titulos)
           
            {
   
       public boolean isCellEditable(int row, int column){
              return false; 
                }
   
   }; 
           
   String datos []= new String[3];
   String sql="SELECT curso.idcurso, curso.Descripcion, nivel.Descripcion FROM curso, nivel where curso.idnivel=nivel.idnivel and curso.idnivel='"+valor+"'"; 
        try {
            Statement st = cn.createStatement();
             rs = st.executeQuery(sql);
            while(rs.next())
            {
                datos[0]=rs.getString("idcurso");
                datos[1]=rs.getString("Descripcion");
                datos[2]= rs.getString("nivel.Descripcion");
                modelo.addRow(datos);
            }
            tabla6.setModel(modelo);
            tabla6.getColumnModel().getColumn(0).setMaxWidth(0);
            tabla6.getColumnModel().getColumn(0).setMinWidth(0);
            tabla6.getColumnModel().getColumn(0).setPreferredWidth(0);
        } catch (SQLException ex) {
            Logger.getLogger(cursos.class.getName()).log(Level.SEVERE, null, ex);
        }
  
  
   }
 
 void cursodivision (){
 String [] titulos= {"ID","CURSO/AÑO","DIVISION","TURNO"};
   modelo=new  DefaultTableModel(null,titulos)   
   
    {
   
       public boolean isCellEditable(int row, int column){
              return false; 
                }
   
   }; 
   
   String datos []= new String[4];
    String sql="SELECT cd.idcurso_division, c.Descripcion, cd.division, t.turno\n" +
"from curso c, curso_division cd, turno t where cd.idcurso=c.idcurso\n" +
"and cd.idturno=t.idturno order by c.descripcion, cd.division asc";
   try {
            Statement st = cn.createStatement();
             rs = st.executeQuery(sql);
            while(rs.next())
            {
                datos[0]=rs.getString("cd.idcurso_division");
                datos[1]=rs.getString("c.Descripcion");
                datos[2]=rs.getString("cd.division");
                datos[3]=rs.getString("t.turno");
                modelo.addRow(datos);
            }
            jTable2.setModel(modelo);
            
            jTable2.getColumnModel().getColumn(0).setMaxWidth(0);
            jTable2.getColumnModel().getColumn(0).setMinWidth(0);
            jTable2.getColumnModel().getColumn(0).setPreferredWidth(0);
        } catch (SQLException ex) {
            Logger.getLogger(cursos.class.getName()).log(Level.SEVERE, null, ex);
        }
 
 }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupMenu1 = new javax.swing.JPopupMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        combonivel = new javax.swing.JComboBox();
        txtidn = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtnc = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla6 = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        txt1 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox();
        txtidcurso = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        cboturno = new javax.swing.JComboBox<>();
        txtidturno = new javax.swing.JTextField();
        txtidcd = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        btneditar = new javax.swing.JButton();

        jMenuItem1.setText("EDITAR");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jPopupMenu1.add(jMenuItem1);

        setClosable(true);
        setResizable(true);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Gestion de Cursos/Años"));

        jLabel1.setText("Nivel Academico:");

        combonivel.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Sellecione Nivel" }));
        combonivel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                combonivelActionPerformed(evt);
            }
        });

        jLabel2.setText("Nombre Curso/Año");

        jButton1.setText("Ingresar Curso");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Nuevo");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtidn, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(txtnc, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton2)
                                .addGap(18, 18, 18)
                                .addComponent(jButton1))
                            .addComponent(combonivel, javax.swing.GroupLayout.PREFERRED_SIZE, 358, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 96, Short.MAX_VALUE)))
                .addGap(27, 27, 27))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(txtidn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(combonivel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtnc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2)
                    .addComponent(jButton1))
                .addContainerGap())
        );

        tabla6.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tabla6.getTableHeader().setResizingAllowed(false);
        tabla6.getTableHeader().setReorderingAllowed(false);
        tabla6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabla6MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabla6);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Agregar Curso/División"));

        jLabel3.setText("Curso/Division:");

        jLabel4.setText("División:");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z" }));

        jLabel5.setText("Turno:");

        cboturno.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Sellecione Turno" }));
        cboturno.setComponentPopupMenu(jPopupMenu1);
        cboturno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboturnoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txt1, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtidcurso, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cboturno, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtidturno, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtidcd, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txt1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtidcurso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(cboturno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtidturno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtidcd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(13, Short.MAX_VALUE))
        );

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
        jScrollPane2.setViewportView(jTable2);

        jButton4.setText("Eliminar CURSO/DIVISION");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setText("Salir");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton3.setText("Crear Curso/División");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        btneditar.setText("Editar CURSO/DIVISION");
        btneditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btneditarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jButton5)
                        .addGap(51, 51, 51)
                        .addComponent(jButton4)
                        .addGap(33, 33, 33)
                        .addComponent(btneditar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton3))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap(16, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton5)
                        .addComponent(jButton4)
                        .addComponent(btneditar))
                    .addComponent(jButton3))
                .addContainerGap(40, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void combonivelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combonivelActionPerformed
        // TODO add your handling code here:
         try {
            PreparedStatement pps=cn.prepareStatement ("select idnivel from nivel where Descripcion=?");
             pps.setString(1, combonivel.getSelectedItem().toString());
             combonivel.setEnabled(false);
              
            ResultSet resultSet = pps.executeQuery();
            if (resultSet.next()) {
               txtidn.setText(resultSet.getString("idnivel"));
             vercursos(txtidn.getText());
               
            }
        }catch (Exception e) {
        }
        
    }//GEN-LAST:event_combonivelActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        String ins="INSERT INTO curso  (Descripcion, idnivel) VALUES(?,?)";
        try {
            PreparedStatement pst = cn.prepareStatement(ins);
            pst.setString(1, txtnc.getText());
            pst.setString(2,txtidn.getText());
             int n= pst.executeUpdate();
            if(n>0){
            JOptionPane.showMessageDialog(this, "Curso Ingresado Correctamente");
            txtnc.setText(null);
            vercursos(ins);
            }
             else
            {
                 JOptionPane.showMessageDialog(this, "Error");
            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
         combonivel.setSelectedIndex(0);
        combonivel.setEnabled(true);
        txtidn.setText("");
    }//GEN-LAST:event_jButton2ActionPerformed

    private void tabla6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabla6MouseClicked
        // TODO add your handling code here:
         int eli=tabla6.getSelectedRow();
        txtidcurso.setText(String.valueOf(tabla6.getValueAt(eli, 0)));
        txt1.setText(String.valueOf(tabla6.getValueAt(eli, 1)));
        
    }//GEN-LAST:event_tabla6MouseClicked

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
         String ins="INSERT INTO curso_division (idcurso, division, idturno) VALUES(?,?,?)";
        try {
            PreparedStatement pst = cn.prepareStatement(ins);
            pst.setString(1, txtidcurso.getText());
            pst.setString(2, jComboBox1.getSelectedItem().toString());
            pst.setString(3, txtidturno.getText());
             int n= pst.executeUpdate();
            if(n>0){
            JOptionPane.showMessageDialog(this, "Curso/Division Agregado Correctamente");
            txtnc.setText(null);
            cursodivision();
            }
             else
            {
                 JOptionPane.showMessageDialog(this, "Error");
            }
        } catch (Exception e) {
        }
        
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed

         int eli=jTable2.getSelectedRow();
        String valor =jTable2.getValueAt(eli, 0).toString();
         if(eli>=0){int bool = JOptionPane.showConfirmDialog(null,"CURSO:"+" "+jTable2.getValueAt(eli, 1).toString()+"\n"+"DIVISION:"+" "+jTable2.getValueAt(eli, 2).toString()+"\n"+"TURNO:"+" "+jTable2.getValueAt(eli, 3).toString(),"Eliminar  CURSO/DIVISION",JOptionPane.YES_NO_OPTION);
            if(bool == JOptionPane.YES_OPTION){
             try {
               
            com.mysql.jdbc.PreparedStatement pps =(com.mysql.jdbc.PreparedStatement) cn.prepareStatement("delete from curso_division where idcurso_division='"+valor+"'");
            pps.executeUpdate();
            
             JOptionPane.showMessageDialog(null, "CURSO/DIVISION Eliminada Correctamente");
             cursodivision();
             } catch (Exception e) {
             }          
        }
         }
        else{
            JOptionPane.showMessageDialog(null, "Sellecionar CURSO/DIVISION para Eliminar");
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jTable2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MouseClicked
        // TODO add your handling code here:
        jButton4.setEnabled(true);
        jButton3.setEnabled(false);
        int eli=jTable2.getSelectedRow();
         if(eli>=0){
         txtidcd.setText(jTable2.getValueAt(eli, 0).toString());    
         txt1.setText(jTable2.getValueAt(eli, 1).toString());
         jComboBox1.setSelectedItem(jTable2.getValueAt(eli, 2).toString());
         cboturno.setSelectedItem(jTable2.getValueAt(eli, 3).toString());
         
         txtidcd.setEnabled(false);
         txt1.setEnabled(false);
         jComboBox1.setEnabled(false);
         cboturno.setEnabled(false);
         txtidturno.setEnabled(false);
         
         }else{
         JOptionPane.showMessageDialog(null, "Fila no Sellecionada");
         }
    }//GEN-LAST:event_jTable2MouseClicked

    private void cboturnoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboturnoActionPerformed
        // TODO add your handling code here:
        try {
            PreparedStatement pps=cn.prepareStatement ("SELECT idturno FROM turno t where turno=?");
             pps.setString(1, cboturno.getSelectedItem().toString());
//             combonivel.setEnabled(false);
              
            ResultSet resultSet = pps.executeQuery();
            if (resultSet.next()) {
               txtidturno.setText(resultSet.getString("idturno"));
             
               
            }
        }catch (Exception e) {
        }
    }//GEN-LAST:event_cboturnoActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        cboturno.setEnabled(true);
        btneditar.setVisible(true);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void btneditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btneditarActionPerformed
        // TODO add your handling code here:
         try {
            // actualizar monto
        com.mysql.jdbc.PreparedStatement pps =(com.mysql.jdbc.PreparedStatement) cn.prepareStatement("update curso_division set idturno='"+txtidturno.getText()+"' where idcurso_division='"+txtidcd.getText()+"'");
        pps.executeUpdate();
        JOptionPane.showMessageDialog(null, "Datos Actualizados");
        cursodivision();
        
        } catch (SQLException ex) {
            
        }
    }//GEN-LAST:event_btneditarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btneditar;
    private javax.swing.JComboBox<String> cboturno;
    private javax.swing.JComboBox combonivel;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable tabla6;
    private javax.swing.JTextField txt1;
    private javax.swing.JTextField txtidcd;
    private javax.swing.JTextField txtidcurso;
    private javax.swing.JTextField txtidn;
    private javax.swing.JTextField txtidturno;
    private javax.swing.JTextField txtnc;
    // End of variables declaration//GEN-END:variables
conectar cc=new conectar();
Connection cn=cc.conexion();
}
