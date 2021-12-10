
package paquetes;

import conexion.conectar;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import static paquetes.reinscripcionalumno.txtida;
import tesis.render;


public class matriculap2 extends javax.swing.JInternalFrame {
ResultSet rs;
DefaultTableModel modelo; 
   
    public matriculap2() {
        initComponents();
        idcl.setVisible(false);
        idcursodivision.setVisible(false);
        nom1.setVisible(false);
        ape.setVisible(false);
        dni.setVisible(false);
        btnact.setVisible(false);
        txtaño1.setVisible(false);
       btnfinalizar.setVisible(false);
       
    }

    public void cursoalumno (String valor, String valor1){
      
     Table1.setDefaultRenderer(Object.class, new render());
     
      JButton btn = new JButton("Ver");
      
 
   String [] titulos= {"Id","Nro. Documento","Apellido","Nombre","Estado", "Detalle"};
   modelo=new  DefaultTableModel(null,titulos)
   {
            @Override
    public boolean isCellEditable(int row, int column){
              return false; 
                }
   };
           
   
    Object datos []= new Object[6];
    String sql="SELECT a.idalumno, a.nombre, a.Apellido, a.DNI, em.estado "
            + "FROM alumno a, detalle_matricula dm, curso_division cd, ciclolectivo cl, estado_alumno ea, estado_matricula em "
            + "where dm.idAlumno=a.idAlumno and dm.idcurso_division=cd.idcurso_division and a.idestado_alumno=ea.idestado_alumno "
            + "and dm.idciclolectivo=cl.idciclolectivo and dm.idestado_matricula=em.idestado_matricula "
            + " and dm.idcurso_division='"+valor+"' and dm.idciclolectivo='"+valor1+"' and ea.estado='Activo' and em.estado ='Inscripto'";
   try {
            Statement st = cn.createStatement();
             rs = st.executeQuery(sql);
            while(rs.next())
            {
                datos[0]=rs.getString("a.idalumno");
                datos[1]=rs.getString("a.DNI");
                datos[2]=rs.getString("a.Apellido");
                datos[3]=rs.getString("a.Nombre");
                datos[4]=rs.getString("em.estado");
                datos[5]=btn;
                
                modelo.addRow(datos);
                
            }
            Table1.setModel(modelo);
            Table1.setRowHeight(20);
            
            Table1.getColumnModel().getColumn(0).setMaxWidth(0);
            Table1.getColumnModel().getColumn(0).setMinWidth(0);
            Table1.getColumnModel().getColumn(0).setPreferredWidth(0);
            
        } 
   
   catch (SQLException ex) {
            Logger.getLogger(matriculap2.class.getName()).log(Level.SEVERE, null, ex);
        }
 
 }
    
    public void cursoalumnopre (String valor, String valor1){
      
     Table2.setDefaultRenderer(Object.class, new render());
     
      JButton btn = new JButton("Matricular");
      
 
   String [] titulos= {"Id","Nro. Documento","Apellido","Nombre","Estado", "Detalle"};
   modelo=new  DefaultTableModel(null,titulos)
   {
            @Override
    public boolean isCellEditable(int row, int column){
              return false; 
                }
   };
           
   
    Object datos []= new Object[6];
    String sql="SELECT a.idalumno, a.nombre, a.Apellido, a.DNI, em.estado "
            + "FROM alumno a, detalle_matricula dm, curso_division cd, ciclolectivo cl, estado_alumno ea, estado_matricula em "
            + "where dm.idAlumno=a.idAlumno and dm.idcurso_division=cd.idcurso_division and a.idestado_alumno=ea.idestado_alumno "
            + "and dm.idciclolectivo=cl.idciclolectivo and dm.idestado_matricula=em.idestado_matricula "
            + " and dm.idcurso_division='"+valor+"' and dm.idciclolectivo='"+valor1+"' and ea.estado='Activo' and em.estado ='Pre-Inscripto'";
   try {
            Statement st = cn.createStatement();
             rs = st.executeQuery(sql);
            while(rs.next())
            {
                datos[0]=rs.getString("a.idalumno");
                datos[1]=rs.getString("a.DNI");
                datos[2]=rs.getString("a.Apellido");
                datos[3]=rs.getString("a.Nombre");
                datos[4]=rs.getString("em.estado");
                datos[5]=btn;
                
                modelo.addRow(datos);
                
            }
            Table2.setModel(modelo);
            Table2.setRowHeight(20);
            
            Table2.getColumnModel().getColumn(0).setMaxWidth(0);
            Table2.getColumnModel().getColumn(0).setMinWidth(0);
            Table2.getColumnModel().getColumn(0).setPreferredWidth(0);
            
        } 
   
   catch (SQLException ex) {
            Logger.getLogger(matriculap2.class.getName()).log(Level.SEVERE, null, ex);
        }
 
 }
  
   void idcurso_division (String valor, String valor1){
  
      try {
           PreparedStatement pps=cn.prepareStatement ("SELECT idcurso_division from curso_division where idcurso='"+valor+"' and division='"+valor1+"'");
      
      rs   = pps.executeQuery();
      while (rs.next()) {
      idcursodivision.setText(rs.getString(1));
      
      }
      } catch (Exception e) {
      }
  }
   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        lc = new javax.swing.JLabel();
        ld = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        Table1 = new javax.swing.JTable();
        idcursodivision = new javax.swing.JTextField();
        btnact = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        Table2 = new javax.swing.JTable();
        txtaño1 = new javax.swing.JTextField();
        idcl = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        nom1 = new javax.swing.JTextField();
        ape = new javax.swing.JTextField();
        dni = new javax.swing.JTextField();
        btnfinalizar = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Matricular Estudiante-Datos del Curso");
        jLabel1.setOpaque(true);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Matrícula Actual del Curso", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 0, 12))); // NOI18N

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel2.setText("Grado/Año:");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel3.setText("Division:");

        lc.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        ld.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        jButton1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jButton1.setText("Agregar Estudiantes");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        Table1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        Table1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Table1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(Table1);

        btnact.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnact.setText("Actualizar");
        btnact.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnactActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("ALUMNOS MATRICULADOS");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("ALUMNOS PRE-MATRICULADOS");

        Table2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        Table2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Table2MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(Table2);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 658, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jButton1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnact, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(267, 267, 267)
                                .addComponent(idcl, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lc, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ld, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(idcursodivision, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtaño1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(42, 42, 42))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 668, Short.MAX_VALUE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(ld, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton1)
                            .addComponent(btnact)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(idcursodivision, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtaño1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(idcl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel5)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(39, Short.MAX_VALUE))
        );

        jButton2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jButton2.setText("Atras");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        btnfinalizar.setText("Finalizar");
        btnfinalizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnfinalizarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(23, 23, 23)
                                .addComponent(jButton2)
                                .addGap(18, 18, 18)
                                .addComponent(nom1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(ape, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(dni, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(27, 27, 27)
                                .addComponent(btnfinalizar)))
                        .addGap(0, 14, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton2)
                        .addComponent(nom1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(ape, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(dni, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnfinalizar)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        matriculap3 m3 =new matriculap3();
        ventana.jdpescritorio.add(m3);
        m3.toFront();
        m3.setVisible(true);
       
               
        
        matriculap3.nom1.setText(nom1.getText());
        matriculap3.ape.setText(ape.getText());
        matriculap3.dni.setText(dni.getText());
       
    }//GEN-LAST:event_jButton1ActionPerformed

    private void Table1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Table1MouseClicked
        // TODO add your handling code here:
        int eli=Table1.getSelectedRow();
        int column = Table1.getColumnModel().getColumnIndexAtX(evt.getX());
        int row = evt.getY()/Table1.getRowHeight();
        
        if(row<Table1.getRowCount()&& row >=0 && column <Table1.getColumnCount()&& column>=0){
        
        Object value=Table1.getValueAt(row, column);
        if(value instanceof JButton){
        ((JButton)value).doClick();
        JButton boton=(JButton)value;
    
             detallealumno1 md =new detallealumno1();
             ventana.jdpescritorio.add(md);
             md.toFront();
             md.setVisible(true);
    
         detallealumno1.jTextField1.setText(gestionmatricula.año.getText());
         detallealumno1.jTextField2.setText(matriculap2.lc.getText());
         detallealumno1.jTextField3.setText(matriculap2.ld.getText());
         
         detallealumno1.txtide.setText(Table1.getValueAt(eli, 0).toString());           
              
              this.dispose();
              md.detallealumno();
              md.combofamiliar(detallealumno1.txtide.getText());
              md.idcl(detallealumno1.jTextField1.getText());
      md.idcuro_division(detallealumno1.jTextField2.getText(), detallealumno1.jTextField3.getText());

        
        }
        }
    }//GEN-LAST:event_Table1MouseClicked

    private void btnactActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnactActionPerformed
        
        DefaultTableModel modelo2 = (DefaultTableModel)Table1.getModel();
        Table1.setModel(modelo2);
        modelo2.setRowCount(0); 
        cursoalumno( idcursodivision.getText(),idcl.getText());
       
        DefaultTableModel modelo3 = (DefaultTableModel)Table2.getModel();
        Table2.setModel(modelo3);
        modelo3.setRowCount(0); 
        cursoalumnopre(idcursodivision.getText(),idcl.getText());
    }//GEN-LAST:event_btnactActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void Table2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Table2MouseClicked
        // TODO add your handling code here:
        
        int eli=Table2.getSelectedRow();
        int column = Table2.getColumnModel().getColumnIndexAtX(evt.getX());
        int row = evt.getY()/Table2.getRowHeight();
        
        if(row<Table2.getRowCount()&& row >=0 && column <Table2.getColumnCount()&& column>=0){
        
        Object value=Table2.getValueAt(row, column);
        if(value instanceof JButton){
        ((JButton)value).doClick();
        JButton boton=(JButton)value;
        
        try {
            JOptionPane.showMessageDialog(null, "PRESENTAR LIBRETA DEL ESTUDIANTE PARA MATRICULAR, NO TENER MAS DE 3 MATERIAS DESAPROBADAS", "¡¡ATENCIÓN!!", JOptionPane.WARNING_MESSAGE);
             reinscripcionalumno md =new reinscripcionalumno();
             ventana.jdpescritorio.add(md);
             md.toFront();
             md.setVisible(true);
                
               md.txtida.setText(Table2.getValueAt(eli, 0).toString());
               md.datosalumno();
               md.cargarfamiliares(txtida.getText());
               reinscripcionalumno.txtaño2.setText(txtaño1.getText());
               
            } catch (Exception e) {
            }   
        }
        }
    }//GEN-LAST:event_Table2MouseClicked

    private void btnfinalizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnfinalizarActionPerformed
        // TODO add your handling code here:
        this.dispose();
        
    }//GEN-LAST:event_btnfinalizarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JTable Table1;
    public static javax.swing.JTable Table2;
    public static javax.swing.JTextField ape;
    public static javax.swing.JButton btnact;
    public static javax.swing.JButton btnfinalizar;
    public static javax.swing.JTextField dni;
    public static javax.swing.JTextField idcl;
    public static javax.swing.JTextField idcursodivision;
    public static javax.swing.JButton jButton1;
    public static javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    public static javax.swing.JLabel lc;
    public static javax.swing.JLabel ld;
    public static javax.swing.JTextField nom1;
    public static javax.swing.JTextField txtaño1;
    // End of variables declaration//GEN-END:variables
conectar cc=new conectar();
Connection cn=cc.conexion();
}
