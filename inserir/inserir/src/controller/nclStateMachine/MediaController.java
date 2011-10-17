package controller.nclStateMachine;

import java.sql.SQLException;

import dao.nclStateMachine.MediaDao;
import entidades.Watch_Tv;

public class MediaController {

    public void Persist(Watch_Tv obj) {
        for (int i = 0; i < obj.getIteractions().size(); i++) {
            if (obj.getIteractions().get(i).getNclMachine() != null) {
                for (int j = 0; j < obj.getIteractions().get(i).getNclMachine().getDocuments().size(); j++) {
                    for (int k = 0; k < obj.getIteractions().get(i).getNclMachine().getDocuments().get(j).getContexts().size(); k++) {
                        // context 1
                        for (int l = 0; l < obj.getIteractions().get(i).getNclMachine().getDocuments().get(j).getContexts().get(k).getMedias().size(); l++) {
                            try {
                                MediaDao.Persist(obj, i, j, k, l);
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                        }
                        // context2
                        for (int k2 = 0; k2 < obj.getIteractions().get(i).getNclMachine().getDocuments().get(j).getContexts().get(k).getContexts().size(); k2++) {
                            for (int l = 0; l < obj.getIteractions().get(i).getNclMachine().getDocuments().get(j).getContexts().get(k).getContexts().get(k2).getMedias().size(); l++) {
                                try {
                                    MediaDao.Persist(obj, i, j, k, l, k2);
                                } catch (SQLException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }
                    for (int l = 0; l < obj.getIteractions().get(i).getNclMachine().getDocuments().get(j).getMedias().size(); l++) {
                        // media direto do documento
                        try {
                            MediaDao.Persist(obj, i, j, l);
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }
}
