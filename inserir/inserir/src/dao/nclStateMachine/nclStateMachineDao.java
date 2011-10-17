package dao.nclStateMachine;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import utilidades.Conexao;
import entidades.Watch_Tv;

public class nclStateMachineDao {

    public static void Persist(Watch_Tv obj, int i) throws SQLException {
        Connection con = Conexao.getConnection();
        PreparedStatement ps = con.prepareStatement(
                "insert into nclstatemachine(Iteraction_idIteraction) values (?)",
                Statement.RETURN_GENERATED_KEYS);

        ps.setInt(1, obj.getIteractions().get(i).getIdIteraction());

        ps.executeUpdate();

        ResultSet rskey = null;
        rskey = ps.getGeneratedKeys();

        if (rskey != null && rskey.next()) {
            obj.getIteractions().get(i).getNclMachine().setIdnclStateMachine(rskey.getInt(1));
        }

        ps.close();
        con.close();
    }
}
