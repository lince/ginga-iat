/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

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
import java.io.File;
import java.sql.Timestamp;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

/**
 * 
 * @author Samuk
 */
public class Main {

    StringBuffer galhoAtual = new StringBuffer(200);
    StringBuffer valorAtual = new StringBuffer(100);

    /**
     * @param args
     *            the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        String caminho = "C:\\Documents and Settings\\Administrador\\Desktop\\PSAI\\PSAI\\";
//        caminho = "C:\\Documents and Settings\\Usuario\\Desktop\\Samuel\\";
        for (int i = 1; i <= 25; i++) {
            if (i < 10) {
                faz(caminho + "interaction_0" + i + ".xml");
            } else {
                faz(caminho + "interaction_" + i + ".xml");
            }
        }
//        faz(caminho + "interaction_03.xml");
    }

    public static void faz(String caminho) {
        SAXParserFactory spf = SAXParserFactory.newInstance();
        SAXParser parser;
        Watch_Tv watchTv = new Watch_Tv();
        try {
            long tempo = System.currentTimeMillis();
            parser = spf.newSAXParser();
            File f = new File(caminho);
            parser.parse(f, new XMLHandler(watchTv));

            Save(watchTv, new Timestamp(System.currentTimeMillis()), "", f.length());

            System.out.println((System.currentTimeMillis() - tempo));

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    public static void Save(Watch_Tv watch_Tv, Timestamp log_Date, String ip,
            long size) {
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
//        new ProgramController().Persist(watch_Tv);
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
}
