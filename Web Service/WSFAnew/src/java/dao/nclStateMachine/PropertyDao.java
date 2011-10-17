package dao.nclStateMachine;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import utilidades.Conexao;
import entidades.Watch_Tv;
import entidades.nclStateMachine.Media;

public class PropertyDao {
	public static void Persist(Media obj, int i) throws SQLException, ClassNotFoundException {
		Connection con = Conexao.getConnection();
		PreparedStatement ps = con
				.prepareStatement(
						"insert into property(Media_idMedia,name,value) values (?,?,?)",
						Statement.RETURN_GENERATED_KEYS);
		ps.setInt(1, obj.getIdMedia());
		ps.setString(2, obj.getProperties().get(i).getName());
		ps.setString(3, obj.getProperties().get(i).getValue());

		ps.executeUpdate();

		ResultSet rskey = null;
		rskey = ps.getGeneratedKeys();

		if (rskey != null && rskey.next()) {
			obj.getProperties().get(i).setIdProperty(rskey.getInt(1));
		}

		ps.close();
		con.close();
	}
}
