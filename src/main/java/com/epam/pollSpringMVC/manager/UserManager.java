package com.epam.pollSpringMVC.manager;

import com.epam.pollSpringMVC.db.DBConnectionProvider;
import com.epam.pollSpringMVC.models.User;
import com.epam.pollSpringMVC.repository.UserRepository;

import java.sql.*;

public class UserManager implements UserRepository<User,Integer> {

    Connection connection;

    public UserManager() {
        connection = DBConnectionProvider.getInstance().getConnection();
    }


    @Override
    public void registerUser(User user) {

        int x = 0;
        try {
            String query = "INSERT INTO `plain`.`user` (`name`, `surname`, `email`, `password`, `photo_id`) VALUES (?, ?, ?, ?, ?);";
            PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getSurname());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.setInt(5, user.getPhoto_id());
            preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            if (rs.first()) {
                x = Integer.parseInt(rs.getString(1));
            }


        } catch (SQLException e) {
            throw new RuntimeException("Oops, something went wrong during create");
    }


    }

    @Override
    public User login(String email, String password) {
        User user = new User();
        try {
            String query = "SELECT * FROM `plain`.`user` WHERE `email` = ? AND `password` = ?; ";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                user.setId(resultSet.getInt("id"));
                user.setName(resultSet.getString("name"));
                user.setSurname(resultSet.getString("surname"));
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));
                user.setPhoto_id(resultSet.getInt("photo_id"));
            }

        } catch (SQLException e) {
            throw new RuntimeException("Oops, something went wrong during create");
        }
        return user;
    }

    @Override
    public boolean findByEmail(String email) {
        try {
            String query = "SELECT * FROM `plain`.`user` WHERE `email` = ? ; ";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, email);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                if (resultSet.getString("email")!=null){
                    return false;
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Oops, something went wrong during create");
        }

        return true;
    }
}
