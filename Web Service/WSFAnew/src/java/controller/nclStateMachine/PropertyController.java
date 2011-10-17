package controller.nclStateMachine;

import java.sql.SQLException;

import dao.nclStateMachine.PropertyDao;

import entidades.Watch_Tv;

public class PropertyController {

    public void Persist(Watch_Tv obj) throws SQLException, ClassNotFoundException {
        for (int i = 0; i < obj.getIteractions().size(); i++) {
            if (obj.getIteractions().get(i).getNclMachine() != null) {
                for (int j = 0; j < obj.getIteractions().get(i).getNclMachine().getDocuments().size(); j++) {
                    // media do document
                    for (int j2 = 0; j2 < obj.getIteractions().get(i).getNclMachine().getDocuments().get(j).getMedias().size(); j2++) {
                        for (int k = 0; k < obj.getIteractions().get(i).getNclMachine().getDocuments().get(j).getMedias().get(j2).getProperties().size(); k++) {
                            PropertyDao.Persist(obj.getIteractions().get(i).getNclMachine().getDocuments().get(j).getMedias().get(j2), k);
                        }
                    }
                    // media do context1
                    for (int j2 = 0; j2 < obj.getIteractions().get(i).getNclMachine().getDocuments().get(j).getContexts().size(); j2++) {
                        for (int k = 0; k < obj.getIteractions().get(i).getNclMachine().getDocuments().get(j).getContexts().get(j2).getMedias().size(); k++) {
                            for (int k2 = 0; k2 < obj.getIteractions().get(i).getNclMachine().getDocuments().get(j).getContexts().get(j2).getMedias().get(k).getProperties().size(); k2++) {
                                PropertyDao.Persist(obj.getIteractions().get(i).getNclMachine().getDocuments().get(j).getContexts().get(j2).getMedias().get(
                                        k), k2);
                            }
                        }
                        // media do context2
                        for (int k = 0; k < obj.getIteractions().get(i).getNclMachine().getDocuments().get(j).getContexts().get(j2).getContexts().size(); k++) {
                            for (int k2 = 0; k2 < obj.getIteractions().get(i).getNclMachine().getDocuments().get(j).getContexts().get(j2).getContexts().get(k).getMedias().size(); k2++) {
                                for (int l = 0; l < obj.getIteractions().get(i).getNclMachine().getDocuments().get(j).getContexts().get(j2).getContexts().get(k).getMedias().get(k2).getProperties().size(); l++) {
                                    PropertyDao.Persist(obj.getIteractions().get(i).getNclMachine().getDocuments().get(j).getContexts().get(j2).getContexts().get(k).getMedias().get(k2), l);
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
