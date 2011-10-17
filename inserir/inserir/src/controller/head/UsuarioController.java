package controller.head;

import java.sql.SQLException;

import dao.CountryDao;
import dao.head.UsuarioDao;
import entidades.Country;
import entidades.Watch_Tv;
import entidades.head.Usuario;

public class UsuarioController {
	public void Persist(Watch_Tv obj) {
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

	public Usuario Search(String identification) {
		Usuario usuario = new Usuario();
		try {
			usuario = UsuarioDao.Search(identification);
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return usuario;
	}
}
