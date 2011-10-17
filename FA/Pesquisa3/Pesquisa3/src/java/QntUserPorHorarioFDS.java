
import POJODW.DimProgram;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Flashchart;
import org.zkoss.zul.PieModel;
import org.zkoss.zul.SimplePieModel;
import org.zkoss.zul.Window;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Samuk
 */
public class QntUserPorHorarioFDS extends Window {

    private Flashchart mychart;
    private PieModel model;

    public void onCreate() {
        mychart = (Flashchart) getFellow("mychart");
        model = new SimplePieModel();
        mychart.setModel(model);
        model.setValue("Off Line", new Double(totalUsuarios()));
        model.setValue("On Line", new Double(0));
    }

    public void update(Date inicio, Date fim) {
        Long usu = usuariosPorData(inicio, fim);
        Long total = totalUsuarios();
        model.setValue("On Line", new Double(usu));
        model.setValue("Off Line", new Double(total - usu));
    }

    public Long totalUsuarios() {
        Configuration cfg = new Configuration().configure("/hibernateDW.cfg.xml");
        SessionFactory sf = cfg.buildSessionFactory();
        Session s = sf.openSession();
        Query qr = null;
        String sql = "SELECT COUNT(DISTINCT dimDevice.serialNumber)" + " FROM DimDevice dimDevice";
        qr = s.createQuery(sql);
        return (Long) qr.list().get(0);
    }

    public Long usuariosPorData(Date inicio, Date fim) {
        Configuration cfg = new Configuration().configure("/hibernateDW.cfg.xml");
        SessionFactory sf = cfg.buildSessionFactory();
        Session s = sf.openSession();
        Query qr = null;

        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();

        c1.setTime(inicio);
        c2.setTime(fim);

        DateFormat formatter = new SimpleDateFormat("HH:mm:ss");
        String sql = "SELECT COUNT(DISTINCT dimDevice.serialNumber)" +
                " FROM DimData dimData, DimDevice dimDevice, IteractionHasDimDevice iteractionHasDimDevice, IteractionHasDimData iteractionHasDimData, Iteraction iteraction" +
                " WHERE TIME(dimData.datacompleta) >= TIME('" + formatter.format(c1.getTime()) + "')" +
                " AND TIME(dimData.datacompleta) <= TIME('" + formatter.format(c2.getTime()) + "')" +
                " AND iteractionHasDimData.id.dimDataIdDimData = dimData.idDimData" +
                " AND iteractionHasDimData.id.iteractionIdIteraction = iteraction.idIteraction" +
                " AND iteractionHasDimDevice.id.iteractionIdIteraction = iteraction.idIteraction" +
                " AND iteractionHasDimDevice.id.dimDeviceIdDimDevice = dimDevice.idDimDevice" +
                " AND (dimData.diaSemana LIKE 'SABADO' OR dimData.diaSemana LIKE 'DOMINGO')";
        qr = s.createQuery(sql);
        return (Long) qr.list().get(0);
    }

    public void redirecionarMenu() {
        Executions.getCurrent().sendRedirect("PesquisasDisponiveis.zul");
    }
}
