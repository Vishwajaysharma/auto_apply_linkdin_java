package com.vishwajay.jobbot.question;

import java.util.Scanner;

public class QuestionAnswerService {

    public void answerQuestion(String question) {
        // Check if the question has already been answered
        String previousAnswer = QuestionAnswerDatabase.getAnswerByQuestion(question);
        if (previousAnswer != null) {
            System.out.println("Answering from memory: " + previousAnswer);
        } else {
            // If the question is new, ask the user for an answer and store it
            Scanner scanner = new Scanner(System.in);
            System.out.println("New question: " + question);
            System.out.print("Please provide an answer: ");
            String answer = scanner.nextLine();

            // Store the new question and answer in the database
            QuestionAnswerDatabase.storeQuestionAnswer(new QuestionAnswer(question, answer));

            System.out.println("Answer stored: " + answer);
        }
    }

	
}
