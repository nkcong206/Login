package main.java.Login;
import main.java.jdbc.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CheckUser {
    public boolean SignIn(String username, String password) throws SQLException, ClassNotFoundException {
        Connection connection = ConnectionUtils.getMyConnection();
        String sql = "select username, password from user where username = ? and password = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, username);
        statement.setString(2, password);
        ResultSet rs = statement.executeQuery();

        while (rs.next()) {
            System.out.println("Dang nhap thanh cong!");
            return true;
        }
        System.out.println("Dang nhap that bai!");
        return false;
    }
}
