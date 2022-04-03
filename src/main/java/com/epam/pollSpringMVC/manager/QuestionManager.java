package com.epam.pollSpringMVC.manager;


import com.epam.pollSpringMVC.db.DBConnectionProvider;
import com.epam.pollSpringMVC.models.Question;
import com.epam.pollSpringMVC.repository.QuestionRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class QuestionManager implements QuestionRepository<Question, Integer> {
    Connection connection;

    public QuestionManager() {

        connection = DBConnectionProvider.getInstance().getConnection();
    }
AnswerManager answerManager = new AnswerManager();
    @Override
    public int addQuestions(Question question, String id) {
        int x = 0;
        try {
            String query = "INSERT INTO question(`text`,`pool_id`) VALUES(?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, question.getText());
            preparedStatement.setString(2, id);
            preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            if (rs.first()) {
                x = Integer.parseInt(rs.getString(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Oops, something went wrong during create");
        }
        return x;
    }

    @Override
    public List<Question> getByPollId(int id) {

        List <Question> questionList = new ArrayList<>();
        try {

            String query = "SELECT * FROM `plain`.`question` WHERE `pool_id` = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Question question = new Question();
                question.setId(resultSet.getInt(1));
                question.setText(resultSet.getString("text"));
                question.setAnswers(answerManager.findByQuestionId(question.getId()));
                questionList.add(question);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Oops, something went wrong during create");
        }
        return questionList;
    }


}
