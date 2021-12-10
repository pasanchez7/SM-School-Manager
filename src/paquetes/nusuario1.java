
package paquetes;

import java.sql.*;
import com.mysql.jdbc.PreparedStatement;
import conexion.conectar;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import reportes.reportes;
import tesis.render;


public class nusuario1 extends javax.swing.JInternalFrame {
    ResultSet rs;
    DefaultTableModel modelo;
  
    public nusuario1() {
        initComponents();
        mostrarusuarios();
        btneliminar1.setVisible(false);
    }
 void mostrarusuarios(){
   
    tbusuario1.setDefaultRenderer(Object.class, new render());
    
      JButton btn = new JButton("Ver");
      DefaultTableModel modelo = (DefaultTableModel)tbusuario1.getModel();
     tbusuario1.setModel(modelo); 

 Object datos []= new Object[5];
   String sql="SELECT u.idusuarios, u.apellido, u.nombre, u.usuario, tu.descripcion "
           + "FROM usuarios u, tipousuario tu, estado_usuarios e where u.idtipousuario=tu.idtipousuario "
           + "and u.idestado_usuarios=e.idestado_usuarios and e.estado='Activo' and descripcion !='docente'"; 
        try {
            Statement st = cn.createStatement();
             rs = st.executeQuery(sql);
            while(rs.next())
            {
                datos[0]=rs.getString("u.idusuarios");
                datos[1]=rs.getString("u.apellido")+"  "+rs.getString("u.nombre");
                datos[2]=rs.getString("u.usuario");
                datos[3]= rs.getString("tu.descripcion");
                datos[4]= btn;
                modelo.addRow(datos);
            }
            tbusuario1.setModel(modelo);
            
             tbusuario1.getColumnModel().getColumn(0).setMaxWidth(0);
             tbusuario1.getColumnModel().getColumn(0).setMinWidth(0);
             tbusuario1.getColumnModel().getColumn(0).setPreferredWidth(0);

            
        } catch (SQLException ex) {
            Logger.getLogger(nusuario.class.getName()).log(Level.SEVERE, null, ex);
        }
  
  
   }
 
  void buscarusuarios(String valor){
   
    DefaultTableModel modelo1 = (DefaultTableModel)tbusuario1.getModel();
tbusuario1.setDefaultRenderer(Object.class, new render());
     
      JButton btn = new JButton("Ver");
      
 
   String [] titulos= {"ID. USUARIO","APELLIDO Y NOMBRE","USUARIO","TIPO USUARIO", "ESTADO", "DETALLE"};
   modelo1=new  DefaultTableModel(null,titulos)
   {
            @Override
    public boolean isCellEditable(int row, int column){
              return false; 
               }
   };
           tbusuario1.setModel(modelo1);
           Object datos []= new Object[6];
   String sql="SELECT u.idusuarios, u.apellido, u.nombre, u.usuario, tu.descripcion, e.estado "
           + "FROM usuarios u, tipousuario tu, estado_usuarios e where u.idtipousuario=tu.idtipousuario "
           + "and u.idestado_usuarios=e.idestado_usuarios and dni='"+valor+"' and descripcion !='docente'"; 
        try {
            Statement st = cn.createStatement();
             rs = st.executeQuery(sql);
            while(rs.next())
            {
                datos[0]=rs.getString("u.idusuarios");
                datos[1]=rs.getString("u.apellido")+"  "+rs.getString("u.nombre");
                datos[2]=rs.getString("u.usuario");
                datos[3]= rs.getString("tu.descripcion");
                datos[4]= rs.getString("e.estado");
                datos[5]= btn;
                modelo1.addRow(datos);
            }
            tbusuario1.setModel(modelo1);
            
             tbusuario1.getColumnModel().getColumn(0).setMaxWidth(0);
             tbusuario1.getColumnModel().getColumn(0).setMinWidth(0);
             tbusuario1.getColumnModel().getColumn(0).setPreferredWidth(0);

            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Usuario No Encontrado");;
        }
  
  
   }
  
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbusuario1 = new javax.swing.JTable();
        btneliminar1 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        txtdni = new javax.swing.JTextField();
        jButton4 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        setResizable(true);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("GESTIONAR PERSONAL AUTORIZADO");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setText("Escuela:");

        jLabel4.setText("I.P.E.T N° 344  PROF. Victor Domínguez");

        tbusuario1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "APELLIDO Y NOMBRE", "USUARIO", "TIPO DE USUARIO", "DETALLE"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbusuario1.getTableHeader().setResizingAllowed(false);
        tbusuario1.getTableHeader().setReorderingAllowed(false);
        tbusuario1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbusuario1MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tbusuario1);
        tbusuario1.getColumnModel().getColumn(0).setResizable(false);
        tbusuario1.getColumnModel().getColumn(0).setPreferredWidth(3);
        tbusuario1.getColumnModel().getColumn(1).setResizable(false);
        tbusuario1.getColumnModel().getColumn(2).setResizable(false);
        tbusuario1.getColumnModel().getColumn(3).setResizable(false);
        tbusuario1.getColumnModel().getColumn(4).setResizable(false);

        btneliminar1.setText("Eliminar");
        btneliminar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btneliminar1ActionPerformed(evt);
            }
        });

        jButton1.setText("Agregar Personal");
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

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setText("DNI:");

        jButton4.setText("BUSCAR");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton3.setText("Imprimir Listado");
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
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel4)))
                        .addGap(0, 325, Short.MAX_VALUE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 593, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(16, 16, 16)
                        .addComponent(txtdni, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jButton2)
                        .addGap(169, 169, 169)
                        .addComponent(jButton3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btneliminar1)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel4))
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtdni, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton4)
                        .addComponent(jLabel3))
                    .addComponent(jButton1))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 133, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btneliminar1)
                    .addComponent(jButton2)
                    .addComponent(jButton3))
                .addGap(30, 30, 30))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btneliminar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btneliminar1ActionPerformed
int eli=tbusuario1.getSelectedRow();
       String valor =tbusuario1.getValueAt(eli, 0).toString();
       int bool = JOptionPane.showConfirmDialog(null,"USUARIO:"+" "+tbusuario1.getValueAt(eli, 1).toString(),"Eliminar Usuario",JOptionPane.YES_NO_OPTION);
          if(bool == JOptionPane.YES_OPTION){  
       if(eli>=0){
             try {
               
            PreparedStatement pps =(PreparedStatement) cn.prepareStatement("delete from usuarios where idusuarios='"+valor+"'");
            pps.executeUpdate();
            
             JOptionPane.showMessageDialog(null, "Dato Eliminado");
             mostrarusuarios();
             } catch (Exception e) {
                 JOptionPane.showMessageDialog(null, "No es posible Eliminar, posee datos cargados");
             }          
        }
        else{
            JOptionPane.showMessageDialog(null, "no hay dato a eliminar"); }  
          }
         
    }//GEN-LAST:event_btneliminar1ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        nusuario nu=new nusuario();
        ventana.jdpescritorio.add(nu);
        nu.toFront();
        nu.setVisible(true);
        this.dispose();
       
        
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void tbusuario1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbusuario1MouseClicked
        // TODO add your handling code here:
        
        
       int eli=tbusuario1.getSelectedRow();
        int column = tbusuario1.getColumnModel().getColumnIndexAtX(evt.getX());
        int row = evt.getY() / tbusuario1.getRowHeight();

        if (row < tbusuario1.getRowCount() && row >= 0 && column < tbusuario1.getColumnCount() && column >= 0) {

            Object value = tbusuario1.getValueAt(row, column);
            if (value instanceof JButton) {
                ((JButton) value).doClick();
                JButton boton = (JButton) value;
            nusuario nu=new nusuario();
            ventana.jdpescritorio.add(nu);
            nu.toFront();
            nu.setVisible(true);
            this.dispose();
            
            
                try {      
              PreparedStatement pps=(PreparedStatement) cn.prepareStatement("SELECT u.idusuarios, u.nombre, u.apellido, u.dni, u.tel, u.sexo, u.usuario, u.contrasena, e.estado, tu.descripcion\n" +
"FROM usuarios u, tipousuario tu, estado_usuarios e where u.idtipousuario=tu.idtipousuario and u.idestado_usuarios=e.idestado_usuarios and u.idusuarios=?");
              pps.setInt(1, Integer.parseInt(tbusuario1.getValueAt(eli, 0).toString()));
              rs = pps.executeQuery();
                    if (rs.next()) {
              nusuario.idusu.setText(rs.getString("u.idusuarios"));
              nusuario.txtnombre.setText(rs.getString("u.nombre"));
              nusuario.txtapellido.setText(rs.getString("u.apellido"));
              nusuario.txtdni.setText(rs.getString("u.dni"));
              nusuario.jComboBox1.setSelectedItem(rs.getString("u.Sexo"));
              nusuario.txttel.setText(rs.getString("u.tel"));
              nusuario.cbotipo1.setSelectedItem(rs.getString("tu.descripcion"));
              nusuario.txtnick1.setText(rs.getString("u.usuario"));
              nusuario.txtpass1.setText(rs.getString("u.contrasena"));
              nusuario.cboestados.setSelectedItem(rs.getString("e.estado"));
              nusuario.jButton1.setEnabled(false);
                      
              
               nusuario.txtnombre.setEnabled(false);
              nusuario.txtapellido.setEnabled(false);
              nusuario.txtdni.setEnabled(false);
              nusuario.jComboBox1.setEnabled(false);
              nusuario.txttel.setEnabled(false);
              nusuario.cbotipo1.setEnabled(false);
              nusuario.txtnick1.setEnabled(false);
              nusuario.txtpass1.setEnabled(false);
              nusuario.jButton1.setEnabled(false);
              nusuario.cboestados.setEnabled(false);
              
            }
                          
             
                    
                } catch (Exception e) {
                }
            
            }
        }
      
    }//GEN-LAST:event_tbusuario1MouseClicked

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        DefaultTableModel modelo3 = (DefaultTableModel)tbusuario1.getModel();
        tbusuario1.setModel(modelo3);
        modelo3.setRowCount(0);
        buscarusuarios(txtdni.getText());
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
         try {
               reportes re =new reportes ();
               re.usuarios();
           } catch (Exception e) {
               System.out.print(e.getMessage());
           }
    }//GEN-LAST:event_jButton3ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btneliminar1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane2;
    public static javax.swing.JTable tbusuario1;
    private javax.swing.JTextField txtdni;
    // End of variables declaration//GEN-END:variables
 conectar cc=new conectar();
   Connection cn=cc.conexion();
   String idfila="";
}
