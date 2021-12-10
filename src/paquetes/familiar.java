
package paquetes;

import conexion.conectar;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import tesis.render;


public class familiar extends javax.swing.JInternalFrame {
ResultSet rs;
DefaultTableModel modelo;



    public familiar() {
        initComponents();
        txtidfamiliar1.setVisible(false);
        txtidf.setVisible(false);
        
       
    }
 void verfamiliar (String valor){
 
 jTable1.setDefaultRenderer(Object.class, new render());
     
      JButton btn = new JButton("Seleccionar");
      
 
   String [] titulos= {"Id.familiar","Nro. Documento", "Sexo", "Apellido","Nombre","Tipo Vinculo", "Acción"};
   modelo=new  DefaultTableModel(null,titulos)
   {
            @Override
    public boolean isCellEditable(int row, int column){
              return false; 
               }
   };
           jTable1.setModel(modelo);
   
   Object datos []= new Object[7];
    String sql="select idfamiliar, DNI, Sexo, Apellido, Nombre, Parentesco from familiar where DNI='"+valor+"'";
   try {
            Statement st = cn.createStatement();
             rs = st.executeQuery(sql);
           if(rs.next())
            {
                datos[0]=rs.getString("idfamiliar");
                datos[1]=rs.getString("DNI");
                datos[2]=rs.getString("Sexo");
                datos[3]=rs.getString("Apellido");
                datos[4]=rs.getString("Nombre");
                datos[5]=rs.getString("Parentesco");
                datos[6]=btn;
                modelo.addRow(datos);
                
            } else {
                JOptionPane.showMessageDialog(null, "No existe Familiar con este DNI");
               
            } 
                      
            jTable1.setModel(modelo);
            jTable1.setRowHeight(15);
             
             jTable1.getColumnModel().getColumn(0).setMaxWidth(0);
             jTable1.getColumnModel().getColumn(0).setMinWidth(0);
             jTable1.getColumnModel().getColumn(0).setPreferredWidth(0);
        } 
   
   catch (SQLException ex) {
            Logger.getLogger(familiar.class.getName()).log(Level.SEVERE, null, ex);
        }

 
 }
 void ver (){
 
     matriculadatos.tabla1.setDefaultRenderer(Object.class, new render());
     
      JButton btn = new JButton("Seleccionar");
     DefaultTableModel model =(DefaultTableModel)matriculadatos.tabla1.getModel();
     String [] titulos= {"Id","Nro. Documento", "Sexo", "Apellido","Nombre","Tipo Vinculo", "Acción"};
     Object datos []= new Object[7];
        
        datos[0]=txtidf.getText();
        datos[1]=txtdnif.getText();
        datos[2]=Combosexo.getSelectedItem().toString();
        datos[3]=txtaf.getText();
        datos[4]=txtnf.getText();
        datos[5]=cbxparentesco.getSelectedItem().toString();
        datos[6]=btn;
        model.addRow(datos);
        matriculadatos.tabla1.setModel(model);
        matriculadatos.idf.setText(txtidf.getText());
        
             matriculadatos.tabla1.getColumnModel().getColumn(0).setMaxWidth(0);
             matriculadatos.tabla1.getColumnModel().getColumn(0).setMinWidth(0);
             matriculadatos.tabla1.getColumnModel().getColumn(0).setPreferredWidth(0);
             
             matriculadatos.jButton2.setEnabled(true);
 }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupMenu1 = new javax.swing.JPopupMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jPopupMenu2 = new javax.swing.JPopupMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jPopupMenu3 = new javax.swing.JPopupMenu();
        jMenuItem3 = new javax.swing.JMenuItem();
        jPopupMenu4 = new javax.swing.JPopupMenu();
        jMenuItem4 = new javax.swing.JMenuItem();
        jPopupMenu5 = new javax.swing.JPopupMenu();
        jMenuItem5 = new javax.swing.JMenuItem();
        panel2 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        txtbf = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        txtidfamiliar1 = new javax.swing.JTextField();
        jPanel7 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        txtnf = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        txtaf = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        txtec = new javax.swing.JTextField();
        txto = new javax.swing.JTextField();
        txtd = new javax.swing.JTextField();
        txtt = new javax.swing.JTextField();
        txttm = new javax.swing.JTextField();
        Combosexo = new javax.swing.JComboBox();
        txtdnif = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        cbxparentesco = new javax.swing.JComboBox();
        btnagregarf = new javax.swing.JButton();
        btnaf = new javax.swing.JButton();
        txtidf = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        fecha = new com.toedter.calendar.JDateChooser();

        jMenuItem1.setText("Editar");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jPopupMenu1.add(jMenuItem1);

        jMenuItem2.setText("Editar");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jPopupMenu2.add(jMenuItem2);

        jMenuItem3.setText("Editar");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jPopupMenu3.add(jMenuItem3);

        jMenuItem4.setText("Editar");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jPopupMenu4.add(jMenuItem4);

        jMenuItem5.setText("Editar");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jPopupMenu5.add(jMenuItem5);

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Ingresar Familiar", 0, 0, new java.awt.Font("Arial", 0, 12), java.awt.Color.black)); // NOI18N

        jLabel10.setText("Número de Documento:");

        txtbf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtbfActionPerformed(evt);
            }
        });
        txtbf.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtbfKeyTyped(evt);
            }
        });

        jButton3.setText("Buscar");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("Volver");

        jButton5.setText("Nuevo Familiar");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10)
                    .addComponent(jButton3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jButton4)
                        .addGap(39, 39, 39)
                        .addComponent(jButton5))
                    .addComponent(txtbf, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(txtbf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton3)
                    .addComponent(jButton4)
                    .addComponent(jButton5))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

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

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtidfamiliar1, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 693, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(47, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addComponent(txtidfamiliar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(178, Short.MAX_VALUE))
        );

        panel2.addTab("Buscar Familiar", jPanel2);

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos Pariente"));

        jLabel13.setText("Nombre");

        jLabel14.setText("Apellido");

        jLabel15.setText("DNI");

        jLabel16.setText("Sexo");

        jLabel17.setText("Fecha de Nacimiento");

        jLabel18.setText("Estado Civil");

        jLabel19.setText("Ocupación");

        jLabel20.setText("Dirección");

        jLabel21.setText("Telefono");

        jLabel22.setText("Telefono Movil");

        txtec.setComponentPopupMenu(jPopupMenu1);

        txto.setComponentPopupMenu(jPopupMenu2);

        txtd.setComponentPopupMenu(jPopupMenu3);

        txtt.setComponentPopupMenu(jPopupMenu4);
        txtt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txttKeyTyped(evt);
            }
        });

        txttm.setComponentPopupMenu(jPopupMenu5);
        txttm.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txttmKeyTyped(evt);
            }
        });

        Combosexo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Masculino", "Femenino" }));

        txtdnif.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtdnifKeyTyped(evt);
            }
        });

        jLabel23.setText("Parentesco");

        cbxparentesco.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Padre", "Madre", "Hermano/a", "Abuelo/a", "Tio/a" }));

        btnagregarf.setText("Guardar Familiar");
        btnagregarf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnagregarfActionPerformed(evt);
            }
        });

        btnaf.setText("Actualizar Datos");
        btnaf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnafActionPerformed(evt);
            }
        });

        jButton1.setText("Agregar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel13)
                                    .addComponent(jLabel14)
                                    .addComponent(jLabel15))
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel7Layout.createSequentialGroup()
                                        .addGap(80, 80, 80)
                                        .addComponent(txtdnif, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtaf, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtnf, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(jLabel16)
                                .addGap(93, 93, 93)
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel7Layout.createSequentialGroup()
                                        .addComponent(Combosexo, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(txtidf, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel7Layout.createSequentialGroup()
                                        .addComponent(fecha, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE)))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel20)
                            .addComponent(jLabel19)
                            .addComponent(jLabel18)
                            .addComponent(jLabel21)))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel17)
                            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(btnagregarf)
                                .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(cbxparentesco, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                                        .addComponent(jButton1)
                                        .addGap(90, 90, 90))
                                    .addComponent(jLabel22, javax.swing.GroupLayout.Alignment.TRAILING))))))
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txtec, javax.swing.GroupLayout.DEFAULT_SIZE, 237, Short.MAX_VALUE)
                            .addComponent(txto)
                            .addComponent(txtd)
                            .addComponent(txtt)
                            .addComponent(txttm))
                        .addGap(22, 22, 22))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnaf)
                        .addGap(43, 43, 43))))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(jLabel18)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel19))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel20))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel21))
                        .addGap(11, 11, 11)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txttm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel22)))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(txtec, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(6, 6, 6))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                                .addComponent(txtnf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtaf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel14))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel15)
                            .addComponent(txtdnif, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Combosexo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel16)
                            .addComponent(txtidf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel17)
                            .addComponent(fecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(12, 12, 12)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbxparentesco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 105, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnagregarf)
                    .addComponent(btnaf)
                    .addComponent(jButton1))
                .addGap(53, 53, 53))
        );

        panel2.addTab("Datos Familiar", jPanel7);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panel2)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panel2)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnagregarfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnagregarfActionPerformed
        // TODO add your handling code here:
        try {
            PreparedStatement pps = cn.prepareStatement("INSERT INTO familiar (Nombre, Apellido, DNI, Sexo, Fecha_Nacimiento, Estado_Civil, Ocupacion, Direccion, Telefono, Telefono_Movil, Parentesco) VALUES (?,?,?,?,?,?,?,?,?,?,?)");

            pps.setString(1, txtnf.getText());
            pps.setString(2, txtaf.getText());
            pps.setString(3, (txtdnif.getText()));
            pps.setString(4, Combosexo.getSelectedItem().toString());
            pps.setDate(5, new java.sql.Date(fecha.getDate().getTime()));
            pps.setString(6, txtec.getText());
            pps.setString(7, txto.getText());
            pps.setString(8, txtd.getText());
            pps.setString(9, (txtt.getText()));
            pps.setString(10, (txttm.getText()));
            pps.setString(11, cbxparentesco.getSelectedItem().toString());

            int res = pps.executeUpdate();
            if (res > 0) {
                JOptionPane.showMessageDialog(null, "Familiar Guardado Correctamente");
                PreparedStatement ps=cn.prepareStatement ("select idfamiliar from familiar");
         rs = ps.executeQuery();
            while (rs.next()) {
                txtidf.setText(rs.getString("idfamiliar"));}
                
                this.setVisible(false);
                ver();
         
            } else {
                JOptionPane.showMessageDialog(null, "Error al Guardar Familiar");

            }

        } catch (Exception e) {
        }


    }//GEN-LAST:event_btnagregarfActionPerformed

    private void txtbfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtbfActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtbfActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        verfamiliar(txtbf.getText());
        try {
            PreparedStatement pps = cn.prepareStatement("select idfamiliar from familiar where DNI=?");
            pps.setInt(1, Integer.parseInt(txtbf.getText()));
            rs = pps.executeQuery();
            if (rs.next()) {
                txtidfamiliar1.setText(rs.getString("idfamiliar"));
            }
        } catch (SQLException | NumberFormatException e) {
        }

    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here: Buscar alumno
        panel2.setSelectedIndex(1);
        panel2.setEnabledAt(1, true);
        btnaf.setVisible(false);
        jButton1.setVisible(false);
        btnaf.setVisible(false);
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:
        int column = jTable1.getColumnModel().getColumnIndexAtX(evt.getX());
        int row = evt.getY() / jTable1.getRowHeight();

        if (row < jTable1.getRowCount() && row >= 0 && column < jTable1.getColumnCount() && column >= 0) {

            Object value = jTable1.getValueAt(row, column);
            if (value instanceof JButton) {
                ((JButton) value).doClick();
                JButton boton = (JButton) value;
            }
            try {
                    panel2.setSelectedIndex(1);
                    panel2.setEnabledAt(1, true);
                    
                    PreparedStatement pps=cn.prepareStatement("SELECT idfamiliar, Nombre, Apellido, DNI, Sexo, Fecha_Nacimiento, Estado_Civil, Ocupacion, Direccion, Telefono, Telefono_Movil, Parentesco FROM familiar WHERE idfamiliar=?" ); 
            pps.setInt(1, Integer.parseInt(txtidfamiliar1.getText()));
            rs = pps.executeQuery();
            if (rs.next()) {
                txtidf.setText(rs.getString("idfamiliar"));
                txtnf.setText(rs.getString("Nombre"));
                txtaf.setText(rs.getString("Apellido"));
                txtdnif.setText(rs.getString("DNI"));
                Combosexo.setSelectedItem(rs.getString("Sexo"));
                
                fecha.setDate(rs.getDate("Fecha_Nacimiento"));
                txtec.setText(rs.getString("Estado_Civil"));
                txto.setText(rs.getString("Ocupacion"));
                txtd.setText(rs.getString("Direccion"));
                txtt.setText(rs.getString("Telefono"));
                txttm.setText(rs.getString("Telefono_Movil"));
                cbxparentesco.setSelectedItem(rs.getString("Parentesco"));
                
                txtnf.setEnabled(false);
                txtaf.setEnabled(false);
                txtdnif.setEnabled(false);
                Combosexo.setEnabled(false);
                fecha.setEnabled(false);
                txtec.setEnabled(false);
                txto.setEnabled(false);
                txtd.setEnabled(false);
                txtt.setEnabled(false);
                txttm.setEnabled(false);
                cbxparentesco.setEnabled(false); 
                
                btnagregarf.setVisible(false);
                btnaf.setVisible(false);
                jButton1.setVisible(true);
            }
                

            } catch (Exception e) {
            }

        }

    }//GEN-LAST:event_jTable1MouseClicked

    private void btnafActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnafActionPerformed
        // TODO add your handling code here:
        try {
             PreparedStatement pps=cn.prepareStatement("UPDATE familiar SET  Nombre=?, Apellido=?, DNI=?, Sexo=?, Fecha_Nacimiento=?, Estado_Civil=?, Ocupacion=?, Direccion=?, Telefono=?, Telefono_Movil=?, Parentesco=? WHERE idfamiliar=?" );
             pps.setString(1, txtnf.getText());
             pps.setString(2, txtaf.getText());
             pps.setString(3,(txtdnif.getText()) );
             pps.setString(4, Combosexo.getSelectedItem().toString());
             pps.setDate(5, new java.sql.Date(fecha.getDate().getTime()));
             pps.setString(6, txtec.getText());
             pps.setString(7, txto.getText());
             pps.setString(8, txtd.getText());
             pps.setString(9,(txtt.getText()) );
             pps.setString(10,(txttm.getText()) );
             pps.setString(11, cbxparentesco.getSelectedItem().toString());
             pps.setInt(12, Integer.parseInt(txtidf.getText()));
      
      
      
      int res = pps.executeUpdate();
            if (res > 0) {
                JOptionPane.showMessageDialog(null, "Familiar Modificado");
                this.setVisible(false);
                ver();
            } else {
                JOptionPane.showMessageDialog(null, "Error al Modificar Familiar");
                
            }
        } catch (Exception e) {
        }
     
                                   
        
    }//GEN-LAST:event_btnafActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        txtec.setEnabled(true);
        btnaf.setVisible(true);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        // TODO add your handling code here:
        txto.setEnabled(true);
        btnaf.setVisible(true);
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        // TODO add your handling code here:
        txtd.setEnabled(true);
        btnaf.setVisible(true);
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        // TODO add your handling code here:
        txtt.setEnabled(true);
        btnaf.setVisible(true);
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        // TODO add your handling code here:
        txttm.setEnabled(true);
        btnaf.setVisible(true);
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        ver();
        this.setVisible(false);
        matriculadatos.jComboBox1.setEnabled(true);
        matriculadatos.jComboBox2.setEnabled(true);
        matriculadatos.jBmatricular.setEnabled(false);
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void txtbfKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtbfKeyTyped
        // TODO add your handling code here:
        char validar=evt.getKeyChar();
        
        if(Character.isLetter(validar)){
        getToolkit().beep();
        evt.consume();
        JOptionPane.showMessageDialog(rootPane, "Ingresar solo Numeros");
        }
    }//GEN-LAST:event_txtbfKeyTyped

    private void txtdnifKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtdnifKeyTyped
        // TODO add your handling code here:
        char validar=evt.getKeyChar();
        
        if(Character.isLetter(validar)){
        getToolkit().beep();
        evt.consume();
        JOptionPane.showMessageDialog(rootPane, "Ingresar solo Numeros");
        }
    }//GEN-LAST:event_txtdnifKeyTyped

    private void txttKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txttKeyTyped
        // TODO add your handling code here:
        char validar=evt.getKeyChar();
        
        if(Character.isLetter(validar)){
        getToolkit().beep();
        evt.consume();
        JOptionPane.showMessageDialog(rootPane, "Ingresar solo Numeros");
        }
    }//GEN-LAST:event_txttKeyTyped

    private void txttmKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txttmKeyTyped
        // TODO add your handling code here:
        char validar=evt.getKeyChar();
        
        if(Character.isLetter(validar)){
        getToolkit().beep();
        evt.consume();
        JOptionPane.showMessageDialog(rootPane, "Ingresar solo Numeros");
        }
    }//GEN-LAST:event_txttmKeyTyped

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox Combosexo;
    private javax.swing.JButton btnaf;
    private javax.swing.JButton btnagregarf;
    private javax.swing.JComboBox cbxparentesco;
    private com.toedter.calendar.JDateChooser fecha;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JPopupMenu jPopupMenu2;
    private javax.swing.JPopupMenu jPopupMenu3;
    private javax.swing.JPopupMenu jPopupMenu4;
    private javax.swing.JPopupMenu jPopupMenu5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    public static javax.swing.JTabbedPane panel2;
    private javax.swing.JTextField txtaf;
    private javax.swing.JTextField txtbf;
    private javax.swing.JTextField txtd;
    private javax.swing.JTextField txtdnif;
    private javax.swing.JTextField txtec;
    public static javax.swing.JTextField txtidf;
    private javax.swing.JTextField txtidfamiliar1;
    private javax.swing.JTextField txtnf;
    private javax.swing.JTextField txto;
    private javax.swing.JTextField txtt;
    private javax.swing.JTextField txttm;
    // End of variables declaration//GEN-END:variables
conectar cc=new conectar();
Connection cn=cc.conexion();
}
