/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dao.head;

import entidades.Watch_Tv;
import entidades.head.Head;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import utilidades.Conexao;

/**
 * 
 * @author samuel
 */
public class HeadDao {

	public static void Persist(Watch_Tv obj) throws SQLException {
		Connection con = Conexao.getConnection();
		PreparedStatement ps = con
				.prepareStatement(
						"insert into Head(Watch_TV_idWatch_TV,Location_idLocation,STB_Device_idSTB_Device,User__idUser) values (?,?,?,?)",
						Statement.RETURN_GENERATED_KEYS);

		ps.setInt(1, obj.getIdWatch_Tv());
		ps.setInt(2, obj.getHead().getLocation().getIdLocation());
		ps.setInt(3, obj.getHead().getsTBDevice().getIdSTB_Device());
		ps.setInt(4, obj.getHead().getUsuario().getIdUsuario());

		ps.executeUpdate();

		ResultSet rskey = null;
		rskey = ps.getGeneratedKeys();

		if (rskey != null && rskey.next()) {
			obj.getHead().setIdHead(rskey.getInt(1));
		}

		ps.close();
		con.close();
	}

}
