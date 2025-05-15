package com.vishwajay.jobbot;

import com.vishwajay.jobbot.question.*;

public class JobApplicationBot {

    public static void main(String[] args) {
        // Initialize the database and create the table
        QuestionAnswerDatabase.initializeDatabase();

        // Create a service to answer questions
        QuestionAnswerService qaService = new QuestionAnswerService();

        // Simulate a question being asked
        String question = "What is your expected salary?";
        qaService.answerQuestion(question);

        // Simulate the same question being asked again
      //  qaService.answerQuestion(question);
    }
}
