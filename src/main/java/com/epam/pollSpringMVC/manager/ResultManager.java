package com.epam.pollSpringMVC.manager;


import com.epam.pollSpringMVC.db.DBConnectionProvider;
import com.epam.pollSpringMVC.models.Result;
import com.epam.pollSpringMVC.repository.ResultRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ResultManager implements ResultRepository {

    Connection connection;

    public ResultManager() {
        connection = DBConnectionProvider.getInstance().getConnection();
    }

    @Override
    public void addResult(Result result, String pollId) {
        try {
            String query = "INSERT INTO `plain`.`result` (`explanation`, `min_score`, `max_score`, `poll_id`) VALUES (?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, result.getExplanation());
            preparedStatement.setInt(2, result.getMaxScore());
            preparedStatement.setInt(3, result.getMinScore());
            preparedStatement.setInt(4, Integer.parseInt(pollId));
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Oops, something went wrong during create");
        }
    }

    @Override
    public Result getByPollId(String pollId) {
       Result result = new Result();
        try {
            String query = "SELECT * FROM `result` WHERE `poll_id` = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, pollId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                result.setId(resultSet.getInt("id"));
                result.setExplanation(resultSet.getString("explanation"));
               result.setMinScore(resultSet.getInt(3));
               result.setMaxScore(resultSet.getInt(4));
               result.setId(1);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Oops, something went wrong during create");
        }
        return result;
    }
    }
