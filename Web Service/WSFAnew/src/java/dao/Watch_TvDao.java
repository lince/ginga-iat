/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entidades.Watch_Tv;
import entidades.head.STBDevice;

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
public class Watch_TvDao {

	public static void Persist(Watch_Tv obj) throws SQLException, ClassNotFoundException {
		// Realizar testes futuros para garantir que o id retornado � o correto
		// para o uso de threads
		Connection con = Conexao.getConnection();
		PreparedStatement ps = con
				.prepareStatement(
						"insert into Watch_TV(StartDate,EndDate,country_idCountry,FlagETL) values (?,?,?,?)",
						Statement.RETURN_GENERATED_KEYS);

		ps.setTimestamp(1, obj.getStartDate());
		ps.setTimestamp(2, obj.getEndDate());
		ps.setInt(3, obj.getCountry().getId());
                ps.setBoolean(4, false);

		ps.executeUpdate();

		ResultSet rskey = null;
		rskey = ps.getGeneratedKeys();

		if (rskey != null && rskey.next()) {
			obj.setIdWatch_Tv(rskey.getInt(1));
		}
		ps.close();
		con.close();

	}

}
