package controller.nclStateMachine;

import java.sql.SQLException;

import dao.nclStateMachine.ContextDao;
import entidades.Watch_Tv;

public class ContextController {

    public void Persist(Watch_Tv obj) throws ClassNotFoundException, SQLException {
        for (int i = 0; i < obj.getIteractions().size(); i++) {
            if (obj.getIteractions().get(i).getNclMachine() != null) {
                for (int j = 0; j < obj.getIteractions().get(i).getNclMachine().getDocuments().size(); j++) {
                    for (int k = 0; k < obj.getIteractions().get(i).getNclMachine().getDocuments().get(j).getContexts().size(); k++) {
                        ContextDao.Persist(obj, i, j, k);
                        for (int l = 0; l < obj.getIteractions().get(i).getNclMachine().getDocuments().get(j).getContexts().get(k).getContexts().size(); l++) {
                            try {
                                ContextDao.Persist(obj, i, j, k, l);
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
        }
    }
}
