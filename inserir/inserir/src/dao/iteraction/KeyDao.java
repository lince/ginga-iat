package dao.iteraction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import utilidades.Conexao;
import entidades.Country;
import entidades.Watch_Tv;
import entidades.iteraction.Key;

public class KeyDao {

    public static void Persist(Watch_Tv obj, int i) throws SQLException {
        Connection con = Conexao.getConnection();
        PreparedStatement ps = con.prepareStatement(
                "insert into key_(Code,Action) values (?,?)",
                Statement.RETURN_GENERATED_KEYS);

        ps.setString(1, obj.getIteractions().get(i).getKey().getCode());
        ps.setString(2, obj.getIteractions().get(i).getKey().getAction());

        ps.executeUpdate();

        ResultSet rskey = null;
        rskey = ps.getGeneratedKeys();

        if (rskey != null && rskey.next()) {
            obj.getIteractions().get(i).getKey().setIdKey(rskey.getInt(1));
        }

        ps.close();
        con.close();
    }

    public static Key Search(Key key) throws SQLException {
        Connection con = Conexao.getConnection();
        PreparedStatement ps = null;
        if (key.getAction() == null) {
            ps = con.prepareStatement("select * from key_ where Code = ? AND Action IS NULL");
            ps.setString(1, key.getCode());
        } else {
            ps = con.prepareStatement("select * from key_ where Code = ? AND Action = ?");
            ps.setString(1, key.getCode());
            ps.setString(2, key.getAction());
        }

        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            Key k = new Key();
            k.setIdKey(rs.getInt("idKey_"));
            k.setCode(rs.getString("Code"));
            k.setAction(rs.getString("Action"));
            ps.close();
            con.close();
            return k;
        } else {
            ps.close();
            con.close();
            return null;
        }
    }
}
