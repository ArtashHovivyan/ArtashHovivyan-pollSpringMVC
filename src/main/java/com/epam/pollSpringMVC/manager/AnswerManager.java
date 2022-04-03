package com.epam.pollSpringMVC.manager;



import com.epam.pollSpringMVC.db.DBConnectionProvider;
import com.epam.pollSpringMVC.models.Answer;
import com.epam.pollSpringMVC.repository.AnswerRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AnswerManager implements AnswerRepository<Answer, Integer> {

    Connection connection;

    public AnswerManager() {
        connection = DBConnectionProvider.getInstance().getConnection();
    }


    @Override
    public void addAnswer(Answer answer, int id) {
        try {
            String query = "INSERT INTO `plain`.`answer`(`text`,`weight`,`question_id`) VALUES(?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, answer.getText());
            preparedStatement.setInt(2, answer.getWeight());
            preparedStatement.setInt(3, id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Oops, something went wrong during create");
        }

    }

    @Override
    public List<Answer> findByQuestionId(int id) {
        String query = "SELECT * FROM `plain`.`answer` WHERE `question_id` = ?";
        List<Answer> answerList = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Answer answer = new Answer();
                answer.setId(resultSet.getInt(1));
                answer.setText(resultSet.getString("text"));
                answer.setWeight(resultSet.getInt("weight"));
                answerList.add(answer);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Oops, something went wrong during create");
        }
        return answerList;
    }
}