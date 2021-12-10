
package reportes;

import java.awt.Frame;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;



public class reportes {
    private int Dispose_on_close;

 public void pago (String cod, String cd2) throws JRException, SQLException {
      JasperReport report =null;
      Connection conectar;
      Map parametro= new HashMap();
        parametro.clear();
        parametro.put("idAlumno", cod);
        parametro.put("ciclolectivo", cd2);
      conectar = (com.mysql.jdbc.Connection) DriverManager.getConnection("jdbc:mysql://localhost/sm_school_manager","root","root");
        
        report=(JasperReport) JRLoader.loadObjectFromFile("D:\\AAAAAA\\Doc Pablo\\Proyecto\\nuevo\\src\\reportes\\pago.jasper");
        
        JasperPrint in=JasperFillManager.fillReport(report,parametro, conectar);
        JasperViewer ver =new JasperViewer(in,false);
        ver.setDefaultCloseOperation(Dispose_on_close);
        ver.setExtendedState(Frame.MAXIMIZED_BOTH);
        ver.setTitle("Reporte de Pago");
        ver.setVisible(true);
 }
 
  public void recaudacion (String cod, String cd2) throws JRException, SQLException {
      JasperReport report =null;
      Connection conectar;
      Map parametro= new HashMap();
        parametro.clear();
        parametro.put("idcl", cod);
        parametro.put("año", cd2);
      conectar = (com.mysql.jdbc.Connection) DriverManager.getConnection("jdbc:mysql://localhost/sm_school_manager","root","root");
        
        report=(JasperReport) JRLoader.loadObjectFromFile("D:\\AAAAAA\\Doc Pablo\\Proyecto\\nuevo\\src\\reportes\\recaudacion.jasper");
        
        JasperPrint in=JasperFillManager.fillReport(report,parametro, conectar);
        JasperViewer ver =new JasperViewer(in,false);
        ver.setDefaultCloseOperation(Dispose_on_close);
        ver.setExtendedState(Frame.MAXIMIZED_BOTH);
        ver.setTitle("Recaudación por Matriculados");
        ver.setVisible(true);
 }
 
 
 public void reportealumno (String cod, String cod2)throws JRException, SQLException {
        JasperReport report =null;
        Connection conectar;
        Map parametro= new HashMap();
        parametro.clear();
        parametro.put("idcl", cod);
        parametro.put("idcd", cod2);
        
     
        conectar = (com.mysql.jdbc.Connection) DriverManager.getConnection("jdbc:mysql://localhost/sm_school_manager","root","root");
        
        report=(JasperReport) JRLoader.loadObjectFromFile("D:\\AAAAAA\\Doc Pablo\\Proyecto\\nuevo\\src\\reportes\\listaalumnos.jasper");
        
        JasperPrint in=JasperFillManager.fillReport(report,parametro, conectar);
        JasperViewer ver =new JasperViewer(in,false);
        ver.setDefaultCloseOperation(Dispose_on_close);
        ver.setExtendedState(Frame.MAXIMIZED_BOTH);
        ver.setTitle("Alumnos por Curso/Division");
        ver.setVisible(true);
        
    }
 
 public void verasignaturas (String cod)throws JRException, SQLException {
        JasperReport report =null;
        Connection conectar;
        Map parametro= new HashMap();
        parametro.clear();
        parametro.put("idcurso", cod);
        
        
     
        conectar = (com.mysql.jdbc.Connection) DriverManager.getConnection("jdbc:mysql://localhost/sm_school_manager","root","root");
        
        report=(JasperReport) JRLoader.loadObjectFromFile("D:\\AAAAAA\\Doc Pablo\\Proyecto\\nuevo\\src\\reportes\\verasignaturas.jasper");
        
        JasperPrint in=JasperFillManager.fillReport(report,parametro, conectar);
        JasperViewer ver =new JasperViewer(in,false);
        ver.setDefaultCloseOperation(Dispose_on_close);
        ver.setExtendedState(Frame.MAXIMIZED_BOTH);
        ver.setTitle(" LISTA DE ASIGNATURAS");
        ver.setVisible(true);
        
    }
 
 public void libreta (String cod, String cod2)throws JRException, SQLException {
        JasperReport report =null;
        Connection conectar;
        Map parametro= new HashMap();
        parametro.clear();
        parametro.put("cursodivision", cod);
        parametro.put("ciclolectivo", cod2);
     
        conectar = (com.mysql.jdbc.Connection) DriverManager.getConnection("jdbc:mysql://localhost/sm_school_manager","root","root");
        
        report=(JasperReport) JRLoader.loadObjectFromFile("D:\\AAAAAA\\Doc Pablo\\Proyecto\\nuevo\\src\\reportes\\libreta2.jasper");
        
        JasperPrint in=JasperFillManager.fillReport(report,parametro, conectar);
        JasperViewer ver =new JasperViewer(in,false);
        ver.setDefaultCloseOperation(Dispose_on_close);
        ver.setExtendedState(Frame.MAXIMIZED_BOTH);
        ver.setTitle("Libreta del Estudiante");
        ver.setVisible(true);
        
    }
 
 public void libretaCE (String cod, String cod2)throws JRException, SQLException {
        JasperReport report =null;
        Connection conectar;
        Map parametro= new HashMap();
        parametro.clear();
        parametro.put("cursodivision", cod);
        parametro.put("ciclolectivo", cod2);
     
        conectar = (com.mysql.jdbc.Connection) DriverManager.getConnection("jdbc:mysql://localhost/sm_school_manager","root","root");
        
        report=(JasperReport) JRLoader.loadObjectFromFile("D:\\AAAAAA\\Doc Pablo\\Proyecto\\nuevo\\src\\reportes\\libretaCE.jasper");
        
        JasperPrint in=JasperFillManager.fillReport(report,parametro, conectar);
        JasperViewer ver =new JasperViewer(in,false);
        ver.setDefaultCloseOperation(Dispose_on_close);
        ver.setExtendedState(Frame.MAXIMIZED_BOTH);
        ver.setTitle("Libreta del Estudiante");
        ver.setVisible(true);
        
    }
  
  public void libretaPaseCE (String cod, String cod2, String cod3)throws JRException, SQLException {
        JasperReport report =null;
        Connection conectar;
        Map parametro= new HashMap();
        parametro.clear();
        parametro.put("cursodivision", cod);
        parametro.put("ciclolectivo", cod2);
        parametro.put("idalumno", cod3);
     
        conectar = (com.mysql.jdbc.Connection) DriverManager.getConnection("jdbc:mysql://localhost/sm_school_manager","root","root");
        
        report=(JasperReport) JRLoader.loadObjectFromFile("D:\\AAAAAA\\Doc Pablo\\Proyecto\\nuevo\\src\\reportes\\libretaPaseCE.jasper");
        
        JasperPrint in=JasperFillManager.fillReport(report,parametro, conectar);
        JasperViewer ver =new JasperViewer(in,false);
        ver.setDefaultCloseOperation(Dispose_on_close);
        ver.setExtendedState(Frame.MAXIMIZED_BOTH);
        ver.setTitle("Libreta del Estudiante");
        ver.setVisible(true);
        
    }
  
  public void libretaPaseCBU (String cod, String cod2, String cod3)throws JRException, SQLException {
        JasperReport report =null;
        Connection conectar;
        Map parametro= new HashMap();
        parametro.clear();
        parametro.put("cursodivision", cod);
        parametro.put("ciclolectivo", cod2);
        parametro.put("idalumno", cod3);
     
        conectar = (com.mysql.jdbc.Connection) DriverManager.getConnection("jdbc:mysql://localhost/sm_school_manager","root","root");
        
        report=(JasperReport) JRLoader.loadObjectFromFile("D:\\AAAAAA\\Doc Pablo\\Proyecto\\nuevo\\src\\reportes\\libretaPaseCBU.jasper");
        
        JasperPrint in=JasperFillManager.fillReport(report,parametro, conectar);
        JasperViewer ver =new JasperViewer(in,false);
        ver.setDefaultCloseOperation(Dispose_on_close);
        ver.setExtendedState(Frame.MAXIMIZED_BOTH);
        ver.setTitle("Libreta del Estudiante");
        ver.setVisible(true);
        
    }
 
public void libretaparcial (String cod, String cod2, String cod3)throws JRException, SQLException {
        JasperReport report =null;
        Connection conectar;
        Map parametro= new HashMap();
        parametro.clear();
        parametro.put("cursodivision", cod);
        parametro.put("ciclolectivo", cod2);
        parametro.put("idtrimestre", cod3);
     
        conectar = (com.mysql.jdbc.Connection) DriverManager.getConnection("jdbc:mysql://localhost/sm_school_manager","root","root");
        
        report=(JasperReport) JRLoader.loadObjectFromFile("D:\\AAAAAA\\Doc Pablo\\Proyecto\\nuevo\\src\\reportes\\libretaparcial.jasper");
        
        JasperPrint in=JasperFillManager.fillReport(report,parametro, conectar);
        JasperViewer ver =new JasperViewer(in,false);
        ver.setDefaultCloseOperation(Dispose_on_close);
        ver.setExtendedState(Frame.MAXIMIZED_BOTH);
        ver.setTitle("Libreta Parcial del Estudiante");
        ver.setVisible(true);
        
    }

public void pase (String cod, String cod2)throws JRException, SQLException {
        JasperReport report =null;
        Connection conectar;
        Map parametro= new HashMap();
        parametro.clear();
        parametro.put("idalumno", cod);
        parametro.put("idciclolectivo", cod2);
     
        conectar = (com.mysql.jdbc.Connection) DriverManager.getConnection("jdbc:mysql://localhost/sm_school_manager","root","root");
        
        report=(JasperReport) JRLoader.loadObjectFromFile("D:\\AAAAAA\\Doc Pablo\\Proyecto\\nuevo\\src\\reportes\\pase.jasper");
        
        JasperPrint in=JasperFillManager.fillReport(report,parametro, conectar);
        JasperViewer ver =new JasperViewer(in,false);
        ver.setDefaultCloseOperation(Dispose_on_close);
        ver.setExtendedState(Frame.MAXIMIZED_BOTH);
        ver.setTitle("Solicitud Pase -Egreso-");
        ver.setVisible(true);
        
    }
    
 public void dpase (String cod)throws JRException, SQLException {
        JasperReport report =null;
        Connection conectar;
        Map parametro= new HashMap();
        parametro.clear();
        parametro.put("idpase", cod);
        
     
        conectar = (com.mysql.jdbc.Connection) DriverManager.getConnection("jdbc:mysql://localhost/sm_school_manager","root","root");
        
        report=(JasperReport) JRLoader.loadObjectFromFile("D:\\AAAAAA\\Doc Pablo\\Proyecto\\nuevo\\src\\reportes\\dpase.jasper");
        
        JasperPrint in=JasperFillManager.fillReport(report,parametro, conectar);
        JasperViewer ver =new JasperViewer(in,false);
        ver.setDefaultCloseOperation(Dispose_on_close);
        ver.setExtendedState(Frame.MAXIMIZED_BOTH);
        ver.setTitle("Detalle Pase -Egreso-");
        ver.setVisible(true);
        
    }
 
 public void listadocente (String cod, String cod2, String cod3)throws JRException, SQLException {
        JasperReport report =null;
        Connection conectar;
        Map parametro= new HashMap();
        parametro.clear();
        parametro.put("idcl", cod);
        parametro.put("idcd", cod2);
        parametro.put("asignatura", cod3);
     
        conectar = (com.mysql.jdbc.Connection) DriverManager.getConnection("jdbc:mysql://localhost/sm_school_manager","root","root");
        
        report=(JasperReport) JRLoader.loadObjectFromFile("D:\\AAAAAA\\Doc Pablo\\Proyecto\\nuevo\\src\\reportes\\listaadocente.jasper");
        
        JasperPrint in=JasperFillManager.fillReport(report,parametro, conectar);
        JasperViewer ver =new JasperViewer(in,false);
        ver.setDefaultCloseOperation(Dispose_on_close);
        ver.setExtendedState(Frame.MAXIMIZED_BOTH);
        ver.setTitle("Listado Alumnos");
        ver.setVisible(true);
  }
 
 public void docente ()throws JRException, SQLException {
        JasperReport report =null;
        Connection conectar;
        Map parametro= new HashMap();
        parametro.clear();

        conectar = (com.mysql.jdbc.Connection) DriverManager.getConnection("jdbc:mysql://localhost/sm_school_manager","root","root");
        
        report=(JasperReport) JRLoader.loadObjectFromFile("D:\\AAAAAA\\Doc Pablo\\Proyecto\\nuevo\\src\\reportes\\docentes.jasper");
        
        JasperPrint in=JasperFillManager.fillReport(report,parametro, conectar);
        JasperViewer ver =new JasperViewer(in,false);
        ver.setDefaultCloseOperation(Dispose_on_close);
        ver.setExtendedState(Frame.MAXIMIZED_BOTH);
        ver.setTitle("Listado Docente");
        ver.setVisible(true);
  }

 public void detalledocente (String cod, String cod1, String cod2, String cod3, String cod4, String cod5)throws JRException, SQLException {
        JasperReport report =null;
        Connection conectar;
        Map parametro= new HashMap();
        parametro.clear();
        parametro.put("idusuario", cod);
        parametro.put("idcl", cod1);
        parametro.put("pnombre", cod2);
        parametro.put("papellido", cod3);
        parametro.put("pdni", cod4);
        parametro.put("ptel", cod5);
        conectar = (com.mysql.jdbc.Connection) DriverManager.getConnection("jdbc:mysql://localhost/sm_school_manager","root","root");
        
        report=(JasperReport) JRLoader.loadObjectFromFile("D:\\AAAAAA\\Doc Pablo\\Proyecto\\nuevo\\src\\reportes\\detalledocente.jasper");
        
        JasperPrint in=JasperFillManager.fillReport(report,parametro, conectar);
        JasperViewer ver =new JasperViewer(in,false);
        ver.setDefaultCloseOperation(Dispose_on_close);
        ver.setExtendedState(Frame.MAXIMIZED_BOTH);
        ver.setTitle("Detalle Docente");
        ver.setVisible(true);
  }

 public void alumnofamiliar (String cod, String cod2)throws JRException, SQLException {
        JasperReport report =null;
        Connection conectar;
        Map parametro= new HashMap();
        parametro.clear();
        parametro.put("idcl", cod);
        parametro.put("idcd", cod2);
        
     
        conectar = (com.mysql.jdbc.Connection) DriverManager.getConnection("jdbc:mysql://localhost/sm_school_manager","root","root");
        
        report=(JasperReport) JRLoader.loadObjectFromFile("D:\\AAAAAA\\Doc Pablo\\Proyecto\\nuevo\\src\\reportes\\alumnosfamiliar.jasper");
        
        JasperPrint in=JasperFillManager.fillReport(report,parametro, conectar);
        JasperViewer ver =new JasperViewer(in,false);
        ver.setDefaultCloseOperation(Dispose_on_close);
        ver.setExtendedState(Frame.MAXIMIZED_BOTH);
        ver.setTitle("Alumnos/Familiar");
        ver.setVisible(true);
  }

  public void usuarios ()throws JRException, SQLException {
        JasperReport report =null;
        Connection conectar;
        Map parametro= new HashMap();
        parametro.clear();
//        parametro.put("idcl", cod);
//        parametro.put("idcd", cod2);
        
     
        conectar = (com.mysql.jdbc.Connection) DriverManager.getConnection("jdbc:mysql://localhost/sm_school_manager","root","root");
        
        report=(JasperReport) JRLoader.loadObjectFromFile("D:\\AAAAAA\\Doc Pablo\\Proyecto\\nuevo\\src\\reportes\\usuarios.jasper");
        
        JasperPrint in=JasperFillManager.fillReport(report,parametro, conectar);
        JasperViewer ver =new JasperViewer(in,false);
        ver.setDefaultCloseOperation(Dispose_on_close);
        ver.setExtendedState(Frame.MAXIMIZED_BOTH);
        ver.setTitle("Listado de Usuarios");
        ver.setVisible(true);
  }

  public void detalleusuario (String cod)throws JRException, SQLException {
        JasperReport report =null;
        Connection conectar;
        Map parametro= new HashMap();
        parametro.clear();
        parametro.put("idusuario", cod);
//        parametro.put("idcd", cod2);
        
     
        conectar = (com.mysql.jdbc.Connection) DriverManager.getConnection("jdbc:mysql://localhost/sm_school_manager","root","root");
        
        report=(JasperReport) JRLoader.loadObjectFromFile("D:\\AAAAAA\\Doc Pablo\\Proyecto\\nuevo\\src\\reportes\\detalleusuario.jasper");
        
        JasperPrint in=JasperFillManager.fillReport(report,parametro, conectar);
        JasperViewer ver =new JasperViewer(in,false);
        ver.setDefaultCloseOperation(Dispose_on_close);
        ver.setExtendedState(Frame.MAXIMIZED_BOTH);
        ver.setTitle("Detalle de Usuario");
        ver.setVisible(true);
  }
   
  public void alumnofamiliar_detalle (String cod, String cod2, String cod3)throws JRException, SQLException {
        JasperReport report =null;
        Connection conectar;
        Map parametro= new HashMap();
        parametro.clear();
        parametro.put("idcl", cod);
        parametro.put("idcd", cod2);
        parametro.put("idalumno", cod3);
     
        conectar = (com.mysql.jdbc.Connection) DriverManager.getConnection("jdbc:mysql://localhost/sm_school_manager","root","root");
        
        report=(JasperReport) JRLoader.loadObjectFromFile("D:\\AAAAAA\\Doc Pablo\\Proyecto\\nuevo\\src\\reportes\\alumnosfamiliar_detalle.jasper");
        
        JasperPrint in=JasperFillManager.fillReport(report,parametro, conectar);
        JasperViewer ver =new JasperViewer(in,false);
        ver.setDefaultCloseOperation(Dispose_on_close);
        ver.setExtendedState(Frame.MAXIMIZED_BOTH);
        ver.setTitle("Alumnos/Familiar");
        ver.setVisible(true);
  }

  public void registrodetema (String cod, String cod2)throws JRException, SQLException {
        JasperReport report =null;
        Connection conectar;
        Map parametro= new HashMap();
        parametro.clear();
        parametro.put("idcursodivision", cod);
        parametro.put("idclectivo", cod2);
        
     
        conectar = (com.mysql.jdbc.Connection) DriverManager.getConnection("jdbc:mysql://localhost/sm_school_manager","root","root");
        
        report=(JasperReport) JRLoader.loadObjectFromFile("D:\\AAAAAA\\Doc Pablo\\Proyecto\\nuevo\\src\\reportes\\librodetema.jasper");
        
        JasperPrint in=JasperFillManager.fillReport(report,parametro, conectar);
        JasperViewer ver =new JasperViewer(in,false);
        ver.setDefaultCloseOperation(Dispose_on_close);
        ver.setExtendedState(Frame.MAXIMIZED_BOTH);
        ver.setTitle("Libro de Tema");
        ver.setVisible(true);
  }

}
