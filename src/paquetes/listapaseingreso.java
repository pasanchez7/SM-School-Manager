
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
import static paquetes.ventana.jdpescritorio;
import tesis.render;


public class listapaseingreso extends javax.swing.JInternalFrame {
ResultSet rs;
DefaultTableModel modelo;
DateFormat df = DateFormat.getDateInstance();
   
    public listapaseingreso() {
        initComponents();
        tabla();
    }
 void tabla (){
    
        jTable1.setDefaultRenderer(Object.class, new render());
     
      JButton btn = new JButton("Ver");
      
      String [] titulos= {"Nro. Pase","Fecha Ingreso","Estudiante", "Ciclo Lectivo Recibido", " Curso Inscripto", "Escuela Procedente","Detalle"};
      modelo=new  DefaultTableModel(null,titulos)
        {
            @Override
    public boolean isCellEditable(int row, int column){
              return false; 
                }
   };
      Object datos []= new Object[7];
      String sql="select idpaseingreso, apellido, nombre, fecha, cl.año, c.descripcion, escuelaprocedente "
              + "from paseingreso pi, ciclolectivo cl, curso c "
              + "where pi.idciclolectivo=cl.idciclolectivo and pi.idcurso=c.idcurso order by cl.año,fecha";
      try {
            Statement st = cn.createStatement();
             rs = st.executeQuery(sql);
            while(rs.next())
            {
                datos[0]=rs.getString("idpaseingreso");
                datos[1]=rs.getString("fecha");
                datos[2]=rs.getString("apellido")+"  "+rs.getString("nombre");
                datos[3]=rs.getString("cl.año");
                datos[4]=rs.getString("descripcion");
                datos[5]=rs.getString("escuelaprocedente");
                datos[6]=btn;
                
                modelo.addRow(datos);
                
            }
            jTable1.setModel(modelo);
            jTable1.setRowHeight(20);
            jTable1.getColumnModel().getColumn(0).setMaxWidth(0);
            jTable1.getColumnModel().getColumn(0).setMinWidth(0);
            jTable1.getColumnModel().getColumn(0).setPreferredWidth(0);
        } 
   
   catch (SQLException ex) {
            Logger.getLogger(listapaseingreso.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
  
 void verdetallepase (String valor){
 
     String sql="select apellido, nombre, dni, fecha, cl.año, c.descripcion, escuelaprocedente, motivo "
             + "from paseingreso pi, ciclolectivo cl, curso c "
             + "where pi.idciclolectivo=cl.idciclolectivo and pi.idcurso=c.idcurso and idpaseingreso='"+valor+"'";
     try {
             Statement st = cn.createStatement();
             rs = st.executeQuery(sql);
            while(rs.next()){
            
            //
            detallepingreso.txtapellido.setText(rs.getString("apellido"));
            detallepingreso.txtnombre.setText(rs.getString("nombre"));
            detallepingreso.txtdni.setText(rs.getString("dni"));
            detallepingreso.fecha.setDate(rs.getDate("fecha"));
            detallepingreso.motivo.setText(rs.getString("motivo"));
            detallepingreso.txtescuela.setText(rs.getString("escuelaprocedente"));
            detallepingreso.año.setText(rs.getString("cl.año"));
            detallepingreso.curso.setText(rs.getString("c.descripcion"));
            
             detallepingreso.txtapellido.setEnabled(false);
            detallepingreso.txtnombre.setEnabled(false);
            detallepingreso.txtdni.setEnabled(false);
            detallepingreso.fecha.setEnabled(false);
            detallepingreso.motivo.setEnabled(false);
            detallepingreso.txtescuela.setEnabled(false);
            detallepingreso.año.setEnabled(false);
            detallepingreso.curso.setEnabled(false);
            
            
            
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
        jLabel1.setText("  GESTIÓN DE PASES INGRESOS");
        jLabel1.setOpaque(true);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "LISTADO DE PASES - INGRESOS"));

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

        jButton2.setText("Nuevo Pase Ingreso");
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
                .addContainerGap(48, Short.MAX_VALUE))
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
        
            
        
        detallepingreso dp=new detallepingreso();
        ventana.jdpescritorio.add(dp);
        dp.toFront();
        dp.setVisible(true);
        
        int eli=jTable1.getSelectedRow();
        /////////////////////////////////////////////////
        verdetallepase((String.valueOf(jTable1.getValueAt(eli, 0))));
        
        }
        
        
        }
        
    }//GEN-LAST:event_jTable1MouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

       
        dpaseingreso np = new dpaseingreso();
        ventana.jdpescritorio.add(np);
        np.show();
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
