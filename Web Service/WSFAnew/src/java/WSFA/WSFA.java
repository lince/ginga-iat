/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package WSFA;

import controller.LogController;
import controller.Watch_TvController;
import controller.head.DeviceController;
import controller.head.HeadController;
import controller.head.InformationController;
import controller.head.ResourceController;
import controller.head.SocialNetworkController;
import controller.head.SocialNetworkNameController;
import controller.head.UsuarioController;
import controller.iteraction.ChannelController;
import controller.iteraction.IteractionController;
import controller.iteraction.IteractionHasProgramController;
import controller.iteraction.KeyController;
import controller.iteraction.MetaController;
import controller.iteraction.ProgramController;
import controller.iteraction.VolumeController;
import controller.nclStateMachine.ContextController;
import controller.nclStateMachine.DocumentController;
import controller.nclStateMachine.InterfaceController;
import controller.nclStateMachine.MediaController;
import controller.nclStateMachine.PropertyController;
import controller.nclStateMachine.nclStateMachineController;
import entidades.Watch_Tv;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.sql.Timestamp;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

/**
 *
 * @author Administrador
 */
@WebService()
public class WSFA {

    /**
     * Operação de serviço web
     */
    @WebMethod(operationName = "retornaXML")
    public String retornaXML(@WebParam(name = "parameter") String parameter) {
        SAXParserFactory spf = SAXParserFactory.newInstance();
        SAXParser parser;
        Watch_Tv watchTv = new Watch_Tv();
        String ret = "";
        File f = new File("f.xml");
        try {
//            byte[] b = null;
//            FileOutputStream fileOutputStream = new FileOutputStream(f);
//            fileOutputStream.write(b);
//            fileOutputStream.flush();
//            fileOutputStream.close();



            FileWriter writer = new FileWriter(f);
            PrintWriter saida = new PrintWriter(writer);

            String[] s = parameter.split("-");
            byte b[] = new byte[s.length];

            for (int i = 0; i < b.length; i++) {
                b[i] = Byte.parseByte(s[i]);
            }
            String c = new String(b);
            String nova = "";
            for (int i = 0; i < c.length(); i++) {
                if ((c.charAt(i) == '\r') || (c.charAt(i) == '\n')) {
                    nova += "";
                } else {
                    nova += c.charAt(i);
                }

            }
            saida.print(nova);
            saida.close();
            writer.close();

//            FileReader reader = new FileReader(f);
//            BufferedReader leitor = new BufferedReader(reader);
//            String linha = null;
//            while ((linha = leitor.readLine()) != null) {
//                System.out.println("Linha: " + linha);
//            }
//
//            leitor.close();
//            reader.close();


            long tempo = System.currentTimeMillis();
            parser = spf.newSAXParser();
            parser.parse(f, new XMLHandler(watchTv));

            Save(watchTv, new Timestamp(System.currentTimeMillis()), "", f.length());

            System.out.println((System.currentTimeMillis() - tempo));

            ret = "sucesso";
        } catch (Exception e) {
            ret = "falha\r\n";
            ret += e.toString();
            System.out.println("falha\r\n");
            e.printStackTrace();
        }

        return ret;
    }

    private static void Save(Watch_Tv watch_Tv, Timestamp log_Date, String ip,
            long size) throws ClassNotFoundException, SQLException {
        watch_Tv.getLog().setLog_Date(log_Date);
        watch_Tv.getLog().setIp(ip);
        watch_Tv.getLog().setSize(size);
        new Watch_TvController().Persist(watch_Tv);
        new UsuarioController().Persist(watch_Tv);
        new HeadController().Persit(watch_Tv);
        new SocialNetworkNameController().Persist(watch_Tv);
        new SocialNetworkController().Persist(watch_Tv);
        new DeviceController().Persist(watch_Tv);
        new ResourceController().Persist(watch_Tv);
        new InformationController().Persist(watch_Tv);
        new KeyController().Persist(watch_Tv);
        new VolumeController().Persist(watch_Tv);
        new ChannelController().Persist(watch_Tv);
        new ProgramController().Persist(watch_Tv);
        new IteractionController().Persist(watch_Tv);
        new IteractionHasProgramController().Persist(watch_Tv);
        new MetaController().Persist(watch_Tv);
        new nclStateMachineController().Persist(watch_Tv);
        new DocumentController().Persist(watch_Tv);
        new ContextController().Persist(watch_Tv);
        new MediaController().Persist(watch_Tv);
        new PropertyController().Persist(watch_Tv);
        new InterfaceController().Persist(watch_Tv);
        new LogController().Persist(watch_Tv);
    }

    /**
     * Operação de serviço web
     */
    @WebMethod(operationName = "opstring")
    public String opstring(@WebParam(name = "parameter") String parameter) {

        return parameter.length() + "";
    }
}
