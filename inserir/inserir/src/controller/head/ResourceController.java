package controller.head;

import java.sql.SQLException;

import dao.head.ResourceDao;
import entidades.Watch_Tv;

public class ResourceController {
	public void Persist(Watch_Tv obj) {
		for (int i = 0; i < obj.getHead().getsTBDevice().getResources().size(); i++) {
			try {
				ResourceDao.Persist(obj, i);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		for (int i = 0; i < obj.getHead().getsTBDevice().getDevices().size(); i++) {
			for (int j = 0; j < obj.getHead().getsTBDevice().getDevices()
					.get(i).getResources().size(); j++) {
				try {
					ResourceDao.Persist(obj, i, j);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
