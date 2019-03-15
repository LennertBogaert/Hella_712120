/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication8;

/**
 *
 * @author Lenne
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class DBConnect {
    
    DBConnect(){
        try{
            String host = "jdbc:derby://localhost:1527/Hella";
            String user_name = "Lennert";
            String user_pass = "admin";

            Connection con = DriverManager.getConnection(host, user_name, user_pass);
            
            Statement stmt = con.createStatement();
            String SQL = "SELECT*FROM HELLA_ACTUATORS";
            ResultSet rs = stmt.executeQuery(SQL);
            rs.next();
            int g_number = rs.getInt("G_NUMBER");
            int min_value = rs.getInt("MIN_VALUE");

            System.out.println(g_number + " " + min_value);
            
        }
        catch(SQLException err){
            System.out.println(err.getMessage());
        }
    }
    
    
}
