package com.book.model.repository.impl;

import com.book.configuration.JDBConnectionWrapper;
import com.book.model.presentation.User;
import com.book.model.repository.UserRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserRepositoryImpl implements UserRepository {

    private final JDBConnectionWrapper jdbConnectionWrapper;

    public UserRepositoryImpl(JDBConnectionWrapper jdbConnectionWrapper) {
        this.jdbConnectionWrapper = jdbConnectionWrapper;
    }

    @Override
    public boolean create(User user) {

        Connection connection = jdbConnectionWrapper.getConnection();

        try{
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO user(firstName, lastName, address, email, phone, salary, username, password, role) " +
                            "VALUES (?,?,?,?,?,?,?,?,?);"
            );

            preparedStatement.setString(1,user.getFirstName());
            preparedStatement.setString(2,user.getLastName());
            preparedStatement.setString(3,user.getAddress());
            preparedStatement.setString(4,user.getEmail());
            preparedStatement.setString(5,user.getPhone());
            preparedStatement.setDouble(6,user.getSalary());
            preparedStatement.setString(7,user.getUsername());
            preparedStatement.setString(8,user.getPassword());
            preparedStatement.setString(9,user.getRole());

            preparedStatement.execute();

        }catch(SQLException e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean delete(String username) {

        Connection connection = jdbConnectionWrapper.getConnection();

        try{

            PreparedStatement preparedStatement = connection.prepareStatement(
                    "DELETE FROM user WHERE username = ?;"
            );

            preparedStatement.setString(1,username);

            preparedStatement.executeUpdate();

        }catch (SQLException e){
            e.printStackTrace();

            return false;
        }

        return true;
    }

    @Override
    public boolean update(User user) {
        Connection connection = jdbConnectionWrapper.getConnection();

        try{
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "UPDATE user SET " +
                            "firstName = ?, " +
                            "lastName = ?, " +
                            "address = ?, " +
                            "email = ?, " +
                            "phone = ?, " +
                            "salary = ? " +
                            "WHERE username = ?"
            );

            preparedStatement.setString(1, user.getFirstName());
            preparedStatement.setString(2,user.getLastName());
            preparedStatement.setString(3,user.getAddress());
            preparedStatement.setString(4,user.getEmail());
            preparedStatement.setString(5,user.getPhone());
            preparedStatement.setDouble(6,user.getSalary());
            preparedStatement.setString(7,user.getUsername());

            preparedStatement.executeUpdate();

        }catch(SQLException e){
            e.printStackTrace();

            return false;
        }

        return true;
    }

    @Override
    public List<User> findAll() {

        Connection connection = jdbConnectionWrapper.getConnection();

        List<User> users = new ArrayList<User>();

        try{

            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT *FROM user;"
            );

            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()) {

                User user = new User();

                user.setId(resultSet.getInt("id"));
                user.setFirstName(resultSet.getString("firstName"));
                user.setLastName(resultSet.getString("lastName"));
                user.setAddress(resultSet.getString("address"));
                user.setEmail(resultSet.getString("email"));
                user.setPhone(resultSet.getString("phone"));
                user.setSalary(resultSet.getDouble("salary"));
                user.setUsername(resultSet.getString("username"));
                user.setPassword(resultSet.getString("password"));
                user.setRole(resultSet.getString("role"));

                users.add(user);
            }

        }catch(SQLException e){
            e.printStackTrace();
            return null;
        }

        return users;
    }

    @Override
    public User findById(Long id) {
        Connection connection = jdbConnectionWrapper.getConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT *FROM user WHERE id = ?;");

            preparedStatement.setLong(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {

                User user = new User();

                user.setId(resultSet.getInt("id"));
                user.setFirstName(resultSet.getString("firstName"));
                user.setLastName(resultSet.getString("lastName"));
                user.setAddress(resultSet.getString("address"));
                user.setEmail(resultSet.getString("email"));
                user.setPhone(resultSet.getString("phone"));
                user.setSalary(resultSet.getDouble("salary"));
                user.setUsername(resultSet.getString("username"));
                user.setPassword(resultSet.getString("password"));
                user.setRole(resultSet.getString("role"));

                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public User loadByUserName(String username) {
        Connection connection = jdbConnectionWrapper.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT *FROM user WHERE username = ?;");

            preparedStatement.setString(1, username);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {

                User user = new User();

                user.setId(resultSet.getInt("id"));
                user.setFirstName(resultSet.getString("firstName"));
                user.setLastName(resultSet.getString("lastName"));
                user.setAddress(resultSet.getString("address"));
                user.setEmail(resultSet.getString("email"));
                user.setPhone(resultSet.getString("phone"));
                user.setSalary(resultSet.getDouble("salary"));
                user.setUsername(resultSet.getString("username"));
                user.setPassword(resultSet.getString("password"));
                user.setRole(resultSet.getString("role"));

                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
