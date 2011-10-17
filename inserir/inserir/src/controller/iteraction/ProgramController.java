package controller.iteraction;

import java.sql.SQLException;

import dao.iteraction.ProgramDao;
import entidades.Watch_Tv;
import entidades.iteraction.Program;

public class ProgramController {

    public void Persist(Watch_Tv obj) {
        for (int i = 0; i < obj.getIteractions().size(); i++) {
            Program program = Search(obj.getIteractions().get(i).getProgram().getCode());
            try {
                if (program == null) {
                    ProgramDao.Persist(obj, i);
                } else {
                    obj.getIteractions().get(i).getProgram().setIdProgram(program.getIdProgram());
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

    }

    public Program Search(int code) {
        Program program = new Program();
        try {
            program = ProgramDao.Search(code);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return program;
    }
}
