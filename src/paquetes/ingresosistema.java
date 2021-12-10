
package paquetes;





import conexion.conectar;
import java.awt.Color;
import java.awt.HeadlessException;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;


public class ingresosistema extends javax.swing.JFrame {
  ResultSet rs;  
   

   
   
    public ingresosistema() {
        initComponents();
        this.setLocationRelativeTo(null);
        this.setTitle("Acceso al Sistema");
//        this.setExtendedState(MAXIMIZED_BOTH);
//        this.getContentPane().setBackground(Color.green);
        
    }
    

    
  void acceder (String usuario, String pass){
  
       String cap="";
       String sql1="SELECT tu.descripcion, u.nombre, u.apellido, u.idusuarios FROM usuarios u, tipousuario tu, estado_usuarios e "
               + "WHERE u.idtipousuario=tu.idtipousuario and u.idestado_usuarios=e.idestado_usuarios and usuario='"+usuario+"' && decode(contrasena,'344')='"+pass+"' && e.estado='Activo'";
       
       String sql="SELECT tu.descripcion, u.nombre, u.apellido, u.idusuarios "
               + "FROM usuarios u, tipousuario tu, estado_usuarios e "
               + "WHERE u.idtipousuario=tu.idtipousuario and u.idestado_usuarios=e.idestado_usuarios and usuario='"+usuario+"' "
               + "&& u.contrasena = md5('"+pass+"') && e.estado='Activo'";
       
       try {
            PreparedStatement pps=cn.prepareStatement (sql);
            rs = pps.executeQuery(sql);
            while(rs.next())
             {
                
             cap=rs.getString("descripcion");      
              
             }
             if(cap.equals("Administrador"))
            {
                  this.setVisible(false);
                    JOptionPane.showMessageDialog(null, "Bienvenido");
                     //ventanadirector ingreso = new ventanadirector();
                     ventana ingreso = new ventana();
                     ingreso.setVisible(true);
                     ingreso.pack();
                     ingreso.setExtendedState(ingreso.MAXIMIZED_BOTH);
                     this.dispose();
                     mostrar(usuario, pass);
                     ingreso.setTitle("VENTANA ADMINISTRADOR");
                     ventana.icalificaciones.setVisible(false);
                     ventana.itemregistrarcalificaciones.setVisible(false);
                     ventana.itempromocionar.setVisible(false);
        
                     
            }
            if(cap.equals("Director"))
            {
                  this.setVisible(false);
                    JOptionPane.showMessageDialog(null, "Bienvenido");
                     //ventanadirector ingreso = new ventanadirector();
                     ventana ingreso = new ventana();
                     ingreso.setVisible(true);
                     ingreso.pack();
                     ingreso.setExtendedState(ingreso.MAXIMIZED_BOTH);
                     this.dispose();
                     mostrar(usuario, pass);
                     ingreso.setTitle("VENTANA DIRECTOR");
                     ventana.icalificaciones.setVisible(false);
                     ventana.itemregistrarcalificaciones.setVisible(false);
                     ventana.itemlibrotema.setVisible(false);
                     ventana.ilibreta.setVisible(false);
                     ventana.itempromocionar.setVisible(false);
                     ventana.itemverregistros.setVisible(false);
                     ventana.jMenuItem12.setVisible(false);
                     ventana.jMenuItem4.setVisible(false);
            }
             if(cap.equals("Secretario"))
            {
            this.setVisible(false);
                    JOptionPane.showMessageDialog(null, "Bienvenido");
//                    vensecre ingresos = new vensecre();
                    ventana ingresos = new ventana();
                    ingresos.setVisible(true);
                    ingresos.pack();
                    mostrar(usuario, pass);
                    ingresos.setTitle("VENTANA SECRETARIO");
                    ingresos.setExtendedState(ingresos.MAXIMIZED_BOTH);
                    this.dispose();
          //Acceso a tareas//          
                    ventana.itemlistadodocente.setVisible(false);
                    ingresos.itemasig.setVisible(false);
                    ingresos.itemdistricu.setVisible(false);
                    ingresos.itemingresarplan.setVisible(false);
                    ingresos.menuestructura.setVisible(false);
                    ingresos.menuperiodoescolar.setVisible(false);
                    ingresos.menuseguridad.setVisible(false);
                     ventana.ivercali.setVisible(false);
                     ventana.itemregistrarcalificaciones.setVisible(false);
                     ventana.itemlibrotema.setVisible(false);
                     ventana.ilibreta.setVisible(false);
                     ventana.itempromocionar.setVisible(false);
                     ventana.itemverregistros.setVisible(false);
                     ventana.jMenuItem12.setVisible(false);
              ///////////////////////////////////////////////////////////////////      
                    
                    
             }
             if(cap.equals("Preceptor"))
            {
            this.setVisible(false);
                    JOptionPane.showMessageDialog(null, "Bienvenido");
//                    venprece ingresos = new venprece();
                    ventana ingresos = new ventana();
                    ingresos.setVisible(true);   
                    ingresos.pack();
                    ingresos.setTitle("VENTANA PRECEPTOR");
                    ingresos.setExtendedState(ingresos.MAXIMIZED_BOTH);
                    mostrar(usuario, pass);
                    this.dispose();
                    //Acceso a tareas// 
                    ventana.itemmatricular.setVisible(false);
                    ingresos.itemasig.setVisible(false);
                    ingresos.itemdistricu.setVisible(false);
                    ingresos.itemingresarplan.setVisible(false);
                    ingresos.menuestructura.setVisible(false);
                    ingresos.menuperiodoescolar.setVisible(false);
                    ingresos.menuseguridad.setVisible(false);
                    ventana.itemregistrarcalificaciones.setVisible(false);
                    ventana.itempaseegreso.setVisible(false);
                    ventana.itempaseingreso.setVisible(false);
                    ventana.ivercali.setVisible(false);
                    ventana.itemregistrarcalificaciones.setVisible(false);
                    ventana.itemlibrotema.setVisible(false);
                    ventana.jMenuItem9.setVisible(false);
              /////////////////////////////////////////////////////////////////// 
                    
             }
             if(cap.equals("Docente"))
             {
             JOptionPane.showMessageDialog(null, "Usuario Docente, Usar Plataforma Web");
             txtpass.setText(null);
             txtusuario.setText(null);
             
         /*  ventana ingresos = new ventana();
                    ingresos.setVisible(true);   
                    ingresos.pack();
                    ingresos.setTitle("VENTANA DOCENTE");
                    ingresos.setExtendedState(ingresos.MAXIMIZED_BOTH);
                    mostrar(usuario, pass);
                    this.dispose();
     //////Tareas/////////
     
                     ventana.itemmatricular.setVisible(false);
                     ventana.jMenuItem20.setVisible(false);
                     ventana.jMenu4.setVisible(false);
                     ventana.menuperiodoescolar.setVisible(false);
                     ventana.menuestructura.setVisible(false);
                     ventana.menuseguridad.setVisible(false);        
                     ventana.jMenu7.setVisible(false);
                     ventana.ivercali.setVisible(false);       
                     ventana.ilibreta.setVisible(false);
                     ventana.icalificaciones.setVisible(false);        
                     ventana.itempromocionar.setVisible(false);
                     ventana.itemverregistros.setVisible(false);
                     ventana.jMenuItem9.setVisible(false);
                     ventana.jMenuItem12.setVisible(false); */    
                            
             }
             
            if((!cap.equals("Director"))&& (!cap.equals("Secretario"))&& (!cap.equals("Preceptor"))&& (!cap.equals("Docente"))&& (!cap.equals("Administrador")))

             {
                JOptionPane.showMessageDialog(null, "Usuario y/o Contraseña Incorrectos","ERROR AL INGRESAR",JOptionPane.ERROR_MESSAGE);
                txtpass.setText(null);
                txtusuario.setText(null);
             
             }
        } catch (SQLException ex) {
            Logger.getLogger(ingresosistema.class.getName()).log(Level.SEVERE, null, ex);
        }
     
  }
  
  void mostrar (String usuario, String pass){
  String sql="SELECT tu.descripcion, u.nombre, u.apellido, u.idusuarios FROM usuarios u, tipousuario tu\n" +
  "WHERE u.idtipousuario=tu.idtipousuario and usuario='"+usuario+"'&& u.contrasena = md5('"+pass+"')";
      try {
          PreparedStatement pps=cn.prepareStatement (sql);
            rs = pps.executeQuery(sql);
            while(rs.next()){

            ventana.lusuario.setText(rs.getString("u.nombre")+" "+rs.getString("u.apellido"));
           ventana.ltipo.setText(rs.getString("tu.descripcion"));
          ventana.id.setText(rs.getString("u.idusuarios"));
            
            }
      } catch (Exception e) {
      }
  }

 

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtusuario = new javax.swing.JTextField();
        txtpass = new javax.swing.JPasswordField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 200, 0));
        setFocusable(false);
        setPreferredSize(new java.awt.Dimension(450, 600));
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 102, 102));
        jPanel1.setRequestFocusEnabled(false);
        jPanel1.setVerifyInputWhenFocusTarget(false);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("Usuario");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("Contraseña");

        jButton1.setBackground(new java.awt.Color(255, 0, 0));
        jButton1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton1.setText("Entrar");
        jButton1.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(255, 51, 51)));
        jButton1.setMaximumSize(new java.awt.Dimension(73, 23));
        jButton1.setMinimumSize(new java.awt.Dimension(73, 23));
        jButton1.setPreferredSize(new java.awt.Dimension(73, 23));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(255, 0, 0));
        jButton2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton2.setText("Salir");
        jButton2.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(255, 51, 51)));
        jButton2.setMaximumSize(new java.awt.Dimension(73, 23));
        jButton2.setMinimumSize(new java.awt.Dimension(73, 23));
        jButton2.setPreferredSize(new java.awt.Dimension(73, 23));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/admin2.JPG"))); // NOI18N

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("SGE : Sistema Gestión Escolar");

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/escudo.jpg"))); // NOI18N

        jLabel6.setBackground(new java.awt.Color(255, 255, 255));
        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Por favor ingrese su nombre de usuario y contraseña");

        txtusuario.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtusuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtusuarioActionPerformed(evt);
            }
        });

        txtpass.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtpass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtpassActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(17, 17, 17))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel2))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jLabel1)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtusuario)
                            .addComponent(txtpass))))
                .addContainerGap())
            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtusuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(62, 62, 62)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtpass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(33, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
      String usu=txtusuario.getText();
      String pas=new String(txtpass.getPassword());
      acceder(usu, pas);
     // mostrar(usu, pas);
     //accederdocente(usu, pas);
      
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
      System.exit(0);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void txtpassActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtpassActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtpassActionPerformed

    private void txtusuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtusuarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtusuarioActionPerformed

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
            java.util.logging.Logger.getLogger(ingresosistema.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ingresosistema.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ingresosistema.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ingresosistema.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /*
         * Create and display the form
         */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new ingresosistema().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPasswordField txtpass;
    private javax.swing.JTextField txtusuario;
    // End of variables declaration//GEN-END:variables
   conectar cc=new conectar();
   Connection cn=cc.conexion();

  
}
