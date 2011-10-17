package controller.head;

import java.sql.SQLException;

import dao.head.HeadDao;
import entidades.Watch_Tv;

public class HeadController {
	public void Persit(Watch_Tv watchTv) {
		try {
			watchTv.getHead().getLocation().setIdLocation(
					new LocationController().Search(
							watchTv.getHead().getLocation()).getIdLocation());
		} catch (Exception e) {
			new LocationController().Persist(watchTv);
			watchTv.getHead().getLocation().setIdLocation(
					new LocationController().Search(
							watchTv.getHead().getLocation()).getIdLocation());
		}
		try {
			watchTv.getHead().getsTBDevice().setIdSTB_Device(
					new STBDeviceController().Search(
							watchTv.getHead().getsTBDevice().getSerialNumber())
							.getIdSTB_Device());
		} catch (Exception e) {
			new STBDeviceController().Persist(watchTv);
			watchTv.getHead().getsTBDevice().setIdSTB_Device(
					new STBDeviceController().Search(
							watchTv.getHead().getsTBDevice().getSerialNumber())
							.getIdSTB_Device());
		}
		try {
			HeadDao.Persist(watchTv);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
}
