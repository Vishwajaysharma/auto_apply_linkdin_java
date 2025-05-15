package com.vishwajay.jobbot.utils;

/*
 * LoggerUtil.java - Handles logging output
 */

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class DatabaseQuestionAnswerService {
    private final String DB_URL = "jdbc:sqlite:qa_storage.db";

    public DatabaseQuestionAnswerService() {
        try (Connection conn = DriverManager.getConnection(DB_URL)) {
            String createTable = "CREATE TABLE IF NOT EXISTS qa_pairs (question TEXT PRIMARY KEY, answer TEXT);";
            conn.createStatement().execute(createTable);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveAnswer(String question, String answer) {
        try (Connection conn = DriverManager.getConnection(DB_URL)) {
            String insert = "INSERT OR REPLACE INTO qa_pairs (question, answer) VALUES (?, ?);";
            PreparedStatement stmt = conn.prepareStatement(insert);
            stmt.setString(1, question);
            stmt.setString(2, answer);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String getAnswer(String question) {
        try (Connection conn = DriverManager.getConnection(DB_URL)) {
            String query = "SELECT answer FROM qa_pairs WHERE question = ?;";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, question);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) return rs.getString("answer");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Map<String, String> getAllAnswers() {
        Map<String, String> answers = new HashMap<>();
        try (Connection conn = DriverManager.getConnection(DB_URL)) {
            String query = "SELECT * FROM qa_pairs;";
            ResultSet rs = conn.createStatement().executeQuery(query);
            while (rs.next()) {
                answers.put(rs.getString("question"), rs.getString("answer"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return answers;
    }
}
