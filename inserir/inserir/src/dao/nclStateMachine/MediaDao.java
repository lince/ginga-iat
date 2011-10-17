package dao.nclStateMachine;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import utilidades.Conexao;
import entidades.Watch_Tv;

public class MediaDao {
	public static void Persist(Watch_Tv obj, int i, int j, int k, int l)
			throws SQLException {
		Connection con = Conexao.getConnection();
		PreparedStatement ps = con
				.prepareStatement(
						"insert into media(Context_idContext,id,Status_,Time) values (?,?,?,?)",
						Statement.RETURN_GENERATED_KEYS);

		ps.setInt(1, obj.getIteractions().get(i).getNclMachine().getDocuments()
				.get(j).getContexts().get(k).getIdContext());
		ps.setString(2, obj.getIteractions().get(i).getNclMachine()
				.getDocuments().get(j).getContexts().get(k).getMedias().get(l)
				.getId());
		ps.setString(3, obj.getIteractions().get(i).getNclMachine()
				.getDocuments().get(j).getContexts().get(k).getMedias().get(l)
				.getStatus());
		ps.setTime(4, obj.getIteractions().get(i).getNclMachine()
				.getDocuments().get(j).getContexts().get(k).getMedias().get(l)
				.getTime());

		ps.executeUpdate();

		ResultSet rskey = null;
		rskey = ps.getGeneratedKeys();

		if (rskey != null && rskey.next()) {
			obj.getIteractions().get(i).getNclMachine().getDocuments().get(j)
					.getContexts().get(k).getMedias().get(l).setIdMedia(
							rskey.getInt(1));
		}

		ps.close();
		con.close();
	}

	public static void Persist(Watch_Tv obj, int i, int j, int k, int l, int k2)
			throws SQLException {
		Connection con = Conexao.getConnection();
		PreparedStatement ps = con
				.prepareStatement(
						"insert into media(Context_idContext,id,Status_,Time) values (?,?,?,?)",
						Statement.RETURN_GENERATED_KEYS);

		ps.setInt(1, obj.getIteractions().get(i).getNclMachine().getDocuments()
				.get(j).getContexts().get(k).getContexts().get(k2)
				.getIdContext());
		ps.setString(2, obj.getIteractions().get(i).getNclMachine()
				.getDocuments().get(j).getContexts().get(k).getContexts().get(
						k2).getMedias().get(l).getId());
		ps.setString(3, obj.getIteractions().get(i).getNclMachine()
				.getDocuments().get(j).getContexts().get(k).getContexts().get(
						k2).getMedias().get(l).getStatus());
		ps.setTime(4, obj.getIteractions().get(i).getNclMachine()
				.getDocuments().get(j).getContexts().get(k).getContexts().get(
						k2).getMedias().get(l).getTime());

		ps.executeUpdate();

		ResultSet rskey = null;
		rskey = ps.getGeneratedKeys();

		if (rskey != null && rskey.next()) {
			obj.getIteractions().get(i).getNclMachine().getDocuments().get(j)
					.getContexts().get(k).getContexts().get(k2).getMedias()
					.get(l).setIdMedia(rskey.getInt(1));
		}

		ps.close();
		con.close();
	}

	public static void Persist(Watch_Tv obj, int i, int j, int l)
			throws SQLException {
		Connection con = Conexao.getConnection();
		PreparedStatement ps = con
				.prepareStatement(
						"insert into media(document_iddocument,id,Status_,Time) values (?,?,?,?)",
						Statement.RETURN_GENERATED_KEYS);

		ps.setInt(1, obj.getIteractions().get(i).getNclMachine().getDocuments()
				.get(j).getIddocument());
		ps.setString(2, obj.getIteractions().get(i).getNclMachine()
				.getDocuments().get(j).getMedias().get(l).getId());
		ps.setString(3, obj.getIteractions().get(i).getNclMachine()
				.getDocuments().get(j).getMedias().get(l).getStatus());
		ps.setTime(4, obj.getIteractions().get(i).getNclMachine()
				.getDocuments().get(j).getMedias().get(l).getTime());

		ps.executeUpdate();

		ResultSet rskey = null;
		rskey = ps.getGeneratedKeys();

		if (rskey != null && rskey.next()) {
			obj.getIteractions().get(i).getNclMachine().getDocuments().get(j)
					.getMedias().get(l).setIdMedia(rskey.getInt(1));
		}

		ps.close();
		con.close();
	}
}
