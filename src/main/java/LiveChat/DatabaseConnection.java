/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LiveChat;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author lukap
 */
public class DatabaseConnection {
    private Connection conn;
    
    public DatabaseConnection(){
        try {
            conn = DriverManager.getConnection(
                    "jdbc:mysql://remotemysql.com:3306/kO86YXWwmm", "kO86YXWwmm", "fFliBClX9p"
            );
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Cant connect to database");
            Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void addUser(String _username, String _email, String _password, String _name, String _lastname){
        try {
            String sql = "INSERT INTO users(username, password, name, lastname, email) VALUES (?,?,?,?,?)";
            PreparedStatement st = (PreparedStatement) conn.prepareStatement(sql);
            st.setString(1, _username);
            st.setString(2, _password);
            st.setString(3, _name);
            st.setString(4, _lastname);
            st.setString(5, _email);
            st.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,ex.getMessage());
            Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public User getUserId(String _username, String _password){
        User u;
        try{
            String sql = "SELECT id, name, lastname, email, username FROM users WHERE (username = ? OR email = ?) AND password = ?";
            PreparedStatement st = (PreparedStatement) conn.prepareStatement(sql);
            st.setString(1, _username);
            st.setString(2, _username);
            st.setString(3, _password);
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                u = new User(rs.getInt(1), rs.getString(2),rs.getString(3),rs.getString(4), rs.getString(5));
                return u;
            }
            
        } catch(SQLException ex){
        }
        return null;
    }
    
    public List<User> getAllContactsThatArentFriends(User u){
        List<User> usr = new ArrayList<User>();
        try{
            String sql = "SELECT u.id, u.name, u.lastname, u.email, u.username FROM users u LEFT OUTER JOIN contacts c ON c.theirContact_id = u.id WHERE ((c.user_id != ? OR c.user_id IS NULL) AND u.id != ?)";
            PreparedStatement st = (PreparedStatement) conn.prepareStatement(sql);
            st.setInt(1, u.GetId());
            st.setInt(2, u.GetId());
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                usr.add(new User(rs.getInt(1), rs.getString(2),rs.getString(3),rs.getString(4), rs.getString(5)));
            }
        } catch(SQLException ex){
            JOptionPane.showMessageDialog(null,ex.getMessage());
            Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return usr;
    }
    
    public List<User> getAllContactsThatAreFriends(User u){
        List<User> usr = new ArrayList<User>();
        try{
            String sql = "SELECT u.id, u.name, u.lastname, u.email, u.username FROM users u INNER JOIN JOIN contacts c ON c.theirContact_id = u.id WHERE (c.user_id = ? AND u.id != ?)";
            PreparedStatement st = (PreparedStatement) conn.prepareStatement(sql);
            st.setInt(1, u.GetId());
            st.setInt(2, u.GetId());
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                usr.add(new User(rs.getInt(1), rs.getString(2),rs.getString(3),rs.getString(4), rs.getString(5)));
            }
        } catch(SQLException ex){
            JOptionPane.showMessageDialog(null,ex.getMessage());
            Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return usr;
    }
    
}
