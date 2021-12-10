
package paquetes;

import codigo.GenerarCodigos;
//import com.sun.xml.internal.bind.v2.model.core.ID;
import conexion.conectar;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import tesis.render;


public class promocionar extends javax.swing.JInternalFrame {

    conectar cc = new conectar();
    Connection cn = cc.conexion();
    ResultSet rs;
    
    public promocionar() {
        initComponents();
        clactual();
        clpromocionar();
        cursodocente();
        idcd.setVisible(false);
        txtcurso.setVisible(false);
        txtd.setVisible(false);
        txtidcp.setVisible(false);
        txtcursoapromocionar.setVisible(false);
        txtestadom.setVisible(false);
        btnfiltrar.setVisible(false);
        txt1.setVisible(false);
        txtdivisionapromocionar.setVisible(false);
        btnpromocionaregresados.setVisible(false);
        txtestadoa.setVisible(false);
        txtclp.setEnabled(false);
        idclp.setVisible(false);
        cboegresado.setVisible(false);
        activar();
        idcurso.setVisible(false);
    }

    void activar (){
    if (txtclp.getText().isEmpty())
            {
                System.out.println("vacio");
                 btnpromocionar.setEnabled(false);
                 btnpromocionaregresados.setEnabled(false);
            }
    else{
    System.out.println("lleno");
    }

    }
    
    void clactual (){
        try {
            PreparedStatement pps=cn.prepareStatement("SELECT idciclolectivo, año  FROM ciclolectivo where año=year(now())");
            rs = pps.executeQuery();
            while (rs.next()) {
                idcla.setText(rs.getString(1));
                txtcla.setText(rs.getString(2));
                
                txtcla.setEnabled(false);
                idcla.setVisible(false);
                
                
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    void clpromocionar (){
        try {
            PreparedStatement pps=cn.prepareStatement("SELECT idciclolectivo, año  FROM ciclolectivo where año>year(now())");
            rs = pps.executeQuery();
            while (rs.next()) {
                idclp.setText(rs.getString(1));
                txtclp.setText(rs.getString(2));
        
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
   
    void cursodocente (){
    try {
                PreparedStatement pps=cn.prepareStatement("SELECT c.descripcion, cd.division FROM curso_division cd, curso c "
                        + "where cd.idcurso=c.idcurso order by c.descripcion, cd.division");
                rs   = pps.executeQuery();
      while (rs.next()) {
      this.combocurso.addItem(rs.getString("c.descripcion")+ " Division "  +rs.getString("cd.division"));
      }
                 } catch (Exception e) {
            }
    
    } 
    
    void idcd (String valor, String valor1){
    try {
                PreparedStatement pps=cn.prepareStatement("SELECT cd.idcurso_division, c.idcurso "
                        + "FROM curso_division cd, curso c where cd.idcurso=c.idcurso "
                        + "and  c.Descripcion='"+valor+"'and division='"+valor1+"'");
                rs   = pps.executeQuery();
      while (rs.next()) {
      idcd.setText(rs.getString("cd.idcurso_division"));
      idcurso.setText(rs.getString("c.idcurso"));
      
      }
                 } catch (Exception e) {
            }
    }
    
    void idcdapromocionar (String valo2, String valor3){
//        if (combocursosig.getSelectedIndex()==-1){
    try {
                PreparedStatement pps=cn.prepareStatement("SELECT idcurso_division "
                        + "FROM curso_division, curso where curso_division.idcurso=curso.idcurso "
                        + "and  curso.Descripcion='"+valo2+"'and division='"+valor3+"'");
                rs   = pps.executeQuery();
      while (rs.next()) {
      txtidcp.setText(rs.getString(1));
      
      }
                 } catch (Exception e) {
            }
//    }
    }
    
    
    void sigcurso ( String valor){
    
       try {
          PreparedStatement pps=cn.prepareStatement("SELECT cd.idcurso_division, c.descripcion, cd.division "
              + "FROM curso_division cd, curso c "
              + "where cd.idcurso=c.idcurso and  c.descripcion>'"+valor+"' limit 1");
            rs = pps.executeQuery();
            while (rs.next()) {
//                txtidcp.setText(rs.getString("cd.idcurso_division"));
                txtcursoapromocionar.setText(rs.getString("c.descripcion"));
                
//                txtclp.setEnabled(false);
//                idclp.setVisible(false);
                
                
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
     
    void cursoapromocionar (String valor){
    if (combocurso.getSelectedIndex()>-1){
         try {
                PreparedStatement pps=cn.prepareStatement("SELECT cd.idcurso_division, c.descripcion, cd.division "
                        + "FROM curso_division cd, curso c where cd.idcurso=c.idcurso and  c.descripcion='"+valor+"'");
                rs   = pps.executeQuery();
      while (rs.next()) {
      this.combocursosig.addItem(rs.getString("c.descripcion")+ " Division "  +rs.getString("cd.division"));
      }
                 } catch (Exception e) {
            }
    }
    }
    
    void alumnos (String valor) {
    
    tablaalumnos.setDefaultRenderer(Object.class, new render());
     
      JButton btn = new JButton("Ver");
      
 
   String [] titulos= {"ID","DNI","APELLIDO", "NOMBRE","PROM FINAL"};
        DefaultTableModel modelo = new  DefaultTableModel(null,titulos)
        {
            
            public boolean isCellEditable(int row, int column){
                return false;
            }
            
        };
           
   
    Object datos []= new Object[5];
    String sql="SELECT a.idalumno, a.nombre, a.Apellido, a.DNI, em.estado "
            + "FROM alumno a, detalle_matricula dm, curso_division cd, ciclolectivo cl, estado_alumno ea, estado_matricula em "
            + "where dm.idAlumno=a.idAlumno and dm.idcurso_division=cd.idcurso_division and a.idestado_alumno=ea.idestado_alumno "
            + "and dm.idciclolectivo=cl.idciclolectivo and dm.idestado_matricula=em.idestado_matricula "
            + "and dm.idcurso_division='"+valor+"' and cl.año=year(now()) and ea.estado='Activo' and em.estado in('Pre-Inscripto','Inscripto')";
   try {
            Statement st = cn.createStatement();
             rs = st.executeQuery(sql);
            while(rs.next())
            {
                datos[0]=rs.getString("a.idalumno");
                datos[1]=rs.getString("a.dni");
                datos[2]=rs.getString("a.apellido");
                datos[3]=rs.getString("a.nombre");
                datos[4]=btn;
                modelo.addRow(datos);
                
            }
            tablaalumnos.setModel(modelo);
            tablaalumnos.setRowHeight(20);
            
            tablaalumnos.getColumnModel().getColumn(0).setMaxWidth(0);
            tablaalumnos.getColumnModel().getColumn(0).setMinWidth(0);
            tablaalumnos.getColumnModel().getColumn(0).setPreferredWidth(0);
            
        } 
   
   catch (SQLException ex) {
            System.out.println(ex);
        }
        
    }
    
    void proalumnos (String valor, String valor1){
        
        int curso=Integer.parseInt(idcurso.getText());
    
         tablaprom.setDefaultRenderer(Object.class, new render());
     
     
      
 
   String [] titulos= {"ASIGNATURA","PROM FINAL", "ESTADO"};
        DefaultTableModel modelo1 = new  DefaultTableModel(null,titulos)
        {
            
            public boolean isCellEditable(int row, int column){
                return false;
            }
            
        };
           
   
    Object datos []= new Object[3];
    String sql="SELECT  ag. nombre, "
            + "ROUND((avg(Nota1 + Nota2  + Nota3)/3),2) as prom "
            + "from notas n, alumno a, trimestre t, ciclolectivo cl, "
            + "asignatura ag where a.idalumno=n.idalumno "
            + "and n.idasignatura=ag.idasignatura "
            + "and n.idcurso_division='"+valor+"' and t.idTrimestre=n.idTrimestre "
            + "and t.idciclolectivo=cl.idciclolectivo and cl.año=year(now())and a.idalumno='"+valor1+"' "
            + "group by a.dni,a.sexo,a.apellido,a.nombre, n.idasignatura  order "
            + "by a.idAlumno, ag.idasignatura";
   try {
            Statement st = cn.createStatement();
             rs = st.executeQuery(sql);
            while(rs.next())
            {
                datos[0]=rs.getString("ag.nombre");
//                datos[1]=rs.getString("prom");
                double promf = Double.parseDouble(rs.getString("prom"));
                
                if(curso <= 3 ){ 
                     datos[1]=Math.round(promf);
                 if(promf<6 ){
                    datos[2]="Desaprobado";}
                        else {
                  datos[2]="Aprobado"; }    
                }
                else {
                   datos[1]=promf;
                if(promf<6 ){
                    datos[2]="Desaprobado";}
                        else {
                  datos[2]="Aprobado"; }
                }
                                                    
                                                    //datos[1]=promf;
                                                    // if(promf<=6 )
                                                    //datos[2]="Desaprobado";
                                                    //else 
                                                    // datos[2]="Aprobado";
                modelo1.addRow(datos);
                
            }
            
             tablaprom.setModel(modelo1);
             tablaprom.setRowHeight(20);
//            
//            tablaalumnos.getColumnModel().getColumn(0).setMaxWidth(0);
//            tablaalumnos.getColumnModel().getColumn(0).setMinWidth(0);
//            tablaalumnos.getColumnModel().getColumn(0).setPreferredWidth(0);
            
        } 
   
   catch (SQLException ex) {
            System.out.println(ex);
        }
        
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        txtcla = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtclp = new javax.swing.JTextField();
        idcla = new javax.swing.JTextField();
        idclp = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        combocurso = new javax.swing.JComboBox();
        jLabel5 = new javax.swing.JLabel();
        idcd = new javax.swing.JTextField();
        txtcurso = new javax.swing.JTextField();
        txtd = new javax.swing.JTextField();
        txtcursoapromocionar = new javax.swing.JTextField();
        txtidcp = new javax.swing.JTextField();
        combocursosig = new javax.swing.JComboBox<String>();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaalumnos = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaprom = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        labelalumno = new javax.swing.JLabel();
        btnpromocionar = new javax.swing.JButton();
        txtestadom = new javax.swing.JTextField();
        txtdivisionapromocionar = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        txt1 = new javax.swing.JTextField();
        btnfiltrar = new javax.swing.JButton();
        txtestadoa = new javax.swing.JTextField();
        btnpromocionaregresados = new javax.swing.JButton();
        cboegresado = new javax.swing.JComboBox<String>();
        jButton2 = new javax.swing.JButton();
        idcurso = new javax.swing.JTextField();

        jLabel3.setText("Curso:");

        setClosable(true);
        setIconifiable(true);
        setTitle("Promocionar");

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("Ciclo Lectivo Actual");

        txtcla.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("Ciclo Lectivo a Promocionar");

        txtclp.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setText("Curso Actual");

        combocurso.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "--Sellecione un Curso--" }));
        combocurso.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                combocursoItemStateChanged(evt);
            }
        });
        combocurso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                combocursoActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setText("Curso a Promocionar");

        combocursosig.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "--Sellecionar--" }));
        combocursosig.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                combocursosigActionPerformed(evt);
            }
        });

        tablaalumnos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tablaalumnos.getTableHeader().setResizingAllowed(false);
        tablaalumnos.getTableHeader().setReorderingAllowed(false);
        tablaalumnos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaalumnosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tablaalumnos);

        tablaprom.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane2.setViewportView(tablaprom);

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setText("Alumno");

        labelalumno.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        btnpromocionar.setText("Promocionar Alumnos");
        btnpromocionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnpromocionarActionPerformed(evt);
            }
        });

        txtestadom.setText("2");

        jButton1.setText("Buscar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
                buscar(evt);
            }
        });

        btnfiltrar.setText("Nuevo");
        btnfiltrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnfiltrarActionPerformed(evt);
            }
        });

        btnpromocionaregresados.setText("Egresar Alumnos");
        btnpromocionaregresados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnpromocionaregresadosActionPerformed(evt);
            }
        });

        cboegresado.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Egresado" }));

        jButton2.setText("Atras");
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
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(combocurso, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGap(170, 170, 170)
                                .addComponent(idcla, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(23, 23, 23)
                                .addComponent(txtestadoa))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(18, 18, 18)
                                .addComponent(txtcla, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(idcurso, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(118, 118, 118)
                                .addComponent(txtestadom, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(idclp, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel2)
                                .addGap(18, 18, 18)
                                .addComponent(txtclp, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(50, 50, 50))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 84, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 619, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addGroup(layout.createSequentialGroup()
                                                    .addComponent(jLabel6)
                                                    .addGap(18, 18, 18)
                                                    .addComponent(labelalumno, javax.swing.GroupLayout.PREFERRED_SIZE, 520, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 615, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGap(20, 20, 20))
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(idcd, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(txtcurso, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(txtd, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(278, 278, 278)
                                            .addComponent(txtcursoapromocionar, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(txtidcp, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(36, 36, 36)
                                            .addComponent(txt1, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(txtdivisionapromocionar, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                            .addComponent(btnpromocionaregresados)
                                            .addGap(18, 18, 18)
                                            .addComponent(btnpromocionar))))
                                .addGap(68, 68, 68))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(btnfiltrar)
                                    .addGap(18, 18, 18)
                                    .addComponent(jButton1)
                                    .addGap(376, 376, 376))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addComponent(jLabel5)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(cboegresado, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(combocursosig, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton2)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtcla, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(txtclp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(idclp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(idcla, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtestadom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtestadoa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(idcurso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(combocurso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(combocursosig, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboegresado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(idcd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtcurso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtcursoapromocionar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtidcp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtdivisionapromocionar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnfiltrar)
                    .addComponent(jButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(labelalumno, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnpromocionar)
                    .addComponent(btnpromocionaregresados))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton2)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void combocursoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combocursoActionPerformed

         if (combocurso.getSelectedIndex()==0){
                 } else {
            String item =combocurso.getSelectedItem().toString();
            String [] data=item.split(" ");
            txtcurso.setText(data [0] + " " + data[1]);
            txtd.setText(data [3]);
        }
        idcd(txtcurso.getText(), txtd.getText());
        sigcurso(txtcurso.getText());
       
        if (txtcurso.getText().equals("7° Año")){   
            txtestadoa.setText("3");
            txt1.setText("");
            txtdivisionapromocionar.setText("");
            txtidcp.setText("");
            txtcursoapromocionar.setText("");
            combocursosig.setVisible(false);
            cboegresado.setVisible(true);
            btnpromocionar.setVisible(false);
            btnpromocionaregresados.setVisible(true);
            
        }else{ 
            
            btnpromocionar.setVisible(true);
            btnpromocionaregresados.setVisible(false);
            combocursosig.setVisible(true);
            cboegresado.setVisible(false);
            combocursosig.removeAllItems();
            cursoapromocionar(txtcursoapromocionar.getText());
        }   

    }//GEN-LAST:event_combocursoActionPerformed

    private void combocursoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_combocursoItemStateChanged
        // TODO add your handling code here:
        
//        if(evt.getSource()==combocurso) {
//			String sellecionado=(String)combocurso.getSelectedItem();
//			setTitle(sellecionado);
//		}
    }//GEN-LAST:event_combocursoItemStateChanged

    private void tablaalumnosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaalumnosMouseClicked
        // TODO add your handling code here:
        int column = tablaalumnos.getColumnModel().getColumnIndexAtX(evt.getX());
        int row = evt.getY() / tablaalumnos.getRowHeight();

        if (row < tablaalumnos.getRowCount() && row >= 0 && column < tablaalumnos.getColumnCount() && column >= 0) {

            Object value = tablaalumnos.getValueAt(row, column);
            if (value instanceof JButton) {
                ((JButton) value).doClick();
                JButton boton = (JButton) value;

                int eli = tablaalumnos.getSelectedRow();
                labelalumno.setText(String.valueOf(tablaalumnos.getValueAt(eli, 2)) +" "+ String.valueOf(tablaalumnos.getValueAt(eli, 3)));
               
                DefaultTableModel modelo3 = (DefaultTableModel)tablaprom.getModel();
                tablaprom.setModel(modelo3);
                modelo3.setRowCount(0);
                proalumnos(idcd.getText(), tablaalumnos.getValueAt(eli, 0).toString());
                

            }

        }
    }//GEN-LAST:event_tablaalumnosMouseClicked

    private void btnpromocionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnpromocionarActionPerformed
        // TODO add your handling code here:
    if (tablaalumnos.getRowCount() > 0){
        int bool = JOptionPane.showConfirmDialog(null,"CICLO LECTIVO A PROMOCIONAR:"+" "+txtclp.getText()+"\n"+"CURSO A PROMOCIONAR:"+" "+combocursosig.getSelectedItem(),"Promocionar Alumnos",JOptionPane.YES_NO_OPTION);
       
    if(bool == JOptionPane.YES_OPTION){
      for(int i=0; i < tablaalumnos.getRowCount(); i++){
            
        try {

        PreparedStatement pps=cn.prepareStatement("insert into detalle_matricula (idAlumno, idciclolectivo, idcurso_division, idestado_matricula) values(?,?,?,?)");
        pps.setString(1, tablaalumnos.getValueAt(i, 0).toString());
        pps.setString(2, idclp.getText());
        pps.setString(3, txtidcp.getText());
        pps.setString(4, txtestadom.getText());
   
        int res = pps.executeUpdate();
        if(res > 0) {
       
         }
       
        } catch (Exception e) {
        }
                
            }     JOptionPane.showMessageDialog(null, "DEBERA CONFIRMAR MATRICULACIÓN, ESTADO ALUMNO PRE-MATRICULADO", "Alumno Promocionados", JOptionPane.WARNING_MESSAGE);    
        }else
        {
        }
        
       }else {
    }
        
    }//GEN-LAST:event_btnpromocionarActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
//         if (txtcurso.getText().equals("7° Año")){ 
//            //combocursosig.setVisible(false);
//            //txteg.setVisible(true);
//            combocursosig.addItem("Egresado");
//            txtestadoa.setText("3");
//            btnpromocionaregresados.setVisible(true);
//            btnpromocionar.setVisible(false);
//            System.out.println("true");
//        }else{ 
//            System.out.println("falso");
//            //combocursosig.setVisible(true);
//            combocursosig.removeAllItems();
//            cursoapromocionar(txtcursoapromocionar.getText());
//        }
//        combocursosig.removeAllItems();
//        cursoapromocionar(txtcursoapromocionar.getText());
        
        DefaultTableModel modelo3 = (DefaultTableModel)tablaalumnos.getModel();
        tablaalumnos.setModel(modelo3);
        modelo3.setRowCount(0);
        
        alumnos(idcd.getText());
        idcdapromocionar(txt1.getText(),txtdivisionapromocionar.getText());
        
        combocurso.setEnabled(false);
        combocursosig.setEnabled(false);
        btnfiltrar.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void combocursosigActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combocursosigActionPerformed
        // TODO add your handling code here:
        if (combocursosig.getSelectedIndex()==-1){
  } else {
            String item =combocursosig.getSelectedItem().toString();
            String [] data=item.split(" ");
            txt1.setText(data [0] + " " + data[1]);
            txtdivisionapromocionar.setText(data [3]);      
        }
 
    }//GEN-LAST:event_combocursosigActionPerformed

    private void buscar(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buscar
        // TODO add your handling code here:
        
    }//GEN-LAST:event_buscar

    private void btnfiltrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnfiltrarActionPerformed
        // TODO add your handling code here:
        
       
        DefaultTableModel modelo3 = (DefaultTableModel)tablaalumnos.getModel();
        tablaalumnos.setModel(modelo3);
        modelo3.setRowCount(0);
        
        DefaultTableModel modelo1 = (DefaultTableModel)tablaprom.getModel();
        tablaprom.setModel(modelo1);
        modelo1.setRowCount(0);
        
        btnfiltrar.setVisible(false);
        combocurso.setEnabled(true);
        combocursosig.setEnabled(true);
    }//GEN-LAST:event_btnfiltrarActionPerformed

    private void btnpromocionaregresadosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnpromocionaregresadosActionPerformed
        // TODO add your handling code here:
         if (tablaalumnos.getRowCount() > 0){
        int bool = JOptionPane.showConfirmDialog(null,"CICLO LECTIVO:"+" "+txtcla.getText()+"\n"+"CURSO A EGRESAR:"+" "+txtcurso.getText(),"Egresar Alumnos",JOptionPane.YES_NO_OPTION);
       
    if(bool == JOptionPane.YES_OPTION){
      for(int i=0; i < tablaalumnos.getRowCount(); i++){
            
        try {               

        PreparedStatement pps=cn.prepareStatement("update alumno set idestado_alumno=3 where idalumnos=");
        pps.setString(1, tablaalumnos.getValueAt(i, 0).toString());
        pps.setString(2, txtestadoa.getText());
       
   
        int res = pps.executeUpdate();
        if(res > 0) {
       
         }
       
        } catch (Exception e) {
        }
                
            }     JOptionPane.showMessageDialog(null, "ESTADO ALUMNO: -EGRESADO-","Alumno Egresados", JOptionPane.WARNING_MESSAGE);    
        }else
        {
        }
        
       }else {
    }
      
    }//GEN-LAST:event_btnpromocionaregresadosActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnfiltrar;
    private javax.swing.JButton btnpromocionar;
    private javax.swing.JButton btnpromocionaregresados;
    private javax.swing.JComboBox<String> cboegresado;
    private javax.swing.JComboBox combocurso;
    private javax.swing.JComboBox<String> combocursosig;
    private javax.swing.JTextField idcd;
    private javax.swing.JTextField idcla;
    private javax.swing.JTextField idclp;
    private javax.swing.JTextField idcurso;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel labelalumno;
    private javax.swing.JTable tablaalumnos;
    private javax.swing.JTable tablaprom;
    private javax.swing.JTextField txt1;
    private javax.swing.JTextField txtcla;
    private javax.swing.JTextField txtclp;
    private javax.swing.JTextField txtcurso;
    private javax.swing.JTextField txtcursoapromocionar;
    private javax.swing.JTextField txtd;
    private javax.swing.JTextField txtdivisionapromocionar;
    private javax.swing.JTextField txtestadoa;
    private javax.swing.JTextField txtestadom;
    private javax.swing.JTextField txtidcp;
    // End of variables declaration//GEN-END:variables

    private void setModel(DefaultTableModel modelo1) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
