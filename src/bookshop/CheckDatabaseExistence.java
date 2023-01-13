/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookshop;

/**
 *
 * @author wajiz.pk
 */
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CheckDatabaseExistence {

    
    
    Connection con;
    Statement st;
    ResultSet rs;
    public String name="bookshops";
    
     public void createDB() {
    
//         String DBName = "carservices";
        try {
            String sql = "CREATE DATABASE IF NOT EXISTS " + name;
            st.executeUpdate(sql);
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+name, "root", "");
            st = con.createStatement();
            System.out.println("Database is created");
        } catch (SQLException ex) {
            System.out.println(ex);
//            Logger.getLogger(MyDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String[] args) {
        Connection conn = null;
             CheckDatabaseExistence db = new CheckDatabaseExistence();
        try {
            // Establish a connection to the database
            conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/", "root", "");
            // Get the metadata for the database
            DatabaseMetaData meta = conn.getMetaData();
            // Get the list of databases
            ResultSet rs = meta.getCatalogs();

            // Iterate through the list and check if the database exists
            boolean found = false;
            
            while (rs.next()) {
                String dbName = rs.getString("TABLE_CAT");
                if (dbName.equals(db.name)) {
                    found = true;
                    break;
                }
            }

            if (found) {
                System.out.println("The database '"+db.name+"' exists.");
            } 
            else{
                
                System.out.println("The database '"+db.name+"' not exists.");
           
                db.createDB();
            }
            
        } catch (SQLException e) {
//                if(e == java.sql.SQLSyntaxErrorException: Unknown database 'carservices'){}
            System.out.println(e);
           
        } finally {
            // Close the connection
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
