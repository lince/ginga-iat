/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.CountryDao;
import dao.Watch_TvDao;
import entidades.Watch_Tv;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 
 * @author samuel
 */
public class Watch_TvController {

	public void Persist(Watch_Tv watch_Tv) throws ClassNotFoundException {
		try {
			try {
				watch_Tv.getCountry().setId(
						new CountryController().Search(
								watch_Tv.getCountry().getName()).getId());
			} catch (Exception e) {
				new CountryController().Persist(watch_Tv);
				watch_Tv.getCountry().setId(
						new CountryController().Search(
								watch_Tv.getCountry().getName()).getId());
			}
			Watch_TvDao.Persist(watch_Tv);
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}
}
