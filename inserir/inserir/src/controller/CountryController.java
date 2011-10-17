/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.CountryDao;
import entidades.Country;
import entidades.Watch_Tv;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 
 * @author samuel
 */
public class CountryController {

	public void Persist(Watch_Tv obj) {
		Country country2 = Search(obj.getCountry().getName());
		try {
			if (country2 == null) {
				CountryDao.Persist(obj);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	public Country Search(String nome) {
		Country country = new Country();
		try {
			country = CountryDao.Search(nome);
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return country;
	}
}
