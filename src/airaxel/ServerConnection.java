/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package airaxel;

import java.sql.*;
/**
 *
 * @author axelv
 */
public class ServerConnection extends AirAxel  {
    static String Username;
    static String FinalPasswd;
    static String DatabaseLocation;
   
    //establish connection to sql database
        protected Connection getConnection(String User, String Password, String DBLoc) throws SQLException {
            
            Connection con;
            if(User != null && Password != null && DBLoc != null){
                Username = User;
                FinalPasswd = Password;
                DatabaseLocation = DBLoc;
            }
            con = DriverManager.getConnection(
                    "jdbc:mysql:"+ DatabaseLocation +"/AxelVanHoyweghenAirAxel",Username,FinalPasswd);
            //System.out.println("Connected to database");
            return con;
        }
}
