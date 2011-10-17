package controller.iteraction;

import java.sql.SQLException;

import dao.iteraction.VolumeDao;
import entidades.Watch_Tv;
import entidades.iteraction.Volume;
import java.util.logging.Level;
import java.util.logging.Logger;

public class VolumeController {

    public void Persist(Watch_Tv obj) {
        for (int i = 0; i < obj.getIteractions().size(); i++) {
            if (obj.getIteractions().get(i).getVolume() != null) {
                Volume volume = Search(obj.getIteractions().get(i).getVolume());
                try {
                    if (volume == null) {
                        VolumeDao.Persist(obj, i);
                    } else {
                        obj.getIteractions().get(i).getVolume().setIdVolume(
                                volume.getIdVolume());
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            } else {
                try {
                    Volume v = new Volume();
                    v.setLevel(0);
                    v.setMute(false);
                    obj.getIteractions().get(i).setVolume(v);
                    VolumeDao.Persist(obj, i);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }

            }
        }

    }

    public Volume Search(Volume volume) {
        Volume v = new Volume();
        try {
            v = VolumeDao.Search(volume);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return v;
    }
}
