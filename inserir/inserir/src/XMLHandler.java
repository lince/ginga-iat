/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import entidades.Country;
import java.sql.Date;
import java.sql.Time;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import entidades.Watch_Tv;
import entidades.head.Device;
import entidades.head.Head;
import entidades.head.Information;
import entidades.head.Location;
import entidades.head.Resource;
import entidades.head.STBDevice;
import entidades.head.SocialNetwork;
import entidades.head.SocialNetworkName;
import entidades.head.Usuario;
import entidades.iteraction.Channel;
import entidades.iteraction.Iteraction;
import entidades.iteraction.Key;
import entidades.iteraction.Meta;
import entidades.iteraction.Program;
import entidades.iteraction.Volume;
import entidades.nclStateMachine.Context;
import entidades.nclStateMachine.Document;
import entidades.nclStateMachine.Interface;
import entidades.nclStateMachine.Media;
import entidades.nclStateMachine.Property;
import entidades.nclStateMachine.nclStateMachine;
import java.sql.Timestamp;

/**
 * 
 * @author Samuk
 */
public class XMLHandler extends DefaultHandler {

    StringBuffer galhoAtual = new StringBuffer(200);
    StringBuffer valorAtual = new StringBuffer(100);
    Watch_Tv watchTv;

    public static String alteraChar(String entrada, char c, int i) {
        String retorno = "";
        for (int j = 0; j < i; j++) {
            retorno += entrada.charAt(j);
        }
        retorno += c;
        for (int j = ++i; j < entrada.length(); j++) {
            retorno += entrada.charAt(j);
        }
        return retorno;
    }

    public XMLHandler(Watch_Tv watchTv) {
        this.watchTv = watchTv;
    }

    public void endDocument() {
        System.out.println("terminando");
    }

    public void startDocument() {
        System.out.println("iniciando");
    }

    public void startElement(String uri, String localName, String tag,
            Attributes atributos) {

        galhoAtual.append("/" + tag);

        // mostra a tag
        // System.out.println("\n<" + galhoAtual.substring(1)
        // + (atributos.getLength() != 0 ? " +ATRIBUTOS" : "") + ">");
        try {
            if (tag.equals("watchTV")) {
                Country country = new Country();
                watchTv.setStartDate(Timestamp.valueOf(alteraChar(atributos.getValue("startDate"), ' ', 10)));
                watchTv.setEndDate(Timestamp.valueOf(alteraChar(atributos.getValue("endDate"), ' ', 10)));
                country.setName(atributos.getValue("country"));
                watchTv.setCountry(country);
            }
            // HEAD

            if (tag.equals("head")) {
                Head head = new Head();
                watchTv.setHead(head);
            }
            if (tag.equals("location")) {
                Location location = new Location();
                location.setLatitude(Float.parseFloat(atributos.getValue("lat")));
                location.setLongitude(Float.parseFloat(atributos.getValue("long")));
                location.setZip(Integer.parseInt(atributos.getValue("zip")));
                watchTv.getHead().setLocation(location);
            }
            if (tag.equals("user")) {
                Usuario usuario = new Usuario();
                usuario.setBirth(Date.valueOf(atributos.getValue("birth")));
                usuario.setIdentification(atributos.getValue("id"));
                if (atributos.getValue("genre").equals("male")) {
                    usuario.setGenre('M');
                } else if (atributos.getValue("genre").equals("female")) {
                    usuario.setGenre('F');
                }
                watchTv.getHead().setUsuario(usuario);
            }
            if (tag.equals("socialNetwork")) {
                SocialNetwork socialNetwork = new SocialNetwork();
                SocialNetworkName socialNetworkName = new SocialNetworkName();
                socialNetwork.setIdentification(atributos.getValue("identification"));
                socialNetworkName.setName(atributos.getValue("name"));
                socialNetwork.setName(socialNetworkName);
                watchTv.getHead().getUsuario().getSocialNetworks().add(
                        socialNetwork);
            }
            if (tag.equals("device") && (galhoAtual.substring(1).equals("watchTV/head/device"))) {
                STBDevice sTBDevice = new STBDevice();
                sTBDevice.setSerialNumber(atributos.getValue("serialNumber"));
                sTBDevice.setType(atributos.getValue("type"));
                sTBDevice.setProfile(atributos.getValue("profile"));
                watchTv.getHead().setsTBDevice(sTBDevice);
            }
            if (tag.equals("resource") && (galhoAtual.substring(1).equals("watchTV/head/device/resources/resource"))) {
                Resource resource = new Resource();
                resource.setType(atributos.getValue("type"));
                watchTv.getHead().getsTBDevice().getResources().add(resource);
            }
            if (tag.equals("information") && (galhoAtual.substring(1).equals("watchTV/head/device/resources/resource/information"))) {
                Information information = new Information();
                information.setName(atributos.getValue("name"));
                information.setValue(atributos.getValue("value"));
                watchTv.getHead().getsTBDevice().getResources().get(
                        watchTv.getHead().getsTBDevice().getResources().size() - 1).getInformations().add(
                        information);
            }
            if (tag.equals("device") && (galhoAtual.substring(1).equals("watchTV/head/device/attachedDevices/device"))) {
                Device device = new Device();
                device.setSerialNumber(atributos.getValue("serialNumber"));
                device.setType(atributos.getValue("type"));
                device.setProfile(atributos.getValue("profile"));
                watchTv.getHead().getsTBDevice().getDevices().add(device);
            }
            if (tag.equals("resource") && (galhoAtual.substring(1).equals("watchTV/head/device/attachedDevices/device/resources/resource"))) {
                Resource resource = new Resource();
                resource.setType(atributos.getValue("type"));
                watchTv.getHead().getsTBDevice().getDevices().get(
                        watchTv.getHead().getsTBDevice().getDevices().size() - 1).getResources().add(
                        resource);
            }
            if (tag.equals("information") && (galhoAtual.substring(1).equals("watchTV/head/device/attachedDevices/device/resources/resource/information"))) {
                Information information = new Information();
                information.setName(atributos.getValue("name"));
                information.setValue(atributos.getValue("value"));
                watchTv.getHead().getsTBDevice().getDevices().get(
                        watchTv.getHead().getsTBDevice().getDevices().size() - 1).getResources().get(
                        watchTv.getHead().getsTBDevice().getDevices().get(
                        watchTv.getHead().getsTBDevice().getDevices().size() - 1).getResources().size() - 1).getInformations().add(information);

            }
            // INTERACTION

            if (tag.equals("interaction")) {
                Iteraction iteraction = new Iteraction();
                iteraction.setTime(Timestamp.valueOf(alteraChar(atributos.getValue("time"), ' ', 10)));
                iteraction.setTipo(atributos.getValue("type"));
                watchTv.getIteractions().add(iteraction);
            }
            if (tag.equals("key")) {
                Key key = new Key();
                key.setAction(atributos.getValue("action"));
                key.setCode(atributos.getValue("code"));
                watchTv.getIteractions().get(
                        watchTv.getIteractions().size() - 1).setKey(key);
            }
            if (tag.equals("channel")) {
                Channel channel = new Channel();
                channel.setCode(Integer.parseInt(atributos.getValue("code")));
                channel.setName(atributos.getValue("name"));
                //Muito feio isso aqui
                watchTv.getIteractions().get(
                        watchTv.getIteractions().size() - 1).setProgram(new Program());
                //Deveria ser mudado
                watchTv.getIteractions().get(
                        watchTv.getIteractions().size() - 1).getProgram().setChannel(channel);
            }
            if (tag.equals("program")) {
                Program program = new Program();
                program.setAge(Integer.parseInt(atributos.getValue("age")));
                program.setGenre(atributos.getValue("genre"));
                program.setSubgenre(atributos.getValue("subgenre"));
                program.setCode(Integer.parseInt(atributos.getValue("code")));
                program.setName(atributos.getValue("name"));
                //Essa linha serva pra consertar a parte do channel
                program.setChannel(watchTv.getIteractions().get(
                        watchTv.getIteractions().size() - 1).getProgram().getChannel());
                //Codigo normal
                watchTv.getIteractions().get(
                        watchTv.getIteractions().size() - 1).setProgram(program);
            }
            if (tag.equals("meta")) {
                Meta meta = new Meta();
                // Falta Algo
                // aki!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
                meta.setMeta("meta tag");
                watchTv.getIteractions().get(
                        watchTv.getIteractions().size() - 1).getProgram().getMetas().add(meta);
            }
            if (tag.equals("volume")) {
                Volume volume = new Volume();
                if (atributos.getValue("mute").equals("no")) {
                    volume.setMute(false);
                } else if (atributos.getValue("mute").equals("yes")) {
                    volume.setMute(true);
                } else {
                    System.err.println("Erro na leitura da tag volume");
                }
                volume.setLevel(Integer.parseInt(atributos.getValue("level")));
                watchTv.getIteractions().get(
                        watchTv.getIteractions().size() - 1).setVolume(volume);
            }

            // NCLSTATEMACHINE

            if (tag.equals("nclStateMachine")) {
                nclStateMachine nclStateMachine = new nclStateMachine();
                watchTv.getIteractions().get(
                        watchTv.getIteractions().size() - 1).setNclMachine(
                        nclStateMachine);
            }
            if (tag.equals("document")) {
                Document document = new Document();
                document.setId(atributos.getValue("id"));
                watchTv.getIteractions().get(
                        watchTv.getIteractions().size() - 1).getNclMachine().getDocuments().add(document);
            }
            if (tag.equals("media")) {
                Media media = new Media();
                media.setId(atributos.getValue("id"));
                media.setStatus(atributos.getValue("status"));
                media.setTime(Time.valueOf(atributos.getValue("time")));
                if (galhoAtual.substring(1).equals(
                        "watchTV/interaction/nclStateMachine/document/media")) {
                    watchTv.getIteractions().get(
                            watchTv.getIteractions().size() - 1).getNclMachine().getDocuments().get(
                            watchTv.getIteractions().get(
                            watchTv.getIteractions().size() - 1).getNclMachine().getDocuments().size() - 1).getMedias().add(media);
                } else if (galhoAtual.substring(1).equals(
                        "watchTV/interaction/nclStateMachine/document/context/media")) {
                    watchTv.getIteractions().get(watchTv.getIteractions().size() - 1).getNclMachine().getDocuments().get(
                            watchTv.getIteractions().get(
                            watchTv.getIteractions().size() - 1).getNclMachine().getDocuments().size() - 1).getContexts().get(
                            watchTv.getIteractions().get(
                            watchTv.getIteractions().size() - 1).getNclMachine().getDocuments().get(
                            watchTv.getIteractions().get(
                            watchTv.getIteractions().size() - 1).getNclMachine().getDocuments().size() - 1).getContexts().size() - 1).getMedias().add(media);
                } else if (galhoAtual.substring(1).equals(
                        "watchTV/interaction/nclStateMachine/document/context/context/media")) {
                    watchTv.getIteractions().get(watchTv.getIteractions().size() - 1).getNclMachine().getDocuments().get(
                            watchTv.getIteractions().get(
                            watchTv.getIteractions().size() - 1).getNclMachine().getDocuments().size() - 1).getContexts().get(
                            watchTv.getIteractions().get(
                            watchTv.getIteractions().size() - 1).getNclMachine().getDocuments().get(
                            watchTv.getIteractions().get(
                            watchTv.getIteractions().size() - 1).getNclMachine().getDocuments().size() - 1).getContexts().size() - 1).getContexts().get(
                            watchTv.getIteractions().get(
                            watchTv.getIteractions().size() - 1).getNclMachine().getDocuments().get(
                            watchTv.getIteractions().get(
                            watchTv.getIteractions().size() - 1).getNclMachine().getDocuments().size() - 1).getContexts().get(
                            watchTv.getIteractions().get(
                            watchTv.getIteractions().size() - 1).getNclMachine().getDocuments().get(
                            watchTv.getIteractions().get(
                            watchTv.getIteractions().size() - 1).getNclMachine().getDocuments().size() - 1).getContexts().size() - 1).getContexts().size() - 1).getMedias().add(media);
                }
            }
            if (tag.equals("context")) {
                Context context = new Context();
                context.setId(atributos.getValue("id"));
                if (galhoAtual.substring(1).equals(
                        "watchTV/interaction/nclStateMachine/document/context")) {
                    watchTv.getIteractions().get(
                            watchTv.getIteractions().size() - 1).getNclMachine().getDocuments().get(
                            watchTv.getIteractions().get(
                            watchTv.getIteractions().size() - 1).getNclMachine().getDocuments().size() - 1).getContexts().add(
                            context);
                } else if (galhoAtual.substring(1).equals(
                        "watchTV/interaction/nclStateMachine/document/context/context")) {
                    watchTv.getIteractions().get(watchTv.getIteractions().size() - 1).getNclMachine().getDocuments().get(
                            watchTv.getIteractions().get(
                            watchTv.getIteractions().size() - 1).getNclMachine().getDocuments().size() - 1).getContexts().get(
                            watchTv.getIteractions().get(
                            watchTv.getIteractions().size() - 1).getNclMachine().getDocuments().get(
                            watchTv.getIteractions().get(
                            watchTv.getIteractions().size() - 1).getNclMachine().getDocuments().size() - 1).getContexts().size() - 1).getContexts().add(context);
                }
            }
            if (tag.equals("interface")) {
                Interface interface1 = new Interface();
                interface1.setId(atributos.getValue("id"));
                interface1.setStatus(atributos.getValue("status"));
                if (galhoAtual.substring(1).equals(
                        "watchTV/interaction/nclStateMachine/document/media/interface")) {
                    watchTv.getIteractions().get(watchTv.getIteractions().size() - 1).getNclMachine().getDocuments().get(
                            watchTv.getIteractions().get(
                            watchTv.getIteractions().size() - 1).getNclMachine().getDocuments().size() - 1).getMedias().get(
                            watchTv.getIteractions().get(
                            watchTv.getIteractions().size() - 1).getNclMachine().getDocuments().get(
                            watchTv.getIteractions().get(
                            watchTv.getIteractions().size() - 1).getNclMachine().getDocuments().size() - 1).getMedias().size() - 1).getInterfaces().add(interface1);
                } else if (galhoAtual.substring(1).equals(
                        "watchTV/interaction/nclStateMachine/document/context/media/interface")) {
                    watchTv.getIteractions().get(watchTv.getIteractions().size() - 1).getNclMachine().getDocuments().get(
                            watchTv.getIteractions().get(
                            watchTv.getIteractions().size() - 1).getNclMachine().getDocuments().size() - 1).getContexts().get(
                            watchTv.getIteractions().get(
                            watchTv.getIteractions().size() - 1).getNclMachine().getDocuments().get(
                            watchTv.getIteractions().get(
                            watchTv.getIteractions().size() - 1).getNclMachine().getDocuments().size() - 1).getContexts().size() - 1).getMedias().get(
                            watchTv.getIteractions().get(
                            watchTv.getIteractions().size() - 1).getNclMachine().getDocuments().get(
                            watchTv.getIteractions().get(
                            watchTv.getIteractions().size() - 1).getNclMachine().getDocuments().size() - 1).getContexts().get(
                            watchTv.getIteractions().get(
                            watchTv.getIteractions().size() - 1).getNclMachine().getDocuments().get(
                            watchTv.getIteractions().get(
                            watchTv.getIteractions().size() - 1).getNclMachine().getDocuments().size() - 1).getContexts().size() - 1).getMedias().size() - 1).getInterfaces().add(interface1);
                } else if (galhoAtual.substring(1).equals(
                        "watchTV/interaction/nclStateMachine/document/context/context/media/interface")) {
                    watchTv.getIteractions().get(watchTv.getIteractions().size() - 1).getNclMachine().getDocuments().get(
                            watchTv.getIteractions().get(
                            watchTv.getIteractions().size() - 1).getNclMachine().getDocuments().size() - 1).getContexts().get(
                            watchTv.getIteractions().get(
                            watchTv.getIteractions().size() - 1).getNclMachine().getDocuments().get(
                            watchTv.getIteractions().get(
                            watchTv.getIteractions().size() - 1).getNclMachine().getDocuments().size() - 1).getContexts().size() - 1).getContexts().get(
                            watchTv.getIteractions().get(
                            watchTv.getIteractions().size() - 1).getNclMachine().getDocuments().get(
                            watchTv.getIteractions().get(
                            watchTv.getIteractions().size() - 1).getNclMachine().getDocuments().size() - 1).getContexts().get(
                            watchTv.getIteractions().get(
                            watchTv.getIteractions().size() - 1).getNclMachine().getDocuments().get(
                            watchTv.getIteractions().get(
                            watchTv.getIteractions().size() - 1).getNclMachine().getDocuments().size() - 1).getContexts().size() - 1).getContexts().size() - 1).getMedias().get(
                            watchTv.getIteractions().get(
                            watchTv.getIteractions().size() - 1).getNclMachine().getDocuments().get(
                            watchTv.getIteractions().get(
                            watchTv.getIteractions().size() - 1).getNclMachine().getDocuments().size() - 1).getContexts().get(
                            watchTv.getIteractions().get(
                            watchTv.getIteractions().size() - 1).getNclMachine().getDocuments().get(
                            watchTv.getIteractions().get(
                            watchTv.getIteractions().size() - 1).getNclMachine().getDocuments().size() - 1).getContexts().size() - 1).getContexts().get(
                            watchTv.getIteractions().get(
                            watchTv.getIteractions().size() - 1).getNclMachine().getDocuments().get(
                            watchTv.getIteractions().get(
                            watchTv.getIteractions().size() - 1).getNclMachine().getDocuments().size() - 1).getContexts().get(
                            watchTv.getIteractions().get(
                            watchTv.getIteractions().size() - 1).getNclMachine().getDocuments().get(
                            watchTv.getIteractions().get(
                            watchTv.getIteractions().size() - 1).getNclMachine().getDocuments().size() - 1).getContexts().size() - 1).getContexts().size() - 1).getMedias().size() - 1).getInterfaces().add(interface1);
                }
            }
            if (tag.equals("property")) {
                Property property = new Property();
                property.setName(atributos.getValue("name"));
                property.setValue(atributos.getValue("value"));
                if (galhoAtual.substring(1).equals(
                        "watchTV/interaction/nclStateMachine/document/media/property")) {
                    watchTv.getIteractions().get(watchTv.getIteractions().size() - 1).getNclMachine().getDocuments().get(
                            watchTv.getIteractions().get(
                            watchTv.getIteractions().size() - 1).getNclMachine().getDocuments().size() - 1).getMedias().get(
                            watchTv.getIteractions().get(
                            watchTv.getIteractions().size() - 1).getNclMachine().getDocuments().get(
                            watchTv.getIteractions().get(
                            watchTv.getIteractions().size() - 1).getNclMachine().getDocuments().size() - 1).getMedias().size() - 1).getProperties().add(property);
                } else if (galhoAtual.substring(1).equals(
                        "watchTV/interaction/nclStateMachine/document/context/media/property")) {
                    watchTv.getIteractions().get(watchTv.getIteractions().size() - 1).getNclMachine().getDocuments().get(
                            watchTv.getIteractions().get(
                            watchTv.getIteractions().size() - 1).getNclMachine().getDocuments().size() - 1).getContexts().get(
                            watchTv.getIteractions().get(
                            watchTv.getIteractions().size() - 1).getNclMachine().getDocuments().get(
                            watchTv.getIteractions().get(
                            watchTv.getIteractions().size() - 1).getNclMachine().getDocuments().size() - 1).getContexts().size() - 1).getMedias().get(
                            watchTv.getIteractions().get(
                            watchTv.getIteractions().size() - 1).getNclMachine().getDocuments().get(
                            watchTv.getIteractions().get(
                            watchTv.getIteractions().size() - 1).getNclMachine().getDocuments().size() - 1).getContexts().get(
                            watchTv.getIteractions().get(
                            watchTv.getIteractions().size() - 1).getNclMachine().getDocuments().get(
                            watchTv.getIteractions().get(
                            watchTv.getIteractions().size() - 1).getNclMachine().getDocuments().size() - 1).getContexts().size() - 1).getMedias().size() - 1).getProperties().add(property);
                } else if (galhoAtual.substring(1).equals(
                        "watchTV/interaction/nclStateMachine/document/context/context/media/property")) {
                    watchTv.getIteractions().get(watchTv.getIteractions().size() - 1).getNclMachine().getDocuments().get(
                            watchTv.getIteractions().get(
                            watchTv.getIteractions().size() - 1).getNclMachine().getDocuments().size() - 1).getContexts().get(
                            watchTv.getIteractions().get(
                            watchTv.getIteractions().size() - 1).getNclMachine().getDocuments().get(
                            watchTv.getIteractions().get(
                            watchTv.getIteractions().size() - 1).getNclMachine().getDocuments().size() - 1).getContexts().size() - 1).getContexts().get(
                            watchTv.getIteractions().get(
                            watchTv.getIteractions().size() - 1).getNclMachine().getDocuments().get(
                            watchTv.getIteractions().get(
                            watchTv.getIteractions().size() - 1).getNclMachine().getDocuments().size() - 1).getContexts().get(
                            watchTv.getIteractions().get(
                            watchTv.getIteractions().size() - 1).getNclMachine().getDocuments().get(
                            watchTv.getIteractions().get(
                            watchTv.getIteractions().size() - 1).getNclMachine().getDocuments().size() - 1).getContexts().size() - 1).getContexts().size() - 1).getMedias().get(
                            watchTv.getIteractions().get(
                            watchTv.getIteractions().size() - 1).getNclMachine().getDocuments().get(
                            watchTv.getIteractions().get(
                            watchTv.getIteractions().size() - 1).getNclMachine().getDocuments().size() - 1).getContexts().get(
                            watchTv.getIteractions().get(
                            watchTv.getIteractions().size() - 1).getNclMachine().getDocuments().get(
                            watchTv.getIteractions().get(
                            watchTv.getIteractions().size() - 1).getNclMachine().getDocuments().size() - 1).getContexts().size() - 1).getContexts().get(
                            watchTv.getIteractions().get(
                            watchTv.getIteractions().size() - 1).getNclMachine().getDocuments().get(
                            watchTv.getIteractions().get(
                            watchTv.getIteractions().size() - 1).getNclMachine().getDocuments().size() - 1).getContexts().get(
                            watchTv.getIteractions().get(
                            watchTv.getIteractions().size() - 1).getNclMachine().getDocuments().get(
                            watchTv.getIteractions().get(
                            watchTv.getIteractions().size() - 1).getNclMachine().getDocuments().size() - 1).getContexts().size() - 1).getContexts().size() - 1).getMedias().size() - 1).getProperties().add(property);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        // limpa a tag atual
        valorAtual.delete(0, valorAtual.length());

    }

    public void endElement(String uri, String localName, String tag) {

        // mostra o valor
        // System.out.println(valorAtual.toString().trim());
        // e limpa
        valorAtual.delete(0, valorAtual.length());

        // seta o galho atual
        galhoAtual.delete(galhoAtual.length() - tag.length() - 1, galhoAtual.length());

    }

    public void characters(char[] ch, int start, int length) {
        valorAtual.append(ch, start, length);

    }
}
