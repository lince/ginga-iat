package dao.nclStateMachine;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import utilidades.Conexao;
import entidades.Watch_Tv;

public class DocumentDao {
	public static void Persist(Watch_Tv obj, int i, int j) throws SQLException, ClassNotFoundException {
		Connection con = Conexao.getConnection();
		PreparedStatement ps = con
				.prepareStatement(
						"insert into document(nclStateMachine_idnclStateMachine,id) values (?,?)",
						Statement.RETURN_GENERATED_KEYS);

		ps.setInt(1, obj.getIteractions().get(i).getNclMachine()
				.getIdnclStateMachine());
		ps.setString(2, obj.getIteractions().get(i).getNclMachine()
				.getDocuments().get(j).getId());

		ps.executeUpdate();

		ResultSet rskey = null;
		rskey = ps.getGeneratedKeys();

		if (rskey != null && rskey.next()) {
			obj.getIteractions().get(i).getNclMachine().getDocuments().get(j)
					.setIddocument(rskey.getInt(1));
		}

		ps.close();
		con.close();
	}
}
