package controller.head;

import java.sql.SQLException;

import dao.head.STBDeviceDao;
import dao.head.SocialNetworkDao;
import entidades.Watch_Tv;
import entidades.head.STBDevice;

public class SocialNetworkController {
	public void Persist(Watch_Tv obj) {
		for (int i = 0; i < obj.getHead().getUsuario().getSocialNetworks()
				.size(); i++) {
			try {
				SocialNetworkDao.Persist(obj, i);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}
}
