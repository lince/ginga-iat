package dao.iteraction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import utilidades.Conexao;
import entidades.Watch_Tv;
import entidades.iteraction.Program;

public class ProgramDao {

    public static void Persist(Watch_Tv obj, int i) throws SQLException, ClassNotFoundException {
        Connection con = Conexao.getConnection();
        PreparedStatement ps = con.prepareStatement(
                "insert into program(Channel_idChannel,Age,Genre,Code,Name,SubGenre) values (?,?,?,?,?,?)",
                Statement.RETURN_GENERATED_KEYS);
        ps.setInt(1, obj.getIteractions().get(i).getProgram().getChannel().getIdChannel());
        ps.setInt(2, obj.getIteractions().get(i).getProgram().getAge());
        ps.setString(3, obj.getIteractions().get(i).getProgram().getGenre());
        ps.setInt(4, obj.getIteractions().get(i).getProgram().getCode());
        ps.setString(5, obj.getIteractions().get(i).getProgram().getName());
        ps.setString(6, obj.getIteractions().get(i).getProgram().getSubgenre());

        ps.executeUpdate();

        ResultSet rskey = null;
        rskey = ps.getGeneratedKeys();

        if (rskey != null && rskey.next()) {
            obj.getIteractions().get(i).getProgram().setIdProgram(rskey.getInt(1));
        }

        ps.close();
        con.close();
    }

    public static Program Search(int code) throws SQLException, ClassNotFoundException {
        Connection con = Conexao.getConnection();
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement("select * from program where Code = ?");

            ps.setInt(1, code);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Program program = new Program();
                program.setIdProgram(rs.getInt("idProgram"));
                program.setAge(rs.getInt("Age"));
                program.setCode(rs.getInt("Code"));
                program.setGenre(rs.getString("Genre"));
                program.setName(rs.getString("Name"));
                program.setSubgenre(rs.getString("SubGenre"));
                return program;
            } else {
                return null;
            }
        } finally {
            ps.close();
            con.close();
        }
    }
}
