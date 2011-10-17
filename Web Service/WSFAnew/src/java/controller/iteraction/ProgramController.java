package controller.iteraction;

import java.sql.SQLException;

import dao.iteraction.ProgramDao;
import entidades.Watch_Tv;
import entidades.iteraction.Program;

public class ProgramController {

    public void Persist(Watch_Tv obj) throws ClassNotFoundException, SQLException {
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

    public Program Search(int code) throws SQLException, ClassNotFoundException {
        Program program = new Program();
        program = ProgramDao.Search(code);
        return program;
    }
}
