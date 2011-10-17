package dao.head;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import utilidades.Conexao;
import entidades.Country;
import entidades.Watch_Tv;
import entidades.head.Location;

public class LocationDao {
	public static void Persist(Watch_Tv obj) throws SQLException {
		Connection con = Conexao.getConnection();
		PreparedStatement ps = con
				.prepareStatement("insert into Location(Zip,Latitude,Longitude) values (?,?,?)");

		ps.setInt(1, obj.getHead().getLocation().getZip());
		ps.setDouble(2, obj.getHead().getLocation().getLatitude());
		ps.setDouble(3, obj.getHead().getLocation().getLongitude());

		ps.executeUpdate();

		ps.close();
		con.close();
	}

	public static Location Search(Location location) throws SQLException {
		Connection con = Conexao.getConnection();
		PreparedStatement ps = null;
		try {
			ps = con
					.prepareStatement("select * from Location where Zip = ? AND Latitude = ? AND Longitude = ?");

			ps.setInt(1, location.getZip());
			ps.setDouble(2, location.getLatitude());
			ps.setDouble(3, location.getLongitude());

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				Location l = new Location();
				l.setIdLocation(rs.getInt("idLocation"));
				l.setZip(rs.getInt("Zip"));
				l.setLatitude(rs.getFloat("Latitude"));
				l.setLongitude(rs.getFloat("Longitude"));
				return l;
			} else {
				return null;
			}
		} finally {
			ps.close();
			con.close();
		}
	}
}
