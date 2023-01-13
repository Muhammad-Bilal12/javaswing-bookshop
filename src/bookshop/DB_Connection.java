/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookshop;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author wajiz.pk
 */
public class DB_Connection {

    Connection con;
    Statement st;
    ResultSet rs;

    public DB_Connection() {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookshop", "root", "");
            st = con.createStatement();
            System.out.println("Db is connected");

        } catch (Exception e) {

            System.out.println(e);
        }
    }

    public ResultSet getBooks() {

        try {
            String sql = "select * from booksdata";
            rs = st.executeQuery(sql);
            return rs;
        } catch (SQLException ex) {
            Logger.getLogger(DB_Connection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }

    public ResultSet searchBook(String search) {

        try {
            String sql = "Select * from booksdata where id like'" + search + "%' OR name like '" + search + "%'";
            rs = st.executeQuery(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DB_Connection.class.getName()).log(Level.SEVERE, null, ex);
        }

        return rs;
    }

    public void addBook(String bookname, String edition, String price) {
        try {
            String sql = "INSERT INTO booksdata(name, edition, price) VALUES ('" + bookname + "','" + edition + "','" + price + "')";
            st.executeUpdate(sql);
            System.out.println("Data added!!!");
        } catch (SQLException ex) {
            Logger.getLogger(DB_Connection.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public int deleteData(String id) {
        int status = 0;
        try {
            String sql = "delete from booksdata where id =" + id;
            status = st.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DB_Connection.class.getName()).log(Level.SEVERE, null, ex);
        }

        return status;

    }

    public int updateData(String id, String name, String edition, String price) {
        int status = 0;
        try {
            String sql = "UPDATE booksdata SET name ='" + name + "',edition='" + edition + "',price ='" + price + "' Where id=" + id;
            status = st.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DB_Connection.class.getName()).log(Level.SEVERE, null, ex);
        }

        return status;
    }

    public static void main(String[] args) {
        DB_Connection db = new DB_Connection();
    }

}
