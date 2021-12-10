
package paquetes;

import conexion.conectar;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.print.DocFlavor;
import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableColumn;
import javax.swing.table.TableRowSorter;
import tesis.render;
import java.util.Date;


public class vercalificacionesne extends javax.swing.JInternalFrame {
conectar cc=new conectar();
Connection cn=cc.conexion();
ResultSet rs;
//DefaultTableModel modelo;
DefaultComboBoxModel mdlNotas;  
    
    
   
    public vercalificacionesne() {
         initComponents();
         
         cursodocente();
         this.setTitle("Registro de Notas");
         fechaactual();
         //trimestre(txtidcl.getText());
         idasignatura.setVisible(false);
         idcursodivi.setVisible(false);
         idtri.setVisible(false);
         txt1.setVisible(false);
         txt2.setVisible(false);
         curso.setVisible(false);
         jTextField1.setVisible(false);
         trimestre.setVisible(false);
         jTable1.setEnabled(false);
         txtidcl.setVisible(false);
         trimestre(txtidcl.getText());
         idcurso.setVisible(false);       
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
  
    void cursodocente (){
    try {
                PreparedStatement pps=cn.prepareStatement("SELECT  c.descripcion, cd.division FROM curso_division cd, curso c where cd.idcurso=c.idcurso order by c.descripcion, cd.division");
                rs   = pps.executeQuery();
                Object datos []= new Object[1];
      while (rs.next()) {
      this.combocurso.addItem(rs.getString("c.descripcion")+ " Division "  +rs.getString("cd.division"));
  
      }
                 } catch (Exception e) {
            }
    
    }
    
    void idcuro_division(String valor, String valor1){
try {
                PreparedStatement pps=cn.prepareStatement("Select cd.idcurso_division, c.idcurso "
                        + "FROM curso_division cd, curso c where cd.idcurso=c.idcurso "
                        + "and  c.Descripcion='"+valor+"'and division='"+valor1+"'");
                rs   = pps.executeQuery();
      while (rs.next()) {
      idcursodivi.setText(rs.getString("cd.idcurso_division"));
      idcurso.setText(rs.getString("c.idcurso"));
      }
                 } catch (Exception e) {
            }
    }
   
    void asigdocente (String valor){
        if (combocurso.getSelectedIndex()>-1){
    try {
                PreparedStatement pps=cn.prepareStatement("SELECT a.nombre FROM asignacion_materias am, asignatura a, curso c "
                        + "where a.idasignatura=am.idasignatura and c.idcurso=am.idcurso and c.descripcion='"+valor+"' order by a.idasignatura");
                rs   = pps.executeQuery();
      while (rs.next()) {
      this.comboasignatura.addItem(rs.getString("a.nombre"));
      }
                 } catch (Exception e) {
            }
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
      idasignatura.setText(rs.getString("asignatura.idasignatura"));
      }
                 } catch (Exception e) {
            }
}
    
    void tablanotas (String valor,String valor1, String valor2, String valor3 ){
    final DefaultTableModel modelo1 = (DefaultTableModel)jTable1.getModel();
    
         jTable1.setModel(modelo1);
        {
          
   };
    Object datos []= new Object[9];
      String sql= "SELECT n.idnotas, a.idAlumno, a.DNI, a.sexo, a.apellido, a.nombre, n.nota1, n.nota2, n.nota3 "
              + "from alumno a left join notas n on a.idAlumno=n.idAlumno and n.idTrimestre="+valor2+" "
              + "and n.idAsignatura="+valor+" and n.idCurso_division="+valor1+""
              + ", detalle_matricula dm, curso_division cd, asignacion_materias am, estado_alumno ea, estado_matricula em "
              + "where dm.idcurso_division=cd.idcurso_division and dm.idAlumno=a.idAlumno and a.idestado_alumno=ea.idestado_alumno "
              + "and em.idestado_matricula=dm.idestado_matricula and ea.estado='Activo' and em.estado ='Inscripto'"
              + "and cd.idCurso=am.IdCurso and am.idasignatura="+valor+" and dm.idcurso_division="+valor1+" and dm.idciclolectivo="+valor3+"";
      try {
            Statement st = cn.createStatement();
             rs = st.executeQuery(sql);
            while(rs.next())
            {  
                datos[0]=rs.getString("n.idnotas");
                datos[1]=rs.getString("a.DNI");
                datos[2]=rs.getString("a.Sexo");
                datos[3]=rs.getString("a.Apellido");
                datos[4]=rs.getString("a.Nombre");
                datos[5]=rs.getString("n.nota1");
                datos[6]=rs.getString("n.nota2");
                datos[7]=rs.getString("n.nota3");
                
                modelo1.addRow(datos);    
            }
           
           
            
            jTable1.setModel(modelo1);
            jTable1.setRowHeight(20);
            
            jTable1.setCellSelectionEnabled(true);
            jTable1.setSurrendersFocusOnKeystroke(true);
            
            jTable1.getColumnModel().getColumn(0).setMaxWidth(0);
            jTable1.getColumnModel().getColumn(0).setMinWidth(0);
            jTable1.getColumnModel().getColumn(0).setPreferredWidth(0);
        } 
   
   catch (SQLException ex) {
            Logger.getLogger(vercalificacionesne.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
    
    void trimestre(String valor2){
try {
                PreparedStatement pps=cn.prepareStatement("SELECT t.descripcion FROM trimestre t, ciclolectivo cl  where t.idciclolectivo=cl.idciclolectivo and t.idciclolectivo="+valor2+"");
                rs   = pps.executeQuery();
      while (rs.next()) {
      combotrimestre.addItem(rs.getString("t.descripcion"));
      }
                 } catch (Exception e) {
            }
    }
    
    void idtri(String valor, String valor2){
try {
                PreparedStatement pps=cn.prepareStatement("SELECT idtrimestre FROM trimestre where descripcion='"+valor+"'and trimestre.idciclolectivo='"+valor2+"'");
                rs   = pps.executeQuery();
      while (rs.next()) {
      idtri.setText(rs.getString(1));
      }
                 } catch (Exception e) {
            }
    }
    
 
 
 void tablaprom(String valor,String valor1, String valor2, String cl){
    DefaultTableModel modelo2 = (DefaultTableModel)jTable2.getModel();
    
         jTable2.setModel(modelo2);
        {
          
   };
    Object datos []= new Object[5];
      String sql="SELECT a.dni, a.sexo, a.apellido, a.nombre, round((n.nota1 + n.nota2 + n.nota3)/3,2.0) prom "
       + "from notas n, alumno a, trimestre t, ciclolectivo cl, estado_alumno ea where a.idalumno=n.idalumno and a.idestado_alumno=ea.idestado_alumno "
       + "and n.idasignatura="+valor+" "
       + "and n.idcurso_division="+valor1+" and n.idtrimestre="+valor2+" and t.idTrimestre=n.idTrimestre  and t.idciclolectivo=cl.idciclolectivo and cl.año="+cl+" and ea.estado='Activo'";
      try {
            Statement st = cn.createStatement();
             rs = st.executeQuery(sql);
            while(rs.next())
            {   
                datos[0]=rs.getString("a.dni");
                datos[1]=rs.getString("a.sexo");
                datos[2]=rs.getString("a.apellido");
                datos[3]=rs.getString("a.nombre");
                double prom = Double.parseDouble(rs.getString("prom"));
                datos[4]=prom;
                modelo2.addRow(datos);       
            }
            jTable2.setModel(modelo2);
            jTable2.setRowHeight(20);
            
        } 
   
   catch (SQLException ex) {
            Logger.getLogger(vercalificacionesne.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
 
 void promfinal (String valor,String valor1, String cl){
     
     int curso=Integer.parseInt(idcurso.getText());
     
    
     
 DefaultTableModel modelo2 = (DefaultTableModel)jTable3.getModel();
    
         jTable3.setModel(modelo2);
        {
          
   };
    Object datos []= new Object[6];
       String sql="SELECT a.dni, a.sexo, a.apellido, a.nombre, ROUND((avg(Nota1 + Nota2  + Nota3)/3),2) prom "
       + "from notas n, alumno a, trimestre t, ciclolectivo cl, estado_alumno ea where a.idestado_alumno=ea.idestado_alumno and a.idalumno=n.idalumno "
               + "and n.idasignatura="+valor+" and n.idcurso_division="+valor1+" and t.idTrimestre=n.idTrimestre and t.idciclolectivo=cl.idciclolectivo and cl.año="+cl+" and ea.estado='Activo' group by a.dni,a.sexo,a.apellido,a.nombre  "
               + "order by a.apellido ";
      try {
            Statement st = cn.createStatement();
             rs = st.executeQuery(sql);
            while(rs.next())
            {   
                datos[0]=rs.getString("a.dni");
                datos[1]=rs.getString("a.sexo");
                datos[2]=rs.getString("a.apellido");
                datos[3]=rs.getString("a.nombre");
                double prom = Double.parseDouble(rs.getString("prom"));
                
                if(curso <= 3 ){ 
                     datos[4]=Math.round(prom);
                 if(prom<6 ){
                    datos[5]="Desaprobado";}
                        else {
                  datos[5]="Aprobado"; }    
                }
                else {
                   datos[4]=prom;
                if(prom<6 ){
                    datos[5]="Desaprobado";}
                        else {
                  datos[5]="Aprobado"; }
                }
     
                modelo2.addRow(datos);       
            }
            jTable3.setModel(modelo2);
            jTable3.setRowHeight(20);
            
        } 
   
   catch (SQLException ex) {
            Logger.getLogger(vercalificacionesne.class.getName()).log(Level.SEVERE, null, ex);
        }
 }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupMenu1 = new javax.swing.JPopupMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        combocurso = new javax.swing.JComboBox();
        comboasignatura = new javax.swing.JComboBox();
        combotrimestre = new javax.swing.JComboBox();
        idcursodivi = new javax.swing.JTextField();
        idasignatura = new javax.swing.JTextField();
        jTextField1 = new javax.swing.JTextField();
        txt1 = new javax.swing.JTextField();
        txt2 = new javax.swing.JTextField();
        btnbuscar = new javax.swing.JButton();
        trimestre = new javax.swing.JTextField();
        idtri = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        curso = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        label = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jLabel9 = new javax.swing.JLabel();
        label2 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        año = new javax.swing.JLabel();
        txtidcl = new javax.swing.JTextField();
        idcurso = new javax.swing.JTextField();

        jMenuItem1.setText("EDITAR");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jPopupMenu1.add(jMenuItem1);

        jLabel1.setBackground(new java.awt.Color(51, 51, 255));
        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("VER CALIFICACIONES DE ESTUDIANTES");
        jLabel1.setOpaque(true);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "SELLECIÓN DE CURSO, ASIGNATURA Y TRIMESTRE"));

        jLabel2.setText("Curso:");

        jLabel3.setText("Asignatura:");

        jLabel4.setText("Trimestre:");

        combocurso.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "--Sellecione un Curso--" }));
        combocurso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                combocursoActionPerformed(evt);
            }
        });

        comboasignatura.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "--Sellecione una asignatura--" }));

        combotrimestre.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "--Seleccione un Trimestre--" }));
        combotrimestre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                combotrimestreActionPerformed(evt);
            }
        });

        btnbuscar.setText("Buscar");
        btnbuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnbuscarActionPerformed(evt);
            }
        });

        jButton2.setText("Editar Filtros");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(jLabel2)
                    .addComponent(btnbuscar))
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(combotrimestre, 0, 186, Short.MAX_VALUE)
                    .addComponent(combocurso, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton2, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(10, 10, 10)
                        .addComponent(comboasignatura, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(80, 80, 80)
                        .addComponent(idasignatura, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(trimestre, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(idtri, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(txt1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt2, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(curso, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(idcursodivi, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(combocurso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(comboasignatura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(combotrimestre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(btnbuscar))
                .addContainerGap(21, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(curso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(idasignatura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(idtri, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(trimestre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(idcursodivi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setText("RESULTADOS DE BUSQUEDA");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "NRO. DOCUMENTO", "SEXO", "APELLIDO", "NOMBRE", "NOTA 1", "NOTA 2", "NOTA 3"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, true, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setComponentPopupMenu(jPopupMenu1);
        jTable1.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setPreferredWidth(30);
            jTable1.getColumnModel().getColumn(1).setPreferredWidth(100);
            jTable1.getColumnModel().getColumn(5).setPreferredWidth(30);
            jTable1.getColumnModel().getColumn(6).setPreferredWidth(50);
            jTable1.getColumnModel().getColumn(7).setPreferredWidth(50);
        }

        jLabel6.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel6.setText("NOTAS PARCIALES:");

        label.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 890, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(label, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(label, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Notas Parciales ", jPanel2);

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "NRO. DOCUMENTO", "SEXO", "APELLIDO", "NOMBRE", "NOTA FINAL"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable2.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(jTable2);
        if (jTable2.getColumnModel().getColumnCount() > 0) {
            jTable2.getColumnModel().getColumn(0).setPreferredWidth(30);
        }

        jLabel9.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel9.setText("NOTAS FINALES:");

        label2.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(label2, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 890, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(label2, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(13, 13, 13)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 199, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Notas Finales  ", jPanel3);

        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "NRO. DOCUMENTO", "SEXO", "APELLIDO", "NOMBRE", "PROMEDIO FINAL", "CONDICION"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable3.getTableHeader().setReorderingAllowed(false);
        jTable3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable3MouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(jTable3);
        if (jTable3.getColumnModel().getColumnCount() > 0) {
            jTable3.getColumnModel().getColumn(0).setPreferredWidth(100);
        }

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 890, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 194, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Calificación Definitiva", jPanel4);

        jButton1.setText("Inicio");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel10.setText("Ciclo Lectivo:");

        año.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTabbedPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton1)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 299, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jLabel10)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(año, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(74, 74, 74)
                                .addComponent(txtidcl, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(idcurso, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtidcl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(idcurso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(año, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addContainerGap(23, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void combocursoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combocursoActionPerformed
           
         if (combocurso.getSelectedIndex()==0){
            
                } else {
                String item =combocurso.getSelectedItem().toString();
                String [] data=item.split(" ");
                txt1.setText(data [0] + " " + data[1]);
                txt2.setText(data [3]);     
        }
         idcuro_division(txt1.getText(), txt2.getText());
            if(combocurso.getSelectedIndex()>-1){
            comboasignatura.removeAllItems();
            asigdocente(txt1.getText());
            }
         curso.setText(combocurso.getSelectedItem().toString());
        //asigdocente(idcursodivi.getText());
    }//GEN-LAST:event_combocursoActionPerformed

    private void btnbuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbuscarActionPerformed
        //tablanotas(idasignatura.getText(), idcursodivi.getText(),trimestre.getText());
        jTextField1.setText(comboasignatura.getSelectedItem().toString());
        idasignatura(txt1.getText(), jTextField1.getText());
        label.setText(trimestre.getText());
        label2.setText(trimestre.getText());
        DefaultTableModel modelo1 = (DefaultTableModel)jTable1.getModel();
        jTable1.setModel(modelo1);
        modelo1.setRowCount(0);
        comboasignatura.setEnabled(false);
        combocurso.setEnabled(false);
        combotrimestre.setEnabled(false);
        btnbuscar.setEnabled(false);
        tablanotas(idasignatura.getText(), idcursodivi.getText(), idtri.getText(), txtidcl.getText());
        
        DefaultTableModel modelo2 = (DefaultTableModel)jTable2.getModel();
        jTable2.setModel(modelo2);
        modelo2.setRowCount(0);
        tablaprom(idasignatura.getText(), idcursodivi.getText(), idtri.getText(), año.getText());
        DefaultTableModel modelo3 = (DefaultTableModel)jTable3.getModel();
        jTable3.setModel(modelo3);
        modelo3.setRowCount(0);
        promfinal(idasignatura.getText(), idcursodivi.getText(), año.getText());
    }//GEN-LAST:event_btnbuscarActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        comboasignatura.setEnabled(true);
        combocurso.setEnabled(true);
        combotrimestre.setEnabled(true);
        btnbuscar.setEnabled(true);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void combotrimestreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combotrimestreActionPerformed
        // TODO add your handling code here:
         if (combotrimestre.getSelectedIndex()==0){
            
                } else {
                String item =combotrimestre.getSelectedItem().toString();
                String [] data=item.split(" ");
                trimestre.setText(data [0] +" "+ data[1]);
                   
        }
         
         idtri(trimestre.getText(), txtidcl.getText());
    }//GEN-LAST:event_combotrimestreActionPerformed

    private void jTable3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable3MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jTable3MouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
       this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
   jTable1.setEnabled(true);
   
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    /**
     * @param args the command line arguments
     */
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel año;
    private javax.swing.JButton btnbuscar;
    private javax.swing.JComboBox comboasignatura;
    private javax.swing.JComboBox combocurso;
    private javax.swing.JComboBox combotrimestre;
    private javax.swing.JTextField curso;
    private javax.swing.JTextField idasignatura;
    private javax.swing.JTextField idcurso;
    private javax.swing.JTextField idcursodivi;
    private javax.swing.JTextField idtri;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable3;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JLabel label;
    private javax.swing.JLabel label2;
    private javax.swing.JTextField trimestre;
    private javax.swing.JTextField txt1;
    private javax.swing.JTextField txt2;
    public static javax.swing.JTextField txtidcl;
    // End of variables declaration//GEN-END:variables
}
