/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.iteraction;

import entidades.Watch_Tv;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import utilidades.Conexao;

/**
 *
 * @author Samuk
 */
public class IteractionHasProgramDao {

    public static void Persist(Watch_Tv obj, int i, int j) throws SQLException {
//        Connection con = Conexao.getConnection();
//        PreparedStatement ps = con.prepareStatement("insert into iteraction_has_program(Iteraction_IdIteraction, Program_IdProgram) values (?,?)",
//                Statement.RETURN_GENERATED_KEYS);
//
//        ps.setInt(1, obj.getIteractions().get(i).getIdIteraction());
//        ps.setInt(2, obj.getIteractions().get(i).getChannel().getPrograms().get(j).getIdProgram());
//
//        ps.executeUpdate();
//
//        ps.close();
//        con.close();
    }
}
