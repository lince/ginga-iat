package controller.iteraction;

import java.sql.SQLException;

import dao.CountryDao;
import dao.iteraction.KeyDao;
import entidades.Country;
import entidades.Watch_Tv;
import entidades.iteraction.Key;
import java.util.logging.Level;
import java.util.logging.Logger;

public class KeyController {

    public void Persist(Watch_Tv obj) throws ClassNotFoundException, SQLException {
        for (int i = 0; i < obj.getIteractions().size(); i++) {
            if (obj.getIteractions().get(i).getKey() != null) {
                Key key = Search(obj.getIteractions().get(i).getKey());
                try {
                    if (key == null) {
                        KeyDao.Persist(obj, i);
                    } else {
                        obj.getIteractions().get(i).getKey().setIdKey(key.getIdKey());
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            } else {
                try {
                    Key k = new Key();
                    k.setCode("NULL");
                    k.setAction("NULL");
                    obj.getIteractions().get(i).setKey(k);
                    KeyDao.Persist(obj, i);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    public Key Search(Key key) throws SQLException, ClassNotFoundException {
        Key k = new Key();
        k = KeyDao.Search(key);
        return k;
    }
}
