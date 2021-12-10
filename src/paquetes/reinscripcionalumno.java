
package paquetes;

import conexion.conectar;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import static paquetes.matriculap2.Table1;
import static paquetes.matriculap2.Table2;
import static paquetes.matriculap2.idcl;
import static paquetes.matriculap2.idcursodivision;
import reportes.reportes;
import tesis.render;


public class reinscripcionalumno extends javax.swing.JInternalFrame {


ResultSet rs;

  
    public reinscripcionalumno() {
      initComponents();
      monto();
     txtida.setVisible(false);
     idmontomatricula.setVisible(false);
     cd.setVisible(false);
     idf.setVisible(false);
     txtmonto.setVisible(false);
     cd.setText(matriculap2.idcursodivision.getText());
//     jButton1.setEnabled(true);
//    jBmatricular.setEnabled(false);
    //datosalumno();
      panel.setEnabledAt(2, false); 
      btnaf.setEnabled(false);
      btnguardarf.setEnabled(false);
      btnagregarf.setEnabled(false);
     txtidf.setVisible(false);
     txtidfamiliar1.setVisible(false);
     txtida.setVisible(false);
     txtestado.setEnabled(false);
     txtidestado.setVisible(false);
     jBmatricular1.setVisible(false);
     txtidcdanterior.setVisible(false);
     txtaño2.setVisible(false);
     txtcd.setVisible(false);
    }
  
   void monto (){
   
    try {
                PreparedStatement pps=cn.prepareStatement("SELECT * FROM montomatricula");
                rs   = pps.executeQuery();
      while (rs.next()) {
      this.jComboBox1.addItem(rs.getString("descripcion")+ " $ " +rs.getString("monto"));
      }
                 } catch (Exception e) {
            }    
   
   }
 
   void idmonto (String valor){
  try {
           PreparedStatement pps=cn.prepareStatement ("SELECT idmontomatricula FROM montomatricula where monto='"+valor+"'");
      
      rs   = pps.executeQuery();
      while (rs.next()) {
      idmontomatricula.setText(rs.getString(1));
      
      }
      } catch (Exception e) {
      }
 }
   
    void idcdanterior (String valor,String valor1 ){
  try {
           PreparedStatement pps=cn.prepareStatement ("select cd.idcurso_division, idalumno, c.descripcion, cd.division from detalle_matricula dm, ciclolectivo cl, curso_division cd, curso c where dm.idciclolectivo=cl.idciclolectivo "
                   + "and idalumno='"+valor+"' and cd.idcurso=c.idcurso and cl.año < '"+valor1+"' LIMIT 1");
      
      rs   = pps.executeQuery();
      while (rs.next()) {
      txtidcdanterior.setText(rs.getString(1));
      txtcd.setText(rs.getString("c.descripcion")+ " Division "  +rs.getString("cd.division"));
      
      }
      } catch (Exception e) {
      }
 }
   
   void datosalumno(){
       
       try {
            PreparedStatement pps=cn.prepareStatement("select  Nombre, Apellido, DNI, Sexo, Fecha_Nacimiento, Direccion, Localidad, Telefono, Telefono_Movil from alumno where idAlumno=?");
              pps.setInt(1, Integer.parseInt(txtida.getText()));
            rs = pps.executeQuery();
            if (rs.next()) {
//                txtida.setText(rs.getString("idAlumno"));
                txtna.setText(rs.getString("Nombre"));
                txtaa.setText(rs.getString("Apellido"));
                txtdnia.setText(rs.getString("DNI"));
                jComboBoxsexo.setSelectedItem(rs.getString("Sexo"));
                
                fecha.setDate(rs.getDate("Fecha_Nacimiento"));
                txtdireccion.setText(rs.getString("Direccion"));
                txtlocalidad.setText(rs.getString("Localidad"));
                txttel.setText(rs.getString("Telefono"));
                txttelm.setText(rs.getString("Telefono_Movil"));
            
               txtna.setEnabled(false);
              txtaa.setEnabled(false);
                txtdnia.setEnabled(false);
                jComboBoxsexo.setEnabled(false);
                fecha.setEnabled(false);
                txtdireccion.setEnabled(false);
                txtlocalidad.setEnabled(false);
                txttel.setEnabled(false);
                txttelm.setEnabled(false);
                
                txtida.setEnabled(false);
               
                actualizar.setVisible(false);
               // matriculadatos.continuar.setVisible(true);
                
                //matriculadatos.jComboBox1.setEnabled(false);
                //matriculadatos.jComboBox2.setEnabled(false);
                //matriculadatos.jButton3.setEnabled(false);
                
//                jPanel4.setVisible(false);
            }
       } catch (Exception e) {
       }
   
   }
   
   void cargarfamiliares ( String valor){
    tabla1.setDefaultRenderer(Object.class, new render());
     
      JButton btn = new JButton("Seleccionar");
      
 
   String [] titulos= {"ID","Nro. Documento", "Apellido","Nombre","Parentesco", "Acción"};
    DefaultTableModel modelo = new  DefaultTableModel(null,titulos)
    {
        @Override
        public boolean isCellEditable(int row, int column){
            return false;
        }
    };
           tabla1.setModel(modelo);
   
    Object datos []= new Object[6];
    String sql="select f.idfamiliar, f.DNI, f.Apellido, f.Nombre, f.parentesco from familiar f, alumno a, familiar_alumno fa "
            + "where fa.idalumno=a.idalumno and fa.idfamiliar=f.idfamiliar and a.idalumno='"+valor+"'";
   try {
            Statement st = cn.createStatement();
             rs = st.executeQuery(sql);
            while(rs.next()) {
                datos[0]=rs.getString("f.idfamiliar");
                datos[1]=rs.getString("f.DNI");
                datos[2]=rs.getString("f.Apellido");
                datos[3]=rs.getString("f.Nombre");
                datos[4]=rs.getString("f.parentesco");
                datos[5]=btn;
                modelo.addRow(datos);
                
            }
            tabla1.setModel(modelo);
            tabla1.setRowHeight(15);
            
            tabla1.getColumnModel().getColumn(0).setMaxWidth(0);
            tabla1.getColumnModel().getColumn(0).setMinWidth(0);
            tabla1.getColumnModel().getColumn(0).setPreferredWidth(0);
            
        } 
   
   catch (SQLException ex) {
            System.out.println(ex);
        }
   }
 
   void verfamiliar (String valor){
 
 jTable1.setDefaultRenderer(Object.class, new render());
     
      JButton btn = new JButton("Seleccionar");
      
 
   String [] titulos= {"Id.familiar","Nro. Documento", "Apellido","Nombre","Tipo Vinculo", "Acción"};
    DefaultTableModel modelo = new  DefaultTableModel(null,titulos)
    {
        @Override
        public boolean isCellEditable(int row, int column){
            return false;
        }
    };
           jTable1.setModel(modelo);
   
   Object datos []= new Object[6];
    String sql="select idfamiliar, DNI, Sexo, Apellido, Nombre, Parentesco from familiar where DNI='"+valor+"'";
   try {
            Statement st = cn.createStatement();
             rs = st.executeQuery(sql);
           if(rs.next())
            {
                datos[0]=rs.getString("idfamiliar");
                datos[1]=rs.getString("DNI");
                
                datos[2]=rs.getString("Apellido");
                datos[3]=rs.getString("Nombre");
                datos[4]=rs.getString("Parentesco");
                datos[5]=btn;
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
            
        }

 
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
        panel = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtna = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtaa = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtdnia = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jComboBoxsexo = new javax.swing.JComboBox();
        txtida = new javax.swing.JTextField();
        fecha = new com.toedter.calendar.JDateChooser();
        jPanel5 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        txtdireccion = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtlocalidad = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txttel = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txttelm = new javax.swing.JFormattedTextField();
        actualizar = new javax.swing.JButton();
        txtaño2 = new javax.swing.JTextField();
        txtcd = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jButton6 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabla1 = new javax.swing.JTable();
        idf = new javax.swing.JTextField();
        paneagregarfamiliar = new javax.swing.JTabbedPane();
        jPanel7 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        txtbf = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        txtidfamiliar1 = new javax.swing.JTextField();
        jPanel9 = new javax.swing.JPanel();
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
        btnguardarf = new javax.swing.JButton();
        btnaf = new javax.swing.JButton();
        txtidf = new javax.swing.JTextField();
        btnagregarf = new javax.swing.JButton();
        fecha1 = new com.toedter.calendar.JDateChooser();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox();
        jBmatricular = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox();
        idmontomatricula = new javax.swing.JTextField();
        txtmonto = new javax.swing.JTextField();
        cd = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        txtestado = new javax.swing.JTextField();
        txtidestado = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jBmatricular1 = new javax.swing.JButton();
        txtidcdanterior = new javax.swing.JTextField();
        jButton5 = new javax.swing.JButton();

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

        setClosable(true);
        setTitle("REISCRIPCION ALUMNO");

        jPanel1.setPreferredSize(new java.awt.Dimension(853, 350));

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos Personales"));

        jLabel2.setText("Nombre");

        jLabel3.setText("Apellido");

        jLabel4.setText("DNI");

        txtdnia.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtdniaKeyTyped(evt);
            }
        });

        jLabel5.setText("Sexo");

        jLabel6.setText("Fecha  Nacimiento");

        jComboBoxsexo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Masculino", "Femenino" }));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5))
                        .addGap(63, 63, 63)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jComboBoxsexo, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtida, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtaa)
                            .addComponent(txtdnia)
                            .addComponent(txtna)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(fecha, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 72, Short.MAX_VALUE)))
                .addGap(50, 50, 50))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtna, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtaa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtdnia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jComboBoxsexo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(fecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(30, Short.MAX_VALUE))
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Contacto"));

        jLabel7.setText("Dirección");

        txtdireccion.setComponentPopupMenu(jPopupMenu1);

        jLabel8.setText("Localidad");

        txtlocalidad.setComponentPopupMenu(jPopupMenu2);

        jLabel9.setText("Telefono");

        txttel.setComponentPopupMenu(jPopupMenu3);
        txttel.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txttelKeyTyped(evt);
            }
        });

        jLabel11.setText("Telefono Movil");

        txttelm.setComponentPopupMenu(jPopupMenu4);
        txttelm.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txttelmKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9)
                    .addComponent(jLabel11))
                .addGap(8, 8, 8)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txttel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 338, Short.MAX_VALUE)
                    .addComponent(txtlocalidad)
                    .addComponent(txttelm)
                    .addComponent(txtdireccion, javax.swing.GroupLayout.Alignment.LEADING))
                .addGap(26, 26, 26))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtdireccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtlocalidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txttel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txttelm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        actualizar.setText("Actualizar Datos");
        actualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                actualizarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(txtaño2, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtcd, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(76, 76, 76)
                        .addComponent(actualizar)))
                .addContainerGap(14, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(actualizar))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtaño2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtcd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(47, Short.MAX_VALUE))
        );

        panel.addTab("Datos Estudiantes", jPanel1);

        jButton6.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton6.setText("+ Agregar Familiar");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        tabla1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Nro. Documento", "Sexo", "Apellido", "Nombre", "Parentesco", "Acción"
            }
        ));
        jScrollPane2.setViewportView(tabla1);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton6)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 820, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(idf, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(90, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(idf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(127, Short.MAX_VALUE))
        );

        panel.addTab("Vinculo Familiar", jPanel2);

        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Ingresar Familiar", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 0, 12))); // NOI18N

        jLabel12.setText("Número de Documento:");

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

        jButton7.setText("Nuevo Familiar");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12)
                    .addComponent(jButton3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jButton4)
                        .addGap(39, 39, 39)
                        .addComponent(jButton7))
                    .addComponent(txtbf, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(txtbf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton3)
                    .addComponent(jButton4)
                    .addComponent(jButton7))
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

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtidfamiliar1, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 693, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(212, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addComponent(txtidfamiliar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(37, Short.MAX_VALUE))
        );

        paneagregarfamiliar.addTab("Buscar Familiar", jPanel7);

        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos Pariente"));

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

        txtd.setComponentPopupMenu(jPopupMenu1);

        txtt.setComponentPopupMenu(jPopupMenu3);
        txtt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txttKeyTyped(evt);
            }
        });

        txttm.setComponentPopupMenu(jPopupMenu4);
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

        btnguardarf.setText("Guardar Familiar");
        btnguardarf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnguardarfActionPerformed(evt);
            }
        });

        btnaf.setText("Actualizar Datos");
        btnaf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnafActionPerformed(evt);
            }
        });

        btnagregarf.setText("Agregar Familiar");
        btnagregarf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnagregarfActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel13)
                                    .addComponent(jLabel14)
                                    .addComponent(jLabel15))
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel9Layout.createSequentialGroup()
                                        .addGap(80, 80, 80)
                                        .addComponent(txtdnif, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtaf, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtnf, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addComponent(jLabel16)
                                .addGap(93, 93, 93)
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel9Layout.createSequentialGroup()
                                        .addComponent(Combosexo, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(txtidf, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel9Layout.createSequentialGroup()
                                        .addComponent(fecha1, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE)))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 195, Short.MAX_VALUE)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel20)
                            .addComponent(jLabel19)
                            .addComponent(jLabel18)
                            .addComponent(jLabel21)))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel17)
                            .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(btnguardarf)
                                .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addComponent(cbxparentesco, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel22, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                                        .addComponent(btnagregarf)
                                        .addGap(102, 102, 102)))))))
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txtec, javax.swing.GroupLayout.DEFAULT_SIZE, 237, Short.MAX_VALUE)
                            .addComponent(txto)
                            .addComponent(txtd)
                            .addComponent(txtt)
                            .addComponent(txttm))
                        .addGap(22, 22, 22))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnaf)
                        .addGap(43, 43, 43))))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(jLabel18)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel19))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel20))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel21))
                        .addGap(11, 11, 11)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txttm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel22)))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(txtec, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(6, 6, 6))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                                .addComponent(txtnf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtaf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel14))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel15)
                            .addComponent(txtdnif, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Combosexo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel16)
                            .addComponent(txtidf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel17)
                            .addComponent(fecha1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(12, 12, 12)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbxparentesco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnguardarf)
                    .addComponent(btnaf)
                    .addComponent(btnagregarf))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        paneagregarfamiliar.addTab("Datos Familiar", jPanel9);

        panel.addTab("Agregar Familiar", paneagregarfamiliar);

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Detalle Matricula"));

        jLabel1.setText("Monto Matricula");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Sellecionar Monto" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jBmatricular.setText("Matricular Alumno");
        jBmatricular.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBmatricularActionPerformed(evt);
            }
        });

        jLabel10.setText("Repitente");

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Si", "No" }));

        jLabel24.setText("Estado Matricula");

        txtestado.setText("Inscripto");
        txtestado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtestadoActionPerformed(evt);
            }
        });

        txtidestado.setText("1");

        jButton1.setText("DEBE REPETIR DE AÑO");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jBmatricular1.setText("Matricular Alumno REPITENTE");
        jBmatricular1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBmatricular1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jBmatricular1, javax.swing.GroupLayout.PREFERRED_SIZE, 455, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtidcdanterior))
                        .addGap(38, 38, 38)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(45, 45, 45)
                                .addComponent(jLabel1)
                                .addGap(18, 18, 18)
                                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(57, 57, 57)
                                .addComponent(jLabel24)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtestado))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(idmontomatricula, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jBmatricular, javax.swing.GroupLayout.PREFERRED_SIZE, 455, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(18, 18, 18)
                .addComponent(txtidestado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addComponent(txtmonto, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(42, 42, 42)
                        .addComponent(cd, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addComponent(jLabel24)
                    .addComponent(txtestado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtidestado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jBmatricular)
                    .addComponent(idmontomatricula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtmonto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtidcdanterior, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jBmatricular1)
                .addContainerGap())
        );

        jButton5.setText("Atras");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton5)
                    .addComponent(panel)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(29, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panel, javax.swing.GroupLayout.DEFAULT_SIZE, 321, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton5)
                .addGap(43, 43, 43))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        txtdireccion.setEnabled(true);
        actualizar.setVisible(true);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        // TODO add your handling code here:
        txtlocalidad.setEnabled(true);
        actualizar.setVisible(true);
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        // TODO add your handling code here:
        txttel.setEnabled(true);
        actualizar.setVisible(true);
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        // TODO add your handling code here:
         txttelm.setEnabled(true);
         actualizar.setVisible(true);
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void actualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_actualizarActionPerformed
        // TODO add your handling code here:
        try {
            PreparedStatement pps=cn.prepareStatement("UPDATE alumno SET Nombre=?, Apellido=?, DNI=?, Sexo=?, Fecha_Nacimiento=?, "
         + "Direccion=?, Localidad=?, Telefono=?, Telefono_Movil=? where idAlumno=?");
            pps.setString(1, txtna.getText());
   pps.setString(2, txtaa.getText()); 
  pps.setString(3,(txtdnia.getText()) );
   pps.setString(4, jComboBoxsexo.getSelectedItem().toString());
  pps.setDate(5, new java.sql.Date(fecha.getDate().getTime()));
   pps.setString(6, txtdireccion.getText());
     pps.setString(7, txtlocalidad.getText());
     pps.setString(8,(txttel.getText()) ); 
     pps.setString(9,(txttelm.getText()) );
      
         pps.setInt(10, Integer.parseInt(txtida.getText()));

        int res = pps.executeUpdate();
           if (res > 0) {
                JOptionPane.showMessageDialog(null, "Alumno Modificado");
                panel.setEnabledAt(1, true);
                
            } else {
                JOptionPane.showMessageDialog(null, "Error al Modificar Alumno");
              
            }
            
        
        }catch (Exception e) {
        }
    }//GEN-LAST:event_actualizarActionPerformed

    private void jBmatricularActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBmatricularActionPerformed
        // TODO add your handling code here:
       int bool = JOptionPane.showConfirmDialog(null,"CONFIRMAR MATRICULACIÓN","Proceso de Matriculación",JOptionPane.YES_NO_OPTION);  
       
       if(bool == JOptionPane.YES_OPTION){
       try {
    PreparedStatement pps=cn.prepareStatement("UPDATE detalle_matricula SET idmontomatricula=?, repite=?, idestado_matricula=1  where idalumno=?");
     
    pps.setInt(1,Integer.parseInt(idmontomatricula.getText()));
    pps.setString(2, jComboBox2.getSelectedItem().toString());
    pps.setInt(3,Integer.parseInt(txtida.getText()));
       int res = pps.executeUpdate();
       if(res > 0) {
       JOptionPane.showMessageDialog(null, "Alumno Matriculado");
       
       
       JOptionPane.showMessageDialog(null, " Generar Pago de Matricula");
        this.dispose();
       
        matriculap2.btnact.setVisible(true);
        matriculap2.jButton1.setEnabled(false);
        matriculap2.btnfinalizar.setVisible(true);
        matriculap2.jButton2.setEnabled(false);
        
        try {
               reportes re =new reportes ();
               re.pago(txtida.getText(),gestionmatricula.año.getText());
           } catch (Exception e) {
               System.out.print(e.getMessage());
           }
        
       }
   else
       {
       JOptionPane.showMessageDialog(null, "Error al Matricular Alumno");
       }
    
        } catch (Exception e) {
        }
       }else
        {
        }
    
        
    }//GEN-LAST:event_jBmatricularActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
 if (jComboBox1.getSelectedIndex()==0){
 txtmonto.setText(null);
 idmontomatricula.setText(null);
 
 } else {
 String item =jComboBox1.getSelectedItem().toString();
 String [] data=item.split(" ");
 
 
 txtmonto.setText(data[3]);
        }
  idmonto(txtmonto.getText());  
        
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        this.dispose();
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
                paneagregarfamiliar.setSelectedIndex(1);
                paneagregarfamiliar.setEnabledAt(1, true);

                PreparedStatement pps=cn.prepareStatement("SELECT idfamiliar, Nombre, Apellido, DNI, Sexo, Fecha_Nacimiento, Estado_Civil, Ocupacion, Direccion, Telefono, Telefono_Movil, Parentesco FROM familiar WHERE idfamiliar=?" );
                pps.setInt(1, Integer.parseInt(txtidfamiliar1.getText()));
                rs = pps.executeQuery();
                if (rs.next()) {
                    txtidf.setText(rs.getString("idfamiliar"));
                    txtnf.setText(rs.getString("Nombre"));
                    txtaf.setText(rs.getString("Apellido"));
                    txtdnif.setText(rs.getString("DNI"));
                    Combosexo.setSelectedItem(rs.getString("Sexo"));
                    fecha1.setDate(rs.getDate("Fecha_Nacimiento"));
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
                    fecha1.setEnabled(false);
                    txtec.setEnabled(false);
                    txto.setEnabled(false);
                    txtd.setEnabled(false);
                    txtt.setEnabled(false);
                    txttm.setEnabled(false);
                    cbxparentesco.setEnabled(false);

//                    btnguardarf.setVisible(false);
//                    btnaf.setVisible(false);
                    btnagregarf.setEnabled(true);
                }

            } catch (Exception e) {
            }

        }
    }//GEN-LAST:event_jTable1MouseClicked

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here: Buscar alumno
        paneagregarfamiliar.setSelectedIndex(1);
        paneagregarfamiliar.setEnabledAt(1, true);
        btnguardarf.setEnabled(true);
        btnagregarf.setEnabled(true);
        btnaf.setEnabled(false);
    }//GEN-LAST:event_jButton7ActionPerformed

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

    private void txtbfKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtbfKeyTyped
        // TODO add your handling code here:
        char validar=evt.getKeyChar();

        if(Character.isLetter(validar)){
            getToolkit().beep();
            evt.consume();
            JOptionPane.showMessageDialog(rootPane, "Ingresar solo Numeros");
        }
    }//GEN-LAST:event_txtbfKeyTyped

    private void txtbfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtbfActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtbfActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        
        panel.setEnabledAt(2, true); 
       panel.setSelectedIndex(2);
        
        
//        familiar md = new familiar();
//        ventana.jdpescritorio.add(md);
//        md.toFront();
//        md.setVisible(true);
//        familiar.panel2.setEnabledAt(1, false);
    }//GEN-LAST:event_jButton6ActionPerformed

    private void txttelmKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txttelmKeyTyped
        // TODO add your handling code here:
        char validar=evt.getKeyChar();

        if(Character.isLetter(validar)){
            getToolkit().beep();
            evt.consume();
            JOptionPane.showMessageDialog(rootPane, "Ingresar solo Numeros");
        }
    }//GEN-LAST:event_txttelmKeyTyped

    private void txttelKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txttelKeyTyped
        // TODO add your handling code here:
        char validar=evt.getKeyChar();

        if(Character.isLetter(validar)){
            getToolkit().beep();
            evt.consume();
            JOptionPane.showMessageDialog(rootPane, "Ingresar solo Numeros");
        }
    }//GEN-LAST:event_txttelKeyTyped

    private void txtdniaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtdniaKeyTyped
        // TODO add your handling code here:
        char validar=evt.getKeyChar();

        if(Character.isLetter(validar)){
            getToolkit().beep();
            evt.consume();
            JOptionPane.showMessageDialog(rootPane, "Ingresar solo Numeros");
        }
    }//GEN-LAST:event_txtdniaKeyTyped

    private void btnagregarfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnagregarfActionPerformed
        // TODO add your handling code here:
        ////        ver();
        //        this.setVisible(false);
        //        matriculadatos.jComboBox1.setEnabled(true);
        //        matriculadatos.jComboBox2.setEnabled(true);
        //        matriculadatos.jBmatricular.setEnabled(false);

        try {
            PreparedStatement pps = cn.prepareStatement("insert into familiar_alumno (idAlumno, idfamiliar)  VALUES (?,?)");

            pps.setString(1, txtida.getText());
            pps.setString(2, txtidf.getText());
            int res = pps.executeUpdate();
            if (res > 0) {
                JOptionPane.showMessageDialog(null, "Familiar Agregado Correctamente");

                DefaultTableModel modelo3 = (DefaultTableModel)tabla1.getModel();
                tabla1.setModel(modelo3);
                modelo3.setRowCount(0);
                cargarfamiliares(txtida.getText());
                panel.setSelectedIndex(1);
                panel.setEnabledAt(2, false); 
                

            } else {
                JOptionPane.showMessageDialog(null, "Error al Guardar Familiar");

            }

        } catch (Exception e) {
        }

    }//GEN-LAST:event_btnagregarfActionPerformed

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
               
            } else {
                JOptionPane.showMessageDialog(null, "Error al Modificar Familiar");

            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_btnafActionPerformed

    private void btnguardarfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnguardarfActionPerformed
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

                

            } else {
                JOptionPane.showMessageDialog(null, "Error al Guardar Familiar");

            }

        } catch (Exception e) {
        }
    }//GEN-LAST:event_btnguardarfActionPerformed

    private void txtdnifKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtdnifKeyTyped
        // TODO add your handling code here:
        char validar=evt.getKeyChar();

        if(Character.isLetter(validar)){
            getToolkit().beep();
            evt.consume();
            JOptionPane.showMessageDialog(rootPane, "Ingresar solo Numeros");
        }
    }//GEN-LAST:event_txtdnifKeyTyped

    private void txttmKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txttmKeyTyped
        // TODO add your handling code here:
        char validar=evt.getKeyChar();

        if(Character.isLetter(validar)){
            getToolkit().beep();
            evt.consume();
            JOptionPane.showMessageDialog(rootPane, "Ingresar solo Numeros");
        }
    }//GEN-LAST:event_txttmKeyTyped

    private void txttKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txttKeyTyped
        // TODO add your handling code here:
        char validar=evt.getKeyChar();

        if(Character.isLetter(validar)){
            getToolkit().beep();
            evt.consume();
            JOptionPane.showMessageDialog(rootPane, "Ingresar solo Numeros");
        }
    }//GEN-LAST:event_txttKeyTyped

    private void txtestadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtestadoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtestadoActionPerformed

    private void jBmatricular1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBmatricular1ActionPerformed
        // TODO add your handling code here:
        int bool = JOptionPane.showConfirmDialog(null,"CONFIRMAR MATRICULACIÓN "+" EL ALUMNO SE INSCRIBIRA EN"+txtcd.getText(),"Proceso Matriculación Alumno Repitente",JOptionPane.YES_NO_OPTION);  
       
       if(bool == JOptionPane.YES_OPTION){
       try {
    PreparedStatement pps=cn.prepareStatement("UPDATE detalle_matricula SET idcurso_division=?, idmontomatricula=?, repite=?, idestado_matricula=1  where idalumno=?");
    
     pps.setInt(1,Integer.parseInt(txtidcdanterior.getText()));
    pps.setInt(2,Integer.parseInt(idmontomatricula.getText()));
    pps.setString(3, jComboBox2.getSelectedItem().toString());
    pps.setInt(4,Integer.parseInt(txtida.getText()));
       int res = pps.executeUpdate();
       if(res > 0) {
       JOptionPane.showMessageDialog(null, "Alumno Matriculado");
       
       
       JOptionPane.showMessageDialog(null, " Generar Pago de Matricula");
        this.dispose();
       
        matriculap2.btnact.setVisible(true);
        matriculap2.jButton1.setEnabled(false);
        matriculap2.btnfinalizar.setVisible(true);
        matriculap2.jButton2.setEnabled(false);
        
        try {
               reportes re =new reportes ();
               re.pago(txtida.getText(),gestionmatricula.año.getText());
           } catch (Exception e) {
               System.out.print(e.getMessage());
           }
        
       }
   else
       {
       JOptionPane.showMessageDialog(null, "Error al Matricular Alumno");
       }
    
        } catch (Exception e) {
        }
       }else
        {
        }
    }//GEN-LAST:event_jBmatricular1ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        jBmatricular.setVisible(false);
        jBmatricular1.setVisible(true);
        idcdanterior(txtida.getText(), txtaño2.getText());
        jComboBox2.setSelectedItem(("Si"));
        jComboBox2.setEnabled(false);
    }//GEN-LAST:event_jButton1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox Combosexo;
    public static javax.swing.JButton actualizar;
    private javax.swing.JButton btnaf;
    private javax.swing.JButton btnagregarf;
    private javax.swing.JButton btnguardarf;
    private javax.swing.JComboBox cbxparentesco;
    public static javax.swing.JTextField cd;
    public static com.toedter.calendar.JDateChooser fecha;
    private com.toedter.calendar.JDateChooser fecha1;
    public static javax.swing.JTextField idf;
    private javax.swing.JTextField idmontomatricula;
    public static javax.swing.JButton jBmatricular;
    public static javax.swing.JButton jBmatricular1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    public static javax.swing.JComboBox jComboBox1;
    public static javax.swing.JComboBox jComboBox2;
    public static javax.swing.JComboBox jComboBoxsexo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    public static javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JPopupMenu jPopupMenu2;
    private javax.swing.JPopupMenu jPopupMenu3;
    private javax.swing.JPopupMenu jPopupMenu4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    public static javax.swing.JTabbedPane paneagregarfamiliar;
    public static javax.swing.JTabbedPane panel;
    public static javax.swing.JTable tabla1;
    public static javax.swing.JTextField txtaa;
    private javax.swing.JTextField txtaf;
    public static javax.swing.JTextField txtaño2;
    private javax.swing.JTextField txtbf;
    private javax.swing.JTextField txtcd;
    private javax.swing.JTextField txtd;
    public static javax.swing.JTextField txtdireccion;
    public static javax.swing.JTextField txtdnia;
    private javax.swing.JTextField txtdnif;
    private javax.swing.JTextField txtec;
    private javax.swing.JTextField txtestado;
    public static javax.swing.JTextField txtida;
    private javax.swing.JTextField txtidcdanterior;
    private javax.swing.JTextField txtidestado;
    public static javax.swing.JTextField txtidf;
    private javax.swing.JTextField txtidfamiliar1;
    public static javax.swing.JTextField txtlocalidad;
    private javax.swing.JTextField txtmonto;
    public static javax.swing.JTextField txtna;
    private javax.swing.JTextField txtnf;
    private javax.swing.JTextField txto;
    private javax.swing.JTextField txtt;
    public static javax.swing.JTextField txttel;
    public static javax.swing.JFormattedTextField txttelm;
    private javax.swing.JTextField txttm;
    // End of variables declaration//GEN-END:variables
conectar cc=new conectar();
Connection cn=cc.conexion();
}
