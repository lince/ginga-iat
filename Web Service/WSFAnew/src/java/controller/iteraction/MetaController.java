package controller.iteraction;

import java.sql.SQLException;

import dao.iteraction.MetaDao;
import dao.iteraction.ProgramDao;
import entidades.Watch_Tv;
import entidades.iteraction.Program;

public class MetaController {

    public void Persist(Watch_Tv obj) throws SQLException, ClassNotFoundException {
        for (int i = 0; i < obj.getIteractions().size(); i++) {
            for (int k = 0; k < obj.getIteractions().get(i).getProgram().getMetas().size(); k++) {
                MetaDao.Persist(obj, i, k);
            }
        }

    }
}
