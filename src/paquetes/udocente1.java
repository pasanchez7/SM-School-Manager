
package paquetes;

import checkbox.Clase_CellEditor;
import checkbox.Clase_CellRender;
import conexion.conectar;
import conexion.conectar;
import java.awt.HeadlessException;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableColumn;
import static paquetes.nusuario.cboestados;
import static paquetes.ventana.jdpescritorio;
import reportes.reportes;
import tesis.render;
import java.util.Date;


public class udocente1 extends javax.swing.JInternalFrame {
ResultSet rs;
//DefaultTableModel modelo;
conectar cc=new conectar();
Connection cn=cc.conexion();  
    

public udocente1() {
        initComponents();
        //ltitulo.setVisible(false);       
        //combocurso.setVisible(false);
        vercurso_division();
        curso.setVisible(false);
        idasignatura.setVisible(false);
        division.setVisible(false);
        btnagregar.setVisible(false);
        idcv.setVisible(false);
        idusudocente.setVisible(false);
        asig.setVisible(false);
        txttipousuario.setEnabled(false);
       //fechaactual();
        clactual();
        txtidcl.setVisible(false);
        btna.setEnabled(false);
        btneliminar.setVisible(false);
        idtipousuario(txttipousuario.getText());
        txtidtipousu.setVisible(false);
        estados();
       txtidestado.setVisible(false);
        jButton5.setVisible(false);
        
    }

void estados (){
        
     try {
            java.sql.PreparedStatement pps=cn.prepareStatement ("SELECT idestado_usuarios FROM estado_usuarios where estado=?");
             pps.setString(1, cboestados.getSelectedItem().toString());
             
              
            ResultSet resultSet = pps.executeQuery();
            if (resultSet.next()) {
               txtidestado.setText(resultSet.getString("idestado_usuarios"));
               
               
            }
        }catch (Exception e) {
        }
 }

void vercurso_division (){
        
     try {
                PreparedStatement pps=cn.prepareStatement("SELECT curso.Descripcion, division "
                        + "FROM curso, curso_division "
                        + "where curso_division.idcurso=curso.idcurso order by curso.Descripcion");
                rs   = pps.executeQuery();
      while (rs.next()) {
      this.combocurso.addItem(rs.getString("Descripcion")+ " Division "  +rs.getString("division"));
      }
                 } catch (Exception e) {
            }
 }

void fechaactual (){
int resultado = 0;
    Date fecha = new Date();
    resultado=fecha.getMonth()+1;
     if (resultado ==1 || resultado ==2){
    clanterior();
     }
     else {
         clactual();
     }
}


void clactual (){
try {
                PreparedStatement pps=cn.prepareStatement("SELECT idciclolectivo, año  FROM ciclolectivo where año=year(now())");
                rs   = pps.executeQuery();
      while (rs.next()) {
         txtidcl.setText(rs.getString(1));
         año.setText(rs.getString(2));
      }
                 } catch (Exception e) {
            }
}

void clanterior (){
try {
                PreparedStatement pps=cn.prepareStatement("SELECT idciclolectivo, año  FROM ciclolectivo where año=year(now())-1");
                rs   = pps.executeQuery();
      while (rs.next()) {
         txtidcl.setText(rs.getString(1));
         año.setText(rs.getString(2));
      }
                 } catch (Exception e) {
            }
}

void tabla (String valor){

     DefaultTableModel modelo = (DefaultTableModel)jTable1.getModel();
     jTable1.setModel(modelo);          
{
};
      Object datos []= new Object[2];
      String sql="SELECT asignatura.nombre FROM asignacion_materias, asignatura, curso "
              + "where asignacion_materias.idcurso=curso.idcurso "
              + "and asignacion_materias.idasignatura=asignatura.idasignatura "
              + "and curso.Descripcion='"+valor+"'";
      try {
            Statement st = cn.createStatement();
             rs = st.executeQuery(sql);
            while(rs.next())
            {
                datos[0]=rs.getString(1);       
                modelo.addRow(datos);           
            }
            jTable1.setModel(modelo);
            jTable1.setRowHeight(20);        
        } 
   
   catch (SQLException ex) {
            Logger.getLogger(udocente1.class.getName()).log(Level.SEVERE, null, ex);
        }
}
 
void idcuro_division(String valor, String valor1){
try {
                PreparedStatement pps=cn.prepareStatement("SELECT idcurso_division "
                        + "FROM curso_division, curso where curso_division.idcurso=curso.idcurso "
                        + "and  curso.Descripcion='"+valor+"'and division='"+valor1+"'");
                rs   = pps.executeQuery();
      while (rs.next()) {
      idcv.setText(rs.getString(1));
      }
                 } catch (Exception e) {
            }
}

void idasignatura (String valor, String valor1){
try {
                PreparedStatement pps=cn.prepareStatement("SELECT asignatura.idasignatura "
                        + "FROM  asignacion_materias, curso, asignatura where asignacion_materias.idcurso=curso.idcurso "
                        + "and curso.Descripcion='"+valor+"'and asignacion_materias.idasignatura=asignatura.idasignatura "
                        + "and asignatura.nombre='"+valor1+"'");
                rs   = pps.executeQuery();
      while (rs.next()) {
      idasignatura.setText(rs.getString(1));
      }
                 } catch (Exception e) {
            }
}

void tabla2 (String valor, String valor1){
 DefaultTableModel modelo1 = (DefaultTableModel)jTable2.getModel();
 jTable2.setModel(modelo1);         
{               
   };
      Object datos []= new Object[4];
      String sql="SELECT ad.idasig_docente, c.Descripcion, cd.division, a.nombre, cl.año\n" +
"FROM asig_docente ad, curso c, asignatura a, curso_division cd, usuarios u, ciclolectivo cl\n" +
"where c.idcurso=cd.idcurso and a.idasignatura=ad.idasignatura and cd.idcurso_division=ad.idcurso_division\n" +
"and u.idusuarios=ad.idusuarios and ad.idciclolectivo=cl.idciclolectivo and ad.idusuarios='"+valor+"'and ad.idciclolectivo='"+valor1+"' "
              + "order by cl.año,c.Descripcion, a.idasignatura";
      try {
            Statement st = cn.createStatement();
             rs = st.executeQuery(sql);
            while(rs.next())
            {
                datos[0]=rs.getString("ad.idasig_docente");
                datos[1]=rs.getString("c.Descripcion")+" "+rs.getString("cd.division");
                datos[2]=rs.getString("a.nombre");
                datos[3]=rs.getString("cl.año");
                modelo1.addRow(datos);             
            }
            jTable2.setModel(modelo1);
            jTable2.setRowHeight(20);   
            
            jTable2.getColumnModel().getColumn(0).setMaxWidth(0);
            jTable2.getColumnModel().getColumn(0).setMinWidth(0);
            jTable2.getColumnModel().getColumn(0).setPreferredWidth(0);
            
           
        } 
   
   catch (SQLException ex) {
            Logger.getLogger(udocente1.class.getName()).log(Level.SEVERE, null, ex);
        }
      
            //int eli=jTable2.getRowCount();
            //txteliminar.setText(String.valueOf(eli));
      
            //int eli=jTable2.getRowCount();
            //txteliminar.setText(String.valueOf(eli));
      
            //int eli=jTable2.getRowCount();
            //txteliminar.setText(String.valueOf(eli));
      
            //int eli=jTable2.getRowCount();
            //txteliminar.setText(String.valueOf(eli));
}
 void mostrarcontrasena (String valor){
    
        try {
            com.mysql.jdbc.PreparedStatement pps=(com.mysql.jdbc.PreparedStatement) cn.prepareStatement("SELECT  decode(contrasena,'"+valor+"')  "
                    + "FROM usuarios WHERE idusuarios=?");
              pps.setInt(1, Integer.parseInt(idusudocente.getText()));
              rs = pps.executeQuery();
              if (rs.next() && valor.equals("344")) {
              String resul= rs.getString(1);
              JOptionPane.showMessageDialog(null, "La contraseña es : " + resul);
              }
              else {
                JOptionPane.showMessageDialog(null, "CODIGO DE SEGURIDAD INCORRECTO");
               
            } 
              
        } catch (Exception e) {
        }
    
    }
 
 void idtipousuario (String valor){
    try {
                java.sql.PreparedStatement pps=cn.prepareStatement("SELECT idtipousuario FROM tipousuario where descripcion='"+valor+"'");
                rs   = pps.executeQuery();
      while (rs.next()) {
      txtidtipousu.setText(rs.getString(1));
      }
                 } catch (Exception e) {
            }
    
    }

//  void idestado (String valor6){
//try {
//                PreparedStatement pps=cn.prepareStatement("SELECT idestado_usuarios FROM estado_usuarios where estado='"+valor6+"'");
//                rs   = pps.executeQuery();
//      while (rs.next()) {
//      txtidestado.setSt(rs.getString(1));
//      }
//                 } catch (Exception e) {
//            }
//}
 
 void comparar (String idusu, String idcl, String idcd, String idasignatura){
 String sql ="SELECT idasignatura FROM asig_docente where "
         + "idusuarios='"+idusu+"' and idciclolectivo='"+idcl+"' and idcurso_division='"+idcd+"' and idasignatura='"+idasignatura+"'";
 
     
 }
 
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupMenu1 = new javax.swing.JPopupMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jPopupMenu2 = new javax.swing.JPopupMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtnombre = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtapellido = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtdni = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        txttel = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        txtnick1 = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtpass1 = new javax.swing.JPasswordField();
        jLabel10 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        ltitulo = new javax.swing.JLabel();
        combocurso = new javax.swing.JComboBox();
        curso = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        division = new javax.swing.JTextField();
        idcv = new javax.swing.JTextField();
        btnagregar = new javax.swing.JButton();
        idasignatura = new javax.swing.JTextField();
        idusudocente = new javax.swing.JTextField();
        asig = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jLabel8 = new javax.swing.JLabel();
        año = new javax.swing.JLabel();
        txtidcl = new javax.swing.JTextField();
        btneliminar = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        txttipousuario = new javax.swing.JTextField();
        txtidtipousu = new javax.swing.JTextField();
        btna = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        cboestados = new javax.swing.JComboBox<>();
        txtidestado = new javax.swing.JTextField();
        jButton5 = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        jButton6 = new javax.swing.JButton();

        jMenuItem1.setText("EDITAR");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jPopupMenu1.add(jMenuItem1);

        jMenuItem2.setText("EDITAR ESTADO");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jPopupMenu2.add(jMenuItem2);

        setClosable(true);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Usuario Docente"));

        jLabel1.setText("Nombre:");

        jLabel2.setText("Apellido:");

        jLabel9.setText("Dni:");

        txtdni.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtdniKeyTyped(evt);
            }
        });

        jLabel4.setText("Sexo:");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Sellecione Sexo", "Masculino", "Femenino" }));

        jLabel3.setText("Telefono:");

        txttel.setComponentPopupMenu(jPopupMenu1);
        txttel.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txttelKeyTyped(evt);
            }
        });

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Datos Login"));

        jLabel6.setText("Usuario");

        jLabel7.setText("Contraseña:");

        jLabel10.setFont(new java.awt.Font("Tahoma", 3, 11)); // NOI18N
        jLabel10.setText("1ra letra Nombre+Apellido+ult 3 numeros Documento");

        jLabel5.setFont(new java.awt.Font("Tahoma", 3, 11)); // NOI18N
        jLabel5.setText("Numero Documento");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7))
                .addGap(27, 27, 27)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtnick1, javax.swing.GroupLayout.DEFAULT_SIZE, 201, Short.MAX_VALUE)
                    .addComponent(txtpass1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addContainerGap())
                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, 574, Short.MAX_VALUE)))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtnick1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtpass1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        jButton1.setText("Generar Login");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        ltitulo.setBackground(new java.awt.Color(255, 153, 153));
        ltitulo.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        ltitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ltitulo.setText("SELLECIONE LOS CURSOS SOBRE LAS QUE SE LE OTORGARA PERMISO DE ACCESO");
        ltitulo.setOpaque(true);

        combocurso.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Sellecionar Curso/División" }));
        combocurso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                combocursoActionPerformed(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Asignatura"
            }
        ));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setHeaderValue("Asignatura");
        }

        btnagregar.setText("Agregar");
        btnagregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnagregarActionPerformed(evt);
            }
        });

        jButton2.setText("Finalizar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Curso/Division", "Asignatura", "Ciclo Lectivo"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(jTable2);
        if (jTable2.getColumnModel().getColumnCount() > 0) {
            jTable2.getColumnModel().getColumn(0).setResizable(false);
            jTable2.getColumnModel().getColumn(1).setResizable(false);
            jTable2.getColumnModel().getColumn(2).setResizable(false);
            jTable2.getColumnModel().getColumn(3).setResizable(false);
        }

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel8.setText("Ciclo Lectivo:");

        año.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        btneliminar.setText("Eliminar");
        btneliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btneliminarActionPerformed(evt);
            }
        });

        jLabel11.setText("Tipo Usuario:");

        txttipousuario.setText("Docente");

        btna.setText("Actualizar");
        btna.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnaActionPerformed(evt);
            }
        });

        jLabel12.setText("Estado:");

        cboestados.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Activo" }));
        cboestados.setComponentPopupMenu(jPopupMenu2);
        cboestados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboestadosActionPerformed(evt);
            }
        });

        jButton5.setText("Imprimir Detalle");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel13.setText("Permisos a Accesos Otorgados");

        jButton6.setText("Atras");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(ltitulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(año, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(70, 70, 70)
                        .addComponent(txtidcl, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(combocurso, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(idcv, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(idusudocente, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(division, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(asig, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(btnagregar, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(btneliminar)
                                        .addGap(137, 137, 137))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(56, 56, 56)
                                        .addComponent(idasignatura, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(curso, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 303, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE))))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(txtnombre, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel2)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txtapellido)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel9)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtdni, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(btna, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(13, 13, 13))
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(jLabel3)))
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(txttel, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                                .addGap(77, 77, 77)
                                                .addComponent(jButton1)))
                                        .addGap(7, 7, 7)
                                        .addComponent(jLabel11)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txttipousuario, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtidtipousu, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(6, 6, 6)
                                        .addComponent(jLabel12)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(cboestados, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtidestado, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE))))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGap(338, 338, 338)
                                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton2)))
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton6)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtnombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(txtapellido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(txtdni, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel11)
                        .addComponent(txttipousuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtidtipousu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel12)
                        .addComponent(cboestados, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtidestado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel3)
                        .addComponent(txttel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel4)))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1)
                    .addComponent(btna))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(ltitulo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtidcl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8)
                            .addComponent(año, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addGap(7, 7, 7)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(combocurso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btneliminar)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(asig, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnagregar)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(idcv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(idusudocente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(division, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(idasignatura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(curso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton5)
                    .addComponent(jButton2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton6)
                .addContainerGap(8, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btneliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btneliminarActionPerformed
        int eli=jTable2.getSelectedRow();

        String valor =jTable2.getValueAt(eli, 0).toString();
        if(eli>=0){
            int bool = JOptionPane.showConfirmDialog(null,"CURSO/DIVISION:"+" "+jTable2.getValueAt(eli, 1).toString()+"\n"+"ASIGNATURA:"+" "+jTable2.getValueAt(eli, 2).toString()+"\n"+"C.Lectivo:"+" "+jTable2.getValueAt(eli, 3).toString()+"\n","\n"+"Eliminar Acceso a Docente",JOptionPane.YES_NO_OPTION);
            if(bool == JOptionPane.YES_OPTION){
                try {

                    com.mysql.jdbc.PreparedStatement pps =(com.mysql.jdbc.PreparedStatement) cn.prepareStatement("delete from asig_docente where idasig_docente='"+valor+"'");
                    pps.executeUpdate();

                    JOptionPane.showMessageDialog(null, "Dato Eliminado");

                    DefaultTableModel modelo2 = (DefaultTableModel)jTable2.getModel();
                    jTable2.setModel(modelo2);
                    modelo2.setRowCount(0);
                    tabla2(idusudocente.getText(),txtidcl.getText());

                } catch (Exception e) {
                }
            }
            else{
                JOptionPane.showMessageDialog(null, "Dato no Eliminado");
            }
        }
    }//GEN-LAST:event_btneliminarActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        udocente ud = new udocente();
        ventana.jdpescritorio.add(ud);
        ud.show();

        this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void btnagregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnagregarActionPerformed
        // TODO add your handling code here:
        //tabla(curso.getText());
        
        
        
        String sql = "INSERT INTO asig_docente (idcurso_division, idasignatura, idusuarios, idciclolectivo) VALUES(?,?,?,?)";
        try {
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setInt(1,Integer.parseInt(idcv.getText()));
            pst.setInt(2,Integer.parseInt(idasignatura.getText()));
            pst.setInt(3,Integer.parseInt(idusudocente.getText()));
            pst.setInt(4,Integer.parseInt(txtidcl.getText()));

            int n= pst.executeUpdate();
            if (n > 0) {
                JOptionPane.showMessageDialog(null, "Dato Agregado correctamente");
                asig.setText(null);
                idasignatura.setText(null);
                DefaultTableModel modelo1 = (DefaultTableModel)jTable2.getModel();
                jTable2.setModel(modelo1);
                modelo1.setRowCount(0);
                tabla2(idusudocente.getText(), txtidcl.getText());
            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_btnagregarActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:
        int column = jTable1.getColumnModel().getColumnIndexAtX(evt.getX());
        int row = evt.getY()/jTable1.getRowHeight();

        if(row<jTable1.getRowCount()&& row >=0 && column <jTable1.getColumnCount()&& column>=0){
            int eli=jTable1.getSelectedRow();
            idasignatura(curso.getText(), jTable1.getValueAt(eli, 0).toString());
            asig.setText(String.valueOf(jTable1.getValueAt(eli, 0)));
        }
    }//GEN-LAST:event_jTable1MouseClicked

    private void combocursoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combocursoActionPerformed
        // TODO add your handling code here:
        DefaultTableModel modelo = (DefaultTableModel)jTable1.getModel();
        jTable1.setModel(modelo);
        modelo.setRowCount(0);
        if (combocurso.getSelectedIndex()==0){
            curso.setText(null);
            division.setText(null);
            idasignatura.setText(null);
            idcv.setText(null);
            idusudocente.setText(null);

        } else {
            String item =combocurso.getSelectedItem().toString();
            String [] data=item.split(" ");

            curso.setText(data [0] + " " + data[1]);
            division.setText(data [3]);

        }

        tabla(curso.getText());

        idcuro_division(curso.getText(), division.getText());
    }//GEN-LAST:event_combocursoActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        String cadena =txtnombre.getText();//usuario
        String item1 =txtapellido.getText();//usuario
        String item2 =txtdni.getText();//contraseña
        char letra = cadena.charAt(0);
        String s;
        s = String.valueOf(letra);
        String [] dat=item1.split(" ");
        String [] dat1=item2.split(" ");
        String nombre = txtdni.getText();;
        char ultimo = nombre.charAt(nombre.length()-3);
        char ultimo1 = nombre.charAt(nombre.length()-2);
        char ultimo2 = nombre.charAt(nombre.length()-1);
        String a;
        a = String.valueOf(ultimo+""+ultimo1+""+ultimo2);
        txtnick1.setText(s +""+ dat[0]+""+a);
        txtpass1.setText(dat1 [0]);
        txtapellido.setEnabled(false);
        txtdni.setEnabled(false);
        txtnick1.setEnabled(false);
        txtnombre.setEnabled(false);
        txtpass1.setEnabled(false);
        txttel.setEnabled(false);
        jComboBox1.setEnabled(false);

        String sql = "INSERT INTO usuarios (nombre, apellido, dni, sexo, tel, usuario, contrasena, idtipousuario, idestado_usuarios) VALUES(?,?,?,?,?,?, MD5(?),?,?)";
        try {
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setString(1,txtnombre.getText());
            pst.setString(2,txtapellido.getText());
            pst.setString(3,(txtdni.getText()));
            pst.setString(4,jComboBox1.getSelectedItem().toString());
            pst.setString(5,(txttel.getText()));
            pst.setString(6,txtnick1.getText());
            pst.setString(7,txtpass1.getText());
            pst.setInt(8, Integer.parseInt(txtidtipousu.getText()));
             pst.setString(9,(txtidestado.getText()));
            int n= pst.executeUpdate();
            if (n > 0) {
                JOptionPane.showMessageDialog(null, "Sellecionar Acceso a Cursos");
                jButton1.setEnabled(false);
                PreparedStatement ps=cn.prepareStatement ("select idusuarios from usuarios");
                rs = ps.executeQuery();
                while (rs.next()) {
                    idusudocente.setText(rs.getString("idusuarios"));}
            }
        }catch (Exception e) {
        }

        ltitulo.setVisible(true);
        combocurso.setVisible(true);
        btnagregar.setVisible(true);
        asig.setVisible(true);

        btneliminar.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void txttelKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txttelKeyTyped
        // TODO add your handling code here:
        char validar=evt.getKeyChar();

        if(Character.isLetter(validar)){
            getToolkit().beep();
            evt.consume();
            JOptionPane.showMessageDialog(rootPane, "Ingresar solo Numeros");
        }
    }//GEN-LAST:event_txttelKeyTyped

    private void txtdniKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtdniKeyTyped
        // TODO add your handling code here:
        char validar=evt.getKeyChar();

        if(Character.isLetter(validar)){
            getToolkit().beep();
            evt.consume();
            JOptionPane.showMessageDialog(rootPane, "Ingresar solo Numeros");
        }
    }//GEN-LAST:event_txtdniKeyTyped

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        txttel.setEnabled(true);
        btna.setEnabled(true);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void cboestadosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboestadosActionPerformed
        // TODO add your handling code here:
        
        try {
            PreparedStatement pps=cn.prepareStatement ("SELECT idestado_usuarios FROM estado_usuarios where estado=?");
             pps.setString(1, cboestados.getSelectedItem().toString());
            // jComboBox1.setEnabled(false);
              
            ResultSet resultSet = pps.executeQuery();
            if (resultSet.next()) {
               txtidestado.setText(resultSet.getString("idestado_usuarios"));
               
               
            }
        }catch (Exception e) {
        }
        
//        idestado(cboestados.getSelectedItem().toString());
    }//GEN-LAST:event_cboestadosActionPerformed

    private void btnaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnaActionPerformed
        // TODO add your handling code here:
        try {
             PreparedStatement pps=cn.prepareStatement("UPDATE usuarios SET  tel=?, idestado_usuarios=? WHERE idusuarios=?");
             pps.setString(1, txttel.getText());
             pps.setString(2,txtidestado.getText());
             pps.setInt(3, Integer.parseInt(idusudocente.getText()));
             int res = pps.executeUpdate();
            if (res > 0) {
                JOptionPane.showMessageDialog(null, "Usuario Modificado Correctamente");
                
            } else {
                JOptionPane.showMessageDialog(null, "Error al Modificar Usuario");
                
            }
        } catch (Exception e) {
        }
        
    }//GEN-LAST:event_btnaActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        // TODO add your handling code here:
        cboestados.setEnabled(true);
        btna.setEnabled(true);
        cboestados.removeAllItems();
        
     try {
                java.sql.PreparedStatement pps=cn.prepareStatement("SELECT estado FROM estado_usuarios");
                rs   = pps.executeQuery();
      while (rs.next()) {
      this.cboestados.addItem(rs.getString("estado"));
      }
                 } catch (Exception e) {
            }
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
         try {
               reportes re =new reportes ();
               re.detalledocente(idusudocente.getText(),txtidcl.getText(),txtnombre.getText(), txtapellido.getText(),txtdni.getText(),txttel.getText());
           } catch (Exception e) {
               System.out.print(e.getMessage());
           }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        this.dispose();
        udocente ud = new udocente();
        jdpescritorio.add(ud);
        ud.show();
    }//GEN-LAST:event_jButton6ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JTextField asig;
    private javax.swing.JLabel año;
    private javax.swing.JButton btna;
    public static javax.swing.JButton btnagregar;
    public static javax.swing.JButton btneliminar;
    public static javax.swing.JComboBox<String> cboestados;
    private javax.swing.JComboBox combocurso;
    public static javax.swing.JTextField curso;
    public static javax.swing.JTextField division;
    public static javax.swing.JTextField idasignatura;
    public static javax.swing.JTextField idcv;
    public static javax.swing.JTextField idusudocente;
    public static javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    public static javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    public static javax.swing.JComboBox jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JPopupMenu jPopupMenu2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JLabel ltitulo;
    public static javax.swing.JTextField txtapellido;
    public static javax.swing.JTextField txtdni;
    public static javax.swing.JTextField txtidcl;
    private javax.swing.JTextField txtidestado;
    private javax.swing.JTextField txtidtipousu;
    public static javax.swing.JTextField txtnick1;
    public static javax.swing.JTextField txtnombre;
    public static javax.swing.JPasswordField txtpass1;
    public static javax.swing.JTextField txttel;
    private javax.swing.JTextField txttipousuario;
    // End of variables declaration//GEN-END:variables
}
