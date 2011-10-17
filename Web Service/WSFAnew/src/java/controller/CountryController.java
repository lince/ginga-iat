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

    public void Persist(Watch_Tv obj) throws SQLException, ClassNotFoundException {
        Country country2 = Search(obj.getCountry().getName());
        if (country2 == null) {
            CountryDao.Persist(obj);
        }
    }

    public Country Search(String nome) throws SQLException, ClassNotFoundException {
        Country country = new Country();
        country = CountryDao.Search(nome);
        return country;
    }
}
