
package paquetes;

import conexion.conectar;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import codigo.GenerarCodigos;
import java.awt.HeadlessException;
import java.io.PrintStream;
import java.sql.*;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


public class ciclolectivo extends javax.swing.JInternalFrame {

    ResultSet rs;
    DefaultTableModel modelo;
    
    
    public ciclolectivo() {
        initComponents();
        jButton2.setEnabled(false);
        jTable1.setVisible(false);
        año.setEditable(false);
        idclectivo.setVisible(false);
        
        clectivo();
        btneliminar.setEnabled(false);
    }

     void codigos(){      
        int j;
        int cont=1;
        String num="";
        String c="";
         String SQL="select max(año) from ciclolectivo";
        try {
            Statement st = cn.createStatement();
            ResultSet rs=st.executeQuery(SQL);
            while(rs.next())
            {              
                 c=rs.getString(1);
            }  
            if(c==null){
                año.setText("2017");
            }
            else{
            char r1=c.charAt(0);
            char r2=c.charAt(1);
            char r3=c.charAt(2);
            char r4=c.charAt(3);
            String r="";
            r=""+r1+r2+r3+r4;
                 j=Integer.parseInt(r);
                GenerarCodigos gen = new GenerarCodigos();
                 gen.generar(j);
                 año.setText(gen.serie());         
            }
    
        } catch (SQLException ex) {    
        }
        }
    
   void clectivo (){
   
    String [] titulos= {"ID","CICLO LECTIVO "};
   modelo=new  DefaultTableModel(null,titulos)
   {
   
       public boolean isCellEditable(int row, int column){
              return false; 
                }
   
   };
   String datos []= new String[5];
   String sql="SELECT * FROM ciclolectivo"; 
        try {
            Statement st = cn.createStatement();
             rs = st.executeQuery(sql);
            while(rs.next())
            {
                datos[0]=rs.getString(1);
                datos[1]=rs.getString(2);
                
                modelo.addRow(datos);
            }
           jTable3.setModel(modelo);
            
             jTable3.getColumnModel().getColumn(0).setMaxWidth(0);
             jTable3.getColumnModel().getColumn(0).setMinWidth(0);
             jTable3.getColumnModel().getColumn(0).setPreferredWidth(0);

            
        } catch (SQLException ex) {
            
        }
   
   
   }
     
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        año = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        idclectivo = new javax.swing.JTextField();
        titulo = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        btneliminar = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(jTable2);

        setClosable(true);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Nuevo Ciclo Lectivo"));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("Ciclo Lectivo/Año:");

        año.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                añoKeyTyped(evt);
            }
        });

        jButton1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton1.setText("Nuevo C.Lectivo");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        titulo.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        titulo.setText("Ciclo Lectivo/Trimestres");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"Primer Trimestre"},
                {"Segundo Trimestre"},
                {"Tercer Trimestre"}
            },
            new String [] {
                "TRIMESTRE"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setGridColor(new java.awt.Color(204, 204, 204));
        jTable1.setSelectionBackground(new java.awt.Color(255, 255, 255));
        jTable1.setSelectionForeground(new java.awt.Color(0, 0, 0));
        jTable1.getTableHeader().setResizingAllowed(false);
        jTable1.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(jTable1);
        jTable1.getColumnModel().getColumn(0).setResizable(false);

        jButton2.setText("CONFIRMAR");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("Ciclos Lectivos Existentes");

        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "CICLO LECTIVO"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable3MouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(jTable3);
        jTable3.getColumnModel().getColumn(0).setResizable(false);
        jTable3.getColumnModel().getColumn(0).setPreferredWidth(3);
        jTable3.getColumnModel().getColumn(1).setResizable(false);

        btneliminar.setText("ELIMINAR C. LECTIVO");
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
                    .addComponent(titulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(año, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(idclectivo, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jButton1))))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(jButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btneliminar)
                .addGap(61, 61, 61))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(año, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1)
                    .addComponent(idclectivo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(titulo)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 95, Short.MAX_VALUE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(btneliminar))
                .addContainerGap(18, Short.MAX_VALUE))
        );

        jButton3.setText("SALIR");
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
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3))
                .addContainerGap(27, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
                .addComponent(jButton3)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        codigos();
        
        jButton2.setEnabled(true);
        jTable1.setVisible(true);
        jTable1.setEnabled(false);
        jButton1.setEnabled(false);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        
        if (jTable1.getRowCount()>0){
            int bool = JOptionPane.showConfirmDialog(null,"CICLO LECTIVO:"+" "+año.getText(),"CREAR CICLO LECTIVO",JOptionPane.YES_NO_OPTION);
            if(bool == JOptionPane.YES_OPTION){
                try {
                    PreparedStatement pps=cn.prepareStatement("INSERT INTO ciclolectivo (año) values (?)");
            pps.setString(1, año.getText());      
            int res = pps.executeUpdate();
            
            try {
             PreparedStatement pp=cn.prepareStatement("SELECT idciclolectivo FROM ciclolectivo where año=?");
              pp.setInt(1, Integer.parseInt(año.getText()));
            rs = pp.executeQuery();
            if (rs.next()) {
               idclectivo.setText(rs.getString("idciclolectivo"));}
        } catch (SQLException | NumberFormatException e) {
        }
                
                 for (int i=0;i<jTable1.getRowCount();i++){
                    try {
                       PreparedStatement ps=cn.prepareStatement("insert into TRIMESTRE (descripcion, idciclolectivo ) values (?,?)");         
                    ps.setString(1, jTable1.getValueAt(i, 0).toString());
                    ps.setString(2, idclectivo.getText().toString());
                    ps.executeUpdate();           
                    } 
                    catch (Exception e) {
                    } 
                }  
                
                } catch (Exception e) {
                }
               
                JOptionPane.showMessageDialog(this, "Ciclo Lectivo Creado Correctamente");
         clectivo();
            }else {
            JOptionPane.showMessageDialog(this, "Ciclo Lectivo No Creado");
            }
        } 
        this.dispose();
      
    }//GEN-LAST:event_jButton2ActionPerformed

    private void añoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_añoKeyTyped
        // TODO add your handling code here:
        char validar=evt.getKeyChar();
        
        if(Character.isLetter(validar)){
        getToolkit().beep();
        evt.consume();
        JOptionPane.showMessageDialog(rootPane, "Ingresar solo Numeros");
        }
    }//GEN-LAST:event_añoKeyTyped

    private void jTable3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable3MouseClicked
        // TODO add your handling code here:  
        btneliminar.setEnabled(true);
    }//GEN-LAST:event_jTable3MouseClicked

    private void btneliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btneliminarActionPerformed
        // TODO add your handling code here:
        int eli=jTable3.getSelectedRow();
        String valor =jTable3.getValueAt(eli, 0).toString();
         if(eli>=0){int bool = JOptionPane.showConfirmDialog(null,"CICLO LECTIVO:"+" "+jTable3.getValueAt(eli, 1).toString(),"Eliminar CICLO LECTIVO",JOptionPane.YES_NO_OPTION);
            if(bool == JOptionPane.YES_OPTION){
             try {
               
            com.mysql.jdbc.PreparedStatement pps =(com.mysql.jdbc.PreparedStatement) cn.prepareStatement("delete from ciclolectivo where idciclolectivo='"+valor+"'");
            pps.executeUpdate();
            
             JOptionPane.showMessageDialog(null, "Ciclo Lectivo eliminado correctamente");
             clectivo();
            
             } catch (SQLException | HeadlessException e) {
                 JOptionPane.showMessageDialog(null,"");
             }          
        }
         }
        else{
            JOptionPane.showMessageDialog(null, "Sellecionar Ciclo Lectivo para eliminar");
        }
    }//GEN-LAST:event_btneliminarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField año;
    private javax.swing.JButton btneliminar;
    private javax.swing.JTextField idclectivo;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable3;
    private javax.swing.JLabel titulo;
    // End of variables declaration//GEN-END:variables
conectar cc=new conectar();
Connection cn=cc.conexion();
}
