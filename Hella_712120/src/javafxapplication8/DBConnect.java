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
import java.sql.SQLException;




public class DBConnect {
    public static void main(String[] args){
        try{
            String host = "jdbc:derby://localhost:1527/Hella";
            String user_name = "Lennert";
            String user_pass = "admin";

            Connection con = DriverManager.getConnection(host, user_name, user_pass);
        }
        catch(SQLException err){
            System.out.println(err.getMessage());
        }
        
    }
}
