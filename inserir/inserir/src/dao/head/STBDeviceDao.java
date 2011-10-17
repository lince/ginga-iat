package dao.head;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import utilidades.Conexao;
import entidades.Country;
import entidades.Watch_Tv;
import entidades.head.STBDevice;

public class STBDeviceDao {
	public static void Persist(Watch_Tv obj) throws SQLException {
		Connection con = Conexao.getConnection();
		PreparedStatement ps = con
				.prepareStatement(
						"insert into stb_device(Profile,Type_,SerialNumber) values (?,?,?)",
						Statement.RETURN_GENERATED_KEYS);

		ps.setString(1, obj.getHead().getsTBDevice().getProfile());
		ps.setString(2, obj.getHead().getsTBDevice().getType());
		ps.setString(3, obj.getHead().getsTBDevice().getSerialNumber());

		ps.executeUpdate();

		ResultSet rskey = null;
		rskey = ps.getGeneratedKeys();

		if (rskey != null && rskey.next()) {
			obj.getHead().getsTBDevice().setIdSTB_Device(rskey.getInt(1));
		}

		ps.close();
		con.close();
	}

	public static STBDevice Search(String serial) throws SQLException {
		Connection con = Conexao.getConnection();
		PreparedStatement ps = null;
		try {
			ps = con
					.prepareStatement("select * from stb_device where SerialNumber = ?");

			ps.setString(1, serial);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				STBDevice stbDevice = new STBDevice();
				stbDevice.setIdSTB_Device(rs.getInt("idSTB_Device"));
				stbDevice.setProfile(rs.getString("Profile"));
				stbDevice.setType(rs.getString("Type_"));
				stbDevice.setSerialNumber(rs.getString("SerialNumber"));
				return stbDevice;
			} else {
				return null;
			}
		} finally {
			ps.close();
			con.close();
		}
	}

}
