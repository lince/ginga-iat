package controller.head;

import java.sql.SQLException;

import dao.head.InformationDao;
import entidades.Watch_Tv;

public class InformationController {
	public void Persist(Watch_Tv obj) {
		for (int i = 0; i < obj.getHead().getsTBDevice().getResources().size(); i++) {
			for (int j = 0; j < obj.getHead().getsTBDevice().getResources()
					.get(i).getInformations().size(); j++) {
				try {
					InformationDao.Persist(obj, i, j);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

		}

		for (int k = 0; k < obj.getHead().getsTBDevice().getDevices().size(); k++) {
			for (int i = 0; i < obj.getHead().getsTBDevice().getDevices()
					.get(k).getResources().size(); i++) {
				for (int j = 0; j < obj.getHead().getsTBDevice().getDevices()
						.get(k).getResources().get(i).getInformations().size(); j++) {
					try {
						InformationDao.Persist(obj, i, j, k);
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
}
