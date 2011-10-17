/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entidades.Country;
import entidades.Watch_Tv;

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
public class CountryDao {

	public static void Persist(Watch_Tv obj) throws SQLException {
		Connection con = Conexao.getConnection();
		PreparedStatement ps = con.prepareStatement(
				"insert into Country(Name) values (?)",
				Statement.RETURN_GENERATED_KEYS);

		ps.setString(1, obj.getCountry().getName());

		ps.executeUpdate();

		ResultSet rskey = null;
		rskey = ps.getGeneratedKeys();

		if (rskey != null && rskey.next()) {
			obj.getCountry().setId(rskey.getInt(1));
		}

		ps.close();
		con.close();
	}

	public static Country Search(String nome) throws SQLException {
		Connection con = Conexao.getConnection();
		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement("select * from Country where Name = ?");

			ps.setString(1, nome);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				Country country = new Country();
				country.setId(rs.getInt("idCountry"));
				country.setName(rs.getString("Name"));
				return country;
			} else {
				return null;
			}
		} finally {
			ps.close();
			con.close();
		}
	}
}
