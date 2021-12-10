
package paquetes;

import conexion.conectar;
import java.awt.Color;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import static paquetes.nusuario1.tbusuario1;
import reportes.reportes;
import tesis.render;


public class udocente extends javax.swing.JInternalFrame {
ResultSet rs;

conectar cc=new conectar();
Connection cn=cc.conexion(); 
    
    public udocente() {
        initComponents();
        tabladocente();
    }
void tabladocente (){
DefaultTableModel modelo = (DefaultTableModel)jTable1docente.getModel();
     jTable1docente.setModel(modelo); 
     
     jTable1docente.setDefaultRenderer(Object.class, new render());
     
      JButton btn = new JButton("Ver");
{
    
};
      Object datos []= new Object[5];
      String sql="SELECT u.idusuarios, u.dni,  u.apellido, u.nombre FROM usuarios u, tipousuario tu, estado_usuarios e "
              + "where u.idtipousuario=tu.idtipousuario and u.idestado_usuarios=e.idestado_usuarios and tu.descripcion='Docente' "
              + "and e.estado='Activo'";
      try {
            Statement st = cn.createStatement();
             rs = st.executeQuery(sql);
            while(rs.next())
            {
                datos[0]=rs.getString("idusuarios");
                datos[1]=rs.getString("dni");
                datos[2]=rs.getString("apellido")+" "+rs.getString("nombre");
                datos[3]=btn;
                modelo.addRow(datos);           
            }
            jTable1docente.setModel(modelo);
            jTable1docente.setRowHeight(20);   
           jTable1docente.getColumnModel().getColumn(0).setMaxWidth(0);
             jTable1docente.getColumnModel().getColumn(0).setMinWidth(0);
            jTable1docente.getColumnModel().getColumn(0).setPreferredWidth(0);
        } 
   
   catch (SQLException ex) {
            Logger.getLogger(udocente1.class.getName()).log(Level.SEVERE, null, ex);
        }

}

void buscardocente (String valor){
    DefaultTableModel modelo1 = (DefaultTableModel)jTable1docente.getModel();
jTable1docente.setDefaultRenderer(Object.class, new render());
     
      JButton btn = new JButton("Ver");
      
 
   String [] titulos= {"ID. USUARIO","NRO. DOCUMENTO", "APELLIDO Y NOMBRE","ESTADO", "ASIGNATURAS"};
   modelo1=new  DefaultTableModel(null,titulos)
   {
            @Override
    public boolean isCellEditable(int row, int column){
              return false; 
               }
   };
           jTable1docente.setModel(modelo1);
      Object datos []= new Object[5];
      String sql="SELECT idusuarios, dni,  apellido, nombre, e.estado FROM usuarios u, tipousuario tu, estado_usuarios e "
              + "where u.idtipousuario=tu.idtipousuario and u.idestado_usuarios=e.idestado_usuarios and tu.descripcion='Docente' and dni='"+valor+"'";
      try {
            Statement st = cn.createStatement();
             rs = st.executeQuery(sql);
            while(rs.next())
            {
                datos[0]=rs.getString("u.idusuarios");
                datos[1]=rs.getString("u.dni");
                datos[2]=rs.getString("u.apellido")+" "+rs.getString("u.nombre");
                datos[3]=rs.getString("e.estado");
                datos[4]=btn;
                modelo1.addRow(datos);           
            }
            jTable1docente.setModel(modelo1);
            jTable1docente.setRowHeight(20);   
           jTable1docente.getColumnModel().getColumn(0).setMaxWidth(0);
             jTable1docente.getColumnModel().getColumn(0).setMinWidth(0);
            jTable1docente.getColumnModel().getColumn(0).setPreferredWidth(0);
        } 
   
   catch (SQLException ex) {
            Logger.getLogger(udocente1.class.getName()).log(Level.SEVERE, null, ex);
        }
}
   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1docente = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        txtdni = new javax.swing.JTextField();
        jButton4 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();

        setClosable(true);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("GESTIONAR USUARIO DOCENTE");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Usuarios Docentes de la Escuela"));

        jTable1docente.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "DNI", "APELLIDO Y NOMBRE", "ASIGNATURAS"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1docente.getTableHeader().setResizingAllowed(false);
        jTable1docente.getTableHeader().setReorderingAllowed(false);
        jTable1docente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1docenteMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1docente);
        if (jTable1docente.getColumnModel().getColumnCount() > 0) {
            jTable1docente.getColumnModel().getColumn(0).setResizable(false);
            jTable1docente.getColumnModel().getColumn(0).setPreferredWidth(10);
            jTable1docente.getColumnModel().getColumn(1).setResizable(false);
            jTable1docente.getColumnModel().getColumn(2).setResizable(false);
            jTable1docente.getColumnModel().getColumn(3).setResizable(false);
        }

        jButton1.setText("Agregar Docente");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Salir");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton4.setText("BUSCAR");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setText("DNI:");

        jButton5.setText("Imprimir Listado");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(200, 200, 200)
                        .addComponent(jButton1))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 711, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel2)
                        .addGap(16, 16, 16)
                        .addComponent(txtdni, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton4)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtdni, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 137, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(jButton5))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(50, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        udocente1 nu=new udocente1();
        ventana.jdpescritorio.add(nu);
        nu.toFront();
        nu.setVisible(true);
        this.dispose();
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTable1docenteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1docenteMouseClicked
        // TODO add your handling code here:
        
        int eli=jTable1docente.getSelectedRow();
        int column = jTable1docente.getColumnModel().getColumnIndexAtX(evt.getX());
        int row = evt.getY() / jTable1docente.getRowHeight();

        if (row < jTable1docente.getRowCount() && row >= 0 && column < jTable1docente.getColumnCount() && column >= 0) {

            Object value = jTable1docente.getValueAt(row, column);
            if (value instanceof JButton) {
                ((JButton) value).doClick();
                JButton boton = (JButton) value;
                
                udocente1 nu=new udocente1();
                        ventana.jdpescritorio.add(nu);
                        nu.toFront();
                        nu.setVisible(true);
                        this.dispose();
                        udocente1.btnagregar.setVisible(true);
                        udocente1.asig.setVisible(true);
                        udocente1.btneliminar.setVisible(true);
                        
                        udocente1.jButton5.setVisible(true);
                       
                try {
                    
              PreparedStatement pps=cn.prepareStatement("select u.idusuarios, u.nombre, u.apellido, u.dni, u.sexo, "
                      + "u.tel, u.usuario, u.contrasena, e.estado from usuarios u, estado_usuarios e "
                      + "where u.idestado_usuarios=e.idestado_usuarios and idusuarios=?");
              pps.setInt(1, Integer.parseInt(jTable1docente.getValueAt(eli, 0).toString()));
            rs = pps.executeQuery();
            if (rs.next()) {
              udocente1.idusudocente.setText(rs.getString("u.idusuarios"));
              udocente1.txtnombre.setText(rs.getString("u.nombre"));
              udocente1.txtapellido.setText(rs.getString("u.apellido"));
              udocente1.txtdni.setText(rs.getString("u.dni"));
              udocente1.jComboBox1.setSelectedItem(rs.getString("u.Sexo"));
              udocente1.txttel.setText(rs.getString("u.tel"));
              udocente1.txtnick1.setText(rs.getString("u.usuario"));
              udocente1.txtpass1.setText(rs.getString("u.contrasena"));
              udocente1.cboestados.setSelectedItem(rs.getString("e.estado"));
              
              udocente1.idusudocente.setEnabled(false);
              udocente1.txtnombre.setEnabled(false);
              udocente1.txtapellido.setEnabled(false);
              udocente1.txtdni.setEnabled(false);
              udocente1.jComboBox1.setEnabled(false);
              udocente1.txttel.setEnabled(false);
              udocente1.txtnick1.setEnabled(false);
              udocente1.txtpass1.setEnabled(false);
              udocente1.jButton1.setEnabled(false);
              udocente1.cboestados.setEnabled(false);
              
            }
           nu.tabla2(udocente1.idusudocente.getText(),udocente1.txtidcl.getText());
        } catch (SQLException | NumberFormatException e) {
        }              
            }

        }
    }//GEN-LAST:event_jTable1docenteMouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        DefaultTableModel modelo3 = (DefaultTableModel)jTable1docente.getModel();
        jTable1docente.setModel(modelo3);
        modelo3.setRowCount(0); 
        buscardocente(txtdni.getText());
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
         try {
               reportes re =new reportes ();
               re.docente();
           } catch (Exception e) {
               System.out.print(e.getMessage());
           }
    }//GEN-LAST:event_jButton5ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1docente;
    private javax.swing.JTextField txtdni;
    // End of variables declaration//GEN-END:variables
}
