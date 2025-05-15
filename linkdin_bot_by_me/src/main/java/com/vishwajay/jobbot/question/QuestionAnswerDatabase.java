package com.vishwajay.jobbot.question;

import org.sqlite.SQLiteDataSource;
import java.sql.*;

public class QuestionAnswerDatabase {

    private static final String DB_URL = "jdbc:sqlite:question_answers.db";

    // Create a new SQLite connection
    public static Connection getConnection() throws SQLException {
        SQLiteDataSource dataSource = new SQLiteDataSource();
        dataSource.setUrl(DB_URL);
        return dataSource.getConnection();
    }

    // Initialize the database (creating the table if not exists)
    public static void initializeDatabase() {
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement()) {

            String createTableSQL = "CREATE TABLE IF NOT EXISTS QuestionAnswers (" +
                    "question TEXT PRIMARY KEY," +
                    "answer TEXT NOT NULL" +
                    ")";
            stmt.execute(createTableSQL);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Store a new question and answer in the database
    public static void storeQuestionAnswer(QuestionAnswer qa) {
        String insertSQL = "INSERT OR REPLACE INTO QuestionAnswers (question, answer) VALUES (?, ?)";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(insertSQL)) {

            pstmt.setString(1, qa.getQuestion());
            pstmt.setString(2, qa.getAnswer());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Get an answer by the question
    public static String getAnswerByQuestion(String question) {
        String querySQL = "SELECT answer FROM QuestionAnswers WHERE question = ?";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(querySQL)) {

            pstmt.setString(1, question);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return rs.getString("answer");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}




