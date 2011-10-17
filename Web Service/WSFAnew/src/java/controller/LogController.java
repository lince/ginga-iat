package controller;

import java.sql.SQLException;

import dao.LogDao;

import entidades.Watch_Tv;

public class LogController {

    public void Persist(Watch_Tv obj) throws SQLException, ClassNotFoundException {
      
            LogDao.Persist(obj);
      
    }
}
