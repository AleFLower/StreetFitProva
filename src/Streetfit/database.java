/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Streetfit;

import java.sql.*;

/**
 *
 * @author aless
 */
public class database {
    
    public static Connection connectDb(){
        try{
            
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Streetfitprova","root","Fiorellino3");
            
            return conn;
            
        }catch(Exception e){
            e.printStackTrace();
    
        }
        return null;
    }
    
}
