package controller.head;

import java.sql.SQLException;

import dao.head.DeviceDao;
import dao.head.SocialNetworkDao;
import entidades.Watch_Tv;

public class DeviceController {
	public void Persist(Watch_Tv obj) {
		for (int i = 0; i < obj.getHead().getsTBDevice().getDevices().size(); i++) {
			try {
				DeviceDao.Persist(obj, i);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
