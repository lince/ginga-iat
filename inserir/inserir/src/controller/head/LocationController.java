package controller.head;

import java.sql.SQLException;

import dao.head.LocationDao;
import entidades.Watch_Tv;
import entidades.head.Location;

public class LocationController {
	public void Persist(Watch_Tv obj) {
		try {
			LocationDao.Persist(obj);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Location Search(Location location) {
		try {
			return LocationDao.Search(location);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

	}

}
