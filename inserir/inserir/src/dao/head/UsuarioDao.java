package dao.head;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import utilidades.Conexao;
import entidades.Country;
import entidades.Watch_Tv;
import entidades.head.Usuario;

public class UsuarioDao {
	public static void Persist(Watch_Tv obj) throws SQLException {
		Connection con = Conexao.getConnection();
		PreparedStatement ps = con
				.prepareStatement(
						"insert into user_(Genre,Birth,Identification) values (?,?,?)",
						Statement.RETURN_GENERATED_KEYS);

		ps.setString(1, String.valueOf(obj.getHead().getUsuario().getGenre()));
		ps.setDate(2, obj.getHead().getUsuario().getBirth());
		ps.setString(3, obj.getHead().getUsuario().getIdentification());

		ps.executeUpdate();

		ResultSet rskey = null;
		rskey = ps.getGeneratedKeys();

		if (rskey != null && rskey.next()) {
			obj.getHead().getUsuario().setIdUsuario(rskey.getInt(1));
		}

		ps.close();
		con.close();
	}

	public static Usuario Search(String identification) throws SQLException {
		Connection con = Conexao.getConnection();
		PreparedStatement ps = null;
		try {
			ps = con
					.prepareStatement("select * from user_ where Identification = ?");

			ps.setString(1, identification);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				Usuario usuario = new Usuario();
				usuario.setIdUsuario(rs.getInt("idUser"));
				usuario.setGenre(rs.getString("Genre").charAt(0));
				usuario.setBirth(rs.getDate("Birth"));
				usuario.setIdentification(rs.getString("Identification"));

				return usuario;
			} else {
				return null;
			}
		} finally {
			ps.close();
			con.close();
		}
	}
}
