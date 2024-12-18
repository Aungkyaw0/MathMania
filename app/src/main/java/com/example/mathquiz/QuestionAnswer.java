package com.example.mathquiz;

public class QuestionAnswer {
    public static String[] question = {
            "What is the result of 4 x 3 ?",
            "What is the answer of 11 - 6 ?",
            "? + 3 = 11. What is the answer?",
            "4 x ? = 12. What is the answer?",
            "3 + ? + 2 = 11. What is the answer?",
            "What is 7 + 4?",
            "What is 6 - 2?",
            "What is 2 x 5?",
            "What is 8 - 4?",
            "What is 12 - 4"
    };

    public static String[][] choices = {
            {"6", "9", "11", "12"},
            {"4", "6", "5", "7"},
            {"7", "6", "8", "9"},
            {"2", "3", "4", "5"},
            {"5", "6", "7", "8"},
            {"11", "12", "10", "9"},
            {"4", "3", "2", "1"},
            {"8", "10", "12", "14"},
            {"3", "4", "2", "1"},
            {"7", "8", "9", "10"}
    };

    public static String[] correctAnswer = {"12", "5", "8", "3", "6", "11", "4", "10", "4", "8"};
}

