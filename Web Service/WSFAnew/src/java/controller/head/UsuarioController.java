package controller.head;

import java.sql.SQLException;

import dao.CountryDao;
import dao.head.UsuarioDao;
import entidades.Country;
import entidades.Watch_Tv;
import entidades.head.Usuario;

public class UsuarioController {

    public void Persist(Watch_Tv obj) throws ClassNotFoundException, SQLException {
        Usuario usuario = Search(obj.getHead().getUsuario().getIdentification());
        try {
            if (usuario == null) {
                UsuarioDao.Persist(obj);
            } else {
                obj.getHead().getUsuario().setIdUsuario(usuario.getIdUsuario());
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public Usuario Search(String identification) throws SQLException, ClassNotFoundException {
        Usuario usuario = new Usuario();
        usuario = UsuarioDao.Search(identification);
        return usuario;
    }
}
