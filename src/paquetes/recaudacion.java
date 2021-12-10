
package paquetes;

import conexion.conectar;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import static paquetes.reportmatriculados.Table1;
import reportes.reportes;
import tesis.render;


public class recaudacion extends javax.swing.JInternalFrame {

    conectar cc = new conectar();
    Connection cn = cc.conexion();
    ResultSet rs;
    DefaultTableModel modelo; 
    
    
    public recaudacion() {
        initComponents();
        fechaactual();
        recaudacion(idcla.getText());
        contarfilas();
        sumarPreciosColumnas2(Table1, 3);
        jTextField1.setEnabled(false);
        jTextField2.setEnabled(false);
        
   
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
            rs = pps.executeQuery();
            while (rs.next()) {
                idcla.setText(rs.getString(1));
                txtcla.setText(rs.getString(2));
                
                txtcla.setEditable(false);
                txtcla.setEnabled(false);
                idcla.setVisible(false);    
            }
        } catch (Exception e) {
            e.printStackTrace();
        } }

void clanterior (){
        try {
            PreparedStatement pps=cn.prepareStatement("SELECT idciclolectivo, año  FROM ciclolectivo where año=year(now())-1");
            rs = pps.executeQuery();
            while (rs.next()) {
                idcla.setText(rs.getString(1));
                txtcla.setText(rs.getString(2));
                
                txtcla.setEditable(false);
                txtcla.setEnabled(false);
                idcla.setVisible(false);    
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    void recaudacion (String valor){
      
//     Table1.setDefaultRenderer(Object.class, new render());
//     
//      JButton btn = new JButton("Ver");
      
 
   String [] titulos= {"Alumno","Curso/División","Detalle","Monto Matricula $"};
   modelo=new  DefaultTableModel(null,titulos)
   {
            @Override
    public boolean isCellEditable(int row, int column){
              return false; 
                }
   };
           
   
    Object datos []= new Object[4];
    String sql="select a.nombre, a.apellido, m.monto, m.descripcion, c.descripcion, cd.division "
            + "from alumno a, montomatricula m,  detalle_matricula dm, curso c, curso_division cd "
            + "where dm.idalumno=a.idalumno and dm.idmontomatricula=m.idmontomatricula and c.idcurso=cd.idcurso and cd.idcurso_division=dm.idcurso_division "
            + "and idciclolectivo='"+valor+"' order by  c.descripcion, cd.division";
   try {
            Statement st = cn.createStatement();
             rs = st.executeQuery(sql);
            while(rs.next())
            {
                datos[0]=rs.getString("a.apellido") +" "+ rs.getString("a.nombre");
                datos[1]=rs.getString("c.descripcion") +" "+ rs.getString("cd.division");
                datos[2]=rs.getString("m.descripcion");
                datos[3]=rs.getString("m.monto");
               
                
                modelo.addRow(datos);
                
            }
            Table1.setModel(modelo);
            Table1.setRowHeight(20);
//            Table1.getColumnModel().getColumn(0).setMaxWidth(0);
//            Table1.getColumnModel().getColumn(0).setMinWidth(0);
//            Table1.getColumnModel().getColumn(0).setPreferredWidth(0);
        } 
   
   catch (SQLException ex) {
            System.out.println(ex);
        }
 
 }

    public int sumarPreciosColumnas2(JTable Table1, int columna) {
        int sumatoria1 = 0;
        for (int i = 0; i < Table1.getRowCount(); i++) {
            int sumatoria = Integer.parseInt(String.valueOf(Table1.getValueAt(i, columna)));
            sumatoria1 += sumatoria;
        jTextField2.setText("$"+Integer.toString(sumatoria1));
        }
       return sumatoria1;
        
}
    
    void contarfilas(){
        
        int fila = Table1.getRowCount();
        System.out.println(fila);
       jTextField1.setText(Integer.toString(fila));
    }
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        txtcla = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        idcla = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        Table1 = new javax.swing.JTable();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        setClosable(true);
        setTitle("RECAUDACIÓN POR MATRICULACIÓN");

        jButton1.setText("Atras");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Imprimir");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        txtcla.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtcla.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("Ciclo Lectivo ");

        Table1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        Table1.getTableHeader().setResizingAllowed(false);
        Table1.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(Table1);

        jTextField2.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setText("Total de Matriculados");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setText("Total Recaudación por Cobro de Matricula");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtcla, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(24, 24, 24)
                        .addComponent(idcla, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton2)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(196, 196, 196)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField2))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 671, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(27, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtcla, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(idcla, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
         try {
               reportes re =new reportes ();
               re.recaudacion(idcla.getText(), txtcla.getText());
           } catch (Exception e) {
               System.out.print(e.getMessage());
           }
    }//GEN-LAST:event_jButton2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable Table1;
    private javax.swing.JTextField idcla;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField txtcla;
    // End of variables declaration//GEN-END:variables

    private String Date(String m) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
