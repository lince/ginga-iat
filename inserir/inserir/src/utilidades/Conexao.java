/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utilidades;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author samuel
 */
public class Conexao {

    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/TVDI", "root", "root");
        } catch (SQLException ex) {
           ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
        	System.out.println("ferro");
            ex.printStackTrace();
        }
        return null;
    }
}
