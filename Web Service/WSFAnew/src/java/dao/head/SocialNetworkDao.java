package dao.head;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import utilidades.Conexao;
import entidades.Country;
import entidades.Watch_Tv;
import entidades.head.SocialNetwork;

public class SocialNetworkDao {
	public static void Persist(Watch_Tv obj, int i) throws SQLException, ClassNotFoundException {
		Connection con = Conexao.getConnection();
		PreparedStatement ps = con
				.prepareStatement(
						"insert into socialnetwork(User__idUser,SocialNetWorkName_idSocialNetWorkName,identification) values (?,?,?)",
						Statement.RETURN_GENERATED_KEYS);

		ps.setInt(1, obj.getHead().getUsuario().getIdUsuario());
		ps.setInt(2, obj.getHead().getUsuario().getSocialNetworks().get(i)
				.getName().getIdSocialNetworkName());
		ps.setString(3, obj.getHead().getUsuario().getSocialNetworks().get(i)
				.getIdentification());

		ps.executeUpdate();

		ResultSet rskey = null;
		rskey = ps.getGeneratedKeys();

		if (rskey != null && rskey.next()) {
			obj.getHead().getUsuario().getSocialNetworks().get(i)
					.setIdSocialNetwork(rskey.getInt(1));
		}

		ps.close();
		con.close();
	}
}
