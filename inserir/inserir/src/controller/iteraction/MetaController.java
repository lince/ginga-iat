package controller.iteraction;

import java.sql.SQLException;

import dao.iteraction.MetaDao;
import dao.iteraction.ProgramDao;
import entidades.Watch_Tv;
import entidades.iteraction.Program;

public class MetaController {

    public void Persist(Watch_Tv obj) {
        for (int i = 0; i < obj.getIteractions().size(); i++) {
            for (int k = 0; k < obj.getIteractions().get(i).getProgram().getMetas().size(); k++) {
                try {
                    MetaDao.Persist(obj, i, k);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
