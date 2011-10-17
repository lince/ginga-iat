package dao.iteraction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import utilidades.Conexao;
import entidades.Watch_Tv;
import entidades.iteraction.Volume;

public class VolumeDao {
	public static void Persist(Watch_Tv obj, int i) throws SQLException, ClassNotFoundException {
		Connection con = Conexao.getConnection();
		PreparedStatement ps = con.prepareStatement(
				"insert into volume(Level,Mute) values (?,?)",
				Statement.RETURN_GENERATED_KEYS);

		ps.setInt(1, obj.getIteractions().get(i).getVolume().getLevel());
		ps.setBoolean(2, obj.getIteractions().get(i).getVolume().isMute());

		ps.executeUpdate();

		ResultSet rskey = null;
		rskey = ps.getGeneratedKeys();

		if (rskey != null && rskey.next()) {
			obj.getIteractions().get(i).getVolume()
					.setIdVolume(rskey.getInt(1));
		}

		ps.close();
		con.close();
	}

	public static Volume Search(Volume volume) throws SQLException, ClassNotFoundException {
		Connection con = Conexao.getConnection();
		PreparedStatement ps = null;
		try {
			ps = con
					.prepareStatement("select * from volume where Level = ? AND Mute = ?");
			ps.setInt(1, volume.getLevel());
			ps.setBoolean(2, volume.isMute());

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				Volume v = new Volume();
				v.setIdVolume(rs.getInt("idVolume"));
				v.setLevel(rs.getInt("Level"));
				v.setMute(rs.getBoolean("Mute"));

				return v;
			} else {
				return null;
			}
		} finally {
			ps.close();
			con.close();
		}
	}
}
