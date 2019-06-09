package com.geektech.quizapp.data;

import com.geektech.quizapp.model.Question;

import java.util.List;

public interface IQuizRepository {

    void getQuestions(int amount,String type,String difficulty, QuestionsCallback callback);

    interface QuestionsCallback {
        void onSuccess(List<Question> questions);

        void onFailure(String message);
    }
}
