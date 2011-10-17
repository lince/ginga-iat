package controller.head;

import java.sql.SQLException;

import dao.head.STBDeviceDao;
import entidades.Watch_Tv;
import entidades.head.STBDevice;

public class STBDeviceController {
	public void Persist(Watch_Tv obj) {
		try {
			STBDeviceDao.Persist(obj);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public STBDevice Search(String serial) {
		STBDevice retorno = null;
		try {
			retorno = STBDeviceDao.Search(serial);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			return retorno;
		}
	}
}
