
package paquetes;

import conexion.conectar;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import reportes.reportes;
import java.util.Date;

public class libretas extends javax.swing.JInternalFrame {

ResultSet rs;
conectar cc=new conectar();
Connection cn=cc.conexion(); 
    
    public libretas() {
        initComponents();
        vercurso_division();
        idcl.setVisible(false);
        idcd.setVisible(false);
        c.setVisible(false);
        d.setVisible(false);
        fechaactual();
        idcurso.setVisible(false);
        
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
    
    void idcuro_division(String valor, String valor1){
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
         idcl.setText(rs.getString(1));
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
         idcl.setText(rs.getString(1));
         año.setText(rs.getString(2));
      }
                 } catch (Exception e) {
            }
}
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        combocurso = new javax.swing.JComboBox();
        jButton1 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        idcd = new javax.swing.JTextField();
        c = new javax.swing.JTextField();
        d = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        idcl = new javax.swing.JTextField();
        año = new javax.swing.JLabel();
        idcurso = new javax.swing.JTextField();

        setClosable(true);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel1.setText("Curso/Division");

        combocurso.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Sellecionar" }));
        combocurso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                combocursoActionPerformed(evt);
            }
        });

        jButton1.setText("Imprimir");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("-Reportes Academicos Anual-");
        jLabel3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setText("Ciclo Lectivo");

        año.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 98, Short.MAX_VALUE)
                            .addComponent(jLabel4)
                            .addComponent(c))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 55, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(año, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(combocurso, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(26, 26, 26)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(idcd, javax.swing.GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE)
                                    .addComponent(idcl))
                                .addGap(0, 42, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(d, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(idcurso, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(68, 68, 68)
                                .addComponent(jButton1)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addGap(43, 43, 43)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(idcl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(año, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE))
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(combocurso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(idcd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel1))
                .addGap(65, 65, 65)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(c, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(d, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(idcurso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void combocursoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combocursoActionPerformed
if (combocurso.getSelectedIndex()==0){
            c.setText(null);
            d.setText(null);
            idcd.setText(null);
 } else {
                String item =combocurso.getSelectedItem().toString();
                String [] data=item.split(" ");
                c.setText(data [0] + " " + data[1]);
                d.setText(data [3]);
        }
               idcuro_division(c.getText(), d.getText());  
    }//GEN-LAST:event_combocursoActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        int curso=Integer.parseInt(idcurso.getText());
        
        if(curso <= 3 )
                   try {
               reportes re =new reportes ();
               re.libreta(idcd.getText(),idcl.getText());
           } catch (Exception e) {
               System.out.print(e.getMessage());
           } 
                else 
        try {
               reportes re =new reportes ();
               re.libretaCE(idcd.getText(),idcl.getText());
           } catch (Exception e) {
               System.out.print(e.getMessage());
           } 
        
        
        
    }//GEN-LAST:event_jButton1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel año;
    private javax.swing.JTextField c;
    private javax.swing.JComboBox combocurso;
    private javax.swing.JTextField d;
    private javax.swing.JTextField idcd;
    private javax.swing.JTextField idcl;
    private javax.swing.JTextField idcurso;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    // End of variables declaration//GEN-END:variables
}
