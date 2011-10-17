package dao.head;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import utilidades.Conexao;
import entidades.Watch_Tv;

public class DeviceDao {
	public static void Persist(Watch_Tv obj, int i) throws SQLException, ClassNotFoundException {
		Connection con = Conexao.getConnection();
		PreparedStatement ps = con
				.prepareStatement(
						"insert into device(STB_Device_idSTB_Device,serialNumber,Type_,Profile) values (?,?,?,?)",
						Statement.RETURN_GENERATED_KEYS);

		ps.setInt(1, obj.getHead().getsTBDevice().getIdSTB_Device());
		ps.setString(2, obj.getHead().getsTBDevice().getDevices().get(i)
				.getSerialNumber());
		ps.setString(3, obj.getHead().getsTBDevice().getDevices().get(i)
				.getType());
		ps.setString(4, obj.getHead().getsTBDevice().getDevices().get(i)
				.getProfile());

		ps.executeUpdate();

		ResultSet rskey = null;
		rskey = ps.getGeneratedKeys();

		if (rskey != null && rskey.next()) {
			obj.getHead().getsTBDevice().getDevices().get(i).setIdDevice(
					rskey.getInt(1));
		}

		ps.close();
		con.close();
	}
}
