package controller.head;

import java.sql.SQLException;

import dao.head.ResourceDao;
import entidades.Watch_Tv;

public class ResourceController {

    public void Persist(Watch_Tv obj) throws SQLException, ClassNotFoundException {
        for (int i = 0; i < obj.getHead().getsTBDevice().getResources().size(); i++) {
            ResourceDao.Persist(obj, i);
        }

        for (int i = 0; i < obj.getHead().getsTBDevice().getDevices().size(); i++) {
            for (int j = 0; j < obj.getHead().getsTBDevice().getDevices().get(i).getResources().size(); j++) {
                ResourceDao.Persist(obj, i, j);
            }
        }
    }
}
