package com.epam.pollSpringMVC.manager;



import com.epam.pollSpringMVC.db.DBConnectionProvider;
import com.epam.pollSpringMVC.models.Poll;
import com.epam.pollSpringMVC.repository.PollRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PollManager implements PollRepository<Poll, Integer> {

    Connection connection;

    public PollManager() {
        connection = DBConnectionProvider.getInstance().getConnection();
    }


    @Override
    public int addPoll(Poll pool) {
        int x = 0;
        try {
            String query = "INSERT INTO poll(`name`,`description`) VALUES(?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, pool.getName());
            preparedStatement.setString(2, pool.getDescription());
            preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            if (rs.first()) {
                x = Integer.parseInt(rs.getString(1));
                System.out.println(x);
            }


        } catch (SQLException e) {
            throw new RuntimeException("Oops, something went wrong during create");
        }
        return x;
    }

    @Override
    public List<Poll> findAll() {
        String query = "SELECT * FROM poll";
        List<Poll> poolList = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Poll poll = new Poll();
                poll.setId(resultSet.getInt("id"));
                poll.setName(resultSet.getString("name"));
                poll.setDescription(resultSet.getString("description"));
                poolList.add(poll);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Oops, something went wrong during get all");
        }
        return poolList;
    }

    @Override
    public Poll getByID(String id) {

        Poll poll = new Poll();
        try {
            String query = "SELECT * FROM `poll` WHERE `id` = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                poll.setId(resultSet.getInt("id"));
                poll.setName(resultSet.getString("name"));
                poll.setDescription(resultSet.getString("description"));
            }

        } catch (SQLException e) {
            throw new RuntimeException("Oops, something went wrong during create");
        }
        return poll;
    }
}


