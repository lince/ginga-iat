package controller.iteraction;

import java.sql.SQLException;

import dao.iteraction.IteractionDao;
import entidades.Watch_Tv;

public class IteractionController {
	public void Persist(Watch_Tv obj) {
		for (int i = 0; i < obj.getIteractions().size(); i++) {
			try {
				IteractionDao.Persist(obj, i);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
