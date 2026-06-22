package com.booknest.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.booknest.dao.UserDAO;
import com.booknest.model.User;
import com.booknest.util.DBConnection;

public class UserDAOImpl implements UserDAO {

    private Connection connection;

    public UserDAOImpl() {
        connection = DBConnection.getConnection();
    }

    @Override
    public boolean registerUser(User user) {

        boolean status = false;

        String query = "INSERT INTO users(full_name, email, phone, password, " +
                       "address_line1, address_line2, city, state, postal_code, country) " +
                       "VALUES(?,?,?,?,?,?,?,?,?,?)";

        try {

            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, user.getFullName());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getPhone());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.setString(5, user.getAddressLine1());
            preparedStatement.setString(6, user.getAddressLine2());
            preparedStatement.setString(7, user.getCity());
            preparedStatement.setString(8, user.getState());
            preparedStatement.setString(9, user.getPostalCode());
            preparedStatement.setString(10, user.getCountry());

            int rowsAffected = preparedStatement.executeUpdate();

            status = rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return status;
    }

    @Override
    public User loginUser(String email, String password) {

        User user = null;

        String query = "SELECT * FROM users WHERE email = ? AND password = ?";

        try {

            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {

                user = new User();

                user.setUserId(resultSet.getInt("user_id"));
                user.setFullName(resultSet.getString("full_name"));
                user.setEmail(resultSet.getString("email"));
                user.setPhone(resultSet.getString("phone"));
                user.setPassword(resultSet.getString("password"));
                user.setAddressLine1(resultSet.getString("address_line1"));
                user.setAddressLine2(resultSet.getString("address_line2"));
                user.setCity(resultSet.getString("city"));
                user.setState(resultSet.getString("state"));
                user.setPostalCode(resultSet.getString("postal_code"));
                user.setCountry(resultSet.getString("country"));
                user.setCreatedAt(resultSet.getTimestamp("created_at"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }

    @Override
    public User getUserById(int userId) {

        User user = null;

        String query = "SELECT * FROM users WHERE user_id = ?";

        try {

            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setInt(1, userId);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {

                user = new User();

                user.setUserId(resultSet.getInt("user_id"));
                user.setFullName(resultSet.getString("full_name"));
                user.setEmail(resultSet.getString("email"));
                user.setPhone(resultSet.getString("phone"));
                user.setPassword(resultSet.getString("password"));
                user.setAddressLine1(resultSet.getString("address_line1"));
                user.setAddressLine2(resultSet.getString("address_line2"));
                user.setCity(resultSet.getString("city"));
                user.setState(resultSet.getString("state"));
                user.setPostalCode(resultSet.getString("postal_code"));
                user.setCountry(resultSet.getString("country"));
                user.setCreatedAt(resultSet.getTimestamp("created_at"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }

    @Override
    public User getUserByEmail(String email) {

        User user = null;

        String query = "SELECT * FROM users WHERE email = ?";

        try {

            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, email);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {

                user = new User();

                user.setUserId(resultSet.getInt("user_id"));
                user.setFullName(resultSet.getString("full_name"));
                user.setEmail(resultSet.getString("email"));
                user.setPhone(resultSet.getString("phone"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }

    @Override
    public User getUserByPhone(String phone) {

        User user = null;

        String query = "SELECT * FROM users WHERE phone = ?";

        try {

            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, phone);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {

                user = new User();

                user.setUserId(resultSet.getInt("user_id"));
                user.setFullName(resultSet.getString("full_name"));
                user.setEmail(resultSet.getString("email"));
                user.setPhone(resultSet.getString("phone"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }

    @Override
    public boolean updateUser(User user) {

        boolean status = false;

        String query = "UPDATE users SET full_name=?, email=?, phone=? WHERE user_id=?";

        try {

            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, user.getFullName());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getPhone());
            preparedStatement.setInt(4, user.getUserId());

            int rowsAffected = preparedStatement.executeUpdate();

            status = rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return status;
    }

    @Override
    public boolean updatePassword(int userId, String newPassword) {

        boolean status = false;

        String query = "UPDATE users SET password=? WHERE user_id=?";

        try {

            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, newPassword);
            preparedStatement.setInt(2, userId);

            int rowsAffected = preparedStatement.executeUpdate();

            status = rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return status;
    }

    @Override
    public boolean updateAddress(int userId, String addressLine1,
                                 String addressLine2, String city,
                                 String state, String postalCode,
                                 String country) {

        boolean status = false;

        String query = "UPDATE users SET address_line1=?, address_line2=?, city=?, " +
                       "state=?, postal_code=?, country=? WHERE user_id=?";

        try {

            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, addressLine1);
            preparedStatement.setString(2, addressLine2);
            preparedStatement.setString(3, city);
            preparedStatement.setString(4, state);
            preparedStatement.setString(5, postalCode);
            preparedStatement.setString(6, country);
            preparedStatement.setInt(7, userId);

            int rowsAffected = preparedStatement.executeUpdate();

            status = rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return status;
    }

    @Override
    public boolean deleteUser(int userId) {

        boolean status = false;

        String query = "DELETE FROM users WHERE user_id=?";

        try {

            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setInt(1, userId);

            int rowsAffected = preparedStatement.executeUpdate();

            status = rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return status;
    }

    @Override
    public boolean emailExists(String email) {

        boolean status = false;

        String query = "SELECT * FROM users WHERE email=?";

        try {

            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, email);

            ResultSet resultSet = preparedStatement.executeQuery();

            status = resultSet.next();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return status;
    }

    @Override
    public boolean phoneExists(String phone) {

        boolean status = false;

        String query = "SELECT * FROM users WHERE phone=?";

        try {

            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, phone);

            ResultSet resultSet = preparedStatement.executeQuery();

            status = resultSet.next();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return status;
    }

    @Override
    public List<User> getAllUsers() {

        List<User> users = new ArrayList<>();

        String query = "SELECT * FROM users";

        try {

            PreparedStatement preparedStatement = connection.prepareStatement(query);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                User user = new User();

                user.setUserId(resultSet.getInt("user_id"));
                user.setFullName(resultSet.getString("full_name"));
                user.setEmail(resultSet.getString("email"));
                user.setPhone(resultSet.getString("phone"));

                users.add(user);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }

    @Override
    public boolean addUser(User user) {

        boolean status = false;

        String query =
                "INSERT INTO users "
                + "(full_name, email, phone, password, "
                + "address_line1, address_line2, city, "
                + "state, postal_code, country) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try {

            Connection connection =
                    DBConnection.getConnection();

            PreparedStatement preparedStatement =
                    connection.prepareStatement(query);

            preparedStatement.setString(1, user.getFullName());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getPhone());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.setString(5, user.getAddressLine1());
            preparedStatement.setString(6, user.getAddressLine2());
            preparedStatement.setString(7, user.getCity());
            preparedStatement.setString(8, user.getState());
            preparedStatement.setString(9, user.getPostalCode());
            preparedStatement.setString(10, user.getCountry());

            int rows =
                    preparedStatement.executeUpdate();

            if(rows > 0){
                status = true;
            }

        }
        catch(Exception e){
            e.printStackTrace();
        }

        return status;
    }
}