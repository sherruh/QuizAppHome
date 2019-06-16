package com.geektech.quizapp.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.geektech.quizapp.data.db.converters.QuestionConverter;
import com.geektech.quizapp.data.db.converters.TimestampConverter;
import com.geektech.quizapp.model.enums.Difficulty;
import com.geektech.quizapp.model.enums.Type;

import java.util.Date;
import java.util.List;

@Entity(tableName = "quiz_result")
public class QuizResult {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "questions")
    @TypeConverters({QuestionConverter.class})
    private List<Question> questions;

    @ColumnInfo(name = "correct_answers")
    private int correctAnswers;

    @ColumnInfo(name = "created_at")
    @TypeConverters({TimestampConverter.class})
    private Date createdAt;

    @ColumnInfo(name = "difficulty")
    private String difficulty;

    @ColumnInfo(name = "category")
    private String category;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public int getCorrectAnswers() {
        return correctAnswers;
    }

    public void setCorrectAnswers(int correctAnswers) {
        this.correctAnswers = correctAnswers;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public QuizResult(int id, List<Question> questions, int correctAnswers,
                      Date createdAt, String difficulty, String category) {
        this.id = id;
        this.questions = questions;
        this.correctAnswers = correctAnswers;
        this.createdAt = createdAt;
        this.difficulty = difficulty;
        this.category = category;
    }
}
