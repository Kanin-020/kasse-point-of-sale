package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.data.User;
import com.utils.DatabaseConnection;

public class UserDAO {

    private static Connection connection = DatabaseConnection.getInstance().getConnection();

    public static int add(User user) throws SQLException {

        String query = "INSERT INTO user_table(username, password, position) VALUES (?,?,?)";

        PreparedStatement statement = connection.prepareStatement(query);

        statement.setString(1, user.getUsername());
        statement.setString(2, user.getPassword());
        statement.setString(3, user.getPosition());

        return statement.executeUpdate();
    }

    public static ArrayList<User> select(User user) throws SQLException {

        ArrayList<User> userList = new ArrayList<>();

        String query = "SELECT * FROM user_table WHERE username = ? AND password = ?";

        PreparedStatement statement = connection.prepareStatement(query);

        statement.setString(1, user.getUsername());
        statement.setString(2, user.getPassword());

        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {

            user = new User(
                    resultSet.getString("username"),
                    resultSet.getString("password"),
                    resultSet.getString("position"));
            userList.add(user);
        }


        return userList;
    }

    public static ArrayList<User> selectAll() throws SQLException {

        ArrayList<User> userList = new ArrayList<>();
    
        String query = "SELECT * FROM user_table";
    
        PreparedStatement statement = connection.prepareStatement(query);
        ResultSet resultSet = statement.executeQuery();
    
        while (resultSet.next()) {
            User user = new User(
                    resultSet.getString("username"),
                    resultSet.getString("password"),
                    resultSet.getString("position"));
            userList.add(user);
        }
    
        return userList;
    }
    

    public static int modify(User user) throws SQLException {

        String query = "UPDATE user_table SET password = ?, position = ? WHERE username = ?";

        PreparedStatement statement = connection.prepareStatement(query);

        statement.setString(1, user.getPassword());
        statement.setString(2, user.getPosition());
        statement.setString(3, user.getUsername());

        return statement.executeUpdate();
    }

    public static int delete(User user) throws SQLException {

        String query = "DELETE FROM user_table WHERE username = ?";

        PreparedStatement statement = connection.prepareStatement(query);

        statement.setString(1, user.getUsername());

        return statement.executeUpdate();
    }

}
