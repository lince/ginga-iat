package controller.nclStateMachine;

import java.sql.SQLException;

import dao.nclStateMachine.DocumentDao;

import entidades.Watch_Tv;

public class DocumentController {

    public void Persist(Watch_Tv obj) throws SQLException, ClassNotFoundException {
        for (int i = 0; i < obj.getIteractions().size(); i++) {
            if (obj.getIteractions().get(i).getNclMachine() != null) {
                for (int j = 0; j < obj.getIteractions().get(i).getNclMachine().getDocuments().size(); j++) {
                    DocumentDao.Persist(obj, i, j);
                }
            }
        }
    }
}
