
package paquetes;

import conexion.conectar;
import java.sql.*;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;
import tesis.render;


public class matricularestudiante extends javax.swing.JFrame {
ResultSet rs;
DefaultTableModel modelo; 
    public matricularestudiante() {
        initComponents();
        this.setLocationRelativeTo(null);
        setTitle("Matricular Estudante");
        ciclolectivo();
        curso();
        año.setVisible(false);
        idciclolectivo.setVisible(false);
        txtidcurso.setVisible(false);
        
        nom1.setVisible(false);
        ape.setVisible(false);
        dni.setVisible(false);

    }

 void ciclolectivo(){

     try {
                PreparedStatement pps=cn.prepareStatement("SELECT año FROM ciclolectivo");
                rs   = pps.executeQuery();
      while (rs.next()) {
      this.combociclo.addItem(rs.getString("año"));
      }
                 } catch (Exception e) {
            } 
}
  void curso(){

     try {
                PreparedStatement pps=cn.prepareStatement("SELECT Descripcion FROM curso");
                rs   = pps.executeQuery();
      while (rs.next()) {
      this.jComboBox1.addItem(rs.getString("Descripcion"));
      }
                 } catch (Exception e) {
            } 
}
  void cursodivision (String valor){
      
      jTable1.setDefaultRenderer(Object.class, new render());
     
      JButton btn = new JButton("Aceptar");
      
 
   String [] titulos= {"CURSO/AÑO","DIVISION", "ACCIONES"};
   modelo=new  DefaultTableModel(null,titulos)
   {
    public boolean isCellEditable(int row, int column){
              return false; 
                }
   };
           
   
   Object datos []= new Object[3];
    String sql="SELECT curso.Descripcion, curso_division.division from curso, curso_division where curso_division.idcurso=curso.idcurso and curso_division.idcurso='"+valor+"'";
   try {
            Statement st = cn.createStatement();
             rs = st.executeQuery(sql);
            while(rs.next())
            {
                datos[0]=rs.getString("curso.Descripcion");
                datos[1]=rs.getString("curso_division.division");
                datos[2]=btn;
                modelo.addRow(datos);
                
            }
            jTable1.setModel(modelo);
            jTable1.setRowHeight(30);
            
        } 
   
   catch (SQLException ex) {
            Logger.getLogger(matricularestudiante.class.getName()).log(Level.SEVERE, null, ex);
        }

 }
  
  void idciclolectivo(String valor){
  try {
           PreparedStatement pps=cn.prepareStatement ("SELECT idciclolectivo FROM ciclolectivo where año='"+valor+"'");
      
      rs   = pps.executeQuery();
      while (rs.next()) {
      idciclolectivo.setText(rs.getString(1));
      
      }
      } catch (Exception e) {
      }
  }
            
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        escriotorio1 = new javax.swing.JDesktopPane();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox();
        jButton1 = new javax.swing.JButton();
        txtidcurso = new javax.swing.JTextField();
        combociclo = new javax.swing.JComboBox();
        idciclolectivo = new javax.swing.JTextField();
        año = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        dni = new javax.swing.JTextField();
        nom1 = new javax.swing.JTextField();
        ape = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        escriotorio1.setBackground(new java.awt.Color(153, 255, 204));
        escriotorio1.setPreferredSize(new java.awt.Dimension(906, 674));

        jLabel1.setBackground(new java.awt.Color(0, 102, 255));
        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setText("Matricular Estudiate-Selecionar Curso");
        jLabel1.setOpaque(true);
        jLabel1.setBounds(40, 20, 230, 15);
        escriotorio1.add(jLabel1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jPanel1.setBackground(new java.awt.Color(204, 204, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Buscar Curso"));

        jLabel2.setText("Ciclo Lectivo:");

        jLabel3.setText("Grado/año:");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-Sellecionar un Grado/Año-" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jButton1.setText("Buscar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        combociclo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-Sellecionar-" }));
        combociclo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                combocicloActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jComboBox1, 0, 190, Short.MAX_VALUE)
                    .addComponent(combociclo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(57, 57, 57)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(año))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtidcurso, javax.swing.GroupLayout.DEFAULT_SIZE, 97, Short.MAX_VALUE)
                    .addComponent(idciclolectivo))
                .addContainerGap(84, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(combociclo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(idciclolectivo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(año, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1)
                    .addComponent(txtidcurso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        jPanel1.setBounds(150, 50, 600, 110);
        escriotorio1.add(jPanel1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jScrollPane1.setBounds(150, 170, 600, 120);
        escriotorio1.add(jScrollPane1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        dni.setBounds(480, 300, 150, 20);
        escriotorio1.add(dni, javax.swing.JLayeredPane.DEFAULT_LAYER);
        nom1.setBounds(150, 300, 150, 20);
        escriotorio1.add(nom1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        ape.setBounds(310, 300, 150, 20);
        escriotorio1.add(ape, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jButton2.setText("Salir");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jButton2.setBounds(30, 540, 53, 23);
        escriotorio1.add(jButton2, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(escriotorio1, javax.swing.GroupLayout.PREFERRED_SIZE, 967, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(escriotorio1, javax.swing.GroupLayout.PREFERRED_SIZE, 583, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
        try {
            PreparedStatement pps=cn.prepareStatement ("select idcurso from curso where Descripcion=?");
             pps.setString(1, jComboBox1.getSelectedItem().toString());
             //jComboBox1.setEnabled(false);
              
            rs= pps.executeQuery();
            if (rs.next()) {
               txtidcurso.setText(rs.getString("idcurso"));
               
               
            }
        }catch (Exception e) {
        }
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        cursodivision(txtidcurso.getText());
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:
        int column = jTable1.getColumnModel().getColumnIndexAtX(evt.getX());
        int row = evt.getY()/jTable1.getRowHeight();
        
        if(row<jTable1.getRowCount()&& row >=0 && column <jTable1.getColumnCount()&& column>=0){
        
        Object value=jTable1.getValueAt(row, column);
        if(value instanceof JButton){
        ((JButton)value).doClick();
        JButton boton=(JButton)value;
       
        // abrir ventana matriculap2
        matriculap2 md= new matriculap2();
        escriotorio1.add(md);
        md.show();
        
       /////////////////////////////////////////////////
        int eli=jTable1.getSelectedRow();
        matriculap2.lc.setText(String.valueOf(jTable1.getValueAt(eli, 0)));
        matriculap2.ld.setText(String.valueOf(jTable1.getValueAt(eli, 1)));
        matriculap2.idcl.setText(idciclolectivo.getText());
        
            matriculap2.nom1.setText(nom1.getText());
            matriculap2.ape.setText(ape.getText());
            matriculap2.dni.setText(dni.getText());

        //md.cursoalumno(matriculap2.idcursodivision.getText());
      md.idcurso_division(jTable1.getValueAt(eli, 0).toString(), jTable1.getValueAt(eli, 1).toString());
      md.cursoalumno(matriculap2.idcursodivision.getText(),matriculap2.idcl.getText());
        }
        
        }
        
    }//GEN-LAST:event_jTable1MouseClicked

    private void combocicloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combocicloActionPerformed
        // TODO add your handling code here:
if (combociclo.getSelectedIndex()==0){
 año.setText(null);
 idciclolectivo.setText(null);
 
 } else {
 String item =combociclo.getSelectedItem().toString();
 String [] data=item.split(" ");
 año.setText(data[0]);
        }
idciclolectivo(año.getText());
    }//GEN-LAST:event_combocicloActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /*
         * Set the Nimbus look and feel
         */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /*
         * If Nimbus (introduced in Java SE 6) is not available, stay with the
         * default look and feel. For details see
         * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(matricularestudiante.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(matricularestudiante.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(matricularestudiante.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(matricularestudiante.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /*
         * Create and display the form
         */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new matricularestudiante().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JTextField ape;
    public static javax.swing.JTextField año;
    public static javax.swing.JComboBox combociclo;
    public static javax.swing.JTextField dni;
    public static javax.swing.JDesktopPane escriotorio1;
    public static javax.swing.JTextField idciclolectivo;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    public static javax.swing.JComboBox jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    public static javax.swing.JTextField nom1;
    private javax.swing.JTextField txtidcurso;
    // End of variables declaration//GEN-END:variables
conectar cc=new conectar();
Connection cn=cc.conexion();}
