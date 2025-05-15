package com.vishwajay.jobbot.question;

import java.util.Arrays;
import java.util.List;

public class QuestionAnswerUtils {

    // A simple method to check if a question is common
    public static boolean isCommonQuestion(String question) {
        List<String> commonQuestions = Arrays.asList(
                "What is your expected salary?",
                "Why do you want to work here?",
                "What are your strengths?",
                "What are your weaknesses?"
        );

        return commonQuestions.contains(question);
    }
}
