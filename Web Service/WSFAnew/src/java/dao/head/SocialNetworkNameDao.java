package dao.head;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import utilidades.Conexao;
import entidades.Country;
import entidades.Watch_Tv;
import entidades.head.SocialNetworkName;

public class SocialNetworkNameDao {
	public static void Persist(Watch_Tv obj, int i) throws SQLException, ClassNotFoundException {
		Connection con = Conexao.getConnection();
		PreparedStatement ps = con.prepareStatement(
				"insert into socialnetworkname(Name) values (?)",
				Statement.RETURN_GENERATED_KEYS);

		ps.setString(1, obj.getHead().getUsuario().getSocialNetworks().get(i)
				.getName().getName());

		ps.executeUpdate();

		ResultSet rskey = null;
		rskey = ps.getGeneratedKeys();

		if (rskey != null && rskey.next()) {
			obj.getHead().getUsuario().getSocialNetworks().get(i).getName()
					.setIdSocialNetworkName(rskey.getInt(1));
		}

		ps.close();
		con.close();
	}

	public static SocialNetworkName Search(String nome) throws SQLException, ClassNotFoundException {
		Connection con = Conexao.getConnection();
		PreparedStatement ps = null;
		try {
			ps = con
					.prepareStatement("select * from socialnetworkname where Name = ?");

			ps.setString(1, nome);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				SocialNetworkName s = new SocialNetworkName();
				s.setIdSocialNetworkName(rs.getInt("idSocialNetWorkName"));
				s.setName(rs.getString("Name"));
				return s;
			} else {
				return null;
			}
		} finally {
			ps.close();
			con.close();
		}
	}
}
