package dao.iteraction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import utilidades.Conexao;
import entidades.Watch_Tv;

public class MetaDao {

    public static void Persist(Watch_Tv obj, int i, int k)
            throws SQLException, ClassNotFoundException {
        Connection con = Conexao.getConnection();
        PreparedStatement ps = con.prepareStatement(
                "insert into meta(Program_idProgram,meta) values (?,?)",
                Statement.RETURN_GENERATED_KEYS);

        ps.setInt(1, obj.getIteractions().get(i).getProgram().getIdProgram());
        ps.setString(2, obj.getIteractions().get(i).getProgram().getMetas().get(k).getMeta());

        ps.executeUpdate();

        ResultSet rskey = null;
        rskey = ps.getGeneratedKeys();

        if (rskey != null && rskey.next()) {
            obj.getIteractions().get(i).getProgram().getMetas().get(k).setIdMeta(rskey.getInt(1));
        }

        ps.close();
        con.close();
    }
}
