
import POJODW.DimData;
import POJODW.DimDevice;
import POJODW.DimLocation;
import POJODW.DimMedia;
import POJODW.DimProgram;
import POJODW.DimSocialnetwork;
import POJODW.DimTime;
import POJODW.DimUser;
import POJODW.DimUserHasDimSocialnetwork;
import POJODW.DimUserHasDimSocialnetworkId;
import POJODW.Iteraction;
import POJODW.IteractionHasDimData;
import POJODW.IteractionHasDimDataId;
import POJODW.IteractionHasDimDevice;
import POJODW.IteractionHasDimDeviceId;
import POJODW.IteractionHasDimLocation;
import POJODW.IteractionHasDimLocationId;
import POJODW.IteractionHasDimMedia;
import POJODW.IteractionHasDimMediaId;
import POJODW.IteractionHasDimProgram;
import POJODW.IteractionHasDimProgramId;
import POJODW.IteractionHasDimTime;
import POJODW.IteractionHasDimTimeId;
import POJODW.IteractionHasDimUser;
import POJODW.IteractionHasDimUserId;
import POJODW.ResourceCpu;
import POJODW.ResourceHid;
import POJODW.ResourceNetwork;
import POJODW.ResourceStorage;
import POJOTVDI.Channel;
import POJOTVDI.Context;
import POJOTVDI.Device;
import POJOTVDI.Document;
import POJOTVDI.Head;
import POJOTVDI.Information;
import POJOTVDI.Key;
import POJOTVDI.Location;
import POJOTVDI.Media;
import POJOTVDI.Nclstatemachine;
import POJOTVDI.Program;
import POJOTVDI.Resource;
import POJOTVDI.Socialnetwork;
import POJOTVDI.Socialnetworkname;
import POJOTVDI.StbDevice;
import POJOTVDI.User;
import POJOTVDI.Volume;
import POJOTVDI.WatchTv;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Samuk
 */
public class Main {

    public static void main(String[] args) throws ParseException {
        Configuration cfgTVDI = new Configuration().configure("/hibernateTVDI.cfg.xml");
        SessionFactory sfTVDI = cfgTVDI.buildSessionFactory();
        Session sTVDI = sfTVDI.openSession();
        Transaction tcTVDI = sTVDI.beginTransaction();

        Configuration cfgDW = new Configuration().configure("/hibernateDW.cfg.xml");
        SessionFactory sfDW = cfgDW.buildSessionFactory();
        Session sDW = sfDW.openSession();
        Transaction tcDW = sDW.beginTransaction();

        Query qrTVDI = null;
        Query qrDW = null;

//Query que resulta todos os Watch_tv que ainda nao participaram do ETL (FlagETL = FALSE)
        String sql = "SELECT watchtv.idWatchTv, watchtv.countryIdCountry, watchtv.startDate, watchtv.endDate, watchtv.flagEtl" + " FROM WatchTv watchtv" + " WHERE watchtv.flagEtl = FALSE";
        qrTVDI = sTVDI.createQuery(sql);
        Iterator itWatchtv = qrTVDI.list().iterator();
        while (itWatchtv.hasNext()) {
            Object[] rowWatchtv = (Object[]) itWatchtv.next();
            WatchTv watchTv = new WatchTv();
            watchTv.setIdWatchTv((Integer) rowWatchtv[0]);
            watchTv.setCountryIdCountry((Integer) rowWatchtv[1]);
            watchTv.setStartDate((Date) rowWatchtv[2]);
            watchTv.setEndDate((Date) rowWatchtv[3]);
            watchTv.setFlagEtl((Boolean) rowWatchtv[4]);

//Outros objetos necessarios
            DimUser dimUser = new DimUser();
            DimDevice dimDevice = new DimDevice();
            DimLocation dimLocation = new DimLocation();


//Query que resulta os Heads que tem o mesmo Watch_tv que o Iteraction
            sql = "SELECT head.idHead, head.userIdUser, head.locationIdLocation, head.watchTvIdWatchTv, head.stbDeviceIdStbDevice" + " FROM Head head" + " WHERE head.watchTvIdWatchTv = " + watchTv.getIdWatchTv();
            qrTVDI = sTVDI.createQuery(sql);
            Iterator itHead = qrTVDI.list().iterator();
            while (itHead.hasNext()) {
                Object[] rowHead = (Object[]) itHead.next();
                Head head = new Head();
                head.setIdHead((Integer) rowHead[0]);
                head.setUserIdUser((Integer) rowHead[1]);
                head.setLocationIdLocation((Integer) rowHead[2]);
                head.setWatchTvIdWatchTv((Integer) rowHead[3]);
                head.setStbDeviceIdStbDevice((Integer) rowHead[4]);
//Query que resulta os Users
                sql = "SELECT user.idUser, user.genre, user.birth, user.identification" + " FROM User user" + " WHERE user.idUser = " + head.getUserIdUser();
                qrTVDI = sTVDI.createQuery(sql);
                Iterator itUser = qrTVDI.list().iterator();
                while (itUser.hasNext()) {
                    Object[] rowUser = (Object[]) itUser.next();
                    User user = new User();
                    user.setIdUser((Integer) rowUser[0]);
                    user.setGenre((Character) rowUser[1]);
                    user.setBirth((Date) rowUser[2]);
                    user.setIdentification((String) rowUser[3]);

//Processo de Transformação dos dados
                    //Calcula a idade dada a data de nascimento
                    Calendar dateOfBirth = new GregorianCalendar();
                    dateOfBirth.setTime(user.getBirth());
                    Calendar today = Calendar.getInstance();
                    int age = today.get(Calendar.YEAR) - dateOfBirth.get(Calendar.YEAR);
                    dateOfBirth.add(Calendar.YEAR, age);
                    if (today.before(dateOfBirth)) {
                        age--;
                    }
                    dimUser.setAge(age);
                    dimUser.setGenre(user.getGenre().toString());

//Processo de Carga
//Query que verifica se ja existe esse user no dw
                    sql = "SELECT dimUser.idDimUser, dimUser.age, dimUser.genre" + " FROM DimUser dimUser" + " WHERE dimUser.age = " + dimUser.getAge() + " AND dimUser.genre LIKE '" + dimUser.getGenre() + "'";
                    qrDW = sDW.createQuery(sql);
                    Iterator itDimUser = qrDW.list().iterator();
//Verifica se o user a ser inserido ja existe no DW, se existir pega o id, se nao insere.
                    if (qrDW.list().size() == 0) {
                        sDW.save(dimUser);
                    } else {
                        Object[] rowDimUser = (Object[]) itDimUser.next();
                        dimUser.setIdDimUser((Integer) rowDimUser[0]);
                    }

//Query que resulta as Socialnetwork de cada User
                    sql = "SELECT socialnetwork.idSocialNetwork, socialnetwork.userIdUser, socialnetwork.socialNetWorkNameIdSocialNetWorkName, socialnetwork.identification" + " FROM Socialnetwork socialnetwork" + " WHERE socialnetwork.userIdUser = " + head.getUserIdUser();
                    qrTVDI = sTVDI.createQuery(sql);
                    Iterator itSocialnetwork = qrTVDI.list().iterator();
                    while (itSocialnetwork.hasNext()) {
                        Object[] rowSocialnetwork = (Object[]) itSocialnetwork.next();
                        Socialnetwork socialnetwork = new Socialnetwork();
                        socialnetwork.setIdSocialNetwork((Integer) rowSocialnetwork[0]);
                        socialnetwork.setUserIdUser((Integer) rowSocialnetwork[1]);
                        socialnetwork.setSocialNetWorkNameIdSocialNetWorkName((Integer) rowSocialnetwork[2]);
                        socialnetwork.setIdentification((String) rowSocialnetwork[3]);
//Query que resulta o nome das redes do usuario
                        sql = "SELECT socialnetworkname.idSocialNetWorkName, socialnetworkname.name" + " FROM Socialnetworkname socialnetworkname" + " WHERE socialnetworkname.idSocialNetWorkName = " + socialnetwork.getSocialNetWorkNameIdSocialNetWorkName();
                        qrTVDI = sTVDI.createQuery(sql);
                        Iterator itSocialnetworkname = qrTVDI.list().iterator();
                        while (itSocialnetworkname.hasNext()) {
                            Object[] rowSocialnetworkname = (Object[]) itSocialnetworkname.next();
                            Socialnetworkname socialnetworkname = new Socialnetworkname();
                            socialnetworkname.setIdSocialNetWorkName((Integer) rowSocialnetworkname[0]);
                            socialnetworkname.setName((String) rowSocialnetworkname[1]);

//Processo de Transformação dos dados
                            DimSocialnetwork dimSocialnetwork = new DimSocialnetwork();
                            dimSocialnetwork.setName(socialnetworkname.getName());

//Processo de Carga
//Query que verifica se ja existe esse social network no dw    
                            sql = "SELECT dimSocialnetwork.idDimSocialNetwork, dimSocialnetwork.name " + " FROM DimSocialnetwork dimSocialnetwork" + " WHERE dimSocialnetwork.name LIKE '" + dimSocialnetwork.getName() + "'";
                            qrDW = sDW.createQuery(sql);
                            Iterator itDimSocialNetworkName = qrDW.list().iterator();
//Verifica se o social network a ser inserido ja existe no DW, se existir pega o id, se nao insere.
                            if (qrDW.list().size() == 0) {
                                sDW.save(dimSocialnetwork);
                            } else {
                                Object[] rowDimSocialNetworkName = (Object[]) itDimSocialNetworkName.next();
                                dimSocialnetwork.setIdDimSocialNetwork((Integer) rowDimSocialNetworkName[0]);
                            }
                            DimUserHasDimSocialnetworkId dimUserHasDimSocialnetworkId = new DimUserHasDimSocialnetworkId();
                            dimUserHasDimSocialnetworkId.setDimSocialNetworkIdDimSocialNetwork(dimSocialnetwork.getIdDimSocialNetwork());
                            dimUserHasDimSocialnetworkId.setDimUserIdDimUser(dimUser.getIdDimUser());
                            DimUserHasDimSocialnetwork dimUserHasDimSocialnetwork = new DimUserHasDimSocialnetwork();
                            dimUserHasDimSocialnetwork.setId(dimUserHasDimSocialnetworkId);
                            sDW.merge(dimUserHasDimSocialnetwork);
                        }
                    }
                }
//Query que resulta o Location
                sql = "SELECT location.idLocation, location.zip, location.latitude, location.longitude" + " FROM Location location" + " WHERE location.idLocation = " + head.getLocationIdLocation();
                qrTVDI = sTVDI.createQuery(sql);
                Iterator itLocation = qrTVDI.list().iterator();
                while (itLocation.hasNext()) {
                    Object[] rowLocation = (Object[]) itLocation.next();
                    Location location = new Location();
                    location.setIdLocation((Integer) rowLocation[0]);
                    location.setZip((Integer) rowLocation[1]);
                    location.setLatitude((Double) rowLocation[2]);
                    location.setLongitude((Double) rowLocation[3]);

//Processo de Transformaçao dos dados
                    dimLocation.setZip(location.getZip().toString());
                    dimLocation.setLatitude(location.getLatitude());
                    dimLocation.setLongitude(location.getLongitude());
                    //Aqui entrara o codigo para descobrir cidade e estado atravez do cep
                    dimLocation.setCity("");
                    dimLocation.setState("");
                    dimLocation.setUf("");

//Processo de Carga
//Query que verifica se esa Location ja existe no DW
                    sql = "SELECT dimLocation.idDimLocation, dimLocation.zip, dimLocation.longitude," + " dimLocation.latitude, dimLocation.city, dimLocation.state, dimLocation.uf" + " FROM DimLocation dimLocation" + " WHERE  dimLocation.zip LIKE '" + dimLocation.getZip() + "'" + " AND dimLocation.longitude  = " + dimLocation.getLongitude() + " AND dimLocation.latitude = " + dimLocation.getLatitude() + " AND dimLocation.city LIKE '" + dimLocation.getCity() + "'" + " AND dimLocation.state LIKE '" + dimLocation.getState() + "'" + " AND dimLocation.uf LIKE '" + dimLocation.getUf() + "'";
                    qrDW = sDW.createQuery(sql);
                    Iterator itDimLoaction = qrDW.list().iterator();
//Verifica se a Location a ser inserido ja existe no DW, se existir pega o id, se nao insere.
                    if (qrDW.list().size() == 0) {
                        sDW.save(dimLocation);
                    } else {
                        Object[] rowDimLocarion = (Object[]) itDimLoaction.next();
                        dimLocation.setIdDimLocation((Integer) rowDimLocarion[0]);
                    }
                }
//Query que resulta o STB_Device
                sql = "SELECT stbDevice.idStbDevice, stbDevice.profile, stbDevice.type, stbDevice.serialNumber" + " FROM StbDevice stbDevice" + " WHERE stbDevice.idStbDevice = " + head.getStbDeviceIdStbDevice();
                qrTVDI = sTVDI.createQuery(sql);
                Iterator itStbDevice = qrTVDI.list().iterator();
                while (itStbDevice.hasNext()) {
                    Object[] rowStbDevice = (Object[]) itStbDevice.next();
                    StbDevice stbDevice = new StbDevice();
                    stbDevice.setIdStbDevice((Integer) rowStbDevice[0]);
                    stbDevice.setProfile((String) rowStbDevice[1]);
                    stbDevice.setType((String) rowStbDevice[2]);
                    stbDevice.setSerialNumber((String) rowStbDevice[3]);

//Processo de Transformação dos dados
                    dimDevice.setProfile(stbDevice.getProfile());
                    dimDevice.setSerialNumber(stbDevice.getSerialNumber());
                    dimDevice.setType(stbDevice.getType());
//Processo de Carga
//Query que verifica se este STB Device ja existe no DW
                    sql = "SELECT dimDevice.idDimDevice, dimDevice.serialNumber, dimDevice.type, dimDevice.profile" + " FROM DimDevice dimDevice" + " WHERE  dimDevice.serialNumber LIKE '" + dimDevice.getSerialNumber() + "'" + " AND dimDevice.type  LIKE '" + dimDevice.getType() + "'" + " AND dimDevice.profile LIKE '" + dimDevice.getProfile() + "'";
                    qrDW = sDW.createQuery(sql);
                    Iterator itDimDevice = qrDW.list().iterator();
//Verifica se o STB Device a ser inserido ja existe no DW, se existir pega o id, se nao insere.
                    if (qrDW.list().size() == 0) {
                        sDW.save(dimDevice);
                    } else {
                        Object[] rowDimDevice = (Object[]) itDimDevice.next();
                        dimDevice.setIdDimDevice((Integer) rowDimDevice[0]);
                    }

//Query que retorna os Resource do StbDevice
                    sql = "SELECT resource.idResource, resource.stbDeviceIdStbDevice, resource.type" + " FROM Resource resource" + " WHERE  resource.stbDeviceIdStbDevice = " + stbDevice.getIdStbDevice();
                    qrTVDI = sTVDI.createQuery(sql);
                    Iterator itResourceSTB = qrTVDI.list().iterator();
                    while (itResourceSTB.hasNext()) {
                        Object[] rowResourceSTB = (Object[]) itResourceSTB.next();
                        Resource resource = new Resource();
                        resource.setIdResource((Integer) rowResourceSTB[0]);
                        resource.setStbDeviceIdStbDevice((Integer) rowResourceSTB[1]);
                        resource.setType((String) rowResourceSTB[2]);
//Processo de Transformação dos dados
                        if (resource.getType().toUpperCase().equals("HID")) {
                            ResourceHid resourceHid = new ResourceHid();
                            resourceHid.setDimDeviceIdDimDevice(dimDevice.getIdDimDevice());
//Query que retorna os Information do Device
                            sql = "SELECT information.idInformation, information.resourceIdResource, information.name, information.value" + " FROM Information information" + " WHERE  information.resourceIdResource = " + resource.getIdResource();
                            qrTVDI = sTVDI.createQuery(sql);
                            Iterator itInformation = qrTVDI.list().iterator();
                            while (itInformation.hasNext()) {
                                Object[] rowInformation = (Object[]) itInformation.next();
                                Information information = new Information();
                                information.setIdInformation((Integer) rowInformation[0]);
                                information.setResourceIdResource((Integer) rowInformation[1]);
                                information.setName((String) rowInformation[2]);
                                information.setValue((String) rowInformation[3]);

//Processo de Transformação dos dados
                                if (information.getName().toUpperCase().equals("TYPE")) {
                                    resourceHid.setType(information.getValue());
                                } else if (information.getName().toUpperCase().equals("MODEL")) {
                                    resourceHid.setModel(information.getValue());
                                }
                            }
//Processo de Carga
//Query que verifica se este resource ja existe no DW
                            sql = "SELECT resourceHid.idResourceHid, resourceHid.dimDeviceIdDimDevice, resourceHid.model, resourceHid.type" + " FROM ResourceHid resourceHid" + " WHERE  resourceHid.model LIKE '" + resourceHid.getModel() + "'" + " AND resourceHid.type  LIKE '" + resourceHid.getType() + "'";
                            qrDW = sDW.createQuery(sql);
                            Iterator itResourceHid = qrDW.list().iterator();
//Verifica se o resource a ser inserido ja existe no DW, se existir pega o id, se nao insere.
                            if (qrDW.list().size() == 0) {
                                sDW.save(resourceHid);
                            } else {
                                Object[] rowResourceHid = (Object[]) itResourceHid.next();
                                resourceHid.setIdResourceHid((Integer) rowResourceHid[0]);
                            }
                        } else if (resource.getType().toUpperCase().equals("NETWORK")) {
                            ResourceNetwork resourceNetwork = new ResourceNetwork();
                            resourceNetwork.setDimDeviceIdDimDevice(dimDevice.getIdDimDevice());
//Query que retorna os Information do Device
                            sql = "SELECT information.idInformation, information.resourceIdResource, information.name, information.value" + " FROM Information information" + " WHERE  information.resourceIdResource = " + resource.getIdResource();
                            qrTVDI = sTVDI.createQuery(sql);
                            Iterator itInformation = qrTVDI.list().iterator();
                            while (itInformation.hasNext()) {
                                Object[] rowInformation = (Object[]) itInformation.next();
                                Information information = new Information();
                                information.setIdInformation((Integer) rowInformation[0]);
                                information.setResourceIdResource((Integer) rowInformation[1]);
                                information.setName((String) rowInformation[2]);
                                information.setValue((String) rowInformation[3]);

//Processo de Transformação dos dados
                                if (information.getName().toUpperCase().equals("SPEED")) {
                                    resourceNetwork.setSpeed(information.getValue());
                                } else if (information.getName().toUpperCase().equals("MODEL")) {
                                    resourceNetwork.setModel(information.getValue());
                                }
                            }
//Processo de Carga
//Query que verifica se este resource ja existe no DW
                            sql = "SELECT resourceNetwork.idResourceNetwork, resourceNetwork.dimDeviceIdDimDevice, resourceNetwork.speed, resourceNetwork.model" + " FROM ResourceNetwork resourceNetwork" + " WHERE  resourceNetwork.speed LIKE '" + resourceNetwork.getSpeed() + "'" + " AND resourceNetwork.model  LIKE '" + resourceNetwork.getModel() + "'";
                            qrDW = sDW.createQuery(sql);
                            Iterator itResourceNetwork = qrDW.list().iterator();
//Verifica se o resource a ser inserido ja existe no DW, se existir pega o id, se nao insere.
                            if (qrDW.list().size() == 0) {
                                sDW.save(resourceNetwork);
                            } else {
                                Object[] rowResourceNetwork = (Object[]) itResourceNetwork.next();
                                resourceNetwork.setIdResourceNetwork((Integer) rowResourceNetwork[0]);
                            }
                        } else if (resource.getType().toUpperCase().equals("CPU")) {
                            ResourceCpu resourceCpu = new ResourceCpu();
                            resourceCpu.setDimDeviceIdDimDevice(dimDevice.getIdDimDevice());
//Query que retorna os Information do Device
                            sql = "SELECT information.idInformation, information.resourceIdResource, information.name, information.value" + " FROM Information information" + " WHERE  information.resourceIdResource = " + resource.getIdResource();
                            qrTVDI = sTVDI.createQuery(sql);
                            Iterator itInformation = qrTVDI.list().iterator();
                            while (itInformation.hasNext()) {
                                Object[] rowInformation = (Object[]) itInformation.next();
                                Information information = new Information();
                                information.setIdInformation((Integer) rowInformation[0]);
                                information.setResourceIdResource((Integer) rowInformation[1]);
                                information.setName((String) rowInformation[2]);
                                information.setValue((String) rowInformation[3]);

//Processo de Transformação dos dados
                                if (information.getName().toUpperCase().equals("CLOCK")) {
                                    resourceCpu.setClock(information.getValue());
                                } else if (information.getName().toUpperCase().equals("MODEL")) {
                                    resourceCpu.setModel(information.getValue());
                                } else if (information.getName().toUpperCase().equals("CORES")) {
                                    resourceCpu.setCores(information.getValue());
                                } else if (information.getName().toUpperCase().equals("ARCH")) {
                                    resourceCpu.setArch(information.getValue());
                                }
                            }
//Processo de Carga
//Query que verifica se este resource ja existe no DW
                            sql = "SELECT resourceCpu.idResourceCpu, resourceCpu.dimDeviceIdDimDevice, resourceCpu.model, resourceCpu.clock, resourceCpu.cores, resourceCpu.arch" + " FROM ResourceCpu resourceCpu" + " WHERE  resourceCpu.model LIKE '" + resourceCpu.getModel() + "'" + " AND resourceCpu.clock  LIKE '" + resourceCpu.getClock() + "'" + " AND resourceCpu.cores  LIKE '" + resourceCpu.getCores() + "'" + " AND resourceCpu.arch  LIKE '" + resourceCpu.getArch() + "'";
                            qrDW = sDW.createQuery(sql);
                            Iterator itResourceCPU = qrDW.list().iterator();
//Verifica se o resource a ser inserido ja existe no DW, se existir pega o id, se nao insere.
                            if (qrDW.list().size() == 0) {
                                sDW.save(resourceCpu);
                            } else {
                                Object[] rowResourceCPU = (Object[]) itResourceCPU.next();
                                resourceCpu.setIdResourceCpu((Integer) rowResourceCPU[0]);
                            }
                        } else if (resource.getType().toUpperCase().equals("STORAGE")) {
                            ResourceStorage resourceStorage = new ResourceStorage();
                            resourceStorage.setDimDeviceIdDimDevice(dimDevice.getIdDimDevice());
//Query que retorna os Information do Device
                            sql = "SELECT information.idInformation, information.resourceIdResource, information.name, information.value" + " FROM Information information" + " WHERE  information.resourceIdResource = " + resource.getIdResource();
                            qrTVDI = sTVDI.createQuery(sql);
                            Iterator itInformation = qrTVDI.list().iterator();
                            while (itInformation.hasNext()) {
                                Object[] rowInformation = (Object[]) itInformation.next();
                                Information information = new Information();
                                information.setIdInformation((Integer) rowInformation[0]);
                                information.setResourceIdResource((Integer) rowInformation[1]);
                                information.setName((String) rowInformation[2]);
                                information.setValue((String) rowInformation[3]);

//Processo de Transformação dos dados
                                if (information.getName().toUpperCase().equals("TOTAL")) {
                                    resourceStorage.setTotal(information.getValue());
                                } else if (information.getName().toUpperCase().equals("FREE")) {
                                    resourceStorage.setFree(information.getValue());
                                }
                            }
//Processo de Carga
//Query que verifica se este resource ja existe no DW
                            sql = "SELECT resourceStorage.idResourceStorage, resourceStorage.dimDeviceIdDimDevice, resourceStorage.total, resourceStorage.free" + " FROM ResourceStorage resourceStorage" + " WHERE  resourceStorage.total LIKE '" + resourceStorage.getTotal() + "'" + " AND resourceStorage.free  LIKE '" + resourceStorage.getFree() + "'";
                            qrDW = sDW.createQuery(sql);
                            Iterator itResourceStorage = qrDW.list().iterator();
//Verifica se o resource a ser inserido ja existe no DW, se existir pega o id, se nao insere.
                            if (qrDW.list().size() == 0) {
                                sDW.save(resourceStorage);
                            } else {
                                Object[] rowResourceStorage = (Object[]) itResourceStorage.next();
                                resourceStorage.setIdResourceStorage((Integer) rowResourceStorage[0]);
                            }
                        }
                    }
//Query que retorna os devices do StbDevice                    
                    sql = "SELECT device.idDevice, device.stbDeviceIdStbDevice, device.serialNumber, device.type, device.profile" + " FROM Device device" + " WHERE  device.stbDeviceIdStbDevice = " + stbDevice.getIdStbDevice();
                    qrTVDI = sTVDI.createQuery(sql);
                    Iterator itDevice = qrTVDI.list().iterator();
                    while (itDevice.hasNext()) {
                        Object[] rowDevice = (Object[]) itDevice.next();
                        Device device = new Device();
                        device.setIdDevice((Integer) rowDevice[0]);
                        device.setStbDeviceIdStbDevice((Integer) rowDevice[1]);
                        device.setSerialNumber((String) rowDevice[2]);
                        device.setType((String) rowDevice[3]);
                        device.setProfile((String) rowDevice[4]);

                        //Query que retorna os Resources dos Devices do StbDevice
                        sql = "SELECT resource.idResource, resource.deviceIdDevice, resource.type" + " FROM Resource resource" + " WHERE  resource.deviceIdDevice = " + device.getIdDevice();
                        qrTVDI = sTVDI.createQuery(sql);
                        Iterator itResource = qrTVDI.list().iterator();
                        while (itResource.hasNext()) {
                            Object[] rowResource = (Object[]) itResource.next();
                            Resource resource = new Resource();
                            resource.setIdResource((Integer) rowResource[0]);
                            resource.setDeviceIdDevice((Integer) rowResource[1]);
                            resource.setType((String) rowResource[2]);

                        }
                    }
                }
            }

//Query que resulta os Iteractions que fazem parte dos Watch_tv resultantes da query anterior(watchtv.idWatchTv = iteraction.watchTvIdWatchTv)
            sql = "SELECT iteraction.idIteraction, iteraction.volumeIdVolume, iteraction.programIdProgram," + " iteraction.keyIdKey, iteraction.watchTvIdWatchTv, iteraction.time, iteraction.type" + " FROM Iteraction iteraction" + " WHERE " + watchTv.getIdWatchTv() + " = iteraction.watchTvIdWatchTv";
            qrTVDI = sTVDI.createQuery(sql);
            Iterator itIteractions = qrTVDI.list().iterator();
            while (itIteractions.hasNext()) {
                Object[] rowIteractions = (Object[]) itIteractions.next();
                POJOTVDI.Iteraction iteraction = new POJOTVDI.Iteraction();
                iteraction.setIdIteraction((Integer) rowIteractions[0]);
                iteraction.setVolumeIdVolume((Integer) rowIteractions[1]);
                iteraction.setProgramIdProgram((Integer) rowIteractions[2]);
                iteraction.setKeyIdKey((Integer) rowIteractions[3]);
                iteraction.setWatchTvIdWatchTv((Integer) rowIteractions[4]);
                iteraction.setTime((Date) rowIteractions[5]);
                iteraction.setType((String) rowIteractions[6]);
//Processo de Transformação dos dados
                Iteraction iteractiondw = new Iteraction();
                iteractiondw.setType(iteraction.getType());

                DimTime dimTime = new DimTime();
                if (iteraction.getTime().getHours() > 12) {
                    dimTime.setHour12(iteraction.getTime().getHours() / 2);
                } else {
                    dimTime.setHour12(iteraction.getTime().getHours());
                }
                dimTime.setHour24(iteraction.getTime().getHours());
                dimTime.setMinute(iteraction.getTime().getMinutes());
                dimTime.setSecond(iteraction.getTime().getSeconds());

                DimData dimData = new DimData();
                dimData.setDatacompleta(iteraction.getTime());
                Calendar c = new GregorianCalendar();
                c.setMinimalDaysInFirstWeek(1);
                c.setTime(iteraction.getTime());
//                System.out.println("");
//                System.out.println(c.getTime());
//                System.out.println(iteraction.getTime().toString());
                dimData.setDia(c.get(Calendar.DAY_OF_MONTH));
//                System.out.println("dia " + dimData.getDia());
                dimData.setMes(c.get(Calendar.MONTH) + 1);
//                System.out.println("mes " + dimData.getMes());
                dimData.setAno(c.get(Calendar.YEAR));
//                System.out.println("ano " + dimData.getAno());
                switch (c.get(Calendar.DAY_OF_WEEK)) {
                    case (1):
                        dimData.setDiaSemana("DOMINGO");
                        break;
                    case (2):
                        dimData.setDiaSemana("SEGUNDA");
                        break;
                    case (3):
                        dimData.setDiaSemana("TERCA");
                        break;
                    case (4):
                        dimData.setDiaSemana("QUARTA");
                        break;
                    case (5):
                        dimData.setDiaSemana("QUINTA");
                        break;
                    case (6):
                        dimData.setDiaSemana("SEXTA");
                        break;
                    case (7):
                        dimData.setDiaSemana("SABADO");
                        break;
                }
                dimData.setSemana(c.get(Calendar.WEEK_OF_MONTH));
//                System.out.println("semana " + dimData.getSemana());
                if (dimData.getDia() < 15) {
                    dimData.setQuinzena(1);
//                    System.out.println("primeira quinzena");
                } else {
                    dimData.setQuinzena(2);
//                    System.out.println("segunda quinzena");
                }
                switch (dimData.getMes()) {
                    case (1):
                        dimData.setTrimestre(1);
//                        System.out.println("primeiro trimestre");
                        dimData.setBimestre(1);
//                        System.out.println("primeiro bimestre");
                        dimData.setSemestre(1);
//                        System.out.println("primeiro semestre");
                        break;
                    case (2):
                        dimData.setTrimestre(1);
//                        System.out.println("primeiro trimestre");
                        dimData.setBimestre(1);
//                        System.out.println("primeiro bimestre");
                        dimData.setSemestre(1);
//                        System.out.println("primeiro semestre");
                        break;
                    case (3):
                        dimData.setTrimestre(1);
//                        System.out.println("primeiro trimestre");
                        dimData.setBimestre(2);
//                        System.out.println("segundo bimestre");
                        dimData.setSemestre(1);
//                        System.out.println("primeiro semestre");
                        break;
                    case (4):
                        dimData.setTrimestre(2);
//                        System.out.println("segundo trimestre");
                        dimData.setBimestre(2);
//                        System.out.println("segundo bimestre");
                        dimData.setSemestre(1);
//                        System.out.println("primeiro semestre");
                        break;
                    case (5):
                        dimData.setTrimestre(2);
//                        System.out.println("segundo trimestre");
                        dimData.setBimestre(3);
//                        System.out.println("terceiro bimestre");
                        dimData.setSemestre(1);
//                        System.out.println("primeiro semestre");
                        break;
                    case (6):
                        dimData.setTrimestre(2);
//                        System.out.println("segundo trimestre");
                        dimData.setBimestre(3);
//                        System.out.println("terceiro bimestre");
                        dimData.setSemestre(1);
//                        System.out.println("primeiro semestre");
                        break;
                    case (7):
                        dimData.setTrimestre(3);
//                        System.out.println("terceiro trimestre");
                        dimData.setBimestre(4);
//                        System.out.println("quarto bimestre");
                        dimData.setSemestre(2);
//                        System.out.println("segundo semestre");
                        break;
                    case (8):
                        dimData.setTrimestre(3);
//                        System.out.println("terceiro trimestre");
                        dimData.setBimestre(4);
//                        System.out.println("quarto bimestre");
                        dimData.setSemestre(2);
//                        System.out.println("segundo semestre");
                        break;
                    case (9):
                        dimData.setTrimestre(3);
//                        System.out.println("terceiro trimestre");
                        dimData.setBimestre(5);
//                        System.out.println("quinto bimestre");
                        dimData.setSemestre(2);
//                        System.out.println("segundo semestre");
                        break;
                    case (10):
                        dimData.setTrimestre(4);
//                        System.out.println("quarto trimestre");
                        dimData.setBimestre(5);
//                        System.out.println("quinto bimestre");
                        dimData.setSemestre(2);
//                        System.out.println("segundo semestre");
                        break;
                    case (11):
                        dimData.setTrimestre(4);
//                        System.out.println("quarto trimestre");
                        dimData.setBimestre(6);
//                        System.out.println("sexto bimestre");
                        dimData.setSemestre(2);
//                        System.out.println("segundo semestre");
                        break;
                    case (12):
                        dimData.setTrimestre(4);
//                        System.out.println("quarto trimestre");
                        dimData.setBimestre(6);
//                        System.out.println("sexto bimestre");
                        dimData.setSemestre(2);
//                        System.out.println("segundo semestre");
                        break;
                }
                //Fica faltando as ferias escolares e feriados nacionais
                dimData.setFeriasEscolares(false);
                dimData.setFeriadoNacional("");


//Query que resulta o volume e key associados a Iteraction
//(volume.idVolume = iteraction.volumeIdVolume AND key.idKey = iteraction.keyIdKey)
                sql = "SELECT volume.idVolume, volume.level, volume.mute, key.idKey, key.code, key.action" +
                        " FROM Volume volume, Key key WHERE volume.idVolume = " + iteraction.getVolumeIdVolume() +
                        " AND key.idKey = " + iteraction.getKeyIdKey();
                qrTVDI = sTVDI.createQuery(sql);
                Iterator itVolumeKey = qrTVDI.list().iterator();
                while (itVolumeKey.hasNext()) {
                    Object[] rowVolumeKey = (Object[]) itVolumeKey.next();
                    Volume volume = new Volume();
                    volume.setIdVolume((Integer) rowVolumeKey[0]);
                    volume.setLevel((Integer) rowVolumeKey[1]);
                    volume.setMute((Boolean) rowVolumeKey[2]);

                    Key key = new Key();
                    key.setIdKey((Integer) rowVolumeKey[3]);
                    key.setCode((String) rowVolumeKey[4]);
                    key.setAction((String) rowVolumeKey[5]);

//Processo de Transformação dos dados
                    iteractiondw.setVolumeLevel(volume.getLevel());
                    iteractiondw.setVolumeMute(volume.getMute());
                    iteractiondw.setKeyAction(key.getAction());
                    iteractiondw.setKeyCode(key.getCode());
                    iteractiondw.setIdIteraction(null);

//Processo de Carga
                    sDW.save(iteractiondw);
//Query que verifica se ja existe esse dimData no DW
                    sql = "SELECT dimData.idDimData, dimData.dia, dimData.mes, dimData.ano, dimData.diaSemana," + " dimData.semana, dimData.quinzena, dimData.trimestre, dimData.bimestre, dimData.semestre," + " dimData.feriasEscolares, dimData.feriadoNacional" + " FROM DimData dimData" + " WHERE dimData.dia = " + dimData.getDia() + " AND dimData.mes = " + dimData.getMes() + " AND dimData.ano = " + dimData.getAno() + " AND dimData.diaSemana LIKE '" + dimData.getDiaSemana() + "'" + " AND dimData.semana = " + dimData.getSemana() + " AND dimData.quinzena = " + dimData.getQuinzena() + " AND dimData.trimestre = " + dimData.getTrimestre() + " AND dimData.bimestre = " + dimData.getBimestre() + " AND dimData.semestre = " + dimData.getSemestre() + " AND dimData.feriasEscolares = " + dimData.getFeriasEscolares() + " AND dimData.feriadoNacional LIKE '" + dimData.getFeriadoNacional() + "'";
                    qrDW = sDW.createQuery(sql);
                    Iterator itDimData = qrDW.list().iterator();
//Verifica se a DimData a ser inserida ja existe no DW, se existir pega o id, se nao insere.
                    if (qrDW.list().size() == 0) {
                        sDW.save(dimData);
                    } else {
                        Object[] rowDimData = (Object[]) itDimData.next();
                        dimData.setIdDimData((Integer) rowDimData[0]);
                    }
                    IteractionHasDimDataId iteractionHasDimDataId = new IteractionHasDimDataId();
                    iteractionHasDimDataId.setDimDataIdDimData(dimData.getIdDimData());
                    iteractionHasDimDataId.setIteractionIdIteraction(iteractiondw.getIdIteraction());
                    IteractionHasDimData iteractionHasDimData = new IteractionHasDimData();
                    iteractionHasDimData.setId(iteractionHasDimDataId);
                    sDW.merge(iteractionHasDimData);

//Query que verifica se ja existe esse dimTime no DW
                    sql = "SELECT dimTime.idDimTime, dimTime.hour12, dimTime.hour24, dimTime.minute, dimTime.second" + " FROM DimTime dimTime" + " WHERE dimTime.hour12 = " + dimTime.getHour12() + " AND dimTime.hour24 = " + dimTime.getHour24() + " AND dimTime.minute = " + dimTime.getMinute() + " AND dimTime.second = " + dimTime.getSecond();
                    qrDW = sDW.createQuery(sql);
                    Iterator itDimTime = qrDW.list().iterator();
//Verifica se a Dim Time a ser inserida ja existe no DW, se existir pega o id, se nao insere.
                    if (qrDW.list().size() == 0) {
                        sDW.save(dimTime);
                    } else {
                        Object[] rowDimTime = (Object[]) itDimTime.next();
                        dimTime.setIdDimTime((Integer) rowDimTime[0]);
                    }
                    IteractionHasDimTimeId iteractionHasDimTimeId = new IteractionHasDimTimeId();
                    iteractionHasDimTimeId.setDimTimeIdDimTime(dimTime.getIdDimTime());
                    iteractionHasDimTimeId.setIteractionIdIteraction(iteractiondw.getIdIteraction());
                    IteractionHasDimTime iteractionHasDimTime = new IteractionHasDimTime();
                    iteractionHasDimTime.setId(iteractionHasDimTimeId);
                    sDW.merge(iteractionHasDimTime);

                }

//Query que resulta os Nclstatemachine associados a Iteraction (nclstatemachine.iteractionIdIteraction = iteraction.idIteraction)
                sql = "SELECT nclstatemachine.idnclStateMachine, nclstatemachine.iteractionIdIteraction" + " FROM Nclstatemachine nclstatemachine" + " WHERE nclstatemachine.iteractionIdIteraction = " + iteraction.getIdIteraction() + "";
                qrTVDI = sTVDI.createQuery(sql);
                Iterator itNclstatemachine = qrTVDI.list().iterator();
                while (itNclstatemachine.hasNext()) {
                    Object[] rowNclstatemachine = (Object[]) itNclstatemachine.next();
                    Nclstatemachine nclstatemachine = new Nclstatemachine();
                    nclstatemachine.setIdnclStateMachine((Integer) rowNclstatemachine[0]);
                    nclstatemachine.setIteractionIdIteraction((Integer) rowNclstatemachine[1]);
//Query que resulta os Document associados ao NclStateMachine (document.nclStateMachineIdnclStateMachine = nclstatemachine.idnclStateMachine)
                    sql = "SELECT document.iddocument, document.nclStateMachineIdnclStateMachine, document.id" + " FROM Document document" + " WHERE document.nclStateMachineIdnclStateMachine = " + nclstatemachine.getIdnclStateMachine() + "";
                    qrTVDI = sTVDI.createQuery(sql);
                    Iterator itDocument = qrTVDI.list().iterator();
                    while (itDocument.hasNext()) {
                        Object[] rowDocument = (Object[]) itDocument.next();
                        Document document = new Document();
                        document.setIddocument((Integer) rowDocument[0]);
                        document.setNclStateMachineIdnclStateMachine((Integer) rowDocument[1]);
                        document.setId((String) rowDocument[2]);
//Query que resulta as medias e os contexts associados diretamente ao document
                        sql = "SELECT media.idMedia, media.documentIddocument, media.id, media.status, media.time," + " context.idContext, context.documentIddocument, context.id" + " FROM Media media, Context context" + " WHERE context.documentIddocument = " + document.getIddocument() + " AND media.documentIddocument = " + document.getIddocument();
                        qrTVDI = sTVDI.createQuery(sql);
                        Iterator itMediaContext = qrTVDI.list().iterator();
                        while (itMediaContext.hasNext()) {
                            Object[] rowMediaContext = (Object[]) itMediaContext.next();
                            Media media = new Media();
                            media.setIdMedia((Integer) rowMediaContext[0]);
                            media.setDocumentIddocument((Integer) rowMediaContext[1]);
                            media.setId((String) rowMediaContext[2]);
                            media.setStatus((String) rowMediaContext[3]);
                            media.setTime((Date) rowMediaContext[4]);

                            Context context = new Context();
                            context.setIdContext((Integer) rowMediaContext[5]);
                            context.setDocumentIddocument((Integer) rowMediaContext[6]);
                            context.setId((String) rowMediaContext[7]);

//Processo de Transformação dos dados
                            DimMedia dimMedia = new DimMedia();
                            dimMedia.setDocument(document.getId());
                            dimMedia.setMedia(media.getId());
                            dimMedia.setStatus(media.getStatus());
                            dimMedia.setTime(media.getTime());

//Processo de Carga
//Query que verifica se ja existe essa media no DW
                            sql = "SELECT dimMedia.idDimMedia, dimMedia.document, dimMedia.media, dimMedia.status, dimMedia.time" + " FROM DimMedia dimMedia" + " WHERE dimMedia.document LIKE '" + dimMedia.getDocument() + "'" + " AND dimMedia.media LIKE '" + dimMedia.getMedia() + "'" + " AND dimMedia.status LIKE '" + dimMedia.getStatus() + "'" + " AND dimMedia.time = '" + new Time(dimMedia.getTime().getTime()) + "'";
                            qrDW = sDW.createQuery(sql);
                            Iterator itDimMedia = qrDW.list().iterator();
//Verifica se a media a ser inserida ja existe no DW, se existir pega o id, se nao insere.
                            if (qrDW.list().size() == 0) {
                                sDW.save(dimMedia);
                            } else {
                                Object[] rowDimMedia = (Object[]) itDimMedia.next();
                                dimMedia.setIdDimMedia((Integer) rowDimMedia[0]);
                            }
                            IteractionHasDimMediaId iteractionHasDimMediaId = new IteractionHasDimMediaId();
                            iteractionHasDimMediaId.setDimMediaIdDimMedia(dimMedia.getIdDimMedia());
                            iteractionHasDimMediaId.setIteractionIdIteraction(iteractiondw.getIdIteraction());
                            IteractionHasDimMedia iteractionHasDimMedia = new IteractionHasDimMedia();
                            iteractionHasDimMedia.setId(iteractionHasDimMediaId);
                            sDW.save(iteractionHasDimMedia);

//Query que resulta as medias e os contexts associados ao context associado ao document
                            sql = "SELECT media.idMedia, media.contextIdContext, media.id, media.status, media.time," + " context.idContext, context.contextIdContext, context.id" + " FROM Media media, Context context" + " WHERE context.contextIdContext = " + context.getIdContext() + " AND media.contextIdContext = " + context.getIdContext();
                            qrTVDI = sTVDI.createQuery(sql);

                            Iterator itMediaContext2 = qrTVDI.list().iterator();
                            while (itMediaContext2.hasNext()) {
                                Object[] rowMediaContext2 = (Object[]) itMediaContext2.next();
                                Media media2 = new Media();
                                media2.setIdMedia((Integer) rowMediaContext2[0]);
                                media2.setContextIdContext((Integer) rowMediaContext2[1]);
                                media2.setId((String) rowMediaContext2[2]);
                                media2.setStatus((String) rowMediaContext2[3]);
                                media2.setTime((Date) rowMediaContext2[4]);

                                Context context2 = new Context();
                                context2.setIdContext((Integer) rowMediaContext2[5]);
                                context2.setContextIdContext((Integer) rowMediaContext2[6]);
                                context2.setId((String) rowMediaContext2[7]);

//Processo de Transformação dos dados
                                DimMedia dimMedia2 = new DimMedia();
//                                dimMedia2.setDocument(document.getId());
                                dimMedia2.setMedia(media2.getId());
                                dimMedia2.setStatus(media2.getStatus());
                                dimMedia2.setTime(media2.getTime());
                                dimMedia2.setDocument(document.getId());

//Processo de Carga
//Query que verifica se ja existe essa media no DW
                                sql = "SELECT dimMedia.idDimMedia, dimMedia.document, dimMedia.media, dimMedia.status, dimMedia.time" + " FROM DimMedia dimMedia" //                                    + " WHERE dimMedia.document = " + dimMedia2.getDocument()
                                        + " WHERE dimMedia.media LIKE '" + dimMedia2.getMedia() + "'" + " AND dimMedia.status LIKE '" + dimMedia2.getStatus() + "'" + " AND dimMedia.time LIKE '" + new Time(dimMedia2.getTime().getTime()) + "'";
                                qrDW = sDW.createQuery(sql);
                                Iterator itDimMedia2 = qrDW.list().iterator();
//Verifica se a media a ser inserida ja existe no DW, se existir pega o id, se nao insere.
                                if (qrDW.list().size() == 0) {
                                    sDW.save(dimMedia2);
                                } else {
                                    Object[] rowDimMedia2 = (Object[]) itDimMedia2.next();
                                    dimMedia2.setIdDimMedia((Integer) rowDimMedia2[0]);
                                }
                                IteractionHasDimMediaId iteractionHasDimMediaId2 = new IteractionHasDimMediaId();
                                iteractionHasDimMediaId2.setDimMediaIdDimMedia(dimMedia2.getIdDimMedia());
                                iteractionHasDimMediaId2.setIteractionIdIteraction(iteractiondw.getIdIteraction());
                                IteractionHasDimMedia iteractionHasDimMedia2 = new IteractionHasDimMedia();
                                iteractionHasDimMedia2.setId(iteractionHasDimMediaId2);
                                sDW.merge(iteractionHasDimMedia2);

//Query que resulta as medias no nivel "mais baixo"
                                sql = "SELECT media.idMedia, media.contextIdContext, media.id, media.status, media.time" + " FROM Media media" + " WHERE media.contextIdContext = " + context2.getIdContext();
                                qrTVDI = sTVDI.createQuery(sql);

                                Iterator itMedia = qrTVDI.list().iterator();
                                while (itMedia.hasNext()) {
                                    Object[] rowMedia = (Object[]) itMedia.next();
                                    Media media3 = new Media();
                                    media3.setIdMedia((Integer) rowMedia[0]);
                                    media3.setContextIdContext((Integer) rowMedia[1]);
                                    media3.setId((String) rowMedia[2]);
                                    media3.setStatus((String) rowMedia[3]);
                                    media3.setTime((Date) rowMedia[4]);

//Processo de Transformação dos dados
                                    DimMedia dimMedia3 = new DimMedia();
                                    //   dimMedia3.setDocument(document.getId());
                                    dimMedia3.setMedia(media3.getId());
                                    dimMedia3.setStatus(media3.getStatus());
                                    dimMedia3.setTime(media3.getTime());
                                    dimMedia3.setDocument(document.getId());

//Processo de Carga
//Query que verifica se ja existe essa media no DW

                                    sql = "SELECT dimMedia.idDimMedia, dimMedia.document, dimMedia.media, dimMedia.status, dimMedia.time" + " FROM DimMedia dimMedia" //                                    + " WHERE dimMedia.document = " + dimMedia2.getDocument()
                                            + " WHERE dimMedia.media LIKE '" + dimMedia3.getMedia() + "'" + " AND dimMedia.status LIKE '" + dimMedia3.getStatus() + "'" + " AND dimMedia.time = '" + new Time(dimMedia3.getTime().getTime()) + "'";
                                    qrDW = sDW.createQuery(sql);
                                    Iterator itDimMedia3 = qrDW.list().iterator();
//Verifica se a media a ser inserida ja existe no DW, se existir pega o id, se nao insere.
                                    if (qrDW.list().size() == 0) {
                                        sDW.save(dimMedia3);
                                    } else {
                                        Object[] rowDimMedia3 = (Object[]) itDimMedia3.next();
                                        dimMedia3.setIdDimMedia((Integer) rowDimMedia3[0]);
                                    }
                                    IteractionHasDimMediaId iteractionHasDimMediaId3 = new IteractionHasDimMediaId();
                                    iteractionHasDimMediaId3.setDimMediaIdDimMedia(dimMedia3.getIdDimMedia());
                                    iteractionHasDimMediaId3.setIteractionIdIteraction(iteractiondw.getIdIteraction());
                                    IteractionHasDimMedia iteractionHasDimMedia3 = new IteractionHasDimMedia();
                                    iteractionHasDimMedia3.setId(iteractionHasDimMediaId3);
                                    sDW.merge(iteractionHasDimMedia3);

                                }
                            }

                        }
                    }
                }
//Query que resulta os Programs associados a Iteraction
                sql = "SELECT program.idProgram, program.channelIdChannel, program.age, program.genre, program.code, program.name, program.subGenre" + " FROM Program program" + " WHERE program.idProgram = " + iteraction.getProgramIdProgram();
                qrTVDI = sTVDI.createQuery(sql);
                Iterator itProgram = qrTVDI.list().iterator();
                while (itProgram.hasNext()) {
                    Object[] rowProgram = (Object[]) itProgram.next();
                    Program program = new Program();
                    program.setIdProgram((Integer) rowProgram[0]);
                    program.setChannelIdChannel((Integer) rowProgram[1]);
                    program.setAge((Integer) rowProgram[2]);
                    program.setGenre((String) rowProgram[3]);
                    program.setCode((Integer) rowProgram[4]);
                    program.setName((String) rowProgram[5]);
                    program.setSubGenre((String) rowProgram[6]);

//Processo de Transformação dos dados
                    DimProgram dimProgram = new DimProgram();
                    dimProgram.setAge(program.getAge());
                    dimProgram.setGenre(program.getGenre());
                    dimProgram.setName(program.getName());
                    dimProgram.setSubgenre(program.getSubGenre());

//Query que resulta os Channel de cada Program
                    sql = "SELECT channel.idChannel, channel.code, channel.name" + " FROM Channel channel" + " WHERE channel.idChannel = " + program.getChannelIdChannel();
                    qrTVDI = sTVDI.createQuery(sql);
                    Iterator itChannel = qrTVDI.list().iterator();
                    while (itChannel.hasNext()) {
                        Object[] rowChannel = (Object[]) itChannel.next();
                        Channel channel = new Channel();
                        channel.setIdChannel((Integer) rowChannel[0]);
                        channel.setCode((Integer) rowChannel[1]);
                        channel.setName((String) rowChannel[2]);

//Processo de Transformação dos dados
                        dimProgram.setChannelCode(channel.getCode());
                        dimProgram.setChannelName(channel.getName());

//Processo de Carga
//Query que verifica se ja existe esse program no DW

                        sql = "SELECT dimProgram.idDimProgram, dimProgram.channelCode, dimProgram.channelName, dimProgram.name, dimProgram.age, dimProgram.genre, dimProgram.subgenre" + " FROM DimProgram dimProgram" + " WHERE dimProgram.channelCode = " + dimProgram.getChannelCode() + " AND dimProgram.channelName LIKE '" + dimProgram.getChannelName() + "'" + " AND dimProgram.name LIKE '" + dimProgram.getName() + "'" + " AND dimProgram.age = " + dimProgram.getAge() + " AND dimProgram.genre LIKE '" + dimProgram.getGenre() + "'" + " AND dimProgram.subgenre LIKE '" + dimProgram.getSubgenre() + "'";
                        qrDW = sDW.createQuery(sql);
                        Iterator itDimProgram = qrDW.list().iterator();
//Verifica se o program a ser inserido ja existe no DW, se existir pega o id, se nao insere.
                        if (qrDW.list().size() == 0) {
                            sDW.save(dimProgram);
                        } else {
                            Object[] rowDimProgram = (Object[]) itDimProgram.next();
                            dimProgram.setIdDimProgram((Integer) rowDimProgram[0]);
                        }
                        IteractionHasDimProgramId iteractionHasDimProgramId = new IteractionHasDimProgramId();
                        iteractionHasDimProgramId.setDimProgramIdDimProgram(dimProgram.getIdDimProgram());
                        iteractionHasDimProgramId.setIteractionIdIteraction(iteractiondw.getIdIteraction());
                        IteractionHasDimProgram iteractionHasDimProgram = new IteractionHasDimProgram();
                        iteractionHasDimProgram.setId(iteractionHasDimProgramId);
                        sDW.merge(iteractionHasDimProgram);
                    }
                }
//Processo de Carga
//Iteraction com user
                IteractionHasDimUserId iteractionHasDimUserId = new IteractionHasDimUserId();
                iteractionHasDimUserId.setDimUserIdDimUser(dimUser.getIdDimUser());
                iteractionHasDimUserId.setIteractionIdIteraction(iteractiondw.getIdIteraction());
                IteractionHasDimUser iteractionHasDimUser = new IteractionHasDimUser();
                iteractionHasDimUser.setId(iteractionHasDimUserId);
                sDW.merge(iteractionHasDimUser);
//Iteraction com STB Device
                IteractionHasDimDeviceId iteractionHasDimDeviceId = new IteractionHasDimDeviceId();
                iteractionHasDimDeviceId.setDimDeviceIdDimDevice(dimDevice.getIdDimDevice());
                iteractionHasDimDeviceId.setIteractionIdIteraction(iteractiondw.getIdIteraction());
                IteractionHasDimDevice iteractionHasDimDevice = new IteractionHasDimDevice();
                iteractionHasDimDevice.setId(iteractionHasDimDeviceId);
                sDW.merge(iteractionHasDimDevice);
//Iteraction com Location
                IteractionHasDimLocationId iteractionHasDimLocationId = new IteractionHasDimLocationId();
                iteractionHasDimLocationId.setDimLocationIdDimLocation(dimLocation.getIdDimLocation());
                iteractionHasDimLocationId.setIteractionIdIteraction(iteractiondw.getIdIteraction());
                IteractionHasDimLocation iteractionHasDimLocation = new IteractionHasDimLocation();
                iteractionHasDimLocation.setId(iteractionHasDimLocationId);
                sDW.merge(iteractionHasDimLocation);
            }
            sql = "UPDATE WatchTv watchtv SET watchtv.flagEtl = TRUE" +
                    " WHERE watchtv.idWatchTv = " + watchTv.getIdWatchTv();
            sTVDI.createQuery(sql).executeUpdate();


        }
        tcTVDI.commit();
        tcDW.commit();

        sTVDI.close();

        sDW.close();
    }
}
