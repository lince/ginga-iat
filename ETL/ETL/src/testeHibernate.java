
import POJOTVDI.Device;
import POJOTVDI.Iteraction;
import java.io.File;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Samuk
 */
public class testeHibernate {

//    public static void main(String[] args) {
//        Configuration cfg = new Configuration().configure("/hibernateTVDI.cfg.xml");
//        SessionFactory sf = cfg.buildSessionFactory();
//
////        SessionFactory sf = new AnnotationConfiguration().configure(new File("C:\\Documents and Settings\\Samuk\\Meus documentos\\NetBeansProjects\\ETL\\src\\hibernateTVDI.cfg.xml")).buildSessionFactory();
//        Session s = sf.openSession();
//        String sql = "from Iteraction";
//        Query qr = s.createQuery(sql);
//        for (int i = 0; i < qr.list().size(); i++) {
//            System.out.println(((Iteraction)qr.list().get(i)).getTipo());
//
//        }
//        s.close();
//    }
}
