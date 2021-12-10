
package paquetes;

import conexion.conectar;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import tesis.render;


public class traspasodivision extends javax.swing.JInternalFrame {
conectar cc=new conectar();
Connection cn=cc.conexion();
ResultSet rs;
DefaultTableModel modelo; 
DefaultTableModel modelo1; 


    public traspasodivision() {
        initComponents();
        curso();
        cl();
        id.setVisible(false);
        idcursodivision.setVisible(false);
        txtcurso.setVisible(false);
        txtdivi.setVisible(false);
        idcursodivision1.setVisible(false);
        txtcurso1.setVisible(false);
        txtdivi1.setVisible(false);
        
    }

    void curso (){
    try {
                PreparedStatement pps=cn.prepareStatement("SELECT c.descripcion, cd.division FROM curso_division cd, curso c where cd.idcurso=c.idcurso order by c.descripcion, cd.division");
                rs   = pps.executeQuery();
      while (rs.next()) {
      this.curso.addItem(rs.getString("c.descripcion")+ " Division "  +rs.getString("cd.division"));
     curso1.addItem(rs.getString("c.descripcion")+ " Division "  +rs.getString("cd.division"));
      }
                 } catch (Exception e) {
            }
    
    }
    
    
    
    void cl (){
try {
                PreparedStatement pps=cn.prepareStatement("SELECT idciclolectivo, año  FROM ciclolectivo where año=year(now())");
                rs   = pps.executeQuery();
      while (rs.next()) {
         id.setText(rs.getString(1));
         año.setText(rs.getString(2));
      }
                 } catch (Exception e) {
            }
}
     void idcuro_division(String valor, String valor1){
try {
                PreparedStatement pps=cn.prepareStatement("SELECT idcurso_division "
                        + "FROM curso_division, curso where curso_division.idcurso=curso.idcurso "
                        + "and  curso.Descripcion='"+valor+"'and division='"+valor1+"'");
                rs   = pps.executeQuery();
      while (rs.next()) {
      idcursodivision.setText(rs.getString(1));
     
      }
                 } catch (Exception e) {
            }
    }
    
      void idcuro_division1(String valor, String valor1){
try {
                PreparedStatement pps=cn.prepareStatement("SELECT idcurso_division "
                        + "FROM curso_division, curso where curso_division.idcurso=curso.idcurso "
                        + "and  curso.Descripcion='"+valor+"'and division='"+valor1+"'");
                rs   = pps.executeQuery();
      while (rs.next()) {
      idcursodivision1.setText(rs.getString(1));
     
      }
                 } catch (Exception e) {
            }
    }
     
      void cursoalumno (String valor, String valor1){

   String [] titulos= {"iddm","Nro. Documento","Apellido","Nombre"};
   modelo=new  DefaultTableModel(null,titulos)
   {
            @Override
    public boolean isCellEditable(int row, int column){
              return false; 
                }
   };
           
   
    Object datos []= new Object[4];
    String sql="select iddetalle_matricula, alumno.DNI, alumno.Apellido, alumno.Nombre "
            + "from alumno, detalle_matricula, ciclolectivo "
            + "where detalle_matricula.idAlumno=alumno.idAlumno and idcurso_division='"+valor+"' "
            + "and ciclolectivo.idciclolectivo=detalle_matricula.idciclolectivo and detalle_matricula.idciclolectivo='"+valor1+"'";
   try {
            Statement st = cn.createStatement();
             rs = st.executeQuery(sql);
            while(rs.next())
            {   
                datos[0]=rs.getString("iddetalle_matricula");
                datos[1]=rs.getString("DNI"); 
                datos[2]=rs.getString("Apellido");
                datos[3]=rs.getString("Nombre");
                
                
                modelo.addRow(datos);
                
            }
           jTable1.setModel(modelo);
            jTable1.setRowHeight(20);
            
            //jTable1.setCellSelectionEnabled(true);
            //jTable1.setSurrendersFocusOnKeystroke(true);
            
            jTable1.getColumnModel().getColumn(0).setMaxWidth(0);
            jTable1.getColumnModel().getColumn(0).setMinWidth(0);
            jTable1.getColumnModel().getColumn(0).setPreferredWidth(0);
            
        } 
   
   catch (SQLException ex) {
            Logger.getLogger(traspasodivision.class.getName()).log(Level.SEVERE, null, ex);
        }
 
 }
     
       void cursoalumno1 (String valor, String valor1){

   String [] titulos= {"iddm","Nro. Documento","Apellido","Nombre"};
   modelo1=new  DefaultTableModel(null,titulos)
   {
            @Override
    public boolean isCellEditable(int row, int column){
              return false; 
                }
   };
   Object datos []= new Object[4];
    String sql="select iddetalle_matricula, alumno.DNI, alumno.Apellido, alumno.Nombre "
            + "from alumno, detalle_matricula, ciclolectivo "
            + "where detalle_matricula.idAlumno=alumno.idAlumno and idcurso_division='"+valor+"' "
            + "and ciclolectivo.idciclolectivo=detalle_matricula.idciclolectivo and detalle_matricula.idciclolectivo='"+valor1+"'";
   try {
            Statement st = cn.createStatement();
             rs = st.executeQuery(sql);
            while(rs.next())
            {   
                datos[0]=rs.getString("iddetalle_matricula");
                datos[1]=rs.getString("DNI"); 
                datos[2]=rs.getString("Apellido");
                datos[3]=rs.getString("Nombre");
        modelo1.addRow(datos);                
            }
           jTable2.setModel(modelo1);
            jTable2.setRowHeight(20);

            jTable2.getColumnModel().getColumn(0).setMaxWidth(0);
            jTable2.getColumnModel().getColumn(0).setMinWidth(0);
            jTable2.getColumnModel().getColumn(0).setPreferredWidth(0);          
        } 
   
   catch (SQLException ex) {
            Logger.getLogger(traspasodivision.class.getName()).log(Level.SEVERE, null, ex);
        }
 }
      
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        curso = new javax.swing.JComboBox();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        idcursodivision = new javax.swing.JTextField();
        txtcurso = new javax.swing.JTextField();
        txtdivi = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        curso1 = new javax.swing.JComboBox();
        txtcurso1 = new javax.swing.JTextField();
        txtdivi1 = new javax.swing.JTextField();
        idcursodivision1 = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jButton3 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        año = new javax.swing.JLabel();
        id = new javax.swing.JTextField();

        setClosable(true);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("Ciclo Lectivo");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Curso/División Origen"));

        curso.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "--Sellecionar--" }));
        curso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cursoActionPerformed(evt);
            }
        });

        jButton1.setText("Buscar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTable1.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(jTable1);

        jButton2.setText("Traspasar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
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
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(curso, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtcurso, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtdivi, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(idcursodivision, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 565, Short.MAX_VALUE))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton2)
                        .addGap(21, 21, 21))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(curso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(idcursodivision, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtcurso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtdivi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton2)
                .addContainerGap(15, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Curso/División Destino"));

        curso1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "--Sellecionar--" }));
        curso1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                curso1ActionPerformed(evt);
            }
        });

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTable2.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(jTable2);

        jButton3.setText("Salir");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jButton3)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(curso1, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(81, 81, 81)
                        .addComponent(txtcurso1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtdivi1, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(idcursodivision1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 565, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(curso1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(idcursodivision1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtcurso1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtdivi1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Traspaso de División");

        año.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(año, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(67, 67, 67)
                        .addComponent(id, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(año, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cursoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cursoActionPerformed
        if (curso.getSelectedIndex()==0){
            
                } else {
                String item =curso.getSelectedItem().toString();
                String [] data=item.split(" ");
                txtcurso.setText(data [0] + " " + data[1]);
                txtdivi.setText(data [3]);   
                 
        }
        
      
         idcuro_division(txtcurso.getText(), txtdivi.getText());
        
         
          
    }//GEN-LAST:event_cursoActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        //curso1.removeAllItems();
        cursoalumno(idcursodivision.getText(), id.getText());
                
       // cursodestino(txtcurso.getText());
        
        //cursodestino(txtcurso.getText());
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void curso1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_curso1ActionPerformed
         if (curso1.getSelectedIndex()==0){
            
                } else {
                String item =curso1.getSelectedItem().toString();
                String [] data=item.split(" ");
                txtcurso1.setText(data [0] + " " + data[1]);
                txtdivi1.setText(data [3]);     
        }
         idcuro_division1(txtcurso1.getText(), txtdivi1.getText());
         cursoalumno1(idcursodivision1.getText(), id.getText());
    }//GEN-LAST:event_curso1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
         int eli=jTable1.getSelectedRow(); 
        
          int bool = JOptionPane.showConfirmDialog(null,"CURSO ORIGEN:"+" "+curso.getSelectedItem().toString()+"\n"+"CURSO DESTINO:"+" "+curso1.getSelectedItem().toString()+"\n"+"ESTUDIANTE:"+" "+modelo.getValueAt(eli, 2).toString()+" "+modelo.getValueAt(eli, 3).toString(),"Traspasar Estudiante",JOptionPane.YES_NO_OPTION);
          if(bool == JOptionPane.YES_OPTION){
                for (int i=0;i<jTable1.getRowCount();i++){
          try {
           
            PreparedStatement pps = cn.prepareStatement("update detalle_matricula  set idcurso_division='"+idcursodivision1.getText()+"' where iddetalle_matricula="+modelo.getValueAt(eli, 0).toString());
        pps.executeUpdate();
        JOptionPane.showMessageDialog(null, "Datos Actualizados");
        cursoalumno(idcursodivision.getText(), id.getText());
        cursoalumno1(idcursodivision1.getText(), id.getText());
        //jButton1.setEnabled(true);
        } catch (SQLException ex) {
            Logger.getLogger(traspasodivision.class.getName()).log(Level.SEVERE, null, ex);
        }
        
                }
          }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_jButton3ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel año;
    private javax.swing.JComboBox curso;
    private javax.swing.JComboBox curso1;
    private javax.swing.JTextField id;
    private javax.swing.JTextField idcursodivision;
    private javax.swing.JTextField idcursodivision1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextField txtcurso;
    private javax.swing.JTextField txtcurso1;
    private javax.swing.JTextField txtdivi;
    private javax.swing.JTextField txtdivi1;
    // End of variables declaration//GEN-END:variables
}
