package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import utilidades.Conexao;
import entidades.Watch_Tv;

public class LogDao {
	public static void Persist(Watch_Tv obj) throws SQLException {
		Connection con = Conexao.getConnection();
		PreparedStatement ps = con
				.prepareStatement(
						"insert into log_(Watch_TV_idWatch_TV,Log_Date,ip,Size) values (?,?,?,?)",
						Statement.RETURN_GENERATED_KEYS);

		ps.setInt(1, obj.getIdWatch_Tv());
		ps.setTimestamp(2, obj.getLog().getLog_Date());
		ps.setString(3, obj.getLog().getIp());
		ps.setLong(4, obj.getLog().getSize());

		ps.executeUpdate();

		ResultSet rskey = null;
		rskey = ps.getGeneratedKeys();

		if (rskey != null && rskey.next()) {
			obj.getLog().setIdLog(rskey.getInt(1));
		}

		ps.close();
		con.close();
	}
}
