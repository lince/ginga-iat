package dao.head;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import utilidades.Conexao;
import entidades.Watch_Tv;

public class InformationDao {
	public static void Persist(Watch_Tv obj, int i, int j) throws SQLException, ClassNotFoundException {
		Connection con = Conexao.getConnection();
		PreparedStatement ps = con
				.prepareStatement(
						"insert into information(Resource_idResource,Name,Value) values (?,?,?)",
						Statement.RETURN_GENERATED_KEYS);

		ps.setInt(1, obj.getHead().getsTBDevice().getResources().get(i)
				.getIdResource());
		ps.setString(2, obj.getHead().getsTBDevice().getResources().get(i)
				.getInformations().get(j).getName());
		ps.setString(3, obj.getHead().getsTBDevice().getResources().get(i)
				.getInformations().get(j).getValue());

		ps.executeUpdate();

		ResultSet rskey = null;
		rskey = ps.getGeneratedKeys();

		if (rskey != null && rskey.next()) {
			obj.getHead().getsTBDevice().getResources().get(i)
					.getInformations().get(j).setIdInformation(rskey.getInt(1));
		}

		ps.close();
		con.close();
	}

	public static void Persist(Watch_Tv obj, int i, int j, int k)
			throws SQLException, ClassNotFoundException {
		Connection con = Conexao.getConnection();
		PreparedStatement ps = con
				.prepareStatement(
						"insert into information(Resource_idResource,Name,Value) values (?,?,?)",
						Statement.RETURN_GENERATED_KEYS);

		ps.setInt(1, obj.getHead().getsTBDevice().getDevices().get(k)
				.getResources().get(i).getIdResource());
		ps.setString(2, obj.getHead().getsTBDevice().getDevices().get(k)
				.getResources().get(i).getInformations().get(j).getName());
		ps.setString(3, obj.getHead().getsTBDevice().getDevices().get(k)
				.getResources().get(i).getInformations().get(j).getValue());

		ps.executeUpdate();

		ResultSet rskey = null;
		rskey = ps.getGeneratedKeys();

		if (rskey != null && rskey.next()) {
			obj.getHead().getsTBDevice().getDevices().get(k).getResources()
					.get(i).getInformations().get(j).setIdInformation(
							rskey.getInt(1));
		}

		ps.close();
		con.close();
	}
}
