
package paquetes;

import beans.Asignatura;
import beans.Curso;
import codigo.GenerarCodigos;
import com.toedter.calendar.JCalendar;
import conexion.conectar;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableColumn;
import tesis.render;


public class librodetema extends javax.swing.JInternalFrame {

    conectar cc = new conectar();
    Connection cn = cc.conexion();
    ResultSet rs;
 
DefaultTableModel modelo;
    public librodetema() {
        initComponents();
        txtidcl.setVisible(false);
        cl();
        cursodocente(ventana.id.getText(),librodetema.txtidcl.getText());
        btoregistrar.setVisible(false);
        btoregistrar4.setVisible(false);
        btoregistrar5.setVisible(false);
        btoregistrar6.setVisible(false);
        txt7.setEnabled(false);
        txt16.setEnabled(false);
        txt19.setEnabled(false);
        txt22.setEnabled(false);
        txt1.setVisible(false);
        txt2.setVisible(false);
        curso.setVisible(false);
        jTextField1.setVisible(false);
        txtidcl1.setVisible(false);
        idcursodivi.setVisible(false);
        idasignatura.setVisible(false);
        iddocente.setVisible(false);
        txtph.setVisible(false);
        txtsh.setVisible(false);
        txtth.setVisible(false);
        txtch.setVisible(false);
        txtid1.setVisible(false);
        txtid2.setVisible(false);
        txtid3.setVisible(false);
        txtid4.setVisible(false);
        
        btnnuevo.setVisible(false);
        solapas.setVisible(false);
        jLabel1.setVisible(false);
        fecha.setVisible(false);
        jLabel4.setVisible(false);
        txtclasen.setVisible(false);
        btnregistro.setVisible(false);
        
        
    }
    
    
   void max(){
  if (jTable1.getRowCount() != 0) {
    ArrayList <Integer> list =new ArrayList <Integer> ();
        for (int i = 0 ; i <jTable1.getRowCount(); i++){
    list.add(Integer.parseInt(jTable1.getValueAt(i, 2).toString()));
    }
    int max=Collections.max(list);    
        int suma = max +1;
        txtclasen.setText(Integer.toString(suma));
    
} else {
      txtclasen.setText("1");
}
  }

   void cl (){
        try {
            PreparedStatement pps=cn.prepareStatement("SELECT idciclolectivo, año  FROM ciclolectivo where año=year(now())");
            rs = pps.executeQuery();
            while (rs.next()) {
                txtidcl.setText(rs.getString(1));
                año.setText(rs.getString(2));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

   void cursodocente(String valor, String valor2) {
        try {
            PreparedStatement pps=cn.prepareStatement("SELECT curso.idcurso, curso.Descripcion, curso.idnivel, "
                    + "curso_division.division, curso_division.idcurso_division "
                + "FROM curso, curso_division join  asig_docente using (idcurso_division)"
                + "where curso.idcurso=curso_division.idcurso and curso_division.idcurso_division=asig_docente.idcurso_division "
                + "and idasig_docente  and idusuarios='"+valor+"' and asig_docente.idciclolectivo='"+valor2+"' group by asig_docente.idcurso_division");
            rs   = pps.executeQuery();
            while (rs.next()) { 
                //this.combocurso.addItem(rs.getString("Descripcion")+ " Division "  +rs.getString("division"));
                this.combocurso.addItem(new Curso(rs.getInt("idcurso"), rs.getString("Descripcion"), rs.getInt("idnivel"), rs.getString("division"), rs.getInt("idcurso_division")));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
   void idcuro_division(String valor, String valor1) {
        try {
            PreparedStatement pps = cn.prepareStatement("SELECT idcurso_division "
                + "FROM curso_division, curso where curso_division.idcurso=curso.idcurso "
                + "and  curso.Descripcion='"+valor+"'and division='"+valor1+"'");
            rs   = pps.executeQuery();
            while (rs.next()) {
                idcursodivi.setText(rs.getString(1));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
   
   void asigdocente (String valor, String valor2, String valor3 ){
        if (combocurso.getSelectedIndex()>-1){
            try {
                PreparedStatement pps = cn.prepareStatement("SELECT a.idasignatura, a.nombre, a.idplanestudio FROM asig_docente, asignatura a "
                    + "where a.idasignatura = asig_docente.idasignatura and idcurso_division = '" + valor + "'"
                    + "and asig_docente.idciclolectivo = '" + valor2 + "' and asig_docente.idusuarios = '" + valor3 + "' order by a.idasignatura");
                rs = pps.executeQuery();
                while (rs.next()) {
                    //this.comboasignatura.addItem(rs.getString("asignatura.nombre"));
                    this.comboasignatura.addItem(new Asignatura(rs.getInt("idasignatura"), rs.getString("nombre"), rs.getInt("idplanestudio")));
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
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
   
   void librodetema (String valor, String valor1, String valor2){
       
    jTable1.setDefaultRenderer(Object.class, new render());
     
      JButton btn = new JButton("Ver");
      
 
   String [] titulos= {"Reg. N°","Fecha", "Clase N°","VER"};
   modelo=new  DefaultTableModel(null,titulos)
   {
            @Override
    public boolean isCellEditable(int row, int column){
              return false; 
               }
   };
           jTable1.setModel(modelo);
   
   Object datos []= new Object[4];
    String sql="SELECT idregistrotema, fecha, clasen, caracterdclase, temadeldia FROM registrotema WHERE idcurso_division='"+valor+"' "
            + "and idasignatura='"+valor1+"' and idciclolectivo='"+valor2+"' group by clasen";
   try {
            Statement st = cn.createStatement();
             rs = st.executeQuery(sql);
           while(rs.next())
            {
                datos[0]=rs.getString("idregistrotema");
                datos[1]=rs.getDate("fecha");
                datos[2]=rs.getString("clasen");
                datos[3]=btn;
                modelo.addRow(datos);
                
            }
                      
            jTable1.setModel(modelo);
            jTable1.setRowHeight(15);
             
             jTable1.getColumnModel().getColumn(0).setMaxWidth(0);
             jTable1.getColumnModel().getColumn(0).setMinWidth(0);
             jTable1.getColumnModel().getColumn(0).setPreferredWidth(0);
             jTable1.getColumnModel().getColumn(1).setPreferredWidth(50);
             jTable1.getColumnModel().getColumn(2).setPreferredWidth(20);
        } 
   
   catch (SQLException ex) {
            System.out.println(ex);
        }

   
   }
   
   
   void detalleprimerahora(String valor, String valor1, String valor2, String valor3){
   try {                   
                    PreparedStatement pps=cn.prepareStatement("SELECT idregistrotema, caracterdclase, temadeldia, actividadadesarrollar, observacion "
                + "FROM registrotema WHERE idcurso_division='"+valor+"' "
                + "and idasignatura='"+valor1+"' and idciclolectivo='"+valor2+"' and clasen='"+valor3+"' and hora='Primera Hora'"); 
            rs = pps.executeQuery();
            if (rs.next()) {

                txtid1.setText(rs.getString("idregistrotema"));
                jComboBox1.setSelectedItem(rs.getString("caracterdclase"));
                txt5.setText(rs.getString("temadeldia"));
                txt6.setText(rs.getString("actividadadesarrollar"));
                txt7.setText(rs.getString("observacion"));
                
//                txtid1.setEnabled(false);
//                jComboBox1.setEnabled(false);
//                txt5.setEnabled(false);
//                txt6.setEnabled(false);
//                txt7.setEnabled(false);
                
            }
            } catch (Exception e) {
                System.out.println(e);
            }
   }
   
   void detallesegundahora(String valor, String valor1, String valor2, String valor3){
   try {                   
                    PreparedStatement pps=cn.prepareStatement("SELECT idregistrotema, caracterdclase, temadeldia, actividadadesarrollar, observacion "
                + "FROM registrotema WHERE idcurso_division='"+valor+"' "
                + "and idasignatura='"+valor1+"' and idciclolectivo='"+valor2+"' and clasen='"+valor3+"' and hora='Segunda Hora'"); 
            rs = pps.executeQuery();
            if (rs.next()) {

                txtid2.setText(rs.getString("idregistrotema"));
                jComboBox5.setSelectedItem(rs.getString("caracterdclase"));
                txt17.setText(rs.getString("temadeldia"));
                txt15.setText(rs.getString("actividadadesarrollar"));
                txt16.setText(rs.getString("observacion"));
                
//                txtid2.setEnabled(false);
//                jComboBox5.setEnabled(false);
//                txt17.setEnabled(false);
//                txt15.setEnabled(false);
//                txt16.setEnabled(false);
            
            }
            } catch (Exception e) {
                System.out.println(e);
            }
   }
   
   void detalletercerahora(String valor, String valor1, String valor2, String valor3){
   try {                   
                    PreparedStatement pps=cn.prepareStatement("SELECT idregistrotema, caracterdclase, temadeldia, actividadadesarrollar, observacion "
                + "FROM registrotema WHERE idcurso_division='"+valor+"' "
                + "and idasignatura='"+valor1+"' and idciclolectivo='"+valor2+"' and clasen='"+valor3+"' and hora='Tercera Hora'"); 
            rs = pps.executeQuery();
            if (rs.next()) {

                txtid3.setText(rs.getString("idregistrotema"));
                jComboBox6.setSelectedItem(rs.getString("caracterdclase"));
                txt20.setText(rs.getString("temadeldia"));
                txt18.setText(rs.getString("actividadadesarrollar"));
                txt19.setText(rs.getString("observacion"));
                
//                 txtid3.setEnabled(false);
//                jComboBox6.setEnabled(false);
//                txt20.setEnabled(false);
//                txt18.setEnabled(false);
//                txt19.setEnabled(false);
                
            }
            } catch (Exception e) {
                System.out.println(e);
            }
   }
   
   void detallecuartahora(String valor, String valor1, String valor2, String valor3){
   try {                   
                    PreparedStatement pps=cn.prepareStatement("SELECT idregistrotema, caracterdclase, temadeldia, actividadadesarrollar, observacion "
                + "FROM registrotema WHERE idcurso_division='"+valor+"' "
                + "and idasignatura='"+valor1+"' and idciclolectivo='"+valor2+"' and clasen='"+valor3+"' and hora='Cuarta Hora'"); 
            rs = pps.executeQuery();
            if (rs.next()) {

                txtid4.setText(rs.getString("idregistrotema"));
                jComboBox7.setSelectedItem(rs.getString("caracterdclase"));
                txt23.setText(rs.getString("temadeldia"));
                txt21.setText(rs.getString("actividadadesarrollar"));
                txt22.setText(rs.getString("observacion"));
                
//                 txtid4.setEnabled(false);
//                jComboBox7.setEnabled(false);
//                txt23.setEnabled(false);
//                txt21.setEnabled(false);
//                txt22.setEnabled(false);
                
            }
            } catch (Exception e) {
                System.out.println(e);
            }
   }
   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel10 = new javax.swing.JLabel();
        año = new javax.swing.JLabel();
        txtidcl = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        combocurso = new javax.swing.JComboBox();
        comboasignatura = new javax.swing.JComboBox();
        btnbuscar = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        txt1 = new javax.swing.JTextField();
        txt2 = new javax.swing.JTextField();
        curso = new javax.swing.JTextField();
        jTextField1 = new javax.swing.JTextField();
        txtidcl1 = new javax.swing.JTextField();
        idcursodivi = new javax.swing.JTextField();
        idasignatura = new javax.swing.JTextField();
        iddocente = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton3 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        fecha = new com.toedter.calendar.JDateChooser();
        jLabel4 = new javax.swing.JLabel();
        txtclasen = new javax.swing.JTextField();
        solapas = new javax.swing.JTabbedPane();
        ph = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txt6 = new javax.swing.JTextArea();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txt7 = new javax.swing.JTextField();
        btoregistrar = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        txt5 = new javax.swing.JTextField();
        txtph = new javax.swing.JTextField();
        txtid1 = new javax.swing.JTextField();
        sh = new javax.swing.JPanel();
        jLabel24 = new javax.swing.JLabel();
        jComboBox5 = new javax.swing.JComboBox<>();
        jLabel25 = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        txt15 = new javax.swing.JTextArea();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        txt16 = new javax.swing.JTextField();
        btoregistrar4 = new javax.swing.JButton();
        jLabel28 = new javax.swing.JLabel();
        txt17 = new javax.swing.JTextField();
        txtsh = new javax.swing.JTextField();
        txtid2 = new javax.swing.JTextField();
        tc = new javax.swing.JPanel();
        jLabel29 = new javax.swing.JLabel();
        jComboBox6 = new javax.swing.JComboBox<>();
        jLabel30 = new javax.swing.JLabel();
        jScrollPane7 = new javax.swing.JScrollPane();
        txt18 = new javax.swing.JTextArea();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        txt19 = new javax.swing.JTextField();
        btoregistrar5 = new javax.swing.JButton();
        jLabel33 = new javax.swing.JLabel();
        txt20 = new javax.swing.JTextField();
        txtth = new javax.swing.JTextField();
        txtid3 = new javax.swing.JTextField();
        ch = new javax.swing.JPanel();
        jLabel34 = new javax.swing.JLabel();
        jComboBox7 = new javax.swing.JComboBox<>();
        jLabel35 = new javax.swing.JLabel();
        jScrollPane8 = new javax.swing.JScrollPane();
        txt21 = new javax.swing.JTextArea();
        jLabel36 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        txt22 = new javax.swing.JTextField();
        btoregistrar6 = new javax.swing.JButton();
        jLabel38 = new javax.swing.JLabel();
        txt23 = new javax.swing.JTextField();
        txtch = new javax.swing.JTextField();
        txtid4 = new javax.swing.JTextField();
        btnnuevo = new javax.swing.JButton();
        btnregistro = new javax.swing.JButton();

        setClosable(true);
        setTitle("REGISTRO DE TEMAS DE CLASES");

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel10.setText("Ciclo Lectivo:");

        año.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "SELLECIÓN DE CURSO Y ASIGNATURA "));

        jLabel2.setText("Curso:");

        jLabel3.setText("Asignatura:");

        combocurso.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "--Sellecione un Curso--" }));
        combocurso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                combocursoActionPerformed(evt);
            }
        });

        comboasignatura.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "--Sellecione una asignatura--" }));
        comboasignatura.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboasignaturaActionPerformed(evt);
            }
        });

        btnbuscar.setText("Ver Registros");
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
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txt1, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt2, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(curso, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtidcl1, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(idcursodivi, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(idasignatura, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(iddocente, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(combocurso, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel3)
                        .addGap(18, 18, 18)
                        .addComponent(comboasignatura, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnbuscar)
                        .addGap(18, 18, 18)
                        .addComponent(jButton2)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(combocurso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(comboasignatura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2)
                    .addComponent(btnbuscar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(curso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtidcl1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(idcursodivi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(idasignatura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(iddocente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
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

        jButton3.setText("Atras");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setText("Fecha:");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setText("Clase N°:");

        txtclasen.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtclasenKeyTyped(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel5.setText("Carácter de la Clase:");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Teórico", "Práctico", "Evaluación", "Laboratorio", "Videoteca", " " }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel7.setText("Actividad a desarrollar:");

        txt6.setColumns(20);
        txt6.setRows(5);
        jScrollPane2.setViewportView(txt6);

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel8.setText("Observaciones:");

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        jLabel9.setText("(Uso supervisor de clase)");

        btoregistrar.setText("Registrar");
        btoregistrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btoregistrarActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel6.setText("Tema de la Clase:");

        txtph.setText("Primera Hora");

        javax.swing.GroupLayout phLayout = new javax.swing.GroupLayout(ph);
        ph.setLayout(phLayout);
        phLayout.setHorizontalGroup(
            phLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(phLayout.createSequentialGroup()
                .addGroup(phLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(phLayout.createSequentialGroup()
                        .addGroup(phLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(phLayout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 234, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, phLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(txtph, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(248, 248, 248)
                        .addComponent(btoregistrar))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, phLayout.createSequentialGroup()
                        .addGroup(phLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(phLayout.createSequentialGroup()
                                .addGap(5, 5, 5)
                                .addGroup(phLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel8)))
                            .addGroup(phLayout.createSequentialGroup()
                                .addGap(34, 34, 34)
                                .addComponent(txtid1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(phLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane2)
                            .addGroup(phLayout.createSequentialGroup()
                                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt5, javax.swing.GroupLayout.DEFAULT_SIZE, 291, Short.MAX_VALUE))
                            .addComponent(txt7))))
                .addContainerGap(70, Short.MAX_VALUE))
        );
        phLayout.setVerticalGroup(
            phLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(phLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(phLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(txt5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(phLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(phLayout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(18, 18, 18)
                        .addComponent(txtid1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(phLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(phLayout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(jLabel9))
                    .addGroup(phLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(phLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(phLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtph, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btoregistrar))))
                .addContainerGap(7, Short.MAX_VALUE))
        );

        solapas.addTab("PRIMERA HORA", ph);

        jLabel24.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel24.setText("Carácter de la Clase:");

        jComboBox5.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Teórico", "Práctico", "Evaluación", "Laboratorio", "Videoteca", " " }));
        jComboBox5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox5ActionPerformed(evt);
            }
        });

        jLabel25.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel25.setText("Actividad a desarrollar:");

        txt15.setColumns(20);
        txt15.setRows(5);
        jScrollPane6.setViewportView(txt15);

        jLabel26.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel26.setText("Observaciones:");

        jLabel27.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        jLabel27.setText("(Uso supervisor de clase)");

        btoregistrar4.setText("Registrar");
        btoregistrar4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btoregistrar4ActionPerformed(evt);
            }
        });

        jLabel28.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel28.setText("Tema de la Clase:");

        txtsh.setText("Segunda Hora");

        javax.swing.GroupLayout shLayout = new javax.swing.GroupLayout(sh);
        sh.setLayout(shLayout);
        shLayout.setHorizontalGroup(
            shLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(shLayout.createSequentialGroup()
                .addGroup(shLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(shLayout.createSequentialGroup()
                        .addGroup(shLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(shLayout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtsh, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(shLayout.createSequentialGroup()
                                .addComponent(jLabel27)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 225, Short.MAX_VALUE)))
                        .addComponent(btoregistrar4))
                    .addGroup(shLayout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addGroup(shLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel25)
                            .addComponent(jLabel24)
                            .addGroup(shLayout.createSequentialGroup()
                                .addComponent(txtid2, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(52, 52, 52))
                            .addComponent(jLabel26))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(shLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane6)
                            .addGroup(shLayout.createSequentialGroup()
                                .addComponent(jComboBox5, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel28)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt17, javax.swing.GroupLayout.DEFAULT_SIZE, 291, Short.MAX_VALUE))
                            .addComponent(txt16))))
                .addContainerGap(70, Short.MAX_VALUE))
        );
        shLayout.setVerticalGroup(
            shLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(shLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(shLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel24)
                    .addComponent(jComboBox5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel28)
                    .addComponent(txt17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(shLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(shLayout.createSequentialGroup()
                        .addComponent(jLabel25)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtid2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(shLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel26))
                .addGroup(shLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(shLayout.createSequentialGroup()
                        .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtsh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(shLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btoregistrar4)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        solapas.addTab("SEGUNDA HORA", sh);

        jLabel29.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel29.setText("Carácter de la Clase:");

        jComboBox6.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Teórico", "Práctico", "Evaluación", "Laboratorio", "Videoteca", " " }));
        jComboBox6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox6ActionPerformed(evt);
            }
        });

        jLabel30.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel30.setText("Actividad a desarrollar:");

        txt18.setColumns(20);
        txt18.setRows(5);
        jScrollPane7.setViewportView(txt18);

        jLabel31.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel31.setText("Observaciones:");

        jLabel32.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        jLabel32.setText("(Uso supervisor de clase)");

        btoregistrar5.setText("Registrar");
        btoregistrar5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btoregistrar5ActionPerformed(evt);
            }
        });

        jLabel33.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel33.setText("Tema de la Clase:");

        txtth.setText("Tercera Hora");

        javax.swing.GroupLayout tcLayout = new javax.swing.GroupLayout(tc);
        tc.setLayout(tcLayout);
        tcLayout.setHorizontalGroup(
            tcLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tcLayout.createSequentialGroup()
                .addGroup(tcLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(tcLayout.createSequentialGroup()
                        .addGroup(tcLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(tcLayout.createSequentialGroup()
                                .addComponent(jLabel32)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(tcLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(txtth, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addComponent(btoregistrar5))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, tcLayout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addGroup(tcLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel30)
                            .addComponent(jLabel29)
                            .addGroup(tcLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtid3, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel31)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(tcLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane7)
                            .addGroup(tcLayout.createSequentialGroup()
                                .addComponent(jComboBox6, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel33)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt20, javax.swing.GroupLayout.DEFAULT_SIZE, 291, Short.MAX_VALUE))
                            .addComponent(txt19))))
                .addContainerGap(70, Short.MAX_VALUE))
        );
        tcLayout.setVerticalGroup(
            tcLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tcLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(tcLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel29)
                    .addComponent(jComboBox6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel33)
                    .addComponent(txt20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(tcLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(tcLayout.createSequentialGroup()
                        .addComponent(jLabel30)
                        .addGap(18, 18, 18)
                        .addComponent(txtid3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(tcLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(tcLayout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(jLabel32)
                        .addGap(0, 30, Short.MAX_VALUE))
                    .addGroup(tcLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(tcLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel31))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(tcLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(tcLayout.createSequentialGroup()
                                .addGap(0, 9, Short.MAX_VALUE)
                                .addComponent(txtth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(tcLayout.createSequentialGroup()
                                .addComponent(btoregistrar5)
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap())
        );

        solapas.addTab("TERCERA HORA", tc);

        jLabel34.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel34.setText("Carácter de la Clase:");

        jComboBox7.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Teórico", "Práctico", "Evaluación", "Laboratorio", "Videoteca", " " }));
        jComboBox7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox7ActionPerformed(evt);
            }
        });

        jLabel35.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel35.setText("Actividad a desarrollar:");

        txt21.setColumns(20);
        txt21.setRows(5);
        jScrollPane8.setViewportView(txt21);

        jLabel36.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel36.setText("Observaciones:");

        jLabel37.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        jLabel37.setText("(Uso supervisor de clase)");

        btoregistrar6.setText("Registrar");
        btoregistrar6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btoregistrar6ActionPerformed(evt);
            }
        });

        jLabel38.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel38.setText("Tema de la Clase:");

        txtch.setText("Cuarta Hora");

        javax.swing.GroupLayout chLayout = new javax.swing.GroupLayout(ch);
        ch.setLayout(chLayout);
        chLayout.setHorizontalGroup(
            chLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(chLayout.createSequentialGroup()
                .addGroup(chLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(chLayout.createSequentialGroup()
                        .addGroup(chLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(chLayout.createSequentialGroup()
                                .addComponent(jLabel37)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(chLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(txtch, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addComponent(btoregistrar6))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, chLayout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addGroup(chLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel35)
                            .addComponent(jLabel34)
                            .addGroup(chLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtid4, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel36)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(chLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane8)
                            .addGroup(chLayout.createSequentialGroup()
                                .addComponent(jComboBox7, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel38)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt23, javax.swing.GroupLayout.DEFAULT_SIZE, 291, Short.MAX_VALUE))
                            .addComponent(txt22))))
                .addContainerGap(70, Short.MAX_VALUE))
        );
        chLayout.setVerticalGroup(
            chLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(chLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(chLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel34)
                    .addComponent(jComboBox7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel38)
                    .addComponent(txt23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(chLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(chLayout.createSequentialGroup()
                        .addComponent(jLabel35)
                        .addGap(18, 18, 18)
                        .addComponent(txtid4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(chLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(chLayout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(jLabel37)
                        .addGap(0, 30, Short.MAX_VALUE))
                    .addGroup(chLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(chLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel36))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(chLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(chLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(txtch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(chLayout.createSequentialGroup()
                                .addComponent(btoregistrar6)
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap())
        );

        solapas.addTab("CUARTA HORA", ch);

        btnnuevo.setText("Nuevo Registro");
        btnnuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnnuevoActionPerformed(evt);
            }
        });

        btnregistro.setText("jButton1");
        btnregistro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnregistroActionPerformed(evt);
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
                    .addComponent(jScrollPane1)
                    .addComponent(solapas, javax.swing.GroupLayout.DEFAULT_SIZE, 786, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnregistro, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnnuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(año, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtidcl, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(fecha, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtclasen, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 468, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10)
                    .addComponent(txtidcl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(año, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnnuevo)
                    .addComponent(btnregistro))
                .addGap(5, 5, 5)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1)
                    .addComponent(fecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel4)
                        .addComponent(txtclasen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(solapas, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
            asigdocente(idcursodivi.getText(),txtidcl.getText(),iddocente.getText());
        }
        curso.setText(combocurso.getSelectedItem().toString());
        //asigdocente(idcursodivi.getText());
    }//GEN-LAST:event_combocursoActionPerformed

    private void comboasignaturaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboasignaturaActionPerformed
        // TODO add your handling code here:
         
    }//GEN-LAST:event_comboasignaturaActionPerformed

    private void btnbuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbuscarActionPerformed
        //tablanotas(idasignatura.getText(), idcursodivi.getText(),trimestre.getText());
        
        
        btnnuevo.setVisible(true);
//        sh.setSelectedIndex(1);
         solapas.setEnabledAt(1, false);
         solapas.setEnabledAt(2, false);
         solapas.setEnabledAt(3, false);
        
        jTextField1.setText(comboasignatura.getSelectedItem().toString());
        idasignatura(txt1.getText(), jTextField1.getText());
        
        btoregistrar.setVisible(true);
       btnnuevo.setEnabled(true);
        
        comboasignatura.setEnabled(false);
        combocurso.setEnabled(false);

        librodetema(idcursodivi.getText(), idasignatura.getText(), txtidcl.getText());
        
        
//        DefaultTableModel modelo = (DefaultTableModel)jTable1.getModel();
//        modelo.addRow(new Object[]{"","","","",""});
        
      
    }//GEN-LAST:event_btnbuscarActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        btnbuscar.setEnabled(true);
        btoregistrar.setVisible(false);
        btoregistrar4.setVisible(false);
        btoregistrar5.setVisible(false);
        btoregistrar6.setVisible(false);
        
        comboasignatura.setEnabled(true);
        combocurso.setEnabled(true); 
        
        solapas.setEnabledAt(1, false);
         solapas.setEnabledAt(2, false);
         solapas.setEnabledAt(3, false);
        
        
//        fecha.setDateFormatString("");
       
        txtclasen.setText("");
        txt5.setText("");
        txt6.setText("");
        txt7.setText("");
        txt17.setText("");
        txt15.setText("");
        txt16.setText("");
        txt20.setText("");
        txt18.setText("");
        txt19.setText("");
        txt23.setText("");
        txt21.setText("");
        txt22.setText("");
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void txtclasenKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtclasenKeyTyped
        // TODO add your handling code here:
        char validar=evt.getKeyChar();

        if(Character.isLetter(validar)){
            getToolkit().beep();
            evt.consume();
            JOptionPane.showMessageDialog(rootPane, "Ingresar solo Numeros");
        }
    }//GEN-LAST:event_txtclasenKeyTyped

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked

      solapas.setVisible(true);
        jLabel1.setVisible(true);
        fecha.setVisible(true);
        jLabel4.setVisible(true);
        txtclasen.setVisible(true);  
        
       txt7.setText("");
       txt16.setText("");
       txt19.setText("");
       txt22.setText("");
       
       fecha.setEnabled(false);
                txtclasen.setEnabled(false);
                
                txtid1.setEnabled(false);
//                jComboBox1.setEnabled(false);
                txt5.setEnabled(false);
                txt6.setEnabled(false);
                txt7.setEnabled(false);
                
                 txtid2.setText("");
//                jComboBox5.removeAllItems();
                txt17.setText("");
                txt15.setText("");
                txt16.setText("");
                
                txtid3.setText("");
//                jComboBox6.removeAllItems();
                txt20.setText("");
                txt18.setText("");
                txt19.setText("");
                
                txtid4.setText("");
//                jComboBox7.removeAllItems();
                txt23.setText("");
                txt21.setText("");
                txt22.setText("");
        
       solapas.setEnabledAt(1, true);
         solapas.setEnabledAt(2, true);
         solapas.setEnabledAt(3, true);
       
       
        int column = jTable1.getColumnModel().getColumnIndexAtX(evt.getX());
        int row = evt.getY() / jTable1.getRowHeight();
        int eli = jTable1.getSelectedRow();
        
        if (row < jTable1.getRowCount() && row >= 0 && column < jTable1.getColumnCount() && column >= 0) {

            Object value = jTable1.getValueAt(row, column);
            if (value instanceof JButton) {
                ((JButton) value).doClick();
                JButton boton = (JButton) value;
            
                fecha.setDate((Date) (jTable1.getValueAt(eli, 1)));
                txtclasen.setText(String.valueOf(jTable1.getValueAt(eli, 2)));

                detalleprimerahora(idcursodivi.getText(), idasignatura.getText(), txtidcl.getText(),String.valueOf(jTable1.getValueAt(eli, 2)));
                detallesegundahora(idcursodivi.getText(), idasignatura.getText(), txtidcl.getText(),String.valueOf(jTable1.getValueAt(eli, 2)));
                detalletercerahora(idcursodivi.getText(), idasignatura.getText(), txtidcl.getText(),String.valueOf(jTable1.getValueAt(eli, 2)));
                detallecuartahora(idcursodivi.getText(), idasignatura.getText(), txtidcl.getText(),String.valueOf(jTable1.getValueAt(eli, 2)));
                
                fecha.setEnabled(false);
                txtclasen.setEnabled(false);
                
                 txtid1.setEnabled(false);
                jComboBox1.setEnabled(false);
                txt5.setEnabled(false);
                txt6.setEnabled(false);
                txt7.setEnabled(false);
                
                 txtid2.setEnabled(false);
                jComboBox5.setEnabled(false);
                txt17.setEnabled(false);
                txt15.setEnabled(false);
                txt16.setEnabled(false);
                
                txtid3.setEnabled(false);
                jComboBox6.setEnabled(false);
                txt20.setEnabled(false);
                txt18.setEnabled(false);
                txt19.setEnabled(false);
                
                txtid4.setEnabled(false);
                jComboBox7.setEnabled(false);
                txt23.setEnabled(false);
                txt21.setEnabled(false);
                txt22.setEnabled(false);
                
                
            }
        }
        btoregistrar.setVisible(false);
        
        btnregistro.setVisible(true);
        btnnuevo.setVisible(false);
        
                
    }//GEN-LAST:event_jTable1MouseClicked

    private void btoregistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btoregistrarActionPerformed
        // TODO add your handling code here:
        
         
        try {
            PreparedStatement pps = cn.prepareStatement("insert into registrotema (fecha, clasen, caracterdclase, temadeldia, actividadadesarrollar, observacion, idciclolectivo, idcurso_division, idasignatura, hora)"
                + "values (?,?,?,?,?,?,?,?,?,?)");

            pps.setDate(1, new java.sql.Date(fecha.getDate().getTime()));
            pps.setInt(2, Integer.parseInt(txtclasen.getText()));
            pps.setString(3, jComboBox1.getSelectedItem().toString());
            pps.setString(4, txt5.getText());
            pps.setString(5, txt6.getText());
            pps.setString(6, txt7.getText());
            pps.setInt(7, Integer.parseInt(txtidcl.getText()));
            pps.setInt(8, Integer.parseInt(idcursodivi.getText()));
            pps.setInt(9, Integer.parseInt(idasignatura.getText()));
            pps.setString(10, txtph.getText());
            int res = pps.executeUpdate();
            if (res > 0) {
                JOptionPane.showMessageDialog(null, "Nuevo Registro Cargado");

                DefaultTableModel modelo3 = (DefaultTableModel)librodetema.jTable1.getModel();
                librodetema.jTable1.setModel(modelo3);
                modelo3.setRowCount(0);
                librodetema(idcursodivi.getText(), idasignatura.getText(), txtidcl.getText());

                btoregistrar.setVisible(false);

                int bool = JOptionPane.showConfirmDialog(null, "¿Desea registrar Nueva Hora?", "Nueva Hora", JOptionPane.YES_NO_OPTION);
                
                  if (bool == JOptionPane.YES_OPTION){
                  solapas.setSelectedIndex(1);
                  solapas.setEnabledAt(1, true);
                  btoregistrar4.setVisible(true);
                  }
                
            } else {
                JOptionPane.showMessageDialog(null, "Error al Guardar Registro");

            }

        } catch (Exception e) {
        }
    }//GEN-LAST:event_btoregistrarActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jComboBox6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox6ActionPerformed

    private void btoregistrar5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btoregistrar5ActionPerformed
        // TODO add your handling code here:
          
        
        try {
            PreparedStatement pps = cn.prepareStatement("insert into registrotema (fecha, clasen, caracterdclase, temadeldia, actividadadesarrollar, observacion, idciclolectivo, idcurso_division, idasignatura, hora)"
                + "values (?,?,?,?,?,?,?,?,?,?)");

            pps.setDate(1, new java.sql.Date(fecha.getDate().getTime()));
            pps.setInt(2, Integer.parseInt(txtclasen.getText()));
            pps.setString(3, jComboBox6.getSelectedItem().toString());
            pps.setString(4, txt20.getText());
            pps.setString(5, txt18.getText());
            pps.setString(6, txt19.getText());
            pps.setInt(7, Integer.parseInt(txtidcl.getText()));
            pps.setInt(8, Integer.parseInt(idcursodivi.getText()));
            pps.setInt(9, Integer.parseInt(idasignatura.getText()));
            pps.setString(10, txtsh.getText());
            int res = pps.executeUpdate();
            if (res > 0) {
                JOptionPane.showMessageDialog(null, "Nuevo Registro Cargado");

                btoregistrar5.setVisible(false);
                
                  int bool = JOptionPane.showConfirmDialog(null, "¿Desea registrar Nueva Hora?", "Nueva Hora", JOptionPane.YES_NO_OPTION);
                
                  if (bool == JOptionPane.YES_OPTION){
                 solapas.setSelectedIndex(3);
                 solapas.setEnabledAt(3, true);
                 btoregistrar6.setVisible(true);   
                  }

            } else {
                JOptionPane.showMessageDialog(null, "Error al Guardar Registro");

            }

        } catch (Exception e) {
        }
    }//GEN-LAST:event_btoregistrar5ActionPerformed

    private void jComboBox7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox7ActionPerformed

    private void btoregistrar6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btoregistrar6ActionPerformed
        // TODO add your handling code here:
        try {
            PreparedStatement pps = cn.prepareStatement("insert into registrotema (fecha, clasen, caracterdclase, temadeldia, actividadadesarrollar, observacion, idciclolectivo, idcurso_division, idasignatura, hora)"
                + "values (?,?,?,?,?,?,?,?,?,?)");

            pps.setDate(1, new java.sql.Date(fecha.getDate().getTime()));
            pps.setInt(2, Integer.parseInt(txtclasen.getText()));
            pps.setString(3, jComboBox7.getSelectedItem().toString());
            pps.setString(4, txt23.getText());
            pps.setString(5, txt21.getText());
            pps.setString(6, txt22.getText());
            pps.setInt(7, Integer.parseInt(txtidcl.getText()));
            pps.setInt(8, Integer.parseInt(idcursodivi.getText()));
            pps.setInt(9, Integer.parseInt(idasignatura.getText()));
            pps.setString(10, txtsh.getText());
            int res = pps.executeUpdate();
            if (res > 0) {
                JOptionPane.showMessageDialog(null, "Nuevo Registro Cargado");


                btoregistrar6.setVisible(false);

            } else {
                JOptionPane.showMessageDialog(null, "Error al Guardar Registro");

            }

        } catch (Exception e) {
        }
    }//GEN-LAST:event_btoregistrar6ActionPerformed

    private void btoregistrar4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btoregistrar4ActionPerformed

        try {
            PreparedStatement pps = cn.prepareStatement("insert into registrotema (fecha, clasen, caracterdclase, temadeldia, actividadadesarrollar, observacion, idciclolectivo, idcurso_division, idasignatura, hora)"
                + "values (?,?,?,?,?,?,?,?,?,?)");

            pps.setDate(1, new java.sql.Date(fecha.getDate().getTime()));
            pps.setInt(2, Integer.parseInt(txtclasen.getText()));
            pps.setString(3, jComboBox5.getSelectedItem().toString());
            pps.setString(4, txt17.getText());
            pps.setString(5, txt15.getText());
            pps.setString(6, txt16.getText());
            pps.setInt(7, Integer.parseInt(txtidcl.getText()));
            pps.setInt(8, Integer.parseInt(idcursodivi.getText()));
            pps.setInt(9, Integer.parseInt(idasignatura.getText()));
            pps.setString(10, txtsh.getText());
            int res = pps.executeUpdate();
            if (res > 0) {
                JOptionPane.showMessageDialog(null, "Nuevo Registro Cargado");

                btoregistrar4.setVisible(false);

                int bool = JOptionPane.showConfirmDialog(null, "¿Desea registrar Nueva Hora?", "Nueva Hora", JOptionPane.YES_NO_OPTION);

                if (bool == JOptionPane.YES_OPTION){
                    solapas.setSelectedIndex(2);
                    solapas.setEnabledAt(2, true);
                    btoregistrar4.setVisible(true);
                }

            } else {
                JOptionPane.showMessageDialog(null, "Error al Guardar Registro");

            }

        } catch (Exception e) {
        }
    }//GEN-LAST:event_btoregistrar4ActionPerformed

    private void jComboBox5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox5ActionPerformed

    private void btnnuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnnuevoActionPerformed
        // TODO add your handling code here:
        solapas.setVisible(true);
        jLabel1.setVisible(true);
        fecha.setVisible(true);
        jLabel4.setVisible(true);
        txtclasen.setVisible(true);
        txtclasen.setEnabled(false);
        max();
    }//GEN-LAST:event_btnnuevoActionPerformed

    private void btnregistroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnregistroActionPerformed
        // TODO add your handling code here:
        
        // borran datos
        fecha.setCalendar(null);
        txt5.setText("");
        txt6.setText("");
        txt7.setText("");
        txt17.setText("");
        txt15.setText("");
        txt16.setText("");
        txt20.setText("");
        txt18.setText("");
        txt19.setText("");
        txt23.setText("");
        txt21.setText("");
        txt22.setText("");
        
        //Hacen editable
        fecha.setEnabled(true);
        jComboBox5.setEnabled(true);
        jComboBox6.setEnabled(true);
        jComboBox6.setEnabled(true);
        jComboBox1 .setEnabled(true);
        txt5.setEnabled(true);
        txt6.setEnabled(true);
        txt17.setEnabled(true);
        txt15.setEnabled(true);
        txt20.setEnabled(true);
        txt18.setEnabled(true);
        txt23.setEnabled(true);
        txt21.setEnabled(true);
     
         solapas.setEnabledAt(1, false);
         solapas.setEnabledAt(2, false);
         solapas.setEnabledAt(3, false);
        
         btoregistrar.setVisible(true);
        max();
    }//GEN-LAST:event_btnregistroActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel año;
    private javax.swing.JButton btnbuscar;
    private javax.swing.JButton btnnuevo;
    private javax.swing.JButton btnregistro;
    private javax.swing.JButton btoregistrar;
    private javax.swing.JButton btoregistrar4;
    private javax.swing.JButton btoregistrar5;
    private javax.swing.JButton btoregistrar6;
    private javax.swing.JPanel ch;
    private javax.swing.JComboBox comboasignatura;
    private javax.swing.JComboBox combocurso;
    private javax.swing.JTextField curso;
    private com.toedter.calendar.JDateChooser fecha;
    public static javax.swing.JTextField idasignatura;
    public static javax.swing.JTextField idcursodivi;
    public static javax.swing.JTextField iddocente;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox5;
    private javax.swing.JComboBox<String> jComboBox6;
    private javax.swing.JComboBox<String> jComboBox7;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    public static javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JPanel ph;
    private javax.swing.JPanel sh;
    private javax.swing.JTabbedPane solapas;
    private javax.swing.JPanel tc;
    private javax.swing.JTextField txt1;
    private javax.swing.JTextArea txt15;
    private javax.swing.JTextField txt16;
    private javax.swing.JTextField txt17;
    private javax.swing.JTextArea txt18;
    private javax.swing.JTextField txt19;
    private javax.swing.JTextField txt2;
    private javax.swing.JTextField txt20;
    private javax.swing.JTextArea txt21;
    private javax.swing.JTextField txt22;
    private javax.swing.JTextField txt23;
    private javax.swing.JTextField txt5;
    private javax.swing.JTextArea txt6;
    private javax.swing.JTextField txt7;
    private javax.swing.JTextField txtch;
    private javax.swing.JTextField txtclasen;
    private javax.swing.JTextField txtid1;
    private javax.swing.JTextField txtid2;
    private javax.swing.JTextField txtid3;
    private javax.swing.JTextField txtid4;
    public static javax.swing.JTextField txtidcl;
    public static javax.swing.JTextField txtidcl1;
    private javax.swing.JTextField txtph;
    private javax.swing.JTextField txtsh;
    private javax.swing.JTextField txtth;
    // End of variables declaration//GEN-END:variables
}