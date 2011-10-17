
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zul.Window;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Usuario
 */
public class ExportarDados extends Window {

    private boolean b1 = false, b2 = false, b3 = false, b4 = false, b5 = false, b6 = false, b7 = false, b8 = false, b9 = false,
            b10 = false, b11 = false, b12 = false, b13 = false, b14 = false, b15 = false, b16 = false, b17 = false, b18 = false,
            b19 = false, b20 = false, b21 = false, b22 = false, b23 = false, b24 = false, b25 = false, b26 = false, b27 = false,
            b28 = false;
    private String sql;
    private Iterator it;

    public void doChecked(String check) {
        if (check.equals("l1")) {
            if (b1) {
                b1 = false;
            } else {
                b1 = true;
            }
        } else if (check.equals("l2")) {
            if (b2) {
                b2 = false;
            } else {
                b2 = true;
            }
        } else if (check.equals("l3")) {
            if (b3) {
                b3 = false;
            } else {
                b3 = true;
            }
        } else if (check.equals("l4")) {
            if (b4) {
                b4 = false;
            } else {
                b4 = true;
            }
        } else if (check.equals("l5")) {
            if (b5) {
                b5 = false;
            } else {
                b5 = true;
            }
        } else if (check.equals("l6")) {
            if (b6) {
                b6 = false;
            } else {
                b6 = true;
            }
        } else if (check.equals("l7")) {
            if (b7) {
                b7 = false;
            } else {
                b7 = true;
            }
        } else if (check.equals("l8")) {
            if (b8) {
                b8 = false;
            } else {
                b8 = true;
            }
        } else if (check.equals("l9")) {
            if (b9) {
                b9 = false;
            } else {
                b9 = true;
            }
        } else if (check.equals("l10")) {
            if (b10) {
                b10 = false;
            } else {
                b10 = true;
            }
        } else if (check.equals("l11")) {
            if (b11) {
                b11 = false;
            } else {
                b11 = true;
            }
        } else if (check.equals("l12")) {
            if (b12) {
                b12 = false;
            } else {
                b12 = true;
            }
        } else if (check.equals("l13")) {
            if (b13) {
                b13 = false;
            } else {
                b13 = true;
            }
        } else if (check.equals("l14")) {
            if (b14) {
                b14 = false;
            } else {
                b14 = true;
            }
        } else if (check.equals("l15")) {
            if (b15) {
                b15 = false;
            } else {
                b15 = true;
            }
        } else if (check.equals("l16")) {
            if (b16) {
                b16 = false;
            } else {
                b16 = true;
            }
        } else if (check.equals("l17")) {
            if (b17) {
                b17 = false;
            } else {
                b17 = true;
            }
        } else if (check.equals("l18")) {
            if (b18) {
                b18 = false;
            } else {
                b18 = true;
            }
        } else if (check.equals("l19")) {
            if (b19) {
                b19 = false;
            } else {
                b19 = true;
            }
        } else if (check.equals("l20")) {
            if (b20) {
                b20 = false;
            } else {
                b20 = true;
            }
        } else if (check.equals("l21")) {
            if (b21) {
                b21 = false;
            } else {
                b21 = true;
            }
        } else if (check.equals("l22")) {
            if (b22) {
                b22 = false;
            } else {
                b22 = true;
            }
        } else if (check.equals("l23")) {
            if (b23) {
                b23 = false;
            } else {
                b23 = true;
            }
        } else if (check.equals("l24")) {
            if (b24) {
                b24 = false;
            } else {
                b24 = true;
            }
        } else if (check.equals("l25")) {
            if (b25) {
                b25 = false;
            } else {
                b25 = true;
            }
        } else if (check.equals("l26")) {
            if (b26) {
                b26 = false;
            } else {
                b26 = true;
            }
        } else if (check.equals("l27")) {
            if (b27) {
                b27 = false;
            } else {
                b27 = true;
            }
        } else if (check.equals("l28")) {
            if (b28) {
                b28 = false;
            } else {
                b28 = true;
            }
        }
    }

    public File exportar() {
        File f = new File("DW.xml");
        try {
            f.createNewFile();
            FileWriter writer = new FileWriter(f);
            PrintWriter saida = new PrintWriter(writer);

            saida.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
            saida.println("<FA>");

            Configuration cfg = new Configuration().configure("/hibernateDW.cfg.xml");
            SessionFactory sf = cfg.buildSessionFactory();
            Session s = sf.openSession();
            Query qr = null;

            if (b1) {
                sql = "SELECT dimData.idDimData, dimData.dia, dimData.mes, dimData.ano, dimData.diaSemana," + " dimData.semana, dimData.quinzena, dimData.trimestre, dimData.bimestre, dimData.semestre," + " dimData.feriasEscolares, dimData.feriadoNacional, dimData.datacompleta" + " FROM DimData dimData";
                qr = s.createQuery(sql);
                it = qr.list().iterator();
                while (it.hasNext()) {
                    Object[] rows = (Object[]) it.next();
                    saida.println("\t<DimData>");
                    saida.println("\t\t<IdDimData>" + rows[0] + "</IdDimData>");
                    saida.println("\t\t<Dia> " + rows[1] + " </Dia>");
                    saida.println("\t\t<Mes>" + rows[2] + "</Mes>");
                    saida.println("\t\t<Ano>" + rows[3] + "</Ano>");
                    saida.println("\t\t<DiaSemana>" + rows[4] + "</DiaSemana>");
                    saida.println("\t\t<Semana>" + rows[5] + " </Semana>");
                    saida.println("\t\t<Quinzena>" + rows[6] + "</Quinzena>");
                    saida.println("\t\t<Trimestre>" + rows[7] + "</Trimestre>");
                    saida.println("\t\t<Bimestre>" + rows[8] + "</Bimestre>");
                    saida.println("\t\t<Semestre>" + rows[9] + "</Semestre>");
                    saida.println("\t</DimData>\r\n");
                }
            }
            if (b2) {
                sql = "SELECT dimDevice.idDimDevice, dimDevice.serialNumber, dimDevice.type, dimDevice.profile" + " FROM DimDevice dimDevice";
                qr = s.createQuery(sql);
                it = qr.list().iterator();
                while (it.hasNext()) {
                    Object[] rows = (Object[]) it.next();
                    saida.println("\t<DimDevice>");
                    saida.println("\t\t<idDimDevice>" + rows[0] + "</idDimDevice>");
                    saida.println("\t\t<serialNumber>" + rows[1] + "</serialNumber>");
                    saida.println("\t\t<type>" + rows[2] + "</type>");
                    saida.println("\t\t<profile>" + rows[3] + "</profile>");
                    saida.println("\t</DimDevice>\r\n");
                }
            }
            if (b3) {
                sql = "SELECT dimLocation.idDimLocation, dimLocation.zip, dimLocation.longitude, dimLocation.latitude," + " dimLocation.city, dimLocation.state, dimLocation.uf" + " FROM DimLocation dimLocation";
                qr = s.createQuery(sql);
                it = qr.list().iterator();
                while (it.hasNext()) {
                    Object[] rows = (Object[]) it.next();
                    saida.println("\t<DimLocation>");
                    saida.println("\t\t<idDimLocation>" + rows[0] + "</idDimLocation>");
                    saida.println("\t\t<zip>" + rows[1] + "</zip>");
                    saida.println("\t\t<longitude>" + rows[2] + "</longitude>");
                    saida.println("\t\t<latitude>" + rows[3] + "</latitude>");
                    saida.println("\t\t<city>" + rows[4] + "</city>");
                    saida.println("\t\t<state>" + rows[5] + "</state>");
                    saida.println("\t\t<uf>" + rows[6] + "</uf>");
                    saida.println("\t</DimLocation>\r\n");
                }
            }
            if (b4) {
                sql = "SELECT dimMedia.idDimMedia, dimMedia.document, dimMedia.media, dimMedia.status, dimMedia.time" +
                        "  FROM DimMedia dimMedia";
                qr = s.createQuery(sql);
                it = qr.list().iterator();
                while (it.hasNext()) {
                    Object[] rows = (Object[]) it.next();
                    saida.println("\t<DimMedia>");
                    saida.println("\t\t<idDimMedia>" + rows[0] + "</idDimMedia>");
                    saida.println("\t\t<document>" + rows[1] + "</document>");
                    saida.println("\t\t<media>" + rows[2] + "</media>");
                    saida.println("\t\t<status>" + rows[3] + "</status>");
                    saida.println("\t\t<time>" + rows[4] + "</time>");
                    saida.println("\t</DimMedia>\r\n");
                }
            }
            if (b5) {
                sql = "SELECT dimProgram.idDimProgram, dimProgram.channelCode, dimProgram.channelName," +
                        " dimProgram.name, dimProgram.age, dimProgram.genre, dimProgram.subgenre" +
                        " FROM DimProgram dimProgram";
                qr = s.createQuery(sql);
                it = qr.list().iterator();
                while (it.hasNext()) {
                    Object[] rows = (Object[]) it.next();
                    saida.println("\t<DimProgram>");
                    saida.println("\t\t<idDimProgram>" + rows[0] + "</idDimProgram>");
                    saida.println("\t\t<channelCode>" + rows[1] + "</channelCode>");
                    saida.println("\t\t<channelName>" + rows[2] + "</channelName>");
                    saida.println("\t\t<name>" + rows[3] + "</name>");
                    saida.println("\t\t<age>" + rows[4] + "</age>");
                    saida.println("\t\t<genre>" + rows[5] + "</genre>");
                    saida.println("\t\t<subgenre>" + rows[6] + "</subgenre>");
                    saida.println("\t</DimProgram>\r\n");
                }
            }
            if (b6) {
                sql = "SELECT dimSocialnetwork.idDimSocialNetwork, dimSocialnetwork.name" +
                        " FROM DimSocialnetwork dimSocialnetwork";
                qr = s.createQuery(sql);
                it = qr.list().iterator();
                while (it.hasNext()) {
                    Object[] rows = (Object[]) it.next();
                    saida.println("\t<DimSocialnetwork>");
                    saida.println("\t\t<idDimSocialNetwork>" + rows[0] + "</idDimSocialNetwork>");
                    saida.println("\t\t<name>" + rows[1] + "</name>");
                    saida.println("\t</DimSocialnetwork>\r\n");
                }
            }
            if (b7) {
                sql = "SELECT dimTime.idDimTime, dimTime.hour12, dimTime.hour24, dimTime.minute, dimTime.second" +
                        " FROM DimTime dimTime";
                qr = s.createQuery(sql);
                it = qr.list().iterator();
                while (it.hasNext()) {
                    Object[] rows = (Object[]) it.next();
                    saida.println("\t<DimTime>");
                    saida.println("\t\t<idDimTime>" + rows[0] + "</idDimTime>");
                    saida.println("\t\t<hour12>" + rows[1] + "</hour12>");
                    saida.println("\t\t<hour24>" + rows[2] + "</hour24>");
                    saida.println("\t\t<minute>" + rows[3] + "</minute>");
                    saida.println("\t\t<second>" + rows[4] + "</second>");
                    saida.println("\t</DimTime>\r\n");
                }
            }
            if (b8) {
                sql = "SELECT dimUser.idDimUser, dimUser.age, dimUser.genre" +
                        " FROM DimUser dimUser";
                qr = s.createQuery(sql);
                it = qr.list().iterator();
                while (it.hasNext()) {
                    Object[] rows = (Object[]) it.next();
                    saida.println("\t<DimUser>");
                    saida.println("\t\t<idDimUser>" + rows[0] + "</idDimUser>");
                    saida.println("\t\t<age>" + rows[1] + "</age>");
                    saida.println("\t\t<genre>" + rows[2] + "</genre>");
                    saida.println("\t</DimUser>\r\n");
                }
            }
            if (b10) {
                sql = "SELECT iteraction.idIteraction, iteraction.type, iteraction.volumeLevel," +
                        " iteraction.volumeMute, iteraction.keyCode, iteraction.keyAction" +
                        " FROM Iteraction iteraction";
                qr = s.createQuery(sql);
                it = qr.list().iterator();
                while (it.hasNext()) {
                    Object[] rows = (Object[]) it.next();
                    saida.println("\t<Iteraction>");
                    saida.println("\t\t<idIteraction>" + rows[0] + "</idIteraction>");
                    saida.println("\t\t<type>" + rows[1] + "</type>");
                    saida.println("\t\t<volumeLevel>" + rows[2] + "</volumeLevel>");
                    saida.println("\t\t<volumeMute>" + rows[3] + "</volumeMute>");
                    saida.println("\t\t<keyCode>" + rows[4] + "</keyCode>");
                    saida.println("\t\t<keyAction>" + rows[5] + "</keyAction>");
                    saida.println("\t</Iteraction>\r\n");
                }
            }
            if (b12) {
                sql = "SELECT iteractionHasDimData.id.iteractionIdIteraction, iteractionHasDimData.id.dimDataIdDimData" +
                        "  FROM IteractionHasDimData iteractionHasDimData";
                qr = s.createQuery(sql);
                it = qr.list().iterator();
                while (it.hasNext()) {
                    Object[] rows = (Object[]) it.next();
                    saida.println("\t<iteractionHasDimDataId>");
                    saida.println("\t\t<iteractionIdIteraction>" + rows[0] + "</iteractionIdIteraction>");
                    saida.println("\t\t<dimDataIdDimData>" + rows[1] + "</dimDataIdDimData>");
                    saida.println("\t</iteractionHasDimDataId>\r\n");
                }
            }
            if (b14) {
                sql = "SELECT iteractionHasDimDeviceId.id.iteractionIdIteraction, iteractionHasDimDeviceId.id.dimDeviceIdDimDevice" +
                        " FROM IteractionHasDimDevice iteractionHasDimDeviceId";
                qr = s.createQuery(sql);
                it = qr.list().iterator();
                while (it.hasNext()) {
                    Object[] rows = (Object[]) it.next();
                    saida.println("\t<IteractionHasDimDeviceId>");
                    saida.println("\t\t<iteractionIdIteraction>" + rows[0] + "</iteractionIdIteraction>");
                    saida.println("\t\t<dimDeviceIdDimDevice>" + rows[1] + "</dimDeviceIdDimDevice>");
                    saida.println("\t</IteractionHasDimDeviceId>\r\n");
                }
            }

            if (b16) {
                sql = "SELECT iteractionHasDimLocationId.id.iteractionIdIteraction, iteractionHasDimLocationId.id.dimLocationIdDimLocation" +
                        " FROM IteractionHasDimLocation iteractionHasDimLocationId";
                qr = s.createQuery(sql);
                it = qr.list().iterator();
                while (it.hasNext()) {
                    Object[] rows = (Object[]) it.next();
                    saida.println("\t<IteractionHasDimLocationId>");
                    saida.println("\t\t<iteractionIdIteraction>" + rows[0] + "</iteractionIdIteraction>");
                    saida.println("\t\t<dimLocationIdDimLocation>" + rows[1] + "</dimLocationIdDimLocation>");
                    saida.println("\t</IteractionHasDimLocationId>\r\n");
                }
            }

            if (b18) {
                sql = "SELECT iteractionHasDimMediaId.id.iteractionIdIteraction, iteractionHasDimMediaId.id.dimMediaIdDimMedia" +
                        " FROM IteractionHasDimMedia iteractionHasDimMediaId";
                qr = s.createQuery(sql);
                it = qr.list().iterator();
                while (it.hasNext()) {
                    Object[] rows = (Object[]) it.next();
                    saida.println("\t<IteractionHasDimMediaId>");
                    saida.println("\t\t<iteractionIdIteraction>" + rows[0] + "</iteractionIdIteraction>");
                    saida.println("\t\t<dimMediaIdDimMedia>" + rows[1] + "</dimMediaIdDimMedia>");
                    saida.println("\t</IteractionHasDimMediaId>\r\n");
                }
            }

            if (b20) {
                sql = "SELECT iteractionHasDimProgramId.id.iteractionIdIteraction, iteractionHasDimProgramId.id.dimProgramIdDimProgram" +
                        " FROM IteractionHasDimProgram iteractionHasDimProgramId";
                qr = s.createQuery(sql);
                it = qr.list().iterator();
                while (it.hasNext()) {
                    Object[] rows = (Object[]) it.next();
                    saida.println("\t<IteractionHasDimProgramId>");
                    saida.println("\t\t<iteractionIdIteraction>" + rows[0] + "</iteractionIdIteraction>");
                    saida.println("\t\t<dimProgramIdDimProgram>" + rows[1] + "</dimProgramIdDimProgram>");
                    saida.println("\t</IteractionHasDimProgramId>\r\n");
                }
            }

            if (b22) {
                sql = "SELECT iteractionHasDimTimeId.id.iteractionIdIteraction, iteractionHasDimTimeId.id.dimTimeIdDimTime" +
                        " FROM IteractionHasDimTime iteractionHasDimTimeId";
                qr = s.createQuery(sql);
                it = qr.list().iterator();
                while (it.hasNext()) {
                    Object[] rows = (Object[]) it.next();
                    saida.println("\t<IteractionHasDimTimeId>");
                    saida.println("\t\t<iteractionIdIteraction>" + rows[0] + "</iteractionIdIteraction>");
                    saida.println("\t\t<dimTimeIdDimTime>" + rows[1] + "</dimTimeIdDimTime>");
                    saida.println("\t</IteractionHasDimTimeId>\r\n");
                }
            }

            if (b24) {
                sql = "SELECT iteractionHasDimUserId.id.iteractionIdIteraction, iteractionHasDimUserId.id.dimUserIdDimUser" +
                        " FROM IteractionHasDimUser iteractionHasDimUserId";
                qr = s.createQuery(sql);
                it = qr.list().iterator();
                while (it.hasNext()) {
                    Object[] rows = (Object[]) it.next();
                    saida.println("\t<IteractionHasDimUserId>");
                    saida.println("\t\t<iteractionIdIteraction>" + rows[0] + "</iteractionIdIteraction>");
                    saida.println("\t\t<dimUserIdDimUser>" + rows[1] + "</dimUserIdDimUser>");
                    saida.println("\t</IteractionHasDimUserId>\r\n");
                }
            }

            if (b25) {
                sql = "SELECT resourceCpu.idResourceCpu, resourceCpu.dimDeviceIdDimDevice, resourceCpu.model, resourceCpu.clock, resourceCpu.cores, resourceCpu.arch" + "  FROM ResourceCpu resourceCpu";
                qr = s.createQuery(sql);
                it = qr.list().iterator();
                while (it.hasNext()) {
                    Object[] rows = (Object[]) it.next();
                    saida.println("\t<ResourceCpu>");
                    saida.println("\t\t<idResourceCpu>" + rows[0] + "</idResourceCpu>");
                    saida.println("\t\t<dimDeviceIdDimDevice>" + rows[1] + "</dimDeviceIdDimDevice>");
                    saida.println("\t\t<model>" + rows[2] + "</model>");
                    saida.println("\t\t<clock>" + rows[3] + "</clock>");
                    saida.println("\t\t<cores>" + rows[4] + "</cores>");
                    saida.println("\t\t<arch>" + rows[5] + "</arch>");
                    saida.println("\t</ResourceCpu>\r\n");
                }
            }

            if (b26) {
                sql = "SELECT resourceHid.idResourceHid, resourceHid.dimDeviceIdDimDevice, resourceHid.model, resourceHid.type" + "  FROM ResourceHid resourceHid";
                qr = s.createQuery(sql);
                it = qr.list().iterator();
                while (it.hasNext()) {
                    Object[] rows = (Object[]) it.next();
                    saida.println("\t<ResourceHid>");
                    saida.println("\t\t<idResourceHid>" + rows[0] + "</idResourceHid>");
                    saida.println("\t\t<dimDeviceIdDimDevice>" + rows[1] + "</dimDeviceIdDimDevice>");
                    saida.println("\t\t<model>" + rows[2] + "</model>");
                    saida.println("\t\t<type>" + rows[3] + "</type>");
                    saida.println("\t</ResourceHid>\r\n");
                }
            }
            if (b27) {
                sql = "SELECT resourceNetwork.idResourceNetwork, resourceNetwork.dimDeviceIdDimDevice, resourceNetwork.speed, resourceNetwork.model" + "  FROM ResourceNetwork resourceNetwork";
                qr = s.createQuery(sql);
                it = qr.list().iterator();
                while (it.hasNext()) {
                    Object[] rows = (Object[]) it.next();
                    saida.println("\t<ResourceNetwork>");
                    saida.println("\t\t<idResourceNetwork>" + rows[0] + "</idResourceNetwork>");
                    saida.println("\t\t<dimDeviceIdDimDevice>" + rows[1] + "</dimDeviceIdDimDevice>");
                    saida.println("\t\t<speed>" + rows[2] + "</speed>");
                    saida.println("\t\t<model>" + rows[3] + "</model>");
                    saida.println("\t</ResourceNetwork>\r\n");
                }
            }
            if (b28) {
                sql = "SELECT resourceStorage.idResourceStorage, resourceStorage.dimDeviceIdDimDevice, resourceStorage.total, resourceStorage.free" + "  FROM ResourceStorage resourceStorage";
                qr = s.createQuery(sql);
                it = qr.list().iterator();
                while (it.hasNext()) {
                    Object[] rows = (Object[]) it.next();
                    saida.println("\t<ResourceStorage>");
                    saida.println("\t\t<idResourceStorage>" + rows[0] + "</idResourceStorage>");
                    saida.println("\t\t<dimDeviceIdDimDevice>" + rows[1] + "</dimDeviceIdDimDevice>");
                    saida.println("\t\t<total>" + rows[2] + "</total>");
                    saida.println("\t\t<free>" + rows[3] + "</free>");
                    saida.println("\t</ResourceStorage>\r\n");
                }
            }

            saida.print("</FA>");

            saida.close();
            writer.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return f;
    }

    public void redirecionarMenu() {
        Executions.getCurrent().sendRedirect("PesquisasDisponiveis.zul");
    }
}
