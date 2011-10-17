package dao.nclStateMachine;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import utilidades.Conexao;
import entidades.nclStateMachine.Media;

public class InterfaceDao {
	public static void Persist(Media obj, int i) throws SQLException, ClassNotFoundException {
		Connection con = Conexao.getConnection();
		PreparedStatement ps = con
				.prepareStatement(
						"insert into interface(Media_idMedia,id,Status_) values (?,?,?)",
						Statement.RETURN_GENERATED_KEYS);
		ps.setInt(1, obj.getIdMedia());
		ps.setString(2, obj.getInterfaces().get(i).getId());
		ps.setString(3, obj.getInterfaces().get(i).getStatus());

		ps.executeUpdate();

		ResultSet rskey = null;
		rskey = ps.getGeneratedKeys();

		if (rskey != null && rskey.next()) {
			obj.getInterfaces().get(i).setIdInterface(rskey.getInt(1));
		}

		ps.close();
		con.close();
	}

}
