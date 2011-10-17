package controller.iteraction;

import java.sql.SQLException;

import dao.iteraction.ChannelDao;
import dao.iteraction.KeyDao;
import entidades.Watch_Tv;
import entidades.iteraction.Channel;
import entidades.iteraction.Key;

public class ChannelController {

    public void Persist(Watch_Tv obj) {
        for (int i = 0; i < obj.getIteractions().size(); i++) {
            Channel channel = Search(obj.getIteractions().get(i).getProgram().getChannel());
            try {
                if (channel == null) {
                    ChannelDao.Persist(obj, i);
                } else {
                    obj.getIteractions().get(i).getProgram().getChannel().setIdChannel(
                            channel.getIdChannel());
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

    }

    public Channel Search(Channel channel) {
        Channel c = new Channel();
        try {
            c = ChannelDao.Search(channel);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return c;
    }
}
