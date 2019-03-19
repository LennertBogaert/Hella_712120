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
import java.util.logging.Level;
import java.util.logging.Logger;


public class DBConnect {
    
    String host = "jdbc:derby://localhost:1527/Hella";
    String user_name = "Lennert";
    String user_pass = "admin";
    Connection con;
    Statement stmt;
    ResultSet rs;
    String SQL;
    
    DBConnect(){
        try{
            con = DriverManager.getConnection(host, user_name, user_pass);
        }
        catch(SQLException err){
            System.out.println(err.getMessage());
        }
    }
    
    public int get_min_endpoint(){
        Statement stmt;
        int min_value = 0;
        
        try {
            stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            String SQL = "SELECT*FROM HELLA_ACTUATORS WHERE G_NUMBER = 125";
            ResultSet rs = stmt.executeQuery(SQL);
            rs.next();
            //int g_number = rs.getInt("G_NUMBER");
            min_value = rs.getInt("MIN_VALUE");
            //rs.updateInt("MIN_VALUE", 50);
            //rs.updateRow();
            //System.out.println(rs.getInt("MIN_VALUE"));
        } catch (SQLException ex) {
            Logger.getLogger(DBConnect.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return min_value;
    }
    
    public void save_point(int x, int y){
        Statement stmt;
        
        try {
            stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            String SQL = "SELECT*FROM GRAPH";
            ResultSet rs = stmt.executeQuery(SQL);
            rs.moveToInsertRow();
            rs.updateInt("X", x);
            rs.updateInt("Y", y);
            rs.insertRow();
            stmt.close();
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(DBConnect.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public int get_x(){
        int x = 0;
        try {
            rs.next();
            x = rs.getInt("X");
            
        } catch (SQLException ex) {
            Logger.getLogger(DBConnect.class.getName()).log(Level.SEVERE, null, ex);
        }
        return x;
    }
    
    public int get_y(){
        int y = 0;
        try {
            rs.next();
            y = rs.getInt("Y");
            
        } catch (SQLException ex) {
            Logger.getLogger(DBConnect.class.getName()).log(Level.SEVERE, null, ex);
        }
        return y;
    }
    
    public void open_database(){
        
        try {
            
            stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            SQL = "SELECT * FROM GRAPH";
            rs = stmt.executeQuery(SQL);
            rs.first();
        } catch (SQLException ex) {
            Logger.getLogger(DBConnect.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void clear(){
        try {
            stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            SQL = "DELETE FROM GRAPH WHERE 1=1";
            stmt.executeUpdate(SQL);
        } catch (SQLException ex) {
            Logger.getLogger(DBConnect.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
