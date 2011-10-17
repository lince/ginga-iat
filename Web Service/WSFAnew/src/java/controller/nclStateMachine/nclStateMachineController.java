package controller.nclStateMachine;

import java.sql.SQLException;

import dao.nclStateMachine.nclStateMachineDao;

import entidades.Watch_Tv;
import entidades.nclStateMachine.nclStateMachine;

public class nclStateMachineController {

    public void Persist(Watch_Tv obj) throws SQLException, ClassNotFoundException {
        for (int i = 0; i < obj.getIteractions().size(); i++) {
            if (obj.getIteractions().get(i).getNclMachine() != null) {
                nclStateMachineDao.Persist(obj, i);
            }
        }
    }
}
