package dao.iteraction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import utilidades.Conexao;
import entidades.Watch_Tv;
import entidades.iteraction.Channel;
import entidades.iteraction.Volume;

public class ChannelDao {

    public static void Persist(Watch_Tv obj, int i) throws SQLException, ClassNotFoundException {
        Connection con = Conexao.getConnection();
        PreparedStatement ps = con.prepareStatement(
                "insert into channel(Code,Name) values (?,?)",
                Statement.RETURN_GENERATED_KEYS);

        ps.setInt(1, obj.getIteractions().get(i).getProgram().getChannel().getCode());
        ps.setString(2, obj.getIteractions().get(i).getProgram().getChannel().getName());

        ps.executeUpdate();

        ResultSet rskey = null;
        rskey = ps.getGeneratedKeys();

        if (rskey != null && rskey.next()) {
            obj.getIteractions().get(i).getProgram().getChannel().setIdChannel(
                    rskey.getInt(1));
        }

        ps.close();
        con.close();
    }

    public static Channel Search(Channel channel) throws SQLException, ClassNotFoundException {
        Connection con = Conexao.getConnection();
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement("select * from channel where Code = ? AND Name = ?");
            ps.setInt(1, channel.getCode());
            ps.setString(2, channel.getName());

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Channel c = new Channel();
                c.setIdChannel(rs.getInt("idChannel"));
                c.setCode(rs.getInt("Code"));
                c.setName(rs.getString("Name"));

                return c;
            } else {
                return null;
            }
        } finally {
            ps.close();
            con.close();
        }
    }
}
