/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package teste;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author Administrador
 */
public class teste {

    public static void main(String[] args) {
        Configuration cfg = new Configuration().configure("/hibernateDW.cfg.xml");
        SessionFactory sf = cfg.buildSessionFactory();
        Session s = sf.openSession();
        Query qr = null;
        String sql = "SELECT COUNT(DISTINCT dimDevice.serialNumber) AS aff" +
                " FROM DimData dimData, DimDevice dimDevice, IteractionHasDimDevice iteractionHasDimDevice," +
                " IteractionHasDimData iteractionHasDimData, Iteraction iteraction" +
                " WHERE dimData.datacompleta >= '2010-06-15 01:01:01'" +
                " AND dimData.datacompleta <= '2010-06-19 01:01:01'" +
                " AND iteractionHasDimData.id.dimDataIdDimData = dimData.idDimData" +
                " AND iteractionHasDimData.id.iteractionIdIteraction = iteraction.idIteraction" +
                " AND iteractionHasDimDevice.id.iteractionIdIteraction = iteraction.idIteraction" +
                " AND iteractionHasDimDevice.id.dimDeviceIdDimDevice = dimDevice.idDimDevice" +
                " AND (dimData.diaSemana LIKE 'SABADO' OR dimData.diaSemana LIKE 'DOMINGO')";
        qr = s.createQuery(sql);
        System.out.println(qr.list().get(0));
    }
}
