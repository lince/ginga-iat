package dao.head;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import utilidades.Conexao;
import entidades.Watch_Tv;

public class ResourceDao {
	public static void Persist(Watch_Tv obj, int i) throws SQLException {
		Connection con = Conexao.getConnection();
		PreparedStatement ps = con
				.prepareStatement(
						"insert into resource(STB_Device_idSTB_Device,Type_) values (?,?)",
						Statement.RETURN_GENERATED_KEYS);

		ps.setInt(1, obj.getHead().getsTBDevice().getIdSTB_Device());
		ps.setString(2, obj.getHead().getsTBDevice().getResources().get(i)
				.getType());

		ps.executeUpdate();

		ResultSet rskey = null;
		rskey = ps.getGeneratedKeys();

		if (rskey != null && rskey.next()) {
			obj.getHead().getsTBDevice().getResources().get(i).setIdResource(
					rskey.getInt(1));
		}

		ps.close();
		con.close();
	}

	public static void Persist(Watch_Tv obj, int i, int j) throws SQLException {
		Connection con = Conexao.getConnection();
		PreparedStatement ps = con.prepareStatement(
				"insert into resource(Device_idDevice,Type_) values (?,?)",
				Statement.RETURN_GENERATED_KEYS);

		ps.setInt(1, obj.getHead().getsTBDevice().getDevices().get(i)
				.getIdDevice());
		ps.setString(2, obj.getHead().getsTBDevice().getDevices().get(i)
				.getResources().get(j).getType());

		ps.executeUpdate();

		ResultSet rskey = null;
		rskey = ps.getGeneratedKeys();

		if (rskey != null && rskey.next()) {
			obj.getHead().getsTBDevice().getDevices().get(i).getResources()
					.get(j).setIdResource(rskey.getInt(1));
		}

		ps.close();
		con.close();
	}
}
