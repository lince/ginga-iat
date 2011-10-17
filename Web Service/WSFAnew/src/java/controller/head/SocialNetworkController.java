package controller.head;

import java.sql.SQLException;

import dao.head.STBDeviceDao;
import dao.head.SocialNetworkDao;
import entidades.Watch_Tv;
import entidades.head.STBDevice;

public class SocialNetworkController {

    public void Persist(Watch_Tv obj) throws SQLException, ClassNotFoundException {
        for (int i = 0; i < obj.getHead().getUsuario().getSocialNetworks().size(); i++) {
            SocialNetworkDao.Persist(obj, i);
        }
    }
}
