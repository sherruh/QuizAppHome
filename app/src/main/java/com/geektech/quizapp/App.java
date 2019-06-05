package com.geektech.quizapp;

import android.app.Application;

import com.geektech.quizapp.data.IQuizRepository;
import com.geektech.quizapp.data.QuizRepository;

public class App extends Application {
    public static IQuizRepository quizRepository;

    @Override
    public void onCreate() {
        super.onCreate();
        quizRepository = new QuizRepository();
    }
}
