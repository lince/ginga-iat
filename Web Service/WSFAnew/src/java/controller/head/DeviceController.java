package controller.head;

import java.sql.SQLException;

import dao.head.DeviceDao;
import dao.head.SocialNetworkDao;
import entidades.Watch_Tv;

public class DeviceController {
	public void Persist(Watch_Tv obj) throws SQLException, ClassNotFoundException {
		for (int i = 0; i < obj.getHead().getsTBDevice().getDevices().size(); i++) {
			
				DeviceDao.Persist(obj, i);
			
		}
	}
}
