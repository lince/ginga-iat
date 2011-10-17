package dao.iteraction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import utilidades.Conexao;
import entidades.Watch_Tv;

public class IteractionDao {

    public static void Persist(Watch_Tv obj, int i) throws SQLException, ClassNotFoundException {
        Connection con = Conexao.getConnection();
        PreparedStatement ps = con.prepareStatement(
                "insert into iteraction(Watch_TV_idWatch_TV,Time,Type_,Key__idKey_,Program_idProgram,Volume_idVolume) values (?,?,?,?,?,?)",
                Statement.RETURN_GENERATED_KEYS);
        //se nao existir alguma tag o valor do campo para esta tag é zero.
        ps.setInt(1, obj.getIdWatch_Tv());
        ps.setTimestamp(2, obj.getIteractions().get(i).getTime());
        ps.setString(3, obj.getIteractions().get(i).getTipo());
        if (obj.getIteractions().get(i).getKey() != null) {
            ps.setInt(4, obj.getIteractions().get(i).getKey().getIdKey());
        } else {
            ps.setInt(4, 0);
        }
        ps.setInt(5, obj.getIteractions().get(i).getProgram().getIdProgram());
        if (obj.getIteractions().get(i).getVolume() != null) {
            ps.setInt(6, obj.getIteractions().get(i).getVolume().getIdVolume());
        } else {
            ps.setInt(6, 0);
        }


        ps.executeUpdate();

        ResultSet rskey = null;
        rskey = ps.getGeneratedKeys();

        if (rskey != null && rskey.next()) {
            obj.getIteractions().get(i).setIdIteraction(rskey.getInt(1));
        }

        ps.close();
        con.close();
    }
}
