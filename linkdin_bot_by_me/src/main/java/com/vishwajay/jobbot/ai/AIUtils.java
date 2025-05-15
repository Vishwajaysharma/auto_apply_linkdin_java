package com.vishwajay.jobbot.ai;

import java.util.ArrayList;
import java.util.List;

public class AIUtils {

    // Tokenizes a job description and filters out stopwords
    public static List<String> tokenizeAndFilter(String text) {
        List<String> stopWords = List.of("the", "is", "and", "to", "in", "of", "for");
        List<String> tokens = new ArrayList<>();

        // Simple tokenization based on spaces
        String[] words = text.split("\\s+");
        for (String word : words) {
            if (!stopWords.contains(word.toLowerCase())) {
                tokens.add(word);
            }
        }
        return tokens;
    }

    // A utility method for checking if a list of tokens contains a particular word
    public static boolean containsTokens(List<String> tokens, String word) {
        return tokens.stream().anyMatch(token -> token.equalsIgnoreCase(word));
    }
}
