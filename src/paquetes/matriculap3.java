
package paquetes;

import conexion.conectar;
import java.awt.Color;
import java.sql.*;
import java.text.DateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import static paquetes.reinscripcionalumno.txtida;
import tesis.render;


public class matriculap3 extends javax.swing.JInternalFrame {
ResultSet rs;
DefaultTableModel modelo;
DateFormat df = DateFormat.getDateInstance();
   
    public matriculap3() {
        initComponents();
        txtide.setVisible(false);
         nom1.setVisible(false);
        ape.setVisible(false);
        dni.setVisible(false);
    }
 void veralumno (String valor){
      
      jTable2.setDefaultRenderer(Object.class, new render());
     
      JButton btn = new JButton("Seleccionar");
      
 
   String [] titulos= {"ID","Nro. Documento", "Sexo", "Apellido","Nombre", "Acción"};
   modelo=new  DefaultTableModel(null,titulos)
   {
            @Override
    public boolean isCellEditable(int row, int column){
              return false; 
               }
   };
           jTable2.setModel(modelo);
   
    Object datos []= new Object[6];
    String sql="select idalumno, DNI, Sexo, Apellido, Nombre from alumno a, estado_alumno ea "
            + "where a.idestado_alumno=ea.idestado_alumno and DNI='"+valor+"' and ea.estado='Pase'";
   try {
            Statement st = cn.createStatement();
             rs = st.executeQuery(sql);
            if(rs.next())
            {
                datos[0]=rs.getString("a.idalumno");
                datos[1]=rs.getString("a.DNI");
                datos[2]=rs.getString("a.Sexo");
                datos[3]=rs.getString("a.Apellido");
                datos[4]=rs.getString("a.Nombre");
                datos[5]=btn;
                modelo.addRow(datos);
                
            }else {
                JOptionPane.showMessageDialog(null, "No existe Alumno con este DNI");
               
            } 
            jTable2.setModel(modelo);
            jTable2.setRowHeight(15);
            
            jTable2.getColumnModel().getColumn(0).setMaxWidth(0);
            jTable2.getColumnModel().getColumn(0).setMinWidth(0);
            jTable2.getColumnModel().getColumn(0).setPreferredWidth(0);
            
        } 
   
   catch (SQLException ex) {
            Logger.getLogger(matriculap3.class.getName()).log(Level.SEVERE, null, ex);
        }

 }
  
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtba = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        txtide = new javax.swing.JTextField();
        nom1 = new javax.swing.JTextField();
        dni = new javax.swing.JTextField();
        ape = new javax.swing.JTextField();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Matricular Estudiante-Datos del Estudiante");
        jLabel1.setOpaque(true);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Estudiante a Matricular", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 0, 12))); // NOI18N

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel2.setText("Número de Documento:");

        txtba.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtbaKeyTyped(evt);
            }
        });

        jButton1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jButton1.setText("Buscar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jButton3.setText("Nuevo Estudiante");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
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
                    .addComponent(jButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtba, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3))
                .addContainerGap(46, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtba, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton3))
                .addContainerGap(34, Short.MAX_VALUE))
        );

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTable2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable2MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable2);

        jButton4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jButton4.setText("Atras");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jButton5.setText("Salir");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addComponent(nom1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(ape, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(dni, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jButton4)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jButton5))
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(txtide, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 703, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGap(0, 10, Short.MAX_VALUE))
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel1)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 29, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtide, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)))
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nom1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dni, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ape, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton4)
                    .addComponent(jButton5))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
       this.dispose();
        matriculadatos md =new matriculadatos();
        ventana.jdpescritorio.add(md);
        md.toFront();
        md.setVisible(true);
        md.panel1.setEnabledAt(1, false);
        md.jPanel4.setEnabled(false);
        md.actualizar.setEnabled(false);
        md.continuar.setEnabled(false);
        md.jButton2.setEnabled(false);
        
            matriculadatos.txtna.setText(nom1.getText());
            matriculadatos.txtaa.setText(ape.getText());
            matriculadatos.txtdnia.setText(dni.getText());
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        veralumno(txtba.getText());
        try {
             PreparedStatement pps=cn.prepareStatement("select idAlumno from alumno where DNI=?");
              pps.setInt(1, Integer.parseInt(txtba.getText()));
            rs = pps.executeQuery();
            if (rs.next()) {
               txtide.setText(rs.getString("idAlumno"));}
        } catch (SQLException | NumberFormatException e) {
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTable2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MouseClicked
        // TODO add your handling code here:
        int column = jTable2.getColumnModel().getColumnIndexAtX(evt.getX());
        int row = evt.getY()/jTable2.getRowHeight();
        
        if(row<jTable2.getRowCount()&& row >=0 && column <jTable2.getColumnCount()&& column>=0){
        
        Object value=jTable2.getValueAt(row, column);
        if(value instanceof JButton){
        ((JButton)value).doClick();
        JButton boton=(JButton)value;
            
        try {
            
             reinscripcionalumno md =new reinscripcionalumno();
             ventana.jdpescritorio.add(md);
             md.toFront();
             md.setVisible(true);
             this.setVisible(false);   
//             md.panel1.setEnabledAt(1, false);  
             md.txtida.setText(jTable2.getValueAt(0, 0).toString());
             md.datosalumno();
             md.cargarfamiliares(reinscripcionalumno.txtida.getText());
             
             
//             PreparedStatement pps=cn.prepareStatement("select idAlumno, Nombre, Apellido, DNI, Sexo, Fecha_Nacimiento, Direccion, Localidad, Telefono, Telefono_Movil from alumno where idAlumno=?");
//              pps.setInt(1, Integer.parseInt(txtide.getText()));
//            rs = pps.executeQuery();
//            if (rs.next()) {
//                matriculadatos.txtida.setText(rs.getString("idAlumno"));
//                matriculadatos.txtna.setText(rs.getString("Nombre"));
//                matriculadatos.txtaa.setText(rs.getString("Apellido"));
//                matriculadatos.txtdnia.setText(rs.getString("DNI"));
//                matriculadatos.jComboBoxsexo.setSelectedItem(rs.getString("Sexo"));
//                
//                matriculadatos.fecha.setDate(rs.getDate("Fecha_Nacimiento"));
//                matriculadatos.txtdireccion.setText(rs.getString("Direccion"));
//                matriculadatos.txtlocalidad.setText(rs.getString("Localidad"));
//                matriculadatos.txttel.setText(rs.getString("Telefono"));
//                matriculadatos.txttelm.setText(rs.getString("Telefono_Movil"));
//            
//                matriculadatos.txtna.setEnabled(false);
//                matriculadatos.txtaa.setEnabled(false);
//                matriculadatos.txtdnia.setEnabled(false);
//                matriculadatos.jComboBoxsexo.setEnabled(false);
//                matriculadatos.fecha.setEnabled(false);
//                matriculadatos.txtdireccion.setEnabled(false);
//                matriculadatos.txtlocalidad.setEnabled(false);
//                matriculadatos.txttel.setEnabled(false);
//                matriculadatos.txttelm.setEnabled(false);
//                
//                matriculadatos.guardarycontinuar.setEnabled(false);
//                matriculadatos.actualizar.setVisible(false);
//               // matriculadatos.continuar.setVisible(true);
//                
//                //matriculadatos.jComboBox1.setEnabled(false);
//                //matriculadatos.jComboBox2.setEnabled(false);
//                //matriculadatos.jButton3.setEnabled(false);
//                
//                matriculadatos.jPanel4.setVisible(false);
//            }
                
            
       
                
            } catch (Exception e) {
            }
       }
        
        }
        
    }//GEN-LAST:event_jTable2MouseClicked

    private void txtbaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtbaKeyTyped
        // TODO add your handling code here:
        char validar=evt.getKeyChar();
        
        if(Character.isLetter(validar)){
        getToolkit().beep();
        evt.consume();
        JOptionPane.showMessageDialog(rootPane, "Ingresar solo Numeros");
        }
    }//GEN-LAST:event_txtbaKeyTyped

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_jButton4ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JTextField ape;
    public static javax.swing.JTextField dni;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable2;
    public static javax.swing.JTextField nom1;
    private javax.swing.JTextField txtba;
    public static javax.swing.JTextField txtide;
    // End of variables declaration//GEN-END:variables
conectar cc=new conectar();
Connection cn=cc.conexion();
}
