
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
public class QntUserPorDataCanal extends Window {

    private Flashchart mychart;
    private PieModel model;

    public void onCreate() {
        mychart = (Flashchart) getFellow("mychart");
        model = new SimplePieModel();
        mychart.setModel(model);
        model.setValue("Off Line/outro canal", new Double(totalUsuarios()));
        model.setValue("On Line", new Double(0));
    }

    public void update(Date inicio, Date fim) {
        Long usu = usuariosPorData(inicio, fim);
        Long total = totalUsuarios();
        model.setValue("On Line", new Double(usu));
        model.setValue("Off Line/outro canal", new Double(total - usu));
    }

    public void update(Date inicio, Date fim, String canal) {
        Long usu = usuariosPorDataCanal(inicio, fim, canal);
        Long total = totalUsuarios();
        model.setValue("On Line", new Double(usu));
        model.setValue("Off Line/outro canal", new Double(total - usu));
    }

    public Long totalUsuarios() {
        Configuration cfg = new Configuration().configure("/hibernateDW.cfg.xml");
        SessionFactory sf = cfg.buildSessionFactory();
        Session s = sf.openSession();
        Query qr = null;
        String sql = "SELECT COUNT(DISTINCT dimDevice.serialNumber)"
                + " FROM DimDevice dimDevice";
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

        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String sql = "SELECT COUNT(DISTINCT dimDevice.serialNumber)"
                + " FROM DimData dimData, DimDevice dimDevice, IteractionHasDimDevice iteractionHasDimDevice, IteractionHasDimData iteractionHasDimData, Iteraction iteraction"
                + " WHERE dimData.datacompleta >= '" + formatter.format(c1.getTime()) + "'"
                + " AND dimData.datacompleta <= '" + formatter.format(c2.getTime()) + "'"
                + " AND iteractionHasDimData.id.dimDataIdDimData = dimData.idDimData"
                + " AND iteractionHasDimData.id.iteractionIdIteraction = iteraction.idIteraction"
                + " AND iteractionHasDimDevice.id.iteractionIdIteraction = iteraction.idIteraction"
                + " AND iteractionHasDimDevice.id.dimDeviceIdDimDevice = dimDevice.idDimDevice";
        qr = s.createQuery(sql);
        return (Long) qr.list().get(0);
    }

    public Long usuariosPorDataCanal(Date inicio, Date fim, String canal) {
        Configuration cfg = new Configuration().configure("/hibernateDW.cfg.xml");
        SessionFactory sf = cfg.buildSessionFactory();
        Session s = sf.openSession();
        Query qr = null;

        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();

        c1.setTime(inicio);
        c2.setTime(fim);

        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String sql = "SELECT COUNT(DISTINCT dimDevice.serialNumber)"
                + " FROM DimData dimData, DimDevice dimDevice, IteractionHasDimDevice iteractionHasDimDevice,"
                + " IteractionHasDimData iteractionHasDimData, Iteraction iteraction,"
                + " IteractionHasDimProgram iteractionHasDimProgram, DimProgram dimProgram"
                + " WHERE dimData.datacompleta >= '" + formatter.format(c1.getTime()) + "'"
                + " AND dimData.datacompleta <= '" + formatter.format(c2.getTime()) + "'"
                + " AND iteractionHasDimData.id.dimDataIdDimData = dimData.idDimData"
                + " AND iteractionHasDimData.id.iteractionIdIteraction = iteraction.idIteraction"
                + " AND iteractionHasDimDevice.id.iteractionIdIteraction = iteraction.idIteraction"
                + " AND iteractionHasDimDevice.id.dimDeviceIdDimDevice = dimDevice.idDimDevice"
                + " AND iteractionHasDimProgram.id.iteractionIdIteraction = iteraction.idIteraction"
                + " AND iteractionHasDimProgram.id.dimProgramIdDimProgram = dimProgram.idDimProgram"
                + " AND dimProgram.channelName LIKE '" + canal + "'";
        qr = s.createQuery(sql);
        return (Long) qr.list().get(0);
    }

    public List todosCanais() {
        ArrayList<DimProgram> retorno = new ArrayList<DimProgram>();
        Configuration cfg = new Configuration().configure("/hibernateDW.cfg.xml");
        SessionFactory sf = cfg.buildSessionFactory();
        Session s = sf.openSession();
        Query qr = null;
        String sql = "SELECT DISTINCT dimProgram.channelName"
                + " FROM DimProgram dimProgram" +
                " ORDER BY dimProgram.channelName";
        qr = s.createQuery(sql);
        Iterator it = qr.list().iterator();
        while (it.hasNext()) {
            DimProgram dimProgram = new DimProgram();
            dimProgram.setChannelName((String) it.next());
            retorno.add(dimProgram);
        }
        return retorno;
    }

    public void redirecionarMenu() {
        Executions.getCurrent().sendRedirect("PesquisasDisponiveis.zul");
    }
}
