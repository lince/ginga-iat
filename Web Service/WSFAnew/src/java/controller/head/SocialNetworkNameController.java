package controller.head;

import java.sql.SQLException;

import dao.CountryDao;
import dao.head.SocialNetworkDao;
import dao.head.SocialNetworkNameDao;
import entidades.Country;
import entidades.Watch_Tv;
import entidades.head.SocialNetworkName;

public class SocialNetworkNameController {

    public void Persist(Watch_Tv obj) throws ClassNotFoundException, SQLException {
        for (int i = 0; i < obj.getHead().getUsuario().getSocialNetworks().size(); i++) {
            SocialNetworkName s = Search(obj.getHead().getUsuario().getSocialNetworks().get(i).getName().getName());
            try {
                if (s == null) {
                    SocialNetworkNameDao.Persist(obj, i);
                } else {
                    obj.getHead().getUsuario().getSocialNetworks().get(i).getName().setIdSocialNetworkName(
                            s.getIdSocialNetworkName());
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    public SocialNetworkName Search(String nome) throws SQLException, ClassNotFoundException {
        SocialNetworkName s = new SocialNetworkName();
        s = SocialNetworkNameDao.Search(nome);
        return s;
    }
}
