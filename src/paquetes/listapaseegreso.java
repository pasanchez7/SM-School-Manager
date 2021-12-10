
package paquetes;

import conexion.conectar;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;
import tesis.render;


public class listapaseegreso extends javax.swing.JInternalFrame {
ResultSet rs;
DefaultTableModel modelo;
DateFormat df = DateFormat.getDateInstance();
   
    public listapaseegreso() {
        initComponents();
        tabla();
    }
 public void tabla (){
    
        jTable1.setDefaultRenderer(Object.class, new render());
     
      JButton btn = new JButton("Ver");
      
      
      String [] titulos= {"Nro. Pase","Fecha Egreso","Estudiante", "Ciclo Lectivo Otorgado", "Ult Año Cursado","Detalle"};
      modelo=new  DefaultTableModel(null,titulos)
        {
            @Override
    public boolean isCellEditable(int row, int column){
              return false; 
                }
   };
      Object datos []= new Object[7];
      String sql="SELECT dpase.iddpase, f_egreso, alumno.Apellido, alumno.Nombre, curso.Descripcion, curso_division.division, cl.año "
              + "FROM alumno, curso_division, dpase, curso, ciclolectivo cl "
              + "where dpase.idAlumno=alumno.idAlumno and dpase.idcurso_division=curso_division.idcurso_division "
              + "and curso_division.idcurso=curso.idcurso and dpase.idciclolectivo=cl.idciclolectivo and cl.año=year(now()) order by cl.año";
      try {
            Statement st = cn.createStatement();
             rs = st.executeQuery(sql);
            while(rs.next())
            {
                datos[0]=rs.getString("dpase.iddpase");
                datos[1]=rs.getString("f_egreso");
                datos[2]=rs.getString("alumno.Apellido")+"  "+rs.getString("alumno.Nombre");
                datos[3]=rs.getString("cl.año");
                datos[4]=rs.getString("curso.Descripcion")+"  "+rs.getString("curso_division.division");
                datos[5]=btn;
                
                
                modelo.addRow(datos);
                
            }
            jTable1.setModel(modelo);
            jTable1.setRowHeight(20);
            jTable1.getColumnModel().getColumn(0).setMaxWidth(0);
            jTable1.getColumnModel().getColumn(0).setMinWidth(0);
            jTable1.getColumnModel().getColumn(0).setPreferredWidth(0);
        } 
   
   catch (SQLException ex) {
            Logger.getLogger(listapaseegreso.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
  
 void verdetallepase (String valor){
 
     String sql="SELECT f_egreso, alumno.idalumno, alumno.Apellido, alumno.Nombre, alumno.dni, destino, motivo, curso.Descripcion, curso_division.division, cl.año "
             + "FROM alumno, curso_division, dpase, curso, ciclolectivo cl "
             + "where dpase.idAlumno=alumno.idAlumno and dpase.idcurso_division=curso_division.idcurso_division "
             + "and curso_division.idcurso=curso.idcurso and dpase.idciclolectivo=cl.idciclolectivo and dpase.iddpase='"+valor+"'";
     try {
             Statement st = cn.createStatement();
             rs = st.executeQuery(sql);
            while(rs.next()){
            
            //
            detallepase.txtid.setText(rs.getString("alumno.idalumno"));
            detallepase.l1.setText(rs.getString("alumno.Apellido")+"  "+rs.getString("alumno.Nombre"));
            detallepase.l2.setText(rs.getString("alumno.dni"));
            detallepase.jfecha.setDate(rs.getDate("dpase.f_egreso"));
            detallepase.txtmotivo.setText(rs.getString("motivo"));
            detallepase.jTextField1.setText(rs.getString("destino"));
            detallepase.cl.setText(rs.getString("cl.año"));
            detallepase.curso.setText(rs.getString("curso.Descripcion")+"  "+rs.getString("curso_division.division"));
            
            
            detallepase.jfecha.setEnabled(false);
            detallepase.txtmotivo.setEnabled(false);
            detallepase.jTextField1.setEnabled(false);
            detallepase.cl.setEnabled(false);
            detallepase.curso.setEnabled(false);
            detallepase.txtcondicion.setEnabled(false);
            
            
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
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("  GESTIÓN DE PASES - EGRESOS");
        jLabel1.setOpaque(true);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "LISTADO DE PASES - EGRESOS"));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setText("Escuela:");

        jLabel4.setText("I.P.E.T N° 344  PROF. Victor Domínguez");

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

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 730, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel4))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jButton1.setText("Salir");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Nuevo Pase Egreso");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
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
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1))
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        this.dispose();
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
        
        detallepase dp=new detallepase();
        ventana.jdpescritorio.add(dp);
        dp.toFront();
        dp.setVisible(true);
        this.dispose();
        
        int eli=jTable1.getSelectedRow();
        /////////////////////////////////////////////////
        
        verdetallepase((String.valueOf(jTable1.getValueAt(eli, 0))));
        detallepase.txtidpase.setText((String.valueOf(jTable1.getValueAt(eli, 0))));
        
        }
        }
        
    }//GEN-LAST:event_jTable1MouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        npase np = new npase();
                ventana.jdpescritorio.add(np);
                np.toFront();
                np.setVisible(true);
                this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    public static javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
conectar cc=new conectar();
Connection cn=cc.conexion();
}
