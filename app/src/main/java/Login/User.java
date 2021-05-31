package main.java.Login;

import main.java.jdbc.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class User {

    private boolean CheckUser(String username, String password) throws SQLException, ClassNotFoundException {
        Connection connection = ConnectionUtils.getMyConnection();
        String sql = "select username, password from user where username = ? and password = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, username);
        statement.setString(2, password);
        try {
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                System.out.println("username : " + rs.getString("username"));
                System.out.println("password : " + rs.getString("password"));
                connection.close();
                return true;
            } else {
                connection.close();
                return false;
            }
        } catch (SQLException throwables) {
            System.out.println(throwables);
            connection.close();
            return false;
        }
    }

    private boolean CreateUser(String username, String password) throws SQLException, ClassNotFoundException {
        Connection connection = ConnectionUtils.getMyConnection();
        String sql = "INSERT into user (username, password) VALUES(?,?);";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, username);
        statement.setString(2, password);
        try {
            int rowCount = statement.executeUpdate();
            System.out.println("Row Count affected = " + rowCount);
            connection.close();
            return true;
        } catch (SQLException throwables) {
            System.out.println(throwables);
            connection.close();
            return false;
        }
    }

    private boolean DeleteUser(String username) throws SQLException, ClassNotFoundException {
        Connection connection = ConnectionUtils.getMyConnection();
        String sql = "DELETE FROM user WHERE username=?;";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, username);
        try {
            int rowCount = statement.executeUpdate();
            System.out.println("Row Count affected = " + rowCount);
            connection.close();
            if (rowCount != 0) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException throwables) {
            System.out.println(throwables);
            connection.close();
            return false;
        }
    }

    public boolean SignUp(String username, String password) throws SQLException, ClassNotFoundException {
        if (CreateUser(username, password)) {
            System.out.println("Them tai khoan thanh cong");
            return true;
        } else {
            System.out.println("Tao tai khoan that bai");
            return false;
        }
    }


    public boolean SignIn(String username, String password) throws SQLException, ClassNotFoundException {
        if (CheckUser(username, password)) {
            System.out.println("Dang nhap thanh cong");
            return true;
        } else {
            System.out.println("Dang nhap that bai");
            return false;
        }
    }

    public boolean Delete(String username) throws SQLException, ClassNotFoundException {
        if (DeleteUser(username)) {
            System.out.println("Xoa thanh cong");
            return true;
        } else {
            System.out.println("Xoa that bai - khong thay user ");
            return false;
        }
    }
}
