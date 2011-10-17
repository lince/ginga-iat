package dao.nclStateMachine;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import utilidades.Conexao;
import entidades.Watch_Tv;

public class ContextDao {
	public static void Persist(Watch_Tv obj, int i, int j, int k, int l)
			throws SQLException {
		Connection con = Conexao.getConnection();
		PreparedStatement ps = con.prepareStatement(
				"insert into context(Context_idContext,id) values (?,?)",
				Statement.RETURN_GENERATED_KEYS);

		ps.setInt(1, obj.getIteractions().get(i).getNclMachine().getDocuments()
				.get(j).getContexts().get(k).getIdContext());
		ps.setString(2, obj.getIteractions().get(i).getNclMachine()
				.getDocuments().get(j).getContexts().get(k).getContexts()
				.get(l).getId());

		ps.executeUpdate();

		ResultSet rskey = null;
		rskey = ps.getGeneratedKeys();

		if (rskey != null && rskey.next()) {
			obj.getIteractions().get(i).getNclMachine().getDocuments().get(j)
					.getContexts().get(k).getContexts().get(l).setIdContext(
							rskey.getInt(1));
		}

		ps.close();
		con.close();
	}

	public static void Persist(Watch_Tv obj, int i, int j, int k)
			throws SQLException {
		Connection con = Conexao.getConnection();
		PreparedStatement ps = con.prepareStatement(
				"insert into context(document_iddocument,id) values (?,?)",
				Statement.RETURN_GENERATED_KEYS);

		ps.setInt(1, obj.getIteractions().get(i).getNclMachine().getDocuments()
				.get(j).getIddocument());
		ps.setString(2, obj.getIteractions().get(i).getNclMachine()
				.getDocuments().get(j).getContexts().get(k).getId());

		ps.executeUpdate();

		ResultSet rskey = null;
		rskey = ps.getGeneratedKeys();

		if (rskey != null && rskey.next()) {
			obj.getIteractions().get(i).getNclMachine().getDocuments().get(j)
					.getContexts().get(k).setIdContext(rskey.getInt(1));
		}

		ps.close();
		con.close();
	}
}
