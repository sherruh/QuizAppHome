package com.geektech.quizapp.model;

import com.geektech.quizapp.model.enums.Difficulty;
import com.geektech.quizapp.model.enums.Type;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Question {
    @SerializedName("category")
    private String category;

    @SerializedName("type")
    private Type type;

    @SerializedName("difficulty")
    private Difficulty difficulty;

    @SerializedName("question")
    private String question;

    @SerializedName("correct_answer")
    private String correctAnswer;

    @SerializedName("incorrect_answers")
    private List<String> incorrectAnswers;

    private String selectedAnswer;

    private int selectedAnswerPosition;

    private ArrayList<String> allAnswers;

    private boolean isAnswered;

    public Question(String category, Type type, Difficulty difficulty, String question, String correctAnswer, List<String> incorrectAnswers) {
        this.category = category;
        this.type = type;
        this.difficulty = difficulty;
        this.question = question;
        this.correctAnswer = correctAnswer;
        this.incorrectAnswers = incorrectAnswers;
        isAnswered = false;
    }

    public void shuffleAnswers(){
        allAnswers = new ArrayList<>();
        allAnswers.addAll(incorrectAnswers);
        allAnswers.add(correctAnswer);
        Collections.shuffle(allAnswers);
    }

    public void setSelectedAnswerPosition(int selectedAnswerPosition) {
        this.selectedAnswerPosition = selectedAnswerPosition;
        if(selectedAnswerPosition == 99) setSelectedAnswer("");
        else if(type == Type.MULTIPLE) setSelectedAnswer(getAllAnswers().get(selectedAnswerPosition));
        else if(selectedAnswerPosition == 0) setSelectedAnswer("True");
        else setSelectedAnswer("False");
    }

    public List<String> getAllAnswers() {
        return allAnswers;
    }

    public int getSelectedAnswerPosition() {
        return selectedAnswerPosition;
    }

    public void setAllAnswers(ArrayList<String> allAnswers) {
        this.allAnswers = allAnswers;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public List<String> getIncorrectAnswers() {
        return incorrectAnswers;
    }

    public void setIncorrectAnswers(List<String> incorrectAnswers) {
        this.incorrectAnswers = incorrectAnswers;
    }

    @Override
    public String toString() {
        return question+" " + correctAnswer;
    }

    public String getSelectedAnswer() {
        return selectedAnswer;
    }

    public void setSelectedAnswer(String selectedAnswer) {
        this.selectedAnswer = selectedAnswer;
    }

    public boolean isAnswered() {
        return isAnswered;
    }

    public void setAnswered(boolean answered) {
        isAnswered = answered;
    }
}
