package controller.iteraction;

import java.sql.SQLException;

import dao.iteraction.IteractionDao;
import entidades.Watch_Tv;

public class IteractionController {
	public void Persist(Watch_Tv obj) throws SQLException, ClassNotFoundException {
		for (int i = 0; i < obj.getIteractions().size(); i++) {
			IteractionDao.Persist(obj, i);
		}
	}
}
