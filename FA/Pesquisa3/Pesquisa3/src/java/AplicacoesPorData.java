
import POJODW.DimMedia;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zul.Label;
import org.zkoss.zul.Grid;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Row;
import org.zkoss.zul.Rows;
import org.zkoss.zul.Window;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Samuk
 */
public class AplicacoesPorData extends Window {

    public void onCreate() {
    }

    public void update(Date inicio, Date fim) {
        List ret = AplicacoesPorData(inicio, fim);
        Grid grid = (Grid) getFellow("grid");
        grid.appendChild(getFellow("rows"));

        Rows rowsGrid = grid.getRows();
        if (rowsGrid != null) {
            rowsGrid.getChildren().removeAll(rowsGrid.getChildren());
        }

        for (int i = 0; i < ret.size(); i++) {
            Row row = new Row();
//            Label label = new Label(((DimMedia) ret.get(i)).getDocument());
            Label label = new Label(((QntAplicacoes) ret.get(i)).getDimMedia().getDocument());
            row.appendChild(label);
            Label label1 = new Label((((QntAplicacoes) ret.get(i)).getQnt() / 2) + "");
            row.appendChild(label1);
            grid.getRows().appendChild(row);
        }
    }

    public List AplicacoesPorData(Date inicio, Date fim) {
        ArrayList<DimMedia> retorno = new ArrayList<DimMedia>();
        ArrayList<QntAplicacoes> retorno2 = new ArrayList<QntAplicacoes>();
        Configuration cfg = new Configuration().configure("/hibernateDW.cfg.xml");
        SessionFactory sf = cfg.buildSessionFactory();
        Session s = sf.openSession();
        Query qr = null;

        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();

        c1.setTime(inicio);
        c2.setTime(fim);

        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

        String sql = "SELECT DISTINCT dimMedia.document" + " FROM DimMedia dimMedia, DimData dimData, IteractionHasDimData iteractionHasDimData," + "Iteraction iteraction, IteractionHasDimMedia iteractionHasDimMedia" + " WHERE dimData.datacompleta >= '" + formatter.format(c1.getTime()) + "'" + " AND dimData.datacompleta <= '" + formatter.format(c2.getTime()) + "'" + " AND dimData.idDimData = iteractionHasDimData.id.dimDataIdDimData" + " AND iteractionHasDimData.id.iteractionIdIteraction = iteraction.idIteraction" + " AND iteraction.idIteraction = iteractionHasDimMedia.id.iteractionIdIteraction" + " AND iteractionHasDimMedia.id.dimMediaIdDimMedia = dimMedia.idDimMedia";
        qr = s.createQuery(sql);
        Iterator it = qr.list().iterator();
        while (it.hasNext()) {
            DimMedia dimMedia = new DimMedia();
            dimMedia.setDocument((String) it.next());
            retorno.add(dimMedia);
        }
        for (int i = 0; i < retorno.size(); i++) {
            sql = "SELECT COUNT(dimMedia.document)" + " FROM DimMedia dimMedia, DimData dimData, IteractionHasDimData iteractionHasDimData," + "Iteraction iteraction, IteractionHasDimMedia iteractionHasDimMedia" + " WHERE dimData.datacompleta >= '" + formatter.format(c1.getTime()) + "'" + " AND dimData.datacompleta <= '" + formatter.format(c2.getTime()) + "'" + " AND dimData.idDimData = iteractionHasDimData.id.dimDataIdDimData" + " AND iteractionHasDimData.id.iteractionIdIteraction = iteraction.idIteraction" + " AND iteraction.idIteraction = iteractionHasDimMedia.id.iteractionIdIteraction" + " AND iteractionHasDimMedia.id.dimMediaIdDimMedia = dimMedia.idDimMedia" + " AND dimMedia.document LIKE '" + retorno.get(i).getDocument() + "'";
            qr = s.createQuery(sql);
            QntAplicacoes qntAplicacoes = new QntAplicacoes();
            qntAplicacoes.setDimMedia(retorno.get(i));
            qntAplicacoes.setQnt((Long) qr.list().get(0));
            retorno2.add(qntAplicacoes);
        }
        return retorno2;
    }

    public void redirecionarMenu() {
        Executions.getCurrent().sendRedirect("PesquisasDisponiveis.zul");
    }
}
